package com.example.travel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.net.CookieManager;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.detailImage);
        TextView titleView = findViewById(R.id.detailTitle);
        TextView likesView = findViewById(R.id.detailTitle2);
        TextView coordView = findViewById((R.id.detailTitle3));
        TextView comsView = findViewById(R.id.detailTitle5);
        Button infos = findViewById(R.id.button12);
        Button comsBtn = findViewById(R.id.button11);

        String url = getIntent().getStringExtra("image_url");
        String title = getIntent().getStringExtra("image_title");
        String likes = getIntent().getStringExtra("image_likes");
        String lat = getIntent().getStringExtra("image_lats");
        String lng = getIntent().getStringExtra("image_lngs");
        String date = getIntent().getStringExtra("image_dates");
        String coms = getIntent().getStringExtra("image_coms");

        int position = getIntent().getIntExtra("image_position", 0);

        List<String[]> comments = AccueilActivity.imageComs.get(position);

        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();

        for (String[] c : comments) {
            sb.append("<b>").append(c[1]).append("</b>")
                    .append(" ")
                    .append(c[0])
                    .append("<br><br>");
        }

        Glide.with(this)
                .load(url)
                .into(imageView);

        if (title != null) {
            titleView.setText(title);
        } else {
            titleView.setText("Photo de voyage");
        }

        likesView.setText(likes);

        infos.setOnClickListener(v -> {

            comsView.setText("");

            String text =
                    "<b>Lat. :</b> " + lat +
                            " | <b>Long. :</b> " + lng +
                            "<br><br><b>Date :</b> " + date;

            coordView.setText(android.text.Html.fromHtml(text));
        });

        comsBtn.setOnClickListener(v -> {
            coordView.setText("");
            comsView.setText(android.text.Html.fromHtml(sb.toString()));
        });

        String text =
                "<b>Lat. :</b> " + lat +
                        " | <b>Long. :</b> " + lng +
                        "<br><br><b>Date :</b> " + date;

        coordView.setText(android.text.Html.fromHtml(text));

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
