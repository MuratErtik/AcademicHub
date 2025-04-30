import React from "react";
import { Link } from "react-router-dom";

const Footer = () => {
    return (
        <footer className="bg-gray-800 text-white">
            <div className="max-w-7xl mx-auto px-4 py-12">
                <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
                    {/* University Info */}
                    <div className="space-y-4">
                        <div className="flex items-center space-x-2">
                            <img src="/kou-logo.png" alt="KOU" className="w-10 h-10" />
                            <span className="font-semibold text-lg">KOCAELİ ÜNİVERSİTESİ</span>
                        </div>
                        <p className="text-gray-300 text-sm">
                            Akademik personel alım süreçlerinin yönetildiği merkezi platform.
                        </p>
                    </div>

                    {/* Quick Links */}
                    <div>
                        <h3 className="text-lg font-semibold mb-4">Hızlı Erişim</h3>
                        <ul className="space-y-2">
                            <li>
                                <Link to="/basvuru-sartlari" className="text-gray-300 hover:text-white transition-colors">
                                    Başvuru Şartları
                                </Link>
                            </li>
                            <li>
                                <Link to="/degerlendirme-kriterleri" className="text-gray-300 hover:text-white transition-colors">
                                    Değerlendirme Kriterleri
                                </Link>
                            </li>
                            <li>
                                <Link to="/sikca-sorulan-sorular" className="text-gray-300 hover:text-white transition-colors">
                                    Sıkça Sorulan Sorular
                                </Link>
                            </li>
                            <li>
                                <Link to="/gizlilik-politikasi" className="text-gray-300 hover:text-white transition-colors">
                                    Gizlilik Politikası
                                </Link>
                            </li>
                        </ul>
                    </div>

                    {/* Contact Info */}
                    <div>
                        <h3 className="text-lg font-semibold mb-4">İletişim</h3>
                        <ul className="space-y-2 text-gray-300">
                            <li>Tel: +90 262 303 10 00</li>
                            <li>E-posta: info@kocaeli.edu.tr</li>
                            <li>Adres: Umuttepe Yerleşkesi, 41380 İzmit/Kocaeli</li>
                        </ul>
                    </div>

                    {/* Social Media */}
                    <div>
                        <h3 className="text-lg font-semibold mb-4">Sosyal Medya</h3>
                        <div className="flex space-x-4">
                            <a
                                href="https://x.com/kou92official"
                                target="_blank"
                                rel="noopener noreferrer"
                                className="text-white transition-colors"
                            >
                                <i className="fab fa-twitter text-xl"></i>
                            </a>
                            <a
                                href="https://www.facebook.com/kou92official"
                                target="_blank"
                                rel="noopener noreferrer"
                                className="text-white transition-colors"
                            >
                                <i className="fab fa-facebook text-xl"></i>
                            </a>
                            <a
                                href="https://www.instagram.com/kou92official/"
                                target="_blank"
                                rel="noopener noreferrer"
                                className="text-white transition-colors"
                            >
                                <i className="fab fa-instagram text-xl"></i>
                            </a>
                            <a
                                href="https://www.linkedin.com/school/kocaeliuniversitesi/"
                                target="_blank"
                                rel="noopener noreferrer"
                                className="text-white transition-colors"
                            >
                                <i className="fab fa-linkedin text-xl"></i>
                            </a>
                        </div>
                    </div>
                </div>

                {/* Copyright */}
                <div className="border-t border-gray-700 mt-8 pt-8 text-center text-gray-300">
                    <p>© {new Date().getFullYear()} Kocaeli Üniversitesi. Tüm hakları saklıdır.</p>
                </div>
            </div>
        </footer>
    );
};

export default Footer;
