import React, { useEffect, useState } from 'react';
import './Styles/Home.css';
import logo from './Styles/nutriminds-logo.png';
import Popup from './Popup.jsx';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function HomePage() {
  const [popupVisible, setPopupVisible] = useState(false);
  const [popupMessage, setPopupMessage] = useState('');
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [welcomeText, setWelcomeText] = useState('');
  const [userProfile, setUserProfile] = useState(null);
  const navigate = useNavigate();

  // Check user login status on page load
  useEffect(() => {
    const checkLoginStatus = async () => {
      try {
        const token = localStorage.getItem('authToken');
        if (token) {
          axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
          const response = await axios.get('http://localhost:8080/api/check-login-status', { withCredentials: true });
          setIsLoggedIn(response.data.isLoggedIn); // Assuming the API returns { isLoggedIn: true/false }
          if (response.data.isLoggedIn) {
            setUserProfile(response.data.user); // Assuming the API returns user data
          }
        }
      } catch (err) {
        console.error('Error checking login status:', err);
      }
    };

    checkLoginStatus();
  }, []);

  // Handle registration or login navigation
  const handleGetStarted = () => {
    if (!isLoggedIn) {
      setPopupMessage('You must log in to get started.');
      setPopupVisible(true);
      navigate("/signup");
    } else {
      navigate("/plan"); // Navigate to the diet plan directly if logged in
    }
  };

  const handleLogin = () => {
    setPopupMessage('If you already have an account, please log in with your credentials.');
    setPopupVisible(true);
    navigate("/login");
  };

  const closePopup = () => {
    setPopupVisible(false);
  };

  const scrollToSection = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
      element.scrollIntoView({ behavior: 'smooth' });
    }
  };

  useEffect(() => {
    const message = "Weelcome to Balancing Diets and Detecting Nutrient Deficiencies";
    let index = 0;

    const interval = setInterval(() => {
      setWelcomeText((prev) => prev + message[index]);
      index++;
      if (index === message.length) {
        setTimeout(() => setWelcomeText(''), 1500); // Pause before clearing text
        index = 0;
      }
    }, 100); // Adjust speed to improve readability

    return () => clearInterval(interval);
  }, []);

  const handleProfileClick = () => {
    // Handle profile click, e.g., show a dropdown menu
    // For simplicity, we'll just log the user profile here
    console.log('User Profile:', userProfile);
  };

  const handleLogout = () => {
    // Handle logout
    localStorage.removeItem('authToken');
    localStorage.removeItem('user');
    setIsLoggedIn(false);
    setUserProfile(null);
    navigate('/');
  };

  return (
    <div className="home-page">
      {popupVisible && <Popup message={popupMessage} onClose={closePopup} />}

      <header className="header">
        <div className="header-container">
          <img src={logo} alt="NutriMinds Logo" className="logo" />
          <nav className="nav">
            <button className="nav-link" onClick={() => scrollToSection('about')}>About Us</button>
            <button className="nav-link" onClick={() => scrollToSection('features')}>Features</button>
            <button className="nav-link" onClick={() => scrollToSection('modules')}>Modules</button>
            <button className="nav-link" onClick={() => scrollToSection('contact')}>Contact</button>
          </nav>
          <div className="auth-links">
            {isLoggedIn ? (
              <div className="profile-icon" onClick={handleProfileClick}>
                <img src={logo} alt="Profile" className="profile-logo" />
                {userProfile && (
                  <div className="profile-dropdown">
                    <p>{userProfile.email}</p>
                    <button onClick={() => navigate('/profile')}>Profile</button>
                    <button onClick={handleLogout} className="logout-button">Logout</button>
                  </div>
                )}
              </div>
            ) : (
              <>
                <button onClick={handleLogin} className="nav-link login">Login</button>
                <button onClick={handleGetStarted} className="nav-link register">Register</button>
              </>
            )}
          </div>
        </div>
      </header>

      <section className="hero">
        <div className="hero-content">
          <h1 className="hero-title">{welcomeText}</h1>
          <p className="hero-description">Helping you achieve a balanced, healthy lifestyle by monitoring and improving your diet.</p>
          <button onClick={handleGetStarted} className="get-started">Get Started</button>
        </div>
      </section>

      <section id="features" className="features-section">
        <h2>Our Features</h2>
        <div className="features-list">
          <div className="feature-card" onClick={() => navigate("/plan")}>
            <h3>Personalized Diet Plans</h3>
            <p>Experience an unparalleled approach to health and nutrition.</p>
          </div>
          <div className="feature-card" onClick={() => navigate("/health-monitoring")}>
            <h3>Health Monitoring & Recommendations</h3>
            <p>Receive recommendations based on your health needs.</p>
          </div>
          <div className="feature-card" onClick={() => navigate("/knowledge-base")}>
            <h3>Extensive Nutritional Knowledge Base</h3>
            <p>Access a comprehensive database of nutritional information.</p>
          </div>
        </div>
      </section>

      <section id="modules" className="modules-section">
        <h2>Our Additional Features</h2>
        <div className="modules-list">
          <div className="module-card" onClick={() => navigate("/deficiency")}>
            <h3>Nutrient Deficiency Analysis Module</h3>
            <p>Get customized recommendations for a healthier lifestyle.</p>
          </div>

          <div className="module-card" onClick={() => navigate("/knowledge-base")}>
            <h3>Nutritional Knowledge Base Module</h3>
            <p>Get customized recommendations for a healthier lifestyle.</p>
          </div>

          {["Meal Planner & Tracker Module"].map((module, index) => (
            <div
              key={index + 1}
              className="module-card"
              onClick={() => navigate("/mealplanner")}
            >
              <h3>{module}</h3>
              <p>Get customized recommendations for a healthier lifestyle.</p>
            </div>
          ))}
        </div>
      </section>

      <section id="about" className="about-section">
        <h2 id="aboutTitle">About Us</h2>
        <p>NutriMinds is dedicated to providing top-notch dietary solutions to help you lead a balanced, healthy life.</p>
      </section>

      <section id="contact" className="contact-section">
        <h2>Contact Us</h2>
        <p>If you have any questions, feel free to reach out!</p>
        <p>Email: 2200039087@kluniversity.in</p>
        <p>Phone: 7080798598</p>
      </section>

      <footer className="footer">
        <p>Follow us on</p>
        <div className="social-icons">
          <a href="https://linkedin.com" className="social-link">LinkedIn</a>
          <a href="https://instagram.com" className="social-link">Instagram</a>
          <a href="https://facebook.com" className="social-link">Facebook</a>
          <a href="https://twitter.com" className="social-link">Twitter</a>
        </div>
        <section className='aa'>
          <p>Akula Aravindh Kumaar © 2024 NutriMinds. All rights reserved.</p>
        </section>
      </footer>
    </div>
  );
}

export default HomePage;
