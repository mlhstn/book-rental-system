**Online Kitap Kiralama Sistemi**

Bu proje, kullanıcıların kitapları çevrimiçi olarak kiralayabileceği, kiralanan kitapları takip edebileceği ve kitap koleksiyonlarıyla
etkileşimde bulunabileceği bir kitap kiralama platformudur. Sistemde kullanıcılar, kitapları kiralayabilir, iade edebilir ve kiralama sürelerine
bağlı ceza uygulamaları ile ödeme yapabilirler.

**Proje Özeti**

Kitap kiralama platformu, kitapların mevcut olup olmadığını kontrol etmeyi, kitapları kiralamayı, iade etmeyi ve cezalar ile ödeme sistemlerini
entegre etmeyi amaçlamaktadır. Ayrıca, kiralama süresi aşılırsa kullanıcıya ceza uygulanır. Özellikler arasında belirli durumlarda
indirim sistemi ve veritabanı entegrasyonu bulunmaktadır.

**Teknolojiler**

**Backend:** Java, Spring Boot

**Veritabanı:** MySQL (JPA/Hibernate)

**API:** RESTful API

**Ödeme ve Ceza Sistemi**

Kiralama süresi aşıldığında günlük 10 TL ceza uygulanır.(Kiralama süresi 14 gün olarak belirtilmiştir.)
Kullanıcılar, ceza tutarını iade sırasında görebilir.
Her kitabın günlük kiralama ücreti farklıdır.

**İndirim Sistemi**

3 kitap kiralayan bir kullanıcıya %10 indirim uygulanır.

**Veritabanı Entegrasyonu**

Kitaplar, kullanıcılar ve kiralamalar veritabanında saklanarak kalıcı hale getirilir. Veritabanı bağlantısı için MySQL kullanılmaktadır.


![image](https://github.com/user-attachments/assets/33c33f89-9573-4f04-95fd-ab62c7df5984)

**Yeni Özellikler**

Bu projeye planlanan bazı yeni özellikler aşağıda belirtilmiştir. Bu özellikler, sistemin işlevselliğini artırmayı ve kullanıcı deneyimini geliştirmeyi amaçlamaktadır.

**1. Rezervasyon Sistemi**

Kullanıcılar, şu anda kiralanabilir durumda olmayan kitapları rezerve edebilir. Kitap iade edildiğinde, rezervasyon sırasındaki kullanıcıya bildirim yapılır ve kitap teslim edilerek, rezervasyonu gerçekleştiren kullanıcıya kiralanabilir hale gelir.

**Özellikler:**

Kitap, mevcutta kiralanabilir değilse kullanıcılar rezervasyon yapabilir.
Kitap iade edildiğinde, sıradaki kullanıcıya bildirim yapılır ve kitap teslim edilir.
2. Okuma Geçmişine Göre Kitap Öneri Sistemi
Kullanıcıların okuma geçmişine göre kitap önerileri yapılacaktır. Bu öneriler, kullanıcıların daha önce okudukları kitaplara benzer şekilde, içerik bazlı öneri algoritması ya da collaborative filtering yöntemi kullanılarak sunulacaktır.

**Özellikler:**

Kullanıcıların okuma geçmişine dayanarak öneriler sunulur.
İçerik bazlı öneri veya collaborative filtering yöntemleri kullanılabilir.
3. İade Tarihi Geçen Kullanıcılara E-posta Gönderimi
İade tarihi geçen kitaplar için kullanıcıya otomatik e-posta hatırlatması gönderilecektir. Bu e-posta, iade tarihi geçen kitapları ve varsa cezaları içerir. Böylece kullanıcılar, cezalar ve iade işlemleri hakkında bilgilendirilir.

**Özellikler:**

Kiralama süresi aşıldığında kullanıcılara e-posta hatırlatması yapılır.
E-posta, iade tarihi geçmiş kitaplar ve cezalar hakkında bilgi içerir.
