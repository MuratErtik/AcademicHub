import React from "react";
import { Routes, Route } from "react-router-dom";
import AuthForm from "./components/AuthForm";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import { Toaster } from "react-hot-toast";

import AdayDashboard from "./pages/AdayDashboard";
import AdminDashboard from "./pages/AdminDashboard";
import JuriDashboard from "./pages/JuriDashboard";
import YoneticiDashboard from "./pages/YoneticiDashboard";
import IlanForm from "./pages/IlanForm";
import Basvurularim from "./pages/Basvurularim";

const App = () => {
  return (
    <>
      <Toaster position="top-right" />
      <div className="flex flex-col min-h-screen">
        <Navbar />
        <main className="flex-grow">
          <Routes>
            {/* Giriş Ekranı */}
            <Route path="/" element={<AuthForm />} />

            {/* Dashboardlar */}
            <Route path="/aday" element={<AdayDashboard />} />
            <Route path="/admin" element={<AdminDashboard />} />
            <Route path="/juri" element={<JuriDashboard />} />
            <Route path="/yonetici" element={<YoneticiDashboard />} />

            {/* İlan başvuru formu */}
            <Route path="/ilan/:id" element={<IlanForm />} />

            {/* Kullanıcıya özel sayfalar */}
            <Route path="/basvurularim" element={<Basvurularim />} />
          </Routes>
        </main>
        <Footer />
      </div>
    </>
  );
};

export default App;
