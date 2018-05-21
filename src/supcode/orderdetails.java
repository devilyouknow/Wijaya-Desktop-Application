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
public class orderdetails {
    
    
    Connection con = DBconnect.connect();
    PreparedStatement ps=null;
    PreparedStatement pst=null;
    ResultSet rs= null;
    ResultSet rst= null;
    
    public int getrowcount(){
    
        int no=0;
        try {
            String sql = "select * from supplier_order";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while(rs.next()){
                no++;
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return no;
    }
    
    public String getinvcode(){
        
        String code = null;
        
        try {
            String sql = "select * from supplier_order";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.last()){
                code = rs.getString("id");
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        return code;
    }
    
    public double getprice(String company,String item,String category){
        Double price = 0.0;
        try {
            String sql = "select * from items where company = '"+company+"' and name = '"+item+"' and category = '"+category+"' ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                price = rs.getDouble("price");
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return price;
    }
    
    public void insertorder(String id,String company, String total, String odate, String edate){
    
        try {
            String sql = "insert into supplier_order values(?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            ps.setString(2, company);
            ps.setDouble(3, Double.parseDouble(total));
            ps.setString(4, odate);
            ps.setString(5, edate);
            
            ps.execute();
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Failed to Enter Order !");
        }
    
    }
    
     public void insertitem(String id,String item, String category, String qty, String tot,String odate,String edate){
    
        try {
            String sql = "insert into su_order values(?,?,?,?,?,?,?)";
            ps = con.prepareStatement(sql);
            
            ps.setString(1, id);
            ps.setString(2, item);
            ps.setString(3, category);
            ps.setInt(4, Integer.parseInt(qty));
            ps.setDouble(5, Double.parseDouble(tot));
            ps.setString(6, odate);
            ps.setString(7, edate);
            
            ps.execute();
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Failed to Enter Orderitems !");
        }
        
        
        
    
    }
     
    public ResultSet getitems(String order){
        try {
            String sql = "select item as 'Item Name', category as 'Category', qty as 'Quantity', total_cost as 'Total Cost', odate as 'Order Date', edate as 'Expecting Date' from su_order where id = '"+order+"'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        return rs;
    } 
    
    public void updateorder(String id,String company, String total, String odate, String edate){
    
        try {
            String sql = "update supplier_order set company = ?, total = ?, order_date = ?, expect_date =? where id = '"+id+"'";
            ps = con.prepareStatement(sql);
            
            
            ps.setString(1, company);
            ps.setDouble(2, Double.parseDouble(total));
            ps.setString(3, odate);
            ps.setString(4, edate);
            
            ps.execute();
        } 
        catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Failed to Update Order !");
        }
    
    }
    public void deleteitems(String order){
        try {
            String sql = "delete from su_order where id = '"+order+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
    public void deleteorder(String order){
        try {
            String sql = "delete from supplier_order where id = '"+order+"'";
            pst = con.prepareStatement(sql);
            pst.execute();
            
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
    }
    
}
