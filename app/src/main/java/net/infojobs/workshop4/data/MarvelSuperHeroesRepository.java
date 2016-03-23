package net.infojobs.workshop4.data;

import net.infojobs.workshop4.domain.SuperHero;

import java.util.List;

import retrofit.RestAdapter;

public class MarvelSuperHeroesRepository implements SuperHeroesRepository {

    private static final String ENDPOINT = "https://marvel-ij.herokuapp.com/";

    private final MarvelApi marvelApi;

    public MarvelSuperHeroesRepository() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .build();
        marvelApi = restAdapter.create(MarvelApi.class);
    }

    @Override
    public List<SuperHero> getAll() {
        return marvelApi.getAll();
    }

    @Override
    public SuperHero getByName(String name) {
        return marvelApi.getByName(name);
    }
}
