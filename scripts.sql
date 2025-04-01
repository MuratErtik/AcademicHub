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
    
INSERT INTO category (category_id, category_name,category_description) VALUES 
('K', 'İdari Görevler ve Üniversiteye Katkı Faaliyetleri','İdari görevlerde vekaleten de olsa en az 6 ay görev yapmış olmak şartı aranır ve normal süresi dolup yeniden
atamalar ayrıca puanlanır. Bu kısımda en fazla 50 puan dikkate alınır.');
INSERT INTO category (category_id, category_name,category_description) VALUES 
('J', 'Ödüller','Temel alanı ile ilgili olmak üzere');
INSERT INTO category (category_id, category_name) VALUES 
('I', 'Editörlük, Yayın Kurulu Üyeliği ve Hakemlik Faaliyetleri');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('H', 'Araştırma Projeleri', 'Tamamlanmış veya devam ediyor olmak koşuluyla, projenin en az dokuz ay süreli
olduğu ve hakem değerlendirilmesinden geçtiği belgelenir ve projenin bütçesi, kabul edildiği yıldaki en son açıklanan
memur taban aylık katsayısının en az 4000 katı olmalıdır.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('G', 'Patentler', 'Aynı faaliyet sadece bir maddede puanlanır.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('F', 'Tez Yöneticiliği', 'Tamamlanmış olması kaydıyla.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('E', 'Eğitim Öğretim Faaliyetleri', 'Son üç yılda verdiği aynı veya farklı dersler, Azami 50 puan, doktora
unvanından sonra.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('D', 'Atıf', 'Atıf yapan eserlerin belgelenmesi kaydıyla, bu yönetmeliğin Temel İlkeler bölümündeki atıflara ilişkin
açıklamalar dikkate alınır.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('C', 'Kitaplar', 'Yabancı dildeki kitapların puanları 1.5 ile çarpıldıktan sonra hesaplama kullanılır.');
INSERT INTO category (category_id, category_name, category_description) VALUES 
('A', 'Makale', 'Başvurulan bilim alanı ile ilgili tam araştırma ve derleme makaleleri');


<<<<<<< HEAD

INSERT INTO Article (category_id, content, point) VALUES
    ('A1', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q1 olarak taranan dergide)', 60),
    ('A2', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q2 olarak taranan dergide)', 55),
    ('A3', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q3 olarak taranan dergide)', 40),
    ('A4', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q4 olarak taranan dergide)', 30),
    ('A5', 'ESCI tarafından taranan dergilerde yayımlanmış makale', 25),
    ('A6', 'Scopus tarafından taranan dergilerde yayımlanmış makale', 20),
    ('A7', 'Uluslararası diğer indekslerde taranan dergilerde yayımlanmış makale', 15),
    ('A8', 'ULAKBİM TR Dizin tarafından taranan ulusal hakemli dergilerde yayımlanmış makale', 10),
    ('A9', '8. madde dışındaki ulusal hakemli dergilerde yayımlanmış makale', 8);


INSERT INTO ScientificMeetingActivity (category_id, content, point) VALUES
    ('B1', 'Uluslararası bilimsel toplantılarda sözlü olarak sunulan, tam metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 8),
    ('B2', 'Uluslararası bilimsel toplantılarda sözlü olarak sunulan, özet metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 7),
    ('B3', 'Uluslararası bilimsel toplantılarda poster olarak sunulan çalışmalar', 6),
    ('B4', 'Ulusal bilimsel toplantılarda sözlü olarak sunulan tam metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 7),
    ('B5', 'Ulusal bilimsel toplantılarda sözlü olarak sunulan, özet metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 6),
    ('B6', 'Ulusal bilimsel toplantılarda poster olarak sunulan çalışmalar', 5),
    ('B7', 'Uluslararası bir kongre, konferans veya sempozyumda organizasyon veya yürütme komitesinde düzenleme kurulu üyeliği veya bilim kurulu üyeliği yapmak', 7),
    ('B8', 'Ulusal bir kongre, konferans veya sempozyumda organizasyon veya yürütme komitesinde düzenleme kurulu üyeliği veya bilim kurulu üyeliği yapmak', 5),
    ('B9', 'Uluslararası konferanslarda, bilimsel toplantı, seminerlerde davetli konuşmacı olarak yer almak', 8),
    ('B10', 'Ulusal konferanslarda, bilimsel toplantı, seminerlerde davetli konuşmacı olarak yer almak', 6),
    ('B11', 'Uluslararası veya ulusal çeşitli kurumlarla işbirliği içinde atölye, çalıştay, yaz okulu organize ederek gerçekleştirmek', 6),
    ('B12', 'Uluslararası veya ulusal çeşitli kurumlarla işbirliği içinde atölye, çalıştay, panel, seminer, yaz okulunda konuşmacı veya panelist olarak görev almak', 5);


INSERT INTO Book (category_id, content, foreignLanguage, point) VALUES
    ('C1', 'Uluslararası yayınevleri tarafından yayımlanmış özgün kitap', 1.5, 60),
    ('C2', 'Uluslararası yayınevleri tarafından yayımlanmış özgün kitap editörlüğü, bölüm yazarlığı (Her bir kitap için maksimum 2 bölüm yazarlığı)', 1.5, 20),
    ('C3', 'Uluslararası yayımlanan ansiklopedi konusu/maddesi (en fazla 3 madde)', 1.5, 5),
    ('C4', 'Ulusal yayınevleri tarafından yayımlanmış özgün kitap', 1.5, 40),
    ('C5', 'Ulusal yayınevleri tarafından yayımlanmış özgün kitap editörlüğü, bölüm yazarlığı (Her bir kitap için maksimum 2 bölüm yazarlığı)', 1.5, 10),
    ('C6', 'Tam kitap çevirisi (Yayınevleri için ilgili ÜAK kriterleri geçerlidir)', 1.5, 15),
    ('C7', 'Çeviri kitap editörlüğü, kitap bölümü çevirisi (Yayınevleri için ilgili ÜAK kriterleri geçerlidir) (Her bir kitap için maksimum 2 bölüm çevirisi)', 1.5, 6),
    ('C8', 'Alanında ulusal yayımlanan ansiklopedi konusu/maddesi (en fazla 3 madde)', 1.5, 3);


INSERT INTO Citation (category_id, content, point) VALUES
    ('D1', 'SCI-E, SSCI ve AHCI tarafından taranan dergilerde; Uluslararası yayınevleri tarafından yayımlanmış kitaplarda yayımlanan ve adayın yazar olarak yer almadığı yayınlardan her birinde, metin içindeki atıf sayısına bakılmaksızın adayın atıf yapılan her eseri için', 4),
    ('D2', 'E-SCI tarafından taranan dergilerde ve adayın yazar olarak yer almadığı yayınlardan her birinde, metin içindeki atıf sayısına bakılmaksızın adayın atıf yapılan her eseri için', 3),
    ('D3', 'SCI-E, SSCI, AHCI, E-SCI dışındaki diğer uluslararası indeksler tarafından taranan dergilerde; Uluslararası yayınevleri tarafından yayımlanmış kitaplarda bölüm yazarı olarak yayımlanan ve adayın yazar olarak yer almadığı yayınlardan her birinde, metin içindeki atıf sayısına bakılmaksızın adayın atıf yapılan her eseri için', 4),
    ('D4', 'Ulusal hakemli dergilerde; Ulusal yayınevleri tarafından yayımlanmış kitaplarda yayımlanan ve adayın yazar olarak yer almadığı yayınlardan her birinde, metin içindeki atıf sayısına bakılmaksızın adayın atıf yapılan her eseri için', 1),
    ('D5', 'Güzel sanatlardaki eserlerin uluslararası kaynak veya yayın organlarında yer alması veya gösterime ya da dinletime girmesi', 3),
    ('D6', 'Güzel sanatlardaki eserlerin ulusal kaynak veya yayın organlarında yer alması veya gösterime ya da dinletime girmesi', 1);


INSERT INTO EducationAction (category_id, content, point) VALUES
    ('E1', 'Önlisans/lisans dersleri', 2),
    ('E2', 'Önlisans/lisans dersleri (Yabancı dilde)', 3),
    ('E3', 'Lisansüstü dersleri', 3),
    ('E4', 'Lisansüstü dersleri (Yabancı dilde)', 4);


INSERT INTO ThesisSupervision (category_id, content, point) VALUES
    ('F1', 'Doktora/Sanatta Yeterlik veya Tıp/Diş Hekimliğinde Uzmanlık Tez Yönetimi', 40),
    ('F2', 'Yüksek Lisans Tez Yönetimi', 15),
    ('F3', 'Doktora/Sanatta Yeterlik (Eş Danışman)', 9),
    ('F4', 'Yüksek Lisans/Sanatta Yeterlik Tez Yönetimi (Eş Danışman)', 4);


INSERT INTO Patent (category_id, content, point) VALUES
    ('G1', 'Lisanslanan Uluslararası Patent', 120),
    ('G2', 'Tescillenmiş Uluslararası Patent', 100),
    ('G3', 'Uluslararası Patent Başvurusu', 50),
    ('G4', 'Lisanslanan Ulusal Patent', 80),
    ('G5', 'Tescillenmiş Ulusal Patent', 60),
    ('G6', 'Ulusal Patent Başvurusu', 30),
    ('G7', 'Lisanslanan Faydalı Model, Endüstriyel Tasarım, Marka', 20),
    ('G8', 'Faydalı Model ve Endüstriyel Tasarım', 15);


INSERT INTO ResearchProjects (category_id, content, point) VALUES
    ('H1', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde koordinatör/alt koordinatör olmak', 250),
    ('H2', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde yürütücü olmak', 150),
    ('H3', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde araştırmacı olmak', 100),
    ('H4', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde (derleme ve rapor hazırlama çalışmaları hariç) koordinatör/alt koordinatör olmak', 150),
    ('H5', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde (derleme ve rapor hazırlama çalışmaları hariç) yürütücü olmak', 120),
    ('H6', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde (derleme ve rapor hazırlama çalışmaları hariç) araştırmacı olmak', 70),
    ('H7', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde (derleme ve rapor hazırlama çalışmaları hariç) danışman olmak', 30),
    ('H8', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde yürütücü olmak', 100),
    ('H9', 'Diğer TÜBİTAK veya Kalkınma Ajansları projelerinde yürütücü olmak', 50),
    ('H10', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde yürütücü olmak', 40),
    ('H11', 'Sanayi kuruluşları ile yapılan Ar-Ge projelerinde yürütücü olmak', 40),
    ('H12', 'Diğer özel kuruluşlar ile yapılan Ar-Ge projelerinde yürütücü olmak', 20),
    ('H13', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde araştırmacı olmak', 50),
    ('H14', 'Diğer TÜBİTAK veya Kalkınma Ajansları projelerinde araştırmacı olmak', 25),
    ('H15', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde araştırmacı olmak', 20),
    ('H16', 'Sanayi kuruluşları ile yapılan bilimsel araştırma projelerinde araştırmacı olmak', 20),
    ('H17', 'Diğer özel kuruluşlar ile yapılan bilimsel araştırma projelerinde araştırmacı olmak', 10),
    ('H18', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde danışman olmak', 25),
    ('H19', 'Diğer TÜBİTAK projelerinde danışman olmak', 12),
    ('H20', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde danışman olmak', 10),
    ('H21', 'Sanayi kuruluşları ile yapılan Ar-Ge projelerinde danışman olmak', 10),
    ('H22', 'Diğer özel kuruluşlar ile yapılan Ar-Ge projelerinde danışman olmak', 10),
    ('H23', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde (derleme ve rapor hazırlama çalışmaları hariç) yürütücü olmak (Hızlı destek, Altyapı, Lab. ve lisansüstü tez projeleri hariç)', 8),
    ('H24', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde araştırmacı olmak (Hızlı destek, Altyapı, Lab. ve lisansüstü tez projeleri hariç)', 6),
    ('H25', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde danışman olmak (Hızlı destek, Altyapı, Lab. ve lisansüstü tez projeleri hariç)', 3),
    ('H26', 'En az dört aylık yurtdışı araştırma çalışmasında bulunmak', 100),
    ('H27', 'En az dört aylık yurtiçi araştırma çalışmasında bulunmak (kurum dışında)', 50),
    ('H28', 'TÜBİTAK 2209-A, 2209-B, 2242 projelerinde danışman olmak (En fazla 100 puan alınabilir. Kabul edildiği yıldaki en son açıklanan memur taban aylık katsayısı koşulu bu madde için geçerli değildir)', 10);


INSERT INTO Editorship (category_id, content, point) VALUES
    ('I1', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde baş editörlük görevinde bulunmak', 100),
    ('I2', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 70),
    ('I3', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde misafir/davetli editörlük görevinde bulunmak', 50),
    ('I4', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde yayın kurulu üyeliği', 40),
    ('I5', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde baş editörlük görevinde bulunmak', 40),
    ('I6', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 30),
    ('I7', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde misafir/davetli editörlük görevinde bulunmak', 20),
    ('I8', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde yayın kurulu üyeliği', 10),
    ('I9', 'ULAKBİM tarafından taranan dergilerde baş editörlük görevi', 15),
    ('I10', 'ULAKBİM tarafından taranan dergilerde yayın kurulu üyeliği veya alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 5),
    ('I11', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde tamamlanmış hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 3),
    ('I12', 'SCI-E, SSCI veya AHCI kapsamı dışındaki uluslararası diğer indeksler tarafından dergilerde tamamlanmış hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 2),
    ('I13', 'ULAKBİM tarafından taranan dergilerde hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 1);


INSERT INTO Award (category_id, content, point) VALUES
    ('J1', 'Sürekli ve periyodik olarak jürili uluslararası kurum veya kuruluşlar tarafından verilen bilim ve sanat ödülleri', 150),
    ('J2', 'TÜBİTAK tarafından verilen Bilim, Özel ve Hizmet Ödülleri', 100),
    ('J3', 'TÜBA tarafından verilen Akademi Ödülleri', 100),
    ('J4', 'TÜBİTAK tarafından verilen Teşvik Ödülü (Yayın teşvik ödülü hariç)', 80),
    ('J5', 'TÜBA tarafından verilen GEBİP ve TESEP ödülleri', 80),
    ('J6', 'Sürekli ve periyodik olarak jürili ulusal kurum veya kuruluşlar tarafından verilen bilim ve sanat ödülleri', 50),
    ('J7', 'Sürekli ve periyodik olarak verilen ve bir jüri değerlendirmesine tabi olmayan uluslararası/ulusal ödüller', 20),
    ('J8', 'Uluslararası hakemli yarışmalarda birincilik derecesi', 20),
    ('J9', 'Uluslararası hakemli yarışmalarda ikincilik derecesi', 10),
    ('J10', 'Uluslararası hakemli yarışmalarda üçüncülük derecesi', 5),
    ('J11', 'Ulusal hakemli yarışmalarda birincilik derecesi', 10),
    ('J12', 'Ulusal hakemli yarışmalarda ikincilik derecesi', 5),
    ('J13', 'Ulusal hakemli yarışmalarda üçüncülük derecesi', 3),
    ('J14', 'Uluslararası bilimsel toplantılarda alınan ödüller', 5),
    ('J15', 'Ulusal bilimsel toplantılarda alınan ödüller', 3),
    ('J16', 'Sanat, tasarım ve mimarlık alanlarında Uluslararası hakemli/jürili yarışmalarda alınan ödüller', 20),
    ('J17', 'Sanat, tasarım ve mimarlık alanlarında Ulusal hakemli/jürili yarışmalarda alınan ödüller', 10),
    ('J18', 'Üniversite kurumsal ödülleri (üniversite genelinde ilgili alanda makale, patent, proje, v.b. dereceye girenler)', 10),
    ('J19', 'Kitap veya makale gibi bilimsel eserlere atfedilen ödüller', 5);


INSERT INTO ContributionActivities (category_id, content, point) VALUES
    ('K1', 'Dekan/Enstitü/Yüksekokul/MYO/Merkez Müdürü', 15),
    ('K2', 'Enstitü Müdür Yrd. / Dekan Yrd. / Yüksekokul Müdür Yrd. / MYO Müdür Yrd. / Merkez Müdürü Yrd./Bölüm Başkanı', 12),
    ('K3', 'Bölüm Başkan Yrd. / Anabilim Dalı Başkanı', 10),
    ('K4', 'Rektörlükçe görevlendirilen Koordinatörlük', 8),
    ('K5', 'Rektörlükçe görevlendirilen Koordinatör Yardımcıları', 7),
    ('K6', 'Rektörlükçe görevlendirilen üniversite düzeyinde Komisyon/Kurul üyelikleri', 6),
    ('K7', 'Dekanlık/Y.O. Müdürlüğü/MYO Müdürlüğü /Konservatuvar Müdürlüğü tarafından görevlendirilen Komisyon/Kurul üyelikleri', 5),
    ('K8', 'Bölüm Başkanlıkları tarafından görevlendirilen Komisyon/Kurul üyelikleri', 4),
    ('K9', 'Rektörlük/Dekanlık/Y.O. Müdürlüğü/MYO Müdürlüğü /Konservatuvar Müdürlüğü/ Bölüm Başkanlığı görevlendirmeleriyle kurum içi ve dışı eğitim, işbirliği vb konularda katkı sağlamak', 3),
    ('K10', 'Uluslararası nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak', 5),
    ('K11', 'Ulusal nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak', 4),
    ('K12', 'Yerel nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak', 3);
=======
INSERT INTO article ( category_id,content, point) VALUES
    ('A', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q1 olarak taranan dergide)', 60),
    ('A', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q2 olarak taranan dergide)', 55),
    ('A', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q3 olarak taranan dergide)', 40),
    ('A', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde yayımlanmış makale (Q4 olarak taranan dergide)', 30),
    ('A', 'ESCI tarafından taranan dergilerde yayımlanmış makale', 25),
    ('A', 'Scopus tarafından taranan dergilerde yayımlanmış makale', 20),
    ('A', 'Uluslararası diğer indekslerde taranan dergilerde yayımlanmış makale', 15),
    ('A', 'ULAKBİM TR Dizin tarafından taranan ulusal hakemli dergilerde yayımlanmış makale', 10),
    ('A', '8. madde dışındaki ulusal hakemli dergilerde yayımlanmış makale', 8);


INSERT INTO scientific_meeting_activity (category_id, content, point) VALUES
    ('B', 'Uluslararası bilimsel toplantılarda sözlü olarak sunulan, tam metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 8),
    ('B', 'Uluslararası bilimsel toplantılarda sözlü olarak sunulan, özet metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 7),
    ('B', 'Uluslararası bilimsel toplantılarda poster olarak sunulan çalışmalar', 6),
    ('B', 'Ulusal bilimsel toplantılarda sözlü olarak sunulan tam metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 7),
    ('B', 'Ulusal bilimsel toplantılarda sözlü olarak sunulan, özet metni matbu veya elektronik olarak bildiri kitapçığında yayımlanmış çalışmalar', 6),
    ('B', 'Ulusal bilimsel toplantılarda poster olarak sunulan çalışmalar', 5),
    ('B', 'Uluslararası bir kongre, konferans veya sempozyumda organizasyon veya yürütme komitesinde düzenleme kurulu üyeliği veya bilim kurulu üyeliği yapmak', 7),
    ('B', 'Ulusal bir kongre, konferans veya sempozyumda organizasyon veya yürütme komitesinde düzenleme kurulu üyeliği veya bilim kurulu üyeliği yapmak', 5),
    ('B', 'Uluslararası konferanslarda, bilimsel toplantı, seminerlerde davetli konuşmacı olarak yer almak', 8),
    ('B', 'Ulusal konferanslarda, bilimsel toplantı, seminerlerde davetli konuşmacı olarak yer almak', 6),
    ('B', 'Uluslararası veya ulusal çeşitli kurumlarla işbirliği içinde atölye, çalıştay, yaz okulu organize ederek gerçekleştirmek', 6),
    ('B', 'Uluslararası veya ulusal çeşitli kurumlarla işbirliği içinde atölye, çalıştay, panel, seminer, yaz okulunda konuşmacı veya panelist olarak görev almak', 5);


INSERT INTO Book (category_id, content, foreign_language, point) VALUES
    ('C', 'Uluslararası yayınevleri tarafından yayımlanmış özgün kitap', 1.5, 60),
    ('C', 'Uluslararası yayınevleri tarafından yayımlanmış özgün kitap editörlüğü, bölüm yazarlığı (Her bir kitap için maksimum 2 bölüm yazarlığı)', 1.5, 20),
    ('C', 'Uluslararası yayımlanan ansiklopedi konusu/maddesi (en fazla 3 madde)', 1.5, 5),
    ('C', 'Ulusal yayınevleri tarafından yayımlanmış özgün kitap', 1.5, 40),
    ('C', 'Ulusal yayınevleri tarafından yayımlanmış özgün kitap editörlüğü, bölüm yazarlığı (Her bir kitap için maksimum 2 bölüm yazarlığı)', 1.5, 10),
    ('C', 'Tam kitap çevirisi (Yayınevleri için ilgili ÜAK kriterleri geçerlidir)', 1.5, 15),
    ('C', 'Çeviri kitap editörlüğü, kitap bölümü çevirisi (Yayınevleri için ilgili ÜAK kriterleri geçerlidir) (Her bir kitap için maksimum 2 bölüm çevirisi)', 1.5, 6),
    ('C','Alanında ulusal yayımlanan ansiklopedi konusu/maddesi (en fazla 3 madde)', 1.5, 3);


INSERT INTO citation (category_id, content, point) VALUES
    ('D', 'SCI-E,SSCI,AHCI tarafından taranan dergilerde;Uluslararası yayınevlerinin yayımladığı,adayın yazar olmadığı yayınlardan birinde adayın atıf yapılan eseri için', 4),
    ('D', 'E-SCI tarafından taranan dergilerde ve adayın yazar olmadığı yayınlardan her birinde adayın atıf yapılan her eseri için', 3),
    ('D', 'SCI-E,SSCI,AHCI,E-SCI harici diğer uluslararası dergilerde;bölüm yazarı olduğu,yazar olmadığı yayınlardan atıf yapılan eseri için', 4),
    ('D', 'Ulusal yayınevleri tarafından yayımlanmış kitaplarda yayımlanan ve adayın olmadığı yayınlardan her birinde adayın atıf yapılan her eseri için', 1),
    ('D', 'Güzel sanatlardaki eserlerin uluslararası kaynak veya yayın organlarında yer alması veya gösterime ya da dinletime girmesi', 3),
    ('D', 'Güzel sanatlardaki eserlerin ulusal kaynak veya yayın organlarında yer alması veya gösterime ya da dinletime girmesi', 1);


INSERT INTO education_action (category_id, content, point) VALUES
    ('E', 'Önlisans/lisans dersleri', 2),
    ('E', 'Önlisans/lisans dersleri (Yabancı dilde)', 3),
    ('E', 'Lisansüstü dersleri', 3),
    ('E', 'Lisansüstü dersleri (Yabancı dilde)', 4);


INSERT INTO thesis_supervision (category_id, content, point) VALUES
    ('F', 'Doktora/Sanatta Yeterlik veya Tıp/Diş Hekimliğinde Uzmanlık Tez Yönetimi', 40),
    ('F', 'Yüksek Lisans Tez Yönetimi', 15),
    ('F', 'Doktora/Sanatta Yeterlik (Eş Danışman)', 9),
    ('F', 'Yüksek Lisans/Sanatta Yeterlik Tez Yönetimi (Eş Danışman)', 4);


INSERT INTO patent (category_id, content, point) VALUES
    ('G', 'Lisanslanan Uluslararası Patent', 120),
    ('G', 'Tescillenmiş Uluslararası Patent', 100),
    ('G', 'Uluslararası Patent Başvurusu', 50),
    ('G', 'Lisanslanan Ulusal Patent', 80),
    ('G', 'Tescillenmiş Ulusal Patent', 60),
    ('G', 'Ulusal Patent Başvurusu', 30),
    ('G', 'Lisanslanan Faydalı Model, Endüstriyel Tasarım, Marka', 20),
    ('G', 'Faydalı Model ve Endüstriyel Tasarım', 15);


INSERT INTO research_projects (category_id, content, point) VALUES
    ('H', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde koordinatör/alt koordinatör olmak', 250),
    ('H', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde yürütücü olmak', 150),
    ('H', 'AB çerçeve programı/NSF/ERC bilimsel araştırma projesinde araştırmacı olmak', 100),
    ('H', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde koordinatör/alt koordinatör olmak', 150),
    ('H', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde yürütücü olmak', 120),
    ('H', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde araştırmacı olmak', 70),
    ('H', 'AB Çerçeve Programı/NSF/ERC bilimsel araştırma projeleri dışındaki uluslararası destekli bilimsel araştırma projelerinde danışman olmak', 30),
    ('H', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde yürütücü olmak', 100),
    ('H', 'Diğer TÜBİTAK veya Kalkınma Ajansları projelerinde yürütücü olmak', 50),
    ('H', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde yürütücü olmak', 40),
    ('H', 'Sanayi kuruluşları ile yapılan Ar-Ge projelerinde yürütücü olmak', 40),
    ('H', 'Diğer özel kuruluşlar ile yapılan Ar-Ge projelerinde yürütücü olmak', 20),
    ('H', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde araştırmacı olmak', 50),
    ('H', 'Diğer TÜBİTAK veya Kalkınma Ajansları projelerinde araştırmacı olmak', 25),
    ('H', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde araştırmacı olmak', 20),
    ('H', 'Sanayi kuruluşları ile yapılan bilimsel araştırma projelerinde araştırmacı olmak', 20),
    ('H', 'Diğer özel kuruluşlar ile yapılan bilimsel araştırma projelerinde araştırmacı olmak', 10),
    ('H', 'TÜBİTAK ARGE (ARDEB, TEYDEB) ve TÜSEB projelerinde danışman olmak', 25),
    ('H', 'Diğer TÜBİTAK projelerinde danışman olmak', 12),
    ('H', 'TÜBİTAK dışındaki diğer kamu kurumlarıyla yapılan bilimsel araştırma projelerinde danışman olmak', 10),
    ('H', 'Sanayi kuruluşları ile yapılan Ar-Ge projelerinde danışman olmak', 10),
    ('H', 'Diğer özel kuruluşlar ile yapılan Ar-Ge projelerinde danışman olmak', 10),
    ('H', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde yürütücü olmak', 8),
    ('H', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde araştırmacı olmak', 6),
    ('H', 'Üniversitelerin bilimsel araştırma projeleri (BAP) koordinatörlükleri destekli araştırma projelerinde danışman olmak', 3),
    ('H', 'En az dört aylık yurtdışı araştırma çalışmasında bulunmak', 100),
    ('H', 'En az dört aylık yurtiçi araştırma çalışmasında bulunmak (kurum dışında)', 50),
    ('H', 'TÜBİTAK 2209-A, 2209-B, 2242 projelerinde danışman olmak', 10);


INSERT INTO editorship (category_id, content, point) VALUES
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde baş editörlük görevinde bulunmak', 100),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 70),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde misafir/davetli editörlük görevinde bulunmak', 50),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamındaki dergilerde yayın kurulu üyeliği', 40),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde baş editörlük görevinde bulunmak', 40),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 30),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde misafir/davetli editörlük görevinde bulunmak', 20),
    ('I', 'SCI-E, SSCI, AHCI, E-SCI veya SCOPUS kapsamı dışındaki uluslararası diğer indeksler tarafından taranan dergilerde yayın kurulu üyeliği', 10),
    ('I', 'ULAKBİM tarafından taranan dergilerde baş editörlük görevi', 15),
    ('I', 'ULAKBİM tarafından taranan dergilerde yayın kurulu üyeliği veya alan/yardımcı/ortak/asistan editörlük görevinde bulunmak', 5),
    ('I', 'SCI-E, SSCI veya AHCI kapsamındaki dergilerde tamamlanmış hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 3),
    ('I', 'SCI-E, SSCI veya AHCI kapsamı dışındaki uluslararası diğer indeksler tarafından dergilerde tamamlanmış hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 2),
    ('I', 'ULAKBİM tarafından taranan dergilerde hakemlik faaliyeti (her bir hakemlik faaliyeti başına)', 1);



INSERT INTO award (category_id, content, point) VALUES
    ('J', 'Sürekli ve periyodik olarak jürili uluslararası kurum veya kuruluşlar tarafından verilen bilim ve sanat ödülleri', 150),
    ('J', 'TÜBİTAK tarafından verilen Bilim, Özel ve Hizmet Ödülleri', 100),
    ('J', 'TÜBA tarafından verilen Akademi Ödülleri', 100),
    ('J', 'TÜBİTAK tarafından verilen Teşvik Ödülü (Yayın teşvik ödülü hariç)', 80),
    ('J', 'TÜBA tarafından verilen GEBİP ve TESEP ödülleri', 80),
    ('J', 'Sürekli ve periyodik olarak jürili ulusal kurum veya kuruluşlar tarafından verilen bilim ve sanat ödülleri', 50),
    ('J', 'Sürekli ve periyodik olarak verilen ve bir jüri değerlendirmesine tabi olmayan uluslararası/ulusal ödüller', 20),
    ('J', 'Uluslararası hakemli yarışmalarda birincilik derecesi', 20),
    ('J', 'Uluslararası hakemli yarışmalarda ikincilik derecesi', 10),
    ('J', 'Uluslararası hakemli yarışmalarda üçüncülük derecesi', 5),
    ('J', 'Ulusal hakemli yarışmalarda birincilik derecesi', 10),
    ('J', 'Ulusal hakemli yarışmalarda ikincilik derecesi', 5),
    ('J', 'Ulusal hakemli yarışmalarda üçüncülük derecesi', 3),
    ('J', 'Uluslararası bilimsel toplantılarda alınan ödüller', 5),
    ('J', 'Ulusal bilimsel toplantılarda alınan ödüller', 3),
    ('J', 'Sanat, tasarım ve mimarlık alanlarında Uluslararası hakemli/jürili yarışmalarda alınan ödüller', 20),
    ('J', 'Sanat, tasarım ve mimarlık alanlarında Ulusal hakemli/jürili yarışmalarda alınan ödüller', 10),
    ('J', 'Üniversite kurumsal ödülleri (üniversite genelinde ilgili alanda makale, patent, proje, v.b. dereceye girenler)', 10),
    ('J', 'Kitap veya makale gibi bilimsel eserlere atfedilen ödüller', 5);


INSERT INTO contribution_activities (category_id, content,max_point, point) VALUES
    ('K', 'Dekan/Enstitü/Yüksekokul/MYO/Merkez Müdürü',50, 15),
    ('K', 'Enstitü Müdür Yrd. / Dekan Yrd. / Yüksekokul Müdür Yrd. / MYO Müdür Yrd. / Merkez Müdürü Yrd./Bölüm Başkanı',50, 12),
    ('K', 'Bölüm Başkan Yrd. / Anabilim Dalı Başkanı',50, 10),
    ('K', 'Rektörlükçe görevlendirilen Koordinatörlük',50, 8),
    ('K', 'Rektörlükçe görevlendirilen Koordinatör Yardımcıları',50, 7),
    ('K', 'Rektörlükçe görevlendirilen üniversite düzeyinde Komisyon/Kurul üyelikleri',50, 6),
    ('K', 'Dekanlık/Y.O. Müdürlüğü/MYO Müdürlüğü /Konservatuvar Müdürlüğü tarafından görevlendirilen Komisyon/Kurul üyelikleri',50, 5),
    ('K', 'Bölüm Başkanlıkları tarafından görevlendirilen Komisyon/Kurul üyelikleri',50, 4),
    ('K', 'Rektörlük/Dekanlık/Y.O. Müdürlüğü/MYO Müdürlüğü /Konservatuvar Müdürlüğü/ Bölüm Başkanlığı görevlendirmeleriyle,işbirliği vb konularda katkı sağlamak',50, 3),
    ('K', 'Uluslararası nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak',50, 5),
    ('K', 'Ulusal nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak',50, 4),
    ('K', 'Yerel nitelikteki bilimsel ve mesleki kurum/kuruluşların yönetimlerinde, kurullarında, komisyon veya komitelerinde görev almak',50, 3);



    INSERT INTO position_posting (description1, description2, description3, description4)
VALUES (
    'İlan edilen kadroya başvuracak olan aday “2547 Sayılı Kanun” hükmünce tüm bilgileri, Tablo 1 ve Tablo 2 göz önüne alınarak uygun biçimde başvuru dosyasına koymak zorundadır.',
    'Jüri üyelerine gönderilmek üzere,adayın başvuru dosyasında;YÖKSİS formatında özgeçmişi, diplomaları ve başarı göstergeleri,yayınları,atıfları,ders yükleri,proje,patent belgeleri gibi puanlamaya esas bütün belgelerin yer alması gerekmektedir.',
    'Başvuru dosyası,Tablo 3’de yer alan faaliyet alanlarına göre Doçentlik kadrosu için “Doktora Sonrası”,Profesörlük kadrosu için de “Doçentlik Ünvanı Sonrası” şeklinde düzenlenmiş olmalıdır.',
    'Farklı temel alan içeren birimlerde ABD kurul kararı ve Fakülte Yönetim Kurulu’nun talebi ve Rektörün onayı ile uygun temel alan gözetilerek atamalar yapılabilir.'
);

-- INSERT INTO important_things_to_fill_table (position, description1)
-- VALUES (
--     'ASSISTANT PROFESSOR',
--     'Tablo 5, aday tarafından başvurduğu akademik kadro belirtilerek son atama tarihinden başvuru tarihine kadar
-- olmak üzere doldurulacak ve jüri üyelerine gönderilecektir. Jüriler,adayların bilgilerini raporuna ekleyeceklerdir.'
-- );

-- INSERT INTO important_things_to_fill_table (position, description1)
-- VALUES (
--     'REPLACEMENT ASSISTANT PROFESSOR',
--     'Tablo 5, aday tarafından başvurduğu akademik kadro belirtilerek kendisinin lisansüstü tez çalışmalarından
-- üretilmemek koşuluyla doktora/tıp/diş hekimliğinde uzmanlık ya da sanatta yeterlik ünvanını aldığı tarihten
-- sonrası için titizlikle doldurulacak.Jüriler,aday tarafından beyan edilen tablodaki bilgileri raporuna ekleyeceklerdir.'
-- );
-- INSERT INTO important_things_to_fill_table (position, description1)
-- VALUES (
--     'ASSOCIATE PROFESSOR',
--     'Profesörlük başvurusu için gerekli belgeler: YÖKSİS özgeçmiş, yayın listesi, atıf belgeleri, proje raporları ve danışmanlık belgeleri.'
-- );
-- INSERT INTO important_things_to_fill_table (position, description1)
-- VALUES (
--     'PROFESSOR',
--     'Profesörlük başvurusu için gerekli belgeler: YÖKSİS özgeçmiş, yayın listesi, atıf belgeleri, proje raporları ve danışmanlık belgeleri.'
-- );

INSERT INTO table3action (action_name, facultygroup_id, position) VALUES 

('A1-A2',1,3),
('A1-A2',1,4),
('A1-A4',1,1),
('A1-A4',1,3),
('A1-A4',1,4),
('A1-A5',1,1),
('A1-A5',1,3),
('A1-A5',1,4),
('A1-A6',1,1),
('A1-A6',1,3),
('A1-A6',1,4),
('A1-A8',1,1),
('A1-A8',1,3),
('A1-A8',1,4),
('Başlıca Yazar',1,1),
('Başlıca Yazar',1,3),
('Başlıca Yazar',1,4),
('Toplam Makale',1,1),
('Toplam Makale',1,3),
('Toplam Makale',1,4),
('Kişisel ve Karma Etkinlik',1,1),
('Kişisel ve Karma Etkinlik',1,3),
('Kişisel ve Karma Etkinlik',1,4),
('F1',1,1),
('F1',1,3),
('F1',1,4),
('F2',1,1),
('F2',1,3),
('F2',1,4),
('H1-H12',1,1),
('H1-H12',1,3),
('H1-H12',1,4),
('H13-H17',1,1),
('H13-H17',1,3),
('H13-H17',1,4),
('H13-H22',1,1),
('H13-H22',1,3),
('H13-H22',1,4),
('D1-D6',1,1),
('D1-D6',1,3),
('D1-D6',1,4),
('E1-E4',1,1),
('E1-E4',1,3),
('E1-E4',1,4),
('F1-F2',1,1),
('F1-F2',1,3),
('F1-F2',1,4),
('H1-H17',1,1),
('H1-H17',1,3),
('H1-H17',1,4),
('H1-H22',1,1),
('H1-H22',1,3),
('H1-H22',1,4),
('K1-K11',1,1),
('K1-K11',1,3),
('K1-K11',1,4),
('Toplam Puan',1,1),
('Toplam Puan',1,3),
('Toplam Puan',1,4);


INSERT INTO publication_criteria (table3action_id, article_count) VALUES
 ( 20, 0),
 ( 21, 0),
 ( 22, 0),
 ( 23, 1),
 ( 24, 2),
 ( 25, 3),
  ( 26, 4),
 ( 27, 7),
 ( 28, 7),
   ( 29, 0),
 ( 30, 0),
 ( 31, 0),
  ( 32, 0),
 ( 33, 1),
 (34,1),
   ( 35, 0),
 ( 36, 1),
 (37,2),
    ( 38, 0),
 ( 39, 1),
 (40,1),
     ( 41, 0),
 ( 42, 2),
 (43,2),
      ( 38, 0),
 ( 39, 0),
 (40,0),
       ( 44, 0),
 ( 45, 0),
 (46,0)

 ;

INSERT INTO min_max_point_criteria (table3action_id, min_point,max_point) VALUES
 (11,45,0),
 (12,125,0),
 (13,125,0),
 (14,5,0),
 (15,0,0),
 (16,0,0),
  (17,0,0),
 (18,0,0),
 (19,0,0),
   (20,0,0),
 (21,0,0),
 (22,0,0),
   (47,0,1500),
 (48,0,1500),
 (49,0,1500),
    (50,0,50),
 (51,0,50),
 (52,0,50),
     (53,0,0),
 (54,15,0),
 (55,15,0),
      (56,0,0),
 (57,20,0),
 (58,20,0),
    (59,0,0),
 (60,0,0),
 (61,0,0),
     (62,0,50),
 (63,0,50),
 (64,0,50),
      (65,100,50),
 (66,250,0),
 (67,250,0);




INSERT INTO table4 (publisher_count,coefficient ) VALUES
(1,1),
(2,0.8),
(3,0.6),
(4,0.5),
(5,0.2),
(6,0.16),
(7,0.14),
(8,0.12),
(9,0.11),
(10,0.1);


--2 more table going to add document must read again
>>>>>>> 9f320b7 (scripts have been added.)


INSERT INTO prerequisites (position, foreign_language_exam_point, ethics_committee_statement)
VALUES (1, 65, 'Adli Sicil Kaydı ve Dil Sınavı PDF Formatında girilmelidir.'),
 (2, 65, 'Adli Sicil Kaydı ve Dil Sınavı PDF Formatında girilmelidir.'),
 (3, 65, 'Adli Sicil Kaydı ve Dil Sınavı PDF Formatında girilmelidir.'),
 (4, 65, 'Adli Sicil Kaydı ve Dil Sınavı PDF Formatında girilmelidir.');


INSERT INTO appointment_requirements (position, facultygroup_id, minmaxxpointcriteria_id, prerequisites_id, publicationcriteria_id)
VALUES (1, 1, 1, 1, 101);




INSERT INTO author_type (coefficient, author_type_name)
VALUES
    (1.0, 'Başlıca Yazar'),
    (1.8, 'Sorumlu Yazar'),
    (1.0, 'Akademik Danışman'),
    (1.0, 'Lisans Öğrencisi'),
 (1.3, 'Kurum Dışından Yazar'),
 (1.0, 'Başka Yazar'),
 (1.8, 'İlk Yazar'),
    (1.0, 'Eş Danışman');