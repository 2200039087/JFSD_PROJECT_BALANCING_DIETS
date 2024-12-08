import React, { useState } from 'react';
import FormInput from './FormInput.jsx';
import './Styles/signup.css';
import logo from './Styles/nutriminds-logo.png';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';

function Register({ onClose }) {
  const [formData, setFormData] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    role: 'USER', // Default role
  });

  const navigate = useNavigate();
  const [responseMessage, setResponseMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Basic password confirmation validation
    if (formData.password !== formData.confirmPassword) {
      setResponseMessage('Passwords do not match!');
      return;
    }

    try {
      setIsLoading(true);

      // Make API request to backend for registration
      const response = await axios.post('http://localhost:8080/api/auth/register', {
        email: formData.email,
        password: formData.password,
        role: formData.role,
      });

      // Assuming the backend returns a token upon successful registration
      const token = response.data.token;
      const user = response.data.user;

      // Store token and user data in local storage
      localStorage.setItem('authToken', token);
      localStorage.setItem('user', JSON.stringify(user));

      setResponseMessage(response.data.message || 'Registration successful!');

      // Redirect to the home page after successful registration
      navigate('/');
    } catch (error) {
      console.error('Error during registration:', error);
      setResponseMessage(error.response?.data?.message || 'An error occurred. Please try again.');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="register-container">
      <header className="navbar">
        <div className="navbar-logo">
          <img src={logo} alt="NutriMinds Logo" className="logo" />
          <span className="navbar-title">NutriMinds</span>
        </div>
        <nav className="navbar-links">
          <Link to="/">Home</Link>
          <Link to="/about">About Us</Link>
          <Link to="/contact">Contact</Link>
        </nav>
      </header>

      <div className="form-container">
        <h2 className="register-title">Create Your Account</h2>
        <form onSubmit={handleSubmit}>
          <FormInput
            label="Email"
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
            required
          />
          <FormInput
            label="Password"
            type="password"
            name="password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <FormInput
            label="Confirm Password"
            type="password"
            name="confirmPassword"
            value={formData.confirmPassword}
            onChange={handleChange}
            required
          />
          <button type="submit" className="register-button">
            {isLoading ? 'Registering...' : 'Register'}
          </button>
        </form>
        {responseMessage && <p className="response-message">{responseMessage}</p>}
        <button onClick={onClose} className="back-button">Back</button>
      </div>
    </div>
  );
}

export default Register;
