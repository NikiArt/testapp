package ru.nikitaboiko.testapp.services;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nikitaboiko.testapp.App;
import ru.nikitaboiko.testapp.adapters.GiphyAdapter;
import ru.nikitaboiko.testapp.services.parsingJson.DataGiphy;
import ru.nikitaboiko.testapp.services.parsingJson.ResponseGiphy;

public class DataGetter {
    private final static String API_KEY = "k7p8MsvOsCYXaUSKBPxLGMUs0xbSUKIo";
    private static DataGetter instance;
    private final List<String> imageUrls = new ArrayList<>();

    private DataGetter() {
    }

    public static synchronized DataGetter getInstance() {
        if (instance == null) {
            instance = new DataGetter();
        }
        return instance;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void getGiphies(String request, GiphyAdapter giphyAdapter) {
        imageUrls.clear();
        Call<ResponseGiphy> call = App.getApi().getGiphies(API_KEY, request);
        call.enqueue(new Callback<ResponseGiphy>() {
            @Override
            public void onResponse(Call<ResponseGiphy> call, Response<ResponseGiphy> response) {
                if (response.body() != null) {
                    for (DataGiphy dataImg : response.body().getData()) {
                        imageUrls.add(dataImg.getImages().getFixed_height().getUrl());
                    }
                    giphyAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseGiphy> call, Throwable t) {
                Log.d("DDLog", "failure - " + t);
            }
        });
    }
}
