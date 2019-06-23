package ru.nikitaboiko.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import ru.nikitaboiko.testapp.R;
import ru.nikitaboiko.testapp.services.DataGetter;

public class GiphyAdapter extends RecyclerView.Adapter<GiphyAdapter.MyViewHolder> {
    private Context context;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String url = DataGetter.getInstance().getImageUrls().get(position);
        holder.text.setText(new Integer(position + 1).toString());
        holder.updateImage(url);
    }

    @Override
    public int getItemCount() {
        return DataGetter.getInstance().getImageUrls().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.item_image);
            text = view.findViewById(R.id.item_position_label);
        }

        protected void updateImage(String url) {
            Glide
                    .with(context)
                    .load(url)
                    .into(image);
        }
    }
}
