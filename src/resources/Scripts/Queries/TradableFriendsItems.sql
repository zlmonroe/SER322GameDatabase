/*
 * What are all of the items my friends have that I do not?
 *
 * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/

SELECT DISTINCT S.nameF, HI.item
FROM hasitem AS HI,
    ((SELECT F.nameF
    FROM friendswith AS F(username, nameF)
    WHERE F.username = 'vedgar' )
        UNION
    (SELECT F.nameF
    FROM friendswith AS F(nameF, friendname)
    WHERE F.friendname = 'vedgar')) AS S,
    playerchar AS C,
    "character" AS CI
WHERE CI.id = C.charid AND (C.player = S.nameF) AND CI.id = HI."character"
    AND HI.item NOT IN
        (SELECT HI2.item
        FROM hasitem AS HI2,
            playerchar AS C2,
            "character" AS CI2
        WHERE CI2.id = C2.charid AND
            CI2.id = HI2."character" AND
            (C2.player = 'vedgar'));