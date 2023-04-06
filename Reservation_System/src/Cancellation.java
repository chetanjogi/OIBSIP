import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Cancellation implements ActionListener {
    JFrame frame;
    JLabel title,label;
    JTextField pnrNumber;
    JPanel panel;

    JButton submit,exit;

    /// credentials for connection sql to java by using jdbc
    String url = "jdbc:mysql://localhost:3306/";
    String username = "root";
    String password = "chetan@427";
    Connection con;
    public Cancellation() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("use ReservationDB");
        }catch (Exception e){
            System.out.println(e);
        }

        frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        title = new JLabel("Cancel Train Ticket");
        title.setBounds(0,60,1000,80);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0xffffff));
        title.setOpaque(true);
        title.setBackground(new Color(0x55c2da));
        title.setFont(new Font(Font.SERIF,Font.BOLD,50));
        frame.add(title);

        panel = new JPanel();
        panel.setBounds(300,300,350,700);
        panel.setLayout(new GridLayout(6,1,0,35));


        label = new JLabel();
        label.setText("PNR Number");
        label.setForeground(new Color(0xffffff));
        label.setBounds(250,200,500,50);
        label.setOpaque(true);
        label.setBackground(new Color(0X6a6a6a));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(Font.MONOSPACED,Font.BOLD,35));


        pnrNumber = new JTextField();
        pnrNumber.setBounds(250,250,500,50);
        pnrNumber.setFont(new Font(Font.SERIF, Font.BOLD,35));
        pnrNumber.setForeground(new Color(0x000));
        pnrNumber.setBorder(BorderFactory.createLineBorder(new Color(0x2c2f33),2));
        pnrNumber.requestFocus();


        submit = new JButton("Submit");
        submit.setFocusable(false);
        submit.setBackground(new Color(0x5783db));
        submit.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
        submit.setBorder(BorderFactory.createEtchedBorder());
        submit.addActionListener(this);


        exit = new JButton("Exit");
        exit.setBackground(new Color(0xadbb5));
        exit.setFont(new Font(Font.MONOSPACED,Font.BOLD,25));
        exit.setFocusable(false);
        exit.setBorder(BorderFactory.createEtchedBorder());
        exit.addActionListener(e -> frame.dispose());

        panel.add(label);
        panel.add(pnrNumber);
        panel.add(submit);
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       try {
            int pnr = Integer.parseInt(pnrNumber.getText());
            String query = "select * from ticketsInformation where pnr = ?";
            PreparedStatement prst = con.prepareStatement(query);
            prst.setInt(1,pnr);
            ResultSet resultSet = prst.executeQuery();
            if (resultSet.next()){
                try {
                    int val = JOptionPane.showConfirmDialog(null,"Confirm Yes to Cancel","Cancel",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if (val == JOptionPane.YES_OPTION){
                        query = "delete from ticketsInformation where pnr = ?";
                        prst = con.prepareStatement(query);
                        prst.setInt(1,pnr);
                        prst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Ticket Cancellation Done!");
                        pnrNumber.setText("");
                    }
                }catch (Exception e2){
                    System.out.println(e2);
                }
            }else {
                JOptionPane.showMessageDialog(null,"Invalid PNR Number","Failed",JOptionPane.ERROR_MESSAGE);
            }
       }
       catch (SQLException ex) {
            System.out.println(ex);
       }
    }
}
