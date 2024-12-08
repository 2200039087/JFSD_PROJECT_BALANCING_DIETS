import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './Styles/HealthMonitoring.css';

function HealthMonitoring() {
  const [healthMetrics, setHealthMetrics] = useState({
    weight: 70,
    height: 170,
    bloodPressure: { systolic: 120, diastolic: 80 },
    bloodSugar: 90,
    cholesterol: 180,
    dailySteps: 8000,
    exerciseMinutes: 30,
    sleepHours: 7,
    waterIntake: 2.5,
  });

  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [healthStatus, setHealthStatus] = useState('');

  // Fetch health metrics from backend
  useEffect(() => {
    const fetchMetrics = async () => {
      try {
        const response = await axios.get('http://localhost:8080/api/health-metrics');
        const latestHealthMetrics = response.data[response.data.length - 1]; // Assuming you want the latest data
        setHealthMetrics(latestHealthMetrics); // Set the fetched data
        setLoading(false);
      } catch (err) {
        console.error('Error fetching health metrics:', err);
        setError('Failed to fetch health metrics. Please try again later.');
        setLoading(false);
      }
    };

    fetchMetrics();
  }, []);

  const calculateBMI = () => {
    const heightInMeters = healthMetrics.height / 100;
    return (healthMetrics.weight / (heightInMeters * heightInMeters)).toFixed(1);
  };

  const handleMetricChange = (e) => {
    const { name, value } = e.target;
    setHealthMetrics({ ...healthMetrics, [name]: parseFloat(value) });
  };

  const handleBloodPressureChange = (e) => {
    const { name, value } = e.target;
    setHealthMetrics({
      ...healthMetrics,
      bloodPressure: {
        ...healthMetrics.bloodPressure,
        [name]: parseInt(value),
      },
    });
  };

  const handleSaveMetrics = async () => {
    try {
      const response = await axios.post('http://localhost:8080/api/health-metrics', healthMetrics);
      const healthStatusResponse = await axios.post('http://localhost:8080/api/health-metrics/analyze', healthMetrics);
      setHealthStatus(healthStatusResponse.data);
      alert('Health metrics saved successfully!');
    } catch (err) {
      console.error('Error saving health metrics:', err);
      setError('Failed to save health metrics. Please try again.');
    }
  };

  if (loading) {
    return <p>Loading health metrics...</p>;
  }

  if (error) {
    return <p className="error-message">{error}</p>;
  }

  return (
    <div className="health-monitoring-container">
      <h2>Health Dashboard</h2>

      {/* Health Metrics Section */}
      <div className="health-metrics">
        <h3>Health Metrics</h3>
        <div className="metric">
          <label>Current Weight (kg):</label>
          <input
            type="number"
            name="weight"
            value={healthMetrics.weight}
            onChange={handleMetricChange}
          />
        </div>
        <div className="metric">
          <label>BMI:</label>
          <span>{calculateBMI()}</span>
        </div>
        <div className="metric">
          <label>Blood Pressure (mmHg):</label>
          <span>
            {healthMetrics.bloodPressure?.systolic ?? 'N/A'}/{healthMetrics.bloodPressure?.diastolic ?? 'N/A'}
          </span>
        </div>
        <div className="metric">
          <label>Blood Sugar (mg/dL):</label>
          <input
            type="number"
            name="bloodSugar"
            value={healthMetrics.bloodSugar}
            onChange={handleMetricChange}
          />
        </div>
        <div className="metric">
          <label>Cholesterol (mg/dL):</label>
          <input
            type="number"
            name="cholesterol"
            value={healthMetrics.cholesterol}
            onChange={handleMetricChange}
          />
        </div>
      </div>

      {/* Daily Activity Section */}
      <div className="daily-activity">
        <h3>Daily Activity</h3>
        <div className="activity">
          <label>Steps Taken:</label>
          <input
            type="number"
            name="dailySteps"
            value={healthMetrics.dailySteps}
            onChange={handleMetricChange}
          />
        </div>
        <div className="activity">
          <label>Exercise Minutes:</label>
          <input
            type="number"
            name="exerciseMinutes"
            value={healthMetrics.exerciseMinutes}
            onChange={handleMetricChange}
          />
        </div>
        <div className="activity">
          <label>Sleep (hours):</label>
          <input
            type="number"
            name="sleepHours"
            value={healthMetrics.sleepHours}
            onChange={handleMetricChange}
          />
        </div>
        <div className="activity">
          <label>Water Intake (liters):</label>
          <input
            type="number"
            name="waterIntake"
            value={healthMetrics.waterIntake}
            onChange={handleMetricChange}
          />
        </div>
      </div>

      {/* Health Status and Suggestions Section */}
      {healthStatus && (
        <div className="health-status">
          <h3>Health Status and Suggestions</h3>
          <p>{healthStatus}</p>
        </div>
      )}
    </div>
  );
}

export default HealthMonitoring;
