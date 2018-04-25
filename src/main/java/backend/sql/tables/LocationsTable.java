package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class LocationsTable extends Table {

    public LocationsTable() {
        super("Locations", "name",
                new String[] {
                        "name VARCHAR(30) NOT NULL",
                        "baseAggro INTEGER NOT NULL",
                        "canTP BOOLEAN NOT NULL",
                        "avgLevel INTEGER NOT NULL",
                        "terrain VARCHAR(50) NOT NULL"}, null,
                new String[] {
                        "'The Friendly Tavern', 0, false, 0, 'INTERRIOR'",
                        "'The Dark Forest', 80, true, 50, 'FOREST'",
                        "'The Forest of Wisdom', 5, true, 1, 'FOREST'",
                        "'The Basin', 15, false, 5, 'DESERT'",
                        "'Shimmering Lake', 20, true, 8, 'LAKE'",
                        "'The Cave under Shimmering Lake', 30, false, 12, 'CAVERN'",
                        "'The Spire of Zunor', 50, false, 25, 'TOWER'",
                        "'Rolling Thunder', 30, true, 15, 'HILLS'"});
    }
}
