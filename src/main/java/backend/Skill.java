package backend;

import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Skill {
    private String name;
    private int level;
    private int coolDown;
    private int manaCost;
    
    private String effect;

    private GameServer gs;
    
    public Skill(String n) {
    	gs = CurrentContext.getGameServer();
    	name = n;
    	loadSkill();
    }
    
    public Skill(String name, int level, int coolDown, int manaCost, String effect) {
        super();
        this.name = name;
        this.level = level;
        this.coolDown = coolDown;
        this.manaCost = manaCost;
        this.effect = effect;
    }

    /**
     * Load skill from database
     */
    private void loadSkill() {
		ResultSet s = gs.querry(new GeneralQuery("skill", "name", name));

			try {
                if (s.next()) {
                    level = s.getInt("level");
                    coolDown = s.getInt("cooldown");
                    manaCost = s.getInt("manacost");
                }
			}catch (SQLException sqlE){
				sqlE.printStackTrace();
				sqlE.getMessage();
			}
    	//after loading the rest of the skill, find the effect
    	loadEffect();
    }
    
    /**
     * Load the effect associated with the skill
     */
    private void loadEffect() {
    	ResultSet e = gs.querry(new GeneralQuery("skilleffect","skill",name));
    	try {
    	    if (e.next())
    	        effect = e.getString("effect");
        } catch (SQLException sqlE){
    	    sqlE.printStackTrace();
    	    sqlE.getMessage();
        }
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
