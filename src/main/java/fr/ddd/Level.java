package fr.ddd;

public enum Level {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    FOURTH(4),
    FIFTH(5);

    private int level;

    Level(int level) {
        this.level = level;
    }

    public boolean greaterThan(Level other) {
        return level >= other.level;
    }

    @Override
    public String toString() {
        return ""+level;
    }
    

}