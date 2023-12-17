INSERT INTO monster 
  (id, race, strength, dexterity, intelligence, is_alive, is_boss, life)
VALUES 
  (1, 'Goblin', 5, 3, 1, true, false, 100),
  (2, 'Troll', 6, 6, 2, true, false, 100),
  (3, 'Orc', 13, 5, 1, true, false, 150),
  (4, 'Giant', 20, 4, 4, true, false, 200),
  (5, 'Dragon', 25, 10, 15, true, false, 250),
  (6, 'Demon', 30, 15, 20, true, false, 300),
  (7, 'Vampire', 35, 20, 25, true, false, 350),
  (8, 'Werewolf', 40, 25, 30, true, false, 400),
  (9, 'Golem', 45, 30, 35, true, false, 450),
  (10, 'Death', 50, 35, 40, true, true, 500);
ALTER SEQUENCE monster_seq RESTART WITH 11;
