package backend;

import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerCharacter {
    private String name;
    private String player;
    private double money;
    private int mana;
    private int hp;
    private int attack;
    private int defense;
    private int sight;
    private int level;
    private int speed;
    private int xp;
    private int maxCarryWeight;
    private String charID;

    private int currentCarry;
    
    private List<String> items;
    private List<String> quests;
    private List<String> locations;
    private List<String> skills;

	private GameServer gs;
	
    public PlayerCharacter(String n) {
    	gs = CurrentContext.getGameServer();
    	name = n;
    	loadCharacter(n);
    }

	public String getName() {
		return name;
	}

	public String getPlayer() {
		return player;
	}

	public double getMoney() {
		return money;
	}

	public int getMana() {
		return mana;
	}

	public int getHp() {
		return hp;
	}

	public int getAttack() {
		return attack;
	}

	public int getDefense() {
		return defense;
	}

	public int getSight() {
		return sight;
	}

	public int getLevel() {
		return level;
	}

	public int getSpeed() {
		return speed;
	}

	public int getXp() {
		return xp;
	}

	public int getMaxCarryWeight() {
		return maxCarryWeight;
	}

	public String getCharID() {
		return charID;
	}

	public int getCurrentCarry() {
		return currentCarry;
	}

	public List<String> getItems() {
		return items;
	}

	public List<String> getQuests() {
		return quests;
	}

	public List<String> getLocations() {
		return locations;
	}

	public List<String> getSkills() {
		return skills;
	}

    /**
     * Loads character from sql database
     * @param n name of character
     * @return false if the player is not in the table
     */
    private boolean loadCharacter(String n) {
    	//TODO
        String atr =  "name";
        String val =  n;
        ResultSet item = gs.querry(new GeneralQuery("PlayerChar",atr, val));
        try {
			if(item.next()) {
				name = item.getString("name");
			    player = item.getString("player");
			    money = item.getDouble("money");
			    mana = item.getInt("mana");
			    hp = item.getInt("hp");
			    attack = item.getInt("attack");
			    defense = item.getInt("defense");
			    sight = item.getInt("sight");
			    level = item.getInt("level");
			    speed = item.getInt("speed");
			    xp = item.getInt("xp");
			    maxCarryWeight = item.getInt("maxCarryWeight");
			    charID  = item.getString("charID");
			    
				loadItems();
				loadLocations();
				loadQuests();
				loadSkills();
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    	return false;
    }
    
	/**
     * calculates the current weight of all items
     */
    private void calculateCurrentWeight() {
    	String[] name = new String[this.items.size()];
        String[] value = new String[this.items.size()];

        for(int i = 0; i < this.items.size(); i++) {
            name[i] = "name";
            value[i] = this.items.get(i);
        }

    	ResultSet rs = gs.querry(new GeneralQuery("ITEM", name, value, "OR"));
        this.currentCarry = 0;
        try {
            while(rs.next()) {
                this.currentCarry += rs.getInt("weight");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Loads items from sql database
     * @return false if unsuccessfull
     */
    private boolean loadItems() {
    	//TODO
    	//Load items, then calc the weight
        this.items = new ArrayList<>();

    	ResultSet itemsSet =
                gs.querry(new GeneralQuery("HASITEM", "character", this.charID));

        try {
            while (itemsSet.next()) {
                this.items.add(itemsSet.getString("item"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        calculateCurrentWeight();
        System.out.println(Arrays.toString(this.items.toArray()));
        return false;
    }
    
    /**
     * Loads quests from sql database
     * @return false if unsuccessfull
     */
    private boolean loadQuests() {
    	//TODO
    	return false;
    }
    
    /**
     * Loads locations from sql database
     * @return false if unsuccessfull
     */
    private boolean loadLocations() {
    	//TODO
    	return false;
    }
    
    /**
     * Loads skills from sql database
     * @return false if unsuccessfull
     */
    private boolean loadSkills() {
    	//TODO
    	return false;
    }
}
