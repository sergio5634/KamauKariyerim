package com.kamukariyer.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private EditText etTakmaAd, etYas, etKpssPuani;
    private AutoCompleteTextView spinnerCinsiyet, spinnerIl, spinnerEgitimSeviyesi, 
                                spinnerBolum, spinnerEhliyet, spinnerKpssTuru;
    private SwitchMaterial switchEngel, switchGuvenlikKarti;
    private Button btnProfiliKaydet;
    
    private TextInputLayout layoutKpssPuani;

    // Bölüm listeleri
    private final String[] CINSIYETLER = {"Erkek", "Kadın"};
    private final String[] ILLER = {"Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", 
        "Antalya", "Artvin", "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", 
        "Bursa", "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", 
        "Erzincan", "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", 
        "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", 
        "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", 
        "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt", 
        "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak", "Van", 
        "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak", 
        "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    
    private final String[] EGITIM_SEVIYELERI = {"Lisans", "Önlisans", "Yüksek Lisans"};
    
    private final String[] BOLUMLER = {
        // Sağlık
        "Hemşirelik", "Tıp", "Eczacılık", "Fizyoterapi ve Rehabilitasyon", "Beslenme ve Diyetetik",
        "Sosyal Hizmetler", "Psikoloji", "Biyokimya", "Moleküler Biyoloji ve Genetik",
        // Mühendislik
        "Bilgisayar Mühendisliği", "Elektrik-Elektronik Mühendisliği", "İnşaat Mühendisliği",
        "Makine Mühendisliği", "Endüstri Mühendisliği", "Yazılım Mühendisliği",
        // İktisadi
        "İşletme", "İktisat", "Maliye", "Muhasebe ve Denetim", "Bankacılık ve Finans",
        // Fen-Edebiyat
        "Matematik", "Fizik", "Kimya", "Biyoloji", "Tarih", "Coğrafya", "Felsefe", "Sosyoloji",
        // Hukuk
        "Hukuk",
        // Eğitim
        "Sınıf Öğretmenliği", "Okul Öncesi Öğretmenliği", "İngilizce Öğretmenliği",
        // Diğer
        "Kamu Yönetimi", "Uluslararası İlişkiler", "İletişim", "Gazetecilik"
    };
    
    private final String[] EHLIYETLER = {"Yok", "A1", "A2", "B", "C", "D", "E", "F", "G", "C ve Üzeri"};
    private final String[] KPSS_TURLERI = {"Lisans (P3)", "Lisans (P1)", "Lisans (P2)", 
        "Önlisans (P93)", "Ortaöğretim (P94)"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        setupSpinners();
        loadProfilBilgileri();

        btnProfiliKaydet.setOnClickListener(v -> profiliKaydet());
    }

    private void initViews() {
        etTakmaAd = findViewById(R.id.etTakmaAd);
        etYas = findViewById(R.id.etYas);
        etKpssPuani = findViewById(R.id.etKpssPuani);
        
        spinnerCinsiyet = findViewById(R.id.spinnerCinsiyet);
        spinnerIl = findViewById(R.id.spinnerIl);
        spinnerEgitimSeviyesi = findViewById(R.id.spinnerEgitimSeviyesi);
        spinnerBolum = findViewById(R.id.spinnerBolum);
        spinnerEhliyet = findViewById(R.id.spinnerEhliyet);
        spinnerKpssTuru = findViewById(R.id.spinnerKpssTuru);
        
        switchEngel = findViewById(R.id.switchEngel);
        switchGuvenlikKarti = findViewById(R.id.switchGuvenlikKarti);
        
        btnProfiliKaydet = findViewById(R.id.btnProfiliKaydet);
        layoutKpssPuani = findViewById(R.id.layoutKpssPuani);
    }

    private void setupSpinners() {
        setSpinnerAdapter(spinnerCinsiyet, CINSIYETLER);
        setSpinnerAdapter(spinnerIl, ILLER);
        setSpinnerAdapter(spinnerEgitimSeviyesi, EGITIM_SEVIYELERI);
        setSpinnerAdapter(spinnerBolum, BOLUMLER);
        setSpinnerAdapter(spinnerEhliyet, EHLIYETLER);
        setSpinnerAdapter(spinnerKpssTuru, KPSS_TURLERI);
    }

    private void setSpinnerAdapter(AutoCompleteTextView spinner, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, 
            android.R.layout.simple_dropdown_item_1line, items);
        spinner.setAdapter(adapter);
    }

    private void loadProfilBilgileri() {
        SharedPreferences prefs = getSharedPreferences("KullaniciProfili", MODE_PRIVATE);
        
        etTakmaAd.setText(prefs.getString("takmaAd", ""));
        etYas.setText(prefs.getString("yas", ""));
        etKpssPuani.setText(prefs.getString("kpssPuani", ""));
        
        setSpinnerValue(spinnerCinsiyet, prefs.getString("cinsiyet", "Erkek"));
        setSpinnerValue(spinnerIl, prefs.getString("il", ""));
        setSpinnerValue(spinnerEgitimSeviyesi, prefs.getString("egitimSeviyesi", "Lisans"));
        setSpinnerValue(spinnerBolum, prefs.getString("bolum", ""));
        setSpinnerValue(spinnerEhliyet, prefs.getString("ehliyet", "Yok"));
        setSpinnerValue(spinnerKpssTuru, prefs.getString("kpssTuru", "Lisans (P3)"));
        
        switchEngel.setChecked(prefs.getBoolean("engelDurumu", false));
        switchGuvenlikKarti.setChecked(prefs.getBoolean("guvenlikKarti", false));
    }

    private void setSpinnerValue(AutoCompleteTextView spinner, String value) {
        if (value != null && !value.isEmpty()) {
            spinner.setText(value, false);
        }
    }

    private void profiliKaydet() {
        // Validasyon
        if (etTakmaAd.getText().toString().trim().isEmpty()) {
            etTakmaAd.setError("Takma ad gerekli");
            return;
        }
        if (etYas.getText().toString().trim().isEmpty()) {
            etYas.setError("Yaş gerekli");
            return;
        }
        if (spinnerBolum.getText().toString().trim().isEmpty()) {
            spinnerBolum.setError("Bölüm seçimi gerekli");
            return;
        }

        SharedPreferences prefs = getSharedPreferences("KullaniciProfili", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        
        editor.putString("takmaAd", etTakmaAd.getText().toString().trim());
        editor.putString("yas", etYas.getText().toString().trim());
        editor.putString("kpssPuani", etKpssPuani.getText().toString().trim());
        
        editor.putString("cinsiyet", spinnerCinsiyet.getText().toString());
        editor.putString("il", spinnerIl.getText().toString());
        editor.putString("egitimSeviyesi", spinnerEgitimSeviyesi.getText().toString());
        editor.putString("bolum", spinnerBolum.getText().toString());
        editor.putString("ehliyet", spinnerEhliyet.getText().toString());
        editor.putString("kpssTuru", spinnerKpssTuru.getText().toString());
        
        editor.putBoolean("engelDurumu", switchEngel.isChecked());
        editor.putBoolean("guvenlikKarti", switchGuvenlikKarti.isChecked());
        
        // Şehir listesi (şimdilik tek şehir, isterseniz 3 şehir yapılabilir)
        editor.putString("sehir1", spinnerIl.getText().toString());
        
        editor.apply();

        Toast.makeText(this, "Profil kaydedildi!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
