package backend.sql.SQLActions;

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
        return "SELECT FRIENDSWITH.friendname as username, PLAYERS.startdate as startdate FROM " +
                "FRIENDSWITH JOIN PLAYERS ON (FRIENDSWITH.username = PLAYERS.username) WHERE " +
                "PLAYERS.username='"+this
                .player+"';";
    }

    @Override
    public String getFunction() {
        return "Get all of the friends of player"+this.player+".";
    }
}
