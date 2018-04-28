package backend;

import backend.sql.GameServer;
import java.sql.Date;
import backend.sql.SQLActions.*;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class Player {

	private String username;
	private String password;
	private double balance;
	private LocalDate startDate;
	private List<String> friends;
	private List<String> characters;
	private GameServer gs;

	private Random random = new Random();

	/**
	 * Create a player object from an existing player in the database
	 */
	public Player(){
		gs = CurrentContext.getGameServer();
	}
	
	/**
	 * Create a new player that does not exist in the database
	 * @param us username of new player
	 * @param pw password of new player
	 * @param b starting balance of new player
	 */
	public Player(String us, String pw) {
		username = us;
		password = pw;
		balance = 0;
		startDate = LocalDate.now();
		String d = Date.valueOf(startDate).toString();
		String[] ar = {username, password, d, "0"}; 
		Insert addPlayer = new Insert("Players", ar);
		gs.execute(addPlayer);
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
	 * adds a new character to the character list
	 * @param characterName add params as required
	 */
	public void addCharacter(String characterName) {
		//code to add character in sql
		Insert insert = new Insert("CHARACTER", new String[]{
				"'"+this.username+"', '"+characterName+"'"+", 50, 5, 100, 5, 5, 5, 1, 5, 0, 25, "+(random.nextInt(2137483647)+1)
		});
		gs.execute(insert);
		loadCharacters();
	}


	public List<String> getCharacters() {
		return characters;
	}
	
	/**
	 * adds a new friend to the friend list
	 * @param friendUS username of the friend to add
	 */
	public void addFriend(String friendUS) {
		//code to add from in sql

        Insert insert = new Insert("FRIENDSWITH", new String[]{
                "'"+this.username+"', '"+friendUS+"'"
        });
        gs.execute(insert);
        loadFriends();
	}

	/**
	 * saves this player into the database
	 * @param attr target attribute to change.
	 * @param newVal value replacing attr's current value
	 */
	public void editPlayer(String attr, String newVal) {
		Update update = new Update("PLAYERS", username, attr, newVal);
		gs.execute(update);
	}

	/**
	 * loads an existing player from the database
	 * @param us player username
	 * @param pw player password
	 * @throws SQLException - if there is a failure to connect to the database
	 */
	public boolean loadPlayer(String us, String pw) throws SQLException {
		//code to load the player info from sql
        String[] atr =  {"username", "password"};
        String[] val =  {us, pw};
        ResultSet player = gs.querry(new GeneralQuery("players",atr, val, "AND"));
        if(player.next()) {
        	System.out.println(player);
        	username = us;
        	password = pw;
        	startDate = player.getDate("startDate").toLocalDate();
        	balance = player.getDouble("balance");
    		loadFriends();
    		loadCharacters();
    		return true;
        }
        return false;
	}
	
	/**
	 * loads this players friends from the database
	 */
	private void loadFriends() {
		//code to load the friends' info from sql
        String uname = this.getUsername();
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
	
	/**
	 * loads this players characters from the database
	 */
	private void loadCharacters() {
		ResultSet playChars = gs.querry(new ListPlayerChars(username));
		characters = new ArrayList<>();
		try {
			while(playChars.next()) {
				characters.add(playChars.getString("name"));
			}
		} catch (SQLException e) {
			System.out.println("Error while trying to load friends from the database");
			e.printStackTrace();
		}
    }
}
