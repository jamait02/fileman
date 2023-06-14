import {Alert, Snackbar} from "@mui/material";
import React from "react";

export default (props: any) => {
    return (
        <Snackbar open={props.open} autoHideDuration={6000}>
            <Alert severity={props.severity} sx={{ width: '100%' }}>
                {props.message}
            </Alert>
        </Snackbar>
    )
}