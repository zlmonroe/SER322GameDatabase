/*
 * Find all available quests in the areas the player has found.
 *
 * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/
SELECT Q.name
FROM Quest Q
WHERE Q.Name IN
    (Select Q2.Quest
    FROM QuestLocation Q2
    WHERE Q2.Location IN
        (SELECT L.Location
        FROM Discovered L
        WHERE L.playerCharacter = 'Zach'));