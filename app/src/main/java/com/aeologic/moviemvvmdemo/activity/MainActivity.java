package com.aeologic.moviemvvmdemo.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aeologic.moviemvvmdemo.R;
import com.aeologic.moviemvvmdemo.adapter.MovieAdapter;
import com.aeologic.moviemvvmdemo.constant.Constants;
import com.aeologic.moviemvvmdemo.model.Movie;
import com.aeologic.moviemvvmdemo.viewmodel.MovieViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int ADD_MOVIE_ID = 101;
    private static final String TAG = MainActivity.class.getSimpleName();
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private FloatingActionButton addFab;

    private MovieViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG,"onCreate()");
        setContentView(R.layout.activity_main);

        initViews();
        setupToolbar();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final MovieAdapter adapter = new MovieAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        viewModel.getAllMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movieList) {
                adapter.setMovies(movieList);
            }
        });

        handleListener();

    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        addFab = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void handleListener() {
        addFab.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == addFab) {
            Intent intent = new Intent(MainActivity.this, AddMovieActivity.class);
            startActivityForResult(intent, ADD_MOVIE_ID);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MOVIE_ID && resultCode == RESULT_OK) {

            if (data != null) {
                String title = data.getStringExtra(Constants.TITLE);
                String desc = data.getStringExtra(Constants.DESC);
                viewModel.insertMovie(new Movie(title, desc, R.drawable.ic_banner));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_delete_all_movies) {
            viewModel.deleteAllMovies();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        Log.v(TAG,"onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.v(TAG,"onResume()");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.v(TAG,"onPause()");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.v(TAG,"onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.v(TAG,"onDestroy()");
        super.onDestroy();
    }
}
