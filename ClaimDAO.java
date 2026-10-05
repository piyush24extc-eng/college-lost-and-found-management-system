package com.college.lostfound.dao;

import com.college.lostfound.database.DBConnection;
import com.college.lostfound.model.Claim;
import java.sql.*;
import java.util.*;

public class ClaimDAO {
    private Claim map(ResultSet r)throws SQLException{Claim c=new Claim();c.setClaimId(r.getInt("claim_id"));c.setItemId(r.getInt("item_id"));c.setUserId(r.getInt("user_id"));c.setItemName(r.getString("item_name"));c.setClaimantName(r.getString("claimant_name"));c.setClaimDescription(r.getString("claim_description"));c.setStatus(r.getString("status"));Timestamp t=r.getTimestamp("claim_date");if(t!=null)c.setClaimDate(t.toLocalDateTime());return c;}
    public boolean addClaim(int itemId,int userId,String description)throws SQLException{String sql="INSERT INTO claims(item_id,user_id,claim_description) VALUES(?,?,?)";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setInt(1,itemId);p.setInt(2,userId);p.setString(3,description);boolean ok=p.executeUpdate()>0;if(ok)try(PreparedStatement u=c.prepareStatement("UPDATE items SET status='CLAIM_REQUESTED' WHERE item_id=?")){u.setInt(1,itemId);u.executeUpdate();}return ok;}}
    public List<Claim> getAll()throws SQLException{List<Claim> list=new ArrayList<>();String sql="SELECT cl.*,i.item_name,u.name claimant_name FROM claims cl JOIN items i ON cl.item_id=i.item_id JOIN users u ON cl.user_id=u.user_id ORDER BY cl.claim_date DESC";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql);ResultSet r=p.executeQuery()){while(r.next())list.add(map(r));}return list;}
    public boolean updateClaim(int claimId,String status)throws SQLException{String sql="UPDATE claims SET status=? WHERE claim_id=?";try(Connection c=DBConnection.getConnection();PreparedStatement p=c.prepareStatement(sql)){p.setString(1,status);p.setInt(2,claimId);if(p.executeUpdate()==0)return false;if("APPROVED".equals(status)){try(PreparedStatement u=c.prepareStatement("UPDATE items SET status='RETURNED' WHERE item_id=(SELECT item_id FROM claims WHERE claim_id=?)")){u.setInt(1,claimId);u.executeUpdate();}}return true;}}
}
