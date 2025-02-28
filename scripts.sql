INSERT INTO faculty_group (faculty_group_name) 
VALUES 
    ('Sağlık Bilimleri, Fen
Bilimleri ve
Matematik,
Mühendislik, Ziraat,
Orman ve Su Ürünleri
Temel Alanı'),
    ('Eğitim Bilimleri,
Filoloji, Mimarlık,
Planlama ve Tasarım,
Sosyal, Beşeri ve İdari
Bilimler ile Spor
Bilimleri
Temel Alanı'),
    ('Hukuk, İlahiyat
Temel Alanı'),
    ('Güzel Sanatlar
(Konservatuvar dahil)
Temel Alanı');





INSERT INTO faculty (faculty_name, facultygroup_id)
VALUES
    ('Sağlık Bilimleri Fakültesi', 1),
    ('Fen Bilimleri Fakültesi', 1),
    ('Mühendislik Fakültesi', 1),
    ('Ziraat Fakültesi', 1),
    ('Tıp Fakültesi', 1),
    ('Havacılık ve Uzay Bilimleri Fakültesi', 1),
    ('Diş Hekimliği Fakültesi', 1),
    ('Denizcilik Fakültesi', 1);










INSERT INTO faculty (faculty_name, facultygroup_id)
VALUES
    ('Eğitim Fakültesi', 2),
    ('Edebiyat Fakültesi', 2),
    ('Mimarlık Fakültesi', 2),
    ('İktisadi ve İdari Bilimler Fakültesi', 2),
    ('Spor Bilimleri Fakültesi', 2),
    ('İletişim Fakültesi', 2),
    ('Turizm Fakültesi', 2);













INSERT INTO faculty (faculty_name, facultygroup_id)
VALUES
    ('Hukuk Fakültesi', 3),
    ('İlahiyat Fakültesi', 3);








INSERT INTO faculty (faculty_name, facultygroup_id)
VALUES
    ('Güzel Sanatlar Fakültesi', 4),
    ('Konservatuvar', 4);




INSERT INTO department (department_name, faculty_id)
VALUES
    ('Bilgisayar Mühendisliği', 3),
    ('Makine Mühendisliği', 3),
    ('Endüstri Mühendisliği', 3),
    ('İnşaat Mühendisliği', 3),
    ('Kimya Mühendisliği', 3),
    ('Çevre Mühendisliği', 3),
    ('Metalurji ve Malzeme Mühendisliği', 3),
    ('Jeoloji Mühendisliği', 3),
    ('Elektrik-Haberleşme Mühendisliği', 3),
    ('Hemşirelik', 1),
    ('Sosyal Hizmet', 1),
    ('Ebelik', 1),
    ('Fizik', 2),
    ('Kimya', 2),
    ('Biyoloji', 2),
    ('Yapay Zeka ve Makine Öğrenmesi', 2),
    ('Matematik', 2),
    ('Bitki Koruma Bölümü', 4),
    ('Bahçe Bitkileri Bölümü', 4),
    ('Tarım Ekonomisi Bölümü', 4),
    ('Tıp', 5),
    ('Havacılık ve Uzay Mühendisliği Bölümü', 6),
    ('Havacılık Yönetimi Bölümü', 6),
    ('Uçak Gövde ve Motor Bakımı Bölümü', 6),
    ('Havacılık Elektrik ve Elektroniği Bölümü', 6),
    ('Diş Hekimliği', 7),
    ('Denizcilik işletmeleri Yönetimi', 8),
    ('Gemi Makineleri İşletme Mühendisliği', 8),
    ('Deniz Ulaştırma İşletme Mühendisliği ', 8);
    

