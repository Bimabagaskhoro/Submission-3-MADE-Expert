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
import com.example.sub3expert.detail.TvDetail;
import com.example.sub3expert.utils.TvData;

import java.util.ArrayList;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.TvViewHolder> {
    private ArrayList<TvData> tvData = new ArrayList<>();
    public void setTvData (ArrayList<TvData>itemData){
        tvData.clear();
        tvData.addAll(itemData);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_tv,parent,false);
        return new TvViewHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int position) {
        holder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final ImageView imgtv;
        final TextView tvTittle;
        final TextView tvDateReleased;
        final TextView tvVoteAve;
        final TextView tvCount;
        final TextView tvBahasa;
        final TextView tvdesc;
        TvViewHolder(@NonNull View itemView) {
            super(itemView);
            imgtv = itemView.findViewById(R.id.img_poster_tv);
            tvTittle = itemView.findViewById(R.id.tv_item_title_tv);
            tvDateReleased = itemView.findViewById(R.id.tv_item_realesed_tv);
            tvVoteAve = itemView.findViewById(R.id.tv_item_voteAverege_tv);
            tvCount = itemView.findViewById(R.id.tv_item_voteCount_tv);
            tvBahasa = itemView.findViewById(R.id.tv_item_bahasa_tv);
            tvdesc = itemView.findViewById(R.id.tv_item_description_tv);
        }
        void bind(TvData tvData){
            String vote_average = Double.toString(tvData.getVoteAverage());
            String imgUrl = "https://image.tmdb.org/t/p/w185" + tvData.getImgtv();
            tvVoteAve.setText(vote_average);
            tvCount.setText(tvData.getVote_count());
            tvTittle.setText(tvData.getTitle());
            tvDateReleased.setText(tvData.getRelease_date());
            tvdesc.setText(tvData.getOverview());
            tvBahasa.setText(tvData.getOriginal_language());
            Glide.with(itemView.getContext())
                    .load(imgUrl)
                    .placeholder(R.color.colorgrey)
                    .dontAnimate()
                    .into(imgtv);
        }
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TvData tvDataa = tvData.get(position);
//
            tvDataa.setTitle(tvDataa.getTitle());
            tvDataa.setOverview(tvDataa.getOverview());

            Intent intent = new Intent(itemView.getContext(), TvDetail.class);
            intent.putExtra(TvDetail.EXTRA_TVSHOW, tvData);
            itemView.getContext().startActivity(intent);
        }
    }
}
