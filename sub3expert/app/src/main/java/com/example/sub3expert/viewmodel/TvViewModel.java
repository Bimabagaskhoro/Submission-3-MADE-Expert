package com.example.sub3expert.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.sub3expert.utils.TvData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

import cz.msebera.android.httpclient.Header;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = "e509d4826aff4c2df9eeb16e6e8d899c";
    private MutableLiveData<ArrayList<TvData>> listtvv = new MutableLiveData<>();

    public void  setTv(final String tvShow) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<TvData> listTvItem = new ArrayList<>();

        String url = "https://api.themoviedb.org/3/discover/tv?api_key=" + API_KEY + "&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String reeult = new String(responseBody);
                    JSONObject object = new JSONObject(reeult);
                    JSONArray array = object.getJSONArray("results");

                    for (int i = 0; i< array.length();i++){
                        JSONObject object1 = array.getJSONObject(i);
                        TvData mData = new TvData(object1);
                        listTvItem.add(mData);
                    }
                    listtvv.postValue(listTvItem);
                }catch (Exception e){
                    Log.d("Exception", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Log.d("onFailure", Objects.requireNonNull(error.getMessage()));
            }
        });
    }
    public LiveData<ArrayList<TvData>> getTv(){
        return listtvv;
    }
}
