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
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author salit
 */
public class c_Orders extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public c_Orders() {
        initComponents();
        
        lastId();
        
        { //making panel transparent
        
        jPanel2.setBackground(new Color(0,0,0,50));
        jPanel3.setBackground(new Color(0,0,0,50));
        
        }
        
        
        {
            //implementing date and time methods
            System.out.println(loyal("001"));
            showdate();
            showtime();
            loadTable();
            fillcombo();
            fillcombo1();
            AutoCompleteDecorator.decorate(jComboBox1);
          
        }
        
        
    }
    
    void loadTable(){
    
    
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from orders");
           
            while (rs.next()){
                Vector v = new Vector();
               
                v.add(rs.getString("OID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("description"));
                v.add(rs.getString("total"));
                v.add(rs.getString("advance"));
                v.add(rs.getString("balance"));
                v.add(rs.getString("date"));
                v.add(rs.getString("status"));
                

                dtm.addRow(v);
                
                System.out.println("inside while");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    
    }
    
    
    
    String loyal(String ID){
        
        int k = 0;
    
        try {
            
            ResultSet rs = DB.getData("SELECT COUNT(CID) AS cc FROM orders WHERE CID ='"+ID+"'");
            while (rs.next()) {
            
                k = rs.getInt("cc");
                
            }
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.toString(k);
    }
    
    
    void fillcombo1(){
        jComboBox2.removeAllItems();
        
        try {
            
            ResultSet rs = DB.getData("SELECT OID FROM rec_orders");
            while (rs.next()) {        
                
                jComboBox2.addItem(rs.getString("OID"));
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
    
    
    void fillcombo(){
    
        jComboBox1.removeAllItems();
        
        try {
            
            
            ResultSet rs = DB.getData("SELECT CID from customers");
            while(rs.next()){
            
                jComboBox1.addItem(rs.getString("CID"));
                
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        
    }
    
     public static boolean validateDouble( String testString )
   {
      boolean re =true;
      
        
        testString = testString.trim();
   
        String regexDecimal = "^-?\\d*\\.\\d+$";
        String regexInteger = "^-?\\d+$";
        String regexDouble = regexDecimal + "|" + regexInteger;
 
        Pattern pattern = Pattern.compile(regexDouble);
         Matcher matcher = pattern.matcher(testString);
        re =(matcher.find());
        
      return re;
   }
    
    void lastId(){
    
     try {
            ResultSet rs = DB.getData("SELECT OID FROM orders ORDER BY OID DESC LIMIT 1");
            
            while (rs.next()){
                jLabel18.setText(rs.getString("OID"));
                System.out.println(rs.getString("OID")); 
               
              
                
                
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
    }
    
    
    
    
    }
    
    
    
    
    
    
    // date and time
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextField3 = new javax.swing.JTextArea();
        jLabel22 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 0));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(550, 570, 290, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(260, 230, 240, 30);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel24.setText("jLabel22");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 70, 80));

        jLabel28.setBackground(new java.awt.Color(51, 51, 51));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cancel.png"))); // NOI18N
        jLabel28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel28MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1330, 10, 30, 30));

        jLabel25.setBackground(new java.awt.Color(51, 51, 51));
        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("LogOut");
        jLabel25.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1380, 101);

        jButton8.setText("Clean");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(420, 700, 90, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel12.setText("Back");
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel12);
        jLabel12.setBounds(120, 110, 100, 50);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 255, 51));
        getContentPane().add(jLabel8);
        jLabel8.setBounds(380, 245, 150, 20);

        jTextField3.setColumns(20);
        jTextField3.setRows(5);
        jScrollPane2.setViewportView(jTextField3);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(260, 290, 240, 120);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("OID");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(830, 680, 30, 15);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(930, 650, 90, 10);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Recover");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(950, 630, 60, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(350, 160, 110, 14);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Last Order ID -");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(260, 160, 110, 14);

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(1190, 180, 70, 30);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(1080, 180, 100, 30);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(970, 180, 100, 30);

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField10);
        jTextField10.setBounds(790, 180, 160, 30);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(270, 700, 93, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Complete and Print");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(410, 640, 170, 33);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add and Print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(220, 640, 150, 33);

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField6);
        jTextField6.setBounds(260, 550, 240, 30);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(260, 440, 240, 30);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField5);
        jTextField5.setBounds(260, 500, 240, 30);

        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField1.setMaximumSize(new java.awt.Dimension(65, 20));
        jTextField1.setMinimumSize(new java.awt.Dimension(65, 20));
        jTextField1.setPreferredSize(new java.awt.Dimension(65, 20));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(260, 180, 240, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Balance");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(110, 560, 90, 15);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Total");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(110, 450, 90, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Advance");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(110, 510, 90, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Description");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(110, 300, 90, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Customer ID");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(110, 240, 90, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Order ID");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(110, 190, 90, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 110, 50, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 62, 140, 30));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Undelete_25px.png"))); // NOI18N
        jButton7.setText("Recover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 110, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(800, 610, 370, 120);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("OID/CID");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 30, 60, 14));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "OIC", "CID", "Description", "Total", "Advance", "Balance", "Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 690, 310));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(590, 160, 750, 390);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 255, 51));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -80, 1380, 860);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        this.dispose();
        new Home().setVisible(true);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String OID,CID,desc,total,advance,balance,date;
        
        OID = jTextField1.getText();
        CID = jComboBox1.getSelectedItem().toString();
        desc = jTextField3.getText();
        total = jTextField5.getText();
        advance = jTextField4.getText();
        balance = jTextField6.getText();
        date = this.date.getText();
        String status ="Pending";
        
        
        
        
        if(jTextField1.getText().equals("")||jComboBox1.getSelectedItem().toString().equals("")||jTextField3.getText().equals("")||jTextField5.getText().equals("")||jTextField4.getText().equals("")||jTextField6.getText().equals("")){
            
            JOptionPane.showMessageDialog(this, "Fields Cannot Be Empty!!");
            return;
             
        }else{
        try {
            
                
            
            if(balance.equals("0")||balance.equals("0.0")||balance.equals("0.00")||balance.equals("00.00")||balance.equals("00.0")){
                
                DB.setData("insert into orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','Completed')");
                
                //interting into temporary databse
                 DB.setData("insert into temp_orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','Completed')");
                 int i = JOptionPane.showConfirmDialog(null, "Order  "+OID+" Added Successfully.Do You Want to Print a Receipt?");
            if(i==0){

                
                String path;
                path = "reports\\order2.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);

                DB.setData("delete from temp_orders where OID='"+OID+"'");
                
                
                
                 
            }
                 
                
                 
                 
                 //message
                //JOptionPane.showMessageDialog(this, "Order  "+OID+" Added Successfully");
            
            }else{
            
            DB.setData("insert into orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','"+status+"')");
            DB.setData("insert into rec_orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','"+status+"')");
            
            
            //interting into temporary databse
                 DB.setData("insert into temp_orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','"+status+"')");
                
                 int i = JOptionPane.showConfirmDialog(null, "Order  "+OID+" Added Successfully.Do You Want to Print a Receipt?");
            if(i==0){

                
                String path;
                path = "reports\\order1.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);

                DB.setData("delete from temp_orders where OID='"+OID+"'");
                
                
                
                 
            }
            
            
            
            }
            
            //getting the count of orders that customer has placed
            ResultSet rs = DB.getData("SELECT COUNT(OID) AS total FROM orders WHERE CID = '"+CID+"'");
            while(rs.next()){
                
                int count = rs.getInt("total");
                System.out.println(count);
                
                int loyalty =1;
                if(count>=5){
                    DB.setData("UPDATE customers set loyalty= '"+1+"' where CID='"+CID+"'");
                    DB.setData("UPDATE rec_customers set loyalty= '"+1+"' where CID='"+CID+"'");
                }
               
            }
            
            
            
            
            //clearing textfields after
          jTextField1.setText("");      
          jComboBox1.setSelectedIndex(-1);
          jTextField3.setText("");    
          jTextField5.setText("");    
          jTextField4.setText("");    
          jTextField6.setText("");    
          
        
          
          //
          lastId();
            
            
            
            
            
            
            

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        
        
        
        
        
                
        
        
        
        }
        loadTable();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        
        String OID,CID,desc,total,advance,balance,date;
        
        OID = jTextField1.getText();
        CID = jComboBox1.getSelectedItem().toString();
        desc = jTextField3.getText();
        total = jTextField5.getText();
        advance = jTextField4.getText();
        balance = jTextField6.getText();
        date = this.date.getText();
        String status ="Pending";
        
        
        
        try {
            
            DB.setData("insert into rec_orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','Completed')");
                

            DB.setData("delete from orders where OID = '"+jTextField1.getText()+"'");

            JOptionPane.showMessageDialog(this, "Order "+jTextField1.getText()+" is DELETED");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        
        
        //clearing textfields after
          jTextField1.setText("");      
          jComboBox1.setSelectedIndex(-1);
          jTextField3.setText("");    
          jTextField5.setText("");    
          jTextField4.setText("");    
          jTextField6.setText("");    
          
        
          lastId();
          loadTable();
          fillcombo1();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        
        
        
         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from orders where OID='"+jTextField10.getText()+"' or CID='"+jTextField10.getText()+"'");
           
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("OID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("description"));
                v.add(rs.getString("total"));
                v.add(rs.getString("advance"));
                v.add(rs.getString("balance"));
                v.add(rs.getString("date"));
                v.add(rs.getString("status"));
               
                

                dtm.addRow(v);
                
                System.out.println("inside while");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("finished");
        
        
        
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       
        
        loadTable();
        
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
      
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while(dtm.getRowCount() > 0)
        {
            dtm.removeRow(0);
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
            
        jTextField3.setText("");
        
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int row = this.jTable1.getSelectedRow();
        this.jTextField1.setText(dtm.getValueAt(row, 0).toString());
        this.jComboBox1.setSelectedItem(dtm.getValueAt(row, 1).toString());
        this.jTextField3.append(dtm.getValueAt(row, 2).toString());
        this.jTextField5.setText(dtm.getValueAt(row, 3).toString());
        this.jTextField4.setText(dtm.getValueAt(row, 4).toString());
        this.jTextField6.setText(dtm.getValueAt(row, 5).toString());
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
        String OID;
        try {
            ResultSet rs = DB.getData("select* from rec_orders where OID='"+jComboBox2.getSelectedItem().toString()+"'");
            while (rs.next()){
                 OID =(rs.getString("OID"));
                String CID =(rs.getString("CID"));
                String desc =(rs.getString("description"));
                String total =(rs.getString("total"));
                String advance =(rs.getString("advance"));
                String balance =(rs.getString("balance"));
                String date =(rs.getString("date"));
                String status =(rs.getString("status"));

                DB.setData("insert into orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date+"','"+status+"')");
                JOptionPane.showMessageDialog(this, "Order "+OID+" Recovery is Successfull");
                
                DB.setData("DELETE FROM rec_orders where OID ='"+OID+"'");
                
                

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        jComboBox2.setSelectedIndex(-1);
        loadTable();
        fillcombo1();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        String OID,CID,desc,total,advance,balance,date1;
        
        
        OID = jTextField1.getText();
        CID = jComboBox1.getSelectedItem().toString();
        desc = jTextField3.getText();
        total = jTextField5.getText();
        advance = jTextField5.getText();
        balance = "0";
        date1 = date.getText();
        String status ="Completed";
        
        
        try {

            DB.setData("update orders set CID='"+CID+"', description='"+desc+"',total='"+total+"', advance='"+advance+"', balance='"+balance+"', date='"+date1+"', status='"+status+"' where OID='"+OID+"'");
            DB.setData("update rec_orders set CID='"+CID+"', description='"+desc+"',total='"+total+"', advance='"+advance+"', balance='"+balance+"', date='"+date1+"', status='"+status+"' where OID='"+OID+"'");
            
            
            
            //interting into temporary databse
                 DB.setData("insert into temp_orders value('"+OID+"', '"+CID+"', '"+desc+"', '"+total+"','"+advance+"','"+balance+"','"+date1+"','Completed')");
                 
            int i = JOptionPane.showConfirmDialog(null, "Order  "+OID+" Added Successfully.Do You Want to Print a Receipt?");
            if(i==0){

                
                String path;
                path = "reports\\order2.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);

                DB.setData("delete from temp_orders where OID='"+OID+"'");
                
                
                
                 
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
                
        //clearing textfields after
          jTextField1.setText("");      
          jComboBox1.setSelectedIndex(-1);
          jTextField3.setText("");    
          jTextField5.setText("");    
          jTextField4.setText("");    
          jTextField6.setText("");    
             
        
          
          //
          lastId();
          loadTable();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        jComboBox1.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            
            if(validateDouble(jTextField5.getText())){
            
            
            double total = Double.parseDouble(jTextField4.getText());
            double advance = Double.parseDouble(jTextField5.getText());
            
            double balance = total-advance;
            jTextField5.setForeground(Color.BLACK);
            jTextField6.setText(Double.toString(balance));
            
        jTextField6.requestFocus();
        }else{
            jTextField5.setForeground(Color.red);
            jTextField5.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
       
        String fromtex = jComboBox1.getSelectedItem().toString();
        
        String fromtab = loyal(fromtex);
        
        int m = Integer.parseInt(fromtab);
       
        
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            String tot =jTextField4.getText();
            
            
            
            if(validateDouble(jTextField4.getText())){
            double total = Double.parseDouble(jTextField4.getText());
            
            
          
            
            if(m>=5){
            
                total = total-((total/100)*7);
                jTextField4.setText(Double.toString(total));
                jLabel4.setText("Loyal Customer 7% Discount");
            }
            
            
        jTextField5.requestFocus();
        jTextField4.setForeground(Color.BLACK);
            }else{
            jTextField4.setForeground(Color.RED);
            jTextField4.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        
       
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        this.dispose();
        new Customer().setVisible(true);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
       
          jTextField1.setText("");      
          jComboBox1.setSelectedIndex(-1);
          jTextField3.setText("");    
          jTextField5.setText("");    
          jTextField4.setText("");    
          jTextField6.setText(""); 
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from orders where OID='"+jTextField10.getText()+"' or CID='"+jTextField10.getText()+"'");
           
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("OID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("description"));
                v.add(rs.getString("total"));
                v.add(rs.getString("advance"));
                v.add(rs.getString("balance"));
                v.add(rs.getString("date"));
                v.add(rs.getString("status"));
               
                

                dtm.addRow(v);
                
                System.out.println("inside while");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        System.out.println("finished");
            
            
            
            
        jTable1.requestFocus();
        }
    }//GEN-LAST:event_jTextField10KeyPressed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextArea jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
