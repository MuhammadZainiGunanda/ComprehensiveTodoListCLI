package muhammadzainigunanda.com.repositories;

import java.sql.SQLException;
import java.util.List;
import muhammadzainigunanda.com.model.TodoList;

public interface TodoListRepository {
     
     List<TodoList> fetchAllTodos() throws SQLException;

     void addTodo(TodoList todoList) throws SQLException;

     boolean removeTodoById(Integer todoId) throws SQLException;
 
}
