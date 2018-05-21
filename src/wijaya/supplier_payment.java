/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import java.awt.Color;
import java.awt.event.ActionEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import supcode.paydetails;
import supcode.supdetail;

/**
 *
 * @author salit
 */
public class supplier_payment extends javax.swing.JFrame {

   ResultSet rs = null;
     PreparedStatement pst = null;
     Connection con = null;
     
     public String totpaid = null;
     
    SimpleDateFormat dateFormat =new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    public String sdate = dateFormat.format(date).toString();
    public supplier_payment() {
        initComponents();
        
      con = DBconnect.connect();
        
        
        
        tableload();
        setid();
        idbox.setEditable(false);
        companybox.setEditable(false);
        totamountbox.setEditable(false);
        dueamountbox.setEditable(false);
            showdate();
            showtime();
          
        
        
        
    }
    
    public void tableload(){
        try {
            String sql = "Select pid as 'Payment ID',orderid as 'Order ID', company as 'Company',totamount as 'Total Amount', paid as 'Payed Amount',due as 'Due Amount', date as 'Payment Date' from supplier_payment1 ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            paytable.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
     public void setid(){
        String Acode;
        int raw = paytable.getRowCount();
        if(raw == 0){
            Acode = "Pay-1";
        }
        else{
            String code = paytable.getValueAt(raw-1, 0).toString();
            int num = Integer.parseInt(code.substring(4))+1;
            Acode = ("Pay-"+Integer.toString(num));
        }
        idbox.setText(Acode);
    }
    
    
    
    
    
    final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        jLabel26.setText(datee);
        
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
        public void datetime(){
               
        Thread clock = new Thread (){
            public void run(){
                for (;;){
                  Calendar cal = new GregorianCalendar();
                      int month = cal.get(Calendar.MONTH);
                      int year = cal.get(Calendar.YEAR);
                      int day = cal.get(Calendar.DAY_OF_MONTH);
                      jLabel26.setText(year +" - "+(month+1) + " - " +day);
                      
                      //int second = new GregorianCalendar();
                      int second = cal.get(Calendar.SECOND);
                      int minute = cal.get(Calendar.MINUTE);
                      int hour = cal.get(Calendar.HOUR_OF_DAY);
                      int a = cal.get(Calendar.AM_PM);
                   
                    
                   
                      time.setText(hour +" : "+ (minute) +" : "+ second );
                      
                     
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(supplier_payment.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                }
            }
        };
        clock.start();
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
    
     
      
       
        
          
                    /********************* SIDE PANEL BAR ***************************/ 
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        idbox = new javax.swing.JTextField();
        oidbox = new javax.swing.JTextField();
        companybox = new javax.swing.JTextField();
        totamountbox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        payamountbox = new javax.swing.JTextField();
        dueamountbox = new javax.swing.JTextField();
        datebox = new com.toedter.calendar.JDateChooser();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        paytable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        searchbox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel12.setText("Back");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, -1, -1));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

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
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 170, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 0, 70, 80));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 80, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1410, 110));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Payment ID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Order ID");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Company");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Total Amount");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Paid amount");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 470, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Due amount");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 560, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Payment Date");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, -1, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Add");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 700, 70, 40));
        getContentPane().add(idbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 230, 40));

        oidbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                oidboxActionPerformed(evt);
            }
        });
        getContentPane().add(oidbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 230, 40));
        getContentPane().add(companybox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 230, 40));
        getContentPane().add(totamountbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 230, 40));

        jButton1.setText("calculate");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 510, -1, -1));
        getContentPane().add(payamountbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 460, 230, 30));
        getContentPane().add(dueamountbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 560, 230, 30));
        getContentPane().add(datebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 650, 230, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 700, 90, 40));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 700, -1, 40));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Clear");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(517, 700, 80, 40));

        paytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Payment Id", "Order Id", "Company", "Total amount", "Paid Amount", "Due Amount", "Payment"
            }
        ));
        paytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paytableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(paytable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 840, 320));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Name");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 60, -1));

        searchbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchboxActionPerformed(evt);
            }
        });
        jPanel2.add(searchbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 200, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 180, 320, 60));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setMinimumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setPreferredSize(new java.awt.Dimension(1184, 666));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(-90, -20, 1480, 860));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jLabel29MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel29MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel29MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
         int val = JOptionPane.showConfirmDialog(null, "Do You Want To Delete ?");
        
        if (val==0){
            String pid = idbox.getText();
        
          
            paydetails ob = new paydetails();
            ob.removepayment(pid);
            
            
            oidbox.setText(null);
            companybox.setText(null);
            totamountbox.setText(null);
            payamountbox.setText(null);
            dueamountbox.setText(null);
            datebox.setDate(null);

            tableload();
            setid();
        
    }//GEN-LAST:event_jButton4ActionPerformed
    }
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        oidbox.setText(null);
            companybox.setText(null);
            totamountbox.setText(null);
            payamountbox.setText(null);
            dueamountbox.setText(null);
            datebox.setDate(null);

            tableload();
            setid();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
         String pid = idbox.getText();
        String oid = oidbox.getText();
        String company = companybox.getText();
        String tot = totamountbox.getText();
        String paid = payamountbox.getText();
        String due = dueamountbox.getText();
       String date = dateFormat.format(datebox.getDate()).toString();
       
       if(!oid.isEmpty()){
           if(!company.isEmpty()){
               if(paid.matches("[0-9].+")){
                   if(due.matches("[0-9].+")){
                       
                       try{
                            if((dateFormat.parse(date)).compareTo(dateFormat.parse(sdate))>=0){
                                paydetails ob =  new paydetails();
                                ob.addpayment(pid, oid, company, tot, paid, due, date);
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                DB.setData("INSERT INTO temo_supplier_payment VALUE('"+pid+"', '"+oid+"', '"+company+"', '"+tot+"', '"+paid+"', '"+due+"', '"+date+"')");
                                int i = JOptionPane.showConfirmDialog(null, "Do You Want to Print a Receipt?");
            if(i==0){

                
                String path;
                path = "reports\\supp.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);

                DB.setData("delete from temo_supplier_payment where pid='"+pid+"'");
                
                
                
                 
            }
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                
                                oidbox.setText(null);
                                companybox.setText(null);
                                totamountbox.setText(null);
                                payamountbox.setText(null);
                                dueamountbox.setText(null);
                                datebox.setDate(null);
                                
                                tableload();
                                setid();
                                
                                datebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                dueamountbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                payamountbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                companybox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                oidbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Invalid Payment Date !");
                                datebox.setDate(null);
                                datebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                      
                            }
                       }
                       catch(Exception e){
                           System.out.println(e);
                       }
                   }
                   else{
                        JOptionPane.showMessageDialog(null,"Invalid Due amount !");
                        dueamountbox.setText(null);
                        dueamountbox.setBorder(BorderFactory.createLineBorder(Color.RED));
                   }
               }
               else{
                JOptionPane.showMessageDialog(null,"Invalid Payment amount !");
                payamountbox.setText(null);
                payamountbox.setBorder(BorderFactory.createLineBorder(Color.RED));
               }
           }
           else{
               JOptionPane.showMessageDialog(null,"Please Enter on order id to get details about order !");
                companybox.setText(null);
                companybox.setBorder(BorderFactory.createLineBorder(Color.RED));
           }
       }
       else{
                JOptionPane.showMessageDialog(null,"Please Enter a order ID !");
                oidbox.setText(null);
                oidbox.setBorder(BorderFactory.createLineBorder(Color.RED));
       }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      
        String tot = totamountbox.getText();
        String paid = payamountbox.getText();
        
        if(tot.matches("[0-9].+")){
            if(paid.matches("[0-9].+")){
                dueamountbox.setText(Double.toString(Double.parseDouble(tot)-Double.parseDouble(paid)));
                payamountbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            }
            else{
                JOptionPane.showMessageDialog(null,"Please Enter a valid payment amount !");
                payamountbox.setText(null);
                payamountbox.setBorder(BorderFactory.createLineBorder(Color.RED)); 
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Please search for a order !");
            
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void searchboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchboxActionPerformed
       
         String name = searchbox.getText();
        paydetails ob = new paydetails();
        rs = ob.searchpayment(name);
        //System.out.println(rs);
        
        DefaultTableModel model1 = (DefaultTableModel) paytable.getModel();
            while(model1.getRowCount()>0){
                for(int i=0;i<model1.getRowCount();i++){
                    model1.removeRow(i);
                }
            }
            
        DefaultTableModel model2 = (DefaultTableModel) paytable.getModel();
        Object[] index = new Object[7];    
        
        try {
            
            while(rs.next()){
                index[0] = rs.getString("pid");
                index[1] = rs.getString("orderid");
                index[2] = rs.getString("company");
                index[3] = rs.getString("totamount");
                index[4] = rs.getString("paid");
                index[5] = rs.getString("due");
                index[6] = rs.getString("date");
               
                
                model2.addRow(index);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_searchboxActionPerformed

    private void oidboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_oidboxActionPerformed
       
         String order = oidbox.getText();
        
        paydetails ob = new paydetails();
        rs = ob.getorder(order);
        
        try {
            if(rs.next()){
                companybox.setText(rs.getString("company"));
                totamountbox.setText(rs.getString("total"));
                
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_oidboxActionPerformed

    private void paytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paytableMouseClicked
        
         int row = paytable.getSelectedRow();
         
         TableModel model1 = paytable.getModel();
         
         idbox.setText(model1.getValueAt(row, 0).toString());
         oidbox.setText(model1.getValueAt(row, 1).toString());
         companybox.setText(model1.getValueAt(row, 2).toString());
         totamountbox.setText(model1.getValueAt(row, 3).toString());
         payamountbox.setText(model1.getValueAt(row, 4).toString());
         dueamountbox.setText(model1.getValueAt(row, 5).toString());
         try{
            datebox.setDate(dateFormat.parse(model1.getValueAt(row, 6).toString()));
         }
         catch(Exception e){
             System.out.println(e);
         }
        
    }//GEN-LAST:event_paytableMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
        String pid = idbox.getText();
        String oid = oidbox.getText();
        String company = companybox.getText();
        String tot = totamountbox.getText();
        String paid = payamountbox.getText();
        String due = dueamountbox.getText();
        String date = dateFormat.format(datebox.getDate()).toString();
       
       if(!oid.isEmpty()){
           if(!company.isEmpty()){
               if(paid.matches("[0-9].+")){
                   if(due.matches("[0-9].+")){
                       
                       try{
                            if((dateFormat.parse(date)).compareTo(dateFormat.parse(sdate))>=0){
                                paydetails ob =  new paydetails();
                                ob.updatepayment(pid, oid, company, tot, paid, due, date);
                                
                                oidbox.setText(null);
                                companybox.setText(null);
                                totamountbox.setText(null);
                                payamountbox.setText(null);
                                dueamountbox.setText(null);
                                datebox.setDate(null);
                                
                                tableload();
                                setid();
                                
                                datebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                dueamountbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                payamountbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                companybox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                                oidbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"Invalid Payment Date !");
                                datebox.setDate(null);
                                datebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                      
                            }
                       }
                       catch(Exception e){
                           System.out.println(e);
                       }
                   }
                   else{
                        JOptionPane.showMessageDialog(null,"Invalid Due amount !");
                        dueamountbox.setText(null);
                        dueamountbox.setBorder(BorderFactory.createLineBorder(Color.RED));
                   }
               }
               else{
                JOptionPane.showMessageDialog(null,"Invalid Payment amount !");
                payamountbox.setText(null);
                payamountbox.setBorder(BorderFactory.createLineBorder(Color.RED));
               }
           }
           else{
               JOptionPane.showMessageDialog(null,"Please Enter on order id to get details about order !");
                companybox.setText(null);
                companybox.setBorder(BorderFactory.createLineBorder(Color.RED));
           }
       }
       else{
                JOptionPane.showMessageDialog(null,"Please Enter a order ID !");
                oidbox.setText(null);
                oidbox.setBorder(BorderFactory.createLineBorder(Color.RED));
       }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked

        this.dispose();
        new supplier_main().setVisible(true);

    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.dispose();
        new supplier_main().setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

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
            java.util.logging.Logger.getLogger(supplier_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier_payment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new supplier_payment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField companybox;
    private com.toedter.calendar.JDateChooser datebox;
    private javax.swing.JTextField dueamountbox;
    private javax.swing.JTextField idbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField oidbox;
    private javax.swing.JTextField payamountbox;
    private javax.swing.JTable paytable;
    private javax.swing.JTextField searchbox;
    private javax.swing.JLabel time;
    private javax.swing.JTextField totamountbox;
    // End of variables declaration//GEN-END:variables
}
