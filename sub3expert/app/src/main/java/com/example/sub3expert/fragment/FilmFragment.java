package com.example.sub3expert.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sub3expert.CustomClikcListener;
import com.example.sub3expert.R;
import com.example.sub3expert.adapter.FilmAdapter;
import com.example.sub3expert.detail.DetailFilm;
import com.example.sub3expert.utils.FilmData;
import com.example.sub3expert.viewmodel.FilmViewModel;

import java.util.ArrayList;


public class FilmFragment extends Fragment {
    private FilmAdapter filmAdapter;
    private ProgressBar progressBar;
    private FilmViewModel filmViewModel;
    private RecyclerView recyclerView;

    public FilmFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_film,container,false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        filmAdapter = new FilmAdapter();
        recyclerView = view.findViewById(R.id.rv_film);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(filmAdapter);

        progressBar = view.findViewById(R.id.progress_bar_film);

        filmViewModel = ViewModelProviders.of(this).get(FilmViewModel.class);
        filmViewModel.getFilm().observe(this, getFilmss);
        filmViewModel.setFilm("EXTRA_FILM");

        loading(true);
    }


    private Observer<ArrayList<FilmData>> getFilmss = new Observer<ArrayList<FilmData>>() {
        @Override
        public void onChanged(final ArrayList<FilmData> filmData) {
            if (filmData != null) {
                filmAdapter.setFilmData(filmData);
                loading(false);
                CustomClikcListener.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v)->
                        showSelectRv(filmData.get(position)));
            }
        }
    };
    private void loading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        }else {
            progressBar.setVisibility(View.GONE);
        }
    }
    private void showSelectRv(FilmData filmData){
        Intent intent = new Intent(getActivity(), DetailFilm.class);
        intent.putExtra(DetailFilm.EXTRA_FILM,filmData);
        startActivity(intent);
    }
}
