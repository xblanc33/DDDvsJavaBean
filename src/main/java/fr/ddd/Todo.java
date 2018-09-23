package fr.ddd;

import java.util.Objects;

public class Todo {
    private String description;
    private boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Todo(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object other) {
        if (! (other instanceof Todo)) return false;
        Todo otherTodo = (Todo) other;
        boolean sameDescription =  otherTodo.getDescription().compareTo(description) == 0;
        boolean done = otherTodo.isDone() == isDone;
        return sameDescription && done;
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return description;
    }
}