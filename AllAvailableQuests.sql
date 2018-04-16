--Find all available quests in the areas the player has found.

SELECT Q.name
FROM Quests Q
WHERE Q.Name IN
    (Select Q2.Quest
    FROM QuestLocation Q2
    WHERE Q2.Location IN
        (SELECT L.Location
        FROM Discovered L
        WHERE L.playerCharacter = 'Zach'));