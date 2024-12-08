import React, { useState } from 'react';
import axios from 'axios';
import './Styles/Login.css';
import logo from './Styles/nutriminds-logo.png';
import { useNavigate } from 'react-router-dom';

function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    // Check if email and password are provided
    if (!email || !password) {
      setErrorMessage('Please fill in both fields.');
      return;
    }

    // Clear previous error messages
    setErrorMessage('');
    setLoading(true); // Set loading state

    try {
      // Send the login data to the backend API
      const response = await axios.post('http://localhost:8080/api/auth/login', { email, password });
      // Assuming the backend returns a token and user data on successful login
      const token = response.data.token;
      const user = response.data.user;

      // Store token and user data in local storage
      localStorage.setItem('authToken', token);
      localStorage.setItem('user', JSON.stringify(user));

      // Redirect or perform any other action on successful login
      navigate('/'); // Replace with your desired redirect
    } catch (error) {
      // Handle any errors during the login request
      setErrorMessage('Invalid email or password.');
    } finally {
      setLoading(false); // Reset loading state
    }
  };

  return (
    <div className="login-container">
      <div className="login-form">
        <img src={logo} alt="NutriMinds Logo" className="logo" />
        <h2 className="login-title">Login to Your Account</h2>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        <form onSubmit={handleLogin}>
          <div className="input-group">
            <label htmlFor="email" className="input-label">Email</label>
            <input
              type="email"
              id="email"
              className="input-field"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
            />
          </div>
          <div className="input-group">
            <label htmlFor="password" className="input-label">Password</label>
            <input
              type="password"
              id="password"
              className="input-field"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="login-button" disabled={loading}>
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </form>
        <p className="register-link">Don't have an account? <a href="/signup">Register here</a></p>
      </div>
    </div>
  );
}

export default Login;
