package ru.nikitaboiko.testapp.fragments;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.nikitaboiko.testapp.R;

public class BrowserFragment extends Fragment {
    @BindView(R.id.fragment_browser_web_view)
    WebView webView;
    @BindView(R.id.fragment_browser_search)
    EditText search;
    @BindView(R.id.fragment_browser_button_back)
    ImageButton buttonBack;
    @BindView(R.id.fragment_browser_button_forward)
    ImageButton buttonForward;
    @BindView(R.id.fragment_browser_button_refresh)
    ImageButton buttonRefresh;
    @BindView(R.id.fragment_browser_button_go)
    ImageButton buttonGo;
    @BindView(R.id.fragment_browser_progress_bar)
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflatedView = inflater.inflate(R.layout.fragment_browser, container, false);
        ButterKnife.bind(this, inflatedView);
        webView.setWebChromeClient(new MyWebChromeClient());
        webView.setWebViewClient(new MyWebViewClient());
        buttonGo.setOnClickListener(v -> loadSite());
        buttonRefresh.setOnClickListener(v -> loadSite());
        buttonBack.setOnClickListener(v -> {
            if (webView.canGoBack()) {
                webView.goBack();
            }
        });
        buttonForward.setOnClickListener(v -> {
            if (webView.canGoForward()) {
                webView.goForward();
            }
        });
        search.setOnKeyListener((v, keyCode, event) -> {
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                loadSite();
            }
            return false;
        });
        loadSite();
        return inflatedView;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadSite();
    }

    private void loadSite() {
        if (!search.getText().toString().isEmpty()) {
            if (search.getText().toString().startsWith("http")) {
                webView.loadUrl(search.getText().toString());
            } else {
                webView.loadUrl("http://" + search.getText().toString());
            }
        }
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            search.setText(url);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }



    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            progressBar.setProgress(newProgress, true);
        }
    }

}
