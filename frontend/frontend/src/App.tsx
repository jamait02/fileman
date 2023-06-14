import React from 'react';
import './App.css';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import {Navigate, Route, Routes} from "react-router-dom";
import SignIn from "./routes/SignIn";
import Root from "./routes/Root";
import SignUp from "./routes/SignUp";
import NotFound from "./routes/NotFound";
import {User} from "./services/openapi";

function App() {
    const isAuthenticated = () => {
        const user: User = JSON.parse(sessionStorage.getItem('user') as string); // Get the user from the session storage
        return !!user; // Return true if the user exists, otherwise false
    };

    return (
            <div className="App">
                <header className="App-header">
                    <Routes>
                        <Route path="/" element={ isAuthenticated() ? <Root /> : <Navigate to="/sign-in" /> } />
                        <Route path="/sign-in" element={ isAuthenticated() ? <Navigate to="/" /> : <SignIn /> } />
                        <Route path="/sign-up" element={ isAuthenticated() ? <Navigate to="/" /> : <SignUp /> } />
                        <Route path="*" element={<NotFound />} />
                    </Routes>
                </header>
            </div>
    );
}

export default App;
