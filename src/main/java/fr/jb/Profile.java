package fr.jb;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Profile {
    private Set<Expertise> expertiseSet;

    public Profile(Set<Expertise> expertiseSet) {
        if (expertiseSet == null) throw new IllegalArgumentException("Set of expertise should not be null");
        this.expertiseSet = new HashSet<Expertise>(expertiseSet);
    }

    public boolean has(Expertise expected) {
        for (Expertise owned : expertiseSet) {
            boolean sameSkill = owned.skill().equals(expected.skill());
            boolean ownedLevel = owned.level().greaterThan(expected.level());
            if ( sameSkill && ownedLevel ) return true;
        }
        return false;
    }

    public boolean contains(Profile contained) {
        for (Expertise containedExpertise : contained.expertiseSet) {
            if (! has(containedExpertise)) return false;
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Profile)) return false;
        Profile otherProfile = (Profile) other;
        return expertiseSet.equals(otherProfile.expertiseSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        String prettyString = "";
        for (Expertise expertise : expertiseSet) {
            prettyString = prettyString + expertise.skill().toString() + expertise.level().toString();
        }
        return prettyString;
    }
}