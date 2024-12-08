import React from 'react';

function FormInput({ label, type, name, value, onChange, required }) {
  return (
    <div className="form-group">
      <label className="form-label" htmlFor={name}>{label}</label>
      <input
        className="form-input"
        type={type}
        name={name}
        id={name} // Added id for better accessibility
        value={value}
        onChange={onChange}
        required={required}
        placeholder={label} // Placeholder for better usability
      />
    </div>
  );
}

export default FormInput;
