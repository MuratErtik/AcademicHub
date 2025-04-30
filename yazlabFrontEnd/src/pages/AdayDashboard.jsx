import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const AdayDashboard = () => {
    const navigate = useNavigate();
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [selectedIlan, setSelectedIlan] = useState(null);

    const ilanlar = [
        {
            id: 1,
            ad: "Bilgisayar Mühendisliği Araştırma Görevlisi",
            tarih: "2025-04-01",
            departman: "Bilgisayar Mühendisliği",
            durum: "Açık",
            // Placeholder for future backend integration
            aciklama: "İş tanımı ve gereksinimler buraya gelecek...",
            gereksinimler: ["Gereksinim 1", "Gereksinim 2", "Gereksinim 3"]
        },
        {
            id: 2,
            ad: "Elektrik-Elektronik Mühendisliği Öğretim Görevlisi",
            tarih: "2025-03-15",
            departman: "Elektrik-Elektronik Mühendisliği",
            durum: "Kapalı",
            // Placeholder for future backend integration
            aciklama: "İş tanımı ve gereksinimler buraya gelecek...",
            gereksinimler: ["Gereksinim 1", "Gereksinim 2", "Gereksinim 3"]
        },
    ];

    const handleIlanTikla = (id) => {
        navigate(`/ilan/${id}`);
    };

    const handleInspectClick = (ilan) => {
        setSelectedIlan(ilan);
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        setSelectedIlan(null);
    };

    return (
        <div className="min-h-screen p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-blue-700 mb-6">Aday Dashboard</h1>

            <div className="overflow-x-auto">
                <table className="min-w-full bg-white rounded shadow">
                    <thead className="bg-blue-100">
                        <tr>
                            <th className="text-left p-3">İlan Adı</th>
                            <th className="text-left p-3">Tarih</th>
                            <th className="text-left p-3">Departman</th>
                            <th className="text-left p-3">Durum</th>
                            <th className="text-left p-3">İşlem</th>
                        </tr>
                    </thead>
                    <tbody>
                        {ilanlar.map((ilan) => (
                            <tr
                                key={ilan.id}
                                className="border-t hover:bg-blue-50"
                            >
                                <td className="p-3">
                                    <div className="flex items-center gap-2">
                                        {ilan.ad}
                                        <button
                                            onClick={() => handleInspectClick(ilan)}
                                            className="text-blue-600 hover:text-blue-800 text-sm px-2 py-1 rounded border border-blue-600 hover:bg-blue-50"
                                            aria-label={`${ilan.ad} ilanını incele`}
                                        >
                                            İncele
                                        </button>
                                    </div>
                                </td>
                                <td className="p-3">{ilan.tarih}</td>
                                <td className="p-3">{ilan.departman}</td>
                                <td className="p-3">{ilan.durum}</td>
                                <td className="p-3">
                                    <button
                                        onClick={() => handleIlanTikla(ilan.id)}
                                        className="text-blue-600 hover:underline"
                                        aria-label={`${ilan.ad} ilanına başvur`}
                                    >
                                        Başvur
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {/* Modal */}
            {isModalOpen && selectedIlan && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
                    <div className="bg-white rounded-lg shadow-xl max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <div className="p-6">
                            <div className="flex justify-between items-center mb-4">
                                <h2 className="text-2xl font-bold text-blue-700">{selectedIlan.ad}</h2>
                                <button
                                    onClick={handleCloseModal}
                                    className="text-gray-500 hover:text-gray-700"
                                    aria-label="Modalı kapat"
                                >
                                    <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                        <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                                    </svg>
                                </button>
                            </div>

                            <div className="space-y-4">
                                <div>
                                    <h3 className="text-lg font-semibold text-gray-800 mb-2">İş Tanımı</h3>
                                    <p className="text-gray-600">{selectedIlan.aciklama}</p>
                                </div>

                                <div>
                                    <h3 className="text-lg font-semibold text-gray-800 mb-2">Gereksinimler</h3>
                                    <ul className="list-disc list-inside text-gray-600">
                                        {selectedIlan.gereksinimler.map((gereksinim, index) => (
                                            <li key={index}>{gereksinim}</li>
                                        ))}
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default AdayDashboard;
