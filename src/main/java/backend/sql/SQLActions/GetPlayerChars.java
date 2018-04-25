package backend.sql.SQLActions;

public class GetPlayerChars implements SQLAction{
    private String playerName;

    public GetPlayerChars(String playerName){
        this.playerName = playerName;
    }

    @Override
    public String getAction() {
        return "SELECT name\nFROM PLAYERCHAR\nWHERE player='" + playerName + "'";
    }

    @Override
    public String getFunction() {
        return "Get all of the player character that player " + playerName + " has.";
    }
}
