--How much money do I and all of my friends have together?

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