📚 **Online Kitap Kiralama Sistemi**

Bu proje, kullanıcıların çevrimiçi ortamda kitap kiralayabileceği bir platformdur. Kullanıcılar kitap kiralayabilir, iade edebilir, geciken iade durumlarında ceza alabilir ve sistem tarafından otomatik bilgilendirme alabilir.

🔍**Proje Özeti**

Sistem; kitap stoğu kontrolü, kiralama ve iade işlemleri, ceza uygulamaları, indirim sistemi ve veritabanı yönetimini içermektedir.
Kullanıcılar, günlük kiralama ücretine göre kitap kiralar. 14 günün aşılması durumunda günlük 10 TL ceza uygulanır.
Ayrıca bir kullanıcı aynı anda 3 veya daha fazla kitap kiralarsa %10 indirim uygulanır.

⚙️ **Kullanılan Teknolojiler**

Backend: Java, Spring Boot

Veritabanı: MySQL (JPA/Hibernate)

API: RESTful Web Services

Mail: JavaMailSender + Mailtrap (test ortamı)

💸**Ödeme ve Ceza Sistemi**

Kiralama süresi: 14 gün

Süre aşıldığında: Günlük 10 TL ceza

Ceza, iade sırasında kullanıcıya gösterilir

Her kitabın günlük kiralama ücreti farklıdır

3 kitap kiralayan kullanıcıya %10 indirim uygulanır

🧩 **Veritabanı Entegrasyonu**

Kitaplar, kullanıcılar ve kiralama işlemleri MySQL veritabanında kalıcı olarak saklanır.
JPA/Hibernate kullanılarak nesne ilişkisel haritalama sağlanır.

✉️ **Otomatik E-Posta Bildirim Sistemi**

Geciken iadeler için sistem, kullanıcılara otomatik e-posta hatırlatması gönderir.
Mail içeriğinde geciken kitap ve varsa ceza bilgileri yer alır.

🔔 **1. Rezervasyon Sistemi (Yeni Özellik)**

Kullanıcılar stokta olmayan kitaplar için rezervasyon yapabilir
Kitap iade edildiğinde, rezervasyon sırasındaki ilk kullanıcıya otomatik e-posta gönderilir
Kullanıcı 1 gün içinde kitabı almazsa rezervasyon iptal edilir
Bu işlem @Scheduled ile her gün otomatik olarak kontrol edilir
isNotified, notifiedAt ve active alanları ile rezervasyon durumu yönetilir
Mail gönderimi için Mailtrap + JavaMailSender kullanılmıştır

🧠 **2. Okuma Geçmişine Göre Kitap Öneri Sistemi (Yakında)**

Kullanıcının okuma geçmişine göre kitap önerileri sunulacaktır
İçerik bazlı öneri algoritmaları veya collaborative filtering yöntemleri kullanılabilir
