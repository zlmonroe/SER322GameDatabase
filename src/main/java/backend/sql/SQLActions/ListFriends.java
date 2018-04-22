package backend.sql;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class ListFriends implements SQLAction {
    private String player;

    public ListFriends(String player) {
        this.player = player;
    }

    @Override
    public String getAction() {
        return "SELECT friendName FROM FRIENDS " +
                "WHERE username='"+this.player+"';";
    }

    @Override
    public String getFunction() {
        return "Get all of the friends of player"+this.player+".";
    }
}
