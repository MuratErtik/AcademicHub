import React, { useState } from "react";
import axios from "axios";
import { toast } from "react-hot-toast";

const dummyIlanlar = [
    {
        id: 1,
        ad: "Bilgisayar Mühendisliği Öğretim Üyesi",
        departman: "Bilgisayar Mühendisliği",
        baslangic: "2024-05-01",
        bitis: "2024-06-01",
        durum: "Kapalı",
        aciklama: "Bilgisayar Mühendisliği bölümü için öğretim üyesi alımı",
        gerekliBelgeler: "CV, Transkript, Referans Mektupları",
        basvuruKosullari: "Doktora derecesi, Yayın yapmış olmak",
        onKosulDurumu: "Geçti",
        juriler: [],
        basvurular: [
            {
                id: 1,
                firstName: "Ali",
                lastName: "Kaya",
                basvuruTarihi: "2024-05-15",
                durum: "Değerlendiriliyor",
                detaylar: {
                    egitim: "Doktora",
                    deneyim: "5 yıl",
                    yayinlar: "10 makale"
                }
            }
        ],
        juriRaporlari: [
            {
                juriId: 1,
                juriAdi: "Prof. Dr. Ahmet Yılmaz",
                rapor: "Adayın yayınları yeterli düzeyde..."
            }
        ]
    },
    {
        id: 2,
        ad: "Elektrik-Elektronik Mühendisliği Öğretim Üyesi",
        departman: "Elektrik-Elektronik Mühendisliği",
        baslangic: "2024-05-01",
        bitis: "2024-06-01",
        durum: "Açık",
        aciklama: "Elektrik-Elektronik Mühendisliği bölümü için öğretim üyesi alımı",
        gerekliBelgeler: "CV, Transkript, Referans Mektupları",
        basvuruKosullari: "Doktora derecesi, Yayın yapmış olmak",
        onKosulDurumu: "Kaldı",
        juriler: [],
        basvurular: [],
        juriRaporlari: []
    }
];

const YoneticiDashboard = () => {
    const [ilanlar, setIlanlar] = useState(dummyIlanlar);
    const [selectedIlan, setSelectedIlan] = useState(null);
    const [showIlanModal, setShowIlanModal] = useState(false);
    const [showBasvuruModal, setShowBasvuruModal] = useState(false);
    const [showDetayModal, setShowDetayModal] = useState(false);
    const [showRaporModal, setShowRaporModal] = useState(false);
    const [showJuriModal, setShowJuriModal] = useState(false);
    const [selectedBasvuru, setSelectedBasvuru] = useState(null);
    const [juriFormData, setJuriFormData] = useState([
        { email: "", tc: "" },
        { email: "", tc: "" },
        { email: "", tc: "" },
        { email: "", tc: "" },
        { email: "", tc: "" }
    ]);
    const [selectedJob, setSelectedJob] = useState(null);
    const [juryMembers, setJuryMembers] = useState({
        1: null,
        2: null,
        3: null,
        4: null,
        5: null
    });
    const [showJuryModal, setShowJuryModal] = useState(false);
    const [juryInputs, setJuryInputs] = useState({
        1: { name: "", email: "" },
        2: { name: "", email: "" },
        3: { name: "", email: "" },
        4: { name: "", email: "" },
        5: { name: "", email: "" }
    });

    const handleIlanInspect = (ilan) => {
        setSelectedIlan(ilan);
        setShowIlanModal(true);
    };

    const handleIlanUpdate = (updatedIlan) => {
        setIlanlar(prev => prev.map(ilan =>
            ilan.id === updatedIlan.id ? updatedIlan : ilan
        ));
        setShowIlanModal(false);
    };

    const handleDurumDegistir = (ilanId) => {
        setIlanlar(prev => prev.map(ilan =>
            ilan.id === ilanId
                ? { ...ilan, durum: ilan.durum === "Kapalı" ? "Açık" : "Kapalı" }
                : ilan
        ));
    };

    const handleBasvuruInspect = (ilan) => {
        setSelectedIlan(ilan);
        setShowBasvuruModal(true);
    };

    const handleBasvuruDetay = (basvuru) => {
        setSelectedBasvuru(basvuru);
        setShowDetayModal(true);
    };

    const handleRaporInspect = (ilan) => {
        setSelectedIlan(ilan);
        setShowRaporModal(true);
    };

    const handleJuriEkle = (ilan) => {
        setSelectedIlan(ilan);
        setJuriFormData(ilan.juriler.length > 0 ? ilan.juriler : [
            { email: "", tc: "" },
            { email: "", tc: "" },
            { email: "", tc: "" },
            { email: "", tc: "" },
            { email: "", tc: "" }
        ]);
        setShowJuriModal(true);
    };

    const handleJuriFormChange = (index, field, value) => {
        const updatedFormData = [...juriFormData];
        updatedFormData[index] = {
            ...updatedFormData[index],
            [field]: value
        };
        setJuriFormData(updatedFormData);
    };

    const handleJuriSubmit = () => {
        if (selectedIlan) {
            const updatedIlanlar = ilanlar.map(ilan =>
                ilan.id === selectedIlan.id
                    ? { ...ilan, juriler: juriFormData }
                    : ilan
            );
            setIlanlar(updatedIlanlar);
            setShowJuriModal(false);
        }
    };

    const handleJuryInputChange = (juryNumber, field, value) => {
        setJuryInputs(prev => ({
            ...prev,
            [juryNumber]: {
                ...prev[juryNumber],
                [field]: value
            }
        }));
    };

    const handleAddJuryMember = async (juryNumber) => {
        if (!selectedJob) {
            toast.error("Lütfen önce bir ilan seçin");
            return;
        }

        const juryData = juryInputs[juryNumber];
        if (!juryData.name || !juryData.email) {
            toast.error("Lütfen jüri üyesinin adı ve e-posta adresini girin");
            return;
        }

        try {
            const response = await axios.post('/api/jury/add', {
                jobId: selectedJob.id,
                juryNumber: juryNumber,
                name: juryData.name,
                email: juryData.email
            });

            if (response.data.success) {
                toast.success(`${juryNumber}. Jüri üyesi başarıyla eklendi`);
                // Clear the input fields after successful addition
                setJuryInputs(prev => ({
                    ...prev,
                    [juryNumber]: { name: "", email: "" }
                }));
            }
        } catch (error) {
            toast.error(`${juryNumber}. Jüri üyesi eklenirken bir hata oluştu`);
            console.error('Error adding jury member:', error);
        }
    };

    return (
        <div className="min-h-screen p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-red-700 mb-6">Yönetici Paneli</h1>

            {/* İlan Listesi */}
            <div className="overflow-x-auto mb-10">
                <h2 className="text-xl font-semibold mb-2">Mevcut İlanlar</h2>
                <table className="min-w-full bg-white rounded shadow">
                    <thead className="bg-red-100">
                        <tr>
                            <th className="text-left p-3">İlan Adı</th>
                            <th className="text-left p-3">Departman</th>
                            <th className="text-left p-3">Tarih</th>
                            <th className="text-left p-3">Durum</th>
                            <th className="text-left p-3">Ön Koşul Durumu</th>
                            <th className="text-left p-3">İşlemler</th>
                        </tr>
                    </thead>
                    <tbody>
                        {ilanlar.map((ilan) => (
                            <tr key={ilan.id} className="border-t hover:bg-red-50">
                                <td className="p-3">{ilan.ad}</td>
                                <td className="p-3">{ilan.departman}</td>
                                <td className="p-3">{ilan.baslangic} → {ilan.bitis}</td>
                                <td className="p-3">
                                    <button
                                        onClick={() => handleDurumDegistir(ilan.id)}
                                        className={`px-2 py-1 rounded text-sm ${ilan.durum === "Açık"
                                            ? "bg-green-100 text-green-800"
                                            : "bg-red-100 text-red-800"
                                            }`}
                                    >
                                        {ilan.durum}
                                    </button>
                                </td>
                                <td className="p-3">
                                    <span className={`font-medium ${ilan.onKosulDurumu === "Geçti"
                                        ? "text-green-600"
                                        : ilan.onKosulDurumu === "Kaldı"
                                            ? "text-red-600"
                                            : "text-gray-600"
                                        }`}>
                                        {ilan.onKosulDurumu}
                                    </span>
                                </td>
                                <td className="p-3 space-x-2">
                                    <button
                                        onClick={() => handleIlanInspect(ilan)}
                                        className="text-blue-600 hover:text-blue-800 underline"
                                    >
                                        İncele
                                    </button>
                                    <button
                                        onClick={() => handleBasvuruInspect(ilan)}
                                        className="text-purple-600 hover:text-purple-800 underline"
                                    >
                                        Başvurular
                                    </button>
                                    <button
                                        onClick={() => handleRaporInspect(ilan)}
                                        className="text-green-600 hover:text-green-800 underline"
                                    >
                                        Raporlar
                                    </button>
                                    <button
                                        onClick={() => handleJuriEkle(ilan)}
                                        className="text-orange-600 hover:text-orange-800 underline"
                                    >
                                        Jüri Ekle
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>

            {/* İlan İnceleme Modal */}
            {showIlanModal && selectedIlan && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">İlan Detayları</h2>
                        <div className="space-y-4">
                            <div>
                                <label className="block font-medium">Gerekli Belgeler</label>
                                <textarea
                                    value={selectedIlan.gerekliBelgeler}
                                    onChange={(e) => setSelectedIlan(prev => ({
                                        ...prev,
                                        gerekliBelgeler: e.target.value
                                    }))}
                                    className="input mt-1 w-full"
                                    rows={3}
                                />
                            </div>
                            <div>
                                <label className="block font-medium">Başvuru Koşulları</label>
                                <textarea
                                    value={selectedIlan.basvuruKosullari}
                                    onChange={(e) => setSelectedIlan(prev => ({
                                        ...prev,
                                        basvuruKosullari: e.target.value
                                    }))}
                                    className="input mt-1 w-full"
                                    rows={3}
                                />
                            </div>
                            <div className="flex justify-end space-x-2">
                                <button
                                    onClick={() => setShowIlanModal(false)}
                                    className="btn-secondary"
                                >
                                    İptal
                                </button>
                                <button
                                    onClick={() => handleIlanUpdate(selectedIlan)}
                                    className="btn-primary"
                                >
                                    Güncelle
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            )}

            {/* Başvurular Modal */}
            {showBasvuruModal && selectedIlan && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-4xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Başvurular</h2>
                        <table className="min-w-full bg-white border border-gray-200 rounded">
                            <thead className="bg-gray-100">
                                <tr>
                                    <th className="text-left p-2">Ad</th>
                                    <th className="text-left p-2">Soyad</th>
                                    <th className="text-left p-2">Başvuru Tarihi</th>
                                    <th className="text-left p-2">Durum</th>
                                    <th className="text-left p-2">İşlemler</th>
                                </tr>
                            </thead>
                            <tbody>
                                {selectedIlan.basvurular.map((basvuru) => (
                                    <tr key={basvuru.id} className="border-t hover:bg-gray-50">
                                        <td className="p-2">{basvuru.firstName}</td>
                                        <td className="p-2">{basvuru.lastName}</td>
                                        <td className="p-2">{basvuru.basvuruTarihi}</td>
                                        <td className="p-2">
                                            <select
                                                value={basvuru.durum}
                                                onChange={(e) => {
                                                    const updatedBasvurular = selectedIlan.basvurular.map(b =>
                                                        b.id === basvuru.id
                                                            ? { ...b, durum: e.target.value }
                                                            : b
                                                    );
                                                    setSelectedIlan(prev => ({
                                                        ...prev,
                                                        basvurular: updatedBasvurular
                                                    }));
                                                }}
                                                className="border rounded px-2 py-1"
                                            >
                                                <option value="Değerlendiriliyor">Değerlendiriliyor</option>
                                                <option value="Kabul Edildi">Kabul Edildi</option>
                                                <option value="Reddedildi">Reddedildi</option>
                                            </select>
                                        </td>
                                        <td className="p-2">
                                            <button
                                                onClick={() => handleBasvuruDetay(basvuru)}
                                                className="text-blue-600 hover:text-blue-800 underline"
                                            >
                                                Detaylar
                                            </button>
                                        </td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className="mt-4 flex justify-end">
                            <button
                                onClick={() => setShowBasvuruModal(false)}
                                className="btn-secondary"
                            >
                                Kapat
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Başvuru Detay Modal */}
            {showDetayModal && selectedBasvuru && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Başvuru Detayları</h2>
                        <table className="min-w-full bg-white border border-gray-200 rounded">
                            <tbody>
                                {Object.entries(selectedBasvuru.detaylar).map(([key, value]) => (
                                    <tr key={key} className="border-t">
                                        <td className="p-2 font-medium">{key}</td>
                                        <td className="p-2">{value}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className="mt-4 flex justify-end">
                            <button
                                onClick={() => setShowDetayModal(false)}
                                className="btn-secondary"
                            >
                                Kapat
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Jüri Raporları Modal */}
            {showRaporModal && selectedIlan && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Jüri Raporları</h2>
                        <div className="space-y-4">
                            {selectedIlan.juriRaporlari.map((rapor) => (
                                <div key={rapor.juriId} className="border rounded p-4">
                                    <h3 className="font-medium mb-2">{rapor.juriAdi}</h3>
                                    <p className="text-gray-700">{rapor.rapor}</p>
                                </div>
                            ))}
                        </div>
                        <div className="mt-4 flex justify-end">
                            <button
                                onClick={() => setShowRaporModal(false)}
                                className="btn-secondary"
                            >
                                Kapat
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Jüri Atama Modal */}
            {showJuriModal && selectedIlan && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Jüri Üyeleri Atama</h2>
                        <div className="space-y-6">
                            {juriFormData.map((juri, index) => (
                                <div key={index} className="border rounded p-4 bg-gray-50">
                                    <h3 className="font-medium mb-3">Jüri {index + 1}</h3>
                                    <div className="space-y-3">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700 mb-1">
                                                E-posta
                                            </label>
                                            <input
                                                type="email"
                                                value={juri.email}
                                                onChange={(e) => handleJuriFormChange(index, 'email', e.target.value)}
                                                className="input w-full"
                                                placeholder="juri@example.com"
                                            />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700 mb-1">
                                                TC Kimlik No
                                            </label>
                                            <input
                                                type="text"
                                                value={juri.tc}
                                                onChange={(e) => handleJuriFormChange(index, 'tc', e.target.value)}
                                                className="input w-full"
                                                placeholder="12345678901"
                                            />
                                        </div>
                                    </div>
                                </div>
                            ))}
                        </div>
                        <div className="mt-6 flex justify-end space-x-2">
                            <button
                                onClick={() => setShowJuriModal(false)}
                                className="btn-secondary"
                            >
                                İptal
                            </button>
                            <button
                                onClick={handleJuriSubmit}
                                className="btn-primary"
                            >
                                Kaydet
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Jüri Üyeleri */}
            {selectedJob && (
                <div className="mt-8 bg-white p-6 rounded-lg shadow">
                    <h3 className="text-lg font-semibold mb-4">Jüri Üyeleri</h3>
                    <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                        {[1, 2, 3, 4, 5].map((juryNumber) => (
                            <div key={juryNumber} className="flex flex-col p-4 bg-gray-50 rounded-lg">
                                <div className="flex justify-between items-center mb-2">
                                    <span className="font-medium">{juryNumber}. Jüri Üyesi</span>
                                    <button
                                        onClick={() => handleAddJuryMember(juryNumber)}
                                        className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition-colors"
                                    >
                                        {juryMembers[juryNumber] ? "Güncelle" : "Ekle"}
                                    </button>
                                </div>
                                {juryMembers[juryNumber] && (
                                    <div className="mt-2 text-sm text-gray-600">
                                        <p>Ad: {juryMembers[juryNumber].name}</p>
                                        <p>E-posta: {juryMembers[juryNumber].email}</p>
                                    </div>
                                )}
                            </div>
                        ))}
                    </div>
                </div>
            )}

            {/* Jury Members Modal */}
            {showJuryModal && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full">
                        <div className="flex justify-between items-center mb-4">
                            <h3 className="text-lg font-semibold">Jüri Üyeleri Ekle</h3>
                            <button
                                onClick={() => setShowJuryModal(false)}
                                className="text-gray-500 hover:text-gray-700"
                            >
                                <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                                    <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M6 18L18 6M6 6l12 12" />
                                </svg>
                            </button>
                        </div>

                        <div className="space-y-4">
                            {[1, 2, 3, 4, 5].map((juryNumber) => (
                                <div key={juryNumber} className="flex items-center space-x-4">
                                    <div className="flex-1 space-y-2">
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">
                                                {juryNumber}. Jüri Üyesi Adı
                                            </label>
                                            <input
                                                type="text"
                                                value={juryInputs[juryNumber].name}
                                                onChange={(e) => handleJuryInputChange(juryNumber, 'name', e.target.value)}
                                                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                                                placeholder="Jüri üyesi adı"
                                            />
                                        </div>
                                        <div>
                                            <label className="block text-sm font-medium text-gray-700">
                                                E-posta
                                            </label>
                                            <input
                                                type="email"
                                                value={juryInputs[juryNumber].email}
                                                onChange={(e) => handleJuryInputChange(juryNumber, 'email', e.target.value)}
                                                className="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-blue-500 focus:ring-blue-500"
                                                placeholder="E-posta adresi"
                                            />
                                        </div>
                                    </div>
                                    <button
                                        onClick={() => handleAddJuryMember(juryNumber)}
                                        className="px-4 py-2 bg-blue-600 text-white rounded hover:bg-blue-700 transition-colors"
                                    >
                                        Ekle
                                    </button>
                                </div>
                            ))}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default YoneticiDashboard;
