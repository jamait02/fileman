import * as React from 'react';
import {ThemeProvider} from '@mui/material/styles';
import useMediaQuery from '@mui/material/useMediaQuery';
import CssBaseline from '@mui/material/CssBaseline';
import Box from '@mui/material/Box';
import Navigator from '../components/Navigator';
import Content from '../components/Content';
import {theme} from "../theme/theme";
import {Card} from "@mui/material";
import {useEffect} from "react";
import {ControllerService, Directory, DirectoryAccess, File, FileAccess, User} from "../services/openapi";
import Snackbar from "../components/Snackbar";
import Header from "../components/Header";
import FormDialog from "../components/Dialog";

const drawerWidth = 256;

export default function Root() {
    const [mobileOpen, setMobileOpen] = React.useState(false);
    const isSmUp = useMediaQuery(theme.breakpoints.up('sm'));

    const handleDrawerToggle = () => {
        setMobileOpen(!mobileOpen);
    };

    const [active, setActive] = React.useState<string>('Directory');
    const [ files, setFiles ] = React.useState<any>([]);


    useEffect(() => {
        switch (active) {
            case "Directory":
                getFileAccess();
                getDirectoryAccess();
                break;
            case "File Upload":
                console.log("File Upload");
                break;
        }
    }, [active]);

    useEffect(() => {
        getDirectoryAccess();
    }, [files]);

    const [open, setOpen] = React.useState(false);
    const [message, setMessage] = React.useState('');
    const [severity, setSeverity] = React.useState('success');


    const user: User = JSON.parse(sessionStorage.getItem('user') as string); // Get the user from the session storage
    const [data, setData] = React.useState<any>([]);

    async function getDirectoryAccess() {
        const directoryAccess: DirectoryAccess[] = [];
        try {
            await ControllerService.getDirectoryAccessByGroupId(user.groupId as number).then((response) => {
                response.forEach((directory) => {
                    directoryAccess.push(directory);
                })
                ControllerService.getDirectoriesByDirectoryIds(directoryAccess.map((directory) => {
                    return directory.directory_id as number;
                })).then((response) => {
                    let directoryPaths: Directory[] = [];
                    response.forEach((directory) => {
                        files.forEach((file: File) => {
                            if (file.directory_id === directory.id) {
                                directoryPaths.push({
                                    ...directory,
                                    path: directory.path + '/' + file.name,
                                });
                            } else {
                                directoryPaths.push(directory);
                            }
                        })
                    })
                    console.log(directoryPaths);
                    setData(directoryPaths);
                })
            })
        } catch (e: any) {
            console.log(e);
            setOpen(true);
            setMessage(e);
            setSeverity('error');
        }
        return directoryAccess;
    }


    async function getFileAccess() {
        const fileAccess: FileAccess[] = [];
        try {
            await ControllerService.getFileAccessByGroupId(user.groupId as number).then((response) => {
                response.forEach((file) => {
                    fileAccess.push(file);
                })
                ControllerService.getFilesByFileIds(fileAccess.map((file) => {
                    return file.file_id as number;
                })).then((response) => {
                    console.log(response)
                    setFiles(response);
                })
            })
        } catch (e: any) {
            console.log(e);
            setOpen(true);
            setMessage(e);
            setSeverity('error');
        }
        return fileAccess;
    }


    const [ openDialog, setOpenDialog ] = React.useState(false);
    useEffect(() => {
        console.log(openDialog)
    }, [openDialog]);

    return (
        <ThemeProvider theme={theme}>
            <Box sx={{
                display: 'flex',
                minHeight: '95vh',
                width: '95%'
            }}>
                <CssBaseline/>
                <Box
                    component="nav"
                    sx={{
                        width: {sm: drawerWidth},
                        flexShrink: {sm: 0}
                    }}
                >
                    {isSmUp ? null : (
                        <Navigator
                            active={active}
                            setActive={setActive}
                            PaperProps={{
                                style: {width: drawerWidth}
                            }}
                            variant="temporary"
                            open={mobileOpen}
                            onClose={handleDrawerToggle}
                        />
                    )}
                    <Navigator
                        active={active}
                        setActive={setActive}
                        PaperProps={{
                            style: {width: drawerWidth}
                        }}
                        sx={{
                            display: {sm: 'block', xs: 'none'}
                        }}
                    />
                </Box>
                <Card sx={{
                    flex: 1,
                    display: 'flex',
                    flexDirection: 'column'
                }}>
                    <Header
                        setOpen={setOpenDialog}
                    />
                    <Box component="main" sx={{
                        flex: 1,
                        py: 6,
                        px: 4,
                        bgcolor: '#eaeff1'
                    }}>
                        <Content
                            active={active}
                            data={data}
                        />
                    </Box>
                    <Box component="footer" sx={{
                        p: 2,
                        bgcolor: '#eaeff1'
                    }}>
                    </Box>
                </Card>
                <Snackbar
                    open={open}
                    setOpen={setOpen}
                    message={message}
                    severity={severity}
                />
                <FormDialog
                    openDialog={openDialog}
                    setOpenDialog={setOpenDialog}
                    setOpenSnackbar={setOpen}
                    setMessage={setMessage}
                    setSeverity={setSeverity}
                    getDirectoryAccess={getDirectoryAccess}
                    user={user}
                />
            </Box>
        </ThemeProvider>
    );
}