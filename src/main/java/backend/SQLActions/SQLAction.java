package backend.SQLActions;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public interface SQLAction {
    /**
     * The getAction method should create
     * sql syntax to load
     */
    public String getAction();

    /**
     * Gets the function of the object
     * @return function
     */
    public String getFunction();
}
