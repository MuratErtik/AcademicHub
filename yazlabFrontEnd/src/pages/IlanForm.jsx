import React, { useState } from "react";


const steps = [
    "Kişisel Bilgiler",
    "Eğitim Bilgileri",
    "Yabancı Dil",
    "Akademik Unvan",
    "Yayınlar",
    "Projeler",
    "Atıflar",
    "Ders Yükü",
    "Tez Danışmanlık",
    "Bildiriler",
    "Ödüller",
];
const stepRequiredFields = {
    0: ["ad", "soyad", "tcno", "dogumTarihi", "telefon", "email", "adres"],
    1: ["lisans_uni", "lisans_bolum", "lisans_tarih"],
    2: ["dil_sinav_turu", "dil_puan", "dil_belge"],
    3: [], // akademik unvanlar varsa boş geçilebilir
    4: ["yayin_baslik", "yayin_dergi", "yayin_yil", "yayin_yazar", "yayin_pdf"],
    5: ["proje_adi", "proje_turu", "proje_rol", "proje_baslangic", "proje_bitis", "proje_butce", "proje_belge"],
    6: ["atif_yayinlar", "atif_eserler", "atif_sayisi", "atif_belgeler"],
    7: ["ders_adi", "ders_donem", "ders_kredi", "ders_seviye"],
    8: ["tez_seviye", "tez_ogrenci", "tez_konusu", "tez_durum"],
    9: ["etkinlik_adi", "etkinlik_tarih_yer", "etkinlik_turu", "etkinlik_belge"],
    10: [], // ödüller varsa, boş geçilebilir
};

const IlanForm = () => {
    const [currentStep, setCurrentStep] = useState(0);
    const [completedSteps, setCompletedSteps] = useState([]);
    const [formData, setFormData] = useState({
        yayinlar: [],
        projeler: [],
        atiflar: [],
        dersler: [],
        tezler: [],
        bildiriler: [],
        oduller: [],
        yabanci_diller: []
    });

    const [yayin, setYayin] = useState({
        yayin_baslik: "",
        yayin_dergi: "",
        yayin_yil: "",
        yayin_yazar: "",
        yayin_doi: "",
        yayin_pdf: null
    });
    const [proje, setProje] = useState({
        proje_adi: "",
        proje_turu: "",
        proje_rol: "",
        proje_baslangic: "",
        proje_bitis: "",
        proje_butce: "",
        proje_belge: null
    });
    const [atif, setAtif] = useState({
        atif_yayinlar: "",
        atif_eserler: "",
        atif_sayisi: "",
        atif_belgeler: null
    });
    const [ders, setDers] = useState({
        ders_adi: "",
        ders_donem: "",
        ders_kredi: "",
        ders_seviye: ""
    });
    const [tez, setTez] = useState({
        tez_seviye: "",
        tez_ogrenci: "",
        tez_konusu: "",
        tez_durum: ""
    });
    const [bildiri, setBildiri] = useState({
        etkinlik_adi: "",
        etkinlik_tarih_yer: "",
        etkinlik_turu: "",
        etkinlik_belge: null
    });
    const [odul, setOdul] = useState({
        odul_adi: "",
        odul_kurulus: "",
        odul_yil: "",
        odul_belge: null
    });
    const [dil, setDil] = useState({
        dil_sinav_turu: "",
        dil_puan: "",
        dil_belge: null
    });

    const isLastStep = currentStep === steps.length - 1;

    const handleChange = (e) => {
        const { name, value, files } = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: files ? (files.length > 1 ? files : files[0]) : value,
        }));
    };

    const goToStep = (index) => {
        // Sadece tamamlanan veya aktif adım seçilebilir
        if (index <= completedSteps.length) {
            setCurrentStep(index);
        }
    };
    const isStepValid = (index) => {
        const requiredFields = stepRequiredFields[index] || [];

        // Eğer zorunlu alan hiç yoksa (boş []), geçerli sayma!
        if (requiredFields.length === 0) return false;

        // Diğer adımlarda her alan doluysa geçerli say
        return requiredFields.every((field) => formData[field]);
    };




    const handleNext = () => {
        if (!completedSteps.includes(currentStep)) {
            setCompletedSteps([...completedSteps, currentStep]);
        }

        if (currentStep < steps.length - 1) {
            setCurrentStep(currentStep + 1);
        }
    };




    const handleBack = () => {
        setCurrentStep((prev) => Math.max(prev - 1, 0));
    };

    const handleFinish = () => {
        // 1. Tüm step'lerdeki zorunlu alanları kontrol et
        const allValid = Object.keys(stepRequiredFields).every((stepIndex) => {
            const requiredFields = stepRequiredFields[stepIndex];
            return requiredFields.every((field) => formData[field]);
        });

        // 2. Eksik varsa uyar
        if (!allValid) {
            alert("Lütfen tüm adımlardaki gerekli alanları doldurun.");
            return;
        }

        // 3. Eksik yoksa form gönderimi
        console.log("Tüm form verisi:", formData);

        // TODO: Backend'e gönderim burada yapılacak
        // fetch('/api/basvuru', { method: 'POST', body: JSON.stringify(formData), headers: { 'Content-Type': 'application/json' } });

        alert("Başvurunuz başarıyla tamamlandı 🎉");
    };


    return (
        <div className="flex min-h-screen bg-gray-50">
            {/* Sol: Adımlar */}
            <aside className="w-64 bg-white border-r p-4">
                <h2 className="text-xl font-bold mb-4 text-blue-800">Başvuru Aşamaları</h2>
                <ul className="space-y-2">
                    {steps.map((step, index) => (
                        <li
                            key={index}
                            onClick={() => goToStep(index)}
                            className={`cursor-pointer p-2 rounded transition duration-150 ${index === currentStep
                                ? "bg-blue-600 text-white"
                                : isStepValid(index)
                                    ? "bg-green-100 text-green-700"
                                    : index < currentStep
                                        ? "bg-red-100 text-red-600"
                                        : "bg-gray-100 text-gray-500"
                                }`}
                        >
                            {step}
                        </li>
                    ))}
                </ul>

            </aside>

            {/* Sağ: Form İçeriği */}
            <main className="flex-1 p-6">
                <h2 className="text-2xl font-semibold mb-4 text-blue-700">{steps[currentStep]}</h2>

                {/* Buraya her adıma göre form içeriği eklenecek */}
                {currentStep === 0 && (
                    <div className="space-y-4">
                        <input name="ad" type="text" placeholder="Ad" className="input" onChange={handleChange} required />
                        <input name="soyad" type="text" placeholder="Soyad" className="input" onChange={handleChange} required />
                        <input name="tcno" type="text" placeholder="T.C. Kimlik No" className="input" onChange={handleChange} required />
                        <input name="dogumTarihi" type="date" className="input" onChange={handleChange} required />
                        <input name="telefon" type="tel" placeholder="Telefon" className="input" onChange={handleChange} required />
                        <input name="email" type="email" placeholder="E-posta" className="input" onChange={handleChange} required />
                        <textarea name="adres" rows={3} placeholder="Adres" className="input" onChange={handleChange} required />
                    </div>
                )}
                {currentStep === 1 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Lisans</h3>
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                            <input name="lisans_uni" type="text" placeholder="Üniversite Adı" className="input" onChange={handleChange} required />
                            <input name="lisans_bolum" type="text" placeholder="Bölüm" className="input" onChange={handleChange} required />
                            <input name="lisans_tarih" type="date" className="input" onChange={handleChange} required />
                        </div>

                        <h3 className="text-lg font-medium text-blue-700 mt-6 mb-2">Yüksek Lisans (Varsa)</h3>
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                            <input name="yuksek_uni" type="text" placeholder="Üniversite Adı" className="input" onChange={handleChange} />
                            <input name="yuksek_bolum" type="text" placeholder="Bölüm" className="input" onChange={handleChange} />
                            <input name="yuksek_tarih" type="date" className="input" onChange={handleChange} />
                        </div>

                        <h3 className="text-lg font-medium text-blue-700 mt-6 mb-2">Doktora (Varsa)</h3>
                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                            <input name="doktora_uni" type="text" placeholder="Üniversite Adı" className="input" onChange={handleChange} />
                            <input name="doktora_bolum" type="text" placeholder="Bölüm" className="input" onChange={handleChange} />
                            <input name="doktora_tarih" type="date" className="input" onChange={handleChange} />
                        </div>

                        <h3 className="text-lg font-medium text-blue-700 mt-6 mb-2">Diplomalar (PDF / Görsel)</h3>
                        <input
                            name="diploma_dosyalari"
                            type="file"
                            accept=".pdf,image/*"
                            multiple
                            className="input"
                            onChange={handleChange}
                        />
                    </div>
                )}
                {currentStep === 2 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Yabancı Dil Bilgisi</h3>

                        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                            <div>
                                <label className="block font-medium mb-1">Sınav Türü</label>
                                <select
                                    name="dil_sinav_turu"
                                    className="input"
                                    value={dil.dil_sinav_turu}
                                    onChange={(e) => setDil({ ...dil, dil_sinav_turu: e.target.value })}
                                    required
                                >
                                    <option value="">Seçiniz</option>
                                    <option value="YDS">YDS</option>
                                    <option value="YÖKDİL">YÖKDİL</option>
                                    <option value="TOEFL">TOEFL</option>
                                    <option value="IELTS">IELTS</option>
                                    <option value="Diğer">Diğer</option>
                                </select>
                            </div>

                            <div>
                                <label className="block font-medium mb-1">Puan</label>
                                <input
                                    name="dil_puan"
                                    type="number"
                                    className="input"
                                    value={dil.dil_puan}
                                    onChange={(e) => setDil({ ...dil, dil_puan: e.target.value })}
                                    placeholder="Örn: 85"
                                    required
                                />
                            </div>

                            <div>
                                <label className="block font-medium mb-1">Belge (PDF/Görsel)</label>
                                <input
                                    name="dil_belge"
                                    type="file"
                                    accept=".pdf,image/*"
                                    className="input"
                                    onChange={(e) => setDil({ ...dil, dil_belge: e.target.files[0] })}
                                    required
                                />
                            </div>
                        </div>

                        {/* Dil Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    yabanci_diller: [...(prev.yabanci_diller || []), dil]
                                }));

                                setDil({
                                    dil_sinav_turu: "",
                                    dil_puan: "",
                                    dil_belge: null
                                });
                            }}
                        >
                            ➕ Yabancı Dil Ekle
                        </button>

                        {/* Eklenen Dilleri Göster */}
                        {formData.yabanci_diller?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Yabancı Diller</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.yabanci_diller.map((d, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>🌐 {d.dil_sinav_turu} - {d.dil_puan} puan</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        yabanci_diller: prev.yabanci_diller.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}
                {currentStep === 3 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Akademik Unvan Bilgileri (Varsa)</h3>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <label className="block font-medium mb-1">Doçentlik Unvanı Tarihi</label>
                                <input
                                    name="unvan_docent_tarih"
                                    type="date"
                                    className="input"
                                    onChange={handleChange}
                                />
                            </div>

                            <div>
                                <label className="block font-medium mb-1">Profesörlük Unvanı Tarihi</label>
                                <input
                                    name="unvan_prof_tarih"
                                    type="date"
                                    className="input"
                                    onChange={handleChange}
                                />
                            </div>
                        </div>
                    </div>
                )}
                {currentStep === 4 && (
                    <div className="space-y-4">
                        <div className="space-y-6">
                            <h2 className="text-2xl font-bold text-blue-700">Yayınlar</h2>
                            <h3 className="text-lg font-semibold text-blue-600">Yayın Bilgileri</h3>

                            {/* Makale Başlığı */}
                            <div>
                                <label className="block font-medium">Makale Başlığı</label>
                                <input
                                    name="yayin_baslik"
                                    value={yayin.yayin_baslik}
                                    onChange={(e) => setYayin({ ...yayin, yayin_baslik: e.target.value })}
                                    placeholder="Makale başlığını giriniz"
                                    className="input"
                                />
                            </div>

                            {/* Dergi ve Yıl */}
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block font-medium">Dergi Adı ve Türü</label>
                                    <input
                                        name="yayin_dergi"
                                        value={yayin.yayin_dergi}
                                        onChange={(e) => setYayin({ ...yayin, yayin_dergi: e.target.value })}
                                        placeholder="SCI-E, SSCI, ESCI, TR Dizin, vb."
                                        className="input"
                                    />
                                </div>
                                <div>
                                    <label className="block font-medium">Yıl</label>
                                    <input
                                        name="yayin_yil"
                                        value={yayin.yayin_yil}
                                        onChange={(e) => setYayin({ ...yayin, yayin_yil: e.target.value })}
                                        placeholder="YYYY"
                                        className="input"
                                    />
                                </div>
                            </div>

                            {/* Yazar Sırası & DOI */}
                            <div className="grid grid-cols-2 gap-4">
                                <div>
                                    <label className="block font-medium">Yazar Sırası</label>
                                    <select
                                        name="yayin_yazar"
                                        value={yayin.yayin_yazar}
                                        onChange={(e) => setYayin({ ...yayin, yayin_yazar: e.target.value })}
                                        className="input"
                                    >
                                        <option value="">Seçiniz</option>
                                        <option value="1">1. Yazar</option>
                                        <option value="2">2. Yazar</option>
                                        <option value="son">Son Yazar</option>
                                    </select>
                                </div>
                                <div>
                                    <label className="block font-medium">DOI Numarası</label>
                                    <input
                                        name="yayin_doi"
                                        value={yayin.yayin_doi}
                                        onChange={(e) => setYayin({ ...yayin, yayin_doi: e.target.value })}
                                        placeholder="10.xxxx/xxxx"
                                        className="input"
                                    />
                                </div>
                            </div>

                            {/* PDF Yükleme */}
                            <div>
                                <label className="block font-medium">Makale PDF Yükleme</label>
                                <input
                                    type="file"
                                    accept=".pdf"
                                    onChange={(e) =>
                                        setYayin({ ...yayin, yayin_pdf: e.target.files[0] })
                                    }
                                    className="input"
                                />
                            </div>

                            {/* Makale Ekle Butonu */}
                            <button
                                type="button"
                                onClick={() => {
                                    setFormData((prev) => ({
                                        ...prev,
                                        yayinlar: [...(prev.yayinlar || []), yayin],
                                    }));

                                    setYayin({
                                        yayin_baslik: "",
                                        yayin_dergi: "",
                                        yayin_yil: "",
                                        yayin_yazar: "",
                                        yayin_doi: "",
                                        yayin_pdf: null,
                                    });
                                }}
                                className="btn-secondary mt-4"
                            >
                                ➕ Makale Ekle
                            </button>

                            {/* Eklenen Yayınları Göster */}
                            {formData.yayinlar?.length > 0 && (
                                <div className="mt-4">
                                    <h4 className="font-semibold mb-2">Eklenen Yayınlar</h4>
                                    <ul className="text-sm text-gray-700 space-y-1">
                                        {formData.yayinlar.map((y, idx) => (
                                            <li key={idx} className="flex items-center">
                                                <span>📄 {y.yayin_baslik} ({y.yayin_yil})</span>
                                                <button
                                                    type="button"
                                                    className="ml-2 text-red-500 hover:text-red-700"
                                                    onClick={() => {
                                                        setFormData(prev => ({
                                                            ...prev,
                                                            yayinlar: prev.yayinlar.filter((_, i) => i !== idx)
                                                        }));
                                                    }}
                                                >
                                                    ❌
                                                </button>
                                            </li>
                                        ))}
                                    </ul>
                                </div>
                            )}
                        </div>

                    </div>
                )}
                {currentStep === 5 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Proje Bilgileri</h3>

                        <div>
                            <label className="block font-medium">Proje Adı</label>
                            <input
                                name="proje_adi"
                                value={proje.proje_adi}
                                onChange={(e) => setProje({ ...proje, proje_adi: e.target.value })}
                                placeholder="Proje başlığı"
                                className="input"
                            />
                        </div>

                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label className="block font-medium">Proje Türü</label>
                                <select
                                    name="proje_turu"
                                    value={proje.proje_turu}
                                    onChange={(e) => setProje({ ...proje, proje_turu: e.target.value })}
                                    className="input"
                                >
                                    <option value="">Seçiniz</option>
                                    <option value="BAP">BAP</option>
                                    <option value="TÜBİTAK">TÜBİTAK</option>
                                    <option value="AB">Avrupa Birliği</option>
                                    <option value="Diğer">Diğer</option>
                                </select>
                            </div>
                            <div>
                                <label className="block font-medium">Projede Rolü</label>
                                <input
                                    name="proje_rol"
                                    value={proje.proje_rol}
                                    onChange={(e) => setProje({ ...proje, proje_rol: e.target.value })}
                                    placeholder="Yürütücü, araştırmacı vb."
                                    className="input"
                                />
                            </div>
                        </div>

                        <div className="grid grid-cols-2 gap-4">
                            <div>
                                <label className="block font-medium">Başlangıç Tarihi</label>
                                <input
                                    name="proje_baslangic"
                                    type="date"
                                    value={proje.proje_baslangic}
                                    onChange={(e) => setProje({ ...proje, proje_baslangic: e.target.value })}
                                    className="input"
                                />
                            </div>
                            <div>
                                <label className="block font-medium">Bitiş Tarihi</label>
                                <input
                                    name="proje_bitis"
                                    type="date"
                                    value={proje.proje_bitis}
                                    onChange={(e) => setProje({ ...proje, proje_bitis: e.target.value })}
                                    className="input"
                                />
                            </div>
                        </div>

                        <div>
                            <label className="block font-medium">Bütçe (₺)</label>
                            <input
                                name="proje_butce"
                                type="number"
                                value={proje.proje_butce}
                                onChange={(e) => setProje({ ...proje, proje_butce: e.target.value })}
                                className="input"
                            />
                        </div>

                        <div>
                            <label className="block font-medium">Belge (PDF)</label>
                            <input
                                name="proje_belge"
                                type="file"
                                accept=".pdf"
                                className="input"
                                onChange={(e) => setProje({ ...proje, proje_belge: e.target.files[0] })}
                            />
                        </div>

                        {/* ➕ Proje Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    projeler: [...(prev.projeler || []), proje]
                                }));

                                setProje({
                                    proje_adi: "",
                                    proje_turu: "",
                                    proje_rol: "",
                                    proje_baslangic: "",
                                    proje_bitis: "",
                                    proje_butce: "",
                                    proje_belge: null
                                });
                            }}
                        >
                            ➕ Proje Ekle
                        </button>

                        {/* Listele */}
                        {formData.projeler?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Projeler</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.projeler.map((p, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>📁 {p.proje_adi} ({p.proje_turu})</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        projeler: prev.projeler.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}

                {currentStep === 6 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Atıf Bilgileri</h3>

                        <div>
                            <label className="block font-medium mb-1">Yayın Ad(lar)ı</label>
                            <input
                                name="atif_yayinlar"
                                type="text"
                                className="input"
                                value={atif.atif_yayinlar}
                                onChange={(e) => setAtif({ ...atif, atif_yayinlar: e.target.value })}
                                placeholder="Atıf yapılan yayın(lar)ı yazınız"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Atıf Yapılan Eser(ler)</label>
                            <textarea
                                name="atif_eserler"
                                rows={3}
                                className="input"
                                value={atif.atif_eserler}
                                onChange={(e) => setAtif({ ...atif, atif_eserler: e.target.value })}
                                placeholder="Atıf yapılan eser detaylarını giriniz"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Toplam Atıf Sayısı</label>
                            <input
                                name="atif_sayisi"
                                type="number"
                                className="input"
                                value={atif.atif_sayisi}
                                onChange={(e) => setAtif({ ...atif, atif_sayisi: e.target.value })}
                                placeholder="Örn: 5"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Kaynak Belgeler (PDF/Görsel)</label>
                            <input
                                name="atif_belgeler"
                                type="file"
                                accept=".pdf,image/*"
                                multiple
                                className="input"
                                onChange={(e) => setAtif({ ...atif, atif_belgeler: e.target.files[0] })}
                                required
                            />
                        </div>

                        {/* Atıf Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    atiflar: [...(prev.atiflar || []), atif]
                                }));

                                setAtif({
                                    atif_yayinlar: "",
                                    atif_eserler: "",
                                    atif_sayisi: "",
                                    atif_belgeler: null
                                });
                            }}
                        >
                            ➕ Atıf Ekle
                        </button>

                        {/* Listele */}
                        {formData.atiflar?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Atıflar</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.atiflar.map((a, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>📚 {a.atif_yayinlar} - {a.atif_sayisi} atıf</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        atiflar: prev.atiflar.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}
                {currentStep === 7 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Ders Yükü Bilgileri</h3>

                        <div>
                            <label className="block font-medium mb-1">Ders Adı</label>
                            <input
                                name="ders_adi"
                                type="text"
                                className="input"
                                value={ders.ders_adi}
                                onChange={(e) => setDers({ ...ders, ders_adi: e.target.value })}
                                placeholder="Dersin tam adını giriniz"
                                required
                            />
                        </div>

                        <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                            <div>
                                <label className="block font-medium mb-1">Dönem</label>
                                <input
                                    name="ders_donem"
                                    type="text"
                                    className="input"
                                    value={ders.ders_donem}
                                    onChange={(e) => setDers({ ...ders, ders_donem: e.target.value })}
                                    placeholder="Örn: Güz 2023"
                                    required
                                />
                            </div>

                            <div>
                                <label className="block font-medium mb-1">Kredi / Saat</label>
                                <input
                                    name="ders_kredi"
                                    type="text"
                                    className="input"
                                    value={ders.ders_kredi}
                                    onChange={(e) => setDers({ ...ders, ders_kredi: e.target.value })}
                                    placeholder="Örn: 3 / 4"
                                    required
                                />
                            </div>
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Ders Seviyesi</label>
                            <select
                                name="ders_seviye"
                                className="input"
                                value={ders.ders_seviye}
                                onChange={(e) => setDers({ ...ders, ders_seviye: e.target.value })}
                                required
                            >
                                <option value="">Seçiniz</option>
                                <option value="Önlisans">Önlisans</option>
                                <option value="Lisans">Lisans</option>
                                <option value="Lisansüstü">Lisansüstü</option>
                            </select>
                        </div>

                        {/* Ders Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    dersler: [...(prev.dersler || []), ders]
                                }));

                                setDers({
                                    ders_adi: "",
                                    ders_donem: "",
                                    ders_kredi: "",
                                    ders_seviye: ""
                                });
                            }}
                        >
                            ➕ Ders Ekle
                        </button>

                        {/* Listele */}
                        {formData.dersler?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Dersler</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.dersler.map((d, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>📘 {d.ders_adi} ({d.ders_seviye})</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        dersler: prev.dersler.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}
                {currentStep === 8 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Tez Danışmanlıkları</h3>

                        <div>
                            <label className="block font-medium mb-1">Tezin Seviyesi</label>
                            <select
                                name="tez_seviye"
                                className="input"
                                value={tez.tez_seviye}
                                onChange={(e) => setTez({ ...tez, tez_seviye: e.target.value })}
                                required
                            >
                                <option value="">Seçiniz</option>
                                <option value="Yüksek Lisans">Yüksek Lisans</option>
                                <option value="Doktora">Doktora</option>
                            </select>
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Öğrenci Adı</label>
                            <input
                                name="tez_ogrenci"
                                type="text"
                                className="input"
                                value={tez.tez_ogrenci}
                                onChange={(e) => setTez({ ...tez, tez_ogrenci: e.target.value })}
                                placeholder="Danışmanlık yapılan öğrenci"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Tez Konusu</label>
                            <textarea
                                name="tez_konusu"
                                rows={3}
                                className="input"
                                value={tez.tez_konusu}
                                onChange={(e) => setTez({ ...tez, tez_konusu: e.target.value })}
                                placeholder="Tez konusu detaylarını giriniz"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Tamamlanma Durumu</label>
                            <select
                                name="tez_durum"
                                className="input"
                                value={tez.tez_durum}
                                onChange={(e) => setTez({ ...tez, tez_durum: e.target.value })}
                                required
                            >
                                <option value="">Seçiniz</option>
                                <option value="Tamamlandı">Tamamlandı</option>
                                <option value="Devam Ediyor">Devam Ediyor</option>
                            </select>
                        </div>

                        {/* Tez Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    tezler: [...(prev.tezler || []), tez]
                                }));

                                setTez({
                                    tez_seviye: "",
                                    tez_ogrenci: "",
                                    tez_konusu: "",
                                    tez_durum: ""
                                });
                            }}
                        >
                            ➕ Tez Ekle
                        </button>

                        {/* Listele */}
                        {formData.tezler?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Tezler</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.tezler.map((t, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>📝 {t.tez_ogrenci} - {t.tez_seviye} ({t.tez_durum})</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        tezler: prev.tezler.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}
                {currentStep === 9 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Bilimsel Toplantılar / Bildiriler</h3>

                        <div>
                            <label className="block font-medium mb-1">Etkinlik Adı</label>
                            <input
                                name="etkinlik_adi"
                                type="text"
                                className="input"
                                value={bildiri.etkinlik_adi}
                                onChange={(e) => setBildiri({ ...bildiri, etkinlik_adi: e.target.value })}
                                placeholder="Örn: X. Ulusal Bilim Kongresi"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Tarih ve Yer</label>
                            <input
                                name="etkinlik_tarih_yer"
                                type="text"
                                className="input"
                                value={bildiri.etkinlik_tarih_yer}
                                onChange={(e) => setBildiri({ ...bildiri, etkinlik_tarih_yer: e.target.value })}
                                placeholder="Örn: 20-22 Kasım 2023 - Ankara"
                                required
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Sunum Türü</label>
                            <select
                                name="etkinlik_turu"
                                className="input"
                                value={bildiri.etkinlik_turu}
                                onChange={(e) => setBildiri({ ...bildiri, etkinlik_turu: e.target.value })}
                                required
                            >
                                <option value="">Seçiniz</option>
                                <option value="Sözlü">Sözlü</option>
                                <option value="Poster">Poster</option>
                            </select>
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Katılım Belgesi (PDF/Görsel)</label>
                            <input
                                name="etkinlik_belge"
                                type="file"
                                accept=".pdf,image/*"
                                className="input"
                                onChange={(e) => setBildiri({ ...bildiri, etkinlik_belge: e.target.files[0] })}
                                required
                            />
                        </div>

                        {/* Bildiri Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    bildiriler: [...(prev.bildiriler || []), bildiri]
                                }));

                                setBildiri({
                                    etkinlik_adi: "",
                                    etkinlik_tarih_yer: "",
                                    etkinlik_turu: "",
                                    etkinlik_belge: null
                                });
                            }}
                        >
                            ➕ Bildiri Ekle
                        </button>

                        {/* Listele */}
                        {formData.bildiriler?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Bildiriler</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.bildiriler.map((b, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>🎤 {b.etkinlik_adi} - {b.etkinlik_turu}</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        bildiriler: prev.bildiriler.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}
                {currentStep === 10 && (
                    <div className="space-y-4">
                        <h3 className="text-lg font-medium text-blue-700 mb-2">Ödüller (Varsa)</h3>

                        <div>
                            <label className="block font-medium mb-1">Ödül Adı</label>
                            <input
                                name="odul_adi"
                                type="text"
                                className="input"
                                value={odul.odul_adi}
                                onChange={(e) => setOdul({ ...odul, odul_adi: e.target.value })}
                                placeholder="Örn: En İyi Bildiri Ödülü"
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Veren Kuruluş</label>
                            <input
                                name="odul_kurulus"
                                type="text"
                                className="input"
                                value={odul.odul_kurulus}
                                onChange={(e) => setOdul({ ...odul, odul_kurulus: e.target.value })}
                                placeholder="Örn: TÜBİTAK, Üniversite Adı"
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Yıl</label>
                            <input
                                name="odul_yil"
                                type="number"
                                className="input"
                                value={odul.odul_yil}
                                onChange={(e) => setOdul({ ...odul, odul_yil: e.target.value })}
                                placeholder="YYYY"
                            />
                        </div>

                        <div>
                            <label className="block font-medium mb-1">Belge Yüklemesi (PDF / Görsel)</label>
                            <input
                                name="odul_belge"
                                type="file"
                                accept=".pdf,image/*"
                                className="input"
                                onChange={(e) => setOdul({ ...odul, odul_belge: e.target.files[0] })}
                            />
                        </div>

                        {/* Ödül Ekle Butonu */}
                        <button
                            type="button"
                            className="btn-secondary mt-4"
                            onClick={() => {
                                setFormData((prev) => ({
                                    ...prev,
                                    oduller: [...(prev.oduller || []), odul]
                                }));

                                setOdul({
                                    odul_adi: "",
                                    odul_kurulus: "",
                                    odul_yil: "",
                                    odul_belge: null
                                });
                            }}
                        >
                            ➕ Ödül Ekle
                        </button>

                        {/* Listele */}
                        {formData.oduller?.length > 0 && (
                            <div className="mt-4">
                                <h4 className="font-semibold mb-2">Eklenen Ödüller</h4>
                                <ul className="text-sm text-gray-700 space-y-1">
                                    {formData.oduller.map((o, idx) => (
                                        <li key={idx} className="flex items-center">
                                            <span>🏆 {o.odul_adi} - {o.odul_kurulus} ({o.odul_yil})</span>
                                            <button
                                                type="button"
                                                className="ml-2 text-red-500 hover:text-red-700"
                                                onClick={() => {
                                                    setFormData(prev => ({
                                                        ...prev,
                                                        oduller: prev.oduller.filter((_, i) => i !== idx)
                                                    }));
                                                }}
                                            >
                                                ❌
                                            </button>
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )}



                {/* Gezinme Butonları */}
                <div className="flex justify-between mt-6">
                    <button
                        onClick={handleBack}
                        disabled={currentStep === 0}
                        className="btn-secondary"
                    >
                        Geri
                    </button>

                    {isLastStep ? (
                        <button
                            onClick={handleFinish}
                            className="btn-primary"
                        >
                            Bitir ve Gönder
                        </button>
                    ) : (
                        <button
                            onClick={handleNext}
                            className="btn-primary"
                        >
                            İleri
                        </button>
                    )}

                </div>
            </main>
        </div>
    );
};

export default IlanForm;
