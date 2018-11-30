package fr.ddd;

import java.util.Objects;

public class Expertise {
    private Skill skill;
    private Level level;

    public Expertise (Skill skill, Level level) {
        if (skill == null) throw new IllegalArgumentException("Skill should not be null");
        if (level == null) throw new IllegalArgumentException("Level should not be null");
        this.skill = skill;
        this.level = level;
    }

    public Skill skill() {
        return skill;
    }

    public Level level() {
        return level;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Expertise)) return false;
        Expertise otherExpertise = (Expertise) other;
        return skill.equals(otherExpertise.skill) && level.equals(otherExpertise.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        return skill.toString()+" "+level.toString();
    }
}