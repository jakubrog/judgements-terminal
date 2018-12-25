package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class MyGUIForm extends JFrame {
    private JTextField commandField;
    private JTextPane resultArea;
    private JPanel mainPanel;
    private JLabel prompt;
    private CustomizedList lastCommands = new CustomizedList();

    public MyGUIForm() {

        add(mainPanel);
        setSize(new Dimension(600, 800));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        resultArea.setContentType("text/html");
        resultArea.setEditable(false);

        commandField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (e.getKeyCode() == VK_UP) {
                    commandField.setText(lastCommands.arrowUp());
                }
                if (e.getKeyCode() == VK_DOWN)
                    commandField.setText(lastCommands.arrowDown());

                if (e.getKeyCode() == VK_ENTER) {
                    resultArea.setText(lastCommands.enter(commandField.getText()));
                    commandField.setText("");
                }
            }

        });
    }
}
