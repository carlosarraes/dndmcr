package org.acme.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.model.Monster;
import org.acme.restclients.MonsterClient;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MonsterService {
  @RestClient MonsterClient monsterClient;

  public Uni<Monster> getMonsterById(Long id) {
    return monsterClient.getMonsterById(id);
  }

  @Transactional
  public Uni<Monster> updateMonster(Long id, Monster monster) {
    return monsterClient.updateMonster(id, monster);
  }
}
