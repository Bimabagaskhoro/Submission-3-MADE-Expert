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

public class DetailFilm extends AppCompatActivity {
    ImageView imgFilm;
    TextView tvTittle;
    TextView tvDateReleased;
    TextView tvVoteAve;
    TextView tvCount;
    TextView tvBahasa;
    TextView tvdesc;

    private ProgressBar progressBar;
    public static final String EXTRA_FILM = "extra_film";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_film);

        tvTittle = findViewById(R.id.title_desc_film_det);
        tvDateReleased = findViewById(R.id.tv_item_realesed_film_det);
        imgFilm = findViewById(R.id.image_desc_film_det);
        tvVoteAve = findViewById(R.id.tv_item_voteAverege_det);
        tvCount = findViewById(R.id.tv_item_voteCount_det);
        tvBahasa = findViewById(R.id.tv_item_bahasa_film_det);
        tvdesc = findViewById(R.id.desc_det_film_det);

        progressBar = findViewById(R.id.progress_bar_film_det);
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
                        FilmData filmData = getIntent().getParcelableExtra(EXTRA_FILM);

                        String vote_average = Double.toString(filmData.getVoteAverage());
                        String url_image = "https://image.tmdb.org/t/p/w185" + filmData.getImgFilm();

                        tvTittle.setText(filmData.getTitle());
                        tvDateReleased.setText(filmData.getRelease_date());
                        tvBahasa.setText(filmData.getOriginal_language());
                        tvVoteAve.setText(vote_average);
                        tvCount.setText(filmData.getVote_count());
                        tvdesc.setText(filmData.getOverview());
                        Glide.with(DetailFilm.this)
                                .load(url_image)
                                .placeholder(R.color.colorPrimary)
                                .into(imgFilm);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
