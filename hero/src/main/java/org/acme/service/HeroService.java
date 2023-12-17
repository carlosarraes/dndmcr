package org.acme.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.model.Hero;
import org.acme.repository.HeroRepository;

@ApplicationScoped
public class HeroService {
  @Inject HeroRepository heroRepository;

  @Transactional
  public Uni<List<Hero>> findAll() {
    return heroRepository.findAll().list();
  }

  @Transactional
  public Uni<Hero> findById(Long id) {
    return heroRepository.findById(id).onItem().ifNull().failWith(new Exception("Hero not found"));
  }

  @Transactional
  public Uni<Hero> create(Hero hero) {
    return heroRepository.persist(hero);
  }

  @Transactional
  public Uni<Hero> update(Long id, Hero hero) {
    Uni<Hero> toUpdate =
        heroRepository.findById(id).onItem().ifNull().failWith(new Exception("Hero not found"));

    return toUpdate
        .onItem()
        .transform(
            h -> {
              h.name = hero.name;
              h.className = hero.className;
              h.strength = hero.strength;
              h.dexterity = hero.dexterity;
              h.intelligence = hero.intelligence;
              h.life = hero.life;
              return h;
            });
  }

  @Transactional
  public Uni<Void> delete(Long id) {
    Uni<Boolean> deleted =
        heroRepository.deleteById(id).onItem().ifNull().failWith(new Exception("Hero not found"));

    return deleted.onItem().transform(b -> null);
  }
}
