package org.acme.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import org.acme.model.Monster;
import org.acme.repository.MonsterRepository;

@ApplicationScoped
public class MonsterService {
  @Inject MonsterRepository monsterRepository;

  @Transactional
  public Uni<List<Monster>> findAll() {
    return monsterRepository.findAll().list();
  }

  @Transactional
  public Uni<Monster> findById(Long id) {
    return monsterRepository
        .findById(id)
        .onItem()
        .ifNull()
        .failWith(new Exception("Monster not found"));
  }

  @Transactional
  public Uni<Monster> create(Monster monster) {
    return monsterRepository.persist(monster);
  }

  @Transactional
  public Uni<Monster> update(Long id, Monster monster) {
    Uni<Monster> toUpdate =
        monsterRepository
            .findById(id)
            .onItem()
            .ifNull()
            .failWith(new Exception("Monster not found"));

    return toUpdate
        .onItem()
        .transform(
            m -> {
              m.race = monster.race;
              m.strength = monster.strength;
              m.dexterity = monster.dexterity;
              m.intelligence = monster.intelligence;
              m.isAlive = monster.isAlive;
              m.life = monster.life;
              return m;
            });
  }

  @Transactional
  public Uni<Void> delete(Long id) {
    Uni<Boolean> deleted =
        monsterRepository
            .deleteById(id)
            .onItem()
            .ifNull()
            .failWith(new Exception("Monster not found"));

    return deleted.onItem().transform(b -> null);
  }
}
