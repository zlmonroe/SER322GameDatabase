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
                        "'Camelot', 1, 1000000, 50, 'Iron Sword', 'Seyyid Mamuka'",
                        "'Castle Rock', 2, 5000, 25, 'Mana Regeneration', 'Shungoun Dain'",
                        "'Mordor', 3, 23000, 30, 'Supser Instant Health', 'Dougal Archibald'",
                        "'NO WAY JOSE', 4, 50000, 45, 'Stone Arrow', 'Alexandre Uilleam'",
                        "'Mother Dearest', 5, 32000, 32, 'Stone Arrow', 'Neilina Gus'",
                        "'Kill Bill', 6, 120000, 11, 'Fire Sword', 'Eoghan Colin'"});
    }
}
