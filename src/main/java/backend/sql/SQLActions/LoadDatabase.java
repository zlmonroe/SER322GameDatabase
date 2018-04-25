package backend.sql.SQLActions;

import backend.sql.tables.*;

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
        Table[] tables = new Table[]{new PlayersTable(), new FriendsTable(), new ItemsTable(),
                new ConsumableTable(), new ArmorTable(), new WeaponsTable(), new CharactersTable()};
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE SCHEMA ").append(schema).append(";\n").append("SET SEARCH_PATH TO ")
                .append(schema).append(";\n");
        for(Table table: tables) {
            sql.append(table.getAction()).append("\n");
            sql.append(table.getInsertSQL().getAction()).append("\n");
        }
        return sql.toString();
    }

    @Override
    public String getFunction() {
        return "Load the database (creates "+schema+", creates tables, and inserts into tables)";
    }
}
