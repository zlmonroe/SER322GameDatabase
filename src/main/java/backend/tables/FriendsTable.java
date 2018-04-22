package backend.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/22/2018.
 */
public class FriendsTable extends Table{

    public FriendsTable() {
        super("FRIENDS", "username, friendName",
                new String[]{
                        "username VARCHAR(20) NOT NULL",
                        "friendName VARCHAR(20) NOT NULL CHECK(friendName <> username)",
                }, new LinkedHashMap<String, String>() {
                    {
                        put("username", "PLAYERS(username)");
                        put("friendName", "PLAYERS(username)");
                    }
                },
                new String[]{
                        "'tcuprak', 'jbush'",
                        "'tcuprak', 'zmonroe'",
                        "'tcuprak', 'vedgar'",
                        "'acastaneda', 'zmonroe'",
                        "'acastaneda', 'vedgar'",
                        "'vedgar', 'tcuprak'",
                        "'vedgar', 'acastaneda'",
                        "'vedgar', 'zmonroe'",
                        "'vedgar', 'jbush'",
                        "'zmonroe', 'tcuprak'",
                        "'zmonroe', 'acastaneda'",
                        "'zmonroe', 'vedgar'",
                        "'zmonroe', 'jbush'",
                        "'jbush', 'tcuprak'",
                        "'jbush', 'vedgar'",
                        "'jbush', 'zmonroe'"
                });
    }
}
