package com.example.travel;

import android.app.Activity;
import android.content.Intent;
import android.view.*;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> imageUrls;
    private List<String> imageTitles;
    private List<String> imageLikes;
    private List<String> imageLats;
    private List<String> imageLngs;
    private List<String> imageDates;
    private List<List<String[]>> imageComs;

    public ImageAdapter(List<String> imageUrls, List<String> imageTitles, List<String> imageLikes, List<String> imageLats, List<String> imageLngs, List<String> imageDates, List<List<String[]>> imageComs) {
        this.imageUrls = imageUrls;
        this.imageTitles = imageTitles;
        this.imageLikes = imageLikes;
        this.imageLats = imageLats;
        this.imageLngs = imageLngs;
        this.imageDates = imageDates;
        this.imageComs = imageComs;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String imageUrl = imageUrls.get(position);
        String title = imageTitles.get(position);
        String likes = imageLikes.get(position);
        String lats = imageLats.get(position);
        String lngs = imageLngs.get(position);
        String dates = imageDates.get(position);

        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.imageView);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("image_url", imageUrl);
            intent.putExtra("image_title", title);
            intent.putExtra("image_likes", likes);
            intent.putExtra("image_lats", lats);
            intent.putExtra("image_lngs", lngs);
            intent.putExtra("image_dates", dates);
            intent.putExtra("image_position", position);

            v.getContext().startActivity(intent);

            Activity activity = (Activity) v.getContext();
            activity.overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
            );
        });
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }
}