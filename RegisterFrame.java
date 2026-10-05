package com.college.lostfound.ui;

import com.college.lostfound.dao.UserDAO;
import javax.swing.*;import java.awt.*;

public class RegisterFrame extends JFrame {
    JTextField name=new JTextField(),roll=new JTextField(),email=new JTextField(),phone=new JTextField();JPasswordField pass=new JPasswordField();
    public RegisterFrame(){setTitle("Student Registration");setSize(450,420);setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);JPanel p=new JPanel(new GridLayout(7,2,10,10));p.setBorder(BorderFactory.createEmptyBorder(20,25,20,25));p.add(new JLabel("Name"));p.add(name);p.add(new JLabel("Roll No"));p.add(roll);p.add(new JLabel("Email"));p.add(email);p.add(new JLabel("Phone"));p.add(phone);p.add(new JLabel("Password"));p.add(pass);JButton reg=new JButton("Register"),back=new JButton("Back");p.add(reg);p.add(back);add(p);reg.addActionListener(e->register());back.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});}
    void register(){if(name.getText().isBlank()||roll.getText().isBlank()||email.getText().isBlank()||new String(pass.getPassword()).isBlank()){JOptionPane.showMessageDialog(this,"Please fill all required fields.");return;}try{new UserDAO().register(name.getText().trim(),roll.getText().trim(),email.getText().trim(),phone.getText().trim(),new String(pass.getPassword()));JOptionPane.showMessageDialog(this,"Registration successful. Please login.");dispose();new LoginFrame().setVisible(true);}catch(Exception ex){JOptionPane.showMessageDialog(this,"Registration failed: "+ex.getMessage());}}
}
