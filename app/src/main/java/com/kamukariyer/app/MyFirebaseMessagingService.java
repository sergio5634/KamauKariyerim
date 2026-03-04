package com.kamukariyer.app;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String CHANNEL_ID = "kamu_ilanlari_channel";
    private static final String CHANNEL_NAME = "Kamu İlan Bildirimleri";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        
        String kurumAdi = remoteMessage.getData().get("kurumAdi");
        String pozisyon = remoteMessage.getData().get("pozisyon");
        String bolum = remoteMessage.getData().get("bolum");
        String ilanId = remoteMessage.getData().get("ilanId");
        
        if (kullaniciyaUygunMu(bolum)) {
            bildirimGoster(kurumAdi, pozisyon, ilanId);
        }
    }

    private boolean kullaniciyaUygunMu(String ilanBolumu) {
        SharedPreferences prefs = getSharedPreferences("KullaniciProfili", MODE_PRIVATE);
        String kullaniciBolum = prefs.getString("bolum", "");
        
        // Tam eşleşme veya "Herhangi bir lisans bölümü"
        return ilanBolumu != null && 
               (ilanBolumu.equalsIgnoreCase(kullaniciBolum) ||
                ilanBolumu.equalsIgnoreCase("Herhangi bir lisans bölümü"));
    }

    private void bildirimGoster(String kurumAdi, String pozisyon, String ilanId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("ilanId", ilanId);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(
            this, 0, intent, 
            PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE
        );

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        
        NotificationCompat.Builder notificationBuilder = 
            new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_menu_info_details)
                .setContentTitle("🎯 Size Uygun Yeni Kamu İlanı!")
                .setContentText(kurumAdi + " - " + pozisyon)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle()
                    .bigText(kurumAdi + "\n" + pozisyon + "\n\nHemen başvurun!"));

        NotificationManager notificationManager = 
            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Profilinize uygun yeni kamu ilanları");
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    @Override
    public void onNewToken(@NonNull String token) {
        super.onNewToken(token);
        SharedPreferences prefs = getSharedPreferences("FCM", MODE_PRIVATE);
        prefs.edit().putString("token", token).apply();
    }
}
