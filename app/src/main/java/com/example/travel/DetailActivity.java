package com.example.travel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView titleView = findViewById(R.id.detailTitle);
        TextView likesView = findViewById(R.id.detailTitle2);

        String url = getIntent().getStringExtra("image_url");
        String title = getIntent().getStringExtra("image_title");
        String likes = getIntent().getStringExtra("image_likes");

        Glide.with(this)
                .load(url)
                .into(imageView);

        if (title != null) {
            titleView.setText(title);
        } else {
            titleView.setText("Photo de voyage");
        }



        likesView.setText(likes);

        ImageView fleche = findViewById(R.id.imageView7);

        fleche.setOnClickListener(v -> {
            startActivity(new Intent(DetailActivity.this, AccueilActivity.class));
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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
}
