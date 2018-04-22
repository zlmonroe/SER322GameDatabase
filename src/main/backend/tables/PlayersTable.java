package backend.tables;

import backend.SQLActions.Insert;
import backend.SQLActions.SQLAction;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class PlayersTable extends Table {

    public PlayersTable() {
        super("PlayersTable", "username", null, null);
    }

    @Override
    public SQLAction getInsertSQL() {
        return new Insert(this.getTableName(), this.getInserts());
    }

    @Override
    public String getAction() {
        return "CREATE TABLE PLAYERS;";
    }

    @Override
    public String getFunction() {
        return "Create table "+getTableName();
    }
}
