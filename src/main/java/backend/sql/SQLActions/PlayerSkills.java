package backend.sql.SQLActions;

public class PlayerSkills implements SQLAction {

    private String name;

    public PlayerSkills(String name){
        this.name = name;
    }
    /**
     * The getAction method should create
     * backend.sql syntax to load
     */
    @Override
    public String getAction() {
        return "SELECT S.skill FROM hasskill AS S, playerchar AS P WHERE P.name='"+name+"' AND S.character = P.charid";
    }

    /**
     * Gets the function of the object
     *
     * @return function
     */
    @Override
    public String getFunction() {
        return "Gets all skills a player character has.";
    }
}