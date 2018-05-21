/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supcode;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import wijaya.DBconnect;

/**
 *
 * @author Aathika Salamddff
 */
public class supdetail {
    
    Connection con = DBconnect.connect();
    PreparedStatement ps=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    ResultSet rst= null;
    
    public void addsuplier(String sid,String name,String conname,String address1,String address2,String tele,String email,String web){
    
        try {
            
            
            String sql = "Insert into supplier_details values(?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setString(1, sid);
            ps.setString(2, name);
            ps.setString(3, conname);
            ps.setString(4, address1);
            ps.setString(5, address2);
            ps.setString(6, tele);
            ps.setString(7,email);
            ps.setString(8,web);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Supplier Added !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Supplier is already Added !");
        }
    }

    public void updatesuplier(String sid,String name,String conname,String address1,String address2,String tele,String email,String web){
    
        try {
            
            
            String sql = "update supplier_details set name = ?, contact_name=?,address1 = ?,address2=?,tel = ?,email = ?, website = ? where sid = '"+sid+"'";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, name);
            ps.setString(2, conname);
            ps.setString(3, address1);
            ps.setString(4, address2);
            ps.setString(5, tele);
            ps.setString(6,email);
            ps.setString(7,web);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Supplier Updated !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to update supplier !");
        }
    }
    
    public void removesupplier(String sid){
        
            try {
            
            
            String sql = "Delete from supplier_details where sid = '"+sid+"' ";
            ps = con.prepareStatement(sql);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Supplier Removed Successfully !");
            
            
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to Remove Supplier !");
        }
    
    }
    
    public ResultSet searchsupplier(String name){
    
        try {
            String sql = "select * from supplier_details where name like '"+name+"%'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            System.out.println("searched");
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Failed to Search !");
        }
        
        return rs;
    } 
    
    
    
}
