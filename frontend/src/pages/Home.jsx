import React from 'react';
import Header from '../components/Header';

const Home = ({user,handleLogout}) => {
    return (
        <div>
            <Header isLoggedIn={true} user={user} handleLogout={handleLogout} />
        </div>
    );
};

export default Home;
