package com.kamukariyer.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessaging;

public class BildirimYoneticisi {

    public static void bildirimlereAboneOl(Context context, String bolum) {
        // Bölüm adını topic formatına çevir
        String topic = bolum.toLowerCase()
                           .replace(" ", "_")
                           .replace("ı", "i")
                           .replace("ğ", "g")
                           .replace("ü", "u")
                           .replace("ş", "s")
                           .replace("ö", "o")
                           .replace("ç", "c");
        
        // Ayrıca "herhangi_lisans_bolumu" topic'ine de abone ol
        String genelTopic = "herhangi_lisans_bolumu";
        
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener(task -> {
                String msg = task.isSuccessful() ? 
                    "Bildirimlere abone olundu: " + topic : 
                    "Abone olma başarısız";
                Log.d("FCM", msg);
            });
            
        // Genel bölüm ilanlarına da abone ol
        FirebaseMessaging.getInstance().subscribeToTopic(genelTopic)
            .addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Log.d("FCM", "Genel bölüm bildirimlerine abone olundu");
                }
            });
        
        SharedPreferences prefs = context.getSharedPreferences("Bildirimler", Context.MODE_PRIVATE);
        prefs.edit().putString("abone_olunan_topic", topic).apply();
    }

    public static void bildirimlerdenCik(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("Bildirimler", Context.MODE_PRIVATE);
        String eskiTopic = prefs.getString("abone_olunan_topic", "");
        
        if (!eskiTopic.isEmpty()) {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(eskiTopic);
            FirebaseMessaging.getInstance().unsubscribeFromTopic("herhangi_lisans_bolumu");
        }
    }
}
