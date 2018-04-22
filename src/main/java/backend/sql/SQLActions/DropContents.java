package backend.sql;

/**
 * Created by zlmonroe on 4/21/2018.
 * Generates an SQLAction which contains the code to drop the contents of
 * the current database and schema and contains a description of this functionality
 */
public class DropContents implements SQLAction {
    private String code;
    private String database;
    private String schema;

    public DropContents(String database, String schema) {
        this.database = database;
        this.schema = schema;

        StringBuilder sql = new StringBuilder();
        for (String table : TableConstants.TABLES) {
            sql.append("DROP TABLE IF EXISTS ").append(table).append(" CASCADE;\n");
        }
        sql.append("DROP SCHEMA IF EXISTS ").append(this.schema).append(" CASCADE;\n");
        code = sql.toString();
    }

    @Override
    public String getAction() {
        return code;
    }

    @Override
    public String getFunction() {
        return "Drop the database " + database + "'s contents including the schema";
    }
}
