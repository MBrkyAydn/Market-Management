# 🛒 Market Yönetim Sistemi (Market Management System)

Bu proje; **Nesne Yönelimli Programlama (OOP)** prensiplerine ve **Katmanlı Mimari (Layered Architecture)** standartlarına uygun olarak **Java** ve **MySQL (JDBC)** ile geliştirilmiş bir konsol tabanlı market yönetim uygulamasıdır.

---

## 📌 Özellikler

- **Ürün Yönetimi (CRUD):**
  - Farklı kategorilerde ürün tanımlama:
    - 🍎 **Gıda Ürünleri (`FoodProduct`):** Son kullanma tarihi takibi ve kategoriye özel vergi oranı.
    - 💻 **Elektronik Ürünler (`ElectronicProduct`):** Garanti süresi (ay) takibi.
    - 🧼 **Temizlik Ürünleri (`CleaningProduct`):** Kullanım alanı bilgisi.
  - Kayıtlı ürünleri listeleme.
  - Ürün bilgilerini (İsim, Fiyat) güncelleme.
  - ID bazlı ürün silme.
- **Satış İşlemleri:**
  - Stok kontrollü ürün satışı (yetersiz stok durumunda hata yönetimi).
  - Satış esnasında otomatik stok düşümü ve toplam tutar hesaplama.
  - Satış geçmişini tarih ve saat bilgileriyle listeleme.
- **Raporlama & Dosyaya Aktarma (I/O):**
  - Ürün listesini `products.txt` dosyasına yazdırma.
  - Satış kayıtlarını `sales.txt` dosyasına yazdırma.
- **Hata Yönetimi & Güvenli Girdi:**
  - Kullanıcı girişlerindeki geçersiz veri tiplerini yakalayan `InputHelper` mekanizması.

---

## 🏛️ Mimari ve OOP Yaklaşımı

Proje **Katmanlı Mimari (Layered Architecture)** ve **DAO (Data Access Object)** deseni kullanılarak tasarlanmıştır:

```text
src/
├── dao/                  # Veritabanı sorguları ve CRUD operasyonları
│   ├── ProductDao.java
│   ├── ProductDaoImpl.java
│   ├── SaleDao.java
│   └── SaleDaoImpl.java
├── entity/               # Varlık sınıfları ve OOP modelleri
│   ├── Category.java     # Enum
│   ├── Product.java      # Soyut (abstract) temel sınıf
│   ├── FoodProduct.java
│   ├── ElectronicProduct.java
│   ├── CleaningProduct.java
│   └── Sale.java
├── service/              # İş kuralları (Business Logic) katmanı
│   ├── ProductService.java
│   └── SaleService.java
├── util/                 # Yardımcı sınıflar
│   ├── DatabaseConnection.java  # JDBC bağlantı yönetimi
│   ├── FileManager.java         # Dosya dışa aktarım işlemleri
│   └── InputHelper.java         # Konsol veri okuma ve doğrulama
└── Main.java             # Menü döngüsü ve uygulama başlangıç noktası
