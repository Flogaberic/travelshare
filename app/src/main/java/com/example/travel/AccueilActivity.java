package com.example.travel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.View;
import android.widget.Button;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONObject;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AccueilActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> imageUrls = new ArrayList<>();
    List<String> imageTitles = new ArrayList<>();
    List<String> imageLikes = new ArrayList<>();

    ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        setContentView(R.layout.activity_accueil);

        recyclerView = findViewById(R.id.recyclerView);

        adapter = new ImageAdapter(imageUrls, imageTitles, imageLikes);

        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);

        layoutManager.setGapStrategy(
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
        );

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

        loadFromJson();

        ImageView btn = findViewById(R.id.imageView4);

        btn.setOnClickListener(v -> {
            startActivity(new Intent(AccueilActivity.this, MainActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        SharedPreferences prefs = getSharedPreferences("app", MODE_PRIVATE);
        boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

        ImageView user = findViewById(R.id.imageView6);

        user.setOnClickListener(v -> {
            if(isLoggedIn){
                startActivity(new Intent(AccueilActivity.this, UserActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            } else {
                startActivity(new Intent(AccueilActivity.this, ConnexionActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this,
                new OnBackPressedCallback(true) {
                    @Override
                    public void handleOnBackPressed() {
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                }
        );

    }

    private void loadFromJson() {

        try {
            InputStream is = getAssets().open("travel.json");

            int size = is.available();
            byte[] buffer = new byte[size];

            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");

            JSONArray array = new JSONArray(json);

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = array.getJSONObject(i);

                String title = obj.getString("title");
                String imageUrl = obj.getString("image");
                String imageLike = obj.getString("like");

                imageUrls.add(imageUrl);
                imageTitles.add(title);
                imageLikes.add(imageLike);
            }

            runOnUiThread(() -> adapter.notifyDataSetChanged());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}