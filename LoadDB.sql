/*
 * Load the database SER322_DB with Schema, Tables, and Examples
 * Run this script from the postgres user with a connection to SER322_DB
 */
CREATE SCHEMA GameSchema;
SET SEARCH_PATH TO GameSchema;

CREATE TABLE PLAYERS (
	username VARCHAR(20) NOT NULL,
	password VARCHAR(20) NOT NULL,
	startDate DATE NOT NULL,
	balance DECIMAL(12, 2) NOT NULL,
	PRIMARY KEY(username)
);

INSERT INTO PLAYERS(username, password, startDate, balance) VALUES
('tcuprak', 'timCuprak', '06/14/17', 40.01),
('acastaneda', 'alexCastaneda', '02/06/16', 43),
('vedgar', 'vatriciaEdgar', '09/30/17', 61),
('zmonroe', 'zacharyMonroe', '09/30/17', 123456789.99),
('jbush', 'jonBush', '01/06/18', 16.1);

CREATE TABLE FRIENDSWITH (
	username VARCHAR(20) NOT NULL REFERENCES PLAYERS(username),
	friendName VARCHAR(20) NOT NULL REFERENCES PLAYERS(username) CHECK(friendName <> username),
	PRIMARY KEY(username, friendName)
);

INSERT INTO FRIENDSWITH(username, friendName) VALUES
('tcuprak', 'jbush'),
('tcuprak', 'zmonroe'),
('tcuprak', 'vedgar'),
('acastaneda', 'zmonroe'),
('acastaneda', 'vedgar'),
('vedgar', 'tcuprak'),
('vedgar', 'acastaneda'),
('vedgar', 'zmonroe'),
('vedgar', 'jbush'),
('zmonroe', 'tcuprak'),
('zmonroe', 'acastaneda'),
('zmonroe', 'vedgar'),
('zmonroe', 'jbush'),
('jbush', 'tcuprak'),
('jbush', 'vedgar'),
('jbush', 'zmonroe');

CREATE TABLE ITEM (
	name VARCHAR(30) NOT NULL,
	weight INTEGER NOT NULL,
	effectMultiplier DECIMAL(8,3),
	PRIMARY KEY(name)
);

INSERT INTO ITEM VALUES
    ('Iron Sword', 15, 0),
    ('Fire Sword', 12, 5),
    ('Stone Arrow', 1, 2),
    ('Iron Arrow', 2, 4),
    ('Emerald Arrow', 3, 8),
    ('Poison Bow', 5, 1.5),
    ('Leather Armor', 12, 0),
    ('Light Iron Armor', 18, 0),
    ('Iron Armor', 24, 0),
    ('Robe', 5, 50),
    ('Iron Armor of the Gods', 24, 30),
    ('Leather Armor of Healing', 12, 2),
    ('Healing Potion', 4, 5),
    ('Instant Health', 4, 30),
    ('Supser Instant Health', 6, 50),
    ('Mana Regeneration', 4, 5),
    ('Poison Apple', 3, 5),
    ('Rat on a Stick', 3, 15);

CREATE TABLE CONSUMABLE (
	name VARCHAR(30) NOT NULL REFERENCES ITEM(name),
	coolDown INTEGER,
	PRIMARY KEY(name)
);

INSERT INTO CONSUMABLE VALUES
    ('Healing Potion', 5),
    ('Instant Health', 8),
    ('Supser Instant Health', 8),
    ('Mana Regeneration', 5),
    ('Poison Apple', 10),
    ('Rat on a Stick', 10);

CREATE TABLE ARMOR (
	name VARCHAR(30) NOT NULL REFERENCES ITEM(name),
	defense INTEGER,
	PRIMARY KEY(name)
);

INSERT INTO ARMOR VALUES
    ('Leather Armor', 6),
    ('Light Iron Armor', 10),
    ('Iron Armor', 14),
    ('Robe', 4),
    ('Iron Armor of the Gods', 14),
    ('Leather Armor of Healing', 6);

CREATE TABLE WEAPON (
	name VARCHAR(30) NOT NULL REFERENCES ITEM(name),
	damage INTEGER,
	PRIMARY KEY(name)
);

INSERT INTO WEAPON VALUES
    ('Iron Sword', 12),
    ('Fire Sword', 8),
    ('Stone Arrow', 5),
    ('Iron Arrow', 8),
    ('Emerald Arrow', 11),
    ('Poison Bow', 2);

CREATE TABLE CHARACTER (
	id INTEGER NOT NULL,
	PRIMARY KEY(id)
);

INSERT INTO CHARACTER(id) VALUES
(235223),
(535416),
(15684),
(654159),
(654168),
(985146),
(93527),
(23673),
(75487),
(74782),
(153724),
(628247),
(89626);

CREATE TABLE PLAYERCHAR (
	name VARCHAR(30) NOT NULL,
	money DECIMAL(12, 2) NOT NULL,
	mana INTEGER NOT NULL,
	hp INTEGER NOT NULL,
	attack INTEGER NOT NULL,
	defense INTEGER NOT NULL,
	sight INTEGER NOT NULL,
	level INTEGER NOT NULL,
	speed INTEGER NOT NULL,
	xp INTEGER NOT NULL,
	maxCarryWeight INTEGER NOT NULL,
	charID INTEGER NOT NULL REFERENCES CHARACTER(id),
	player VARCHAR(20) NOT NULL REFERENCES PLAYERS(username),
	PRIMARY KEY(name)
);

INSERT INTO PLAYERCHAR(name, player, money, mana, hp, attack, defense, sight, level, speed, xp, maxCarryWeight, charID) VALUES
('Tim', 'tcuprak', 24523, 526, 6415, 10, 100, 20, 30, 5, 54325, 150, 235223),
('Tim2', 'tcuprak', 542, 300, 800, 3, 20, 10, 12, 6, 642, 100, 535416),
('Alex', 'acastaneda', 5658424, 1530, 15562, 20, 130, 40, 100, 10, 9881123, 200, 15684),
('Vatrica', 'vedgar', 74156, 1026, 8510, 19, 80, 30, 34, 6, 971, 130, 654159),
('Zach', 'zmonroe', 12342, 248, 1068, 4, 80, 10, 7, 5, 378, 100, 654168),
('Jon', 'jbush', 67, 243, 362, 3, 12, 10, 5, 5, 32, 100, 985146);

CREATE TABLE NONPLAYERCHAR (
	name VARCHAR(30) NOT NULL,
	money DECIMAL(12, 2) NOT NULL,
	mana INTEGER,
	hp INTEGER,
	attack INTEGER,
	defense INTEGER,
	sight INTEGER,
	level INTEGER,
	speed INTEGER,
	spawnFrequency INTEGER NOT NULL,
	aggro INTEGER NOT NULL,
	charID INTEGER NOT NULL REFERENCES CHARACTER(id),
	PRIMARY KEY(name)
);

INSERT INTO NONPLAYERCHAR (name, money, mana, hp, attack, defense,
 sight, level, speed, spawnFrequency, aggro, charID) VALUES
('Seyyid Mamuka', 112.12, 100, 100, 3, 30, 532, 643, 24, 246, 754, 93527),
('Shungoun Dain', 120.12, 100, 100, 23, 84, 7540, 730, 430, 436, 6, 23673),
('Dougal Archibald', 16315.83, 100, 2, 73, 357, 357, 357, 34, 123, 60, 75487),
('Alexandre Uilleam', 8312.23, 100, 100, 72, 40, 80, 60, 60, 60, 6, 74782),
('Neilina Gus', 2362.21, 100, 100, 71, 10, 10, 10, 10, 10, 10, 153724),
('Eoghan Colin', 2, 100, 10, 100, 72, 10, 5, 626, 672, 327, 628247),
('Alberte Mungo', .01, 100, 100, 63163, 635322, 1, 10000, 1000, 1000, 10000, 89626);

CREATE TABLE QUESTS (
	name VARCHAR(30) NOT NULL,
	minLevel INTEGER NOT NULL,
	moneyReward DECIMAL(12,2) NOT NULL,
	xpReward INTEGER NOT NULL,
	itemReward VARCHAR(30) REFERENCES ITEM(name),
	npc VARCHAR(20) REFERENCES NONPLAYERCHAR(name),
	PRIMARY KEY(name)
);

INSERT INTO QUESTS (Name, MinLevel, MoneyReward, XPReward, NPC, ItemReward) VALUES
('Camelot', 1, 1000000, 50, 'Seyyid Mamuka', 'Iron Sword'),
('Castle Rock', 2, 5000, 25, 'Shungoun Dain', 'Mana Regeneration'),
('Mordor', 3, 23000, 30, 'Dougal Archibald', 'Supser Instant Health'),
('NO WAY JOSE', 4, 50000, 45, 'Alexandre Uilleam', 'Stone Arrow'),
('Mother Dearest', 5, 32000, 32, 'Neilina Gus', 'Stone Arrow'),
('Kill Bill', 6, 120000, 11, 'Eoghan Colin', 'Fire Sword');

CREATE TABLE EFFECT (
	name VARCHAR(30) NOT NULL,
	duration INTEGER,

	--Absolute increases
	mana INTEGER,
	hp INTEGER,
	attack INTEGER,
	defense INTEGER,
	sight INTEGER,
	strength INTEGER,

	--Relative increases
	manaP DECIMAL(8, 4),
	hpP DECIMAL(8, 4),
	attackP DECIMAL(8, 4),
	defenseP DECIMAL(8, 4),
	sightP DECIMAL(8, 4),
	strengthP DECIMAL(8, 4),

	PRIMARY KEY(name)
);

INSERT INTO EFFECT(name, duration, hp) VALUES
    ('Time Healing', 60, 1),
    ('Time Damage', 60, -1);

INSERT INTO EFFECT(name, duration, hpP) VALUES
    ('Percent Healing', 0, 1),
    ('Percent Damage', 0, -1);

INSERT INTO EFFECT(name, duration, manaP) VALUES
    ('Percent Mana Gain', 0, 1),
    ('Percent Mana Drain', 0, -1);

INSERT INTO EFFECT(name, duration, attack) VALUES
    ('Time Attack Boost', 60, 1),
    ('Time Attack Drain', 60, -1);

INSERT INTO EFFECT(name, duration, hp, sight) VALUES
    ('Fire', 60, -5, -2);

INSERT INTO EFFECT(name, duration, attack, defense) VALUES
    ('Fight Boost', 60, 1, 1);

CREATE TABLE LOCATIONS(
	name VARCHAR(30) NOT NULL,
	baseAggro INTEGER NOT NULL,
	canTP BOOLEAN NOT NULL,
	avgLevel INTEGER NOT NULL,
	terrain VARCHAR(50) NOT NULL,
  PRIMARY KEY(name)
);

INSERT INTO LOCATIONS VALUES
    ('The Friendly Tavern', 0, false, 0, 'INTERRIOR'),
    ('The Dark Forest', 80, true, 50, 'FOREST'),
    ('The Forest of Wisdom', 5, true, 1, 'FOREST'),
    ('The Basin', 15, false, 5, 'DESERT'),
    ('Shimmering Lake', 20, true, 8, 'LAKE'),
    ('The Cave under Shimmering Lake', 30, false, 12, 'CAVERN'),
    ('The Spire of Zunor', 50, false, 25, 'TOWER'),
    ('Rolling Thunder', 30, true, 15, 'HILLS');

CREATE TABLE QUESTLOCATION(
	quest VARCHAR(30) NOT NULL REFERENCES QUESTS(name),
	location VARCHAR(30) NOT NULL REFERENCES LOCATIONS(name),
	PRIMARY KEY(quest, location)
);

INSERT INTO QUESTLOCATION VALUES
    ('Camelot','The Forest of Wisdom'),
    ('Castle Rock', 'The Forest of Wisdom'),
    ('Mordor', 'Shimmering Lake'),
    ('NO WAY JOSE', 'The Friendly Tavern'),
    ('Mother Dearest', 'The Friendly Tavern'),
    ('Kill Bill','The Forest of Wisdom' ),
    ('Castle Rock', 'The Spire of Zunor'),
    ('Mordor', 'The Cave under Shimmering Lake'),
    ('Mother Dearest', 'The Forest of Wisdom'),
    ('Kill Bill', 'The Basin'),
    ('Mordor', 'The Spire of Zunor'),
    ('Mother Dearest', 'The Basin'),
    ('Kill Bill', 'Shimmering Lake'),
    ('Mordor', 'The Dark Forest'),
    ('Mother Dearest', 'Rolling Thunder'),
    ('Kill Bill', 'Rolling Thunder');

CREATE TABLE SKILL (
	name VARCHAR(20) NOT NULL,
	level INTEGER NOT NULL,
	coolDown INTEGER,
	manaCost INTEGER NOT NULL,
	PRIMARY KEY(name)
);

INSERT INTO SKILL (Name, Level, CoolDown, ManaCost) VALUES
('Berzerk', 5, 6, 6),
('Endulge', 2, 1, 2),
('Rally', 4, 4, 4),
('Preach', 3, 1, 2),
('Sneak', 6, 2, 4),
('Sprint', 2, 7, 1),
('Infect', 7, 4, 10);


CREATE TABLE HASITEM (
	character INTEGER NOT NULL REFERENCES CHARACTER(id),
	item VARCHAR(30) NOT NULL REFERENCES ITEM(name),
	PRIMARY KEY(character, item)
);

INSERT INTO HASITEM VALUES
    (235223, 'Iron Sword'),
    (235223, 'Rat on a Stick'),
    (535416, 'Fire Sword'),
    (535416, 'Rat on a Stick'),
    (15684, 'Stone Arrow'),
    (15684, 'Iron Arrow'),
    (15684, 'Emerald Arrow'),
    (15684, 'Poison Bow'),
    (15684, 'Rat on a Stick'),
    (654159, 'Leather Armor'),
    (654159, 'Rat on a Stick'),
    (654159, 'Iron Sword'),
    (654168, 'Light Iron Armor'),
    (985146, 'Light Iron Armor'),
    (23673, 'Robe'),
    (75487, 'Supser Instant Health'),
    (74782, 'Poison Apple'),
    (153724, 'Rat on a Stick'),
    (628247, 'Iron Armor'),
    (628247, 'Iron Sword'),
    (628247, 'Rat on a Stick'),
    (89626, 'Fire Sword'),
    (89626, 'Light Iron Armor'),
    (89626, 'Rat on a Stick');

CREATE TABLE ITEMEFFECT (
	effect VARCHAR(30) NOT NULL REFERENCES EFFECT(name),
	item VARCHAR(30) NOT NULL REFERENCES ITEM(name),
	PRIMARY KEY(effect, item)
);

INSERT INTO ITEMEFFECT(item, effect) VALUES
    ('Fire Sword','Fire'),
    ('Poison Bow', 'Time Damage'),
    ('Iron Armor of the Gods', 'Fight Boost'),
    ('Leather Armor of Healing', 'Time Healing'),
    ('Healing Potion', 'Time Healing'),
    ('Instant Health', 'Percent Healing'),
    ('Supser Instant Health', 'Percent Healing'),
    ('Mana Regeneration', 'Percent Mana Gain'),
    ('Poison Apple', 'Time Damage'),
    ('Rat on a Stick', 'Percent Healing');

CREATE TABLE DISCOVERED (
	playerCharacter VARCHAR(30) NOT NULL REFERENCES PLAYERCHAR(name),
	location VARCHAR(30) NOT NULL REFERENCES LOCATIONS(name),
	PRIMARY KEY(playerCharacter, location)
);

INSERT INTO DISCOVERED VALUES
    ('Tim', 'The Friendly Tavern'),
    ('Tim2', 'The Friendly Tavern'),
    ('Alex', 'The Friendly Tavern'),
    ('Vatrica', 'The Friendly Tavern'),
    ('Zach', 'The Friendly Tavern'),
    ('Jon', 'The Friendly Tavern'),
    ('Tim', 'The Forest of Wisdom'),
    ('Tim2','The Forest of Wisdom'),
    ('Alex', 'The Forest of Wisdom'),
    ('Vatrica', 'The Forest of Wisdom'),
    ('Zach', 'The Forest of Wisdom'),
    ('Jon', 'The Forest of Wisdom'),
    ('Tim', 'The Basin'),
    ('Tim2', 'Shimmering Lake'),
    ('Alex','The Spire of Zunor' ),
    ('Vatrica', 'The Dark Forest'),
    ('Zach', 'The Basin'),
    ('Tim', 'The Cave under Shimmering Lake'),
    ('Tim2', 'The Cave under Shimmering Lake'),
    ('Alex', 'The Dark Forest'),
    ('Vatrica', 'The Spire of Zunor');

CREATE TABLE CHARACTERQUEST (
	playerCharacter VARCHAR(30) NOT NULL REFERENCES PLAYERCHAR(name),
	quest VARCHAR(30) NOT NULL REFERENCES QUESTS(name),
	status INTEGER NOT NULL,
	PRIMARY KEY(playerCharacter, quest)
);

INSERT INTO CHARACTERQUEST(quest, playerCharacter, status) VALUES
('Camelot','Alex',100),
('Castle Rock','Vatrica',85),
('NO WAY JOSE','Zach',89),
('Mother Dearest','Alex',100),
('Mordor','Tim2',100),
('Mordor','Vatrica',100),
('Mordor','Jon',50),
('Kill Bill','Tim',23),
('Kill Bill','Jon',100),
('Kill Bill','Alex',75);

CREATE TABLE HASSKILL (
	character INTEGER NOT NULL REFERENCES CHARACTER(id),
	skill VARCHAR(20) NOT NULL REFERENCES SKILL(name),
	PRIMARY KEY(character, skill)
);

INSERT INTO HASSKILL (Character, Skill) VALUES
(235223, 'Berzerk'),
(153724, 'Rally'),
(89626, 'Sneak'),
(654168, 'Sprint'),
(93527, 'Infect'),
(23673, 'Preach'),
(535416, 'Endulge');

CREATE TABLE SKILLEFFECT (
	skill VARCHAR(20) NOT NULL REFERENCES SKILL(name),
	effect VARCHAR(30) NOT NULL REFERENCES EFFECT(name),
	PRIMARY KEY(skill, effect)
);

INSERT INTO SKILLEFFECT (effect, skill) VALUES
('Time Damage', 'Sprint'),
('Percent Healing', 'Preach'),
('Percent Mana Drain', 'Infect'),
('Percent Mana Gain', 'Endulge'),
('Fight Boost', 'Berzerk'),
('Time Attack Boost','Rally'),
('Time Healing', 'Sneak');