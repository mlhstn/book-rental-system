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

