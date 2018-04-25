package backend;

public class Skill {
    private String name;
    private int level;
    private int coolDown;
    private int manaCost;
    
    private String effect;
    
    public Skill(String n) {
    	name = n;
    	loadSkill();
    }
    /**
     * Load skill from database
     */
    private void loadSkill() {
    	//TODO
    	//after loading the rest of the skill, find the effect
    	loadEffect();
    }
    
    /**
     * Load the effect associated with the skill
     */
    private void loadEffect() {
    	//TODO
    }
    
	public String getName() {
		return name;
	}
	
	public int getLevel() {
		return level;
	}
	
	public int getCoolDown() {
		return coolDown;
	}
	
	public int getManaCost() {
		return manaCost;
	}
	
	public String getEffect() {
		return effect;
	}
    
}
