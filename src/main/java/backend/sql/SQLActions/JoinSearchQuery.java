package backend.sql.SQLActions;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/26/2018.
 */
public class JoinSearchQuery implements SQLAction {
    private String query;

    public JoinSearchQuery(String[]joinAll, LinkedHashMap<String, String> on,
                           LinkedHashMap<String, String> values, String[] searches,
                           String orderBy) {
        if(joinAll == null || on == null) {
            throw new IllegalArgumentException("Joins must not be null!");
        }
        if(!(joinAll.length == on.size()+1)) {
            throw new IllegalArgumentException("Joins must match conditions! ("+joinAll
                    .length+"!="+(on.size()+1)+")");
        }

        ArrayList<String> keys = new ArrayList<>(on.keySet());

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT DISTINCT ").append(searches[0]);
        for(int i = 1; i < searches.length; i++) {
            sql.append(", ").append(searches[i]);
        }

        sql.append(" FROM ").append(joinAll[0]);

        for(int i = 0; i < joinAll.length - 1; i++) {
            sql.append("\nFULL OUTER JOIN ").append(joinAll[i+1]).append(" ON ").append(keys.get(i))
                    .append(" = ").append(on.get(keys.get(i)));
        }

        sql.append("\nWHERE\n");

        String operator = " AND\n";
        int i = 0;
        for(String key : values.keySet()) {
            operator = i!=values.size()-1 ? operator:"";
            System.out.println(i+" "+(values.size()-1));
            sql.append(key).append(" LIKE '%").append(values.get(key)).append("%'").append
                    (operator);
            i++;
        }
        sql.append("\nORDER BY ").append(orderBy).append(";");
        this.query = sql.toString();
    }

    @Override
    public String getAction() {
        return query;
    }

    @Override
    public String getFunction() {
        return "Joins a list of tables on a list of join conditions where attributes have certain" +
                " values (this is a very general query).";
    }
}
