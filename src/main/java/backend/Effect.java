package backend;

import backend.sql.GameServer;
import backend.sql.SQLActions.GeneralQuery;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Effect {
    private String name;
    private int durration;
    private int mana;
    private double manap;
    private int hp;
    private double hpp;
    private int attack;
    private double attackp;
    private int defense;
    private double defensep;
    private int sight;
    private double sightp;
    private int strength;
    private double strengthp;

    private GameServer gs;

    public Effect(String name){
        gs = CurrentContext.getGameServer();
        this.name = name;
        loadEffect();
    }

    private void loadEffect(){
        ResultSet e = gs.querry(new GeneralQuery("effect", "name", name));
        try {
            if (e.next()){
                durration = e.getInt("durration");
                mana = e.getInt("mana");
                manap = e.getDouble("manap");
                hp = e.getInt("hp");
                hpp = e.getDouble("hpp");
                attack = e.getInt("attack");
                attackp = e.getDouble("attackp");
                defense = e.getInt("defense");
                defensep = e.getDouble("defensep");
                sight = e.getInt("sight");
                sightp = e.getDouble("sightp");
                strength = e.getInt("strength");
                strengthp = e.getDouble("strengthp");
            }
        } catch (SQLException sqlE){
            sqlE.printStackTrace();
            sqlE.getMessage();
        }
    }

    public String getName() {
        return name;
    }

    public int getDurration() {
        return durration;
    }

    public int getMana() {
        return mana;
    }

    public double getManaPerc() {
        return manap;
    }

    public int getHp() {
        return hp;
    }

    public double getHpPerc() {
        return hpp;
    }

    public int getAttack() {
        return attack;
    }

    public double getAttackPerc() {
        return attackp;
    }

    public int getDefense() {
        return defense;
    }

    public double getDefensePerc() {
        return defensep;
    }

    public int getSight() {
        return sight;
    }

    public double getSightPerc() {
        return sightp;
    }

    public int getStrength() {
        return strength;
    }

    public double getStrengthPerc() {
        return strengthp;
    }
}
