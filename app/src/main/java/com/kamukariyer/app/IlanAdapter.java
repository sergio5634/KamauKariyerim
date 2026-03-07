package com.kamukariyer.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class IlanAdapter extends RecyclerView.Adapter<IlanAdapter.IlanViewHolder> {

    private List<Ilan> ilanListesi;
    private OnIlanClickListener listener;

    public interface OnIlanClickListener {
        void onDetayClick(Ilan ilan);
        void onFavoriClick(Ilan ilan);
    }

    public IlanAdapter(List<Ilan> ilanListesi, OnIlanClickListener listener) {
        this.ilanListesi = ilanListesi;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ilan_item, parent, false);
        return new IlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IlanViewHolder holder, int position) {
        Ilan ilan = ilanListesi.get(position);
        
        // Temel bilgiler
        holder.tvKurumAdi.setText(ilan.getKurumAdi());
        holder.tvPozisyon.setText(ilan.getPozisyon());
        holder.tvKadroTipi.setText(ilan.getKadroTipi());
        holder.tvSehir.setText(ilan.getSehir());
        holder.tvSonBasvuru.setText("Son: " + ilan.getSonBasvuruTarihi());
        
        // Tablo şartları
        holder.tvSartBolum.setText(ilan.getBolum());
        holder.tvSartKpss.setText(ilan.getKpssMinimum() > 0 ? (int)ilan.getKpssMinimum() + "+" : "Şartsız");
        
        // Yaş şartı
        if (ilan.getYasSarti() != null && !ilan.getYasSarti().isEmpty()) {
            holder.tvSartYas.setText(ilan.getYasSarti());
            holder.rowYas.setVisibility(View.VISIBLE);
        } else {
            holder.rowYas.setVisibility(View.GONE);
        }
        
        // Cinsiyet şartı
        if (ilan.getCinsiyetSarti() != null && !ilan.getCinsiyetSarti().isEmpty() && 
            !ilan.getCinsiyetSarti().equalsIgnoreCase("Farketmez")) {
            holder.tvSartCinsiyet.setText(ilan.getCinsiyetSarti());
            holder.rowCinsiyet.setVisibility(View.VISIBLE);
        } else {
            holder.rowCinsiyet.setVisibility(View.GONE);
        }
        
        // Ehliyet şartı
        if (ilan.getEhliyetSartı() != null && !ilan.getEhliyetSartı().equals("Yok")) {
            holder.tvSartEhliyet.setText(ilan.getEhliyetSartı());
            holder.rowEhliyet.setVisibility(View.VISIBLE);
        } else {
            holder.rowEhliyet.setVisibility(View.GONE);
        }
        
        // Tecrübe şartı
        if (ilan.getTecrubeSarti() != null && !ilan.getTecrubeSarti().equals("Yok")) {
            holder.tvSartTecrube.setText(ilan.getTecrubeSarti());
            holder.rowTecrube.setVisibility(View.VISIBLE);
        } else {
            holder.rowTecrube.setVisibility(View.GONE);
        }
        
        // İkamet şartı
        if (ilan.getIkametSarti() != null && !ilan.getIkametSarti().isEmpty()) {
            holder.tvSartIkamet.setText(ilan.getIkametSarti());
            holder.rowIkamet.setVisibility(View.VISIBLE);
        } else {
            holder.rowIkamet.setVisibility(View.GONE);
        }
        
        // Engel durumu şartı
        if (ilan.isEngelDurumuSarti()) {
            holder.tvSartEngel.setText("Engelli adaylar öncelikli");
            holder.rowEngel.setVisibility(View.VISIBLE);
        } else {
            holder.rowEngel.setVisibility(View.GONE);
        }
        
        // Güvenlik kartı şartı
        if (ilan.isGuvenlikKartiSarti()) {
            holder.tvSartGuvenlik.setText("Gerekli");
            holder.rowGuvenlik.setVisibility(View.VISIBLE);
        } else {
            holder.rowGuvenlik.setVisibility(View.GONE);
        }
        
        // Elden evrak teslimi
        if (ilan.isEldenEvrak()) {
            holder.tvSartEldenEvrak.setText("Elden evrak teslimi gerekli");
            holder.rowEldenEvrak.setVisibility(View.VISIBLE);
        } else {
            holder.rowEldenEvrak.setVisibility(View.GONE);
        }
        
        // Uyum yüzdesi
        holder.tvUyumYuzdesi.setText("%" + ilan.getUyumYuzdesi());
        
        // Uyum rengi
        int uyumRengi;
        if (ilan.getUyumYuzdesi() >= 80) {
            uyumRengi = 0xFF2E7D32; // Yeşil
        } else if (ilan.getUyumYuzdesi() >= 50) {
            uyumRengi = 0xFFF57C00; // Turuncu
        } else {
            uyumRengi = 0xFFD32F2F; // Kırmızı
        }
        holder.tvUyumYuzdesi.setTextColor(uyumRengi);

        // Yeni ilan rozeti
        holder.tvYeniRozet.setVisibility(ilan.isYeniIlan() ? View.VISIBLE : View.GONE);

        // Butonlar
        holder.btnDetay.setOnClickListener(v -> listener.onDetayClick(ilan));
        holder.btnFavori.setOnClickListener(v -> listener.onFavoriClick(ilan));
    }

    @Override
    public int getItemCount() {
        return ilanListesi.size();
    }

    public void guncelleListe(List<Ilan> yeniListe) {
        this.ilanListesi = yeniListe;
        notifyDataSetChanged();
    }

    static class IlanViewHolder extends RecyclerView.ViewHolder {
        TextView tvKurumAdi, tvPozisyon, tvKadroTipi, tvSehir, tvSonBasvuru;
        TextView tvSartBolum, tvSartKpss, tvSartYas, tvSartCinsiyet, tvSartEhliyet, tvSartTecrube, tvSartIkamet;
        TextView tvSartEngel, tvSartGuvenlik, tvSartEldenEvrak, tvUyumYuzdesi, tvYeniRozet;
        TableRow rowYas, rowCinsiyet, rowEhliyet, rowTecrube, rowIkamet, rowEngel, rowGuvenlik, rowEldenEvrak;
        MaterialButton btnDetay, btnFavori;

        public IlanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKurumAdi = itemView.findViewById(R.id.tvKurumAdi);
            tvPozisyon = itemView.findViewById(R.id.tvPozisyon);
            tvKadroTipi = itemView.findViewById(R.id.tvKadroTipi);
            tvSehir = itemView.findViewById(R.id.tvSehir);
            tvSonBasvuru = itemView.findViewById(R.id.tvSonBasvuru);
            
            // Tablo şartları
            tvSartBolum = itemView.findViewById(R.id.tvSartBolum);
            tvSartKpss = itemView.findViewById(R.id.tvSartKpss);
            tvSartYas = itemView.findViewById(R.id.tvSartYas);
            tvSartCinsiyet = itemView.findViewById(R.id.tvSartCinsiyet);
            tvSartEhliyet = itemView.findViewById(R.id.tvSartEhliyet);
            tvSartTecrube = itemView.findViewById(R.id.tvSartTecrube);           
            tvSartEngel = itemView.findViewById(R.id.tvSartEngel);
            tvSartGuvenlik = itemView.findViewById(R.id.tvSartGuvenlik);
            tvSartEldenEvrak = itemView.findViewById(R.id.tvSartEldenEvrak);
            
            // TableRow'lar
            rowYas = itemView.findViewById(R.id.rowYas);
            rowCinsiyet = itemView.findViewById(R.id.rowCinsiyet);
            rowEhliyet = itemView.findViewById(R.id.rowEhliyet);
            rowTecrube = itemView.findViewById(R.id.rowTecrube);
            rowIkamet = itemView.findViewById(R.id.rowIkamet);
            rowEngel = itemView.findViewById(R.id.rowEngel);
            rowGuvenlik = itemView.findViewById(R.id.rowGuvenlik);
            rowEldenEvrak = itemView.findViewById(R.id.rowEldenEvrak);
            
            tvUyumYuzdesi = itemView.findViewById(R.id.tvUyumYuzdesi);
            tvYeniRozet = itemView.findViewById(R.id.tvYeniRozet);
            btnDetay = itemView.findViewById(R.id.btnDetay);
            btnFavori = itemView.findViewById(R.id.btnFavori);
        }
    }
}
