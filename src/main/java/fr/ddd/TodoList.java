package fr.ddd;

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
        if (todoList.contains(t)) throw new IllegalArgumentException("Todo already in the list");
        todoList.add(t);
    }

    public void removeTodo(Todo t) {
        todoList.remove(t);
    }

    public void didIt(Todo todo) {
        if (!todoList.contains(todo)) throw new IllegalArgumentException("Todo not in the list");
        int index = todoList.lastIndexOf(todo);
        todoList.remove(index);
        todoList.add(index, new Todo(todo.getDescription(), true));
    }
}