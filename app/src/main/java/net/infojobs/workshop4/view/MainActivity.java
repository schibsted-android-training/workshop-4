package net.infojobs.workshop4.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import net.infojobs.workshop4.R;
import net.infojobs.workshop4.data.SuperHeroesRepositoryFactory;
import net.infojobs.workshop4.domain.SuperHero;
import net.infojobs.workshop4.data.SuperHeroesRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SuperHeroesRepository superHeroesRepository;
    private SuperHeroesAdapter adapter;
    private ContentLoadingProgressBar loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        initializeAdapter();
        initializeViews();

        superHeroesRepository = SuperHeroesRepositoryFactory.create();

        loadSuperHeroes();
    }

    private void loadSuperHeroes() {
        loadingView.show();

        List<SuperHero> superHeroes = superHeroesRepository.getAll();
        adapter.setSuperHeroes(superHeroes);
        loadingView.hide();
    }

    private void initializeAdapter() {
        adapter = new SuperHeroesAdapter(new SuperHeroesAdapter.SuperHeroClickListener() {
            @Override
            public void onSuperHeroClick(SuperHero superHero) {
                openSuperHero(superHero);
            }
        });
    }

    private void initializeViews() {
        loadingView = (ContentLoadingProgressBar) findViewById(R.id.progress_bar);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void openSuperHero(SuperHero superHero) {
        Intent intent = SuperHeroDetailActivity.buildIntent(this, superHero);
        startActivity(intent);
    }
}