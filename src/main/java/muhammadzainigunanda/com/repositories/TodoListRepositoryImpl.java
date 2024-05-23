package muhammadzainigunanda.com.repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.zaxxer.hikari.HikariDataSource;
import muhammadzainigunanda.com.model.TodoList;

public class TodoListRepositoryImpl implements TodoListRepository {

     private HikariDataSource dataSource;

     public TodoListRepositoryImpl(HikariDataSource dataSource) {
          this.dataSource = dataSource;
     }

     @Override
     public List<TodoList> fetchAllTodos() throws SQLException {
          var getTodo = "SELECT * FROM todolists";

          try (var connection = dataSource.getConnection();
               var getStatement = connection.createStatement();
               var resultSet = getStatement.executeQuery(getTodo)) {
               
               List<TodoList> todoLists = new ArrayList<>();

               while (resultSet.next()) {
                    var todo = new TodoList();
                    todo.setId(resultSet.getInt("id"));
                    todo.setTodo(resultSet.getString("todo"));
     
                    todoLists.add(todo);
               }
     
               return todoLists;
          }
     }

     @Override
     public void addTodo(TodoList todoList) throws SQLException {
          var createTodo = "INSERT INTO todolists(todo) VALUES (?)";

          try (var connection = dataSource.getConnection();
               var createStatement = connection.prepareStatement(createTodo)) {
               createStatement.setString(1, todoList.getTodo());
               createStatement.executeUpdate();
          }
     }

     @Override
     public boolean removeTodoById(Integer todoId) throws SQLException {
          var checkExistsTodo = "SELECT id FROM todolists WHERE id = ?";
          var deleteTodo = "DELETE FROM todolists WHERE id = ?";

          try(var connection = dataSource.getConnection()) {
               connection.setAutoCommit(false);

               try (var checkPreparedStatement = connection.prepareStatement(checkExistsTodo)) {
                    checkPreparedStatement.setInt(1, todoId);

                    try (var resultSet = checkPreparedStatement.executeQuery()) {
                         if (resultSet.next()) {
                              try (var deletePreparedStatement = connection.prepareStatement(deleteTodo)) {
                                   deletePreparedStatement.setInt(1, resultSet.getInt("id"));
                                   deletePreparedStatement.executeUpdate();
                                   connection.commit();

                                   return true;
                              }
                         } else {
                              connection.rollback();
                              return false;
                         }
                    }
               }
          }
     }
     
}
