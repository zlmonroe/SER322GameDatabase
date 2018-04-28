package backend.sql.SQLActions;

/**
 * Created by zlmonroe on 4/27/2018.
 */
public class StringsLike implements SQLAction {
    private String table;
    private String attribute;
    private String value;

    public StringsLike(String table, String attribute, String value) {
        this.table = table;
        this.attribute = attribute;
        this.value = value;
    }

    @Override
    public String getAction() {
        return "SELECT "+attribute+" FROM "+table+" WHERE "+attribute+" ILIKE '%"+value+"%';";
    }

    @Override
    public String getFunction() {
        return "Finds strings similar in a single attribute"+attribute+" in a single table " +
                "("+table+"). Used for search fields.";
    }
}
