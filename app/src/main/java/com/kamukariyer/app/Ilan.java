package com.kamukariyer.app;

import java.io.Serializable;

public class Ilan implements Serializable {
    private String id;
    private String kurumAdi;
    private String pozisyon;
    private String bolum;
    private String kadroTipi;
    private String sehir;
    private String sonBasvuruTarihi;
    private double kpssMinimum;
    private String ehliyetSartı;
    private String tecrubeSarti;
    private int yasSarti;
    private String cinsiyetSarti;
    private String aciklama;
    private String basvuruLinki;
    private boolean yeniIlan;
    private long yayinlanmaTarihi;
    private int uyumYuzdesi;

    public Ilan() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getKurumAdi() { return kurumAdi; }
    public void setKurumAdi(String kurumAdi) { this.kurumAdi = kurumAdi; }

    public String getPozisyon() { return pozisyon; }
    public void setPozisyon(String pozisyon) { this.pozisyon = pozisyon; }

    public String getBolum() { return bolum; }
    public void setBolum(String bolum) { this.bolum = bolum; }

    public String getKadroTipi() { return kadroTipi; }
    public void setKadroTipi(String kadroTipi) { this.kadroTipi = kadroTipi; }

    public String getSehir() { return sehir; }
    public void setSehir(String sehir) { this.sehir = sehir; }

    public String getSonBasvuruTarihi() { return sonBasvuruTarihi; }
    public void setSonBasvuruTarihi(String sonBasvuruTarihi) { this.sonBasvuruTarihi = sonBasvuruTarihi; }

    public double getKpssMinimum() { return kpssMinimum; }
    public void setKpssMinimum(double kpssMinimum) { this.kpssMinimum = kpssMinimum; }

    public String getEhliyetSartı() { return ehliyetSartı; }
    public void setEhliyetSartı(String ehliyetSartı) { this.ehliyetSartı = ehliyetSartı; }

    public String getTecrubeSarti() { return tecrubeSarti; }
    public void setTecrubeSarti(String tecrubeSarti) { this.tecrubeSarti = tecrubeSarti; }

    public int getYasSarti() { return yasSarti; }
    public void setYasSarti(int yasSarti) { this.yasSarti = yasSarti; }

    public String getCinsiyetSarti() { return cinsiyetSarti; }
    public void setCinsiyetSarti(String cinsiyetSarti) { this.cinsiyetSarti = cinsiyetSarti; }

    public String getAciklama() { return aciklama; }
    public void setAciklama(String aciklama) { this.aciklama = aciklama; }

    public String getBasvuruLinki() { return basvuruLinki; }
    public void setBasvuruLinki(String basvuruLinki) { this.basvuruLinki = basvuruLinki; }

    public boolean isYeniIlan() { return yeniIlan; }
    public void setYeniIlan(boolean yeniIlan) { this.yeniIlan = yeniIlan; }

    public long getYayinlanmaTarihi() { return yayinlanmaTarihi; }
    public void setYayinlanmaTarihi(long yayinlanmaTarihi) { this.yayinlanmaTarihi = yayinlanmaTarihi; }

    public int getUyumYuzdesi() { return uyumYuzdesi; }
    public void setUyumYuzdesi(int uyumYuzdesi) { this.uyumYuzdesi = uyumYuzdesi; }
}
