package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class ConsumableTable extends Table {

    public ConsumableTable() {
        super("Consumable", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "coolDown INTEGER"},
                        new LinkedHashMap<String,String>() {
                        {
                            put("name", "ITEMS(name)");
                        }
                },
                new String[] {
                        "'Healing Potion', 5",
                        "'Instant Health', 8",
                        "'Supser Instant Health', 8",
                        "'Mana Regeneration', 5",
                        "'Poison Apple', 10",
                        "'Rat on a Stick', 10"});
    }
}
