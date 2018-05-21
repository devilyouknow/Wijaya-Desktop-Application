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
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author salit
 */
public class c_Customer extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public c_Customer() {
        initComponents();
        
        lastId();
        
        { 
       
        jPanel2.setBackground(new Color(0,0,0,50));
        
        
        }
        
        
        {
            //implementing date and time methods
            showdate();
            showTime();
            loadTable();
            fillcombo();
          
        }
        
        
    }
    
    void fillcombo(){
        jComboBox1.removeAllItems();
        
        try {
            
            ResultSet rs = DB.getData("SELECT CID FROM rec_customers");
            while(rs.next()) {                
                jComboBox1.addItem(rs.getString("CID"));
                
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }
    
    
    void loadTable(){
    
     DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from customers");

            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("CID"));
                v.add(rs.getString("f_name"));
                v.add(rs.getString("l_name"));
                v.add(rs.getString("address_1"));
                v.add(rs.getString("address_2"));
                v.add(rs.getString("city"));
                v.add(rs.getString("tel"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("nic"));
                if(rs.getInt("loyalty")==1){

                    v.add("Yes");

                }else{
                    v.add("NO");
                }

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
    
    
    
    
    public static boolean validatePhone( String val )
   {
       //Pattern pattern = Pattern.compile("\\d{10}");
       // Matcher matcher = pattern.matcher(phone);
      
        
       // return matcher.matches();
       if(val.startsWith("07") || val.startsWith("+94"))
        {
            if(val.startsWith("+94"))
            {
                if(val.length()==12)
                {
                    return true;
                }
                else return false;
            }
            else if(val.length()==10)
            {
                return true;
            }
            else return false;
        }
        String ph_pattern="^\\+?[0-9.()-](10,25)$";
        if(val.length()==10)
        {
            return true;
        }
    else return false;
   }
    
    
     public static boolean validateNIC( String nic )
   {
       boolean nic1 =true;
       if(nic.length()!=10){
            
            nic1 = false;
            return nic1;
        }
        
        
        int length =  nic.length()-1;
        
        if(length==9&&(nic.charAt(9)=='v'||nic.charAt(9)=='V')){
        
        nic1 = true;
         
        
        }else{
        nic1 = false;
        
       
            
        
        } 
         return nic1;
   } 
    
    
    
    
    
    
    void lastId(){
    
     try {
            ResultSet rs = DB.getData("SELECT CID FROM customers ORDER BY CID DESC LIMIT 1");
            
            while (rs.next()){
                jLabel18.setText(rs.getString("CID"));
                System.out.println(rs.getString("CID")); 
               
              
                
                
                
            }
            
        } catch (Exception e) {
            System.out.println(e);
    }
    
    
    
    
    }
    
    
    
    
    
    
    // date and time
    final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = adf.format(d);
        date.setText(date1);
        
         }
    
    
    
    void showTime(){
        new Timer(0, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
            
              Date d = new Date();
              SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
              String time1 = sdf.format(d);
             time.setText(time1);
            }
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

        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jTextField10 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jTextField9 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(1170, 180, 70, 30);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(1060, 180, 100, 30);

        jTextField10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField10KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField10);
        jTextField10.setBounds(800, 180, 230, 30);

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("NIC/CID/Tel/Name");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(660, 190, 130, 14);

        jButton4.setText("Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(900, 480, 59, 25);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "CID", "First Name", "Last Name", "Address Line 1", "Address Line 2", "City", "Telephone", "dob", "NIC", "Loyalty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
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

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(570, 230, 700, 240);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 20, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel24.setText("jLabel22");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 0, 70, 80));

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
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1220, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1370, 101);

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
        jLabel23.setBounds(80, 110, 100, 50);

        jDateChooser1.setDateFormatString("yyyy.MM.dd");
        jDateChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jDateChooser1MouseClicked(evt);
            }
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
        jDateChooser1.setBounds(230, 570, 220, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(320, 180, 110, 14);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Last Customer ID -");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(230, 180, 110, 14);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(410, 680, 97, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 680, 120, 33);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 680, 100, 33);

        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField9KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField9);
        jTextField9.setBounds(230, 610, 220, 30);

        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField7KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField7);
        jTextField7.setBounds(230, 530, 220, 30);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("NIC Number");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(80, 620, 90, 15);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Date of Birth");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(80, 580, 90, 15);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Telephone");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(80, 540, 90, 15);

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField6);
        jTextField6.setBounds(230, 460, 220, 30);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField5);
        jTextField5.setBounds(230, 420, 220, 30);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(230, 380, 220, 30);

        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField3);
        jTextField3.setBounds(230, 280, 220, 30);

        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField2);
        jTextField2.setBounds(230, 240, 220, 30);

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
        jTextField1.setBounds(230, 200, 220, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("City");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(80, 470, 90, 15);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Line 2");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(80, 430, 90, 15);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Line 1");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(80, 390, 90, 15);

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(280, 340, 100, 10);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Address");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(300, 320, 90, 10);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Last Name");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(80, 290, 90, 15);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("First Name");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(80, 250, 90, 15);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Customer ID");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(80, 210, 90, 15);

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

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Recover");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 60, -1));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 90, 10));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("CID");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 30, -1));

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Undelete_25px.png"))); // NOI18N
        jButton7.setText("Recover");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 50, 110, 30));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel2.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 52, 140, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(730, 590, 370, 120);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1370, 770);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        
        this.dispose();
        new Home().setVisible(true);
        
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       
        String cid,f_name,l_name,ad1,ad2,city,dob,nic,tel;
         
        
        cid = jTextField1.getText();
        f_name = jTextField2.getText();
        l_name = jTextField3.getText();
        ad1 = jTextField4.getText();
        ad2 = jTextField5.getText();
        city = jTextField6.getText();
        tel = jTextField7.getText();
        dob = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        nic = jTextField9.getText();
        
        int loyalty =0;
        
        if(jTextField1.getText().equals("")||jTextField2.getText().equals("")||jTextField3.getText().equals("")||jTextField4.getText().equals("")||jTextField5.getText().equals("")||jTextField6.getText().equals("")||
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText().equals("")||jTextField9.getText().equals("")){
            
            JOptionPane.showMessageDialog(this, "Fields Cannot Be Empty!!");
            return;
             
        }else{
        try {

            DB.setData("insert into customers value('"+cid+"', '"+f_name+"', '"+l_name+"', '"+ad1+"','"+ad2+"','"+city+"','"+tel+"','"+dob+"','"+nic+"','"+loyalty+"','"+date.getText()+"')");
            JOptionPane.showMessageDialog(this, "Customer  "+f_name+" "+l_name+"  Added Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
                
        //clearing textfields after
          jTextField1.setText("");      
          jTextField2.setText("");    
          jTextField3.setText("");    
          jTextField4.setText("");    
          jTextField5.setText("");    
          jTextField6.setText("");    
          jTextField7.setText("");    
          ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");    
          jTextField9.setText("");    
        
          
          //
          lastId();
          loadTable();
        
        
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
         String cid,f_name,l_name,ad1,ad2,city,dob,nic;
        int tel;
        
        cid = jTextField1.getText();
        f_name = jTextField2.getText();
        l_name = jTextField3.getText();
        ad1 = jTextField4.getText();
        ad2 = jTextField5.getText();
        city = jTextField6.getText();
        tel = Integer.parseInt(jTextField7.getText());
        dob = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        nic = jTextField9.getText();
        
        int loyalty =0;
        
        
        
        try {
            DB.setData("insert into rec_customers value('"+cid+"', '"+f_name+"', '"+l_name+"', '"+ad1+"','"+ad2+"','"+city+"','"+tel+"','"+dob+"','"+nic+"','"+loyalty+"','"+jLabel3.getText()+"')");
            DB.setData("delete from customers where cid = '"+jTextField1.getText()+"'");

            JOptionPane.showMessageDialog(this, "Customer "+jTextField2.getText()+" "+jTextField3.getText()+" Deleted Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        
        
        //clearing textfields after
          jTextField1.setText("");      
          jTextField2.setText("");    
          jTextField3.setText("");    
          jTextField4.setText("");    
          jTextField5.setText("");    
          jTextField6.setText("");    
          jTextField7.setText("");    
         ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");     
          jTextField9.setText(""); 
        
          lastId();
          loadTable();
          fillcombo();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
      
        try {
            ResultSet rs = DB.getData("select* from rec_customers where CID='"+jComboBox1.getSelectedItem().toString()+"'");
            while (rs.next()){
                String CID =(rs.getString("CID"));
                String f_name =(rs.getString("f_name"));
                String l_name =(rs.getString("l_name"));
                String address_1 =(rs.getString("address_1"));
                String address_2 =(rs.getString("address_2"));
                String city =(rs.getString("city"));
                String tel =(rs.getString("tel"));
                String dob =(rs.getString("dob"));
                String nic =(rs.getString("nic"));
                String loyalty =(rs.getString("loyalty"));
                String date =(rs.getString("date"));

               DB.setData("insert into customers value('"+CID+"', '"+f_name+"', '"+l_name+"', '"+address_1+"','"+address_2+"','"+city+"','"+tel+"','"+dob+"','"+nic+"','"+loyalty+"','"+date+"')");
                JOptionPane.showMessageDialog(this, "Customer "+f_name+" "+l_name+" Recovery is Successfull");
               DB.setData("DELETE from rec_customers where CID='"+jComboBox1.getSelectedItem().toString()+"'");
               
                
                

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        jComboBox1.setSelectedIndex(-1);
        
        loadTable();
        lastId();
        fillcombo();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
        String cid,f_name,l_name,ad1,ad2,city,dob,nic,tel;
      
        
        cid = jTextField1.getText();
        f_name = jTextField2.getText();
        l_name = jTextField3.getText();
        ad1 = jTextField4.getText();
        ad2 = jTextField5.getText();
        city = jTextField6.getText();
        tel = jTextField7.getText();
        dob = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        nic = jTextField9.getText();
        
        int loyalty =0;
        
        
        try {

            DB.setData("update customers set f_name='"+f_name+"', l_name='"+l_name+"',address_1='"+ad1+"', address_2='"+ad2+"', city='"+city+"', tel='"+tel+"', dob='"+dob+"', nic='"+nic+"',date ='"+date.getText()+"' where CID='"+cid+"'");
            // DB.setData("update rec_customers set f_name='"+f_name+"', l_name='"+l_name+"',address_1='"+ad1+"', address_2='"+ad2+"', city='"+city+"', tel='"+tel+"', dob='"+dob+"', nic='"+nic+"' where CID='"+cid+"'");
            
             JOptionPane.showMessageDialog(this, "Customer  "+f_name+" "+l_name+"  updated Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
                
        //clearing textfields after
          jTextField1.setText("");      
          jTextField2.setText("");    
          jTextField3.setText("");    
          jTextField4.setText("");    
          jTextField5.setText("");    
          jTextField6.setText("");    
          jTextField7.setText("");    
          ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");    
          jTextField9.setText("");    
        
          
          //
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
       String name  = jTextField2.getText();
       
       boolean b = validateName(name);
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            if(b){
                jTextField2.setForeground(Color.BLACK);
            jTextField3.requestFocus();
            }

            else{
                
            JOptionPane.showMessageDialog(this, "Invalid First Name!!");
            jTextField2.setForeground(Color.RED);
            }
            
        
        }
    }//GEN-LAST:event_jTextField2KeyPressed

    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        
          String name  = jTextField3.getText();
       int length =name.length();
       boolean b = validateName(name);
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            if(b){
                jTextField3.setForeground(Color.BLACK);
            jTextField4.requestFocus();
            }

            else{
                
            JOptionPane.showMessageDialog(this, "Invalid Last Name!!");
            jTextField3.setForeground(Color.RED);
            }
            
        
        }
    }//GEN-LAST:event_jTextField3KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        jTextField6.requestFocus();
        }
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        
          String name  = jTextField6.getText();
       
       boolean b = validateName(name);
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            
            if(b){
                jTextField6.setForeground(Color.BLACK);
            jTextField7.requestFocus();
            }

            else{
                
            JOptionPane.showMessageDialog(this, "Invalid City!!");
            jTextField6.setForeground(Color.RED);
            }
            
        
        }
    }//GEN-LAST:event_jTextField6KeyPressed

    private void jTextField7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyPressed
       
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(validatePhone(jTextField7.getText())){
              
             jDateChooser1.requestFocus();
             jTextField7.setForeground(Color.BLACK);
         
         } else{
         JOptionPane.showMessageDialog(this, "Invalid Phone Number!!");
         jTextField7.requestFocus();
         jTextField7.setForeground(Color.red);
         
         
         }
        }
    }//GEN-LAST:event_jTextField7KeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        
        
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void jLabel23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel23MouseClicked
        this.dispose();
        new Customer().setVisible(true);
    }//GEN-LAST:event_jLabel23MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jDateChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseClicked

         
             
        
    }//GEN-LAST:event_jDateChooser1MouseClicked

    private void jTextField9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField9KeyPressed
       
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            if(validateNIC(jTextField9.getText())){
              
             jButton1.requestFocus();
             jTextField9.setForeground(Color.BLACK);
         
         } else{
         JOptionPane.showMessageDialog(this, "Invalid NIC!!");
         jTextField9.requestFocus();
         jTextField9.setForeground(Color.red);
         
         
         }
        }
        
    }//GEN-LAST:event_jTextField9KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

               DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        
                int row = this.jTable1.getSelectedRow();
                this.jTextField1.setText(dtm.getValueAt(row, 0).toString());
                this.jTextField2.setText(dtm.getValueAt(row, 1).toString());
                this.jTextField3.setText(dtm.getValueAt(row, 2).toString());
                this.jTextField4.setText(dtm.getValueAt(row, 3).toString());
                this.jTextField5.setText(dtm.getValueAt(row, 4).toString());
                this.jTextField6.setText(dtm.getValueAt(row, 5).toString());
                this.jTextField7.setText(dtm.getValueAt(row, 6).toString());
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 7).toString());
                this.jTextField9.setText(dtm.getValueAt(row, 8).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        MessageFormat header = new MessageFormat("Customers");
        MessageFormat footer = new MessageFormat("Page{0,number,integer}");
        try {
            jTable1.print(JTable.PrintMode.NORMAL, header, footer);

        } catch (PrinterException ex) {
            Logger.getLogger(c_Customer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while(dtm.getRowCount() > 0)
        {
            dtm.removeRow(0);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

       loadTable();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jTextField10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField10KeyPressed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            
            ResultSet rs = DB.getData("SELECT * FROM customers where tel LIKE '"+jTextField10.getText()+"%' or nic LIKE'"+jTextField10.getText()+"%'or f_name LIKE'"+jTextField10.getText()+"%' or l_name LIKE '"+jTextField10.getText()+"%' or city LIKE '"+jTextField10.getText()+"%' ");
            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("CID"));
                v.add(rs.getString("f_name"));
                v.add(rs.getString("l_name"));
                v.add(rs.getString("address_1"));
                v.add(rs.getString("address_2"));
                v.add(rs.getString("city"));
                v.add(rs.getString("tel"));
                v.add(rs.getString("dob"));
                v.add(rs.getString("nic"));

                if(rs.getInt("loyalty")==1){

                    v.add("Yes");

                }else{
                    v.add("NO");
                }

                dtm.addRow(v);

                System.out.println("inside while");

            }

        } catch (Exception e) {
            e.printStackTrace();

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
    private javax.swing.JComboBox jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
