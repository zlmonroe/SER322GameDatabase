/*
 * Find all of the quests my friends are doing so I can join them.
 *
 * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/
SELECT DISTINCT S.nameF, CQ.quest
FROM characterquest AS CQ,
    ((SELECT F.nameF
    FROM friendswith AS F(username, nameF)
    WHERE F.username= 'vedgar' )
        UNION
    (SELECT F.nameF
    FROM friendswith AS F(nameF, friendname)
    WHERE F.friendname = 'vedgar')) AS S,
    playerchar AS C
WHERE CQ.playercharacter = C.name AND (C.player = S.nameF);