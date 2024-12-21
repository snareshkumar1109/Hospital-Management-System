package com.hosptial.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hosptial.connection.DBConnection;
import com.hosptial.model.*;
public class MessageDao {
     public List<Message> ViewAllNotread(String name,int id){
    	 String query = "SELECT * FROM Messages WHERE toid = ? and toname=? AND status='not read'";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement statement = con.prepareStatement(query)) {
             statement.setInt(1, id);
             statement.setString(2, name);
             ResultSet rs = statement.executeQuery();

             List<Message> Messages = new ArrayList<>();
             while (rs.next()) {
                 Message message=new Message();
                 message.setId(rs.getInt("id"));
                 message.setFromid(rs.getInt("Fromid"));
                 message.setFromname(rs.getString("Fromname"));
                 message.setToid(id);
                 message.setToname(name);
                 message.setSubject(rs.getString("Subject"));
                 message.setBody(rs.getString("Body"));
                 message.setStatus(rs.getString("status"));
                 Messages.add(message);
             }
             return Messages;
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return null;
     }
     public List<Message> ViewAllReMessages(String name,int id){
    	 String query = "SELECT * FROM Messages WHERE toid = ? and toname=? and status='read'";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement statement = con.prepareStatement(query)) {
             statement.setInt(1, id);
             statement.setString(2, name);
             ResultSet rs = statement.executeQuery();

             List<Message> Messages = new ArrayList<>();
             while (rs.next()) {
                 Message message=new Message();
                 message.setId(rs.getInt("id"));
                 message.setFromid(rs.getInt("Fromid"));
                 message.setFromname(rs.getString("Fromname"));
                 message.setToid(id);
                 message.setToname(name);
                 message.setSubject(rs.getString("Subject"));
                 message.setBody(rs.getString("Body"));
                 message.setStatus(rs.getString("status"));
                 Messages.add(message);
             }
             return Messages;
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return null;
     }
     public List<Message> ViewAllsendMessages(String name,int id){
    	 String query = "SELECT * FROM Messages WHERE Formid = ? and Fromname=?";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement statement = con.prepareStatement(query)) {
             statement.setInt(1, id);
             statement.setString(2, name);
             ResultSet rs = statement.executeQuery();

             List<Message> Messages = new ArrayList<>();
             while (rs.next()) {
                 Message message=new Message();
                 message.setId(rs.getInt("id"));
                 message.setFromid(rs.getInt("Fromid"));
                 message.setFromname(rs.getString("Fromname"));
                 message.setToid(rs.getInt("Toid"));
                 message.setToname(rs.getString("Fromname"));
                 message.setSubject(rs.getString("Subject"));
                 message.setBody(rs.getString("Body"));
                 message.setStatus(rs.getString("status"));
                 Messages.add(message);
             }
             return Messages;
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return null;
     }
     public boolean sending(String Fromname, int Fromid, String Toname, int Toid, String subject, String body) {
         String query = "INSERT INTO Messages (Fromname, Fromid, Toname, Toid, subject, body, status) VALUES(?, ?, ?, ?, ?, ?, ?)";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement statement = con.prepareStatement(query)) {

             statement.setString(1, Fromname);
             statement.setInt(2, Fromid);
             statement.setString(3, Toname);
             statement.setInt(4, Toid);
             statement.setString(5, subject);
             statement.setString(6, body);
             statement.setString(7, "not read");
             return statement.executeUpdate() > 0;
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return false;
     }
     public boolean updateStatus(String status, int id) {
         String query = "UPDATE Messages SET status = ? WHERE id = ?";
         try (Connection con = DBConnection.getConnection();
              PreparedStatement ps = con.prepareStatement(query)) {
             ps.setString(1, status);
             ps.setInt(2, id);

             return ps.executeUpdate() > 0;
         } catch (ClassNotFoundException | SQLException e) {
             e.printStackTrace();
         }
         return false;
     }
    

}
