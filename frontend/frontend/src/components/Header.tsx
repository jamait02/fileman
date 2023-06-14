import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Grid from '@mui/material/Grid';
import IconButton from '@mui/material/IconButton';
import Toolbar from '@mui/material/Toolbar';
import LogoutIcon from '@mui/icons-material/Logout';
import AddIcon from "@mui/icons-material/Add";
import DeleteIcon from '@mui/icons-material/Delete';

export default function Header(props: any) {
    return (
        <React.Fragment>
            <AppBar color="primary" position="sticky" elevation={0}>
                <Toolbar>
                    <Grid container spacing={1} alignItems="center">
                        <Grid item>
                            <IconButton
                                color="inherit"
                                sx={{ p: 0.5 }}
                                onClick={() => props.setOpen(true)}
                            >
                                <AddIcon />
                            </IconButton>
                        </Grid>
                        <Grid item>
                            <IconButton
                                color="inherit"
                                sx={{ p: 0.5 }}
                            >
                                <DeleteIcon />
                            </IconButton>
                        </Grid>
                        <Grid item xs />
                        <Grid item>
                            <IconButton
                                onClick={() => {
                                    sessionStorage.clear();
                                    window.location.href = '/';
                                }}
                                color="inherit"
                                sx={{ p: 0.5 }}>
                                <LogoutIcon />
                            </IconButton>
                        </Grid>
                    </Grid>
                </Toolbar>
            </AppBar>
        </React.Fragment>
    );
}