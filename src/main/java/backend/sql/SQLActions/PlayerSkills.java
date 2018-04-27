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
        return "SELECT S.name, S.level, S.coolDown, E.effect, S.manaCost FROM hasskill AS H,"+
                " playerchar AS P, skill AS S, skilleffect as E WHERE P.name='"+ name +
                "' AND H.character = P.charid AND H.skill = S.name AND E.skill = S.name";
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