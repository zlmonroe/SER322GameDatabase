package backend.sql.tables;

import backend.sql.SQLActions.Insert;
import backend.sql.SQLActions.SQLAction;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public abstract class Table implements SQLAction {
    private final String tableName;
    private final String primaryKey;
    private final String[] keys;
    private LinkedHashMap<String, String> foreingKeys;
    private final String[] inserts;

    public Table(String tableName, String primaryKey, String[] keys, LinkedHashMap<String, String>
            foreingKeys, String[] inserts) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.keys = keys;
        this.foreingKeys = foreingKeys;
        this.inserts = inserts;
    }

    @Override
    public String getAction() {
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(tableName.toUpperCase()).append("(\n");
        for(String key: keys) {
            String keyN = key.split("\\s+")[0];
            sql.append("\t").append(key);
            if (foreingKeys!=null && keyN!=null && foreingKeys.containsKey(keyN)) {
                sql.append(" REFERENCES ").append(foreingKeys.get(keyN));
            }
            sql.append(",\n");
        }
        sql.append("\tPRIMARY KEY(").append(primaryKey).append(")\n);");
        System.out.println(sql.toString());
        return sql.toString();
    }

    public SQLAction getInsertSQL() {
        return new Insert(this.getTableName(), this.getInserts());
    }

    @Override
    public String getFunction() {
        return "Create table "+getTableName();
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public Set<String> getForeingKeys() {
        return foreingKeys.keySet();
    }

    public String[] getInserts() {
        return inserts;
    }

    public String[] getKeys() {
        return keys;
    }
}
