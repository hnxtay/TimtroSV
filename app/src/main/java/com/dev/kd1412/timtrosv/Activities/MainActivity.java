package com.dev.kd1412.timtrosv.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.dev.kd1412.timtrosv.View.HomeFragment;
import com.dev.kd1412.timtrosv.View.SearchFragment;
import com.dev.kd1412.timtrosv.R;
import com.dev.kd1412.timtrosv.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    private HomeFragment homeFragment;
    private SearchFragment searchFragment;
    private ActivityMainBinding binding;
    private NavController navController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        navController = Navigation.findNavController(this, R.id.fragment);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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
}