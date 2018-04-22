package backend.SQLActions;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class Insert implements SQLAction {
    private final String tableName;
    private final String[] values;

    public Insert(String tableName, String[] values) {
        this.tableName = tableName;
        this.values = values;
    }

    @Override
    public String getAction() {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ").append(tableName).append("VALUES\n");
        String sep = "";
        for(String value : values) {
            sql.append(sep);
            sql.append("(").append(value).append(")");
            sep = ",\n";
        }
        sql.append(";");
        return sql.toString();
    }

    @Override
    public String getFunction() {
        return "Insert values into " + tableName;
    }
}
