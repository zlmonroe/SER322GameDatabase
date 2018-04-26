package backend.sql.SQLActions;

public class NPCSkills implements SQLAction {

    private String name;

    public NPCSkills(String name){
        this.name = name;
    }
    /**
     * The getAction method should create
     * backend.sql syntax to load
     */
    @Override
    public String getAction() {
        return "SELECT S.skill FROM hasskill AS S, nonplayerchar AS C WHERE C.name='"+name+"' AND S.character = C.charid";
    }

    /**
     * Gets the function of the object
     *
     * @return function
     */
    @Override
    public String getFunction() {
        return "Gets all skills an NPC has.";
    }
}
