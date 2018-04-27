package backend.sql.SQLActions;

public class PlayCharLocations implements SQLAction{
    
    private String name;

    public PlayCharLocations(String name){
        this.name = name;
    }

    @Override
    public String getAction() {
        return "SELECT L.name, L.baseAggro, L.canTP, L.avgLevel, L.terrain FROM hasskill AS H,"+
                "locations AS L, discovered AS D WHERE D.location='"+ name +"'";
    }

    @Override
    public String getFunction() {
        return "Gets all locations a player character has discovered.";
    }

}
