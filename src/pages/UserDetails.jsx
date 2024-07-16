// src/components/UserDetails.js
import React from 'react';

const UserDetails = ({ user }) => {
    return (
        <div>
            <h1>Welcome, {user.username}</h1>
            <p>User Details:</p>
            <pre>
        {`
        Username: ${user.username}
        Password: ${user.password}
        `}
    </pre>
        </div>
    );
};

export default UserDetails;