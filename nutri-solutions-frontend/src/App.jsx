import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Home from './Components/Home';
import Login from './Components/Login';
import Signup from './Components/Signup';
import DietPlan from './Components/DietPlan';
import HealthMonitoring from './Components/HealthMonitoring';
import MealPlannerTracker from './Components/MealPlannerTracker';
import NutrientDeficiencyAnalysis from './Components/NutrientDeficiencyAnalysis';
import NutritionalKnowledgeBase from './Components/NutritionalKnowledgeBase';
import Profile from './Components/Profile';
// import About from './Components/About';
// import Contact from './Components/Contact';


function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path="/profile" element={<Profile />} />
          {/* <Route path="/about" element={<About />} />
          <Route path="/contact" element={<Contact />} /> */}
          <Route path="/plan" element={<DietPlan />} />
          <Route path="/health-monitoring" element={<HealthMonitoring />} />
          <Route path="/mealplanner" element={<MealPlannerTracker />} />
          <Route path="/deficiency" element={<NutrientDeficiencyAnalysis />} />
          <Route path="/knowledge-base" element={<NutritionalKnowledgeBase />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
