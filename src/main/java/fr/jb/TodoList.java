package fr.jb;

import java.util.ArrayList;
import java.util.List;

public class TodoList {
    private List<Todo> todoList;

    public TodoList() {
        todoList = new ArrayList<>();
    }

    public List<Todo> getTodoList() {
        return new ArrayList<>(this.todoList);
    }

    public void addTodo(Todo t) {
        if (todoList.contains(t)) throw new IllegalArgumentException("Todo already in the List");
        todoList.add(t);
    }

    public void removeTodo(Todo t) {
        todoList.remove(t);
    }

    public List<Todo> getUndoneTodo() {
        return getTodo(false);
    }

    public List<Todo> getDoneTodo() {
        return getTodo(true);
    }

    private List<Todo> getTodo(boolean isDone) {
        List<Todo> List = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.getDone() == isDone) {
                List.add(todo);
            }
        }
        return List;
    }

    public boolean isEmpty() {
        return todoList.isEmpty();
    }
}