package backend.sql.SQLActions;

import java.sql.SQLException;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public interface SQLAction {
    /**
     * The getAction method should create
     * backend.sql syntax to load
     */
    public String getAction();

    /**
     * Gets the function of the object
     * @return function
     */
    public String getFunction();
}
