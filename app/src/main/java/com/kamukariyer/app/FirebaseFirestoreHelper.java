    public void aktifIlanlariGetir() {
        db.collection(COLLECTION_ILANLAR)
            .whereEqualTo("aktif", true)
            .whereEqualTo("kurumTipi", "Kamu") // SADECE KAMU ALIMLARI
            .orderBy("yayinlanmaTarihi", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener(querySnapshot -> {
                List<Ilan> ilanlar = new ArrayList<>();
                for (QueryDocumentSnapshot doc : querySnapshot) {
                    Ilan ilan = dokumaniIlanYap(doc);
                    if (ilan != null) {
                        ilanlar.add(ilan);
                    }
                }
                if (listener != null) {
                    listener.onIlanlarGeldi(ilanlar);
                }
            })
            .addOnFailureListener(e -> {
                Log.e(TAG, "İlanlar çekilirken hata: " + e.getMessage());
                if (listener != null) {
                    listener.onHata(e.getMessage());
                }
            });
    }
