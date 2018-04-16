--How much attack and defense do all of my friends
-- and I have combined (for fighting a raid together)?

SELECT SUM(attack) as TOTALATTACK, sum(defense) as TOTALDEFENSE
FROM (
SELECT name, attack, defense
FROM playerchar
WHERE playerchar.player IN (
SELECT friendname
FROM friendswith
WHERE friendswith.username = 'zmonroe')
  OR playerchar.player = 'zmonroe'
GROUP BY name) as PARTYSattack;