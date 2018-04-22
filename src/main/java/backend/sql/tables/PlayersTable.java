package backend.sql.tables;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class PlayersTable extends Table {

    public PlayersTable() {
        super("Players", "username",
                new String[] {
                        "username VARCHAR(20) NOT NULL",
                        "password VARCHAR(20) NOT NULL",
                        "startDate DATE NOT NULL",
                        "balance DECIMAL(12, 2) NOT NULL"}, null,
                new String[] {
                        "'tcuprak', 'timCuprak', '06/14/17', 40.01",
                        "'acastaneda', 'alexCastaneda', '02/06/16', 43",
                        "'vedgar', 'vatriciaEdgar', '09/30/17', 61",
                        "'zmonroe', 'zacharyMonroe', '09/30/17', 123456789.99",
                        "'jbush', 'jonBush', '01/06/18', 16.1"});
    }
}
