import React, { useState } from 'react';
import axios from 'axios';
import { Form, Button, Container, Row, Col, Alert } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import { Link } from 'react-router-dom'
import '../style.css';

const Register = () => {
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');
    const navigate = useNavigate();

    const handleRegister = async (e) => {

        e.preventDefault();
        setError('');
        setSuccess('');

        

        if (password !== confirmPassword) {
            setError('Passwords do not match');
            return;
        }

        try {
            const response = await axios.post('http://localhost:8080/api/user/', {
                username,
                email,
                password
            });

            if (response.status === 201) {
                setSuccess('Registration successful! Please login.');
                navigate('/login');
            }
        } catch (err) {
            setError('Registration failed. Please try again.');
        }
    };

    return (
        <div className="login-background">
            <Container className="login-shape w-25">
                <Row className="justify-content-md-center">
                    <Col md="10" className="d-flex flex-column">
                        <h2 className="text-center mb-4">Register</h2>
                        <Form onSubmit={handleRegister}>
                            <Form.Group controlId="formBasicName" className="mb-3">
                                <Form.Label>Username</Form.Label>
                                <Form.Control
                                    type="text"
                                    placeholder="Enter Username"
                                    value={username}
                                    onChange={(e) => setUsername(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicEmail" className="mb-3">
                                <Form.Label>Email</Form.Label>
                                <Form.Control
                                    type="email"
                                    placeholder="Enter email"
                                    value={email}
                                    onChange={(e) => setEmail(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicPassword" className="mb-3">
                                <Form.Label>Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    placeholder="Password"
                                    value={password}
                                    onChange={(e) => setPassword(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Form.Group controlId="formBasicConfirmPassword" className="mb-3">
                                <Form.Label>Confirm Password</Form.Label>
                                <Form.Control
                                    type="password"
                                    placeholder="Confirm Password"
                                    value={confirmPassword}
                                    onChange={(e) => setConfirmPassword(e.target.value)}
                                    required
                                />
                            </Form.Group>

                            <Button variant="primary" type="submit" className="w-100">
                                Register
                            </Button>
                        </Form>
                        {error && <Alert variant="danger" className="mt-3">{error}</Alert>}
                        {success && <Alert variant="success" className="mt-3">{success}</Alert>}
                        <div className="mt-3 text-center">
                            Already have an account?{' '}
                            <Link to="/login">
                                Login here
                            </Link>
                        </div>
                    </Col>
                </Row>
            </Container>
        </div>
    );
};

export default Register;