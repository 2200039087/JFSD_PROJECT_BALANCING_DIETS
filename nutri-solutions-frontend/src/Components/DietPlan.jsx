import React, { useState } from 'react';
import axios from 'axios';
import './Styles/DietPlan.css';

function DietPlan() {
  const [formData, setFormData] = useState({
    goal: '',
    dietaryPreference: '',
    allergies: '',
    mealsPerDay: 3,
    mealTiming: '',
    foodFrequency: '',
    calorieIntake: '',
    macroDistribution: { carbs: 0, proteins: 0, fats: 0 },
    cookingPref: '',
    kitchenEquip: '',
    mealPrepTime: '',
  });

  const [responseMessage, setResponseMessage] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [dietPlan, setDietPlan] = useState(null);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleMacroChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      macroDistribution: { ...formData.macroDistribution, [name]: value },
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setIsLoading(true);
    try {
      // Log the form data being sent
      console.log('Form Data:', formData);

      // Send data to backend
      const response = await axios.post('http://localhost:8080/api/diet-plans', {
        ...formData,
        ...formData.macroDistribution,
      });
      setResponseMessage(response.data.message || 'Diet Plan submitted successfully!');
      setDietPlan(response.data); // Assuming the backend returns the diet plan
    } catch (error) {
      console.error('Error submitting diet plan:', error);
      if (error.response) {
        // The request was made and the server responded with a status code
        // that falls out of the range of 2xx
        setResponseMessage(`Error: ${error.response.status} - ${error.response.data.message || 'An error occurred. Please try again.'}`);
      } else if (error.request) {
        // The request was made but no response was received
        setResponseMessage('Error: No response from server. Please try again.');
      } else {
        // Something happened in setting up the request that triggered an Error
        setResponseMessage('Error: An error occurred. Please try again.');
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="diet-plan-container">
      <nav className="navbar">
        <h1>Personalized Diet Plan</h1>
        <div className="nav-links">
          <a href="#goal">Goals</a>
          <a href="#dietaryPreferences">Dietary Preferences</a>
          <a href="#mealPlan">Meal Plan</a>
          <a href="#tracking">Progress Tracking</a>
        </div>
      </nav>

      <form className="diet-form" onSubmit={handleSubmit}>
        <section id="goal">
          <h2>Health and Dietary Goals</h2>
          <label>Primary Goal:
            <select name="goal" value={formData.goal} onChange={handleChange}>
              <option value="">Select a Goal</option>
              <option value="weight_loss">Weight Loss</option>
              <option value="weight_maintenance">Weight Maintenance</option>
              <option value="weight_gain">Weight Gain</option>
              <option value="muscle_gain">Muscle Gain</option>
              <option value="energy">Improve Energy Levels</option>
            </select>
          </label>
        </section>

        <section id="dietaryPreferences">
          <h2>Dietary Preferences</h2>
          <label>Specific Preferences:
            <input
              type="text"
              name="dietaryPreference"
              value={formData.dietaryPreference}
              onChange={handleChange}
              placeholder="E.g., Vegetarian, Vegan"
            />
          </label>
          <label>Allergies or Intolerances:
            <input
              type="text"
              name="allergies"
              value={formData.allergies}
              onChange={handleChange}
              placeholder="E.g., Nuts, Dairy"
            />
          </label>
        </section>

        <section id="mealPlan">
          <h2>Meal Plan</h2>
          <label>Meals per Day:
            <input
              type="number"
              name="mealsPerDay"
              value={formData.mealsPerDay}
              onChange={handleChange}
              min="1"
              max="6"
            />
          </label>
          <label>Typical Meal Timing:
            <input
              type="text"
              name="mealTiming"
              value={formData.mealTiming}
              onChange={handleChange}
              placeholder="E.g., Breakfast at 8AM"
            />
          </label>
        </section>

        <section id="tracking">
          <h2>Progress Tracking</h2>
          <label>Food Frequency:
            <input
              type="text"
              name="foodFrequency"
              value={formData.foodFrequency}
              onChange={handleChange}
              placeholder="E.g., 3 times a day"
            />
          </label>
          <label>Calorie Intake:
            <input
              type="text"
              name="calorieIntake"
              value={formData.calorieIntake}
              onChange={handleChange}
              placeholder="E.g., 2000 kcal"
            />
          </label>
          <label>Macro Distribution:
            <input
              type="number"
              name="carbs"
              value={formData.macroDistribution.carbs}
              onChange={handleMacroChange}
              placeholder="Carbs"
            />
            <input
              type="number"
              name="proteins"
              value={formData.macroDistribution.proteins}
              onChange={handleMacroChange}
              placeholder="Proteins"
            />
            <input
              type="number"
              name="fats"
              value={formData.macroDistribution.fats}
              onChange={handleMacroChange}
              placeholder="Fats"
            />
          </label>
          <label>Cooking Preferences:
            <input
              type="text"
              name="cookingPref"
              value={formData.cookingPref}
              onChange={handleChange}
              placeholder="E.g., Quick meals"
            />
          </label>
          <label>Kitchen Equipment:
            <input
              type="text"
              name="kitchenEquip"
              value={formData.kitchenEquip}
              onChange={handleChange}
              placeholder="E.g., Oven, Stove"
            />
          </label>
          <label>Meal Prep Time:
            <input
              type="text"
              name="mealPrepTime"
              value={formData.mealPrepTime}
              onChange={handleChange}
              placeholder="E.g., 30 minutes"
            />
          </label>
          <button type="submit" className="submit-button">
            {isLoading ? 'Submitting...' : 'Generate Diet Plan'}
          </button>
        </section>
      </form>

      {responseMessage && <p className="response-message">{responseMessage}</p>}

      {dietPlan && (
        <div className="diet-plan-result">
          <h2>Your Personalized Diet Plan</h2>
          <p><strong>Goal:</strong> {dietPlan.goal}</p>
          <p><strong>Dietary Preference:</strong> {dietPlan.dietaryPreference}</p>
          <p><strong>Allergies:</strong> {dietPlan.allergies}</p>
          <p><strong>Meals per Day:</strong> {dietPlan.mealsPerDay}</p>
          <p><strong>Meal Timing:</strong> {dietPlan.mealTiming}</p>
          <p><strong>Food Frequency:</strong> {dietPlan.foodFrequency}</p>
          <p><strong>Calorie Intake:</strong> {dietPlan.calorieIntake}</p>
          <p><strong>Macro Distribution:</strong> Carbs: {dietPlan.carbs}, Proteins: {dietPlan.proteins}, Fats: {dietPlan.fats}</p>
          <p><strong>Cooking Preferences:</strong> {dietPlan.cookingPref}</p>
          <p><strong>Kitchen Equipment:</strong> {dietPlan.kitchenEquip}</p>
          <p><strong>Meal Prep Time:</strong> {dietPlan.mealPrepTime}</p>
        </div>
      )}
    </div>
  );
}

export default DietPlan;
