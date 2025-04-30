import React, { useState } from "react";

const JuriDashboard = () => {
    const [degerlendirmeMetni, setDegerlendirmeMetni] = useState("");
    const [raporDosyasi, setRaporDosyasi] = useState(null);
    const [karar, setKarar] = useState("");
    const [showApplicationsModal, setShowApplicationsModal] = useState(false);
    const [showEvaluationModal, setShowEvaluationModal] = useState(false);
    const [showApplicationDetailsModal, setShowApplicationDetailsModal] = useState(false);
    const [selectedJob, setSelectedJob] = useState(null);
    const [selectedApplication, setSelectedApplication] = useState(null);

    // Dummy data for job postings assigned to the jury member
    const assignedJobs = [
        {
            id: 1,
            title: "Bilgisayar Mühendisliği Öğretim Üyesi",
            department: "Bilgisayar Mühendisliği",
            date: "2024-05-01",
            prerequisiteStatus: "Geçti",
            applications: [
                {
                    id: 1,
                    fullName: "Ali Kaya",
                    applicationDate: "2024-05-15",
                    status: "Değerlendiriliyor",
                    details: {
                        egitim: "Doktora",
                        deneyim: "5 yıl",
                        yayinlar: "10 makale"
                    }
                },
                {
                    id: 2,
                    fullName: "Ayşe Demir",
                    applicationDate: "2024-05-16",
                    status: "Değerlendiriliyor",
                    details: {
                        egitim: "Doktora",
                        deneyim: "3 yıl",
                        yayinlar: "8 makale"
                    }
                }
            ]
        },
        {
            id: 2,
            title: "Elektrik-Elektronik Mühendisliği Öğretim Üyesi",
            department: "Elektrik-Elektronik Mühendisliği",
            date: "2024-05-01",
            prerequisiteStatus: "Geçti",
            applications: [
                {
                    id: 3,
                    fullName: "Mehmet Yılmaz",
                    applicationDate: "2024-05-17",
                    status: "Değerlendiriliyor",
                    details: {
                        egitim: "Doktora",
                        deneyim: "4 yıl",
                        yayinlar: "12 makale"
                    }
                }
            ]
        }
    ];

    const handleDosyaYukle = (e) => {
        setRaporDosyasi(e.target.files[0]);
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!degerlendirmeMetni || !raporDosyasi || !karar) {
            alert("Tüm alanları doldurmanız gerekmektedir.");
            return;
        }

        // Değerlendirme gönderimi
        console.log("Değerlendirme gönderildi:", {
            metin: degerlendirmeMetni,
            rapor: raporDosyasi.name,
            karar,
        });

        alert("Değerlendirmeniz başarıyla gönderildi.");
        setShowEvaluationModal(false);
    };

    const handleViewApplications = (job) => {
        setSelectedJob(job);
        setShowApplicationsModal(true);
    };

    const handleEvaluate = (job) => {
        setSelectedJob(job);
        setShowEvaluationModal(true);
    };

    const handleViewApplicationDetails = (application) => {
        setSelectedApplication(application);
        setShowApplicationDetailsModal(true);
    };

    return (
        <div className="min-h-screen p-8 bg-gray-50">
            <h1 className="text-3xl font-bold text-purple-700 mb-6">Jüri Paneli</h1>

            {/* Job Postings Table */}
            <div className="bg-white shadow rounded p-6 space-y-6">
                <h2 className="text-xl font-semibold mb-4">Atanan İlanlar</h2>
                <div className="overflow-x-auto">
                    <table className="min-w-full bg-white rounded shadow">
                        <thead className="bg-purple-100">
                            <tr>
                                <th className="text-left p-3">İlan Adı</th>
                                <th className="text-left p-3">Departman</th>
                                <th className="text-left p-3">Tarih</th>
                                <th className="text-left p-3">Ön Koşul Durumu</th>
                                <th className="text-left p-3">İşlemler</th>
                            </tr>
                        </thead>
                        <tbody>
                            {assignedJobs.map((job) => (
                                <tr key={job.id} className="border-t hover:bg-purple-50">
                                    <td className="p-3">{job.title}</td>
                                    <td className="p-3">{job.department}</td>
                                    <td className="p-3">{job.date}</td>
                                    <td className="p-3">
                                        <span className={`px-2 py-1 rounded text-sm ${job.prerequisiteStatus === "Geçti"
                                            ? "bg-green-100 text-green-800"
                                            : "bg-red-100 text-red-800"
                                            }`}>
                                            {job.prerequisiteStatus}
                                        </span>
                                    </td>
                                    <td className="p-3 space-x-2">
                                        <button
                                            onClick={() => handleViewApplications(job)}
                                            className="text-blue-600 hover:text-blue-800 underline"
                                        >
                                            Başvurular
                                        </button>
                                        <button
                                            onClick={() => handleEvaluate(job)}
                                            className="text-green-600 hover:text-green-800 underline"
                                        >
                                            Değerlendir
                                        </button>
                                    </td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>

            {/* Applications Modal */}
            {showApplicationsModal && selectedJob && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-4xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Başvurular - {selectedJob.title}</h2>
                        <table className="min-w-full bg-white border border-gray-200 rounded">
                            <thead className="bg-gray-100">
                                <tr>
                                    <th className="text-left p-2">Ad Soyad</th>
                                    <th className="text-left p-2">Başvuru Tarihi</th>
                                    <th className="text-left p-2">Durum</th>
                                    <th className="text-left p-2">İşlemler</th>
                                </tr>
                            </thead>
                            <tbody>
                                {selectedJob.applications.map((application) => (
                                    <tr key={application.id} className="border-t hover:bg-gray-50">
                                        <td className="p-2">{application.fullName}</td>
                                        <td className="p-2">{application.applicationDate}</td>
                                        <td className="p-2">
                                            <span className={`px-2 py-1 rounded text-sm ${application.status === "Değerlendiriliyor"
                                                ? "bg-yellow-100 text-yellow-800"
                                                : application.status === "Kabul Edildi"
                                                    ? "bg-green-100 text-green-800"
                                                    : "bg-red-100 text-red-800"
                                                }`}>
                                                {application.status}
                                            </span>
                                        </td>
                                        <td className="p-2">
                                            <button
                                                onClick={() => handleViewApplicationDetails(application)}
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
                                onClick={() => setShowApplicationsModal(false)}
                                className="btn-secondary"
                            >
                                Kapat
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Application Details Modal */}
            {showApplicationDetailsModal && selectedApplication && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full max-h-[90vh] overflow-y-auto">
                        <h2 className="text-xl font-semibold mb-4">Başvuru Detayları</h2>
                        <table className="min-w-full bg-white border border-gray-200 rounded">
                            <tbody>
                                {Object.entries(selectedApplication.details).map(([key, value]) => (
                                    <tr key={key} className="border-t">
                                        <td className="p-2 font-medium">{key}</td>
                                        <td className="p-2">{value}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        <div className="mt-4 flex justify-end">
                            <button
                                onClick={() => setShowApplicationDetailsModal(false)}
                                className="btn-secondary"
                            >
                                Kapat
                            </button>
                        </div>
                    </div>
                </div>
            )}

            {/* Evaluation Modal */}
            {showEvaluationModal && selectedJob && (
                <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4">
                    <div className="bg-white rounded-lg p-6 max-w-2xl w-full relative">
                        <button
                            onClick={() => setShowEvaluationModal(false)}
                            className="absolute top-4 right-4 text-gray-500 hover:text-gray-700"
                            aria-label="Kapat"
                        >
                            <svg xmlns="http://www.w3.org/2000/svg" className="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </button>
                        <h2 className="text-xl font-semibold mb-4">Değerlendirme - {selectedJob.title}</h2>
                        <form onSubmit={handleSubmit} className="space-y-4">
                            <div>
                                <label className="font-medium block mb-1">Değerlendirme Notunuz:</label>
                                <textarea
                                    value={degerlendirmeMetni}
                                    onChange={(e) => setDegerlendirmeMetni(e.target.value)}
                                    rows={4}
                                    className="input w-full"
                                    required
                                />
                            </div>

                            <div className="flex justify-end space-x-4">
                                <button
                                    type="button"
                                    onClick={() => {
                                        setKarar("Olumlu");
                                        handleSubmit(new Event('submit'));
                                    }}
                                    className="btn-primary bg-green-600 hover:bg-green-700"
                                >
                                    Olumlu Değerlendirme
                                </button>
                                <button
                                    type="button"
                                    onClick={() => {
                                        setKarar("Olumsuz");
                                        handleSubmit(new Event('submit'));
                                    }}
                                    className="btn-primary bg-red-600 hover:bg-red-700"
                                >
                                    Olumsuz Değerlendirme
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default JuriDashboard;
