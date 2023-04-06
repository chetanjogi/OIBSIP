import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Booking implements ActionListener{

    // frame ,title
    JFrame frame;
    JLabel title;

    // Basic user data
    JLabel fName;
    JTextField tName;
    JLabel lName;
    JTextField tlName;
    JLabel age;
    JTextField tAge;
     JLabel gender;
     JRadioButton male;
     JRadioButton female;
     ButtonGroup gengp;

     JLabel email;
     JTextField emailField;



    // train number, train name , source and destination
     JLabel tnumber;

     JLabel tname;

     JLabel src;
     JLabel dest;

     JComboBox<String> trainNumbers;
     String[] tNums = {"17480","17487","22708","12864","12868"};

     JComboBox<String> trainNames;
     String[] tNames = {"TPTY PURI EXP","TIRUMALA EXP","VSKP DOUBLE DECK","SMVB HOWRAH EXP","PDY HOWRAH EXP"};

     JComboBox<String> sourceStations;
     String[] start ={"Tirupathi","Renigunta","Nellore","Vijayawada","Rajamundry","Tuni","Duvvada","Vishakapatnam"};

     JComboBox<String> destinationStations;

     String[] destination ={"Tirupathi","Renigunta","Nellore","Vijayawada","Rajamundry","Tuni","Duvvada","Vishakapatnam"};

     JLabel bearthClass;

     JComboBox<String> brthClass;

     String[] bClass = {"SL","2AC","1AC","3AC","GN"};

     JLabel dateOfJourney;
     JComboBox<String> date,month,year;
     String[] dates = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
     String[] months = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
     String[] years = {"2023","2024"};

     // terms and conditions , submit and exit.
    JCheckBox term;
    JButton sub;
    JButton exit;

    /// credentials for connection sql to java by using jdbc
    String url = "jdbc:mysql://localhost:3306/";
    String username = "root";
    String password = "chetan@427";
    Connection con;
    public Booking() {
        // Connect to your database
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,username,password);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("create database if not exists ReservationDB");
            stmt.executeUpdate("use ReservationDB");
            stmt.executeUpdate("create table if not exists ticketsInformation(pnr int unique not null,firstName varchar(50) not null,lastName varchar(50) not null,age int not null,gender varchar(50) not null,emailid varchar(50) not null,trainNum int not null,trainName varchar(50) not null,source varchar(50) not null,destination varchar(50) not null,journeyDate DATE not null,classType varchar(50) not null)");
        } catch (Exception e) {
            e.printStackTrace();
        }


        frame = new JFrame();
        frame.setSize(1000,1000);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(null);

        title = new JLabel("Book Train Ticket");
        title.setBounds(0,60,1000,80);
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setForeground(new Color(0xffffff));
        title.setOpaque(true);
        title.setBackground(new Color(0x55c2da));
        title.setFont(new Font(Font.SERIF,Font.BOLD,50));
        frame.add(title);

        fName = new JLabel("FirstName");
        fName.setFont(new Font("Arial",Font.PLAIN,25));
        fName.setSize(150,20);
        fName.setLocation(50,200);
        fName.requestFocus();
        frame.add(fName);

        tName = new JTextField();
        tName.setFont(new Font("Arial", Font.BOLD, 15));
        tName.setSize(190, 25);
        tName.setLocation(190, 200);
        frame.add(tName);

        lName = new JLabel("LastName");
        lName.setFont(new Font("Arial",Font.PLAIN,25));
        lName.setSize(150,20);
        lName.setLocation(430,200);
        frame.add(lName);

        tlName = new JTextField();
        tlName.setFont(new Font("Arial", Font.BOLD, 15));
        tlName.setSize(190, 25);
        tlName.setLocation(560, 200);
        frame.add(tlName);

        age = new JLabel("Age");
        age.setFont(new Font("Arial",Font.PLAIN,20));
        age.setSize(40,25);
        age.setLocation(50,270);
        frame.add(age);

        tAge = new JTextField();
        tAge.setFont(new Font("Arial", Font.BOLD, 15));
        tAge.setSize(60, 25);
        tAge.setLocation(100, 270);
        frame.add(tAge);

        gender = new JLabel("Gender");
        gender.setFont(new Font("Arial", Font.PLAIN, 20));
        gender.setSize(100, 25);
        gender.setLocation(190, 270);
        frame.add(gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSelected(true);
        male.setSize(60, 20);
        male.setLocation(270, 273);
        frame.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(330, 273);
        frame.add(female);

        gengp = new ButtonGroup();
        gengp.add(male);
        gengp.add(female);

        email = new JLabel("Email");
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setSize(80, 25);
        email.setLocation(440, 270);
        frame.add(email);

        emailField = new JTextField();
        emailField.setFont(new Font("Arial", Font.BOLD, 15));
        emailField.setSize(220, 25);
        emailField.setLocation(500, 270);
        frame.add(emailField);

        tnumber = new JLabel("Train Number");
        tnumber.setFont(new Font("Arial",Font.PLAIN,23));
        tnumber.setSize(200,20);
        tnumber.setLocation(50,350);
        frame.add(tnumber);

        trainNumbers = new JComboBox<>(tNums);
        trainNumbers.setFont(new Font(Font.SERIF,Font.BOLD,15));
        trainNumbers.setBounds(210,340,130,40);
        frame.add(trainNumbers);

        trainNumbers.addActionListener(e -> {
            if(e.getSource() == trainNumbers)trainNumbers.getSelectedItem();
        });

        tname = new JLabel("Train Name");
        tname.setFont(new Font("Arial",Font.PLAIN,23));
        tname.setSize(150,20);
        tname.setLocation(390,350);
        frame.add(tname);

        trainNames = new JComboBox<>(tNames);
        trainNames.setFont(new Font(Font.SERIF,Font.BOLD,15));
        trainNames.setBounds(530,340,230,40);
        frame.add(trainNames);

        trainNames.addActionListener(e -> {
            if(e.getSource() == trainNames)trainNames.getSelectedItem();
        });


        src = new JLabel("From Station");
        src.setFont(new Font("Arial",Font.PLAIN,23));
        src.setSize(150,20);
        src.setLocation(50,435);
        frame.add(src);

        sourceStations = new JComboBox<>(start);
        sourceStations.setFont(new Font(Font.SERIF,Font.BOLD,15));
        sourceStations.setBounds(210,425,130,40);
        frame.add(sourceStations);

        sourceStations.addActionListener(e -> {
            if(e.getSource() == sourceStations)sourceStations.getSelectedItem();
        });

        dest = new JLabel("To Station");
        dest.setFont(new Font("Arial",Font.PLAIN,23));
        dest.setSize(150,20);
        dest.setLocation(390,435);
        frame.add(dest);

        destinationStations = new JComboBox<>(destination);
        destinationStations.setFont(new Font(Font.SERIF,Font.BOLD,15));
        destinationStations.setBounds(530,425,130,40);
        frame.add(destinationStations);

        destinationStations.addActionListener(e -> {
            if(e.getSource() == destinationStations)destinationStations.getSelectedItem();
        });

        bearthClass= new JLabel("Class");
        bearthClass.setFont(new Font("Arial",Font.PLAIN,20));
        bearthClass.setSize(50,25);
        bearthClass.setLocation(700,430);
        frame.add(bearthClass);

        brthClass = new JComboBox<>(bClass);
        brthClass.setFont(new Font(Font.SERIF,Font.BOLD,15));
        brthClass.setBounds(765,423,60,40);
        frame.add(brthClass);

        brthClass.addActionListener(e -> {
            if(e.getSource() == brthClass)brthClass.getSelectedItem();
        });

        dateOfJourney = new JLabel("Date of Journey");
        dateOfJourney.setFont(new Font("Arial", Font.PLAIN, 20));
        dateOfJourney.setSize(200, 20);
        dateOfJourney.setLocation(50, 500);
        frame.add(dateOfJourney);

        date = new JComboBox<>(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(215, 500);
        frame.add(date);

        month = new JComboBox<>(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(280, 500);
        frame.add(month);

        year = new JComboBox<>(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(350, 500);
        frame.add(year);

        term = new JCheckBox("Accept Terms And Conditions.");
        term.setFont(new Font("Arial", Font.PLAIN, 15));
        term.setSize(250, 20);
        term.setLocation(50, 550);
        frame.add(term);

        sub = new JButton("Submit");
        sub.setFont(new Font("Arial", Font.PLAIN, 20));
        sub.setSize(100, 30);
        sub.setLocation(100, 600);
        sub.addActionListener(this);
        frame.add(sub);

        exit = new JButton("Exit");
        exit.setFont(new Font("Arial", Font.PLAIN, 20));
        exit.setSize(100, 30);
        exit.setLocation(250, 600);
        exit.addActionListener(e -> frame.dispose());
        frame.add(exit);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //when submitted
        if (e.getSource() == sub){
            if (term.isSelected()){
                String query;
                int pnr = new Random().nextInt(100) + new Random().nextInt(1000);
                String firstname = tName.getText();
                String lastname = tlName.getText();
                int age = Integer.parseInt(tAge.getText());
                String gender;
                if (male.isSelected()) gender = "male";
                else gender = "female";
                String email = emailField.getText();
                if (!validateEmail(email)){
                    JOptionPane.showMessageDialog(null,"Invalid Email Retry","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int trainNumber = Integer.parseInt(Objects.requireNonNull(trainNumbers.getSelectedItem()).toString());
                    String trainName = Objects.requireNonNull(trainNames.getSelectedItem()).toString();
                    String sourceStation = Objects.requireNonNull(sourceStations.getSelectedItem()).toString();
                    String destinationStation = Objects.requireNonNull(destinationStations.getSelectedItem()).toString();
                    String sDate = Objects.requireNonNull(year.getSelectedItem()).toString()+"-"+Objects.requireNonNull(month.getSelectedItem()).toString()+"-"+Objects.requireNonNull(date.getSelectedItem()).toString();
                    String optionClass = Objects.requireNonNull(brthClass.getSelectedItem()).toString();

                    // insert into sql table name with ticketsInformation.
                    query = "insert into ticketsInformation(pnr,firstName,lastName,age,gender,emailid,trainNum,trainName,source,destination,journeyDate,classType)values(?,?,?,?,?,?,?,?,?,?,?,?)";

                    try {
                        PreparedStatement prst = con.prepareStatement(query);
                        prst.setInt(1,pnr);
                        prst.setString(2,firstname);
                        prst.setString(3,lastname);
                        prst.setInt(4,age);
                        prst.setString(5,gender);
                        prst.setString(6,email);
                        prst.setInt(7,trainNumber);
                        prst.setString(8,trainName);
                        prst.setString(9,sourceStation);
                        prst.setString(10,destinationStation);
                        prst.setDate(11, Date.valueOf(sDate));
                        prst.setString(12,optionClass);

                        prst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Ticket Booked \n your PNR number is: "+pnr);
                        tname.setText("");
                        tlName.setText("");
                        tAge.setText("");
                        emailField.setText("");
                        term.setEnabled(false);
                    } catch (SQLException ex) {
                        System.out.println(ex);
                        JOptionPane.showMessageDialog(null,"Error Please Retry","Retry",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null,"Please accept the terms and conditions..","T&C",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // when exit button clicked
        else if (e.getSource() == exit){
            frame.dispose();
        }
    }

    //validating the email address
    public boolean validateEmail(String temp){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(temp);
        return matcher.matches();
    }
}
