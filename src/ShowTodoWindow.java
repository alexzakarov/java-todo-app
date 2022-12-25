import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShowTodoWindow {
    private JFrame frame;
    private List<JTextField> textAreas = new ArrayList<>();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TodoRepository todoRepository;
    public int textSize = 1;
    private JPanel panel;

    public ShowTodoWindow(int width, int height, TodoRepository todoRepository, Boolean existTodoScreen1) {
        this.frame = new JFrame("Todo App");
        this.frame.setSize(width,height);
        this.panel = new JPanel();
        this.closeEvent(this.textAreas);
        this.todoRepository = todoRepository;
    }


    public void show() {
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }

    public void hide() {
        this.frame.setVisible(false);
    }
    public void addTextArea(int x, int y, int width, int height, int id) {
        JTextField textArea = new JTextField(5);
        textArea.setLocation(x,y);
        textArea.setSize(width,height);
        textArea.setName("todo"+id);
        textArea.setCaretColor(Color.BLACK);
        this.textAreas.add(textArea);
        this.frame.add(textArea);
        this.frame.repaint();
        //this.show();
    }
    public void updateTextArea(int x, int y, int width, int height, String text) {
        JTextField textArea = new JTextField();
        textArea.setLocation(x,y);
        textArea.setSize(width,height);
        textArea.setCaretColor(Color.BLACK);
        textArea.setText(text);
        this.textAreas.add(textArea);
        this.frame.add(textArea);
    }


    public void removeTextAreas() {
        int size = this.textAreas.size();
        for(int i = size-1; i >= 0; i--) {
            JTextField removingField = this.textAreas.get(i);
            this.textAreas.remove(i);
            this.frame.remove(removingField);
        }
        this.frame.repaint();
    }
    public void updateTextAreaButton(int x, int y, ShowTodoWindow showTodoWindow) {
        JButton b = new JButton("Add New Text");
        b.setLocation(x, y);
        b.setSize(150, 30);
        this.updateTextEventListener(b, showTodoWindow);
        this.frame.add(b);
    }


    public void updateButton(int x, int y, ShowTodoWindow showTodoWindow, MainWindow mainWindow) {
        JButton b = new JButton("Update");
        b.setLocation(x, y);
        b.setSize(150, 30);
        this.updateEventListener(b, showTodoWindow, mainWindow);
        this.frame.add(b);
    }

    public void updateText(int x, int y, String text) {
        JLabel label = new JLabel();
        label.setLocation(x,y);
        label.setText(text);
        label.setSize(200, 20);
        this.frame.add(label);
    }

    public void updateTextEventListener(JButton button, ShowTodoWindow showTodoWindow) {
        button.addActionListener(ActionListener -> {
            if(this.textSize < 15) {
                showTodoWindow.addTextArea(100,this.textSize*20+80,200,20, this.textSize);
                showTodoWindow.show();
                this.textSize++;
            }
        });
    }


    public void updateEventListener(JButton button, ShowTodoWindow showTodoWindow, MainWindow mainWindow) {
        button.addActionListener(ActionListener -> {
            int size = this.textAreas.size();
            TodoEntity todo = new TodoEntity();
            todo.subject = this.textAreas.get(0).getText();
            for(int i = 1; i < size; i++) {
                todo.texts.add(this.textAreas.get(i).getText());
            }
            todo.isActive = true;
            System.out.println(mainWindow.updatingButton);
            todoRepository.updateTodo(mainWindow.updatingButton, todo);
            showTodoWindow.removeTextAreas();
            showTodoWindow.textSize = 1;
            mainWindow.showTodos();
            this.hide();

        });
    }

    public void closeEvent(List<JTextField> textAreas) {
        ShowTodoWindow todoWindow = this;
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    todoWindow.removeTextAreas();
                    todoWindow.textSize = 1;
                    todoWindow.hide();

                }
            }
        });
    }

}