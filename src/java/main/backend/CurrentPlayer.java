package backend;

import java.time.LocalDate;

public class CurrentPlayer {

	private String username;
	private String password;
	private double balance;
	private LocalDate startDate;
	
	public CurrentPlayer(String us, String pw) {
		loadPlayer(us, pw);
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

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

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	private void loadPlayer(String us, String pw) {
		//code to load the player info from sql
		//TODO
	}
}
