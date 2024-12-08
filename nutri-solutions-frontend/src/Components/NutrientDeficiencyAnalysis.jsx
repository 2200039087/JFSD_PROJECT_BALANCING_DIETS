import React, { useState } from 'react';
import axios from 'axios';  // Import axios
import './Styles/NutrientDeficiencyAnalysis.css';

function NutrientDeficiencyAnalysis() {
  const [inputData, setInputData] = useState({
    dailyFoodIntake: '',
    healthConditions: '',
    activityLevel: 'sedentary',
    stressLevel: 'low',
    sleepHours: '',
    healthGoals: '',
    symptoms: '',
  });
  const [results, setResults] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setInputData({ ...inputData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Set loading state to true while waiting for the response
    setLoading(true);
    setError(null);  // Reset error state

    try {
      // Make the API call to your backend (updated endpoint)
      const response = await axios.post('http://localhost:8080/api/nutrient-analysis/analyze', inputData);

      // Assuming the backend response contains the analysis data
      setResults(response.data);
    } catch (err) {
      // Handle errors from the backend
      setError('An error occurred while analyzing the data. Please try again later.');
    } finally {
      // Set loading state to false after receiving response or encountering error
      setLoading(false);
    }
  };

  return (
    <div className="analysis-container">
      <h2>Nutrient Deficiency Analysis Module</h2>
      <form onSubmit={handleSubmit} className="input-form">
        <label>
          Daily Food Intake:
          <textarea name="dailyFoodIntake" value={inputData.dailyFoodIntake} onChange={handleInputChange} />
        </label>
        <label>
          Existing Health Conditions:
          <textarea name="healthConditions" value={inputData.healthConditions} onChange={handleInputChange} />
        </label>
        <label>
          Activity Level:
          <select name="activityLevel" value={inputData.activityLevel} onChange={handleInputChange}>
            <option value="sedentary">Sedentary</option>
            <option value="lightly active">Lightly Active</option>
            <option value="moderately active">Moderately Active</option>
            <option value="very active">Very Active</option>
          </select>
        </label>
        <label>
          Stress Level:
          <select name="stressLevel" value={inputData.stressLevel} onChange={handleInputChange}>
            <option value="low">Low</option>
            <option value="medium">Medium</option>
            <option value="high">High</option>
          </select>
        </label>
        <label>
          Sleep Hours:
          <input type="number" name="sleepHours" value={inputData.sleepHours} onChange={handleInputChange} />
        </label>
        <label>
          Health Goals:
          <textarea name="healthGoals" value={inputData.healthGoals} onChange={handleInputChange} />
        </label>
        <label>
          Symptoms:
          <textarea name="symptoms" value={inputData.symptoms} onChange={handleInputChange} />
        </label>
        <button type="submit" className="submit-button">Analyze</button>
      </form>

      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}

      {results && (
        <div className="results-container">
          <h3>Nutritional Overview</h3>
          <p><strong>Intake:</strong> {results.nutrientIntake}</p>
          <h4>Deficiencies</h4>
          <ul>{results.deficiencies.map(def => <li key={def}>{def}</li>)}</ul>
          <h4>Symptoms Analysis</h4>
          <p>{results.symptomsAnalysis}</p>
          <h4>Recommendations</h4>
          <p>{results.recommendations}</p>
        </div>
      )}
    </div>
  );
}

export default NutrientDeficiencyAnalysis;
