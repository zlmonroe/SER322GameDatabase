package backend;

public class CurrentContext {
	public static Player player;
	
	private CurrentContext() {
		//You not allowed to make an instance of this class!
	}
	
	public static void loginPlayer(String us, String pw) {
		player = new Player(us, pw);
	}
	
	public static Player getPlayer() {
		return player;
	}
	
}
