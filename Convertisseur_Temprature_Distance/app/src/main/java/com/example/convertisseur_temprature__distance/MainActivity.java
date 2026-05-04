package com.example.convertisseur_temprature__distance;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabNavigation;
    private ViewPager2 mainPager;
    private MainPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        tabNavigation = findViewById(R.id.main_tabs);
        mainPager = findViewById(R.id.content_pager);

        // Configuration de l'adaptateur
        pagerAdapter = new MainPagerAdapter(this);
        mainPager.setAdapter(pagerAdapter);

        // Liaison entre les onglets et le ViewPager
        new TabLayoutMediator(tabNavigation, mainPager, (tab, position) -> {
            if (position == 0) {
                tab.setText("Température");
            } else {
                tab.setText("Distance");
            }
        }).attach();

        // Gestion du bouton Retour
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmation();
            }
        });
    }

    private void showExitConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Confirmation")
                .setMessage("Voulez-vous quitter l'application ?")
                .setPositiveButton("Oui", (dialog, which) -> finish())
                .setNegativeButton("Non", null)
                .setCancelable(true)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_exit) {
            showExitConfirmation();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}