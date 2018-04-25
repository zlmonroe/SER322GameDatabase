package backend.sql;

import backend.sql.SQLActions.CreateDatabase;
import backend.sql.SQLActions.DropContents;
import backend.sql.SQLActions.ListFriends;
import backend.sql.SQLActions.ListTables;
import backend.sql.SQLActions.LoadDatabase;
import backend.sql.SQLActions.SQLAction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
    }

    /**
     * Simple constructor for default Postgres setup
     * @param password The password for the Postgres server
     */
    public GameServer(String password) throws SQLException {
        this("ser322", "GameSchema", "127.0.0.1",
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
        boolean[] found = new boolean[TableConstants.TABLES.length];
        ResultSet rs = this.querry(new ListTables(schema));
        while(rs != null && rs.next()) {
            String tableName = rs.getString("table_name");
            for(int i=0; i < TableConstants.TABLES.length; i++) {
                if(TableConstants.TABLES[i].equals(tableName)) {
                    found[i] = true;
                }
            }
        }
        if (rs != null) {
            rs.close();
        }
        boolean foundAll = true;
        for(boolean b: found) {
            foundAll &= b;
        }
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
        ResultSet friends = gs.querry(new ListFriends("zmonroe"));

        //Get meta data from result (in case column name must be found)
        ResultSetMetaData friendsMetaData = friends.getMetaData();
        System.out.println(friendsMetaData.getColumnName(1));

        //iterate through result and get the names
        while(friends.next()) {
                System.out.println(friends.getString("friendName"));
        };
    }
}
