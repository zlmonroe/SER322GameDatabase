package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class CharacterQuestsTable extends Table {

    public CharacterQuestsTable() {
        super("CharacterQuest", "playerCharacter, quest",
                new String[] {
                        "playerCharacter VARCHAR(30) NOT NULL",
                        "quest VARCHAR(30) NOT NULL",
                        "status INTEGER NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("playerCharacter","PLAYERCHAR(name)");
                        put("quest","QUESTS(name)");
                    }
                },
                new String[] {
                        "'Camelot','Alex',100",
                        "'Castle Rock','Vatrica',85",
                        "'NO WAY JOSE','Zach',89",
                        "'Mother Dearest','Alex',100",
                        "'Mordor','Tim2',100",
                        "'Mordor','Vatrica',100",
                        "'Mordor','Jon',50",
                        "'Kill Bill','Tim',23",
                        "'Kill Bill','Jon',100",
                        "'Kill Bill','Alex',75"});
    }
}
