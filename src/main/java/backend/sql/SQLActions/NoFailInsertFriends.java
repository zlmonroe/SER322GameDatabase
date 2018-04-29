package backend.sql.SQLActions;

/**
 * Created by zlmonroe on 4/27/2018.
 */
public class NoFailInsertFriends implements SQLAction {
    private String username;
    private String friend;

    public NoFailInsertFriends(String username, String friend) {
        this.username = username;
        this.friend = friend;
    }

    @Override
    public String getAction() {
        return "INSERT INTO FRIENDSWITH VALUES ('"+username+"', '"+friend+"')\n" +
                "ON CONFLICT (username, friendname) DO NOTHING;";
    }

    @Override
    public String getFunction() {
        return "Insert into friends and on fail update instead";
    }
}
