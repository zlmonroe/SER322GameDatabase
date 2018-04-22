package backend.SQLActions;

/**
 * Created by zlmonroe on 4/21/2018.
 *
 * Generates an SQLAction which contains the code to drop the current database
 * and a description of this functionality
 */
public class DropDatabase implements SQLAction {
    private String database;

    public DropDatabase(String database) {
        this.database = database;
    }

    @Override
    public String getAction() {
        return "DROP DATABASE " + database + ";\n";
    }

    @Override
    public String getFunction() {
        return "Drop the database " + database;
    }
}
