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
	GameServer gs;

    public Item(String n) {
		gs = CurrentContext.getGameServer();
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
        ResultSet item = gs.querry(new GeneralQuery("ITEM","name", n));
        try {
			if(item.next()) {
				name = item.getString("name");
				weight = item.getInt("weight");
				System.out.println(weight);
				effectMultiplier = item.getDouble("effectmultiplier");
				//loadEffect();
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return false;
    }

    private boolean loadEffect() {
    	System.out.println(name);
    	ResultSet e = gs.querry(new GeneralQuery("itemeffect","item", name));
    	boolean foundEffect = false;
    	try {
			while (e.next()){
				effect.add(e.getString("effect"));
				if (!foundEffect)
					foundEffect = true;
			}
		}catch (SQLException sqlE){
    		sqlE.printStackTrace();
    		sqlE.getMessage();
		}
        return foundEffect;
    }
}
