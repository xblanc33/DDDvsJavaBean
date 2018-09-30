package fr.jb;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TodoListShould {

    private TodoList todos;
    private Todo codeDamnIt;
    private Todo codeDamnItTwice;

    @Before
    public void init() {
        todos = new TodoList();
        codeDamnIt = new Todo("code damn it");
        codeDamnItTwice = new Todo("code twice damn it");
    }

    @Test
    public void create_an_empty_list() {
        assertNotNull(todos);
        assertTrue(todos.isEmpty());
    }

    @Test
    public void add_one_todo_to_todo_list() {
        todos.addTodo(codeDamnIt);

        assertThatTodoListHas(1);
        assertFalse(codeDamnIt.getDone());
    }

    @Test
    public void add_many_todo_to_todo_list() {
        todos.addTodo(codeDamnIt);
        todos.addTodo(codeDamnItTwice);

        assertThatTodoListHas(2);
        assertFalse(codeDamnIt.getDone());
        assertFalse(codeDamnItTwice.getDone());
    }

    @Test
    public void pass_a_todo_status_to_done() {
        todos.addTodo(codeDamnIt);
        todos.addTodo(codeDamnItTwice);

        codeDamnIt.setDone(true);

        List<Todo> undone = todos.getUndoneTodo();
        assertEquals(1, undone.size());
        List<Todo> done = todos.getDoneTodo();
        assertEquals(1, done.size());
    }

    private void assertThatTodoListHas(int todoCount) {
        assertFalse(todos.isEmpty());
        assertEquals(todoCount, todos.getTodoList().size());
    }
}