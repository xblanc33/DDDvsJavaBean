package fr.jb;

public class Todo {
    private String description;
    private boolean done;

    public Todo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}