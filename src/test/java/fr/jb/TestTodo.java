package fr.jb;

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

        todo1.setDone(true);
        
        List<Todo> undone = todoList.getUndoneTodo();
        assertEquals(1, undone.size());
        
        List<Todo> done = todoList.getDoneTodo();
        assertEquals(1, done.size());
    }
}