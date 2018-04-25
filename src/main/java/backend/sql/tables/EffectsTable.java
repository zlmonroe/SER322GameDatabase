package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class EffectsTable extends Table {

    public EffectsTable() {
        super("Effect", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "duration INTEGER",
                        "mana INTEGER",
                        "hp INTEGER",
                        "attack INTEGER",
                        "defense INTEGER",
                        "sight INTEGER",
                        "strength INTEGER",
                        "manaP DECIMAL(8, 4)",
                        "hpP DECIMAL(8, 4)",
                        "attackP DECIMAL(8, 4)",
                        "defenseP DECIMAL(8, 4)",
                        "sightP DECIMAL(8, 4)",
                        "strengthP DECIMAL(8, 4)"}, null,
                new String[] {
                        "'Time Healing', 60, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL",
                        "'Time Damage', 60, NULL, -1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL",
                        "'Percent Healing', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL",
                        "'Percent Damage', 0, NULL, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL, NULL, NULL, NULL",
                        "'Percent Mana Gain', 0, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL",
                        "'Percent Mana Drain', 0, NULL, NULL, NULL, NULL, NULL, NULL, -1, NULL, NULL, NULL, NULL, NULL",
                        "'Time Attack Boost', 60, NULL, NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL",
                        "'Time Attack Drain', 60, NULL, NULL, -1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL",
                        "'Fire', 60, NULL, -5, NULL, NULL, -2, NULL, NULL, NULL, NULL, NULL, NULL, NULL",
                        "'Fight Boost', 60, NULL, NULL, 1, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL"});
    }
}
