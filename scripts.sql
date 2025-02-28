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
