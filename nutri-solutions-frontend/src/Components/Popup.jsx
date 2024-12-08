import React from 'react';
import './Styles/Popup.css';

const Popup = ({ message, onClose, onRegister }) => {
  return (
    <div className="popup-overlay">
      <div className="popup-content">
        <p>{message}</p>
        <button onClick={onClose}>Close</button>
        {onRegister && <button onClick={onRegister} className="popup-button">Register</button>}
      </div>
    </div>
  );
};

export default Popup;
