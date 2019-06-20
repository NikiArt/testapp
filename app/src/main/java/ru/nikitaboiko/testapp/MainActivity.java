package ru.nikitaboiko.testapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import ru.nikitaboiko.testapp.fragments.BrowserFragment;
import ru.nikitaboiko.testapp.fragments.GiphyFragment;

public class MainActivity extends AppCompatActivity {
    private TextView mTextMessage;
    private final Fragment giphyFragment = new GiphyFragment();
    private final Fragment browserFragment = new BrowserFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_giphy:
                    changeFragment(1);
                    return true;
                case R.id.menu_browser:
                    changeFragment(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        mTextMessage = findViewById(R.id.message);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, giphyFragment);
        fragmentTransaction.commit();
    }

    private void changeFragment(int fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, getCurrentFragment(fragment));
        fragmentTransaction.commit();

    }

    private Fragment getCurrentFragment(int fragmentNumber) {
        Fragment currentFragment;
        switch (fragmentNumber) {
            case 1:
                currentFragment = giphyFragment;
                break;
            case 2:
                currentFragment = browserFragment;
                break;
            default:
                currentFragment = giphyFragment;
                break;
        }
        return currentFragment;
    }
}
