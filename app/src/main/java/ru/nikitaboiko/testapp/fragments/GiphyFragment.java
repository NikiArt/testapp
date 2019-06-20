package ru.nikitaboiko.testapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.nikitaboiko.testapp.App;
import ru.nikitaboiko.testapp.R;
import ru.nikitaboiko.testapp.services.parsingJson.ResponseGiphy;


public class GiphyFragment extends Fragment {
    private final static String API_KEY = "k7p8MsvOsCYXaUSKBPxLGMUs0xbSUKIo";
    @BindView(R.id.fragment_giphy_search_view)
    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_giphy, container, false);
        ButterKnife.bind(this, inflatedView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                Toast.makeText(getContext(), "DONE " + query, Toast.LENGTH_SHORT).show();
                getGiphies(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                return true;
            }
        });
        return inflatedView;
    }

    private void getGiphies(String request) {
        Call<ResponseGiphy> call = App.getApi().getGiphies(API_KEY, request);
        call.enqueue(new Callback<ResponseGiphy>() {

            @Override
            public void onResponse(Call<ResponseGiphy> call, Response<ResponseGiphy> response) {
                if (response.body() != null) {
                    System.out.println("done");
                }
            }

            @Override
            public void onFailure(Call<ResponseGiphy> call, Throwable t) {

            }
        });
    }
}
