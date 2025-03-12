**Online Kitap Kiralama Sistemi**

Bu proje, kullanıcıların kitapları çevrimiçi olarak kiralayabileceği, kiralanan kitapları takip edebileceği ve kitap koleksiyonlarıyla
etkileşimde bulunabileceği bir kitap kiralama platformudur. Sistemde kullanıcılar, kitapları kiralayabilir, iade edebilir ve kiralama sürelerine
bağlı ceza uygulamaları ile ödeme yapabilirler.

**Proje Özeti**

Kitap kiralama platformu, kitapların mevcut olup olmadığını kontrol etmeyi, kitapları kiralamayı, iade etmeyi ve cezalar ile ödeme sistemlerini
entegre etmeyi amaçlamaktadır. Ayrıca, kiralama süresi aşılırsa kullanıcıya ceza uygulanır. İleri düzey özellikler arasında, belirli kdurumlarda
indirim sistemi ve veritabanı entegrasyonu bulunmaktadır.

**Teknolojiler**

**Backend:** Java, Spring Boot

**Veritabanı:** MySQL (JPA/Hibernate)

**API:** RESTful API

**Ödeme ve Ceza Sistemi**

Kiralama süresi aşıldığında günlük 10 TL ceza uygulanır.(Kiralama süresi 14 gün olarak belirtilmiştir.)
Kullanıcılar, ceza tutarını iade sırasında görebilir.

**İndirim Sistemi**

3 kitap kiralayan bir kullanıcıya %10 indirim uygulanabilir.

**Veritabanı Entegrasyonu**

Kitaplar, kullanıcılar ve kiralamalar veritabanında saklanarak kalıcı hale getirilir. Veritabanı bağlantısı için MySQL kullanılmaktadır.

**Exception Handling**
Geçersiz işlemler (örneğin, kiralama süresi geçtiği halde kitap iade edilmeye çalışılması) için uygun hata mesajları döndürülür.

