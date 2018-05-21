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
import supcode.supdetail;

/**
 *
 * @author salit
 */
public class supplier_details extends javax.swing.JFrame {

    ResultSet rs = null;
    PreparedStatement pst = null;
    Connection con = null;
    public supplier_details() {
        initComponents();
        
         con = DBconnect.connect();
        
         
      
        tableload();
        sidbox.setEditable(false);
        setid();
        
      
            showdate();
            showtime();
          
        
        
        
    }
    public void tableload(){
        try {
            String sql = "Select sid as 'SID',name as 'Name', contact_name as 'Conatact Name',address1 as 'Address 1', address2 as 'Address 2',tel as 'Telephone', email as 'Email', website as 'Website' from supplier_details ";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            suptable.setModel(DbUtils.resultSetToTableModel(rs));
        } 
        catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void setid(){
        String Acode;
        int raw = suptable.getRowCount();
        if(raw == 0){
            Acode = "S-1";
        }
        else{
            String code = suptable.getValueAt(raw-1, 0).toString();
            int num = Integer.parseInt(code.substring(2))+1;
            Acode = ("S-"+Integer.toString(num));
        }
        sidbox.setText(Acode);
    }
    
    final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        date.setText(datee);
        
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
                      date.setText(year +" - "+(month+1) + " - " +day);
                      
                      //int second = new GregorianCalendar();
                      int second = cal.get(Calendar.SECOND);
                      int minute = cal.get(Calendar.MINUTE);
                      int hour = cal.get(Calendar.HOUR_OF_DAY);
                      int a = cal.get(Calendar.AM_PM);
                   
                    
                   
                      time.setText(hour +" : "+ (minute) +" : "+ second );
                      
                     
                    try {
                        sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(supplier_details.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
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
        jLabel10 = new javax.swing.JLabel();
        sidbox = new javax.swing.JTextField();
        namebox = new javax.swing.JTextField();
        connamebox = new javax.swing.JTextField();
        address1box = new javax.swing.JTextField();
        address2box = new javax.swing.JTextField();
        telebox = new javax.swing.JTextField();
        emailbox = new javax.swing.JTextField();
        webbox = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        suptable = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        searchbox = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel12.setText("Back");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, -1, -1));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

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
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 20, 150, 40));

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
        jLabel1.setText("SID");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 50, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 60, 40));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Contact name");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 140, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Address1");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Address2");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, -1, -1));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Telephone");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 520, -1, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("email");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 590, -1, -1));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Website");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 650, -1, -1));
        getContentPane().add(sidbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 220, 30));
        getContentPane().add(namebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 240, 220, 30));
        getContentPane().add(connamebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 220, 30));
        getContentPane().add(address1box, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 220, 30));
        getContentPane().add(address2box, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 440, 220, 30));
        getContentPane().add(telebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 220, 30));
        getContentPane().add(emailbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, 220, 30));
        getContentPane().add(webbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 650, 220, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 660, 110, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 660, 90, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 100, 40));

        suptable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "SID", "Name", "Contact Name", "Address1", "Address2", "Telephone", "email", "Website"
            }
        ));
        suptable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                suptableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(suptable);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 230, 820, 370));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 660, 80, 40));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Name");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 50, -1));

        searchbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchboxActionPerformed(evt);
            }
        });
        jPanel2.add(searchbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 12, 170, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 300, 60));

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
         String sid = sidbox.getText();
        String name = namebox.getText();
        String conname = connamebox.getText();
        String add1 = address1box.getText();
        String add2 = address2box.getText();
        String tele = telebox.getText();
        String email = emailbox.getText();
        String web = webbox.getText();
        
        if(name.matches("[A-Z a-z]+")){
            if(conname.matches("[A-Z a-z]+")){
                if(((tele.matches("[+][0-9]+"))||((tele.matches("[0-9]+"))))&&((tele.length()==10)||(tele.length()==9)||(tele.length()==12))){
                    if((!email.matches(" +"))&&(email.indexOf('@')<email.indexOf('.'))){
                        supdetail ob = new supdetail();
                        ob.addsuplier(sid, name, conname, add1, add2, tele, email, web);
                        sidbox.setText(null);
                        namebox.setText(null);
                        connamebox.setText(null);
                        address1box.setText(null);
                        address2box.setText(null);
                        telebox.setText(null);
                        emailbox.setText(null);
                        webbox.setText(null);
                        emailbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        telebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        connamebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        namebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        
                        tableload();
                        setid();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please Enter a valid Email Address !");
                        emailbox.setText(null);
                        emailbox.setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please Enter a valid Telephone !");
                    telebox.setText(null);
                    telebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Please Enter a valid contact name !");
                connamebox.setText(null);
                connamebox.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Please Enter a valid company name !");
            namebox.setText(null);
            namebox.setBorder(BorderFactory.createLineBorder(Color.RED));                                           
        }
        

        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
         int val = JOptionPane.showConfirmDialog(null, "Do You Want To Update ?");
        
        if (val==0){
                    
        String sid = sidbox.getText();
        String name = namebox.getText();
        String conname = connamebox.getText();
        String add1 = address1box.getText();
        String add2 = address2box.getText();
        String tele = telebox.getText();
        String email = emailbox.getText();
        String web = webbox.getText();
        
        if(name.matches("[A-Z a-z]+")){
            if(conname.matches("[A-Z a-z]+")){
                if(((tele.matches("[+][0-9]+"))||((tele.matches("[0-9]+"))))&&((tele.length()==10)||(tele.length()==9)||(tele.length()==12))){
                    if((!email.matches(" +"))&&(email.indexOf('@')<email.indexOf('.'))){
                        supdetail ob = new supdetail();
                        ob.updatesuplier(sid, name, conname, add1, add2, tele, email, web);
                        sidbox.setText(null);
                        namebox.setText(null);
                        connamebox.setText(null);
                        address1box.setText(null);
                        address2box.setText(null);
                        telebox.setText(null);
                        emailbox.setText(null);
                        webbox.setText(null);
                        emailbox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        telebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        connamebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        namebox.setBorder(BorderFactory.createLineBorder(Color.WHITE));
                        
                        tableload();
                        setid();
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Please Enter a valid Email Address !");
                        emailbox.setText(null);
                        emailbox.setBorder(BorderFactory.createLineBorder(Color.RED));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Please Enter a valid Telephone !");
                    telebox.setText(null);
                    telebox.setBorder(BorderFactory.createLineBorder(Color.RED));
                }
                
            }
            else{
                JOptionPane.showMessageDialog(null,"Please Enter a valid contact name !");
                connamebox.setText(null);
                connamebox.setBorder(BorderFactory.createLineBorder(Color.RED));
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Please Enter a valid company name !");
            namebox.setText(null);
            namebox.setBorder(BorderFactory.createLineBorder(Color.RED));                                           
        }
        

        }
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
          int val = JOptionPane.showConfirmDialog(null, "Do You Want To Delete ?");
        
        if (val==0){
            String sid = sidbox.getText();
        
          
            supdetail ob = new supdetail();
            ob.removesupplier(sid);
            sidbox.setText(null);
            namebox.setText(null);
            connamebox.setText(null);
            address1box.setText(null);
            address2box.setText(null);
            telebox.setText(null);
            emailbox.setText(null);
            webbox.setText(null);
           
            tableload();
            setid();
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void searchboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchboxActionPerformed
       
         System.out.println("done");
        String name = searchbox.getText();
        supdetail ob = new supdetail();
        rs = ob.searchsupplier(name);
        System.out.println(rs);
        
        DefaultTableModel model1 = (DefaultTableModel) suptable.getModel();
            while(model1.getRowCount()>0){
                for(int i=0;i<model1.getRowCount();i++){
                    model1.removeRow(i);
                }
            }
            
        DefaultTableModel model2 = (DefaultTableModel) suptable.getModel();
        Object[] index = new Object[8];    
        
        try {
            
            while(rs.next()){
                index[0] = rs.getString("sid");
                index[1] = rs.getString("name");
                index[2] = rs.getString("contact_name");
                index[3] = rs.getString("address1");
                index[4] = rs.getString("address2");
                index[5] = rs.getString("tel");
                index[6] = rs.getString("email");
                index[7] = rs.getString("website");
                
                model2.addRow(index);
            }
        } 
        catch (Exception e) {
            System.out.println(e);
        }
        
        
    }//GEN-LAST:event_searchboxActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
          sidbox.setText(null);
        namebox.setText(null);
        connamebox.setText(null);
        address1box.setText(null);
        address2box.setText(null);
        telebox.setText(null);
        emailbox.setText(null);
        webbox.setText(null);
        searchbox.setText(null);
        
        tableload();
        setid();
                
                
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void suptableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_suptableMouseClicked
       
         int row = suptable.getSelectedRow();
         
         TableModel model1 = suptable.getModel();
         
         sidbox.setText(model1.getValueAt(row, 0).toString());
         namebox.setText(model1.getValueAt(row, 1).toString());
         connamebox.setText(model1.getValueAt(row, 2).toString());
         address1box.setText(model1.getValueAt(row, 3).toString());
         address2box.setText(model1.getValueAt(row, 4).toString());
         telebox.setText(model1.getValueAt(row, 5).toString());
         emailbox.setText(model1.getValueAt(row, 6).toString());
         webbox.setText(model1.getValueAt(row, 7).toString());
      
        
    }//GEN-LAST:event_suptableMouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.dispose();
        new supplier_main().setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        this.dispose();
        new Home().setVisible(true);

    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(supplier_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(supplier_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(supplier_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(supplier_details.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new supplier_details().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address1box;
    private javax.swing.JTextField address2box;
    private javax.swing.JTextField connamebox;
    private javax.swing.JLabel date;
    private javax.swing.JTextField emailbox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JTextField namebox;
    private javax.swing.JTextField searchbox;
    private javax.swing.JTextField sidbox;
    private javax.swing.JTable suptable;
    private javax.swing.JTextField telebox;
    private javax.swing.JLabel time;
    private javax.swing.JTextField webbox;
    // End of variables declaration//GEN-END:variables
}
