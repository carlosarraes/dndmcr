INSERT INTO hero 
  (id, name, class_name, strength, dexterity, intelligence, life) 
VALUES 
  (1, 'Carl', 'Knight', 8, 4, 3, 100),
  (2, 'John', 'Mage', 3, 4, 8, 100),
  (3, 'Peter', 'Ranger', 4, 8, 3, 100),
  (4, 'Mark', 'Priest', 3, 3, 8, 100),
  (5, 'Paul', 'Warrior', 8, 3, 4, 100),
  (6, 'Luke', 'Rogue', 4, 8, 3, 100),
  (7, 'Matthew', 'Druid', 3, 4, 8, 100),
  (8, 'James', 'Paladin', 8, 3, 4, 100),
  (9, 'Simon', 'Barbarian', 4, 3, 8, 100),
  (10, 'Judas', 'Assassin', 3, 8, 4, 100);
ALTER SEQUENCE hero_seq RESTART WITH 11;
