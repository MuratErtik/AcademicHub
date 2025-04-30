import React from "react";

const Basvurularim = () => {
    // Şimdilik dummy veri
    const basvurular = [
        {
            id: 1,
            ilanAd: "Dr. Öğr. Üyesi - Bilgisayar Müh.",
            tarih: "2025-04-01",
            departman: "Bilgisayar Mühendisliği",
            durum: "Beklemede",
        },
        {
            id: 2,
            ilanAd: "Doçent - Elektrik Müh.",
            tarih: "2025-03-15",
            departman: "Elektrik Mühendisliği",
            durum: "Onaylandı",
        },
    ];

    return (
        <div className="p-8 bg-gray-50 min-h-screen">
            <h1 className="text-3xl font-bold text-blue-700 mb-6">Başvurularım</h1>

            {basvurular.length === 0 ? (
                <p>Henüz başvuru yapmadınız.</p>
            ) : (
                <div className="overflow-x-auto">
                    <table className="min-w-full bg-white rounded shadow">
                        <thead className="bg-blue-100">
                            <tr>
                                <th className="text-left p-3">İlan Adı</th>
                                <th className="text-left p-3">Tarih</th>
                                <th className="text-left p-3">Departman</th>
                                <th className="text-left p-3">Durum</th>
                            </tr>
                        </thead>
                        <tbody>
                            {basvurular.map((basvuru) => (
                                <tr key={basvuru.id} className="border-t hover:bg-blue-50">
                                    <td className="p-3">{basvuru.ilanAd}</td>
                                    <td className="p-3">{basvuru.tarih}</td>
                                    <td className="p-3">{basvuru.departman}</td>
                                    <td className="p-3">
                                        <span
                                            className={`px-2 py-1 rounded text-sm font-medium ${basvuru.durum === "Onaylandı"
                                                    ? "bg-green-100 text-green-700"
                                                    : basvuru.durum === "Reddedildi"
                                                        ? "bg-red-100 text-red-700"
                                                        : "bg-yellow-100 text-yellow-700"
                                                }`}
                                        >
                                            {basvuru.durum}
                                        </span>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Basvurularim;
