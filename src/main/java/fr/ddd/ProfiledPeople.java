package fr.ddd;

import java.util.Objects;

public class ProfiledPeople {
    private Name first;
    private Name last;
    private Profile profile;

    public ProfiledPeople(String first, String last, Profile profile) {
        this.first = new Name(first);
        this.last = new Name(first);
        if (profile == null) throw new IllegalArgumentException("profile should not be null");
        this.profile = profile;
    }

    public Name firstName() {
        return first;
    }

    public Name lastName() {
        return last;
    }

    public Profile profile() {
        return profile;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof ProfiledPeople)) return false;
        ProfiledPeople otherProfiledPeople = (ProfiledPeople) other;
        boolean sameFirst = first.equals(otherProfiledPeople.first);
        boolean sameLast = last.equals(otherProfiledPeople.last);
        boolean sameProfile = profile.equals(otherProfiledPeople.profile);
        return sameFirst && sameLast && sameProfile;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        String ProfiledPeopleString = first.toString() 
            + ";" + last.toString()
            + ";" + profile.toString();
        return ProfiledPeopleString;
    }


}