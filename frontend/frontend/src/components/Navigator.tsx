import * as React from 'react';
import Divider from '@mui/material/Divider';
import Drawer from '@mui/material/Drawer';
import List from '@mui/material/List';
import Box from '@mui/material/Box';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import PeopleIcon from '@mui/icons-material/People';
import FolderIcon from '@mui/icons-material/Folder';
import FileUploadIcon from '@mui/icons-material/FileUpload';import PersonIcon from '@mui/icons-material/Person';
import LogoutIcon from "@mui/icons-material/Logout";
import Button from "@mui/material/Button";

const item = {
    py: '2px',
    px: 3,
    color: 'rgba(255, 255, 255, 0.7)',
    '&:hover, &:focus': {
        bgcolor: 'rgba(255, 255, 255, 0.08)',
    },
};

const itemCategory = {
    boxShadow: '0 -1px 0 rgb(255,255,255,0.1) inset',
    py: 1.5,
    px: 3,
};

export default function Navigator(props: any) {
    const { ...other } = props;
    // const [ active, setActive ] = React.useState<string>('Directory');

    const categories = [
        {
            id: 'File Management',
            children: [
                {
                    id: 'Directory',
                    icon: <FolderIcon />,
                    active: props.active === 'Directory'
                },
                {
                    id: 'File Upload',
                    icon: <FileUploadIcon />,
                    active: props.active === 'File Upload'
                },
            ],
        },
        {
            id: 'User Management',
            children: [
                {
                    id: 'Groups',
                    icon: <PeopleIcon />,
                    active: props.active === 'Groups'
                },
                {
                    id: 'Users',
                    icon: <PersonIcon />,
                    active: props.active === 'Users'
                },
            ],
        },
    ];

    return (
        <Drawer variant="permanent" {...other}>
            <List disablePadding>
                <ListItem sx={{ ...item, ...itemCategory, fontSize: 22, color: '#FFFFFFB2' }}>
                    FileMan
                </ListItem>
                {categories.map(({ id, children }) => (
                    <Box key={id} sx={{ bgcolor: '#101F33' }}>
                        <ListItem sx={{ py: 2, px: 3 }}>
                            <ListItemText sx={{ color: '#FFFFFFB2' }}>{id}</ListItemText>
                        </ListItem>
                        {children.map(({ id: childId, icon, active }) => (
                            <ListItem disablePadding key={childId}>
                                <ListItemButton
                                    selected={active}
                                    sx={item}
                                    onClick={() => {
                                        props.setActive(childId);
                                    }}
                                >
                                    <ListItemIcon>{icon}</ListItemIcon>
                                    <ListItemText>{childId}</ListItemText>
                                </ListItemButton>
                            </ListItem>
                        ))}
                        <Divider sx={{ mt: 2 }} />
                    </Box>
                ))}
                <Button
                    onClick={() => {
                        sessionStorage.clear();
                        window.location.href = '/';
                    }}
                    sx={{
                        p: 0.5,
                        width: '80%',
                        color: 'rgba(255, 255, 255, 0.7)',
                        '&:hover, &:focus': {
                            bgcolor: 'rgba(255, 255, 255, 0.08)',
                        },
                }}>
                    <LogoutIcon />
                </Button>
            </List>
        </Drawer>
    );
}