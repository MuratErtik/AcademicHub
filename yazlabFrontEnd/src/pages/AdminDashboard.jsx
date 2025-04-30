import React, { useState } from "react";

const dummyBasvurular = [
    { ilanId: 1, firstName: "Ali", lastName: "Kaya", tc: "12345678901", status: "Kabul Edildi" },
    { ilanId: 1, firstName: "Ayşe", lastName: "Demir", tc: "23456789012", status: "Değerlendiriliyor" },
    { ilanId: 2, firstName: "Mehmet", lastName: "Yılmaz", tc: "34567890123", status: "Reddedildi" },
    { ilanId: 2, firstName: "Fatma", lastName: "Öztürk", tc: "45678901234", status: "Kabul Edildi" },
];

const AdminDashboard = () => {
    const [ilanlar, setIlanlar] = useState([]);
    const [formData, setFormData] = useState({
        id: null,
        ad: "",
        baslangic: "",
        bitis: "",
        departman: "",
        aciklama: "",
        durum: "Kapalı",
    });

    const [expandedIlanId, setExpandedIlanId] = useState(null);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: value,
        }));
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (formData.id) {
            setIlanlar((prev) =>
                prev.map((ilan) =>
                    ilan.id === formData.id ? { ...formData } : ilan
                )
            );
        } else {
            const yeniIlan = { ...formData, id: Date.now(), durum: "Kapalı" };
            setIlanlar((prev) => [...prev, yeniIlan]);
        }
        setFormData({
            id: null,
            ad: "",
            baslangic: "",
            bitis: "",
            departman: "",
            aciklama: "",
            durum: "Kapalı",
        });
        setExpandedIlanId(null);
    };

    const handleDuzenle = (ilan) => {
        setFormData(ilan);
    };

    const toggleExpandIlan = (ilanId) => {
        setExpandedIlanId(expandedIlanId === ilanId ? null : ilanId);
    };

    const filtrelenmisBasvurular = (ilanId) => {
        return dummyBasvurular.filter((b) => b.ilanId === ilanId);
    };

    return (
        <div className="min-h-screen p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-green-700 mb-6">Admin Paneli</h1>

            {/* İlan Oluştur / Düzenle */}
            <form onSubmit={handleSubmit} className="bg-white p-6 rounded shadow mb-8 space-y-4">
                <h2 className="text-xl font-semibold">{formData.id ? "İlan Düzenle" : "Yeni İlan Oluştur"}</h2>

                <input name="ad" value={formData.ad} onChange={handleChange} placeholder="İlan Adı" className="input" required />
                <div className="flex gap-4">
                    <input name="baslangic" type="date" value={formData.baslangic} onChange={handleChange} className="input w-1/2" required />
                    <input name="bitis" type="date" value={formData.bitis} onChange={handleChange} className="input w-1/2" required />
                </div>
                <input name="departman" value={formData.departman} onChange={handleChange} placeholder="Departman Adı" className="input" required />
                <textarea
                    name="aciklama"
                    value={formData.aciklama}
                    onChange={handleChange}
                    placeholder="İlan Açıklaması"
                    className="input"
                    rows={3}
                    required
                />
                <p className="text-sm text-gray-600 italic">
                    Not: İlan, yönetici onayından sonra "Açık" durumuna geçecektir.
                </p>

                <button type="submit" className="btn-primary">
                    {formData.id ? "Güncelle" : "Oluştur"}
                </button>
            </form>

            {/* İlan Listesi */}
            <div className="overflow-x-auto mb-10">
                <h2 className="text-xl font-semibold mb-2">Mevcut İlanlar</h2>
                <table className="min-w-full bg-white rounded shadow">
                    <thead className="bg-green-100">
                        <tr>
                            <th className="text-left p-3">İlan Adı</th>
                            <th className="text-left p-3">Tarih</th>
                            <th className="text-left p-3">Departman</th>
                            <th className="text-left p-3">Durum</th>
                            <th className="text-left p-3">İşlemler</th>
                        </tr>
                    </thead>
                    <tbody>
                        {ilanlar.map((ilan) => (
                            <React.Fragment key={ilan.id}>
                                <tr className="border-t hover:bg-green-50">
                                    <td className="p-3">{ilan.ad}</td>
                                    <td className="p-3">{ilan.baslangic} → {ilan.bitis}</td>
                                    <td className="p-3">{ilan.departman}</td>
                                    <td className="p-3">
                                        <span className={`px-2 py-1 rounded text-sm ${ilan.durum === "Açık" ? "bg-green-100 text-green-800" : "bg-red-100 text-red-800"}`}>
                                            {ilan.durum}
                                        </span>
                                    </td>
                                    <td className="p-3 space-x-2">
                                        <button
                                            onClick={() => handleDuzenle(ilan)}
                                            className="text-blue-600 hover:text-blue-800 underline"
                                        >
                                            Düzenle
                                        </button>
                                        <button
                                            onClick={() => toggleExpandIlan(ilan.id)}
                                            className="text-purple-600 hover:text-purple-800 underline"
                                        >
                                            Başvurular {expandedIlanId === ilan.id ? "▲" : "▼"}
                                        </button>
                                    </td>
                                </tr>

                                {/* Expandable Applications Card */}
                                {expandedIlanId === ilan.id && (
                                    <tr>
                                        <td colSpan="5" className="p-0">
                                            <div className="bg-gray-50 p-4 border-t border-b border-gray-200">
                                                <h3 className="font-medium mb-2">Başvurular</h3>

                                                <div className="max-h-64 overflow-y-auto">
                                                    {filtrelenmisBasvurular(ilan.id).length > 0 ? (
                                                        <table className="min-w-full bg-white border border-gray-200 rounded">
                                                            <thead className="bg-gray-100">
                                                                <tr>
                                                                    <th className="text-left p-2">Ad</th>
                                                                    <th className="text-left p-2">Soyad</th>
                                                                    <th className="text-left p-2">Durum</th>
                                                                    <th className="text-left p-2">İşlemler</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                {filtrelenmisBasvurular(ilan.id).map((basvuru, idx) => (
                                                                    <tr key={idx} className="border-t hover:bg-gray-50">
                                                                        <td className="p-2">{basvuru.firstName}</td>
                                                                        <td className="p-2">{basvuru.lastName}</td>
                                                                        <td className="p-2">
                                                                            <span className={`px-2 py-1 rounded text-xs ${basvuru.status === "Kabul Edildi"
                                                                                ? "bg-green-100 text-green-800"
                                                                                : basvuru.status === "Reddedildi"
                                                                                    ? "bg-red-100 text-red-800"
                                                                                    : "bg-yellow-100 text-yellow-800"
                                                                                }`}>
                                                                                {basvuru.status}
                                                                            </span>
                                                                        </td>
                                                                        <td className="p-2">
                                                                            <button className="text-blue-600 text-sm hover:text-blue-800 underline">
                                                                                Detaylar
                                                                            </button>
                                                                        </td>
                                                                    </tr>
                                                                ))}
                                                            </tbody>
                                                        </table>
                                                    ) : (
                                                        <p className="text-gray-500 italic">Bu ilana başvuru bulunmamaktadır.</p>
                                                    )}
                                                </div>
                                            </div>
                                        </td>
                                    </tr>
                                )}
                            </React.Fragment>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default AdminDashboard;
