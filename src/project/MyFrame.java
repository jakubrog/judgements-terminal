package project;


import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super("Hello World");
        setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,400);
        setVisible(true);
        setResizable(false);
        JTextField nameField = new JTextField();
        String s = nameField.getText();
    }
}