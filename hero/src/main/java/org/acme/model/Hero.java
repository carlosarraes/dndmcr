package org.acme.model;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
@Cacheable
public class Hero extends PanacheEntity {
  public String name;

  @Column(name = "class_name")
  public String className;

  public Integer strength;
  public Integer dexterity;
  public Integer intelligence;
  public Integer life = 100;
}
