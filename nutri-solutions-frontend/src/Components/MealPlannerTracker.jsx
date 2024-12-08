import React, { useState } from 'react';
import axios from 'axios';  // Import axios for API calls
import './Styles/MealPlannerTracker.css';

function MealPlannerTracker() {
  const [preferences, setPreferences] = useState({
    allergies: '',
    cuisine: '',
    mealType: 'breakfast',
    calories: 2000,
    carbs: 50,
    proteins: 30,
    fats: 20,
  });

  const [mealPlan, setMealPlan] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  const handlePreferencesChange = (e) => {
    const { name, value } = e.target;
    setPreferences({ ...preferences, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Set loading state to true while waiting for the response
    setLoading(true);
    setError(null);  // Reset error state

    try {
      // Step 1: Send preferences to the backend to save them
      const saveResponse = await axios.post('http://localhost:8080/api/meal-planner/preferences', preferences);

      if (saveResponse.status === 200) {
        // Step 2: Generate meal plan based on saved preferences
        const mealPlanResponse = await axios.post('http://localhost:8080/api/meal-planner/generate', preferences);
        setMealPlan(mealPlanResponse.data); // Set the meal plan
      }
    } catch (err) {
      // Log full error for debugging
      console.error('Error generating meal plan:', err);

      // Handle errors from the backend
      setError('An error occurred while generating the meal plan. Please try again later.');
    } finally {
      setLoading(false); // Reset loading state after the request
    }
  };

  return (
    <div className="meal-planner-container">
      <h2>Meal Planner & Tracker</h2>

      {/* Preferences Form */}
      <form onSubmit={handleSubmit} className="preferences-form">
        <h3>Set Your Preferences</h3>
        <input
          type="text"
          placeholder="Food Allergies or Intolerances"
          name="allergies"
          value={preferences.allergies}
          onChange={handlePreferencesChange}
        />
        <select
          name="cuisine"
          value={preferences.cuisine}
          onChange={handlePreferencesChange}
        >
          <option value="">Preferred Cuisine</option>
          <option value="Mediterranean">Mediterranean</option>
          <option value="Asian">Asian</option>
          <option value="American">American</option>
        </select>
        <input
          type="number"
          placeholder="Target Daily Calories"
          name="calories"
          value={preferences.calories}
          onChange={handlePreferencesChange}
        />
        {/* Additional preferences can be added here */}
        <button type="submit" className="submit-button">Generate Meal Plan</button>
      </form>

      {/* Loading Indicator */}
      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}

      {/* Display Meal Plan */}
      {mealPlan && (
        <div className="meal-plan">
          <h3>Weekly Meal Plan</h3>
          {Object.keys(mealPlan).map((day, index) => (
            <div key={index} className="day-plan">
              <h4>{day.charAt(0).toUpperCase() + day.slice(1)}</h4>
              {Object.keys(mealPlan[day]).map((mealType, index) => (
                <p key={index}>
                  <strong>{mealType.charAt(0).toUpperCase() + mealType.slice(1)}:</strong> {mealPlan[day][mealType].meal} ({mealPlan[day][mealType].calories} kcal)
                </p>
              ))}
            </div>
          ))}
        </div>
      )}

      {/* Shopping List */}
      <div className="shopping-list">
        <h3>Shopping List</h3>
        {/* Shopping list items can be dynamically generated based on the meal plan */}
        <ul>
          <li>Greek yogurt (1 tub)</li>
          <li>Honey (1 jar)</li>
          <li>Berries (1 pack)</li>
          <li>Quinoa (1 pack)</li>
          <li>Chickpeas (canned or dry)</li>
          <li>Salmon fillet (1 lb)</li>
        </ul>
      </div>

      {/* Progress Tracking */}
      <div className="progress-tracker">
        <h3>Progress Monitoring</h3>
        <p>Track weight changes and meal plan adherence here.</p>
      </div>
    </div>
  );
}

export default MealPlannerTracker;
