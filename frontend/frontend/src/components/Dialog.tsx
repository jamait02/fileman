import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import {ControllerService} from "../services/openapi";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";

export default function FormDialog(props: any) {
    const [ path, setPath ] = React.useState<string>('');
    const [ type, setType ] = React.useState<string>('Directory');

    function separatePath(path: string): { directory: string, filename: string } {
        const lastSeparatorIndex = path.lastIndexOf('/');
        const directory = path.substring(0, lastSeparatorIndex);
        const filename = path.substring(lastSeparatorIndex + 1);

        return { directory, filename };
    }

    return (
        <div>
            <Dialog
                open={props.openDialog}
            >
                <DialogTitle>Path Name</DialogTitle>
                <DialogContent
                    sx={{
                        marginTop: '20px',
                        width: '500px',
                    }}
                >
                    <FormControl
                        sx={{
                            marginTop: '20px',
                            marginBottom: '40px',
                        }}
                        fullWidth
                    >
                        <InputLabel id="demo-simple-select-label">Item Type</InputLabel>
                        <Select
                            labelId="demo-simple-select-label"
                            id="demo-simple-select"
                            value={type}
                            label="Item Type"
                            onChange={(e) => setType(e.target.value)}
                        >
                            <MenuItem value={'Directory'}>Directory</MenuItem>
                            <MenuItem value={'File'}>File</MenuItem>
                        </Select>
                    </FormControl>
                    <DialogContentText>
                        Enter the path you want to create...
                    </DialogContentText>
                    <TextField
                        autoFocus
                        margin="dense"
                        id="name"
                        label="Path"
                        type="path"
                        fullWidth
                        variant="standard"
                        onChange={(event) => setPath(event.target.value)}
                    />
                </DialogContent>
                <DialogActions>
                    <Button
                        onClick={() => props.setOpenDialog(false)}
                    >
                        Cancel
                    </Button>
                    <Button
                        onClick={() => {
                            if (type === 'Directory') {
                                try {
                                    ControllerService.addDirectory(
                                        path,
                                        props.user.id,
                                        props.user.id
                                    ).then((response) => {
                                        if (response > 0) {
                                            ControllerService.getDirectoryByPath(
                                                path
                                            ).then((response) => {
                                                if (response.id) {
                                                    ControllerService.addDirectoryAccess(
                                                        response.id,
                                                        props.user.groupId,
                                                        props.user.id
                                                    ).then((response) => {
                                                        if (response > 0) {
                                                            props.setOpenSnackbar(true);
                                                            props.setMessage('Successfully created directory!');
                                                            props.setSeverity('success');
                                                            props.getDirectoryAccess();
                                                        } else {
                                                            props.setOpenSnackbar(true);
                                                            props.setMessage('Failed to create directory access!');
                                                            props.setSeverity('error');
                                                        }
                                                    })
                                                } else {
                                                    props.setOpenSnackbar(true);
                                                    props.setMessage('Failed to find directory!');
                                                    props.setSeverity('error');
                                                }
                                            })
                                        } else {
                                            props.setOpenSnackbar(true);
                                            props.setMessage('Failed to create directory!');
                                            props.setSeverity('error');
                                        }
                                    })
                                } catch (e: any) {
                                    console.log(e);
                                    props.setOpenSnackbar(true);
                                    props.setMessage(e);
                                    props.setSeverity('error');
                                }
                            } else {
                                try {
                                    ControllerService.getDirectoryByPath(
                                        separatePath(path).directory
                                    ).then((response) => {
                                        if (response.id) {
                                            ControllerService.addFile(
                                                separatePath(path).filename,
                                                response.id,
                                                props.user.id,
                                                props.user.id
                                            ).then((response) => {
                                                if (response > 0) {
                                                    props.setOpenSnackbar(true);
                                                    props.setMessage('Successfully created file!');
                                                    props.setSeverity('success');
                                                    props.getDirectoryAccess();
                                                } else {
                                                    props.setOpenSnackbar(true);
                                                    props.setMessage('Failed to create file!');
                                                    props.setSeverity('error');
                                                }
                                                // if (response > 0) {
                                                //     ControllerService.addDirectory(
                                                //         separatePath(path).directory,
                                                //         props.user.id,
                                                //         props.user.id
                                                //     ).then((response) => {
                                                //         if (response > 0) {
                                                //             props.setOpenSnackbar(true);
                                                //             props.setMessage('Successfully created file!');
                                                //             props.setSeverity('success');
                                                //             props.getDirectoryAccess();
                                                //         } else {
                                                //             props.setOpenSnackbar(true);
                                                //             props.setMessage('Failed to create file!');
                                                //             props.setSeverity('error');
                                                //         }
                                                //     })
                                                // } else {
                                                //     props.setOpenSnackbar(true);
                                                //     props.setMessage('Failed to create file!');
                                                //     props.setSeverity('error');
                                                // }
                                            })
                                        } else {
                                            props.setOpenSnackbar(true);
                                            props.setMessage('Failed to find directory!');
                                            props.setSeverity('error');
                                        }
                                    })
                                } catch (e: any) {
                                    console.log(e);
                                    props.setOpenSnackbar(true);
                                    props.setMessage(e);
                                    props.setSeverity('error');
                                }
                            }
                            props.setOpenDialog(false)
                        }}
                    >
                        Save
                    </Button>
                </DialogActions>
            </Dialog>
        </div>
    );
}