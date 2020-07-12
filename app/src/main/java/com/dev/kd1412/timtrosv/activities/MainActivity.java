package com.dev.kd1412.timtrosv.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.dev.kd1412.timtrosv.views.HomeFragment;
import com.dev.kd1412.timtrosv.views.SearchFragment;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, NavController.OnDestinationChangedListener {
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ActivityMainBinding binding;
    public  NavController navController;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.fragment);

        bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        navController.addOnDestinationChangedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                navController.navigate(R.id.navigation_home);
                break;
            case R.id.navigation_news:
                navController.navigate(R.id.navigation_news);
                break;
            case R.id.navigation_search:
                navController.navigate(R.id.navigation_search);
                break;
            case R.id.navigation_profile:
                navController.navigate(R.id.navigation_profile);
                break;
        }
        return true;
    }


    public void setFragment(int id) {
        navController.navigate(id);
    }

    @Override
    public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
        switch (destination.getId()) {
            case R.id.uploadFragmentStep1:
            case R.id.uploadFragmentStep2:
            case R.id.uploadFragmentStep3:
                bottomNavigationView.setVisibility(BottomNavigationView.GONE);
                break;

            default:
                bottomNavigationView.setVisibility(View.VISIBLE);
                break;
        }
    }
}