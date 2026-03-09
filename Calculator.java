import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator{
    //creating the frame
    int windowWidth = 360;
    int windowHeight = 540;

    //assigning the variables of the colors
    Color lightBlue = new Color(204,229,255);
    Color gray = new Color(192,192,192);
    Color black = new Color(28,28,28);
    Color blue = new Color(94,131,241);
    Color skyblue = new Color(94,205,241);

    //the buttons and their places in the frame
    String[] buttonValues = {"AC", "+/-","%", "÷",
                            "7","8","9", "×",
                            "4","5","6","-",
                            "1","2","3","+",
                            "0",".","√","="};
    String[] rightButtons = {"÷", "×", "-","+","="};
    String[] topButtons = {"AC","+/-", "%"};

    //frame, label and panels
    JFrame window = new JFrame("Calculator");
    JLabel label = new JLabel();
    JPanel panel = new JPanel();
    JPanel buttons = new JPanel();

    //the numbers that going to be used
    String first = "0";
    String operator = null;
    String second = null;

    Calculator(){
        //visual settings for frame
        window.setVisible(true);
        window.setSize(windowWidth, windowHeight);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());

        label.setBackground(black);
        label.setForeground(Color.white);
        label.setFont(new Font("Arial",Font.PLAIN,80));
        label.setHorizontalAlignment(JLabel.RIGHT);
        label.setText("0");
        label.setOpaque(true);

        panel.setLayout(new BorderLayout());
        panel.add(label);
        window.add(panel,BorderLayout.NORTH);

        buttons.setLayout(new GridLayout(5,4));
        buttons.setBackground(black);
        window.add(buttons);

        //deciding the colors of the buttons and backgrounds
        for(int i = 0 ; i<buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial",Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false);
            if(Arrays.asList(topButtons).contains(buttonValue)){
                button.setBackground(lightBlue);
                button.setForeground(black);
            }
            else if(Arrays.asList(rightButtons).contains(buttonValue)){
                if(buttonValue == "="){
                    button.setBackground(blue);
                    button.setForeground(Color.white);
                }
                else {
                    button.setBackground(skyblue);
                    button.setForeground(Color.white);
                }
            }
            else{
                button.setBackground(gray);
                button.setForeground(Color.white);
            }
            buttons.add(button);

            //right buttons' operations
            button.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();
                    if(Arrays.asList(rightButtons).contains(buttonValue)){

                        //giving the result
                        if(buttonValue == "="){
                            if(first != null){
                                second = label.getText();
                                double firstnum = Double.parseDouble(first);
                                double secondnum = Double.parseDouble(second);

                                //adding
                                if(operator == "+"){
                                    label.setText(arrangeDecimal(firstnum+secondnum));
                                }

                                //subtracting
                                else if(operator == "-"){
                                    label.setText(arrangeDecimal(firstnum-secondnum));
                                }

                                //multiplying
                                else if(operator == "×"){
                                    label.setText(arrangeDecimal(firstnum*secondnum));
                                }

                                //dividing
                                else if(operator == "÷"){
                                    label.setText(arrangeDecimal(firstnum/secondnum));
                                }
                                clear();
                            }

                        }

                        //null control for operator
                        else if("+-×÷".contains(buttonValue)){
                            if( operator == null){
                                first = label.getText();
                                label.setText("0");
                                second = "0";
                            }
                            operator = buttonValue;
                        }
                    }
                    //top buttons' operations
                    else if(Arrays.asList(topButtons).contains(buttonValue)){

                        //clearing
                        if(buttonValue== "AC"){
                            clear();
                            label.setText("0");
                        }

                        //changing sign
                        else if(buttonValue == "+/-"){
                            double number = Double.parseDouble(label.getText());
                            number *= -1;
                            label.setText(arrangeDecimal(number));
                        }

                        //calulating the percentage
                        else if (buttonValue == "%"){
                            double number = Double.parseDouble(label.getText());
                            number /= 100;
                            label.setText(arrangeDecimal(number));
                        }
                    }
                    else{

                        //decimal point writing
                        if(buttonValue == "."){
                            if(!label.getText().contains(buttonValue)){
                                label.setText(label.getText() + buttonValue);
                            }
                        }

                        //number writing
                        else if("0123456789".contains(buttonValue)){
                            if(label.getText() == "0"){
                                label.setText(buttonValue);
                            }
                            else{
                                label.setText(label.getText() + buttonValue);
                            }
                        }

                        //square root operation
                        else if(buttonValue == "√"){
                            double number = Math.sqrt(Double.parseDouble(label.getText()));
                            label.setText(arrangeDecimal(number));
                        }
                    }
                }
            });
            window.setVisible(true);
        }
    }

    //decimal point arrangement for the integers
    String arrangeDecimal(double number) {
        if(number % 1 == 0){
            return Integer.toString((int) number);
        }
        else{
            return Double.toString(number);
        }
    }

    //clearing the variables
    void clear() {
        first = "0";
        operator = null;
        second = null;
    }


}
