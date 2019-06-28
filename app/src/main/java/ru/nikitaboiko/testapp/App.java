package ru.nikitaboiko.testapp;

import android.app.Application;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.nikitaboiko.testapp.services.GiphyApi;

public class App extends Application {
    private static App instance;
    private static GiphyApi giphyApi;

    public static App instance() {
        return instance;
    }

    public static GiphyApi getApi() {
        return giphyApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        createRetrofitInstance();
        Log.i("DDLog", "startService(): Done!");
    }

    private void createRetrofitInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.giphy.com/v1/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        giphyApi = retrofit.create(GiphyApi.class);
    }
}
