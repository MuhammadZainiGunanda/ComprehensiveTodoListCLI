package muhammadzainigunanda.com.model;

public class TodoList {
     
     private Integer id;

     private String todo;

     public TodoList(String todo) {
          this.todo = todo;
     }

     public TodoList() {

     }

     public Integer getId() {
          return id;
     }

     public void setId(Integer id) {
          this.id = id;
     }

     public String getTodo() {
          return todo;
     }

     public void setTodo(String todo) {
          this.todo = todo;
     }

     @Override
     public String toString() {
          return "TodoList [id=" + id + ", todo=" + todo + "]";
     }

     @Override
     public int hashCode() {
          final int prime = 31;
          int result = 1;
          result = prime * result + ((id == null) ? 0 : id.hashCode());
          result = prime * result + ((todo == null) ? 0 : todo.hashCode());
          return result;
     }

     @Override
     public boolean equals(Object obj) {
          if (this == obj)
               return true;
          if (obj == null)
               return false;
          if (getClass() != obj.getClass())
               return false;
          TodoList other = (TodoList) obj;
          if (id == null) {
               if (other.id != null)
                    return false;
          } else if (!id.equals(other.id))
               return false;
          if (todo == null) {
               if (other.todo != null)
                    return false;
          } else if (!todo.equals(other.todo))
               return false;
          return true;
     }

}
