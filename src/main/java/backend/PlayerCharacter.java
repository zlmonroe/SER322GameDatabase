package backend;

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
    
    public PlayerCharacter(String n) {
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
    	//After loading the basic attributes of the character, load the relationships:
    	
    	loadItems();
    	loadLocations();
    	loadQuests();
    	loadSkills();
    	return false;
    }
    
	/**
     * calculates the current weight of all items
     */
    private void calculateCurrentWeight() {
    	//TODO
    }
    
    /**
     * Loads items from sql database
     * @return false if unsuccessfull
     */
    private boolean loadItems() {
    	//TODO
    	//Load items, then calc the weight
    	
    	calculateCurrentWeight();
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
