import React from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';
import '../style.css';

const Header = ({ isLoggedIn, user, handleLogout }) => {
  return (
    <Navbar bg="light" expand="lg" className="header-navbar">
      <Navbar.Brand href="#">Note App</Navbar.Brand>
      <Button variant="success" className="ml-2">Add Note</Button>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="ml-auto d-flex justify-content-between w-100">
          <div className="nav-left">
            {/* Add any additional left-side navigation items here */}
          </div>
          <div className="nav-right">
            {isLoggedIn ? (
              <div className="d-flex align-items-center gap-4">
                <Navbar.Text className="mr-3">Signed in as: {user}</Navbar.Text>
                <Button variant="outline-danger" onClick={handleLogout}>Logout</Button>
              </div>
            ) : (
              <Button variant="outline-success" href="/login">Login</Button>
            )}
          </div>
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
