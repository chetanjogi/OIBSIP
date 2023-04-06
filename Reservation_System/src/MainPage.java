import org.w3c.dom.xpath.XPathNamespace;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MainPage {
    JFrame frame;
    JLabel title;
    JPanel panel;

    JButton book,cancel,checkPnr,exit;
    String url = "jdbc:mysql://localhost:3306/";
    String username = "root";
    String password = "chetan@427";
    Connection con;

    public MainPage() {
        // Connect to your database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("create database if not exists ReservationDB");
            stmt.executeUpdate("use ReservationDB");
            stmt.executeUpdate("create table if not exists ticketsdata(pnr int unique not null,firstName varchar(50) not null,lastName varchar(50) not null,age int not null,emailid varchar(50) not null,trainNum int not null,trainName varchar(50) not null,source varchar(50) not null,destination varchar(50) not null,journeyDate DATE not null,classType varchar(50) not null)");
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        title = new JLabel("Welcome To Indian Railway");
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


        book = new JButton("Book Ticket");
        book.setFocusable(false);
        book.setBackground(new Color(0x5783db));
        book.setFont(new Font(Font.MONOSPACED,Font.PLAIN,25));
        book.setBorder(BorderFactory.createEtchedBorder());
        book.addActionListener(e -> {
            Booking booking = new Booking();
        });


        cancel = new JButton("Cancel Ticket");
        cancel.setBackground(new Color(0xadbb5));
        cancel.setFont(new Font(Font.MONOSPACED,Font.PLAIN,25));
        cancel.setFocusable(false);
        cancel.setBorder(BorderFactory.createEtchedBorder());
        cancel.addActionListener(e -> {
            Cancellation cancellation = new Cancellation();
        });

        checkPnr = new JButton("Check PNR Status");
        checkPnr.setBackground(new Color(0xa881af));
        checkPnr.setFont(new Font(Font.MONOSPACED,Font.PLAIN,25));
        checkPnr.setFocusable(false);
        checkPnr.setBorder(BorderFactory.createEtchedBorder());
        checkPnr.addActionListener(e -> {
            CheckPnr checkPnr1 = new CheckPnr();
        });

        exit = new JButton("Exit");
        exit.setBackground(new Color(0xdd7973));
        exit.setFont(new Font(Font.MONOSPACED,Font.PLAIN,25));
        exit.setFocusable(false);
        exit.setBorder(BorderFactory.createEtchedBorder());
        exit.addActionListener(e -> frame.dispose());

        panel.add(book);
        panel.add(cancel);
        panel.add(checkPnr);
        panel.add(exit);

        frame.add(panel);
        frame.setVisible(true);
    }
}
