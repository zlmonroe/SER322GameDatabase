package backend;

import backend.SQLActions.CreateDatabase;
import backend.SQLActions.DropContents;
import backend.SQLActions.DropDatabase;
import backend.SQLActions.ListTables;
import backend.SQLActions.LoadDatabase;
import backend.SQLActions.SQLAction;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
        if(!this.connect(hostname, port, user, password) || !this.alreadyCreated()) {
            this.execute(new DropContents(database, schema));
            this.execute(new DropDatabase(database));
            this.execute(new CreateDatabase(database, user));
            this.execute(new LoadDatabase(schema));
        }
    }

    /**
     * Simple constructor for default Postgres setup
     * @param password The password for the Postgres server
     */
    public GameServer(String password) throws SQLException {
        this("SER321_DB", "GameSchema", "localhost",
                5432, "postgres", password);
    }

    /**
     * Initiates connection to the server at the given hostname and port
     * with the given database name.
     *
     * @return success: The status of the connection
     */
    public boolean connect(String hostname, Integer port, String user, String password) {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager
                    .getConnection("jdbc:postgresql://" + hostname + ":" + port + "/" + database,
                            user, password);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
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
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println(sqlE.getClass().getName() + ":" + sqlE.getMessage());
            System.err.println("While trying to perform action:" + sql.getAction());
            return false;
        }
        return true;
    }

    /**
     * Runs a query on the sql server after connection is established.
     * @param sql an SQLAction to perform represeting a query.
     * @return A ResultSet containing all matches to the query.
     */
    public ResultSet querry(SQLAction sql) {
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(sql.getAction());
            statement.close();
        }
        catch (SQLException sqlE) {
            sqlE.printStackTrace();
            System.err.println(sqlE.getClass().getName() + ":" + sqlE.getMessage());
            System.err.println("While trying to perform querry:" + sql.getAction());
        }
        return rs;
    }

    public static void main(String[] args) throws SQLException {
        GameServer gs = new GameServer("Joshua130316");
    }
}
