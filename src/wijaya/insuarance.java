/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import com.sun.glass.events.KeyEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import static net.sf.jasperreports.engine.JasperCompileManager.compileReport;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import static wijaya.repair_mainitain.validateDouble;

/**
 *
 * @author salit
 */
public class insuarance extends javax.swing.JFrame {
    Connection con=null;
    PreparedStatement pst;
    DB db = new DB();
    
    public insuarance() {
        initComponents();
        
        { 
        jPanel2.setBackground(new Color(255,255,255,100));
        
        }
        
        
        {
           
            showdate();
           showtime();
           LoadTable();
           GetLastInsuaranceId();
           fillcombo();                 
        }       
    }
    public static boolean validateDouble(String testString)
    {
        boolean re=true;
            testString = testString.trim();
            
            String regexDecimal = "^-?\\d*\\.\\d+$";
            String regexInteger = "^-?\\d+$";
            String regexDouble = regexDecimal + "|" + regexInteger;
            
            Pattern pattern = Pattern.compile(regexDouble);
                Matcher matcher = pattern.matcher(testString);
            re = (matcher.find());
            
            return re;
            
    }
    void fillcombo(){
        
    jComboBox1.removeAllItems();
     try {
            ResultSet rs = DB.getData("SELECT VID FROM vehicle");
            while (rs.next()) {
            jComboBox1.addItem(rs.getString("VID"));
                              }
         } 
     catch (Exception e) {
        e.printStackTrace();
        }
    }
 
public void GetLastInsuaranceId(){    
     try {
            ResultSet rs = DB.getData("SELECT INSID FROM insuarence ORDER BY INSID DESC LIMIT 1");            
            while (rs.next()){
                jLabel18.setText(rs.getString("INSID"));              
                             }           
        } 
     catch (Exception e) {
            System.out.println(e);
                         }
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
                        Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                    }
                      
                }
            }
        };
        clock.start();
       }
        /********************** DATE AND TIME *********************************/
    
    
    public void LoadTable(){
               DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                
        try {
            ResultSet rs = DB.getData("select* from insuarence");
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("INSID"));
                v.add(rs.getString("VID"));
                v.add(rs.getString("expire_date"));
                v.add(rs.getString("amount"));
                v.add(rs.getString("payment_date"));             
                dtm.addRow(v);
                            }
            } 
        catch (Exception e) {
            e.printStackTrace();
                            }
    }
    public void clearAll(){
        insid.setText("");
        jComboBox1.setSelectedIndex(-1);
       ((JTextField)cal1.getDateEditor().getUiComponent()).setText("");
        amount1.setText("");
        ((JTextField)cal2.getDateEditor().getUiComponent()).setText("");
    }
     
     public boolean ValidationForSave(){
          boolean b1 = true; 
       
          String INSID,VID,expire_date,amount,payment_date;
       
       String INSIDpattern1 = "[\\d]{2}-[\\d]{4}";
       String INSIDpattern2 = "[\\d]{2}[\\d]{4}";
       String VIDpattern1 = "([A-Za-z]{2}-[\\d]{4})";
       String VIDpattern2 = "([A-Za-z]{3}[\\d]{4})";
      
        INSID = insid.getText();
        VID =jComboBox1.getSelectedItem().toString();
        expire_date = ((JTextField)cal1.getDateEditor().getUiComponent()).getText();
        amount = amount1.getText();
        payment_date = ((JTextField)cal2.getDateEditor().getUiComponent()).getText();
        
        if(INSID.equals("") || VID.equals("")|| expire_date.equals("")|| amount.equals("")|| payment_date.equals("") || !validateDouble(amount)){
            JOptionPane.showMessageDialog(null, "Invalid inputs!!","",JOptionPane.ERROR_MESSAGE);
            return b1;
        }
         if(!INSID.matches(INSIDpattern2) && !INSID.matches(INSIDpattern1)){
             JOptionPane.showMessageDialog(null, "Invalid Insuarance Number!! Ex :00-0000 or 000000","",JOptionPane.ERROR_MESSAGE);
             return b1;
         }
         
         if(!VID.matches(VIDpattern2) && !VID.matches(VIDpattern1)){
             JOptionPane.showMessageDialog(null, "Invalid Vehicle Number!! Ex :XX-0000 or XXX0000","",JOptionPane.ERROR_MESSAGE);
             return b1;
         }
     return b1;
     }
    

    
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        Delete1 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        amount1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<String>();
        cal1 = new com.toedter.calendar.JDateChooser();
        cal2 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        q = new javax.swing.JLabel();
        insid = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        btnNew1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Last Insuarance ID -");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(150, 180, 110, 14);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(260, 180, 110, 14);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(10, 520, 80, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(120, 520, 100, 33);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(660, 210, 140, 2);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("INSUARANCE VEHICLE");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(690, 180, 160, 30);

        Delete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        Delete1.setText("Delete");
        Delete1.setMaximumSize(new java.awt.Dimension(97, 33));
        Delete1.setMinimumSize(new java.awt.Dimension(97, 33));
        Delete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Delete1ActionPerformed(evt);
            }
        });
        getContentPane().add(Delete1);
        Delete1.setBounds(250, 520, 100, 33);

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSearch.setMaximumSize(new java.awt.Dimension(65, 20));
        txtSearch.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSearch.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(txtSearch);
        txtSearch.setBounds(520, 250, 160, 30);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Insuarance ID", "Vehicle ID", "Expire Date", "Amount", "Payment Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(520, 310, 590, 190);

        amount1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        amount1.setMaximumSize(new java.awt.Dimension(65, 20));
        amount1.setMinimumSize(new java.awt.Dimension(65, 20));
        amount1.setPreferredSize(new java.awt.Dimension(65, 20));
        amount1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                amount1KeyPressed(evt);
            }
        });
        getContentPane().add(amount1);
        amount1.setBounds(180, 360, 160, 30);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Payment Date");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 420, 90, 15);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle/go_previous_blue.png"))); // NOI18N
        jLabel9.setText("jLabel8");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(90, 120, 60, 40);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(180, 260, 160, 30);

        cal1.setDateFormatString("yyyy-MM-dd");
        cal1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cal1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cal1MouseReleased(evt);
            }
        });
        getContentPane().add(cal1);
        cal1.setBounds(180, 310, 160, 30);

        cal2.setDateFormatString("yyyy-MM-dd");
        getContentPane().add(cal2);
        cal2.setBounds(180, 410, 160, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Expire Date");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 320, 90, 15);

        q.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        q.setForeground(new java.awt.Color(255, 255, 255));
        q.setText("Insuarence ID");
        getContentPane().add(q);
        q.setBounds(30, 210, 120, 30);

        insid.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        insid.setMaximumSize(new java.awt.Dimension(65, 20));
        insid.setMinimumSize(new java.awt.Dimension(65, 20));
        insid.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(insid);
        insid.setBounds(180, 210, 160, 30);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Amount");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 370, 90, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vehicle ID");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 270, 90, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 110, 60, 50);

        jButton8.setText("Report");
        jButton8.setMaximumSize(new java.awt.Dimension(97, 33));
        jButton8.setMinimumSize(new java.awt.Dimension(97, 33));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton8);
        jButton8.setBounds(380, 520, 100, 30);

        btnNew1.setText("Clear");
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew1);
        btnNew1.setBounds(350, 210, 70, 30);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 270, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 20, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 0, 70, 80));

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
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1260, 10, 30, 30));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1390, 101);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 70, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 100, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 100, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(490, 170, 650, 380);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1366, 768));
        jLabel2.setMinimumSize(new java.awt.Dimension(1366, 768));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(1366, 768));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1390, 770);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(260, 510, 93, 33);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        this.dispose();
       new Home().setVisible(true);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void cal1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cal1MouseReleased
        
    }//GEN-LAST:event_cal1MouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

       if (txtSearch.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Search value cannot be empty!!","",JOptionPane.ERROR_MESSAGE);
        } else{
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {
                ResultSet rs = DB.getData("select* from insuarence where INSID like '%"+txtSearch.getText()+"%'");

                while (rs.next()){
                    Vector v = new Vector();
                    v.add(rs.getString("INSID"));
                    v.add(rs.getString("VID"));
                    v.add(rs.getString("expire_date"));
                    v.add(rs.getString("amount"));
                    v.add(rs.getString("payment_date"));
                    dtm.addRow(v);
                }

            } 
            catch (Exception e) {
                e.printStackTrace();

            }
        }      
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       LoadTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

         DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while(dtm.getRowCount() > 0)
        {
            dtm.removeRow(0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     
        ResultSet rs=null;
        try {
            rs = db.getData("select * from insuarence where INSID = '" + insid.getText() + "'");
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Vehicle ID "+insid.getText()+" alredy exists. You cannot insert!!","",JOptionPane.ERROR_MESSAGE);  
            }
            
            else{
                boolean validationStatus = ValidationForSave();
                if (validationStatus == true   ) {
                    
                    
                    String INSID,VID,expire_date,payment_date;
            
            INSID = insid.getText();
            VID = jComboBox1.getSelectedItem().toString();
            expire_date = ((JTextField)cal1.getDateEditor().getUiComponent()).getText();
            double Amount = Double.parseDouble(amount1.getText());
            payment_date = ((JTextField)cal2.getDateEditor().getUiComponent()).getText();

            try {
                String strSql= ("insert into insuarence value('"+INSID+"', '"+VID+"', '"+expire_date+"' ,'"+Amount+"','"+payment_date+"')");         
                JOptionPane.showMessageDialog(this, "Vehicle ID: "+VID+"  Added Successfully");
                System.out.println(strSql);
                db.setData(strSql);
                } 
            catch (Exception ex) {
                ex.printStackTrace();
                                 }
            clearAll();
            GetLastInsuaranceId();
            LoadTable();
                }            }
        } 
        catch (Exception ex) {
            Logger.getLogger(insuarance.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        boolean validationStatus = ValidationForSave();
        if(validationStatus==true){  
         
            String INSID,VID,expire_date,payment_date;
        
        INSID = insid.getText();
        VID = jComboBox1.getSelectedItem().toString();
        double Amount=Double.parseDouble(amount1.getText());
        SimpleDateFormat j = new SimpleDateFormat("yyyy-MM-dd");
        expire_date = j.format(cal1.getDate());
        payment_date = j.format(cal2.getDate());
           
        try {
           String strSql =  "update insuarence set VID='"+VID+"',expire_date='"+expire_date+"',amount='"+Amount+"', payment_date='"+payment_date+"'where INSID='"+INSID+"'";
           db.setData(strSql);
           JOptionPane.showMessageDialog(this, "Insuarance "+INSID+"  updated Successfully");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        GetLastInsuaranceId();
        LoadTable();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       
        try {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            
            int row = this.jTable1.getSelectedRow();
            this.insid.setText(dtm.getValueAt(row, 0).toString());
            this.jComboBox1.setSelectedItem(dtm.getValueAt(row, 1).toString());
            SimpleDateFormat j = new SimpleDateFormat("yyyy-MM-dd");
            cal1.setDate(j.parse(dtm.getValueAt(row, 2).toString()));
            cal2.setDate(j.parse(dtm.getValueAt(row, 4).toString()));
            this.amount1.setText(dtm.getValueAt(row, 3).toString());
                                  
            insid.enable(false);
            jButton1.enable(false);
        } 
        catch (ParseException ex) {
            Logger.getLogger(insuarance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel28MouseClicked

    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
       insid.enable(true);
       jButton1.enable(true);
       clearAll();
    }//GEN-LAST:event_btnNew1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
         try {
            
           int i = JOptionPane.showConfirmDialog(null,"Do You Want to Print a Receipt?");
            if(i==0){

               String path;
                path = "krishani\\report2.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);
                        }
            
        } catch (Exception e) {
         e.printStackTrace();
        }  
    }//GEN-LAST:event_jButton8ActionPerformed

    private void amount1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_amount1KeyPressed

         char amount1 =evt.getKeyChar();
         if(!(Character.isDigit(amount1))|| (amount1 == KeyEvent.VK_BACKSPACE)||(amount1 == KeyEvent.VK_DELETE)){
         //evt.consume();
         }else evt.consume();
    }//GEN-LAST:event_amount1KeyPressed

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.dispose();
        new main2().setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked

    private void Delete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Delete1ActionPerformed
     int x = JOptionPane.showConfirmDialog(null, "Do you want to remove this vehicle?");
        if (x == 0) {
            try {
                
                String strSql = "delete from insuarence where INSID = '" + insid.getText() + "'";
                DB.setData(strSql);

                JOptionPane.showMessageDialog(this, "Insuarance " + insid.getText() + "  Deleted Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
            clearAll();
            GetLastInsuaranceId();
            LoadTable();
            insid.enable(true);
        }  
    }//GEN-LAST:event_Delete1ActionPerformed
    
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
    private javax.swing.JButton Delete1;
    private javax.swing.JTextField amount1;
    private javax.swing.JButton btnNew1;
    private com.toedter.calendar.JDateChooser cal1;
    private com.toedter.calendar.JDateChooser cal2;
    private javax.swing.JLabel date;
    private javax.swing.JTextField insid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel q;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
