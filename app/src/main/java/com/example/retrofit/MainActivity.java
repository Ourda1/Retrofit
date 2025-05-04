package com.example.retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.retrofit.adapter.UserAdapter;
import com.example.retrofit.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView errorText = findViewById(R.id.errorText);
        SearchView searchView = findViewById(R.id.searchView);

        // Initialisation de l'adapter avec le TextView pour les messages vides
        adapter = new UserAdapter(errorText);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(UserViewModel.class);
        viewModel.getUsers().observe(this, users -> {
            progressBar.setVisibility(View.GONE);
            if (users != null) {
                adapter.setUserList(users);
            }
        });

        // Configuration du SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.filter(newText);
                return true;
            }
        });
    }
}