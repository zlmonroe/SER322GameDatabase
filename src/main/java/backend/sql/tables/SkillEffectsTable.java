package backend.sql.tables;

import java.util.LinkedHashMap;

/**
 * Created by zlmonroe on 4/21/2018.
 */
public class SkillEffectsTable extends Table {

    public SkillEffectsTable() {
        super("SkillEffect", "skill, effect",
                new String[] {
                        "skill VARCHAR(20) NOT NULL",
                        "effect VARCHAR(30) NOT NULL"},
                new LinkedHashMap<String, String>(){
                    {
                        put("skill","SKILL(name)");
                        put("effect","EFFECT(name)");
                    }
                },
                new String[] {
                        "'Time Damage', 'Sprint'",
                        "'Percent Healing', 'Preach'",
                        "'Percent Mana Drain', 'Infect'",
                        "'Percent Mana Gain', 'Endulge'",
                        "'Fight Boost', 'Berzerk'",
                        "'Time Attack Boost','Rally'",
                        "'Time Healing', 'Sneak'"});
    }
}
