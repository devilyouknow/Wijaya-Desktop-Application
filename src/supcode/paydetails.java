/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import wijaya.DBconnect;

/**
 *
 * @author Aathika Salamddff
 */
public class paydetails {
    
     Connection con = DBconnect.connect();
    PreparedStatement ps=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    ResultSet rst= null;
    
    public ResultSet searchpayment(String name){
    
        try {
            String sql = "select * from supplier_payment1 where company like '"+name+"%'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        //    System.out.println("searched");
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to Search !");
        }
        
        return rs;
    } 
    
    public ResultSet getorder(String oid){
        try {
            String sql = "select * from supplier_order where id = '"+oid+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
        } catch (Exception e) {
        }
        return rs;
    }
    
    public void addpayment(String pid,String order,String company,String tot,String paid,String due,String date){
    
        try {
            
            
            String sql = "Insert into supplier_payment1 values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, pid);
            ps.setString(2, order);
            ps.setString(3, company);
            ps.setString(4, tot);
            ps.setString(5, paid);
            ps.setString(6, due);
            ps.setString(7, date);
           
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Payment done !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to do payment !");
        }
    }
    
    public void updatepayment(String pid,String order,String company,String tot,String paid,String due,String date){
    
        try {
            
            
            String sql = "update supplier_payment1 set orderid = ?, company=?,totamount = ?,paid = ?,due = ?, date= ? where pid = '"+pid+"'";
            ps = con.prepareStatement(sql);
            
           
            ps.setString(1, order);
            ps.setString(2, company);
            ps.setString(3, tot);
            ps.setString(4, paid);
            ps.setString(5, due);
            ps.setString(6, date);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Payment Updated !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to update Payment !");
        }
    }
    
     public void removepayment(String pid){
        
            try {
            
            
            String sql = "Delete from supplier_payment1 where pid = '"+pid+"' ";
            ps = con.prepareStatement(sql);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Payment Removed Successfully !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to Remove Payment !");
        }
    
    }
    
    
}
