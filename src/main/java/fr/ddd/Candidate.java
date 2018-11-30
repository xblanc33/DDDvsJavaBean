package fr.ddd;

import java.util.Objects;

public class Candidate extends ProfiledPeople {
    private PhoneNumber number;

    public Candidate(String first, String last,  Profile profile, String number) {
        super(first, last, profile);
        this.number = new PhoneNumber(number);
    }

    public PhoneNumber phoneNumber() {
        return number;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Candidate)) return false;
        Candidate otherCandidate = (Candidate) other;
        boolean sameFirst = firstName().equals(otherCandidate.firstName());
        boolean sameLast = lastName().equals(otherCandidate.lastName());
        boolean sameNumber = number.equals(otherCandidate.number);
        return sameFirst && sameLast && sameNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toString());
    }

    @Override
    public String toString() {
        String candidateString = firstName().toString() 
            + ";" + lastName().toString() 
            + ";" + number.toString();
        return candidateString;
    }


}