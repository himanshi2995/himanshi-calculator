



import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class calculator extends JFrame implements ActionListener {
    JTextField display;
    String operator;
    double num1, num2, result;

    public calculator() {
        // Frame settings
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        add(display, BorderLayout.NORTH);

        // Button panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("[0-9]")) {
            display.setText(display.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) {
            try {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (cmd.equals("=")) {
            try {
                num2 = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/": 
                        if (num2 == 0) {
                            display.setText("Error");
                            return;
                        }
                        result = num1 / num2; break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
        } else if (cmd.equals("C")) {
            display.setText("");
            num1 = num2 = result = 0;
        }
    }

    public static void main(String[] args) {
        new calculator();
    }
}