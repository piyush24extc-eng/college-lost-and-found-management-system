package com.college.lostfound.ui;

import com.college.lostfound.dao.UserDAO;
import com.college.lostfound.model.User;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField email=new JTextField(); private final JPasswordField password=new JPasswordField();
    public LoginFrame(){setTitle("College Lost & Found - Login");setSize(430,330);setLocationRelativeTo(null);setDefaultCloseOperation(EXIT_ON_CLOSE);setResizable(false);
        JPanel p=new JPanel(new GridBagLayout());GridBagConstraints g=new GridBagConstraints();g.insets=new Insets(8,10,8,10);g.fill=GridBagConstraints.HORIZONTAL;
        JLabel title=new JLabel("COLLEGE LOST & FOUND",SwingConstants.CENTER);title.setFont(new Font("SansSerif",Font.BOLD,22));g.gridx=0;g.gridy=0;g.gridwidth=2;p.add(title,g);
        g.gridwidth=1;g.gridy=1;p.add(new JLabel("Email:"),g);g.gridx=1;p.add(email,g);g.gridx=0;g.gridy=2;p.add(new JLabel("Password:"),g);g.gridx=1;p.add(password,g);
        JButton login=new JButton("Student Login"), register=new JButton("Register"), admin=new JButton("Admin Login");g.gridx=0;g.gridy=3;p.add(login,g);g.gridx=1;p.add(register,g);g.gridx=0;g.gridy=4;g.gridwidth=2;p.add(admin,g);add(p);
        login.addActionListener(e->login());register.addActionListener(e->{dispose();new RegisterFrame().setVisible(true);});admin.addActionListener(e->adminLogin());
    }
    private void login(){try{User u=new UserDAO().login(email.getText().trim(),new String(password.getPassword()));if(u==null)JOptionPane.showMessageDialog(this,"Invalid email or password.");else{dispose();new StudentDashboard(u).setVisible(true);}}catch(Exception ex){error(ex);}}
    private void adminLogin(){JTextField u=new JTextField();JPasswordField pw=new JPasswordField();Object[] fields={"Username:",u,"Password:",pw};if(JOptionPane.showConfirmDialog(this,fields,"Admin Login",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION)try{if(new UserDAO().adminLogin(u.getText().trim(),new String(pw.getPassword()))){dispose();new AdminDashboard().setVisible(true);}else JOptionPane.showMessageDialog(this,"Invalid admin credentials.");}catch(Exception ex){error(ex);}}
    private void error(Exception ex){JOptionPane.showMessageDialog(this,"Database error: "+ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
}
