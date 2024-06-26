import React, { useState } from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Navigate } from 'react-router-dom';
import Home from './pages/Home';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [user, setUser] = useState('');

  const handleLogin = (email) => {
    setIsLoggedIn(true);
    setUser(email);
  };

  const handleLogout = () => {
    setIsLoggedIn(false);
    setUser('');
  };
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route exact path="/login" element={<Login onLogin={handleLogin}/>} />
          <Route exact path='/register' element={<Register />} />
          {/* <Route exact path="/home" element={<Home />} /> */}
          <Route path="/home" element={isLoggedIn ? <Home user={user} handleLogout={handleLogout} /> : <Navigate to="/login" />} />
          <Route path="*" element={<Navigate to="/login" />} />
        </Routes>
      </Router>
    </div>
  );
}

export default App;
