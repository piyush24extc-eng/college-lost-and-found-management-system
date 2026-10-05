package com.college.lostfound.ui;

import com.college.lostfound.dao.ItemDAO;import com.college.lostfound.model.Item;import com.college.lostfound.model.User;import javax.swing.*;import javax.swing.table.DefaultTableModel;import java.awt.*;

public class MyReportsFrame extends JFrame {final User user;final DefaultTableModel model=new DefaultTableModel(new String[]{"ID","Item","Type","Category","Location","Date","Status"},0);public MyReportsFrame(User user){this.user=user;setTitle("My Reports");setSize(800,450);setLocationRelativeTo(null);setDefaultCloseOperation(DISPOSE_ON_CLOSE);add(new JScrollPane(new JTable(model)),BorderLayout.CENTER);load();}void load(){try{for(Item i:new ItemDAO().getByUser(user.getUserId()))model.addRow(new Object[]{i.getItemId(),i.getItemName(),i.getType(),i.getCategoryName(),i.getLocation(),i.getItemDate(),i.getStatus()});}catch(Exception e){JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());}}}
