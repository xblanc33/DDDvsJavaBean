package fr.ddd;

import java.util.Objects;

public class Room {
    Name name;

    public Room(String name) {
        this.name = new Name(name);
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Room)) return false;
        Room otherRoom = (Room) other;
        return name.equals(otherRoom.name);
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