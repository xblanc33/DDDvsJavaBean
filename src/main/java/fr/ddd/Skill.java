package fr.ddd;

import java.util.Objects;

public class Skill {
    Name name;

    public Skill(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Skill)) return false;
        Skill otherSkill = (Skill) other;
        return name.equals(otherSkill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name.toString();
    }
}