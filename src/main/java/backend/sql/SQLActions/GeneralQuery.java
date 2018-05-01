package backend.sql.SQLActions;

import java.sql.SQLException;

public class GeneralQuery implements SQLAction  {

    private StringBuilder query = new StringBuilder("SELECT * FROM ");
    private StringBuilder desc = new StringBuilder("Query to find entries of table ");
    private int len = query.length();
    private SQLException e = null;

    public GeneralQuery(String table, String[] attributes, String[] values, String opperand) {
        query.append(table.toUpperCase());
        if (attributes!=null && attributes.length == 0)
            attributes = null;
        if (values !=null && values.length == 0)
            values = null;
        if(attributes!=null && values!=null){
            try {
                if (attributes.length != values.length) {
                    System.out.println("array size mismatch error");
                    throw new SQLException("Mismatch in size of attributes and values arrays. attributes was size " + attributes.length +
                            ", values was size " + values.length + ".");
                }else{
                    query.append(" WHERE "+attributes[0]+"='"+values[0]+"'");
                    if (attributes.length > 1) {
                        if (opperand.toUpperCase().compareTo("AND") != 0 && opperand.toUpperCase().compareTo("OR") != 0) {
                            System.out.println("Invalid Opperator error");
                            throw new SQLException("Invalid opperand type. should be \"AND\" or \"OR\", was \"" + opperand + "\"");
                        } else {
                            for (int i = 1; i < attributes.length; i++) {
                                query.append(" " + opperand + " " + attributes[i] + "='" + values[i] + "'");
                            }
                        }
                    }
                }
            }catch(SQLException e){
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    public GeneralQuery(String table, String attribute, String value) {
        this(table,new String[]{attribute}, new String[]{value}, null);
    }

    public GeneralQuery(String table) {
        this(table, null, null, null);
    }

    /**

     * The getAction method should create
     * backend.sql syntax to load
     */
    @Override
    public String getAction() {
        return query.toString();
    }

    /**
     * Gets the function of the object
     *
     * @return function
     */
    @Override
    public String getFunction() {
        desc.append(query.subSequence(len,query.length()).toString().toLowerCase());
        return desc.toString();
    }
}
