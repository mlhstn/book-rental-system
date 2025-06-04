## 📚 Online Kitap Kiralama Sistemi

Çevrimiçi ortamda kitap kiralama, iade, gecikme cezaları ve otomatik bildirimleri bir arada sunan tam donanımlı bir platform.

---

### ⚙️ Kullanılan Teknolojiler

- **Backend:** Java, Spring Boot  
- **Veritabanı:** MySQL (JPA/Hibernate)  
- **API:** RESTful Web Services  
- **Mail:** JavaMailSender + Mailtrap (test ortamı)  

---

- **📦 Stok Kontrolü & Kiralama:** Kitaplar yalnızca stokta varsa kiralanabilir. Her kitabın günlük kiralama ücreti farklıdır.

- **⏱️ İade & Ceza:** Kiralama süresi 14 gündür. Gecikilen her gün için otomatik olarak günlük 10 ₺ ceza uygulanır.

- **🎁 İndirim Sistemi:** Aynı anda 3 veya daha fazla kitap kiralayan kullanıcılara sistem otomatik olarak %10 indirim uygular.

- **✉️ Geciken İade E-Postası:** İade tarihi geçen kitaplar için kullanıcıya otomatik e-posta hatırlatması gönderilir.

- **🔔 Rezervasyon Sistemi:** Stokta olmayan kitaplar için rezervasyon yapılabilir. Kitap iade edildiğinde sıradaki kullanıcıya otomatik e-posta gönderilir. 1 gün içinde alınmazsa rezervasyon iptal edilir.

- **📬 Otomatik Bildirimler:** `@Scheduled` kullanılarak sistem gecikmeleri ve rezervasyonları arka planda düzenli olarak kontrol eder.

- **📝 Yorum & Puanlama:** Kullanıcılar kitaplara 1–5 yıldız arasında puan verebilir ve yorum bırakabilir. Yorumlar listelenir, kitaplara ait ortalama puan hesaplanır.

- **📊 Ortalama Puan Gösterimi:** Her kitabın kullanıcılar tarafından verilen puanlarının ortalaması dinamik olarak hesaplanıp gösterilir.

---

### 🧠 3. Okuma Geçmişine Göre Kitap Önerileri (Yakında)

Kullanıcının okuma geçmişine dayalı içerik‑bazlı veya collaborative filtering öneri algoritmaları eklenecek.  
