package backend;

import backend.sql.GameServer;

import javax.swing.*;
import java.sql.SQLException;

public class CurrentContext {
	public static Player player;
	public static GameServer gameServer;
	
	private CurrentContext() {
		//You not allowed to make an instance of this class!
	}
	
	public static boolean loginPlayer(String us, String pw) throws SQLException {
		player = new Player();
		if(!player.loadPlayer(us, pw)) {
			player = null;
			return false;
		}
	    System.out.println(player.getUsername());
	    return true;
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

    public static void signupPlayer(String us, String pw) {
        player = new Player(us, pw);
    }
	
}
