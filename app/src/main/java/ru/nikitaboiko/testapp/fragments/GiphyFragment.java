package ru.nikitaboiko.testapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikitaboiko.testapp.R;
import ru.nikitaboiko.testapp.adapters.GiphyAdapter;
import ru.nikitaboiko.testapp.services.DataGetter;


public class GiphyFragment extends Fragment {

    @BindView(R.id.fragment_giphy_list)
    RecyclerView imageList;
    @BindView(R.id.fragment_giphy_search_view)
    SearchView searchView;
    @BindView(R.id.fragment_giphy_root)
    View rootView;
    private GiphyAdapter giphyAdapter = new GiphyAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_giphy, container, false);
        ButterKnife.bind(this, inflatedView);
        imageList.setLayoutManager(new LinearLayoutManager(getContext()));
        imageList.setAdapter(giphyAdapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                DataGetter.getInstance().getGiphies(query, giphyAdapter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                return true;
            }
        });
        return inflatedView;
    }
}
