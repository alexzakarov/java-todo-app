import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TodoWindowcopy {
    private JFrame frame;
    private JButton btnAddField;
    private JTextField txTodoField;
    private String nmField;
    private int cntField;
    private List<JTextField> textAreas = new ArrayList<>();
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private TodoRepository todoRepository;
    private int textSize = 1;
    private JPanel panel;



    public TodoWindowcopy() {
        nmField = "tField";
        cntField = 0;
    }

    private void showForm() {
        frame = new JFrame("Todo Windows");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setLayout(null);
        btnAddField = new JButton("Add Field");
        btnAddField.setLocation(100, 200);
        btnAddField.setSize(200, 30);
        btnAddField.addActionListener(ActionListener -> {
            txTodoField = new JTextField();
            txTodoField.setName(nmField + cntField);
            txTodoField.setLocation(100,this.textSize*20);
            cntField++;
            frame.add(txTodoField);
            frame.repaint();
        });
        frame.add(btnAddField);
        //frame.pack();
        //frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoWindowcopy().showForm();
            }
        });
    }
    /*


    public void addTextAreaButton(int x, int y, TodoWindow todoWindow) {
        JButton b = new JButton("Add New Text");
        b.setLocation(x, y);
        b.setSize(200, 30);
        this.addTextEventListener(b, todoWindow);
        this.frame.add(b);
    }
    public void addTextEventListener(JButton button, TodoWindow todoWindow) {
        button.addActionListener(ActionListener -> {


            todoWindow.addTextArea(100,this.textSize*20+100,200,20);
            todoWindow.show();
            this.textSize++;
        });

    }*/
}
