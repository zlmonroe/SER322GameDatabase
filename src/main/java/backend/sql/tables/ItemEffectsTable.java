package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class ItemEffectsTable extends Table {

    public ItemEffectsTable() {
        super("ItemEffect", "item, effect",
                new String[] {
                        "item VARCHAR(30) NOT NULL",
                        "effect VARCHAR(30) NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("item","ITEM(name)");
                        put("effect","EFFECT(name)");
                    }
                },
                new String[] {
                        "'Fire Sword','Fire'",
                        "'Poison Bow', 'Time Damage'",
                        "'Iron Armor of the Gods', 'Fight Boost'",
                        "'Leather Armor of Healing', 'Time Healing'",
                        "'Healing Potion', 'Time Healing'",
                        "'Instant Health', 'Percent Healing'",
                        "'Supser Instant Health', 'Percent Healing'",
                        "'Mana Regeneration', 'Percent Mana Gain'",
                        "'Poison Apple', 'Time Damage'",
                        "'Rat on a Stick', 'Percent Healing'"});
    }
}
