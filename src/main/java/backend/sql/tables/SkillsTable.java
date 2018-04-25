package backend.sql.tables;


/**
 * Created by zlmonroe on 4/21/2018.
 */
public class SkillsTable extends Table {

    public SkillsTable() {
        super("Skill", "name",
                new String[] {
                        "name VARCHAR(20) NOT NULL",
                        "level INTEGER NOT NULL",
                        "coolDown INTEGER",
                        "manaCost INTEGER NOT NULL"}, null,
                new String[] {
                        "'Berzerk', 5, 6, 6",
                        "'Endulge', 2, 1, 2",
                        "'Rally', 4, 4, 4",
                        "'Preach', 3, 1, 2",
                        "'Sneak', 6, 2, 4",
                        "'Sprint', 2, 7, 1",
                        "'Infect', 7, 4, 10"});
    }
}
