package com.example.simpleui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private Button downloadButton;

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
        imageView = findViewById(R.id.imageView);
        downloadButton = findViewById(R.id.downloadButton);
        downloadButton.setOnClickListener(new
                                                  View.OnClickListener() {
                                                      @Override
                                                      public void onClick(View v) {
                                                          String imageUrl =
                                                                  "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQykUKbHv4ruhdGJFoibGSHsTobb4-fVfXUkw&s";
                                                          new
                                                                  ImageDownloader(imageView).downloadImage(imageUrl);
                                                      }
                                                  });
    }
}