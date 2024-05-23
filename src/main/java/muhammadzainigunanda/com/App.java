package muhammadzainigunanda.com;

import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;
import muhammadzainigunanda.com.repositories.TodoListRepository;
import muhammadzainigunanda.com.repositories.TodoListRepositoryImpl;
import muhammadzainigunanda.com.service.TodoListService;
import muhammadzainigunanda.com.service.TodoListServiceimpl;
import muhammadzainigunanda.com.util.DatabaseUtil;
import muhammadzainigunanda.com.view.TodoListView;

public class App {
    
    public static void main(String[] args) {
        // Base
        HikariDataSource hikariDataSource = DatabaseUtil.getDataSource();
        TodoListRepository todoListRepository = new TodoListRepositoryImpl(hikariDataSource);
        TodoListService todoListService = new TodoListServiceimpl(todoListRepository);
        TodoListView todoListView = new TodoListView(todoListService);

        // Core
        try {
            todoListView.showTodoList();
        } catch (SQLException exception) {
            System.out.println("System Errors: " + exception.getMessage());
            System.exit(0);
        }
    }

}
