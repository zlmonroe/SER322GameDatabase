package backend.sql.tables;

public class ItemsTable extends Table {

    public ItemsTable() {
        super("Items", "name",
                new String[]{
                        "name VARCHAR(30) NOT NULL",
                        "weight INTEGER NOT NULL",
                        "effectMultiplier DECIMAL(8,3)"}, null,
                new String[]{
                        "'Iron Sword', 15, 0",
                        "'Fire Sword', 12, 5",
                        "'Stone Arrow', 1, 2",
                        "'Iron Arrow', 2, 4",
                        "'Emerald Arrow', 3, 8",
                        "'Poison Bow', 5, 1.5",
                        "'Leather Armor', 12, 0",
                        "'Light Iron Armor', 18, 0",
                        "'Iron Armor', 24, 0",
                        "'Robe', 5, 50",
                        "'Iron Armor of the Gods', 24, 30",
                        "'Leather Armor of Healing', 12, 2",
                        "'Healing Potion', 4, 5",
                        "'Instant Health', 4, 30",
                        "'Supser Instant Health', 6, 50",
                        "'Mana Regeneration', 4, 5",
                        "'Poison Apple', 3, 5",
                        "'Rat on a Stick', 3, 15"});
    }
}
