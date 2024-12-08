import React, { useState } from "react";
import axios from "axios";
import "./Styles/NutritionalKnowledgeBase.css";

function NutritionalKnowledgeBase() {
  const [searchQuery, setSearchQuery] = useState("");
  const [dietaryRestriction, setDietaryRestriction] = useState("");
  const [healthCondition, setHealthCondition] = useState("");
  const [results, setResults] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Unified handler for searching knowledge base
  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    try {
      const searchRequest = {
        searchQuery: searchQuery.trim(),
        dietaryRestriction: dietaryRestriction.trim(),
        healthCondition: healthCondition.trim(),
      };

      // Using fetch to query the backend API
      const response = await fetch("http://localhost:8080/api/knowledge-base/search", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(searchRequest),
      });

      if (!response.ok) {
        throw new Error("Failed to fetch data");
      }

      const data = await response.json();
      setResults(data); // Setting the results into state
    } catch (err) {
      console.error("Error fetching data:", err.message);
      setError("An error occurred while fetching the data. Please try again later.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="knowledge-base-container">
      <h2>Nutritional Knowledge Base</h2>
      <form onSubmit={handleSearch} className="search-form">
        <input
          type="text"
          placeholder="Enter nutrient keyword (e.g., Vitamin C)"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          className="search-input"
        />
        <select
          value={dietaryRestriction}
          onChange={(e) => setDietaryRestriction(e.target.value)}
          className="dropdown"
        >
          <option value="">Dietary Restriction</option>
          <option value="vegetarian">Vegetarian</option>
          <option value="gluten-free">Gluten-Free</option>
          <option value="vegan">Vegan</option>
        </select>
        <select
          value={healthCondition}
          onChange={(e) => setHealthCondition(e.target.value)}
          className="dropdown"
        >
          <option value="">Health Condition</option>
          <option value="diabetes">Diabetes</option>
          <option value="heart-disease">Heart Disease</option>
          <option value="anemia">Anemia</option>
        </select>
        <button type="submit" className="search-button">
          Search
        </button>
      </form>

      {loading && <p>Loading...</p>}
      {error && <p className="error">{error}</p>}

      {results && (
        <div className="results-container">
          {/* Nutrient Information */}
          <div className="result-category">
            <h3>Nutrient Information</h3>
            {results.nutrients &&
              results.nutrients.map((nutrient, index) => (
                <div key={index} className="card">
                  <h4>{nutrient.name}</h4>
                  <p>{nutrient.description}</p>
                  <p>
                    <strong>RDA:</strong> {nutrient.rda}
                  </p>
                  <p>
                    <strong>Foods:</strong> {nutrient.foods.join(", ")}
                  </p>
                </div>
              ))}
          </div>

          {/* Health Condition Guides */}
          <div className="result-category">
            <h3>Health Condition Guides</h3>
            {results.healthGuides &&
              results.healthGuides.map((guide, index) => (
                <div key={index} className="card">
                  <h4>{guide.condition}</h4>
                  <p>{guide.description}</p>
                </div>
              ))}
          </div>

          {/* Recipes and Meal Ideas */}
          <div className="result-category">
            <h3>Recipes and Meal Ideas</h3>
            {results.recipes &&
              results.recipes.map((recipe, index) => (
                <div key={index} className="card">
                  <h4>{recipe.name}</h4>
                  <p>
                    <strong>Dietary Restriction:</strong> {recipe.dietaryRestriction}
                  </p>
                </div>
              ))}
          </div>
        </div>
      )}
    </div>
  );
}

export default NutritionalKnowledgeBase;
