/*
 * How much money do I and all of my friends have together?
 *
 * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/
SELECT SUM(money) as TOTALMONEY
FROM (
SELECT name, money
FROM playerchar
WHERE playerchar.player IN (
SELECT friendname
FROM friendswith
WHERE friendswith.username = 'zmonroe')
  OR playerchar.player = 'zmonroe'
GROUP BY name) as PARTYSMONEY;