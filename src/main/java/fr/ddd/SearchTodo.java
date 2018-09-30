package fr.ddd;

import java.util.ArrayList;
import java.util.List;

public class SearchTodo {
    public List<Todo> findUndoneTodo(List<Todo> todoList) {
        return findTodo(false, todoList);
    }

    public List<Todo> findDoneTodo(List<Todo> todoList) {
        return findTodo(true, todoList);
    }

    private List<Todo> findTodo(boolean isDone, List<Todo> todoList) {
        List<Todo> list = new ArrayList<>();
        for (Todo todo : todoList) {
            if (todo.isDone() == isDone) {
                list.add(todo);
            }
        }
        return list;
    }
}