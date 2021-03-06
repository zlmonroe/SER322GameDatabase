package backend.sql;

import backend.sql.SQLActions.CreateDatabase;
import backend.sql.SQLActions.DropContents;
import backend.sql.SQLActions.GeneralQuery;
import backend.sql.SQLActions.JoinSearchQuery;
import backend.sql.SQLActions.ListTables;
import backend.sql.SQLActions.LoadDatabase;
import backend.sql.SQLActions.SQLAction;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 * This is the main class of the backend. It represents the SQL server
 * and provides an execute and connect method as well as a constructor.
 */
public class GameServer {
    private Connection connection;
    private String database;
    private String schema;

    /**
     * Constrcutor for any Postgres setup
     * @param database The database name you want to use
     * @param schema The schema name you want to use
     * @param hostname The hostname the Postgres server can be found at
     * @param port The port number of the Postgres server
     * @param user The username for the Postgres server
     * @param password The password for the Postgres server
     */
    public GameServer(String database, String schema, String hostname,
                      Integer port, String user, String password) throws SQLException {

        this.schema = schema;
        this.database = database;

        //If connection to Postgres fails or the table is incomplete, recreate it.
        if(!this.connect(hostname, port, user, password, database)) {
            System.out.println("Could not connect to the database, creating it instead.");
            this.connect(hostname, port, user, password, user);
            this.execute(new CreateDatabase(database, user));
            this.connection.close();
            this.connect(hostname, port, user, password, database);
        }
        if(!this.alreadyCreated()) {
            System.out.println("Missing tables were found in the database. Repopulating.");
            this.execute(new DropContents(database, schema));;
            this.connect(hostname, port, user, password, database);
            this.execute(new LoadDatabase(schema));
        }
        else {
            this.execute(new SQLAction() {
                @Override
                public String getAction() {
                    return "SET SEARCH_PATH = "+schema+";";
                }

                @Override
                public String getFunction() {
                    return "set the schema";
                }
            });
            System.out.println("All tables were found, using existing configuration.");
        }
    }

    /**
     * Simple constructor for default Postgres setup
     * @param password The password for the Postgres server
     */
    public GameServer(String password) throws SQLException {
        this("ser322", "gameschema", "127.0.0.1",
                5432, "postgres", password);
    }

    /**
     * Initiates connection to the server at the given hostname and port
     * with the given database name.
     *
     * @return success: The status of the connection
     */
    public boolean connect(String hostname, Integer port, String user, String password, String
            databaseName) {
        connection = null;
        String url = "jdbc:postgresql://" + hostname + ":" + port + "/" + databaseName;
        System.out.println("Connecting to "+url);
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.out.println("Connection failed.");
            return false;
        }
        System.out.println("Connection successful.");
        return true;
    }

    /**
     * Determines if the server is already correctly configured (all tables are present)
     * @return status
     * @throws SQLException on invalid database or schema settings.
     */
    private boolean alreadyCreated() throws SQLException {
        System.out.println("****************************************");
        boolean[] found = new boolean[TableConstants.TABLES.length];
        ResultSet rs = this.querry(new ListTables(schema));
        int i = 0;
        while(rs.next()) {
            String tableName = rs.getString("table_name");
            for(int j = 0; j < found.length; j++) {
                if(tableName.equalsIgnoreCase(TableConstants.TABLES[j])) {
                    found[i] = true;
                }
            }
            if(!found[i]) {
                System.out.println("N: "+tableName);
                }
            else {
                System.out.println("Y: "+tableName);
            }
            i++;
            if(i>=found.length) {
                break;
            }
        }
        rs.close();
        boolean foundAll = true;
        for(boolean b: found) {
            foundAll &= b;
        }
        System.out.println("****************"+foundAll+"*******************");
        return foundAll;
    }

    /**
     * Executes SQLAction codes and prints a stacktrace along with the function of
     * the SQLAction on failure
     * @param sql An SQLAction to execute
     * @return success
     */
    public boolean execute(SQLAction sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql.getAction());
            statement.close();
            System.out.println(sql.getFunction());
            System.out.println(sql.getAction());
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println(sqlE.getClass().getName() + ":" + sqlE.getMessage());
            System.err.println("Error while trying to perform action:\n" + sql.getFunction());
            return false;
        }
        return true;
    }

    /**
     * Runs a query on the backend.sql server after connection is established.
     * @param sql an SQLAction to perform represeting a query.
     * @return A ResultSet containing all matches to the query.
     */
    public ResultSet querry(SQLAction sql) {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql.getAction());
            System.out.println(sql.getAction());
            System.out.println(sql.getFunction());
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println(sqlE.getClass().getName() + ":" + sqlE.getMessage());
            System.err.println("While trying to perform querry:" + sql.getAction());
        }
        return rs;
    }

    public static void main(String[] args) throws SQLException, IOException {
        String password = null;
        if(args.length == 1) {
            password = args[0];
        }
        else {
            System.err.println("Invalid number of parameters");
            System.exit(1);
        }

        //Create a GameServer object
        GameServer gs = new GameServer(password);

        //Run an example SQLAction
        String[] attrs = new String[]{"username", "balance","startDate"};
        String[] values = new String[]{"tcuprak","61","09/30/17"};
        GeneralQuery q1 = new GeneralQuery("Players");
        GeneralQuery q2 =new GeneralQuery("Players", "username", "tjcuprak");
        GeneralQuery q3 =new GeneralQuery("Players", attrs, values, "OR");

        System.out.println(q1.getAction()+"hey\n");
        System.out.println(q1.getFunction()+"\n");

        System.out.println(q2.getAction()+"\n");
        System.out.println(q2.getFunction()+"\n");

        System.out.println(q3.getAction()+"\n");
        System.out.println(q3.getFunction()+"\n");

        GeneralQuery q4 = new GeneralQuery("friendswith", "username","zmonroe");
        System.out.println(q4.getAction()+"\n");
        System.out.println(q4.getFunction()+"\n");

        ResultSet friends = gs.querry(q4);


        //Get meta data from result (in case column name must be found)
        ResultSetMetaData friendsMetaData = friends.getMetaData();
        System.out.println(friendsMetaData.getColumnName(2));

        System.out.println("GS:"+gs.schema);
        //iterate through result and get the names
        while(friends.next()) {
            System.out.println(friends.getString(2));
        }

        String[] tables = new String[]{"PLAYERS", "FRIENDSWITH", "PLAYERCHAR",
                "HASITEM"};

        LinkedHashMap<String, String> joinConditions = new LinkedHashMap<>();
        joinConditions.put("PLAYERS.username","FRIENDSWITH.username");
        joinConditions.put("FRIENDSWITH.friendname","PLAYERCHAR.player");
        joinConditions.put("PLAYERCHAR.charid", "HASITEM.character");

        LinkedHashMap<String, String> attrConditions = new LinkedHashMap<>();
        attrConditions.put("PLAYERS.username", "zmonroe");

        String[] searches = new String[]{"friendname", "item"};

        SQLAction jsq = new JoinSearchQuery(tables, joinConditions, attrConditions, searches,
                "friendname",false);
        ResultSet joinRs = gs.querry(jsq);
        while(joinRs.next()) {
            System.out.println(joinRs.getString("friendname")+"\t|\t"+joinRs.getString
                    ("item"));
        }
    }
}
