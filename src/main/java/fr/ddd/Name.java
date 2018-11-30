package fr.ddd;

import java.util.Objects;

public class Name {
    private String value;

    public Name(String value) {
        if (value == null) throw new IllegalArgumentException("Name should not be null");
        if (value.length() > 20) throw new IllegalArgumentException("Length of Name should be 20 maximum");
        this.value = value;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Name)) return false;
        Name otherName = (Name) other;
        return value.compareTo(otherName.value)==0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
