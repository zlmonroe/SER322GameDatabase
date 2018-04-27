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
	
	public static void loginPlayer(String us, String pw) throws SQLException {
		player = new Player();
		if(!player.loadPlayer(us, pw)) {
			player = null;
			JOptionPane.showMessageDialog(null,"The username and/or password you entered was invalid. Please try again.", "Invalid Credentials",JOptionPane.ERROR_MESSAGE);
		}
		else {
		    System.out.println(player.getUsername());
		}
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
