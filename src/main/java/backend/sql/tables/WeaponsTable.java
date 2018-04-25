package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class WeaponsTable extends Table {

    public WeaponsTable() {
        super("Weapon", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "damage INTEGER"},
                new LinkedHashMap<String,String>() {
                    {
                        put("name", "ITEMS(name)");
                    }
                },
                new String[] {
                        "'Iron Sword', 12",
                        "'Fire Sword', 8",
                        "'Stone Arrow', 5",
                        "'Iron Arrow', 8",
                        "'Emerald Arrow', 11",
                        "'Poison Bow', 2"});
    }
}
