package backend;

public class Location {
    private String name;
    private int baseAggro;
    private boolean canTP;
    private int avgLevel;
    private String terrain;
    
    public Location(String name) {
        this.name = name;
    }
    
    public Location(String name, int baseAggro, boolean canTP, int avgLevel, String terrain) {
        super();
        this.name = name;
        this.baseAggro = baseAggro;
        this.canTP = canTP;
        this.avgLevel = avgLevel;
        this.terrain = terrain;
    }

    public String getName() {
        return name;
    }
    public int getBaseAggro() {
        return baseAggro;
    }
    public boolean isCanTP() {
        return canTP;
    }
    public int getAvgLevel() {
        return avgLevel;
    }
    public String getTerrain() {
        return terrain;
    }
    //sql
    private void loadLocation(String n) {
        //TODO
    }
}
