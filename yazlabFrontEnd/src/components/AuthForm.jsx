import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const AuthForm = () => {
    const navigate = useNavigate();

    const [role, setRole] = useState("aday");
    const [mode, setMode] = useState("kayit");
    const [formData, setFormData] = useState({});
    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        setFormData((prev) => ({
            ...prev,
            [e.target.name]: e.target.value,
        }));
    };

    const handleRoleChange = (e) => {
        const selectedRole = e.target.value;
        setRole(selectedRole);
        setMode(selectedRole === "aday" ? "kayit" : "giris");
        setFormData({});
        setErrors({});
    };

    const validateForm = () => {
        const newErrors = {};

        if (!formData.ad) newErrors.ad = "Ad gerekli";
        if (!formData.soyad) newErrors.soyad = "Soyad gerekli";
        if (!formData.tcno) newErrors.tcno = "TC Kimlik No gerekli";
        if (!formData.email) newErrors.email = "E-posta gerekli";
        else if (!/\S+@\S+\.\S+/.test(formData.email)) newErrors.email = "Geçersiz e-posta";

        if (role !== "aday") {
            if (!formData.sifre) newErrors.sifre = "Şifre gerekli";
        }

        if (role !== "aday" && mode === "kayit") {
            if (!formData.departman) newErrors.departman = "Departman gerekli";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!validateForm()) return;

        const data = { ...formData, rol: role };
        console.log("Veri gönderildi:", data);

        // Başarılı işlem sonrası yönlendirme
        navigate(`/${role}`);
    };

    const renderInput = (name, type, placeholder) => (
        <>
            <input
                name={name}
                type={type}
                placeholder={placeholder}
                onChange={handleChange}
                className={`input ${errors[name] ? "border-red-500" : ""}`}
            />
            {errors[name] && <p className="text-red-500 text-sm">{errors[name]}</p>}
        </>
    );

    const renderFormFields = () => {
        if (role === "aday") {
            return (
                <>
                    {renderInput("ad", "text", "Ad")}
                    {renderInput("soyad", "text", "Soyad")}
                    {renderInput("tcno", "text", "TC Kimlik No")}
                    {renderInput("email", "email", "E-posta")}
                    {renderInput("telefon", "tel", "Telefon Numarası")}
                </>
            );
        }

        if (mode === "giris") {
            return (
                <>
                    {renderInput("email", "email", "E-posta")}
                    {renderInput("tcno", "text", "TC Kimlik No")}
                    {renderInput("sifre", "password", "Şifre")}
                </>
            );
        }

        return (
            <>
                {renderInput("ad", "text", "Ad")}
                {renderInput("soyad", "text", "Soyad")}
                {renderInput("tcno", "text", "TC Kimlik No")}
                {renderInput("email", "email", "E-posta")}
                {renderInput("telefon", "tel", "Telefon Numarası")}
                {renderInput("sifre", "password", "Şifre")}
                {renderInput("departman", "text", "Departman")}
            </>
        );
    };

    return (
        <div className="flex flex-col md:flex-row h-screen bg-gray-100">
            {/* Sol: Logo */}
            <div className="md:w-1/2 w-full flex items-center justify-center bg-white p-10">
                <div className="text-center">
                    <img src="/kou-logo.png" alt="Kocaeli Üniversitesi" className="w-40 mx-auto mb-4" />
                    <h1 className="text-3xl font-bold text-blue-800">KOCAELİ ÜNİVERSİTESİ</h1>
                    <p className="text-gray-600 mt-2">Akademik Personel Başvuru</p>
                </div>
            </div>

            {/* Sağ: Form */}
            <div className="md:w-1/2 w-full flex flex-col justify-center p-8">
                <h2 className="text-2xl font-semibold mb-6 text-center">
                    {mode === "giris" ? "Giriş" : "Kayıt"}
                </h2>

                <select value={role} onChange={handleRoleChange} className="input mb-6">
                    <option value="aday">Aday</option>
                    <option value="admin">Admin</option>
                    <option value="juri">Jüri</option>
                    <option value="yonetici">Yönetici</option>
                </select>

                <form onSubmit={handleSubmit} className="flex flex-col gap-4">
                    {renderFormFields()}

                    <button type="submit" className="btn-primary">
                        {mode === "giris" ? "Giriş Yap" : "Kayıt Ol"}
                    </button>

                    {role !== "aday" && mode === "giris" && (
                        <p className="text-sm text-center mt-2">
                            Hesabınız yok mu?{" "}
                            <button type="button" onClick={() => setMode("kayit")} className="text-blue-600 underline">
                                Kayıt Ol
                            </button>
                        </p>
                    )}
                </form>
            </div>
        </div>
    );
};

export default AuthForm;
