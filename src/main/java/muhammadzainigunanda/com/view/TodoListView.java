package muhammadzainigunanda.com.view;

import java.sql.SQLException;

import muhammadzainigunanda.com.service.TodoListService;
import muhammadzainigunanda.com.util.InputUtil;

public class TodoListView {

     private TodoListService todoListService;

     public TodoListView(TodoListService todoListService) {
          this.todoListService = todoListService;
     }

     public void showTodoList() throws SQLException {
          while (true) {
               todoListService.showTodoList();

               System.out.println("MENU : ");
               System.out.println("1. Add");
               System.out.println("2. Remove");
               System.out.println("x. Out");

               String input = InputUtil.input("What you want to do!!");

               if (input.equals("1")) {
                    submitAddTodoList();
               } else if (input.equals("2")) {
                    submitRemoveTodoList();
               } else if (input.equals("x")) {
                    System.exit(0);
               } else {
                    System.out.println(
                         "Sorry system doesn't understand"
                    );
               }
          }
     }

     public void submitAddTodoList() throws SQLException {
          System.out.println("Adding Todolist");

          String inputTodo = InputUtil.input(
               "Do? (type 'x' if you want to cancel)"
          );

          if (inputTodo.equals("x")) {
               showTodoList();
          } else {
               todoListService.addTodoList(inputTodo);
          }
     }

     public void submitRemoveTodoList() throws NumberFormatException, SQLException {
          System.out.println("Removed Todolist");

          String inputId = InputUtil.input(
               "Type number you want to delete! (type 'x' if you want to cancel)"
          );

          if (inputId.equals("x")) {
               showTodoList();
          } else {
               todoListService.removeTodoList(Integer.valueOf(inputId));
          }
     }

}
