package com.andrea.fragments.gui;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.andrea.fragments.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container, new FirstFragment())
                    .commit();
        }

        btn1.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new FirstFragment())
                .addToBackStack(null) // Permette di tornare indietro col tasto fisico
                .commit();
        });

        btn2.setOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SecondFragment())
                .addToBackStack(null)
                .commit();
        });
    }

    /*
    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
    */
}