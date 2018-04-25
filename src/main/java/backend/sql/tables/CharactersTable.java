package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class CharactersTable extends Table {

    public CharactersTable() {
        super("Character", "id",
                new String[] {
                        "id INTEGER NOT NULL"}, null,
                new String[] {
                        "235223",
                        "535416",
                        "15684",
                        "654159",
                        "654168",
                        "985146",
                        "93527",
                        "23673",
                        "75487",
                        "74782",
                        "153724",
                        "628247",
                        "89626"});
    }
}
