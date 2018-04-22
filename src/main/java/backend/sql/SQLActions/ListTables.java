package backend.sql.SQLActions;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class ListTables implements SQLAction {
    private String schema;

    public ListTables(String schema) {
        this.schema = schema;
    }

    @Override
    public String getAction() {
        return "select table_name from information_schema.tables " +
                "where table_schema='"+schema+"';";
    }

    @Override
    public String getFunction() {
        return "Determine if tables are already configured";
    }
}
