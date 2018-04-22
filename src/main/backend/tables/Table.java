package backend.tables;

import backend.SQLActions.SQLAction;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public abstract class Table implements SQLAction {
    private final String tableName;
    private final String primaryKey;
    private final String[] foreingKeys;
    private final String[] inserts;

    public Table(String tableName, String primaryKey, String[] foreingKeys, String[] inserts) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.foreingKeys = foreingKeys;
        this.inserts = inserts;
    }

    public String getTableName() {
        return tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public String[] getForeingKeys() {
        return foreingKeys;
    }

    public String[] getInserts() {
        return inserts;
    }

    public abstract SQLAction getInsertSQL();
}
