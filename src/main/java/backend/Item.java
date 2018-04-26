package backend;

public class Item {
    private String name;
    private int weight;
    private double effectMultiplier;

    private String effect;

    public Item(String n) {
        name = n;
        loadItem(n);
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public double getEffectMultiplier() {
        return effectMultiplier;
    }

    public String getEffect() {
        return effect;
    }

    private boolean loadItem(String n) {
        //TODO

        loadEffect();
        return false;
    }

    private boolean loadEffect() {
        //TODO
        return false;
    }
}
