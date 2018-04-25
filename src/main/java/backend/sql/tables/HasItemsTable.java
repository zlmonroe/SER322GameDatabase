package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class HasItemsTable extends Table {

    public HasItemsTable() {
        super("HasItem", "character, item",
                new String[] {
                        "character INTEGER NOT NULL",
                        "item VARCHAR(30) NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("character","CHARACTER(id)");
                        put("item","ITEM(name)");
                    }
                },
                new String[] {
                        "235223, 'Iron Sword'",
                        "235223, 'Rat on a Stick'",
                        "535416, 'Fire Sword'",
                        "535416, 'Rat on a Stick'",
                        "15684, 'Stone Arrow'",
                        "15684, 'Iron Arrow'",
                        "15684, 'Emerald Arrow'",
                        "15684, 'Poison Bow'",
                        "15684, 'Rat on a Stick'",
                        "654159, 'Leather Armor'",
                        "654159, 'Rat on a Stick'",
                        "654159, 'Iron Sword'",
                        "654168, 'Light Iron Armor'",
                        "985146, 'Light Iron Armor'",
                        "23673, 'Robe'",
                        "75487, 'Supser Instant Health'",
                        "74782, 'Poison Apple'",
                        "153724, 'Rat on a Stick'",
                        "628247, 'Iron Armor'",
                        "628247, 'Iron Sword'",
                        "628247, 'Rat on a Stick'",
                        "89626, 'Fire Sword'",
                        "89626, 'Light Iron Armor'",
                        "89626, 'Rat on a Stick'"});
    }
}
