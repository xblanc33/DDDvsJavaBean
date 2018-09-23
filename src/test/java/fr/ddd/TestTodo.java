package fr.ddd;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class TestTodo {

    @Test
    public void test() {
        TodoList todoList = new TodoList();
        Todo todo1 = new Todo("code damn it");
        todoList.addTodo(todo1);
        Todo todo2 = new Todo("code twice damn it");
        todoList.addTodo(todo2);
        assertEquals(2, todoList.getTodoList().size());

        todoList.didIt(todo1);
        
        SearchTodo search = new SearchTodo();
        List<Todo> undone = search.findUndoneTodo(todoList.getTodoList());
        assertEquals(1, undone.size());

        List<Todo> done = search.findDoneTodo(todoList.getTodoList());
        assertEquals(1, done.size());
    }
}