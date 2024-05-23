package muhammadzainigunanda.com.service;

import java.sql.SQLException;

public interface TodoListService {
     
     void showTodoList() throws SQLException;

     void addTodoList(String todo) throws SQLException;

     void removeTodoList(Integer integer) throws SQLException;

}
