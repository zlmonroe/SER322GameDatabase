package backend.sql.tables;

        import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class QuestsTable extends Table {

    public QuestsTable() {
        super("Quests", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "minLevel INTEGER NOT NULL",
                        "moneyReward DECIMAL(12,2) NOT NULL",
                        "xpReward INTEGER NOT NULL",
                        "itemReward VARCHAR(30)",
                        "npc VARCHAR(30)"},
                new LinkedHashMap<String, String>() {
                    {
                        put("itemReward", "ITEM(name)");
                        put("npc", "NONPLAYERCHAR(name)");
                    }
                },
                new String[] {
                        "'Camelot', 1, 1000000, 50, 'Seyyid Mamuka', 'Iron Sword'",
                        "'Castle Rock', 2, 5000, 25, 'Shungoun Dain', 'Mana Regeneration'",
                        "'Mordor', 3, 23000, 30, 'Dougal Archibald', 'Supser Instant Health'",
                        "'NO WAY JOSE', 4, 50000, 45, 'Alexandre Uilleam', 'Stone Arrow'",
                        "'Mother Dearest', 5, 32000, 32, 'Neilina Gus', 'Stone Arrow'",
                        "'Kill Bill', 6, 120000, 11, 'Eoghan Colin', 'Fire Sword'"});
    }
}
