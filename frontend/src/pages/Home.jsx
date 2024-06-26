import React from 'react';
import Header from '../components/Header';

const Home = ({user,handleLogout}) => {
    return (
        <div>
            <Header isLoggedIn={true} user={user} handleLogout={handleLogout} />
            <h1>Welcome to the Home Page</h1>
        </div>
    );
};

export default Home;
