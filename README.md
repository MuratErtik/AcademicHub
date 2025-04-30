# AcademicHub

AcademicHub, üniversitelerde akademik personel başvurularını yönetmek için geliştirilen tam işlevsel bir web uygulamasıdır. Proje, Spring Boot ile yazılmış bir backend ve React + Vite + Tailwind CSS kullanılarak geliştirilmiş bir frontend içerir.

## 🚀 Özellikler

- 🔐 Yetkili (Admin, Yönetici, Jüri) ve Aday için giriş/kayıt sistemi
- 📋 Aday başvuru ekranı
- 🧑‍🏫 Jüri için başvuru değerlendirme modülü
- 🗃️ Yönetici paneli üzerinden ilan oluşturma ve jüri atama
- 📁 Rapor yükleme ve başvuru detay görüntüleme
- 🌐 Modern kullanıcı arayüzü ve responsif tasarım

## 🧰 Kullanılan Teknolojiler

### Backend
- Java 17
- Spring Boot
- Spring Security
- JPA (Hibernate)
- PostgreSQL
- HikariCP

### Frontend
- React (Vite)
- Tailwind CSS
- Axios
- React Router DOM

## 📂 Proje Yapısı

### Backend (Spring Boot)
```
academicHub/
├── src/main/java/com/yazlab/academichub
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entities/
│   └── request/response/dto
```

### Frontend (React)
```
yazlabFrontEnd/
├── src/
│   ├── components/
│   ├── pages/
│   └── App.jsx
```

## 🛠️ Kurulum

### Backend için:
```bash
cd academicHub
./mvnw spring-boot:run
```

> `application.properties` içinde PostgreSQL yapılandırmasını ayarlamayı unutmayın.

### Frontend için:
```bash
cd yazlabFrontEnd
npm install
npm run dev
```

## 🔐 Giriş Detayları

- Admin, Yönetici, Jüri kullanıcıları için kayıt ekranı: TC, Ad, Soyad, Telefon, E-posta, Departman, Şifre
- Aday kullanıcı için girişte: TC, Ad, Soyad, Telefon, E-posta

## 📸 Ekran Görüntüleri

> Uygulamanın örnek ekran görüntüleri aşağıdaki gibidir:

- Giriş Ekranı
- Aday Dashboard
- Admin Paneli
- Jüri Değerlendirme Ekranı

(Resimleri `assets/screenshots/` altına yerleştirerek Markdown ile ekleyebilirsin.)

## 🧪 Test

- Postman ile `/signin` ve diğer endpoint'ler test edilebilir
- Form kontrolleri frontend'de yapılmaktadır

## 📌 Katkı Sağlamak

1. Fork'la 🍴
2. Yeni bir branch oluştur: `git checkout -b yeni-ozellik`
3. Değişikliklerini commit et: `git commit -m 'Yeni özellik eklendi'`
4. Push et: `git push origin yeni-ozellik`
5. Pull Request gönder 🎉

## 📄 Lisans

Bu proje MIT Lisansı ile lisanslanmıştır.
