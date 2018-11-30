package fr.ddd;

import java.util.Arrays;
import java.util.Objects;

public class PhoneNumber {
    private int[] value = new int[10];

    public PhoneNumber(String value) {
        if (value == null) throw new IllegalArgumentException("PhoneNumber should not be null");
        if (value.length() != 10) throw new IllegalArgumentException("Length of PhoneNumber should be 10");
        for (int i=0 ; i < 10 ; i++) {
            this.value[i] = Integer.valueOf(value.charAt(i)).intValue();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof PhoneNumber)) return false;
        PhoneNumber otherPhoneNumber = (PhoneNumber) other;
        for (int i=0 ; i < 10 ; i++) {
            if (this.value[i]!=otherPhoneNumber.value[i]) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.value.toString());
    }

    @Override
    public String toString() {
        return Arrays.toString(value);
    }
}