package com.college.lostfound.ui;

import com.college.lostfound.model.User;import javax.swing.*;import java.awt.*;

public class StudentDashboard extends JFrame {
    private final User user;
    public StudentDashboard(User user){this.user=user;setTitle("Student Dashboard - College Lost & Found");setSize(650,430);setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);JPanel p=new JPanel(new GridLayout(4,2,15,15));p.setBorder(BorderFactory.createEmptyBorder(30,40,30,40));JLabel welcome=new JLabel("Welcome, "+user.getName(),SwingConstants.CENTER);welcome.setFont(new Font("SansSerif",Font.BOLD,22));JButton lost=new JButton("Report Lost Item"),found=new JButton("Report Found Item"),searchLost=new JButton("Search Lost Items"),searchFound=new JButton("Search Found Items"),mine=new JButton("My Reports"),logout=new JButton("Logout");p.add(welcome);p.add(new JLabel("Roll No: "+user.getRollNo()));p.add(lost);p.add(found);p.add(searchLost);p.add(searchFound);p.add(mine);p.add(logout);add(p);lost.addActionListener(e->new ReportItemFrame(user,"LOST").setVisible(true));found.addActionListener(e->new ReportItemFrame(user,"FOUND").setVisible(true));searchLost.addActionListener(e->new SearchItemsFrame(user,"LOST").setVisible(true));searchFound.addActionListener(e->new SearchItemsFrame(user,"FOUND").setVisible(true));mine.addActionListener(e->new MyReportsFrame(user).setVisible(true));logout.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});}
}
