import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class RegisterPage {
    JFrame frame;
    JLabel title;
    JLabel home;
    JLabel userName_label;
    JLabel password_label;

    JTextField userName_textField;
    JPasswordField user_passwordField;

    JButton registerButton;
    JButton backToLoginButton;
    String url = "jdbc:mysql://localhost:3306/";
    String username = "root";
    String password = "chetan@427";
    Connection con;

    public RegisterPage() {

            // Connect to your database
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url,username,password);
                Statement stmt = con.createStatement();
                stmt.executeUpdate("create database if not exists ReservationDB");
                stmt.executeUpdate("use ReservationDB");
            } catch (Exception e) {
                e.printStackTrace();
            }

        frame = new JFrame("Train Reservation System");
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);


        title = new JLabel("Train Reservation System");
        title.setBounds(0,60,1000,80);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0xffffff));
        title.setOpaque(true);
        title.setBackground(new Color(0x55c2da));
        title.setFont(new Font(Font.SERIF,Font.BOLD,50));
        frame.add(title);

        userName_label = new JLabel();
        userName_label.setText("User Name");
        userName_label.setForeground(new Color(0xffffff));
        userName_label.setBounds(250,200,500,50);
        userName_label.setOpaque(true);
        userName_label.setBackground(new Color(0X8AAAE5));
        userName_label.setHorizontalAlignment(JLabel.CENTER);
        userName_label.setFont(new Font(Font.MONOSPACED,Font.BOLD,35));
        frame.add(userName_label);


        userName_textField = new JTextField();
        userName_textField.setBounds(250,250,500,50);
        userName_textField.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        userName_textField.setForeground(new Color(0x000));
        userName_textField.requestFocus();
        frame.add(userName_textField);


        password_label = new JLabel();
        password_label.setText("Password");
        password_label.setForeground(new Color(0xffffff));
        password_label.setBounds(250,350,500,50);
        password_label.setOpaque(true);
        password_label.setBackground(new Color(0X8AAAE5));
        password_label.setHorizontalAlignment(JLabel.CENTER);
        password_label.setFont(new Font(Font.MONOSPACED,Font.BOLD,35));
        frame.add(password_label);


        user_passwordField = new JPasswordField();
        user_passwordField.setBounds(250,400,500,50);
        user_passwordField.setFont(new Font(Font.SERIF,Font.PLAIN,30));
        user_passwordField.setForeground(new Color(0x000));
        frame.add(user_passwordField);


        registerButton = new JButton("Register");
        registerButton.setBounds(250,500,500,40);
        registerButton.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        registerButton.setBackground(new Color(0x187bcd));
        registerButton.setOpaque(true);
        registerButton.setFocusable(false);
        registerButton.addActionListener(e -> {
            registerDataIntoDatabase();
        });
        frame.add(registerButton);


        backToLoginButton = new JButton("Back To Login");
        backToLoginButton.setBounds(250,580,500,40);
        backToLoginButton.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        backToLoginButton.setFocusable(false);
        backToLoginButton.setBackground(new Color(0x187bcd));
        backToLoginButton.setOpaque(true);
        backToLoginButton.addActionListener(e -> {
                frame.dispose();
        });
        frame.add(backToLoginButton);

        frame.setVisible(true);
    }

    public void registerDataIntoDatabase(){
        try {
            String user_name = userName_textField.getText();
            String pwd = user_passwordField.getText();
            String query = "create table if not exists customers(username varchar(50) unique not null,password varchar(50))";
            PreparedStatement pstm = con.prepareStatement(query);
            pstm.execute();
            query = "insert into customers(username,password)values(?,?)";
            pstm = con.prepareStatement(query);
            pstm.setString(1,user_name);
            pstm.setString(2,pwd);
            try {
                pstm.executeUpdate();
                user_passwordField.setText("");
                JOptionPane.showMessageDialog(null,"Registration Successful!","INFO",JOptionPane.INFORMATION_MESSAGE);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"UserName is taken","RETRY",JOptionPane.WARNING_MESSAGE);
            }
            userName_textField.setText("");
            userName_textField.requestFocus();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
