package net.infojobs.workshop4.view;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import net.infojobs.workshop4.R;
import net.infojobs.workshop4.data.SuperHeroesRepositoryFactory;
import net.infojobs.workshop4.domain.SuperHero;
import net.infojobs.workshop4.data.SuperHeroesRepository;

public class SuperHeroDetailActivity extends AppCompatActivity {

    private static final String SUPER_HERO_NAME_KEY = "super_hero_name_key";

    private ImageView superHeroPhotoImageView;
    private TextView superHeroNameTextView;
    private TextView superHeroDescriptionTextView;
    private ContentLoadingProgressBar loadingView;
    private FloatingActionButton favoriteFAB;
    private SuperHeroesRepository superHeroesRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_hero_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getSuperHeroName());
        initializeViews();

        superHeroesRepository = SuperHeroesRepositoryFactory.create();

        loadSuperHero();
    }

    private void loadSuperHero() {
        new AsyncTask<Void, Void, SuperHero>() {
            @Override
            protected void onPreExecute() {
                loadingView.show();
            }

            @Override
            protected SuperHero doInBackground(Void... params) {
                return superHeroesRepository.getByName(getSuperHeroName());
            }

            @Override
            protected void onPostExecute(SuperHero superHero) {
                showSuperHero(superHero);
                loadingView.hide();
            }
        }.execute();
    }

    public void showSuperHero(SuperHero superHero) {
        Picasso.with(this).load(superHero.getPhoto()).fit().centerCrop().into(superHeroPhotoImageView);
        superHeroNameTextView.setText(superHero.getName());
        superHeroDescriptionTextView.setText(superHero.getDescription());
    }

    private String getSuperHeroName() {
        return getIntent().getExtras().getString(SUPER_HERO_NAME_KEY);
    }

    private void initializeViews() {
        superHeroPhotoImageView = (ImageView) findViewById(R.id.iv_super_hero_photo);
        superHeroNameTextView = (TextView) findViewById(R.id.tv_super_hero_name);
        superHeroDescriptionTextView = (TextView) findViewById(R.id.tv_super_hero_description);
        loadingView = (ContentLoadingProgressBar) findViewById(R.id.progress_bar);
        favoriteFAB = (FloatingActionButton) findViewById(R.id.fab_super_hero_favorite);
        favoriteFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SuperHeroDetailActivity.this, "Favorite!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent buildIntent(Context context, SuperHero superHero) {
        Intent intent = new Intent(context, SuperHeroDetailActivity.class);
        intent.putExtra(SUPER_HERO_NAME_KEY, superHero.getName());
        return intent;
    }
}
