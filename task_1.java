import java.util.*;
import javax.swing.*;
import java.awt.*;

class task_1 {
    public static void main(String[] args) {
        Calculator obj = new Calculator();
    }
}

class Calculator extends JFrame {
    
    JTextField tf = new JTextField(25);
    JButton[] numButtons = new JButton[10];
    JButton delButton, addButton, subButton, mulButton, divButton, eqButton;

    Calculator() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(4, 4));

        // Add text field
        add(tf, BorderLayout.NORTH);
        tf.setPreferredSize(new Dimension(300, 50));
        tf.setFont(new Font("Arial", Font.PLAIN, 25)); // set the size 

        // Create number buttons
        for (int i = 0; i <= 9; i++) {
            numButtons[i] = new JButton(String.valueOf(i));
        }
        // when press the particular button 
        for (int i = 0; i <= 9; i++) {
            numButtons[i].setText(String.valueOf(i));  // Set button text directly
            numButtons[i].addActionListener(ae -> {
                String currentText = tf.getText();
                String buttonText = ((JButton) ae.getSource()).getText();  // Get the text of the clicked button
                tf.setText(currentText.concat(buttonText));  // Use concat() instead of + operator
            });
        }
        
        delButton = new JButton("del");
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        eqButton = new JButton("=");

        numButtons[1].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[2].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[3].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[4].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[5].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[6].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[7].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[8].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[9].setFont(new Font("Arial", Font.BOLD, 40));
        numButtons[0].setFont(new Font("Arial", Font.BOLD, 40));

        delButton.setFont(new Font("Arial", Font.BOLD, 40));
        addButton.setFont(new Font("Arial", Font.BOLD, 40));
        subButton.setFont(new Font("Arial", Font.BOLD, 40));
        mulButton.setFont(new Font("Arial", Font.BOLD, 40));
        divButton.setFont(new Font("Arial", Font.BOLD, 40));
        eqButton.setFont(new Font("Arial", Font.BOLD, 40));

        panel.add(numButtons[1]);
        panel.add(numButtons[2]);
        panel.add(numButtons[3]);
        panel.add(delButton);
        panel.add(numButtons[4]);
        panel.add(numButtons[5]);
        panel.add(numButtons[6]);
        panel.add(addButton);
        panel.add(numButtons[7]);
        panel.add(numButtons[8]);
        panel.add(numButtons[9]);
        panel.add(subButton);
        panel.add(numButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);
        panel.add(mulButton);

        eqButton.addActionListener(ae -> {
            String expression = tf.getText();
            tf.setText(String.valueOf(evaluateExpression(expression)));
        });

        // when button is pressed :--------------

        addButton.addActionListener(ae -> {
            String currentText = tf.getText();
            String buttonText = ((JButton) ae.getSource()).getText();
            tf.setText(currentText.concat(buttonText));
        });
        
        subButton.addActionListener(ae -> {
            String currentText = tf.getText();
            String buttonText = ((JButton) ae.getSource()).getText();
            tf.setText(currentText.concat(buttonText));
        });
        
        mulButton.addActionListener(ae -> {
            String currentText = tf.getText();
            String buttonText = ((JButton) ae.getSource()).getText();
            tf.setText(currentText.concat(buttonText));
        });
        
        divButton.addActionListener(ae -> {
            String currentText = tf.getText();
            String buttonText = ((JButton) ae.getSource()).getText();
            tf.setText(currentText.concat(buttonText));
        });
        
        delButton.addActionListener(ae->{
            String s = tf.getText();
            tf.setText(s.substring(0,s.length()-1));

        });
        add(panel, BorderLayout.CENTER);

        setVisible(true);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    
    private int evaluateExpression(String expression) {
        ArrayList<Integer> values = new ArrayList<>();
        ArrayList<Character> operators = new ArrayList<>();

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                values.add(num);
            } else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') {
                while (!operators.isEmpty() && hasPrecedence(ch, operators.get(operators.size() - 1))) {
                    values.add(applyOperation(operators.remove(operators.size() - 1), values.remove(values.size() - 1), values.remove(values.size() - 1)));
                }
                operators.add(ch);
                i++;
            } else {
                i++;
            }
        }

        while (!operators.isEmpty()) {
            values.add(applyOperation(operators.remove(operators.size() - 1), values.remove(values.size() - 1), values.remove(values.size() - 1)));
        }

        return values.get(values.size() - 1);
    }

    private boolean hasPrecedence(char operator1, char operator2) {
        if (operator2 == '(' || operator2 == ')') {
            return false;
        }
        if ((operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-')) {
            return false;
        }
        return true;
    }

    private int applyOperation(char operator, int b, int a) {
        switch (operator) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
            default:
                System.out.println("Invalid operator: " + operator); // Prints the invalid operator
                return 0;
        }
    }
}
