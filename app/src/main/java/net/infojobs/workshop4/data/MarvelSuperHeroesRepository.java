package net.infojobs.workshop4.data;

import com.google.gson.Gson;

import net.infojobs.workshop4.domain.SuperHero;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MarvelSuperHeroesRepository implements SuperHeroesRepository {

    private static final String ENDPOINT = "https://marvel-ij.herokuapp.com/";

    private final OkHttpClient httpClient;
    private final Gson gson;

    public MarvelSuperHeroesRepository() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    @Override

    public List<SuperHero> getAll() {
        Request request = new Request.Builder()
                .get()
                .url(ENDPOINT + "characters")
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String jsonResponse = response.body().string();
            SuperHero[] superHeroes = gson.fromJson(jsonResponse, SuperHero[].class);
            return Arrays.asList(superHeroes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SuperHero getByName(String name) {
        Request request = new Request.Builder()
                .get()
                .url(ENDPOINT + "characters/" + name)
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            String jsonResponse = response.body().string();
            return gson.fromJson(jsonResponse, SuperHero.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
