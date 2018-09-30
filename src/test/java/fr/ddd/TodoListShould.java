package fr.ddd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TodoListShould {

    private static final String FIRST_TODO_DESCRIPTION = "code damn it";
    private static final String SECOND_TODO_DESCRPTION = "code twice damn it";

    private TodoList todos;
    private Todo codeDamnIt;
    private Todo codeDamnItTwice;

    @Before
    public void init() {
        todos = new TodoList();
        codeDamnIt = new Todo(FIRST_TODO_DESCRIPTION);
        codeDamnItTwice = new Todo(SECOND_TODO_DESCRPTION);
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
        assertFalse(codeDamnIt.isDone());
    }

    @Test
    public void add_many_todo_to_todo_list() {
        todos.addTodo(codeDamnIt);
        todos.addTodo(codeDamnItTwice);

        assertThatTodoListHas(2);
        assertFalse(codeDamnIt.isDone());
        assertFalse(codeDamnItTwice.isDone());
    }

    @Test
    public void pass_a_todo_status_to_done() {
        todos.addTodo(codeDamnIt);
        todos.addTodo(codeDamnItTwice);

        todos.didIt(codeDamnIt);

        SearchTodo search = new SearchTodo();
        List<Todo> undone = search.findUndoneTodo(todos.getTodoList());
        assertEquals(1, undone.size());
        List<Todo> done = search.findDoneTodo(todos.getTodoList());
        assertEquals(1, done.size());
    }

    private void assertThatTodoListHas(int todoCount) {
        assertFalse(todos.isEmpty());
        assertEquals(todoCount, todos.getTodoList().size());
    }
}