import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGame implements ActionListener {

    static int noOfGuess;
    JFrame frame;
    JPanel panel;
    JLabel label,text,output,outputNoOfGuesses;
    JTextField input;

    JButton button,exit;

    int random;
    public NumberGame() {
        this.random = new Random().nextInt(1,100);
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);

        label = new JLabel("Guess Number in Range of 1 to 100");
        label.setBounds(0,0,600,60);
        label.setBackground(new Color(0Xa8a3c1));
        label.setOpaque(true);
        label.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label);


        panel = new JPanel();
        panel.setBounds(100,90,400,450);

        text = new JLabel("Guess the Number :");
        text.setBounds(30,20,300,50);
        text.setFont(new Font(Font.SERIF,Font.BOLD,30));

        input = new JTextField();
        input.setBounds(40,80,300,50);
        input.setFont(new Font(Font.DIALOG,Font.BOLD,25));


        button = new JButton("Submit");
        button.setBounds(115,140,150,30);
        button.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        button.setFocusable(false);
        button.setBackground(new Color(0xB2A4FF));
        button.setOpaque(true);
        button.addActionListener(this);

        output = new JLabel();
        output.setBounds(30,200,300,80);
        output.setFont(new Font(Font.SERIF,Font.BOLD,20));

        outputNoOfGuesses = new JLabel();
        outputNoOfGuesses.setBounds(30,250,300,80);
        outputNoOfGuesses.setFont(new Font(Font.SERIF,Font.BOLD,20));


        exit = new JButton("Exit");
        exit.setBackground(new Color(0xdd7973));
        exit.setBounds(115,400,150,30);
        exit.setFont(new Font(Font.MONOSPACED,Font.PLAIN,25));
        exit.setFocusable(false);
        exit.setBorder(BorderFactory.createEtchedBorder());
        exit.addActionListener(e -> frame.dispose());


        panel.add(exit);
        panel.add(output);
        panel.add(outputNoOfGuesses);
        panel.add(button);
        panel.add(input);
        panel.add(text);
        panel.setOpaque(true);
        panel.setBackground(new Color(0xe4e1f0));

        panel.setLayout(null);

        frame.add(panel);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        noOfGuess++;
        String s = null;
        int val = 0;
        try {
         val = Integer.parseInt(input.getText());
         if (val >0 && val <=100){
             if(val == random){
                 s = "Your Guess was Right, it was "+val;
                 input.setText("");
                 input.requestFocus();
                 outputNoOfGuesses.setText("You guessed it in "+noOfGuess+" attempts");
             } else if (val > random) {
                 s = val+" is High Guess low";
                 input.setText("");
                 input.requestFocus();
             }else {
                 s = val +" is Low Guess high";
                 input.setText("");
                 input.requestFocus();
             }
             output.setText(s);
         }else{
             JOptionPane.showMessageDialog(null,"Number out of range","Info",JOptionPane.INFORMATION_MESSAGE);
         }
        }catch (Exception e1){
            JOptionPane.showMessageDialog(null,"Not a Number","Error",JOptionPane.ERROR_MESSAGE);
            input.requestFocus();
        }
    }
}
