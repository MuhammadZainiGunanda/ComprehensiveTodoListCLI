package muhammadzainigunanda.com.repositories;

import java.sql.SQLException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import com.zaxxer.hikari.HikariDataSource;
import muhammadzainigunanda.com.model.TodoList;
import muhammadzainigunanda.com.util.DatabaseUtil;

@DisplayName("A TodoList")
public class TodoListRepositoryTest {

     private static HikariDataSource dataSource;

     private static TodoListRepository todoListRepository;

     @BeforeAll
     public static void setUp() throws SQLException {
          dataSource = DatabaseUtil.getDataSource();
          todoListRepository = new TodoListRepositoryImpl(dataSource);
     }

     @AfterAll
     public static void tearDown() throws SQLException {
          dataSource.close();
     }

     @Nested
     @DisplayName("when add todo, ")
     public class AddTodo {

          @BeforeEach
          public void beforeEach() {
               dataSource.close();
          }

          @AfterEach
          public void afterEach() throws SQLException {
               var result = todoListRepository.removeTodoById(2);
               Assertions.assertTrue(result);
          }

          @Test
          @DisplayName("should be able to success add todo")
          public void addTodoSuccess() throws SQLException {
               TodoList newTodo = new TodoList("Example todo");
               todoListRepository.addTodo(newTodo);
          }

          @Test
          @DisplayName("should reject if connector is failed")
          public void addTodoFailed() throws SQLException {
               TodoList newTodo = new TodoList("Example todo");
               Assertions.assertThrowsExactly(SQLException.class, () -> {
                    todoListRepository.addTodo(newTodo);
               });
          }

     }

     @Nested
     @DisplayName("when remove todo, ")
     public class RemoveTodo {
          
          @BeforeEach
          public void beforeEach() throws SQLException {
               TodoList newTodo = new TodoList("Example todo");
               todoListRepository.addTodo(newTodo);
          }

          @Test
          @DisplayName("should be able to success remove todo")
          public void removeTodoSuccess() throws SQLException {
               var isRemove = todoListRepository.removeTodoById(3);
               Assertions.assertTrue(isRemove);
          }

          @Test
          @DisplayName("should reject if id invalid")
          public void remoceTodoFailed() throws SQLException {
               var isRemove = todoListRepository.removeTodoById(3);
               Assertions.assertFalse(isRemove);
          }

     }

     @Nested
     @DisplayName("when get all todo,")
     public class GetAllTodo {

          @BeforeEach
          public void beforeEach() throws SQLException {
               todoListRepository.addTodo(new TodoList("Example todo 1"));
               todoListRepository.addTodo(new TodoList("Example todo 1"));
               todoListRepository.addTodo(new TodoList("Example todo 1"));
          }

          @AfterEach
          public void afterEach() throws SQLException {
               for (var index = 7; index <= 9; index++) {
                    todoListRepository.removeTodoById(index);
               }
          }

          @Test
          @DisplayName("should be abel to get todolist data")
          public void getAllTodoSuccess() throws SQLException {
               List<TodoList> todoLists = todoListRepository.fetchAllTodos();
               Assertions.assertEquals(3, todoLists.size());

               todoLists.forEach(todo -> System.out.println(todo.getId() + ": " + todo.getTodo()));
          }

          @Test
          @DisplayName("it should get blank, because the data doesn't exist")
          public void getAllTodoIsBlank() throws SQLException {
               List<TodoList> todoLists = todoListRepository.fetchAllTodos();
               Assertions.assertEquals(0, todoLists.size());
          }

     }
     
}
