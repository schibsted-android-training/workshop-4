package net.infojobs.workshop4.data;

import net.infojobs.workshop4.domain.SuperHero;

import java.util.List;

public interface SuperHeroesRepository {
    List<SuperHero> getAll();

    SuperHero getByName(String name);
}
