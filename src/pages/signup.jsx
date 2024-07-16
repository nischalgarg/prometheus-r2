// src/components/Signup.js
import React, { useState } from 'react';
import axiosApiInstance from "./axios.config";
import {API_ROUTES} from "../routes";

function isNullOrEmpty(str) {
    return !str || str.length === 0;
}

const Signup = ({ onSignupSuccess }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        const response = await axiosApiInstance.post(API_ROUTES.SIGNUP, {
            username: username,
            password: password,
        });
        console.log(response)
        if (response && response.data && response.data.resultCode === 200) {
            onSignupSuccess();
        } else if (response && response.data && !isNullOrEmpty(response.data.message)){
            alert(response.data.message);
        }
        else {
            alert('Signup failed');
        }
    };

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Username:</label>
                <input type="text" value={username} onChange={(e) => setUsername(e.target.value)} />
            </div>
            <div>
                <label>Password:</label>
                <input type="password" value={password} onChange={(e) => setPassword(e.target.value)} />
            </div>
            <button type="submit">Signup</button>
        </form>
    );
};

export default Signup;