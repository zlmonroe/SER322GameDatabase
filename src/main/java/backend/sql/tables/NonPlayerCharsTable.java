package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class NonPlayerCharsTable extends Table {

    public NonPlayerCharsTable() {
        super("NonPlayerChars", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "money DECIMAL(12, 2) NOT NULL",
                        "mana INTEGER",
                        "hp INTEGER",
                        "attack INTEGER",
                        "defense INTEGER",
                        "sight INTEGER",
                        "level INTEGER",
                        "speed INTEGER",
                        "spawnFrequency INTEGER NOT NULL",
                        "aggro INTEGER NOT NULL",
                        "charID INTEGER NOT NULL"},
                new LinkedHashMap<String, String>() {
                    {
                        put("charID", "CHARACTER(id)");
                    }
                },
                new String[] {
                        "'Seyyid Mamuka', 112.12, 100, 100, 3, 30, 532, 643, 24, 246, 754, 93527",
                        "'Shungoun Dain', 120.12, 100, 100, 23, 84, 7540, 730, 430, 436, 6, 23673",
                        "'Dougal Archibald', 16315.83, 100, 2, 73, 357, 357, 357, 34, 123, 60, 75487",
                        "'Alexandre Uilleam', 8312.23, 100, 100, 72, 40, 80, 60, 60, 60, 6, 74782",
                        "'Neilina Gus', 2362.21, 100, 100, 71, 10, 10, 10, 10, 10, 10, 153724",
                        "'Eoghan Colin', 2, 100, 10, 100, 72, 10, 5, 626, 672, 327, 628247",
                        "'Alberte Mungo', .01, 100, 100, 63163, 635322, 1, 10000, 1000, 1000, 10000, 89626"});
    }
}