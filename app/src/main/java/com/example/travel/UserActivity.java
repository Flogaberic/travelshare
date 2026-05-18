package com.example.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_user);

        ImageView fleche = findViewById(R.id.imageView8);

        Button deconnexion = findViewById(R.id.button9);

        Button notifs = findViewById(R.id.button7);
        Button publier = findViewById(R.id.button13);

        publier.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, PublierActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        notifs.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, NotifsActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        deconnexion.setOnClickListener(v -> {
                    SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isLoggedIn", false);
                    editor.apply();
                    Intent intent = new Intent(UserActivity.this, AccueilActivity.class);
                    startActivity(intent);
        });

        fleche.setOnClickListener(v -> {
            startActivity(new Intent(UserActivity.this, AccueilActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        Intent intent = new Intent(UserActivity.this, AccueilActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }
        );
    }
}
