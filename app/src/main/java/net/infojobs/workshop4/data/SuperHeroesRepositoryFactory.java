package net.infojobs.workshop4.data;

public class SuperHeroesRepositoryFactory {

    public static SuperHeroesRepository create() {
        return new FakeSuperHeroesRepository();
    }

}
