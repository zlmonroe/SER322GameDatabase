package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class HasSkillsTable extends Table {

    public HasSkillsTable() {
        super("HasSkill", "character, skill",
                new String[] {
                        "character INTEGER NOT NULL",
                        "skill VARCHAR(20) NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("character","CHARACTER(id)");
                        put("skill","SKILL(name)");
                    }
                },
                new String[] {
                        "235223, 'Berzerk'",
                        "153724, 'Rally'",
                        "89626, 'Sneak'",
                        "654168, 'Sprint'",
                        "93527, 'Infect'",
                        "23673, 'Preach'",
                        "535416, 'Endulge'"});
    }
}
