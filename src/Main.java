import java.awt.*;


public class Main {

    private TodoRepository todoRepository;
    public static void main(String args[]) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        TodoRepository todoRepository = new TodoRepository();

        MainWindow mainWindow = new MainWindow(screenSize.width, screenSize.height, todoRepository, false);
        TodoWindow mainWindow2 = new TodoWindow(400, 500, todoRepository, false);
        ShowTodoWindow mainWindow3 = new ShowTodoWindow(400, 500, todoRepository, false);
        mainWindow.setTodoWindow(mainWindow2);
        mainWindow.setShowTodoWindow(mainWindow3);
        mainWindow.show();
    }
}



