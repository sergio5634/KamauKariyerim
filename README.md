# KamuKariyerim 📱

Kamu kurumları personel alım ilanlarını takip eden Android uygulaması.

## 🎯 Özellikler

- ✅ 400+ lisans bölümü desteği
- ✅ Kendi bölümünüze özel ilanlar
- ✅ "Herhangi lisans bölümü" şartlı ilanlar (sizin için de uygun!)
- ✅ Profil bazlı akıllı filtreleme (bölüm, KPSS, ehliyet)
- ✅ Uyum yüzdesi hesaplama ve renkli gösterim
- ✅ Anlık Firebase bildirimleri (yeni ilan gelince bildirim)
- ✅ Şartlar özeti kartları (tek bakışta tüm şartlar)
- ✅ Favori ilanları kaydetme

## 🎓 Bölüm Eşleştirme Mantığı

Uygulama size şu ilanları gösterir:

| Durum | Açıklama | Örnek |
|-------|----------|-------|
| Tam Eşleşme | Bölümünüz tam olarak isteniyor | "Bilgisayar Mühendisliği" |
| Genel Eşleşme | "Herhangi bir lisans bölümünden" şartı var | Tüm lisans mezunları uygun |
| Kısmi Eşleşme | Bölüm grubu eşleşiyor | "Mühendislik fakültesi" |

## 🛠️ Kurulum

1. Android Studio'yu açın
2. "Open an existing project" seçin
3. Bu klasörü seçin
4. Firebase Console'dan google-services.json indirip app/ klasörüne ekleyin
5. Sync Project with Gradle Files
6. Run ▶

## 🔥 Firebase Yapılandırması

### Firestore Collection: kamuilani

`javascript
{
  kurumAdi: "Sağlık Bakanlığı",
  pozisyon: "Bilgisayar Mühendisi",
  bolum: "Bilgisayar Mühendisliği", // veya "Herhangi bir lisans bölümü"
  bolumGrubu: "Mühendislik", // veya "Fen-Edebiyat", "İktisadi" vb.
  kadroTipi: "Sözleşmeli",
  sehir: "Ankara",
  sonBasvuruTarihi: timestamp,
  kpssMinimum: 70,
  ehliyetSartı: "B",
  tecrubeSarti: "2 yıl",
  aciklama: "Detaylı açıklama...",
  basvuruLinki: "https://...",
  aktif: true,
  yayinlanmaTarihi: timestamp
}
