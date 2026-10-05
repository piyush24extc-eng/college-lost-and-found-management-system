package com.college.lostfound.dao;

import com.college.lostfound.database.DBConnection;
import com.college.lostfound.model.Item;
import java.sql.*;
import java.util.*;

public class ItemDAO {
    private Item map(ResultSet r)throws SQLException{
        Item i=new Item(); i.setItemId(r.getInt("item_id"));i.setUserId(r.getInt("user_id"));i.setCategoryId(r.getInt("category_id"));
        i.setItemName(r.getString("item_name"));i.setDescription(r.getString("description"));i.setLocation(r.getString("location"));i.setItemDate(r.getDate("item_date").toLocalDate());
        i.setType(r.getString("type"));i.setStatus(r.getString("status"));i.setContactInfo(r.getString("contact_info"));i.setCategoryName(r.getString("category_name"));i.setReporterName(r.getString("reporter_name"));return i;
    }
    public List<String> getCategories() throws SQLException { List<String> list=new ArrayList<>(); String sql="SELECT category_name FROM categories ORDER BY category_name"; try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())list.add(r.getString(1));} return list; }
    public int getCategoryId(String name)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("SELECT category_id FROM categories WHERE category_name=?")){p.setString(1,name);try(ResultSet r=p.executeQuery()){if(r.next())return r.getInt(1);}}return -1;}
    public boolean addItem(Item i)throws SQLException{String sql="INSERT INTO items(user_id,category_id,item_name,description,location,item_date,type,contact_info) VALUES(?,?,?,?,?,?,?,?)";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,i.getUserId());p.setInt(2,i.getCategoryId());p.setString(3,i.getItemName());p.setString(4,i.getDescription());p.setString(5,i.getLocation());p.setDate(6,Date.valueOf(i.getItemDate()));p.setString(7,i.getType());p.setString(8,i.getContactInfo());return p.executeUpdate()>0;}}
    public List<Item> search(String type,String keyword)throws SQLException{List<Item> list=new ArrayList<>();String sql="SELECT i.*,c.category_name,u.name reporter_name FROM items i JOIN categories c ON i.category_id=c.category_id JOIN users u ON i.user_id=u.user_id WHERE i.type=? AND i.status IN ('ACTIVE','CLAIM_REQUESTED') AND (i.item_name LIKE ? OR i.location LIKE ? OR i.description LIKE ?) ORDER BY i.created_at DESC";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){String k="%"+(keyword==null?"":keyword.trim())+"%";p.setString(1,type);p.setString(2,k);p.setString(3,k);p.setString(4,k);try(ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}}return list;}
    public List<Item> getByUser(int userId)throws SQLException{List<Item> list=new ArrayList<>();String sql="SELECT i.*,c.category_name,u.name reporter_name FROM items i JOIN categories c ON i.category_id=c.category_id JOIN users u ON i.user_id=u.user_id WHERE i.user_id=? ORDER BY i.created_at DESC";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,userId);try(ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}}return list;}
    public List<Item> getAll()throws SQLException{List<Item> list=new ArrayList<>();String sql="SELECT i.*,c.category_name,u.name reporter_name FROM items i JOIN categories c ON i.category_id=c.category_id JOIN users u ON i.user_id=u.user_id ORDER BY i.created_at DESC";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}return list;}
    public boolean updateStatus(int itemId,String status)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("UPDATE items SET status=? WHERE item_id=?")){p.setString(1,status);p.setInt(2,itemId);return p.executeUpdate()>0;}}
    public boolean delete(int itemId)throws SQLException{try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement("DELETE FROM items WHERE item_id=?")){p.setInt(1,itemId);return p.executeUpdate()>0;}}
}
