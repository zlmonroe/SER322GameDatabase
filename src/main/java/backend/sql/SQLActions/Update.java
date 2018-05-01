package backend.sql.SQLActions;

public class Update implements SQLAction{
    private final String tableName;
    private final String username;
    private final String attr;
    private final String newVal;

    public Update(String tableName, String username, String attr, String newVal) {
        this.tableName = tableName;
        this.username = username;
        this.attr = attr;
        this.newVal = newVal;
    }

    @Override
    public String getAction() {
        if(attr == null || newVal == null) {
            return "";
        }
        return "UPDATE " + tableName + "\n"
                + "SET " + attr + " = " + newVal + "\n"
                + "WHERE username = " + username + ";";
    }

    @Override
    public String getFunction() {
        return "Updates attribute " + attr + " to " + newVal + "for player " + username + " in " + tableName;
    }
}
