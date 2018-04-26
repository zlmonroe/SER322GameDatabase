package backend;

import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Item {
    private String name;
    private int weight;
    private double effectMultiplier;
    
    private List<String> effect;
    
    public Item(String n) {
    	name = n;
    	loadItem(n);
    }
    
    public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public double getEffectMultiplier() {
		return effectMultiplier;
	}

	public List<String> getEffect() {
		return effect;
	}

	private boolean loadItem(String n) {
    	//TODO
		GameServer gs = CurrentContext.getGameServer();
        ResultSet item = gs.querry(new GeneralQuery("PlayerChar","name", n));
        try {
			if(item.next()) {
				name = item.getString("name");
				weight = item.getInt("weight");
				effectMultiplier = item.getDouble("effectmultiplier");
				loadEffect();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return false;
    }
    
    private boolean loadEffect() {
    	//TODO
    	return false;
    }
}
