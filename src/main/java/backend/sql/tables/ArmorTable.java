package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class ArmorTable extends Table {

    public ArmorTable() {
        super("Armor", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "defense INTEGER"},
                new LinkedHashMap<String,String>() {
                    {
                        put("name", "ITEM(name)");
                    }
                },
                new String[] {
                        "'Leather Armor', 6",
                        "'Light Iron Armor', 10",
                        "'Iron Armor', 14",
                        "'Robe', 4",
                        "'Iron Armor of the Gods', 14",
                        "'Leather Armor of Healing', 6"});
    }
}
