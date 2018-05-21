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
public class m_Machine extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public m_Machine() {
        initComponents();
        
        lastId();
        
        { //making panel transparent
        
        jPanel2.setBackground(new Color(0,0,0,50));
        jPanel3.setBackground(new Color(0,0,0,50));
        
        }
        
        
        {
            //implementing date and time methods
            jComboBox1.removeAllItems();
            jComboBox2.removeAllItems();
            
            showdate();
           showtime();
           fillcombo();
           fillcombo2();
           loadTable();
           AutoCompleteDecorator.decorate(jComboBox2);
           jComboBox2.setSelectedIndex(-1);
          
        }
        
        
    }
    
    
    void loadTable(){
     DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from machine");
           
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MID"));
                v.add(rs.getString("name"));
                v.add(rs.getString("type"));
                v.add(rs.getString("vendor"));
                v.add(rs.getString("warranty"));
                v.add(rs.getString("price"));
                v.add(rs.getString("date"));
                
                
                

                dtm.addRow(v);
                
                

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        
    
    
    }
    
   public static boolean validateName( String Name )
   {
      return Name.matches( "[A-Z][a-zA-Z]*" );
   } 
    
   void fillcombo2(){
   
       try {
          ResultSet rs = DB.getData("SELECT MID FROM rec_machine");
          while(rs.next()){
          jComboBox2.addItem(rs.getString("MID"));
          
          }
           
       } catch (Exception e) {
           e.printStackTrace();
       }
       
   }
    
    void fillcombo(){
    
        jComboBox1.addItem("Printer");
        jComboBox1.addItem("Cutter");
        jComboBox1.addItem("Binder");
        jComboBox1.addItem("PC");
        jComboBox1.addItem("Laptop");
        jComboBox1.addItem("PC-Part");
        jComboBox1.addItem("TV");
        jComboBox1.addItem("Other");
        

    
    }
    
    void lastId(){
    
     try {
            ResultSet rs = DB.getData("SELECT MID FROM machine ORDER BY MID DESC LIMIT 1");
            
            while (rs.next()){
                jLabel18.setText(rs.getString("MID"));
                System.out.println(rs.getString("MID")); 
               
              
                
                
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
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
    
    
    
    // date and time
    final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        date3.setText(datee);
        
         }
    
    void showtime(){
        
        new Timer(0,(ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String timee = sdf.format(d);
            time3.setText(timee);
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

        jComboBox1 = new javax.swing.JComboBox();
        jPanel6 = new javax.swing.JPanel();
        time3 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        date3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
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
        jPanel3 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(220, 320, 240, 30);

        jPanel6.setBackground(new java.awt.Color(0, 0, 0));
        jPanel6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time3.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time3.setForeground(new java.awt.Color(255, 255, 255));
        time3.setText("Time");
        jPanel6.add(time3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 10, 219, 60));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel6.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 10, 40, 60));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel6.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 40, 80));

        date3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date3.setForeground(new java.awt.Color(255, 255, 255));
        date3.setText("date");
        jPanel6.add(date3, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 20, 150, 40));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel6.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel38.setText("jLabel22");
        jPanel6.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 0, 70, 80));

        jLabel39.setBackground(new java.awt.Color(51, 51, 51));
        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cancel.png"))); // NOI18N
        jLabel39.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel39MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(1290, 10, 30, 30));

        jLabel40.setBackground(new java.awt.Color(51, 51, 51));
        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("LogOut");
        jLabel40.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 80, 60, -1));

        getContentPane().add(jPanel6);
        jPanel6.setBounds(0, 0, 1390, 101);

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel23.setText("Back");
        jLabel23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel23MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel23);
        jLabel23.setBounds(120, 110, 100, 50);

        jDateChooser1.setDateFormatString("yyyy.MM.dd");
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseReleased(evt);
            }
        });
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
        });
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(220, 440, 240, 30);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Undelete_25px.png"))); // NOI18N
        jButton7.setText("Recover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(990, 670, 110, 30);

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("MID");
        getContentPane().add(jLabel22);
        jLabel22.setBounds(800, 680, 30, 15);

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(900, 650, 90, 10);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Recover");
        getContentPane().add(jLabel17);
        jLabel17.setBounds(920, 630, 60, 15);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(310, 180, 110, 14);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Last Machine ID -");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(220, 180, 110, 14);
        getContentPane().add(jTextField10);
        jTextField10.setBounds(750, 200, 200, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("(S/N)/Name/Vendor");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(610, 210, 130, 14);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(370, 600, 97, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(230, 600, 110, 33);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(100, 600, 100, 33);

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField6);
        jTextField6.setBounds(220, 500, 240, 30);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(220, 380, 240, 30);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(220, 260, 240, 30);

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
        jTextField1.setBounds(220, 200, 240, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Price");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(70, 510, 90, 15);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Warranty");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(70, 450, 90, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Vendor");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(70, 390, 90, 15);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Type");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 330, 90, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Name");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(70, 270, 90, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Serial Nmber");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(70, 210, 90, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 110, 50, 40);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 130, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(770, 610, 370, 120);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 370, 70, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 120, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 20, 140, 30));

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "S/N", "Name", "Type", "Vendor", "Warranty", "Price", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
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

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 690, 300));

        jButton8.setBackground(new java.awt.Color(0, 204, 51));
        jButton8.setText("Print a Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 370, 190, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(570, 180, 770, 410);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1380, 780);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        this.dispose();
        new Home().setVisible(true);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
       String date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String ink = "100";
        
        if(jTextField1.getText().equals("")||jTextField2.getText().equals("")||jComboBox1.getSelectedItem().toString().equals("")||jTextField4.getText().equals("")||date.equals("")||jTextField6.getText().equals("")||date3.getText().equals("")){
        
            JOptionPane.showMessageDialog(this, "Fields Cannot be empty, Try Again");

        }else{
        
            try {

            DB.setData("insert into machine value('"+jTextField1.getText()+"', '"+jTextField2.getText()+"', '"+jComboBox1.getSelectedItem().toString()+"', '"+jTextField4.getText()+"','"+date+"','"+jTextField6.getText()+"','"+date3.getText()+"')");
            
            
            JOptionPane.showMessageDialog(this, "Machine  "+jTextField2.getText()+"  Added Successfully");
            
            if(jComboBox1.getSelectedItem().toString()==("Printer")){
                DB.setData("Insert into ink_level value('"+jTextField1.getText()+"','"+ink+"')");
                
            }

        } catch (Exception ex) {
            ex.printStackTrace();

        }
            
             lastId();
          jTextField1.setText("");
          jTextField2.setText("");
          jComboBox1.setSelectedIndex(-1);
          jTextField4.setText("");
          jTextField6.setText("");
          
          loadTable();
        
        
        }
        
        
        
        
        
        
         
          
          
        
        
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        
        
        
        try {
            
            
            DB.setData("insert into rec_machine value('"+jTextField1.getText()+"', '"+jTextField2.getText()+"', '"+jComboBox1.getSelectedItem().toString()+"', '"+jTextField4.getText()+"','"+date+"','"+jTextField6.getText()+"','"+date3.getText()+"')");
            

            DB.setData("delete from machine where MID = '"+jTextField1.getText()+"'");

            JOptionPane.showMessageDialog(this, "Machine "+jTextField2.getText()+" Deleted Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        
        
        //clearing textfields after
          jTextField1.setText("");      
          jTextField2.setText("");
          jComboBox1.setSelectedIndex(-1);
          jTextField4.setText("");
          jTextField6.setText("");
          
          
        
          lastId();
          loadTable();
          fillcombo2();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        
        
        
         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from machine where MID='"+jTextField10.getText()+"' or name='"+jTextField10.getText()+"' or type='"+jTextField10.getText()+"' or  vendor='"+jTextField10.getText()+"'");
           
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("MID"));
                v.add(rs.getString("name"));
                v.add(rs.getString("type"));
                v.add(rs.getString("vendor"));
                v.add(rs.getString("warranty"));
                v.add(rs.getString("price"));
                v.add(rs.getString("date"));
                
               
                

                dtm.addRow(v);
                
               

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
        
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int row = this.jTable1.getSelectedRow();
        this.jTextField1.setText(dtm.getValueAt(row, 0).toString());
        this.jTextField2.setText(dtm.getValueAt(row, 1).toString());
        this.jComboBox1.setSelectedItem(dtm.getValueAt(row, 2).toString());
        this.jTextField4.setText(dtm.getValueAt(row, 3).toString());
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 4).toString());
        this.jTextField6.setText(dtm.getValueAt(row, 5).toString());
   
        
      
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
         String MID;
        try {
            ResultSet rs = DB.getData("select* from rec_machine where MID='"+jComboBox2.getSelectedItem().toString()+"'");
            while (rs.next()){
                 MID =(rs.getString("MID"));
                String name =(rs.getString("name"));
                String type =(rs.getString("type"));
                String vendor =(rs.getString("vendor"));
                String warranty =(rs.getString("warranty"));
                String price =(rs.getString("price"));
                String date =(rs.getString("date"));
                

               DB.setData("insert into machine value('"+MID+"', '"+name+"', '"+type+"', '"+vendor+"','"+warranty+"','"+price+"','"+date+"')");
                JOptionPane.showMessageDialog(this, "Machine "+name+" Recovery is Successfull");
                
                DB.setData("DELETE FROM rec_machine WHERE MID ='"+MID+"'");
                
                

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        jComboBox2.removeAllItems();
        fillcombo2();
        
        loadTable();
        
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
       
        String date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        try {

            DB.setData("update machine set name='"+jTextField2.getText()+"', type='"+jComboBox1.getSelectedItem().toString()+"',vendor='"+jTextField4.getText()+"', warranty='"+date+"', price='"+jTextField6.getText()+"', date='"+date3.getText()+"' WHERE MID ='"+jTextField1.getText()+"'");
            DB.setData("update rec_machine set name='"+jTextField2.getText()+"', type='"+jComboBox1.getSelectedItem().toString()+"',vendor='"+jTextField4.getText()+"', warranty='"+date+"', price='"+jTextField6.getText()+"', date='"+date3.getText()+"' WHERE MID='"+jTextField1.getText()+"'");
            
            
            
             JOptionPane.showMessageDialog(this, "Machine  "+jTextField1.getText()+" updated Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
                
        //clearing textfields after
          jTextField1.setText("");      
          jTextField2.setText("");    
          jComboBox1.setSelectedIndex(-1);    
          jTextField4.setText("");    
;    
          jTextField6.setText("");    
           
          ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");    
      
        
          
          
          lastId();
          loadTable();
        
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jDateChooser1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseReleased
        //  if(jLabel12.getText().after(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText())){}
    }//GEN-LAST:event_jDateChooser1MouseReleased

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        jTextField2.requestFocus();
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        
        
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
                jComboBox1.requestFocus();
            
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            if(validateName(jTextField4.getText())){
                jDateChooser1.requestFocus();
                jTextField4.setForeground(Color.BLACK);
            }else{
                jTextField4.requestFocus();
                jTextField4.setForeground(Color.RED);
                
            }
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       
            if(validateDouble(jTextField6.getText())){
            jTextField6.setForeground(Color.BLACK);
            jButton1.requestFocus();
            }else{
            jTextField6.setForeground(Color.RED);
            jTextField6.requestFocus();
            }
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
//        jTextField9.requestFocus();
        }
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        this.dispose();
        new Machine().setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel39MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel39MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel39MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        try {
            String path;
                path = "reports\\machine.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

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
    private javax.swing.JLabel date3;
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
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel time3;
    // End of variables declaration//GEN-END:variables
}
