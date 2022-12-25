import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

    List<TodoEntity> todos = new ArrayList<>();
    public TodoRepository() {
        TodoEntity todo = new TodoEntity();
        todo.subject = "Mock_data";
        todo.texts.add("Mock_data");
        todo.texts.add("Mock_data");
        todo.texts.add("Mock_data");
        todo.texts.add("Mock_data");
        todo.texts.add("Mock_data");
        todo.isActive = true;
        todos.add(todo);
    }
    public String getSubject(int index) {
        return todos.get(index).subject;
    }
    public List<String> getTexts(int index) {
        return todos.get(index).texts;
    }
    public Boolean getIsActive(int index) {
        return todos.get(index).isActive;
    }
    public List<TodoEntity> getTodos() {
        return todos;
    }
    public void addTodo(TodoEntity todo) {
        todos.add(todo);
    }
    public void updateTodo(int index, TodoEntity todo) {
        todos.set(index, todo);
    }
    public void deleteTodo(int index) {
        this.todos.remove(index);
    }

}