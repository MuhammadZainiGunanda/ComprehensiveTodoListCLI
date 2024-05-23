package muhammadzainigunanda.com.service;

import java.sql.SQLException;
import java.util.List;
import muhammadzainigunanda.com.model.TodoList;
import muhammadzainigunanda.com.repositories.TodoListRepository;

public class TodoListServiceimpl implements TodoListService {

     private TodoListRepository todoListRepository;

     public TodoListServiceimpl(TodoListRepository todoListRepository) {
          this.todoListRepository = todoListRepository;
     }

     @Override
     public void showTodoList() throws SQLException {
          List<TodoList> todoLists = todoListRepository.fetchAllTodos();

          System.out.println("TODOLISTS");

          todoLists.forEach(data -> {
               System.out.println(data.getId() + ". " + data.getTodo());
          });
     }

     @Override
     public void addTodoList(String todo) throws SQLException {
          todoListRepository.addTodo(new TodoList(todo));
          System.out.println("Successfully adding todo!");
     }

     @Override
     public void removeTodoList(Integer todoId) throws SQLException {
          boolean isSuccess = todoListRepository.removeTodoById(todoId);

          if (isSuccess) {
               System.out.println("Successfully removed todo!");
          } else {
               System.out.println("Failed to delete todo..");
          }
     }
     
}
