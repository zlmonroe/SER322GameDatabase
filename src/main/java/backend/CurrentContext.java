package backend;

public class CurrentContext {
	public static CurrentPlayer player;
	
	private CurrentContext() {
		//You not allowed to make an instance of this class!
	}
	
	public static void loginPlayer(String us, String pw) {
		player = new CurrentPlayer(us, pw);
	}
	
	public static CurrentPlayer getPlayer() {
		return player;
	}
	
}
