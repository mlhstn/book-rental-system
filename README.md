## 📚 Online Kitap Kiralama Sistemi

Çevrimiçi ortamda kitap kiralama, iade, gecikme cezaları ve otomatik bildirimleri bir arada sunan tam donanımlı bir platform.

---

### 🔍 Proje Özeti

- **Stok Kontrolü & Kiralama:** Kitaplar günlük kiralama ücretine göre kiralanır.  
- **İade & Ceza:** 14 günün aşılması durumunda günlük 10 ₺ ceza uygulanır.  
- **İndirim:** Aynı anda 3 veya daha fazla kitap kiralayan kullanıcılara %10 indirim.  

---

### ⚙️ Kullanılan Teknolojiler

- **Backend:** Java, Spring Boot  
- **Veritabanı:** MySQL (JPA/Hibernate)  
- **API:** RESTful Web Services  
- **Mail:** JavaMailSender + Mailtrap (test ortamı)  

---

### 💸 Ödeme & Ceza Sistemi

- **Kiralama Süresi:** 14 gün  
- **Gecikme Cezası:** Günlük 10 ₺  
- **Farklı Ücret:** Her kitabın günlük kiralama ücreti ayrı  
- **İndirim:** 3+ kitap → %10  

---

### 🧩 Veritabanı

Kitap, kullanıcı ve kiralama verileri MySQL’de kalıcı olarak saklanır; JPA/Hibernate ile nesne‑ilişkisel haritalama gerçekleştirilir.

---

### ✉️ Otomatik E‑Posta Bildirimleri

Geciken iadeler için Mailtrap üzerinden otomatik hatırlatma e‑postası gönderilir; içerikte gecikme süresi ve ceza bilgisi yer alır.

---

### 🔔 1. Rezervasyon Sistemi (Yeni Özellik)

- Stokta olmayan kitaplar için **rezervasyon**  
- İade anında **sıradaki kullanıcıya mail**  
- 1 gün içinde alınmazsa **otomatik iptal**  
- `@Scheduled` ile günlük kontrol  
- Yönetim: `isNotified`, `notifiedAt`, `active`  
- Mailtrap + JavaMailSender ile test  

---

### 🖋️ 2. Yorum & Puanlama Sistemi (Yeni Özellik)

- Kullanıcılar kitaplara **1–5 yıldız** arası puan verebilir  
- **500 karakter**e kadar metin yorumu ekleyebilir  
- Kitap sayfalarında **ortalama puan** ve **yorum listesi** görüntülenir  
- Spring Boot, JPA One‑To‑Many, Bean Validation, ModelMapper kullanıldı  

---

### 🧠 3. Okuma Geçmişine Göre Kitap Önerileri (Yakında)

Kullanıcının okuma geçmişine dayalı içerik‑bazlı veya collaborative filtering öneri algoritmaları eklenecek.  
