package com.example.simpleui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        String param1 = "First";
        String param2 = "Second";
        String param3 = "Fragment";
// Create the first fragment
        FirstFragment firstFragment =
                FirstFragment.newInstance(param1, param3);
// Create the second fragment
        FirstFragment secondFragment =
                FirstFragment.newInstance(param2, param3);
// Add the fragments to their respective containers
        FragmentManager fragmentManager =
                getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container1,
                firstFragment);
        fragmentTransaction.add(R.id.fragment_container2,
                secondFragment);
        fragmentTransaction.commit();
    }
}