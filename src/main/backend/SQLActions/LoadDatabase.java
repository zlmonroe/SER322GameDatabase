package backend.SQLActions;

import backend.tables.PlayersTable;
import backend.tables.Table;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class LoadDatabase implements SQLAction {
    private String schema;

    public LoadDatabase(String schema) {
        this.schema = schema;
    }

    @Override
    public String getAction() {
        Table[] tables = new Table[]{new PlayersTable()};
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE SCHEMA ").append(schema).append(";\n").append("SET SEARCH_PATH TO ")
                .append(schema).append(";");
        for(Table table: tables) {
            sql.append(table.getAction());
            sql.append(table.getInsertSQL());
        }
        return sql.toString();
    }

    @Override
    public String getFunction() {
        return "Load the database (creates "+schema+", creates tables, and inserts into tables)";
    }
}
