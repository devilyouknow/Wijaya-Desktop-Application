/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author BS
 */
public class fi_bill extends javax.swing.JFrame {

    ResultSet rs = null;
    PreparedStatement pst = null;
    Connection con = null;
    
     double first;
    double second;
    String oparation;
    double result;
    public fi_bill() {
        initComponents();
         con = DBconnect.connect();
        loadtableotherbill();
        showdate();
        showtime();
    }
    
    
      final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd [ E ]");
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
    
    
       public void getlastidfiwater(){
        
                try{
      String q = " SELECT billno FROM fiwater ORDER BY billno DESC LIMIT 1";
      pst = con.prepareStatement(q);
      rs = pst.executeQuery();

      while(rs.next()){
      //jLabel30.setText("");
      jLabel30.setText(rs.getString("billno"));
        }
      
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
        
    }
     public void getlastidfi_elec(){
        
                try{
      String q = " SELECT billno FROM fi_elec ORDER BY billno DESC LIMIT 1";
      pst = con.prepareStatement(q);
      rs = pst.executeQuery();

        while(rs.next()){
           //jLabel30.setText("");
            jLabel30.setText(rs.getString("billno"));
        }
      
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
        
    }
      public void getlastidfi_tele(){
        
                try{
      String q = " SELECT billno FROM fi_tele ORDER BY billno DESC LIMIT 1";
      pst = con.prepareStatement(q);
      rs = pst.executeQuery();

        while(rs.next()){
          // jLabel30.setText("");
            jLabel30.setText(rs.getString("billno"));
        }
      
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
        
    }
      
        public void getlastidfi_fiother(){
        
                try{
      String q = " SELECT billno FROM fi_otherbill ORDER BY billno DESC LIMIT 1";
      pst = con.prepareStatement(q);
      rs = pst.executeQuery();

        while(rs.next()){
          // jLabel30.setText("");
            jLabel12.setText(rs.getString("billno"));
        }
      
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
        
    }
      
      
      
      
    
     
     
     public void loadtable(){
        
        try{
        String a = " select billno as Bill_NO , billacc as Bill_Acc , amount as Amount , duedate as Duedate , status as Status from water order by billno ASC" ;
        pst = con.prepareStatement(a);
        rs = pst.executeQuery();
        
        jTable1.setModel (DbUtils.resultSetToTableModel(rs));
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
      public void loadtablefiwater(){
        
        try{
        String a = " select value as Bill_Type,billno as Bill_NO , billacc as Bill_Acc , month as Month, amount as Amount , duedate as Duedate , status as Status from fiwater order by billno ASC" ;
        pst = con.prepareStatement(a);
        rs = pst.executeQuery();
        
        jTable1.setModel (DbUtils.resultSetToTableModel(rs));
        
        getlastidfiwater(); 
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
        public void loadtablefi_elec(){
        
        try{
        String a = " select value as Bill_Type, billno as Bill_NO , billacc as Bill_Acc , month as Month ,amount as Amount , duedate as Duedate ,status as Status from fi_elec order by billno ASC " ;
        pst = con.prepareStatement(a);
        rs = pst.executeQuery();
        
        jTable1.setModel (DbUtils.resultSetToTableModel(rs));
        getlastidfi_elec(); 
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
          public void loadtablefi_tele(){
        
        try{
        String a = " select value as Bill_Type,billno as Bill_NO , billacc as Bill_Acc ,month as Month, amount as Amount , duedate as Duedate ,status as Status from fi_tele order by billno ASC" ;
        pst = con.prepareStatement(a);
        rs = pst.executeQuery();
        
        jTable1.setModel (DbUtils.resultSetToTableModel(rs));
        getlastidfi_tele(); 
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
        
    }
       
           public void loadtableotherbill(){
        
        try{
        String a = " select billno as Bill_No , billtype as Bill_Type , billmonth as Bill_Month , duedate as Due_Date"
                + " , advance as Advance , amount as Amount ,status as Status from fi_otherbill order by billno ASC" ;
        pst = con.prepareStatement(a);
        rs = pst.executeQuery();
        
        jTable2.setModel (DbUtils.resultSetToTableModel(rs));
        getlastidfi_fiother();
        
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
           }
           
           
              public void getlastidfi_otherbill(){
        
                try{
      String q = " SELECT billno FROM fi_otherbill ORDER BY billno DESC LIMIT 1";
      pst = con.prepareStatement(q);
      rs = pst.executeQuery();

        while(rs.next()){
          // jLabel30.setText("");
            jLabel12.setText(rs.getString("billno"));
        }
      
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,e);
      }
        
        
    }
       
       
//       final void showdate(){
//        Date d = new Date();
//        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd [ E ]");
//        String datee = adf.format(d);
//        date2.setText(datee);
//        
//         }
//    
//    void showtime(){
//        
//        new Timer(0,(ActionEvent e) -> {
//            Date d = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
//            String timee = sdf.format(d);
//            time1.setText(timee);
//        }).start();
//   
//    }
    
    
    
      void backgroundset(JLabel x){
        x.setBackground(new Color (0,0,0));
       
    }
    
    void backgroundreset(JLabel y){
        y.setBackground(new Color (102,102,102));
        
    }
    
    
       public void deleteallfromtable(){
           int x = JOptionPane.showConfirmDialog(null, "Do u want to delete all ?", " Dlete all", JOptionPane.INFORMATION_MESSAGE);
        
        if(x == 0){
            
            
            
        try{
            String q = "delete * from water";
            pst = con.prepareStatement(q);
            pst.execute();
            
            loadtable();
            JOptionPane.showMessageDialog(null," Table Destroyed ");
            
        }catch(Exception e){
            System.out.println(e);
        }}
    }
       
         void pic(){
        bulb.setVisible(false);
        phone.setVisible(false);
        //jLabel11.setVisible(false);
        water.setVisible(false);
         }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton9 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel21 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        phone = new javax.swing.JLabel();
        bulb = new javax.swing.JLabel();
        water = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jTextField13 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        status = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        amount = new javax.swing.JTextField();
        advance = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        duedate = new com.toedter.calendar.JDateChooser();
        billmonth = new com.toedter.calendar.JDateChooser();
        jLabel29 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        billtype = new javax.swing.JTextField();
        billno = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel21 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField18 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jButton9.setText("BACK");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(51, 51, 51));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("<html><font color = \"white\" >Bill Month :</font></html>");
        jPanel10.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 100, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("<html><font color = \"white\" >Bill Account No :</font></html>");
        jPanel10.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 140, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Status :");
        jPanel10.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 70, 30));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTextField10MouseClicked(evt);
            }
        });
        jTextField10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField10ActionPerformed(evt);
            }
        });
        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField10KeyTyped(evt);
            }
        });
        jPanel10.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 80, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("<html><font color = \"white\" >Bill Type :</font></html>");
        jPanel10.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 30));
        jPanel10.add(jDateChooser3, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 190, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("<html><font color = \"white\" >Due Date :</font></html>");
        jPanel10.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 90, 30));
        jPanel10.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 190, 30));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel10.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 160, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jTextField15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField15.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField15KeyTyped(evt);
            }
        });
        jPanel10.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 120, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("<html><font color = \"white\" >L. Bill No :</font></html>");
        jPanel10.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 60, 30));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("<html><font color = \"white\" >Bill No :</font></html>");
        jPanel10.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Bill Type", "Electricity Bill", "Water Bill", "Telephone Bill" }));
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel10.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 130, 30));

        jLabel30.setBackground(new java.awt.Color(51, 51, 51));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 80, 30));

        jLabel65.setBackground(new java.awt.Color(51, 51, 51));
        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, 20, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel10.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 20, 30));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("Rs.");
        jPanel10.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 280, 30, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("<html><font color = \"white\" >Amount :</font></html>");
        jPanel10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 80, 30));
        jPanel10.add(phone, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 30, 30));
        jPanel10.add(bulb, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 30, 30));
        jPanel10.add(water, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 30, 30));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Paid", "Unpaid" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        jPanel10.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 320, 120, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Clear");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 260, 130, 30));

        jButton2.setFont(new java.awt.Font("Anklepants", 0, 18)); // NOI18N
        jButton2.setText("Insert");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 170, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 180, 130, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 220, 130, 30));

        jLabel2.setFont(new java.awt.Font("TAPEMAN", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Electricity / Water / Telephone Bills");
        jPanel10.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 310, 30));
        jPanel10.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 300, -1));

        jPanel1.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 610, 350));

        jPanel9.setBackground(new java.awt.Color(51, 51, 51));
        jPanel9.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bill_Type", "Bill_No", "Bill_AccountNo", "Month", "Amount", "Due_date", "Status"
            }
        ));
        jTable1.setOpaque(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel9.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 590, 170));

        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, -1, -1));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTextField13.setForeground(new java.awt.Color(255, 255, 255));
        jTextField13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField13KeyReleased(evt);
            }
        });
        jPanel9.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 170, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("<html><font color = \"white\" >Bill Acc :</font></html>");
        jPanel9.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 30));

        jLabel7.setBackground(new java.awt.Color(51, 51, 51));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 40, 30));

        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesfor/pdf-icon.png"))); // NOI18N
        jLabel57.setToolTipText("See All");
        jLabel57.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel57MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 40, 50));

        jLabel58.setToolTipText("Delete All");
        jLabel58.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel58MouseClicked(evt);
            }
        });
        jPanel9.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 40, 50));

        jPanel1.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 610, 230));

        jPanel15.setBackground(new java.awt.Color(51, 51, 51));
        jPanel15.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel15.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        status.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---", "Paid", "Unpaid" }));
        jPanel15.add(status, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 120, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Status :");
        jPanel15.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 70, 30));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("<html><font color = \"white\" >Amount :</font></html>");
        jPanel15.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 80, 30));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 255, 255));
        jLabel67.setText("Rs.");
        jPanel15.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 30, 30));

        amount.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        amount.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amountKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                amountKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                amountKeyTyped(evt);
            }
        });
        jPanel15.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 130, 30));

        advance.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        advance.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        advance.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                advanceKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                advanceKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                advanceKeyTyped(evt);
            }
        });
        jPanel15.add(advance, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 130, 30));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Rs.");
        jPanel15.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 230, 30, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("<html><font color = \"white\" >Advance :</font></html>");
        jPanel15.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 80, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("<html><font color = \"white\" >Due Date :</font></html>");
        jPanel15.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 90, 30));
        jPanel15.add(duedate, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 150, 30));

        billmonth.setDateFormatString("yyyy.MM.dd");
        billmonth.setMinSelectableDate(new java.util.Date(-62135785725000L));
        jPanel15.add(billmonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 150, 30));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Bill Date :");
        jPanel15.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 90, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Reason :");
        jPanel15.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 80, 30));

        billtype.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        billtype.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel15.add(billtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 110, 220, 30));

        billno.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        billno.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        billno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                billnoMouseClicked(evt);
            }
        });
        billno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                billnoActionPerformed(evt);
            }
        });
        billno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                billnoKeyTyped(evt);
            }
        });
        jPanel15.add(billno, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 140, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("<html><font color = \"white\" >Bill No :</font></html>");
        jPanel15.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 70, 30));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel34.setText("<html><font color = \"white\" >L. Bill No :</font></html>");
        jPanel15.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 60, 30));

        jLabel12.setBackground(new java.awt.Color(51, 51, 51));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setToolTipText("");
        jPanel15.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 60, 30));

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setText("Delete");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 180, 130, 30));

        jButton8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton8.setText("Clear");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, 130, 30));

        jButton5.setFont(new java.awt.Font("Anklepants", 0, 18)); // NOI18N
        jButton5.setText("Insert");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 140, 170, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 220, 130, 30));

        jLabel1.setFont(new java.awt.Font("TAPEMAN", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Other Bills");
        jPanel15.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 0, 100, 30));
        jPanel15.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 30, 90, -1));

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 70, 660, 350));

        jPanel21.setBackground(new java.awt.Color(51, 51, 51));
        jPanel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Bill_No", "Bill_Type", "Bill_Month", "Due_Date", "Advance", "Amount", "Status"
            }
        ));
        jTable2.setOpaque(false);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable2MouseEntered(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel21.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 640, 170));

        jTextField18.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jTextField18.setForeground(new java.awt.Color(255, 255, 255));
        jTextField18.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField18.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField18KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField18KeyTyped(evt);
            }
        });
        jPanel21.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 130, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("<html><font color = \"white\" >Bill No :</font></html>");
        jPanel21.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 30));

        jLabel46.setBackground(new java.awt.Color(51, 51, 51));
        jLabel46.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel46MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, 40, 30));

        jLabel62.setToolTipText("Delete All");
        jLabel62.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel62MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 0, 40, 50));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesfor/Blue-Backup-B-icon(1).png"))); // NOI18N
        jLabel48.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel48MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 40, 30));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel21.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 10, 100, 30));

        jLabel64.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesfor/pdf-icon.png"))); // NOI18N
        jLabel64.setToolTipText("See All");
        jLabel64.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel64MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 0, 40, 50));

        jLabel49.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel49MouseClicked(evt);
            }
        });
        jPanel21.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 10, 40, 30));

        jPanel1.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 430, 660, 230));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 70, 10, 590));

        time.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 0));
        time.setText("time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 120, 20));

        date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 0));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 40, 140, 20));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, 140, 10));

        jLabel26.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 24)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("PAY BILL HERE");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 220, 30));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesfor/go_previous_blue.png"))); // NOI18N
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 40, 30));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagesfor/Apps-Home-icon.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 40, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField13KeyReleased
        String acc = jTextField13.getText();

        try{

            String q = " Select * from water where billacc like '%"+acc+"%'";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTextField13KeyReleased

    private void jTextField10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField10MouseClicked
        String value = jComboBox1.getSelectedItem().toString();

        if(value == "Selct Bill Type"){
            jComboBox1.setBackground(new Color (255, 3, 22));
        }
    }//GEN-LAST:event_jTextField10MouseClicked

    private void jTextField10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField10ActionPerformed
        /*  String value = jComboBox1.getSelectedItem().toString();

        if(value == "Selct Bill Type"){
            jComboBox1.setBackground(new Color (153,0,0));
        }*/
    }//GEN-LAST:event_jTextField10ActionPerformed

    private void jTextField10KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField10KeyTyped

    private void jTextField15KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField15KeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField15KeyTyped

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked

        /*  String value = jComboBox1.getSelectedItem().toString();

        if (value == "Water Bill"){

            jPanel14.removeAll();
            water.setVisible(true);
        }
        else if( value == "Electricity Bill"){
            jPanel14.removeAll();
            bulb.setVisible(true);
        }
        else if (value == "Telephone Bill"){
            jPanel14.removeAll();
            phone.setVisible(true);
        }*/
    }//GEN-LAST:event_jComboBox1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String value = jComboBox1.getSelectedItem().toString();

        if (value == "Water Bill"){

            //jPanel14.removeAll();
            bulb.setVisible(false);
            phone.setVisible(false);
            water.setVisible(true);
        }
        else if( value == "Electricity Bill"){
            //jPanel14.removeAll();
            water.setVisible(false);
            phone.setVisible(false);
            bulb.setVisible(true);
        }
        else if (value == "Telephone Bill"){
            // jPanel14.removeAll();
            bulb.setVisible(false);
            water.setVisible(false);
            phone.setVisible(true);
        }
        else{
            bulb.setVisible(false);
            water.setVisible(false);
            phone.setVisible(false);
        }

        if (value == "Electricity Bill"){

            try {

                jLabel30.setText("");
                loadtablefi_elec();

                jLabel4.setText("E");
                jLabel65.setText("E");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                jComboBox3.setSelectedIndex(0);

            }catch(Exception e){
                System.out.println(e);
            }

        }
        if(value == "Water Bill"){
            try {

                jLabel4.setText("W");
                jLabel65.setText("W");
                jLabel30.setText("");
                loadtablefiwater();

                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                jComboBox3.setSelectedIndex(0);

            }catch(Exception e){
                System.out.println(e);
            }
        }
        if(value == "Telephone Bill"){
            try {
                jLabel30.setText("");
                loadtablefi_tele();

                jLabel4.setText("T");
                jLabel65.setText("T");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                jComboBox3.setSelectedIndex(0);

            }catch(Exception e){
                System.out.println(e);
            }
        }

        if(value == "Select Bill Type"){

            //loadtable();
            jLabel65.setText("");
            jLabel4.setText("");
            jLabel30.setText("");
            jTextField10.setText("");
            jTextField14.setText("");
            jDateChooser3.setDate(null);
            jTextField15.setText("");
            jDateChooser4.setDate(null);

        }
    }//GEN-LAST:event_jComboBox1ActionPerformed
        
    private void billnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_billnoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_billnoMouseClicked

    private void billnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_billnoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_billnoActionPerformed

    private void billnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_billnoKeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_billnoKeyTyped

    private void advanceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advanceKeyPressed

    }//GEN-LAST:event_advanceKeyPressed

    private void advanceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advanceKeyReleased

    }//GEN-LAST:event_advanceKeyReleased

    private void advanceKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_advanceKeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_advanceKeyTyped

    private void amountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyPressed

    private void amountKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_amountKeyReleased

    private void amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amountKeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_amountKeyTyped

    private void jLabel64MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel64MouseClicked
        MessageFormat header = new MessageFormat("Report Print!");
        MessageFormat foter = new MessageFormat("page{0,number,integer}");
          
            try{

                jTable2.print(JTable.PrintMode.NORMAL,header,foter);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Try Again"); }
    }//GEN-LAST:event_jLabel64MouseClicked

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jLabel62MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel62MouseClicked

        int x = JOptionPane.showConfirmDialog(null, "Do u want to delete all ?", " Dlete all", JOptionPane.INFORMATION_MESSAGE);

        if(x == 0){

            try{
                String q = "delete * from fi_otherbill";
                pst = con.prepareStatement(q);
                pst.execute();

                JOptionPane.showMessageDialog(null," Table Destroyed ");

            }catch(Exception e){
                System.out.println(e);
            }}
    }//GEN-LAST:event_jLabel62MouseClicked

    private void jLabel46MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel46MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel46MouseClicked

    private void jTextField18KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyTyped
        char inumber = evt.getKeyChar();
        if(!(Character.isDigit(inumber)) || (inumber == KeyEvent.VK_BACK_SPACE)|| (inumber == KeyEvent.VK_DELETE)){
            evt.consume();
        }
    }//GEN-LAST:event_jTextField18KeyTyped

    private void jTextField18KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField18KeyReleased
        String acc = jTextField18.getText();

        try{

            String q = " Select * from fi_otherbill where billno like '%"+acc+"%'";
            pst = con.prepareStatement(q);
            rs = pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_jTextField18KeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow();
        String click = (jTable2.getModel().getValueAt(row, 0).toString());

        DefaultTableModel dtm = (DefaultTableModel) jTable2.getModel();

        try{

            String sql = " select * from fi_otherbill where billno = '"+click+"' " ;
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            if(rs.next()){

                String a1 = rs.getString("billno");
                billno.setText(a1);

                String a2 = rs.getString("billtype");
                billtype.setText(a2);

                String a3 = rs.getString("advance");
                advance.setText(a3);

                String a4 = rs.getString("amount");
                amount.setText(a4);

                String a6 = rs.getString("status");
                status.setSelectedItem(a6);

                ((JTextField)billmonth.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 2).toString());

                ((JTextField)duedate.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());

            }}catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        String click = (jTable1.getModel().getValueAt(row, 2).toString());
        String value = jComboBox1.getSelectedItem().toString();

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        if( value == "Electricity Bill"){

            try{

                String sql = " select * from fi_elec where billacc = '"+click+"' " ;
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                if(rs.next()){

                    String a2 = rs.getString("billno");
                    jTextField10.setText(a2);

                    String a3 = rs.getString("billacc");
                    jTextField14.setText(a3);

                    String a5 = rs.getString("amount");
                    jTextField15.setText(a5);

                    //String a6 = rs.getString("status");
                    jComboBox3.setSelectedItem(rs.getString("status"));

                    ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());

                    ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 5).toString());

                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }}
        
         if( value == "Water Bill"){

            try{

                String sql = " select * from fiwater where billacc = '"+click+"' " ;
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                if(rs.next()){

                    String a2 = rs.getString("billno");
                    jTextField10.setText(a2);

                    String a3 = rs.getString("billacc");
                    jTextField14.setText(a3);

                    String a5 = rs.getString("amount");
                    jTextField15.setText(a5);

                    //String a6 = rs.getString("status");
                    jComboBox3.setSelectedItem(rs.getString("status"));

                    ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());

                    ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 5).toString());

                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }}
        
        if( value == "Telephone Bill"){

            try{

                String sql = " select * from fi_tele where billacc = '"+click+"' " ;
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                if(rs.next()){

                    String a2 = rs.getString("billno");
                    jTextField10.setText(a2);

                    String a3 = rs.getString("billacc");
                    jTextField14.setText(a3);

                    String a5 = rs.getString("amount");
                    jTextField15.setText(a5);

                    //String a6 = rs.getString("status");
                    jComboBox3.setSelectedItem(rs.getString("status"));

                    ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());

                    ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 5).toString());

                }

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }}
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel58MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel58MouseClicked
        deleteallfromtable();
    }//GEN-LAST:event_jLabel58MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        //        String a = jTextField2.getText();
        //
        //        try{
            //
            //            String sql = " select * from deletebill where billno like '%"+a+"%'";
            //            pst = con.prepareStatement(sql);
            //            rs = pst.executeQuery();
            //
            //            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            //
            //
            //        }catch(Exception e){
            //            System.out.print(e);
            //        }
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        //            String acc = jTextField13.getText();
        //
        //        try{
            //
            //            String q = " Select * from water where billacc like '%"+acc+"%'";
            //            pst = con.prepareStatement(q);
            //            rs = pst.executeQuery();
            //
            //            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            //
            //
            //        }catch(Exception e){
            //            JOptionPane.showMessageDialog(null, e);
            //        }
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel48MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel48MouseClicked

        //        int row = jTable2.getSelectedRow();
        //        String click = (jTable2.getModel().getValueAt(row, 0).toString());

        try{

            String sq1 = " select * from  fiotherdelete where billno= '"+jTextField3.getText()+"'";
            pst = con.prepareStatement(sq1);
            rs = pst.executeQuery();

            if(rs.next()){
                String a1 = rs.getString("billno");

                String a2 = rs.getString("billtype");

                String a3 = rs.getString("billmonth");

                String a4 = rs.getString("duedate");

                String a5 = rs.getString("advance");

                String a6 = rs.getString("amount");

                String a7 = rs.getString("status");

                String w = " insert into fi_otherbill (billno,billtype,billmonth,duedate,advance,amount,status) "
                + " values('"+a1+"' , '"+a2+"' , '"+a3+"' , '"+a4+"' ,"
                + " '"+a5+"' , '"+a6+"' , '"+a7+"' )";
                pst = con.prepareStatement(w);
                pst.execute();

                String q = " delete from fiotherdelete where billno = '"+jTextField3.getText()+"'";
                pst = con.prepareStatement(q);
                pst.execute();

                JOptionPane.showMessageDialog(null, " Bill No :" +a1+ " Recovery is Successfull");
                jTextField3.setText("");
                loadtableotherbill();
            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(null, e);

        }
    }//GEN-LAST:event_jLabel48MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
              String value = jComboBox1.getSelectedItem().toString();

        if ( value == "Electricity Bill"){

            jComboBox1.setSelectedIndex(1);

            //jLabel30.setText("");
            jTextField10.setText("");
            jTextField14.setText("");
            jDateChooser3.setDate(null);
            jTextField15.setText("");
            jDateChooser4.setDate(null);
            status.setSelectedIndex(0); }
            
             if ( value == "Water Bill"){

            jComboBox1.setSelectedIndex(2);

            //jLabel30.setText("");
            jTextField10.setText("");
            jTextField14.setText("");
            jDateChooser3.setDate(null);
            jTextField15.setText("");
            jDateChooser4.setDate(null);
            status.setSelectedIndex(0);
            

    }  
               if ( value == "Telephone Bill"){

            jComboBox1.setSelectedIndex(3);

            //jLabel30.setText("");
            jTextField10.setText("");
            jTextField14.setText("");
            jDateChooser3.setDate(null);
            jTextField15.setText("");
            jDateChooser4.setDate(null);
            status.setSelectedIndex(0);
            

    }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel49MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel49MouseClicked

        //        int row = jTable2.getSelectedRow();
        //        String click = (jTable2.getModel().getValueAt(row, 0).toString());

        try{

            String sq1 = " select * from  fiotherdelete where billno= '"+jTextField3.getText()+"'";
            pst = con.prepareStatement(sq1);
            rs = pst.executeQuery();

            if(rs.next()){
                String a1 = rs.getString("billno");

                String a2 = rs.getString("billtype");

                String a3 = rs.getString("billmonth");

                String a4 = rs.getString("duedate");

                String a5 = rs.getString("advance");

                String a6 = rs.getString("amount");

                String a7 = rs.getString("status");

                String w = " insert into fi_otherbill (billno,billtype,billmonth,duedate,advance,amount,status) "
                + " values('"+a1+"' , '"+a2+"' , '"+a3+"' , '"+a4+"' ,"
                + " '"+a5+"' , '"+a6+"' , '"+a7+"' )";
                pst = con.prepareStatement(w);
                pst.execute();

                String q = " delete from fiotherdelete where billno = '"+jTextField3.getText()+"'";
                pst = con.prepareStatement(q);
                pst.execute();

                JOptionPane.showMessageDialog(null, " Bill No :" +a1+ " Recovery is Successfull");
                jTextField3.setText("");
                loadtableotherbill();
            }

        } catch(Exception e){

            JOptionPane.showMessageDialog(null, e);

        }

    }//GEN-LAST:event_jLabel49MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         billno.setText("");
        billtype.setText("");
        billmonth.setDate(null);
        duedate.setDate(null);
        advance.setText("");

        amount.setText("");
        status.setSelectedIndex(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         String value = jComboBox1.getSelectedItem().toString();
        String billno = jTextField10.getText();
        String billacc = jTextField14.getText();
         String month = ((JTextField)jDateChooser3.getDateEditor().getUiComponent()).getText();
        String amount = jTextField15.getText();
       String duedate = ((JTextField)jDateChooser4.getDateEditor().getUiComponent()).getText();
        String stat = jComboBox3.getSelectedItem().toString();

        int x = JOptionPane.showConfirmDialog(null, " Do u want to save ?", "add to DB", JOptionPane.INFORMATION_MESSAGE);

        if( x == 0 && value == "Electricity Bill"){

            try{
                String s  = " insert into fi_elec(value , billno , billacc , month,amount,duedate,status )  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"', '"+stat+"' ) ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefi_elec();
                JOptionPane.showMessageDialog(this,"Complete !");

                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

                jComboBox1.setSelectedIndex(1);
                jComboBox3.setSelectedIndex(1);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Try Again!");

            }

        }

        if( x == 0 && value == "Water Bill"){

            try{
                String s  = " insert into fiwater(value , billno , billacc , month,amount,duedate , status)  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"' , '"+stat+"') ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefiwater();
                JOptionPane.showMessageDialog(this,"Complete !");

                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

                jComboBox1.setSelectedIndex(2);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Try Again!");

            }

        }

        if( x == 0 && value == "Telephone Bill"){

            try{
                String s  = " insert into fi_tele(value , billno , billacc , month,amount,duedate,status  )  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"' , '"+stat+"' ) ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefi_tele();
                JOptionPane.showMessageDialog(this,"Complete !");

                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

                jComboBox1.setSelectedIndex(3);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Try Again!");

            }

        }
        
        if(x == 0 && value == "Select Bill Type"){
            JOptionPane.showMessageDialog(null, "Please Select Bill Type" , "Warning! " , JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          int row = jTable1.getSelectedRow();
        String click = (jTable1.getModel().getValueAt(row, 2).toString());

        String value = jComboBox1.getSelectedItem().toString();
        String billno = jTextField10.getText();
        String billacc = jTextField14.getText();
        Date month = jDateChooser3.getDate();
        String amount = jTextField15.getText();
        Date duedate = jDateChooser4.getDate();

        int x = JOptionPane.showConfirmDialog(null, "Do u want to delete? ");

        if (x == 0 && value == "Electricity Bill"){

            try{

                String q  = " delete from fi_elec where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                loadtablefi_elec();

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    }     
        
              if (x == 0 && value == "Water Bill"){

            try{

                String q  = " delete from fiwater where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                loadtablefiwater();

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    } 
        
        if (x == 0 && value == "Telephone Bill"){

            try{

                String q  = " delete from fi_tele where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                loadtablefi_tele();

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    } 
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       
          int row = jTable1.getSelectedRow();
        String click = (jTable1.getModel().getValueAt(row, 2).toString());

        String value = jComboBox1.getSelectedItem().toString();
        String billno = jTextField10.getText();
        String billacc = jTextField14.getText();
        Date month = jDateChooser3.getDate();
        String amount = jTextField15.getText();
        Date duedate = jDateChooser4.getDate();
        String stat = jComboBox3.getSelectedItem().toString();

        int x = JOptionPane.showConfirmDialog(null, "Do u want to delete? ");

        if (x == 0 && value == "Electricity Bill"){

            try{

                String q  = " delete from fi_elec where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                
                
                
                String s  = " insert into fi_elec(value , billno , billacc , month,amount,duedate,status )  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"', '"+stat+"' ) ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefi_elec();
                JOptionPane.showMessageDialog(this,"Complete !");

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                status.setSelectedIndex(0);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    }     
        
              if (x == 0 && value == "Water Bill"){

            try{

                String q  = " delete from fiwater where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                
                 String s  = " insert into fiwater(value , billno , billacc , month,amount,duedate , status)  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"' , '"+stat+"') ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefiwater();
                JOptionPane.showMessageDialog(this,"Complete !");

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                status.setSelectedIndex(0);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    } 
        
        if (x == 0 && value == "Telephone Bill"){

            try{

                String q  = " delete from fi_tele where billacc = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();
                
                String s  = " insert into fi_tele(value , billno , billacc , month,amount,duedate,status  )  "
                + "values ('"+value+"' , '"+billno+"', '"+billacc+"' , '"+month+"', '"+amount+"' , '"+duedate+"' , '"+stat+"' ) ";
                pst = con.prepareStatement(s);
                pst.execute();

                loadtablefi_tele();
                JOptionPane.showMessageDialog(this,"Complete !");

                //jComboBox1.setSelectedIndex(1);

                //jLabel30.setText("");
                jTextField10.setText("");
                jTextField14.setText("");
                jDateChooser3.setDate(null);
                jTextField15.setText("");
                jDateChooser4.setDate(null);
                status.setSelectedIndex(0);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }

    } 
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String bno = billno.getText();
        String btype = billtype.getText();
        String bmonth = ((JTextField)billmonth.getDateEditor().getUiComponent()).getText();
       String nduedate = ((JTextField)duedate.getDateEditor().getUiComponent()).getText();
        String adva = advance.getText();
        String amou  = amount.getText();
        String stat = status.getSelectedItem().toString();

        Component f=null;

        //LocalDate bmonth  = datePicker1.getDate();

        if(billtype.getText().isEmpty()){
            JOptionPane.showMessageDialog(f," Enter Bill Type","Alert",JOptionPane.WARNING_MESSAGE);
        }
        else{

            int x = JOptionPane.showConfirmDialog(null,"Do u want to add data ?" , "Add To DB" , JOptionPane.INFORMATION_MESSAGE);

            if ( x == 0){
                try{

                    String w = " insert into fi_otherbill (billno,billtype,billmonth,duedate,advance,amount,status) "
                    + "values('"+bno+"' , '"+btype+"' , '"+bmonth+"' , '"+nduedate+"' ,"
                    + " '"+adva+"' , '"+amou+"' , '"+stat+"' )";
                    pst = con.prepareStatement(w);
                    pst.execute();

                    //                String ww = " insert into fi_otherbill_backup (billno,billtype,billmonth,duedate,advance,amount,method,type,action) "
                    //                + "values('"+bno+"' , '"+btype+"' , '"+bmonth+"' , '"+nduedate+"' , '"+adva+"' , '"+amou+"' )";
                    //                pst = con.prepareStatement(ww);
                    //                pst.execute();

                    JOptionPane.showMessageDialog(null,"Complete !");
                    loadtableotherbill();
                    getlastidfi_otherbill();

                    billno.setText("");
                    billtype.setText("");
                    billmonth.setDate(null);
                    duedate.setDate(null);
                    advance.setText("");

                    amount.setText("");
                    status.setSelectedIndex(0);

                }catch(Exception e){
                    JOptionPane.showMessageDialog(null,"Try Again !");
                }
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          int row = jTable2.getSelectedRow();
        String click = (jTable2.getModel().getValueAt(row, 0).toString());

        String bno = billno.getText();
        String btype = billtype.getText();
        Date bmonth = billmonth.getDate();
        Date nduedate = duedate.getDate();
        String adva = advance.getText();
        String amou  = amount.getText();
        String sta = status.getSelectedItem().toString();

        int x= JOptionPane.showConfirmDialog(null,"Do u really want to delete ?" , " Delete Data" , JOptionPane.INFORMATION_MESSAGE);

        if ( x == 0){
            try{

                String w = " insert into fiotherdelete (billno,billtype,billmonth,duedate,advance,amount,status) "
                + "values('"+bno+"' , '"+btype+"' , '"+bmonth+"' , '"+nduedate+"' , '"+adva+"' , '"+amou+"' , '"+sta+"' )";
                pst = con.prepareStatement(w);
                pst.execute();

                String q = " delete from fi_otherbill where billno = '"+click+"'";
                pst = con.prepareStatement(q);
                pst.execute();

                loadtableotherbill();
                getlastidfi_otherbill();

                JOptionPane.showMessageDialog(null, " Complete! ");

                billno.setText("");
                billtype.setText("");
                billmonth.setDate(null);
                duedate.setDate(null);
                advance.setText("");

                amount.setText("");
                status.setSelectedIndex(0);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e);
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        int row = jTable2.getSelectedRow();
        String click = (jTable2.getModel().getValueAt(row, 0).toString());

        String type = status.getSelectedItem().toString();
        String bno = billno.getText();
        //String acc = jTextField14.getText();
        String ty = billtype.getText();
        Date mo = billmonth.getDate();
        Date du = duedate.getDate();
        String adv = advance.getText();
        String amou = amount.getText();

        try{

            //          //String sql = " insert into test (type,bno,acc) values ('"+type+"' , '"+bno+"' , '"+acc+"' )";
            //
            //          String sql = " update fi_otherbill set billtype= '"+ty+"',billmonth = '"+mo+"' , "
            //                  + "duedate = '"+du+"' ,advance = '"+adv+"' ,amount = '"+amou+"' , "
            //                  + "status = '"+type+"', where billno = '"+bno+"'";
            //          pst = con.prepareStatement(sql);
            //          pst.execute();

            String q = " delete from fi_otherbill where billno = '"+click+"'";
            pst = con.prepareStatement(q);
            pst.execute();

            String w = " insert into fi_otherbill (billno,billtype,billmonth,duedate,advance,amount,status) "
            + "values('"+bno+"' , '"+ty+"' , '"+mo+"' , '"+du+"' ,"
            + " '"+adv+"' , '"+amou+"' , '"+type+"' )";
            pst = con.prepareStatement(w);
            pst.execute();

            loadtableotherbill();
            JOptionPane.showMessageDialog(null," "+bno+" Update Is Complete !");

            billno.setText("");
            billtype.setText("");
            billmonth.setDate(null);
            duedate.setDate(null);
            advance.setText("");

            amount.setText("");
            status.setSelectedIndex(0);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);

        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Fi_Main am = new Fi_Main();
        am.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        Fi_Main am = new Fi_Main();
        am.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        Home ho = new Home();
        ho.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel57MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel57MouseClicked

        MessageFormat header = new MessageFormat("Report Print!");
        MessageFormat foter = new MessageFormat("page{0,number,integer}");
        String value = jComboBox1.getSelectedItem().toString();

        if( value == "Select Bill Type"){
            JOptionPane.showMessageDialog(null,"Please Select Bill Type", "Try Again ..." , JOptionPane.INFORMATION_MESSAGE);
        }

        else{
            try{

                jTable1.print(JTable.PrintMode.NORMAL,header,foter);

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Try Again"); }}
    }//GEN-LAST:event_jLabel57MouseClicked

    private void jTable2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable2MouseEntered

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
            java.util.logging.Logger.getLogger(fi_bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fi_bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fi_bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fi_bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fi_bill().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField advance;
    private javax.swing.JTextField amount;
    private com.toedter.calendar.JDateChooser billmonth;
    private javax.swing.JTextField billno;
    private javax.swing.JTextField billtype;
    private javax.swing.JLabel bulb;
    private javax.swing.JLabel date;
    private com.toedter.calendar.JDateChooser duedate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel phone;
    private javax.swing.JComboBox<String> status;
    private javax.swing.JLabel time;
    private javax.swing.JLabel water;
    // End of variables declaration//GEN-END:variables
}
