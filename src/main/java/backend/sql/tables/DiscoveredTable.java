package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class DiscoveredTable extends Table {

    public DiscoveredTable() {
        super("Discovered", "playerCharacter, location",
                new String[] {
                        "playerCharacter VARCHAR(30) NOT NULL",
                        "location VARCHAR(30) NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("playerCharacter","PLAYERCHAR(name)");
                        put("location","LOCATIONS(name)");
                    }
                },
                new String[] {
                        "'Tim', 'The Friendly Tavern'",
                        "'Tim2', 'The Friendly Tavern'",
                        "'Alex', 'The Friendly Tavern'",
                        "'Vatrica', 'The Friendly Tavern'",
                        "'Zach', 'The Friendly Tavern'",
                        "'Jon', 'The Friendly Tavern'",
                        "'Tim', 'The Forest of Wisdom'",
                        "'Tim2','The Forest of Wisdom'",
                        "'Alex', 'The Forest of Wisdom'",
                        "'Vatrica', 'The Forest of Wisdom'",
                        "'Zach', 'The Forest of Wisdom'",
                        "'Jon', 'The Forest of Wisdom'",
                        "'Tim', 'The Basin'",
                        "'Tim2', 'Shimmering Lake'",
                        "'Alex','The Spire of Zunor' ",
                        "'Vatrica', 'The Dark Forest'",
                        "'Zach', 'The Basin'",
                        "'Tim', 'The Cave under Shimmering Lake'",
                        "'Tim2', 'The Cave under Shimmering Lake'",
                        "'Alex', 'The Dark Forest'",
                        "'Vatrica', 'The Spire of Zunor'"});
    }
}
