package backend.SQLActions;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class CreateDatabase implements SQLAction {
    private String database;
    private String owner;

    public CreateDatabase(String database, String owner) {
        this.database = database;
        this.owner = owner;
    }

    @Override
    public String getAction() {
        return "CREATE DATABASE SER322_DB\n" +
                "    WITH\n" +
                "    OWNER = "+this.owner+"\n" +
                "    ENCODING = 'UTF8'\n" +
                "    LC_COLLATE = 'C'\n" +
                "    LC_CTYPE = 'C'\n" +
                "    TABLESPACE = pg_default\n" +
                "    CONNECTION LIMIT = -1\n" +
                "    TEMPLATE template0;";
    }

    @Override
    public String getFunction() {
        return "Initialize the database "+ this.database +" from template0 with owner set as " +
                ""+this.owner;
    }
}
