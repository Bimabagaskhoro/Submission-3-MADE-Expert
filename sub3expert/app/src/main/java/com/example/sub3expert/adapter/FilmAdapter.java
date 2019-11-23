package com.example.sub3expert.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sub3expert.R;
import com.example.sub3expert.detail.DetailFilm;
import com.example.sub3expert.utils.FilmData;

import java.util.ArrayList;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {

    private ArrayList<FilmData>mData = new ArrayList<>();
    public void setFilmData (ArrayList<FilmData>itemData){
        mData.clear();
        mData.addAll(itemData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_film,parent,false);
        return new FilmViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class FilmViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgFilm;
        final TextView tvTittle;
        final TextView tvDateReleased;
        final TextView tvVoteAve;
        final TextView tvCount;
        final TextView tvBahasa;
        final TextView tvdesc;


        FilmViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.img_poster_film);
            tvTittle = itemView.findViewById(R.id.tv_item_title_film);
            tvDateReleased = itemView.findViewById(R.id.tv_item_realesed_film);
            tvVoteAve = itemView.findViewById(R.id.tv_item_voteAverege);
            tvCount = itemView.findViewById(R.id.tv_item_voteCount);
            tvBahasa = itemView.findViewById(R.id.tv_item_bahasa_film);
            tvdesc = itemView.findViewById(R.id.tv_item_description_film);
        }

        void bind(FilmData filmData) {
            String voteAve = Double.toString(filmData.getVoteAverage());
            String imgUrl = "https://image.tmdb.org/t/p/w185" + filmData.getImgFilm();
            tvVoteAve.setText(voteAve);
            tvCount.setText(filmData.getVote_count());
            tvTittle.setText(filmData.getTitle());
            tvDateReleased.setText(filmData.getRelease_date());
            tvdesc.setText(filmData.getOverview());
            tvBahasa.setText(filmData.getOriginal_language());
            Glide.with(itemView.getContext())
                    .load(imgUrl)
                    .placeholder(R.color.colorgrey)
                    .dontAnimate()
                    .into(imgFilm);
        }

    }
}
