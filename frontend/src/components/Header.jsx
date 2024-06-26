import React from 'react';
import { Navbar, Nav, Button } from 'react-bootstrap';

const Header = ({ isLoggedIn, user, handleLogout }) => {
  return (
    <Navbar bg="light" expand="lg">
      <Navbar.Brand href="#">Note App</Navbar.Brand>
      <Navbar.Toggle aria-controls="basic-navbar-nav" />
      <Navbar.Collapse id="basic-navbar-nav">
        <Nav className="ml-auto">
          {isLoggedIn ? (
            <>
              <Navbar.Text className="mr-3">Signed in as: {user}</Navbar.Text>
              <Button variant="outline-danger" onClick={handleLogout}>Logout</Button>
            </>
          ) : (
            <Button variant="outline-success" href="/login">Login</Button>
          )}
        </Nav>
      </Navbar.Collapse>
    </Navbar>
  );
};

export default Header;
