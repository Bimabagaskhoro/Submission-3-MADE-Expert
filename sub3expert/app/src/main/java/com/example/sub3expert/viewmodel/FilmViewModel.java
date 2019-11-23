package com.example.sub3expert.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sub3expert.utils.FilmData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class FilmViewModel extends ViewModel {
    private static final String API_KEY = "e509d4826aff4c2df9eeb16e6e8d899c";
    private MutableLiveData<ArrayList<FilmData>>listFilmss = new MutableLiveData<>();

    public void  setFilm(final String film){
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<FilmData> listFilmItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+ API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String reeult = new String(responseBody);
                    JSONObject object = new JSONObject(reeult);
                    JSONArray listFlm = object.getJSONArray("results");

                    for (int i = 0; i< listFlm.length();i++){
                        JSONObject filmObject = listFlm.getJSONObject(i);
                        FilmData mData = new FilmData(filmObject);
                        listFilmItem.add(mData);
                    }
                    listFilmss.postValue(listFilmItem);
                } catch (Exception e){
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("Fail", Objects.requireNonNull(error.getMessage()));
            }
        });
    }
    public LiveData<ArrayList<FilmData>>getFilm(){
        return listFilmss;
    }
}
