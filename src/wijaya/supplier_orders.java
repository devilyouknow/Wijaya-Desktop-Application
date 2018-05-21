/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import supcode.orderdetails;

/**
 *
 * @author salit
 */
public class supplier_orders extends javax.swing.JFrame {
         ResultSet rs = null;
    PreparedStatement pst = null;
    Connection con = null;
    
    private String orderno = null;
    
    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = new Date();
    public String sdate = dateFormat.format(date1).toString();
   
    public supplier_orders() {
        initComponents();
        
        
         con = DBconnect.connect();
        

        tableload();
        setorderno();
        idbox.setEditable(false);
        setcompanies();
        //setitems();
        
        
        
        
            //implementing date and time methods
            
            
           jLabel26.setText(showdate());
           showtime();
          
        
        
        
    }
    public void tableload(){
        try {
            String sql = "Select id as 'OrderID',company as 'Company',total as 'Total Cost',order_date as 'Order Date', expect_date as 'Expect Date' from supplier_order ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            maintable.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void setorderno(){
        
            String Acode;
            int raw = maintable.getRowCount();
            if(raw == 0){
                Acode = "Ord-1";
            }
            else{
                String code = maintable.getValueAt(raw-1, 0).toString();
                int num = Integer.parseInt(code.substring(4))+1;
                Acode = ("Ord-"+Integer.toString(num));
            }
            idbox.setText(Acode);
    }
            public void setcompanies(){
            
            try {
                String sql = "select * from supplier_details";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    companybox.addItem(rs.getString("name"));
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        
        
        }
            public void setitems(String name){
            try {
                String sql = "select * from items where company = '"+name+"' group by name";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    itembox.addItem(rs.getString("name"));
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
            public void setcategory(String company,String name){
            try {
                String sql = "select * from items where company = '"+company+"' and name = '"+name+"'";
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();
                
                while(rs.next()){
                    categorybox.addItem(rs.getString("category"));
                }
            } 
            catch (Exception e) {
                System.out.println(e);
            }
        }
            public void settotal(){
    
            Double nettotal = 0.0;

            DefaultTableModel model1 = (DefaultTableModel) ordertable.getModel();

            for(int i=0;i<model1.getRowCount();i++){
                nettotal += Double.parseDouble(model1.getValueAt(i, 3).toString());
            }

            netbox.setText(Double.toString(nettotal));
        }

    
    
     
     
      
      
    
    final String showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        return datee;
         }
    
    void showtime(){
        
        new Timer(0,(ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String timee = sdf.format(d);
            time.setText(timee);
        }).start();
   
    }
    
    
        /********************** DATE AND TIME *********************************/
    
    
    
    

    
     /********************** SET BACKGROUND COLOR ************************/
    void setpanel(JPanel x){
        x.setBackground(new Color(102,102,102));
    }
     void resetpanell (JPanel x){
        x.setBackground(new Color(102,102,102));
    }
    
    
    
     void setpanelkalu(JPanel x){
        x.setBackground(new Color(51,51,51));
    }
    void resetpanel (JPanel x){
        x.setBackground(new Color(51,51,51));
    } 
    /********************** SET BACKGROUND COLOR ************************/
    
         
     /********************* SIDE PANEL BAR ***************************/   
    void go_to_google(){
             try{
            String url = "https://www.google.lk/?gfe_rd=cr&dcr=0&ei=_pObWtGPKdekhwPJxaegCg";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
      void go_to_youtube(){
             try{
            String url = "https://www.youtube.com";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
      
        void go_to_gmail(){
             try{
            String url = "https://accounts.google.com/ServiceLogin/signinchooser?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
        
          void go_to_facebook(){
             try{
            String url = "https://www.facebook.com";
            java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
                    /********************* SIDE PANEL BAR ***************************/ 
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        idbox = new javax.swing.JTextField();
        companybox = new javax.swing.JComboBox<String>();
        itembox = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();
        categorybox = new javax.swing.JComboBox<String>();
        totbox = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        qtybox = new javax.swing.JTextField();
        odatebox = new com.toedter.calendar.JDateChooser();
        edatebox = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        maintable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ordertable = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        netbox = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cancel.png"))); // NOI18N
        jLabel29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel29MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 0, 40, 50));

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 200, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 40, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 70, 80));

        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 700, 90, 40));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 80, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Order ID");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 100, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Company");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Item");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Category");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Quantity");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 430, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Cost");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 540, -1, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Order date");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Expecting Date");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 650, -1, -1));
        getContentPane().add(idbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 210, 30));

        companybox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                companyboxKeyPressed(evt);
            }
        });
        getContentPane().add(companybox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 210, 30));

        itembox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemboxKeyPressed(evt);
            }
        });
        getContentPane().add(itembox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 210, 30));

        jButton1.setText("Calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, -1));

        getContentPane().add(categorybox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 210, 30));
        getContentPane().add(totbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 540, 210, 30));

        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 640, 80, 30));
        getContentPane().add(qtybox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 210, 30));
        getContentPane().add(odatebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 600, 220, 30));
        getContentPane().add(edatebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 650, 220, 30));

        maintable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Order ID", "Company", "Total Cost", "Order Date", "Expecting Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        maintable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                maintableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(maintable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 150, 920, 210));

        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 700, 90, 40));

        jButton5.setText("Delete");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(399, 700, 80, 40));

        ordertable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Item Name", "Category", "quantity", "totalcost", "order date", "expect date"
            }
        ));
        ordertable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ordertableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(ordertable);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 920, 230));

        jButton3.setText("Quotation");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 700, 120, 40));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total Cost");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 640, 100, -1));
        getContentPane().add(netbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 630, 190, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel13.setText("Back");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setMinimumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setPreferredSize(new java.awt.Dimension(1184, 666));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -20, 1450, 860));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel29MouseClicked

    private void companyboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_companyboxKeyPressed
       companybox.setEditable(false);
        companybox.setEnabled(false);
        
        String name = companybox.getSelectedItem().toString();
        
        setitems(name);
        
    }//GEN-LAST:event_companyboxKeyPressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
         String order = idbox.getText();
        String company = companybox.getSelectedItem().toString();
        String orderdate = dateFormat.format(odatebox.getDate()).toString();
        String expectdate = dateFormat.format(edatebox.getDate()).toString();
        String nettot = netbox.getText();
        
      
            MessageFormat header = new MessageFormat("Order -'"+order+"' - '"+company+"'");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");

            try {
                ordertable.print(JTable.PrintMode.FIT_WIDTH, header , footer);           
            } 
            catch (Exception e) {
                System.out.println(e);
            }
            
            orderdetails ob = new orderdetails();
            ob.insertorder(order, company, nettot, orderdate, expectdate);
            
            DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
            int x = model2.getRowCount();

            for(int i=0;i<x;i++){
                ob.insertitem(order, model2.getValueAt(i, 0).toString(), model2.getValueAt(i, 1).toString(), model2.getValueAt(i, 2).toString(), model2.getValueAt(i, 3).toString(),model2.getValueAt(i, 4).toString(),model2.getValueAt(i, 5).toString());
            }
            
            while(model2.getRowCount()>0){
                for(int i=0;i<model2.getRowCount();i++){
                    model2.removeRow(i);
                }
            }
            
            
            tableload();
            setorderno();
            odatebox.setEnabled(true);
            edatebox.setEnabled(true);
            companybox.setEnabled(true);
            netbox.setText(null);
            odatebox.setDate(null);
            edatebox.setDate(null);
            
            int itemCount = itembox.getItemCount();

            for(int i=0;i<itemCount;i++){
                itembox.removeItemAt(0);
            }
            
            int catCount = categorybox.getItemCount();

            for(int i=0;i<catCount;i++){
                categorybox.removeItemAt(0);
            }

        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      
        String orderdate = dateFormat.format(odatebox.getDate()).toString();
        
        
        String order = idbox.getText();
        String company = companybox.getSelectedItem().toString();
        
        String expectdate = dateFormat.format(edatebox.getDate()).toString();
        String nettot = netbox.getText();
        
      
            MessageFormat header = new MessageFormat("Order -'"+order+"' - '"+company+"'");
            MessageFormat footer = new MessageFormat("Page{0,number,integer}");

            try {
                ordertable.print(JTable.PrintMode.FIT_WIDTH, header , footer);           
            } 
            catch (Exception e) {
                System.out.println(e);
            }
            
            orderdetails ob = new orderdetails();
            ob.updateorder(order, company, nettot, orderdate, expectdate);
            ob.deleteitems(order);
            DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
            int x = model2.getRowCount();

            for(int i=0;i<x;i++){
                ob.insertitem(order, model2.getValueAt(i, 0).toString(), model2.getValueAt(i, 1).toString(), model2.getValueAt(i, 2).toString(), model2.getValueAt(i, 3).toString(),model2.getValueAt(i, 4).toString(),model2.getValueAt(i, 5).toString());
            }
            
            while(model2.getRowCount()>0){
                for(int i=0;i<model2.getRowCount();i++){
                    model2.removeRow(i);
                }
            }
            
            
            tableload();
            setorderno();
            odatebox.setEnabled(true);
            edatebox.setEnabled(true);
            companybox.setEnabled(true);
            netbox.setText(null);
            odatebox.setDate(null);
            edatebox.setDate(null);
            
            int itemCount = itembox.getItemCount();

            for(int i=0;i<itemCount;i++){
                itembox.removeItemAt(0);
            }
            
            int catCount = categorybox.getItemCount();

            for(int i=0;i<catCount;i++){
                categorybox.removeItemAt(0);
            }
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
         String order = idbox.getText();
        
        orderdetails ob = new orderdetails();
        ob.deleteorder(order);
        ob.deleteitems(order);
        
        DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
        
        while(model2.getRowCount()>0){
                for(int i=0;i<model2.getRowCount();i++){
                    model2.removeRow(i);
                }
            }
            
            
            tableload();
            setorderno();
            odatebox.setEnabled(true);
            edatebox.setEnabled(true);
            companybox.setEnabled(true);
            netbox.setText(null);
            odatebox.setDate(null);
            edatebox.setDate(null);
            
            int itemCount = itembox.getItemCount();

            for(int i=0;i<itemCount;i++){
                itembox.removeItemAt(0);
            }
            
            int catCount = categorybox.getItemCount();

            for(int i=0;i<catCount;i++){
                categorybox.removeItemAt(0);
            }

        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
        
         while(model2.getRowCount()>0){
                for(int i=0;i<model2.getRowCount();i++){
                    model2.removeRow(i);
                }
            }
         
            int itemCount = itembox.getItemCount();

            for(int i=0;i<itemCount;i++){
                itembox.removeItemAt(0);
            }
            
            int catCount = categorybox.getItemCount();

            for(int i=0;i<catCount;i++){
                categorybox.removeItemAt(0);
            }
            
            qtybox.setText(null);
            totbox.setText(null);
            odatebox.setDate(null);
            edatebox.setDate(null);
            odatebox.setEnabled(true);
            edatebox.setEnabled(true);
            companybox.setEnabled(true);
         
        
        
        tableload();
        setorderno();
        idbox.setEditable(false);
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String quantity = qtybox.getText();
        String cost = totbox.getText();
        String orderdate = dateFormat.format(odatebox.getDate()).toString();
        String expectdate = dateFormat.format(edatebox.getDate()).toString();
        
        if(itembox.getItemCount()!=0){
            if(categorybox.getItemCount()!=0){
                if(quantity.matches("[0-9]+")){
                    if(cost.matches("[0-9].+")){
                        try {
                            if((dateFormat.parse(orderdate)).compareTo(dateFormat.parse(sdate))>=0){
                                if((dateFormat.parse(expectdate)).compareTo(dateFormat.parse(orderdate))>0){
                                    
                                    String item = itembox.getSelectedItem().toString();
                                    String category = categorybox.getSelectedItem().toString();
                                    
                                    DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
                                    Object[] index = new Object[6];    

                                    try {

                                        
                                            index[0] = item;
                                            index[1] = category;
                                            index[2] = quantity;
                                            index[3] = cost;
                                            index[4] = orderdate;
                                            index[5] = expectdate;
                                          
                                            model2.addRow(index);
                                        
                                    } 
                                    catch (Exception e) {
                                        System.out.println(e);
                                    }
                                        odatebox.setEnabled(false);
                                        edatebox.setEnabled(false);
                                        
                                        int catCount = categorybox.getItemCount();

                                        for(int i=0;i<catCount;i++){
                                            categorybox.removeItemAt(0);
                                        }
                                        qtybox.setText(null);
                                        totbox.setText(null);
                                        
                                        settotal();
                                        
                                        odatebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                        edatebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                        totbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));    
                                        qtybox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                        categorybox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                        itembox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                }
                                else{
                                    JOptionPane.showMessageDialog(null,"Invalid expect date !");
                                    edatebox.setDate(null);
                                    edatebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                                }
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Invalid order date !");
                                odatebox.setDate(null);
                                odatebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                            }
                        }
                        catch (Exception e) 
                        {
                            System.out.println(e);
                        }
                        
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please calsulate the cost !");
                        totbox.setText(null);
                        totbox.setBorder(BorderFactory.createLineBorder(Color.RED));
                    
                    }
                    
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please enter a valid Quantity !");
                    qtybox.setText(null);
                    qtybox.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Please select a Category !");
                categorybox.setSelectedIndex(0);
                categorybox.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        
        }
        else{
            JOptionPane.showMessageDialog(null,"Please select an Item !");
            itembox.setSelectedIndex(0);
            itembox.setBorder(BorderFactory.createLineBorder(Color.RED));
        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String company = companybox.getSelectedItem().toString();
         String item = itembox.getSelectedItem().toString();
         String category = categorybox.getSelectedItem().toString();
         
         orderdetails ob = new orderdetails();
         Double price = ob.getprice(company, item, category);
         
         String qt = qtybox.getText();
         if(qt.matches("[0-9]+")){
            double qty = Double.parseDouble(qt);
            totbox.setText(Double.toString(price*qty));
            qtybox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
         }
         else{
            JOptionPane.showMessageDialog(null,"Please enter a valid quantity !");
            qtybox.setText(null);
            qtybox.setBorder(BorderFactory.createLineBorder(Color.RED));
         }
         
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void itemboxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemboxKeyPressed
        
         int itemCount = categorybox.getItemCount();

        for(int i=0;i<itemCount;i++){
            categorybox.removeItemAt(0);
        }
        
        String company = companybox.getSelectedItem().toString();
        String item = itembox.getSelectedItem().toString();
        
        setcategory(company,item);
        
        
    }//GEN-LAST:event_itemboxKeyPressed

    private void ordertableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ordertableMouseClicked
       
        int x = JOptionPane.showConfirmDialog(null, "Do you want to remove this Item ?");
        
        if(x==0){
            
            DefaultTableModel model2 = (DefaultTableModel) ordertable.getModel();
            int row = ordertable.getSelectedRow();
            model2.removeRow(row);
            
            settotal();
        }
        
    }//GEN-LAST:event_ordertableMouseClicked

    private void maintableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_maintableMouseClicked
       
        int row = maintable.getSelectedRow();
         
         TableModel model1 = maintable.getModel();
         
         String orderdate = model1.getValueAt(row, 3).toString();
         try {
         if((dateFormat.parse(orderdate)).compareTo(dateFormat.parse(sdate))>0){

             String order = model1.getValueAt(row, 0).toString();

             orderdetails ob = new orderdetails();
             rs = ob.getitems(order);

             ordertable.setModel(DbUtils.resultSetToTableModel(rs));

             idbox.setText(order);
             companybox.setSelectedItem(model1.getValueAt(row, 1).toString());
             companybox.setEnabled(false);
             
                odatebox.setDate(dateFormat.parse(model1.getValueAt(row, 3).toString()));
                edatebox.setDate(dateFormat.parse(model1.getValueAt(row, 4).toString()));
          


             settotal();
             
             String name = companybox.getSelectedItem().toString();
        
             setitems(name);

           
            }
         else{
             JOptionPane.showMessageDialog(null, "Sry you can't modify this order as the order date is already expired !");
         }
          } catch (Exception e) {
                 System.out.println(e);
            }
        
    }//GEN-LAST:event_maintableMouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        this.dispose();
        new supplier_main().setVisible(true);
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel16MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        this.dispose();
        new supplier_main().setVisible(true);

    }//GEN-LAST:event_jLabel11MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(supplier_orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier_orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier_orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier_orders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new supplier_orders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> categorybox;
    private javax.swing.JComboBox<String> companybox;
    private com.toedter.calendar.JDateChooser edatebox;
    private javax.swing.JTextField idbox;
    private javax.swing.JComboBox<String> itembox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable maintable;
    private javax.swing.JTextField netbox;
    private com.toedter.calendar.JDateChooser odatebox;
    private javax.swing.JTable ordertable;
    private javax.swing.JTextField qtybox;
    private javax.swing.JLabel time;
    private javax.swing.JTextField totbox;
    // End of variables declaration//GEN-END:variables
}
