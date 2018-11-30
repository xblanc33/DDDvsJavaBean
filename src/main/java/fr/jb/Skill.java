package fr.jb;

public class Skill {
    private String name;

    public Skill(String name) {
        if (name == null) throw new IllegalArgumentException("name cannot be null");
        this.name = name;
    }

    public String getName() {
        return name;
    }

}