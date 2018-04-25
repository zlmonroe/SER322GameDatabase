package backend;

import backend.sql.GameServer;
import java.sql.SQLException;

public class CurrentContext {
	public static Player player;
	public static GameServer gameServer;
	
	private CurrentContext() {
		//You not allowed to make an instance of this class!
	}
	
	public static void loginPlayer(String us, String pw) {
		player = new Player(us, pw);
	}
	
	public static Player getPlayer() {
		return player;
	}

	public static boolean startGameServer(String SQLServerPassword) {
        try {
            CurrentContext.gameServer = new GameServer(SQLServerPassword);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	public static GameServer getGameServer() {
	    return gameServer;
    }
	
}
