 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
//import static org.junit.experimental.ParallelComputer.methods;

/**
 *
 * @author Sri
 */
public class emp_emp extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    /**
     * Creates new form emp_emp
     */
    public emp_emp() {
        initComponents();
        
       
        
        //db
        con = DBconnect.connect();
        
        //table load and last ID
        tableload();
        lastid();
        
        
        showdate();
        showtime();
        
     
       
        
      
    }
  //***********************************************Phone Validation************************************//  
    
    boolean validatePhone(String val)
    {
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
   
    //***********************************************NIC Validation************************************//
     
     boolean validateNIC(String val)
     {
         String nic_pattern="^[0-9]{9}V$";
         if(val.matches(nic_pattern))
         {
             return true;
         }
         else return false;
     }
   
    //***********************************************Last ID************************************// 
     
    public void lastid()
    {
        try
        {
        String q="SELECT EmpId FROM my1 ORDER BY EmpId DESC LIMIT 1";
        pst = con.prepareStatement(q);
        rs = pst.executeQuery();
        
        while(rs.next())
        {
        lid.setText(rs.getString("EmpId"));
        }
        
        }
        catch(Exception e){}
    }
   
    
    //***********************************************Table Loade************************************//
    public void tableload()
    {
        try{
        String lod = "SELECT EmpId as Emp_ID, FirstName as First_Name,LastName as Last_Name,TelNo as Tel_no,Dateofbirth as Date_of_Birth,Nicnumber as Nic_Number,l1 as Ad_Line1,l2 as Ad_Line2,City as City,Gender as Gender,Age as Age,dep as Departmant FROM my1 order by EmpId";
        pst = con.prepareStatement(lod);
        rs = pst.executeQuery();
        
        table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       }
   
    
    
        /********************** DATE AND TIME *********************************/
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        b3 = new javax.swing.JTextField();
        b2 = new javax.swing.JTextField();
        b1 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tx = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        b6 = new javax.swing.JTextField();
        b4 = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        b9 = new javax.swing.JTextField();
        b8 = new javax.swing.JTextField();
        b7 = new javax.swing.JTextField();
        b11 = new javax.swing.JTextField();
        b10 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lid = new javax.swing.JLabel();
        b5 = new com.toedter.calendar.JDateChooser();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        b12 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        b13 = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel2.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 10, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel2.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel2.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 70, 80));

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
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 10, 30, 30));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 80, 60, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Employee");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 70, 130, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 110));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bola/rsz_1go_previous_blue.png"))); // NOI18N
        jLabel6.setText("jLabel6");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 50, 70));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Last Name");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        b3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 150, 30));

        b2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 150, 30));

        b1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel1.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 150, 30));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("First Name");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Employee Id");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, -1, -1));

        tx.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tx.setForeground(new java.awt.Color(255, 255, 255));
        tx.setText("Last Employee ID -");
        jPanel1.add(tx, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jLabel47.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Phone number");
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("date of birth");
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, -1));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Nic Number");
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 140, -1, -1));

        b6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        jPanel1.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 130, 140, 30));

        b4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                b4KeyTyped(evt);
            }
        });
        jPanel1.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 140, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Address");
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 50, -1));

        jLabel49.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Line 1");
        jPanel1.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 40, -1, -1));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Linee 2");
        jPanel1.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, -1, -1));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("City");
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, -1, -1));

        b9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 130, 150, 30));

        b8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        jPanel1.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 80, 150, 30));

        b7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        jPanel1.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 150, 30));

        b11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b11.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b11ActionPerformed(evt);
            }
        });
        jPanel1.add(b11, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 80, 140, 30));

        b10.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Male", "Female" }));
        b10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b10ActionPerformed(evt);
            }
        });
        jPanel1.add(b10, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 30, 140, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 255, 255));
        jLabel39.setText("Age");
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 90, 30, -1));

        jLabel40.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("Gender");
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 40, -1, 20));

        lid.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lid.setForeground(new java.awt.Color(255, 255, 255));
        lid.setText("ID");
        jPanel1.add(lid, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 30, 10));

        b5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 80, 140, 30));

        jButton7.setText("Clear");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton7MouseClicked(evt);
            }
        });
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 190, 90, 30));

        jButton6.setText("Delete");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 90, 30));

        jButton5.setText("update");
        jButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton5MouseClicked(evt);
            }
        });
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 100, 30));

        jButton3.setText("Add");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 90, 30));

        b12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b12.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Finances", "Designers", "Stores", "Drivers", "Labers" }));
        b12.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b12ActionPerformed(evt);
            }
        });
        jPanel1.add(b12, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 140, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Departmant");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 140, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 1160, 230));

        jPanel3.setBackground(new java.awt.Color(51, 51, 51));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "new", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11"
            }
        ));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(table);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 1130, 190));

        b13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        b13.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        b13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b13ActionPerformed(evt);
            }
        });
        b13.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                b13KeyReleased(evt);
            }
        });
        jPanel3.add(b13, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 30, 210, 20));

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("NIC/EID/Tel/Name");
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, 20));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 1160, 280));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bola/black_background_pattern_light_texture_55291_1920x1200.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1320, 670));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        new emp_home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b1ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b8ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b7ActionPerformed

    private void b11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b11ActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked

        int ro = table.getSelectedRow();

        DefaultTableModel dtm = (DefaultTableModel) table.getModel();

        String eid =table.getValueAt(ro,0).toString();
        String fname =table.getValueAt(ro,1).toString();
        String lname = table.getValueAt(ro,2).toString();
        String tel = table.getValueAt(ro,3).toString();
        ((JTextField)b5.getDateEditor().getUiComponent()).setText(dtm.getValueAt(ro, 4).toString());
        String nic = table.getValueAt(ro,5).toString();
        String l1 =table.getValueAt(ro,6).toString();
        String l2 =table.getValueAt(ro,7).toString();
        String city =table.getValueAt(ro,8).toString();
        String gen = table.getValueAt(ro,9).toString();
        String age = table.getValueAt(ro,10).toString();
        String dep = table.getValueAt(ro,11).toString();

        b1.setText(eid);
        b2.setText(fname);
        b3.setText(lname);
        b4.setText(tel);
        b6.setText(nic);
        b7.setText(l1);
        b8.setText(l2);
        b9.setText(city);
        b10.setSelectedItem(gen);
        b11.setText(age);
        b12.setSelectedItem(dep);

    }//GEN-LAST:event_tableMouseClicked

    private void b13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b13ActionPerformed

    private void b13KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b13KeyReleased
        String name = b13.getText();
       // String nic = b13.getText();
       // String tel = b13.getText();

        try
        {
            String sql ="select EmpId,FirstName,LastName,TelNo,Dateofbirth,Nicnumber,l1,"
            + "l2,City,Gender,Age,dep from my1 where FirstName LIKE '%"+name+"%'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_b13KeyReleased

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        String test = b4.getText();
        String ni = b6.getText();
        
        if(validatePhone(test))
        {
                if(validateNIC(ni))
                {
                    String eid   = b1.getText();
                    String fname = b2.getText();
                    String lname = b3.getText();
                    String tel   = b4.getText();
                    String birth   = ((JTextField)b5.getDateEditor().getUiComponent()).getText();
                    String nic   = b6.getText();
                    String l1    = b7.getText();
                    String l2    = b8.getText();
                    String city  = b9.getText();
                    String gen   = (String) b10.getSelectedItem();
                    String age   = b11.getText();
                    String dep   = (String) b12.getSelectedItem();

                   try
                    {
                    String ep = "INSERT INTO my1 (EmpId,FirstName,LastName,TelNo,Dateofbirth,Nicnumber,l1,l2,City,"
                    + "Gender,Age,dep) values ('"+eid+"','"+fname+"','"+lname+"','"+tel+"','"+birth+"','"+nic+"',"
                    + "'"+l1+"','"+l2+"','"+city+"','"+gen+"','"+age+"','"+dep+"')";
                    pst = con.prepareStatement(ep);
                    pst.execute();

                    tableload();
                    lastid();
                    JOptionPane.showMessageDialog(this," Successfuly Added  !");

                     b1.setText(null);
                     b2.setText(null);
                     b3.setText(null);
                     b4.setText(null);
                     b5.setDate(null);
                     b6.setText(null);
                     b7.setText(null);
                     b8.setText(null);
                     b9.setText(null);
                     b10.setSelectedIndex(0);
                     b11.setText(null);
                     b12.setSelectedIndex(0);

                    }
                    catch(Exception e)
                        {
                         JOptionPane.showMessageDialog(this," Unsuccessfull Add  !");
                         System.out.println(e);
                        }
        
                } 
                else 
                {
                    JOptionPane.showMessageDialog(this," Nic Number Invalide  !");
                }
            } 
            else 
            {
                 JOptionPane.showMessageDialog(this," Phone Number Invalide  !");
            }
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton5MouseClicked

        int x = JOptionPane.showConfirmDialog(null," Are You Sure  ?");
       
        String test = b4.getText();
        String ni = b6.getText();
        
        if(validatePhone(test))
        {
                if(validateNIC(ni))
                {
                    if(x==0)
                    {
                        String eid   = b1.getText();
                        String fname = b2.getText();
                        String lname = b3.getText();
                        String tel   = b4.getText();
                        String birth   =((JTextField)b5.getDateEditor().getUiComponent()).getText();
                        String nic   = b6.getText();
                        String l1    = b7.getText();
                        String l2    = b8.getText();
                        String city  = b9.getText();
                        String gen   = (String) b10.getSelectedItem();
                        String age   = b11.getText();
                        String dep   = (String) b12.getSelectedItem();

            try
            {
                String sq = "UPDATE my1 SET FirstName = '"+fname+"',LastName= '"+lname+"',TelNo= '"+tel+"',"
                + "Dateofbirth= '"+birth+"',Nicnumber= '"+nic+"',l1= '"+l1+"',l2= '"+l2+"',City= '"+city+"',"
                + "Gender= '"+gen+"',Age= '"+age+"',dep='"+dep+"' WHERE EmpId = '"+eid+"'";

                pst = con.prepareStatement(sq);
                pst.execute();

                tableload();
                JOptionPane.showMessageDialog(this," Update Successfull  !");
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this," Update Unsuccessfull  !");
                System.out.println(e);
            }
            
            b1.setText(null);
            b2.setText(null);
            b3.setText(null);
            b4.setText(null);
            b5.setDate(null);
            b6.setText(null);
            b7.setText(null);
            b8.setText(null);
            b9.setText(null);
            b10.setSelectedIndex(0);
            b11.setText(null);
            b12.setSelectedIndex(0);
            }
            } 
                else 
                {
                 JOptionPane.showMessageDialog(this," Nic Number Invalide  !");
                }
              } 
              else 
             {
                 JOptionPane.showMessageDialog(this," Phone Number Invalide  !");
             }

        

    }//GEN-LAST:event_jButton5MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        int y = JOptionPane.showConfirmDialog(null," Are You Sure  ?");

        if(y==0)
        {
            String eid = b1.getText();
            try
            {
                String sql ="DELETE from my1 where EmpId = '"+eid+"'";
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload();
                JOptionPane.showMessageDialog(this," Delete Successfull  !");
                lastid();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this," Delete Unsuccessfull  !");
                System.out.println(e);
            }
        }

        b1.setText(null);
        b2.setText(null);
        b3.setText(null);
        b4.setText(null);
        b5.setDate(null);
        b6.setText(null);
        b7.setText(null);
        b8.setText(null);
        b9.setText(null);
        b10.setSelectedIndex(0);
        b11.setText(null);
        b12.setSelectedIndex(0);

    }//GEN-LAST:event_jButton6MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MouseClicked

        b1.setText(null);
        b2.setText(null);
        b3.setText(null);
        b4.setText(null);
        b5.setDate(null);
        b6.setText(null);
        b7.setText(null);
        b8.setText(null);
        b9.setText(null);
        b10.setSelectedIndex(0);
        b11.setText(null);
        b12.setSelectedIndex(0);

    }//GEN-LAST:event_jButton7MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b6ActionPerformed

    private void b10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b10ActionPerformed

    private void b4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_b4KeyTyped
       
        
    }//GEN-LAST:event_b4KeyTyped

    private void b12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_b12ActionPerformed

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
            java.util.logging.Logger.getLogger(emp_emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(emp_emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(emp_emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(emp_emp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new emp_emp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField b1;
    private javax.swing.JComboBox<String> b10;
    private javax.swing.JTextField b11;
    private javax.swing.JComboBox<String> b12;
    private javax.swing.JTextField b13;
    private javax.swing.JTextField b2;
    private javax.swing.JTextField b3;
    private javax.swing.JTextField b4;
    private com.toedter.calendar.JDateChooser b5;
    private javax.swing.JTextField b6;
    private javax.swing.JTextField b7;
    private javax.swing.JTextField b8;
    private javax.swing.JTextField b9;
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lid;
    private javax.swing.JTable table;
    private javax.swing.JLabel time;
    private javax.swing.JLabel tx;
    // End of variables declaration//GEN-END:variables
}
