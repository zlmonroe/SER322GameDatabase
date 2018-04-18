/*
 * DROP TABLES
  * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/

DROP TABLE SKILLEFFECT CASCADE;
DROP TABLE HASSKILL CASCADE;
DROP TABLE CHARACTERQUEST CASCADE;
DROP TABLE DISCOVERED CASCADE;
DROP TABLE ITEMEFFECT;
DROP TABLE HASITEM CASCADE;
DROP TABLE SKILL;
DROP TABLE QUESTLOCATION CASCADE;
DROP TABLE LOCATIONS;
DROP TABLE EFFECT;
DROP TABLE QUESTS CASCADE;
DROP TABLE NONPLAYERCHAR CASCADE;
DROP TABLE PLAYERCHAR CASCADE;
DROP TABLE CHARACTER;
DROP TABLE WEAPON;
DROP TABLE ARMOR;
DROP TABLE CONSUMABLE;
DROP TABLE ITEM CASCADE;
DROP TABLE FRIENDSWITH;
DROP TABLE PLAYERS CASCADE;
DROP SCHEMA GameSchema;