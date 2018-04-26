package backend;

public class CharacterQuest {
    private int status;

    private String quest;
    
    public CharacterQuest(int s, String q) {
        status = s;
        quest = q;
    }
    
    public int getStatus() {
        return status;
    }

    public String getQuest() {
        return quest;
    }

}
