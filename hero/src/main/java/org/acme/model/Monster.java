package org.acme.model;

public class Monster {
  public Long id;
  public String race;
  public Integer strength;
  public Integer dexterity;
  public Integer intelligence;

  public Boolean isAlive = true;

  public Boolean isBoss = false;

  public Integer life = isBoss ? 200 : 100;
}
