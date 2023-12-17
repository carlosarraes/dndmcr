package org.acme.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class Monster extends PanacheEntity {
  public String race;
  public Integer strength;
  public Integer dexterity;
  public Integer intelligence;

  @Column(name = "is_alive")
  public Boolean isAlive = true;

  @Column(name = "is_boss")
  public Boolean isBoss = false;

  public Integer life = isBoss ? 200 : 100;
}
