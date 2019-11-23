package com.example.sub3expert.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.sub3expert.CustomClikcListener;
import com.example.sub3expert.R;
import com.example.sub3expert.adapter.TvAdapter;
import com.example.sub3expert.detail.TvDetail;
import com.example.sub3expert.utils.TvData;
import com.example.sub3expert.viewmodel.TvViewModel;

import java.util.ArrayList;

public class TvMovieFragment extends Fragment {
    private TvAdapter tvAdapter;
    private ProgressBar progressBar;
    private TvViewModel tvViewModel;
    RecyclerView recyclerView;

    public TvMovieFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_movie,container,false);

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvAdapter = new TvAdapter();
        recyclerView = view.findViewById(R.id.rv_tvMovie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(tvAdapter);

        progressBar = view.findViewById(R.id.progress_bar_tv);

        tvViewModel = ViewModelProviders.of(this).get(TvViewModel.class);
        tvViewModel.getTv().observe(this, gettvv);
        tvViewModel.setTv("EXTRA_TVshow");

        loading(true);

    }

    private Observer<ArrayList<TvData>>gettvv =new Observer<ArrayList<TvData>>() {
        @Override
        public void onChanged(ArrayList<TvData> tvData) {
            if (tvData !=null){
                tvAdapter.setTvData(tvData);
                loading(false);
                CustomClikcListener.addTo(recyclerView).setOnItemClickListener((recyclerView, position, v)->
                        showSelectRv(tvData.get(position)));
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
    private void showSelectRv(TvData tvData){
        Intent intent = new Intent(getActivity(), TvDetail.class);
        intent.putExtra(TvDetail.EXTRA_TVSHOW,tvData);
        startActivity(intent);
    }
}
