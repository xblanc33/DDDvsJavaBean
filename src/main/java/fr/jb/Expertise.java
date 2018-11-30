package fr.jb;

public class Expertise {
    private Skill skill;
    private Level level;

    public Expertise (Skill skill, Level level) {
        if (skill == null) throw new IllegalArgumentException("Skill should not be null");
        if (level == null) throw new IllegalArgumentException("Level should not be null");
        this.skill = skill;
        this.level = level;
    }

    public Skill getSkill() {
        return skill;
    }

    public Level getLevel() {
        return level;
    }
}