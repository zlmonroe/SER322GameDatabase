package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class PlayerCharsTable extends Table {

    public PlayerCharsTable() {
        super("PlayerChar", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "player VARCHAR(20) NOT NULL",
                        "money DECIMAL(12, 2) NOT NULL",
                        "mana INTEGER NOT NULL",
                        "hp INTEGER NOT NULL",
                        "attack INTEGER NOT NULL",
                        "defense INTEGER NOT NULL",
                        "sight INTEGER NOT NULL",
                        "level INTEGER NOT NULL",
                        "speed INTEGER NOT NULL",
                        "xp INTEGER NOT NULL",
                        "maxCarryWeight INTEGER NOT NULL",
                        "charID INTEGER NOT NULL"},
                new LinkedHashMap<String, String>() {
                    {
                        put("charID", "CHARACTER(id)");
                        put("player", "PLAYERS(username)");
                    }
                },
                new String[] {
                        "'Tim', 'tcuprak', 24523, 526, 6415, 10, 100, 20, 30, 5, 54325, 150, 235223",
                        "'Tim2', 'tcuprak', 542, 300, 800, 3, 20, 10, 12, 6, 642, 100, 535416",
                        "'Alex', 'acastaneda', 5658424, 1530, 15562, 20, 130, 40, 100, 10, 9881123, 200, 15684",
                        "'Vatrica', 'vedgar', 74156, 1026, 8510, 19, 80, 30, 34, 6, 971, 130, 654159",
                        "'Zach', 'zmonroe', 12342, 248, 1068, 4, 80, 10, 7, 5, 378, 100, 654168",
                        "'Jon', 'jbush', 67, 243, 362, 3, 12, 10, 5, 5, 32, 100, 985146"});
    }
}
