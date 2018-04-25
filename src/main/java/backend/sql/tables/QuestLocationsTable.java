package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class QuestLocationsTable extends Table {

    public QuestLocationsTable() {
        super("QuestLocation", "quest, location",
                new String[] {
                        "quest VARCHAR(30) NOT NULL",
                        "location VARCHAR(30) NOT NULL"},
                new LinkedHashMap<String,String>(){
                        {
                            put("quest","QUESTS(name)");
                            put("location","LOCATIONS(name)");
                        }
                },
                new String[] {
                        "'Camelot','The Forest of Wisdom'",
                        "'Castle Rock', 'The Forest of Wisdom'",
                        "'Mordor', 'Shimmering Lake'",
                        "'NO WAY JOSE', 'The Friendly Tavern'",
                        "'Mother Dearest', 'The Friendly Tavern'",
                        "'Kill Bill','The Forest of Wisdom' ",
                        "'Castle Rock', 'The Spire of Zunor'",
                        "'Mordor', 'The Cave under Shimmering Lake'",
                        "'Mother Dearest', 'The Forest of Wisdom'",
                        "'Kill Bill', 'The Basin'",
                        "'Mordor', 'The Spire of Zunor'",
                        "'Mother Dearest', 'The Basin'",
                        "'Kill Bill', 'Shimmering Lake'",
                        "'Mordor', 'The Dark Forest'",
                        "'Mother Dearest', 'Rolling Thunder'",
                        "'Kill Bill', 'Rolling Thunder'"});
    }
}
