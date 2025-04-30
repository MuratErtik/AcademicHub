import React from "react";
import { Link, useLocation } from "react-router-dom";

const Navbar = () => {
    const location = useLocation();

    // Check if the current path is the AdayDashboard
    const isAdayDashboard = () => {
        return location.pathname.toLowerCase().includes("aday");
    };


    return (
        <nav className="bg-white border-b shadow px-6 py-3">
            <div className="max-w-7xl mx-auto flex justify-between items-center">
                {/* Logo */}
                <Link to="/">
                    <div className="flex items-center space-x-2">
                        <img src="/kou-logo.png" alt="KOU" className="w-10 h-10" />
                        <span className="font-semibold text-xl text-blue-800">KOCAELİ ÜNİVERSİTESİ</span>
                    </div>
                </Link>

                {/* Navigation Links */}
                <div className="flex items-center space-x-6">
                    {/* Show "Başvurularım" link only in AdayDashboard */}
                    {isAdayDashboard() && (
                        <Link
                            to="/basvurularim"
                            className="text-blue-600 hover:text-blue-800 font-medium transition-colors"
                        >
                            Başvurularım
                        </Link>
                    )}
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
