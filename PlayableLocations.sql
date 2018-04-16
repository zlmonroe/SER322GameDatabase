--Find all locations within 5 levels of the players level.

SELECT L.name
FROM Locations L, playerChar P
WHERE P.name = 'Zach' AND L.avgLevel>= (p.level - 5) AND L.avgLevel <= (P.level + 5)