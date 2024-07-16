// src/App.js
import React, { useState } from 'react';
import Login from '../src/pages/login';
import Signup from '../src/pages/signup';
import UserDetails from '../src/pages/UserDetails';

const App = () => {
  const [user, setUser] = useState(null);
  const [isSignupSuccessful, setIsSignupSuccessful] = useState(false);

  const handleLoginSuccess = (user) => {
    setUser(user);
  };

  const handleSignupSuccess = () => {
    setIsSignupSuccessful(true);
  };

  return (
      <div>
        {!user ? (
            <div>
              <h1>Login</h1>
              {isSignupSuccessful && <p>Signup successful! Please log in.</p>}
              <Login onLoginSuccess={handleLoginSuccess} />
              <h2>Signup</h2>
              <Signup onSignupSuccess={handleSignupSuccess} />
            </div>
        ) : (
            <UserDetails user={user} />
        )}
      </div>
  );
};

export default App;