/*
 * What are the names of all items that have Time Healing
 * effect with a multiplier of at least 5?
 *
 * This script should be run from SER322_DB with GameSchema as
 * the currently selected schema (first in the search path)
*/

SELECT item.name
FROM item , itemeffect
WHERE item.name = itemeffect.item
GROUP BY item.name, effect
HAVING effectmultiplier >= 5 AND effect = 'Time Healing';