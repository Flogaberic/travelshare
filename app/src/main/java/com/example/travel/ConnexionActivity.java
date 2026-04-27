package com.example.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ConnexionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_connexion);

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }
        );

        Button inscription = findViewById(R.id.button6);

        inscription.setOnClickListener(v -> {
            startActivity(new Intent(ConnexionActivity.this, InscriptionActivity.class));
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        ImageView btn = findViewById(R.id.imageView8);

        btn.setOnClickListener(v -> {
            startActivity(new Intent(ConnexionActivity.this, AccueilActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        Button connexion = findViewById(R.id.button5);

        connexion.setOnClickListener(v -> {
            startActivity(new Intent(ConnexionActivity.this, AccueilActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
            prefs.edit().putBoolean("isLoggedIn", true).apply();
        });

    }
}
