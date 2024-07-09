package com.example.simpleui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private FloatingActionButton fab;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main
        ), (v, insets) -> {
            Insets systemBars =
                    insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top,
                    systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.fab);
        apiService = ApiClient.getApiService();
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this));
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this,
                    AddPostActivity.class);
            startActivity(intent);
        });
        fetchPosts();
    }

    private void fetchPosts() {
        apiService.getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call,
                                   Response<List<Post>> response) {
                if (response.isSuccessful() && response.body()
                        != null) {
                    postAdapter = new
                            PostAdapter(MainActivity.this, response.body(), new
                            PostAdapter.OnItemClickListener() {
                                @Override
                                public void onEditClick(Post post) {
// Handle edit post
                                }

                                @Override
                                public void onDeleteClick(Post post) {
// Handle delete post
                                }
                            });
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call,
                                  Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch posts", Toast.LENGTH_SHORT).show();
            }
        });
    }
}