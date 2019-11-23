package com.example.sub3expert.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sub3expert.R;
import com.example.sub3expert.utils.FilmData;
import com.example.sub3expert.utils.TvData;

public class TvDetail extends AppCompatActivity {
    ImageView imgTv;
    TextView tvTittle;
    TextView tvDateReleased;
    TextView tvVoteAve;
    TextView tvCount;
    TextView tvBahasa;
    TextView tvdesc;

    private ProgressBar progressBar;
    public static final String EXTRA_TVSHOW = "extra_tvshow";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);

        tvTittle = findViewById(R.id.title_desc_tv_det);
        tvDateReleased = findViewById(R.id.tv_item_realesed_tv_det);
        imgTv = findViewById(R.id.image_desc_tv_det);
        tvVoteAve = findViewById(R.id.tv_item_voteAverege_tv_det);
        tvCount = findViewById(R.id.tv_item_voteCount_tv_det);
        tvBahasa = findViewById(R.id.tv_item_bahasa_film_det);
        tvdesc = findViewById(R.id.desc_det_tv_det);

        progressBar = findViewById(R.id.progress_bar_tv_det);
        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                }
                catch (Exception e){
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TvData tvData = getIntent().getParcelableExtra(EXTRA_TVSHOW);

                        String vote_average = Double.toString(tvData.getVoteAverage());
                        String url_image = "https://image.tmdb.org/t/p/w185" + tvData.getImgtv();

                        tvTittle.setText(tvData.getTitle());
                        tvDateReleased.setText(tvData.getRelease_date());
                        tvBahasa.setText(tvData.getOriginal_language());
                        tvVoteAve.setText(vote_average);
                        tvCount.setText(tvData.getVote_count());
                        tvdesc.setText(tvData.getOverview());
                        Glide.with(TvDetail.this)
                                .load(url_image)
                                .placeholder(R.color.colorPrimary)
                                .into(imgTv);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
