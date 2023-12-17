package org.acme.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Map;
import org.acme.model.Hero;
import org.acme.model.Monster;

@ApplicationScoped
public class AdventureService {
  @Inject HeroService heroService;
  @Inject MonsterService monsterService;

  @Transactional
  public Uni<Map<String, String>> fight(Long heroId, Long monsterId) {
    Uni<Hero> heroUni = heroService.findById(heroId);
    Uni<Monster> monsterUni = monsterService.getMonsterById(monsterId);

    return Uni.combine()
        .all()
        .unis(heroUni, monsterUni)
        .asTuple()
        .onItem()
        .transformToUni(
            tuple -> {
              Hero hero = tuple.getItem1();
              Monster monster = tuple.getItem2();

              int heroHit = Math.max(Math.max(hero.strength, hero.dexterity), hero.intelligence);
              if (monster.isBoss) {
                heroHit /= 2;
              }

              final int hit = heroHit;
              monster.life -= hit;

              return monsterService
                  .updateMonster(monsterId, monster)
                  .onItem()
                  .transform(
                      updatedMonster ->
                          Map.of(
                              "message",
                              "Hero "
                                  + hero.name
                                  + " hit monster "
                                  + updatedMonster.race
                                  + " for "
                                  + hit
                                  + " damage"));
            });
  }
}
