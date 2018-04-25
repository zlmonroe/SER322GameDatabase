package backend;

import backend.sql.GameServer;
import backend.sql.SQLActions.Insert;
import backend.sql.SQLActions.ListFriends;
import backend.sql.SQLActions.SQLAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Player {

	private String username;
	private String password;
	private double balance;
	private LocalDate startDate;
	private List<String> friends;

	/**
	 * Create a player object from an existing player in the database
	 * @param us user name of the player to get
	 * @param pw password of player to get
	 */
	public Player(String us, String pw) {
		loadPlayer(us, pw);
		loadFriends();
	}
	
	/**
	 * Create a new player that does not exist in the database
	 * @param us username of new player
	 * @param pw password of new player
	 * @param b starting balance of new player
	 */
	public Player(String us, String pw, double b) {
		username = us;
		password = pw;
		balance = b;
		startDate = LocalDate.now();
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	/**
	 * sets the player's password. Ensures that the player knows their old password
	 * @param password new password 
	 * @param originalPassword old password for checking
	 * @return
	 */
	public boolean setPassword(String password, String originalPassword) {
		if(originalPassword.equals(this.password)) {
			this.password = password;
			return true;
		}
		return false;
	}

	public double getBalance() {
		return balance;
	}

	/**
	 * adds to the user balance
	 * @param money amount of money to add
	 */
	public void addBalance(double money) {
		this.balance += money;
	}

	public LocalDate getStartDate() {
		return startDate;
	}
	
	public List<String> getFriends() {
		return friends;
	}
	
	/**
	 * adds a new friend to the friend list
	 * @param friendUS username of the friend to add
	 */
	public void addFriend(String friendUS) {
		//code to add from in sql

        Insert insert = new Insert("FRIENDS", new String[]{
                "'"+this.username+"', '"+friendUS+"'"
        });
        CurrentContext.getGameServer().execute(insert);
        loadFriends();
	}
	
	/**
	 * saves this player into the database
	 */
	public void savePlayer() {
		//TODO
	}

	/**
	 * loads an existing player from the database
	 * @param us player username
	 * @param pw player password
	 */
	private void loadPlayer(String us, String pw) {
		//code to load the player info from sql
		//TODO DONT KEEP THESE INSERTS, ACTUALLY RUN A QUERY AND VERIFY LOGIN
        this.username = us;
        this.password = pw;
	}
	
	/**
	 * loads this players friends from the database
	 */
	private void loadFriends() {//similar method will exist for characters
		//code to load the friend's info from sql
        String uname = this.getUsername();
        GameServer gs = CurrentContext.getGameServer();
        SQLAction getFriends = new ListFriends(uname);
        ResultSet friendsSet = gs.querry(getFriends);

        friends = new ArrayList<>();
        try {
            while(friendsSet.next()) {
                friends.add(friendsSet.getString("friendName"));
            }
        } catch (SQLException e) {
            System.out.println("Error while trying to load friends from the database");
            e.printStackTrace();
        }

    }
}
