package net.infojobs.workshop4.data;

import net.infojobs.workshop4.domain.SuperHero;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;


public interface MarvelApi {

    @GET("/characters")
    List<SuperHero> getAll();

    @GET("/characters/{name}")
    SuperHero getByName(@Path("name") String name);

}
