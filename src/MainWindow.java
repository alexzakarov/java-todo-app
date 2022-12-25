import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

class MainWindow extends JFrame {

    private JFrame frame;
    private boolean existTodoScreen = false;

    private List<JTextField> textAreas = new ArrayList<>();
    private List<JLabel> textLabels = new ArrayList<>();
    private List<JButton> updateButtons = new ArrayList<>();
    private List<JButton> deactivateTodoButtons = new ArrayList<>();
    private List<JButton> activateTodoButtons = new ArrayList<>();
    public int updatingButton;

    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TodoRepository todoRepository;
    private TodoWindow todoWindow;
    private ShowTodoWindow showTodoWindow;
    private boolean todoButtonChecked = false;
    public MainWindow(int width, int height, TodoRepository todoRepository, Boolean existTodoScreen1) {
        this.todoRepository = todoRepository;
        this.frame = new JFrame("Todo App");
        this.frame.setSize(width,height);
        this.closeEvent();
        this.showTodos();
        this.show();
    }

    public void setTodoWindow(TodoWindow todoWindow) {
        this.todoWindow = todoWindow;
    }
    public void setShowTodoWindow(ShowTodoWindow showTodoWindow) {
        this.showTodoWindow = showTodoWindow;
    }
    public JFrame getFrame() {
        return this.frame;
    }

    public void show() {
        this.frame.setLayout(null);
        this.frame.setVisible(true);
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
    public void showTodos() {
        this.addTodoButton((int)(float)(screenSize.width/2.4),(int)((float)screenSize.height*0.85));

        for(int i = 0; i < this.textLabels.size(); i++) {
            this.frame.remove(this.textLabels.get(i));
        }
        for(int i = 0; i < this.updateButtons.size(); i++) {
            this.frame.remove(this.updateButtons.get(i));
        }
        for(int i = 0; i < this.deactivateTodoButtons.size(); i++) {
            this.frame.remove(this.deactivateTodoButtons.get(i));
        }
        for(int i = 0; i < this.activateTodoButtons.size(); i++) {
            this.frame.remove(this.activateTodoButtons.get(i));
        }

        int size = todoRepository.todos.size();
        this.addText(200, 150,"subject");
        this.addText(600, 150,"Is Active");
        this.addText(1030, 150, "Update");
        this.addText(1230, 150, "Deactivate");
        this.addText(1430, 150, "Activate");
        for(int i = 0; i < size; i++) {
            String subject = todoRepository.todos.get(i).subject;
            String isActive = "";
            if(todoRepository.todos.get(i).isActive) {
                isActive = "active";
            } else {
                isActive = "deactivate";
            }
            this.addText(200, i*50+200,subject);
            this.addText(600, i*50+200,isActive);
            this.addUpdateButton(1000, i*50+200, i);
            this.deactivateTodoButton(1200, i*50+200, i);
            this.activateTodoButton(1400, i*50+200, i);
        }
        this.frame.repaint();

    }
    public void addText(int x, int y, String text) {
        JLabel label = new JLabel();
        label.setLocation(x,y);
        label.setText(text);
        label.setSize(200, 20);
        this.textLabels.add(label);
        this.frame.add(label);
    }
    public JButton addTodoButton(int x, int y) {
        JButton b = new JButton("Add New Todo");
        b.setLocation(x, y);
        b.setSize(200, 30);
        this.addTodoEventListener(b);
        this.frame.add(b);
        return b;
    }

    public JButton addUpdateButton(int x, int y, int id) {
        JButton b = new JButton("Update");
        b.setLocation(x, y);
        b.setSize(130, 20);
        b.setName("todo"+id);
        this.updateEventListener(b, id);
        this.updateButtons.add(b);
        this.frame.add(b);
        return b;
    }


    public JButton deactivateTodoButton(int x, int y, int todoId) {
        JButton b = new JButton("Deactivate");
        b.setLocation(x, y);
        b.setSize(130, 20);
        this.deactivateTodoButtons.add(b);
        this.deactivateTodoEventListener(b, todoId);
        this.frame.add(b);
        return b;
    }

    public JButton activateTodoButton(int x, int y, int todoId) {
        JButton b = new JButton("Activate");
        b.setLocation(x, y);
        b.setSize(130, 20);
        this.activateTodoButtons.add(b);
        this.activateTodoEventListener(b, todoId);
        this.frame.add(b);
        return b;
    }
    public void addTodoEventListener(JButton button) {
        button.addActionListener(ActionListener -> {
            todoWindow.addTextAreaButton(50,400, todoWindow);
            todoWindow.saveButton(200,400, todoWindow, this);
            todoWindow.addText(100,20, "Subject", todoWindow);
            todoWindow.addTextArea(100,40,200,20, 0);
            todoWindow.addText(100,60, "Todos", todoWindow);
            todoWindow.addTextArea(100,80,200,20, 1);
            todoWindow.show();
        });
    }

    public void deactivateTodoEventListener(JButton button, int todoId) {
        button.addActionListener(ActionListener -> {
            todoRepository.todos.get(todoId).isActive = false;
            this.showTodos();
        });
    }
    public void activateTodoEventListener(JButton button, int todoId) {
        button.addActionListener(ActionListener -> {
            todoRepository.todos.get(todoId).isActive = true;
            this.showTodos();
        });
    }

    public void updateTodo() {
        showTodoWindow.updateTextAreaButton(50,400, showTodoWindow);
        showTodoWindow.updateButton(200,400, showTodoWindow, this);
        showTodoWindow.updateText(100,20, "Subject");
        showTodoWindow.updateTextArea(100,40,200,20, todoRepository.todos.get(this.updatingButton).subject);
        showTodoWindow.updateText(100,60, "Todos");
        System.out.println(todoRepository.todos.get(this.updatingButton).texts);
        int size = todoRepository.todos.get(this.updatingButton).texts.size();
        for(int i = 0; i < size; i++) {
            String text = todoRepository.todos.get(this.updatingButton).texts.get(i);
            showTodoWindow.updateTextArea(100,i*20+80,200,20, text);
        }
        showTodoWindow.textSize = todoRepository.todos.get(this.updatingButton).texts.size();
        showTodoWindow.show();
    }
    public void updateEventListener(JButton button, int todoId) {
        button.addActionListener(ActionListener -> {
            this.updatingButton = todoId;
            this.updateTodo();
        });
    }

    public void closeEvent() {
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to close this window?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
                    System.exit(0);
                }
            }
        });
    }

}
