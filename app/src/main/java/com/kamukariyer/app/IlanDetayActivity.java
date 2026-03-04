package com.kamukariyer.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class IlanDetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_detay);

        Ilan ilan = (Ilan) getIntent().getSerializableExtra("ilan");
        if (ilan == null) {
            Toast.makeText(this, "İlan bilgisi bulunamadı", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // View'ları bağla
        TextView tvKurum = findViewById(R.id.tvDetayKurum);
        TextView tvPozisyon = findViewById(R.id.tvDetayPozisyon);
        TextView tvBolum = findViewById(R.id.tvDetayBolum);
        TextView tvKadro = findViewById(R.id.tvDetayKadro);
        TextView tvSehir = findViewById(R.id.tvDetaySehir);
        TextView tvKpss = findViewById(R.id.tvDetayKpss);
        TextView tvEhliyet = findViewById(R.id.tvDetayEhliyet);
        TextView tvTecrube = findViewById(R.id.tvDetayTecrube);
        TextView tvIkamet = findViewById(R.id.tvDetayIkamet);
        TextView tvAciklama = findViewById(R.id.tvDetayAciklama);
        TextView tvSonBasvuru = findViewById(R.id.tvDetaySonBasvuru);
        Button btnBasvur = findViewById(R.id.btnBasvur);
        Button btnGeri = findViewById(R.id.btnGeri);

        // Verileri doldur
        tvKurum.setText(ilan.getKurumAdi());
        tvPozisyon.setText(ilan.getPozisyon());
        tvBolum.setText("Bölüm: " + ilan.getBolum());
        tvKadro.setText("Kadro: " + ilan.getKadroTipi());
        tvSehir.setText("Şehir: " + ilan.getSehir());
        tvKpss.setText("KPSS Şartı: " + (ilan.getKpssMinimum() > 0 ? ilan.getKpssMinimum() + "+" : "Yok"));
        tvEhliyet.setText("Ehliyet: " + ilan.getEhliyetSartı());
        tvTecrube.setText("Tecrübe: " + (ilan.getTecrubeSarti() != null ? ilan.getTecrubeSarti() : "Yok"));
        
        // İkamet şartı (yeni)
        if (ilan.getIkametSarti() != null && !ilan.getIkametSarti().isEmpty()) {
            tvIkamet.setText("İkamet Şartı: " + ilan.getIkametSarti());
            tvIkamet.setVisibility(TextView.VISIBLE);
        } else {
            tvIkamet.setVisibility(TextView.GONE);
        }
        
        tvAciklama.setText(ilan.getAciklama());
        tvSonBasvuru.setText("Son Başvuru: " + ilan.getSonBasvuruTarihi());

        // Butonlar
        btnBasvur.setOnClickListener(v -> {
            if (ilan.getBasvuruLinki() != null && !ilan.getBasvuruLinki().isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(ilan.getBasvuruLinki()));
                startActivity(browserIntent);
            } else {
                Toast.makeText(this, "Başvuru linki bulunamadı", Toast.LENGTH_SHORT).show();
            }
        });

        btnGeri.setOnClickListener(v -> finish());
    }
}
