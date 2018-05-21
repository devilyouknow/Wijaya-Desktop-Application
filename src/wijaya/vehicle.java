/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import com.sun.glass.events.KeyEvent;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import wijaya.DB;
import wijaya.Home;
import wijaya.main2;
import static wijaya.repair_mainitain.validateDouble;

/**
 *
 * @author salit
 */
//Strar Connection
public class vehicle extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst;
    DB db = new DB();

    /**
     * Creates new form Home
     */
    public vehicle() {
        this.pst = null;
        initComponents();

        {   
            jPanel2.setBackground(new Color(255, 255, 255, 100));
            jPanelOwner.setBackground(new Color(0, 0, 0, 0));
            jPanelOwner.setVisible(false);
                        }
        {
            showdate();
            showtime();
            LoadTable();
            }
    }

    public static boolean validateDouble(String testString) {
        boolean re = true;

        testString = testString.trim();

        String regexDecimal = "^-?\\d*\\.\\d+$";
        String regexInteger = "^-?\\d+$";
        String regexDouble = regexDecimal + "|" + regexInteger;

        Pattern pattern = Pattern.compile(regexDouble);
        Matcher matcher = pattern.matcher(testString);
        re = (matcher.find());

        return re;

    }

    final void showdate() {
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        date.setText(datee);

    }

    void showtime() {

        new Timer(0, (ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String timee = sdf.format(d);
            time.setText(timee);
        }).start();

    }

    /**
     * ******************** DATE AND TIME ********************************
     */
    public void datetime() {

        Thread clock = new Thread() {
            public void run() {
                for (;;) {
                    Calendar cal = new GregorianCalendar();
                    int month = cal.get(Calendar.MONTH);
                    int year = cal.get(Calendar.YEAR);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    date.setText(year + " - " + (month + 1) + " - " + day);

                    //int second = new GregorianCalendar();
                    int second = cal.get(Calendar.SECOND);
                    int minute = cal.get(Calendar.MINUTE);
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int a = cal.get(Calendar.AM_PM);

                    time.setText(hour + " : " + (minute) + " : " + second);

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

    
    //Start Table Loading//
    public void LoadTable() {

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from vehicle");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("VID"));
                v.add(rs.getString("type"));
                v.add(rs.getString("model"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("make"));
                v.add(rs.getString("year"));
                v.add(rs.getString("owner_name"));
                v.add(rs.getString("owner_nic"));
                v.add(rs.getString("address"));
                v.add(rs.getString("phone"));
                v.add(rs.getString("deposit"));
                v.add(rs.getString("rental"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Start Clear All Details(clean Button)//
    public void clearAll() {
        txtVehicleId.setText("");
        cmbType.setSelectedIndex(0);
        txtModel.setText("");
        txtMake.setText("");
        txtYear.setText("");
        ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).setText("");
        txtOwner.setText("");
        txtNIC.setText("");
        add.setText("");
        tel.setText("");
        txtDeposit.setText("");
        txtRental.setText("");
    }

   //Start Validation function//
    public boolean ValidationForSave1() {
        boolean b1 = false;
        boolean b2 = true;

        String VID, type, model, date, make, year, owner, NIC,Add,phone, rental, deposit;

        String Yearpattern = "^\\d{4}$";
        String phonepattern = "^\\d{10}$";
        String VIDpattern1 = "([A-Za-z]{2}-[\\d]{4})";
        //String VIDpattern2 = "([A-Za-z]{3}[\\d]{4})";
        String NICpattern1 = "\\d{9}[V|v|x|X]";
        String NICpattern2 = "\\d{12}";

        VID = txtVehicleId.getText();
        type = cmbType.getSelectedItem().toString();
        model = txtModel.getText();
        date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
        make = txtMake.getText();
        year = txtYear.getText();
        owner = txtOwner.getText();
        NIC = txtNIC.getText();
        Add = add.getText();
        phone = tel.getText();
        rental = txtRental.getText();
        deposit = txtDeposit.getText();

        if (VID.equals("") || model.equals("") || date.equals("") || make.equals("") || year.equals("")) {
            JOptionPane.showMessageDialog(null, "Invalid inputs!!", "", JOptionPane.ERROR_MESSAGE);
            return b1;
            }

        if (!year.matches(Yearpattern)) {
            JOptionPane.showMessageDialog(null, "Invalid year value!!", "", JOptionPane.ERROR_MESSAGE);
            return b1;
            }
        
        if (!VID.matches(VIDpattern1)) {
            JOptionPane.showMessageDialog(null, "Invalid Vehicle Number!! Ex :XX-0000", "", JOptionPane.ERROR_MESSAGE);
            return b1;
            }
        
        if (cmbType.getSelectedIndex() == 1) {
            if (owner.equals("") || NIC.equals("") || deposit.equals("") || rental.equals("")) {
                JOptionPane.showMessageDialog(null, "Invalid Inputs!!", "", JOptionPane.ERROR_MESSAGE);
                return b1;
            }
            if (!NIC.matches(NICpattern2) && !NIC.matches(NICpattern1)) {
                JOptionPane.showMessageDialog(null, "Invalid NIC Number!!", "", JOptionPane.ERROR_MESSAGE);
                return b1;
            }
            if (!phone.matches(phonepattern)) {
            JOptionPane.showMessageDialog(null, "Invalid Telephone value!!", "", JOptionPane.ERROR_MESSAGE);
            return b1;
            }
        }
        return b2;
    }

    //Set Background colour//
    void setpanel(JPanel x) {
        x.setBackground(new Color(102, 102, 102));
    }

    void resetpanell(JPanel x) {
        x.setBackground(new Color(102, 102, 102));
    }

    void setpanelkalu(JPanel x) {
        x.setBackground(new Color(51, 51, 51));
    }

    void resetpanel(JPanel x) {
        x.setBackground(new Color(51, 51, 51));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSave = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtYear = new javax.swing.JTextField();
        txtMake = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtModel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbType = new javax.swing.JComboBox<String>();
        jLabel6 = new javax.swing.JLabel();
        txtVehicleId = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanelOwner = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRental = new javax.swing.JTextField();
        txtOwner = new javax.swing.JTextField();
        txtNIC = new javax.swing.JTextField();
        txtDeposit = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        add = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        btnSave.setText("Add");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave);
        btnSave.setBounds(110, 560, 90, 33);

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        getContentPane().add(btnUpdate);
        btnUpdate.setBounds(230, 560, 120, 33);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(btnDelete);
        btnDelete.setBounds(380, 560, 93, 33);

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle/go_previous_blue.png"))); // NOI18N
        jLabel19.setText("jLabel8");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel19);
        jLabel19.setBounds(130, 120, 60, 40);

        txtYear.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtYear.setToolTipText("");
        txtYear.setMaximumSize(new java.awt.Dimension(65, 20));
        txtYear.setMinimumSize(new java.awt.Dimension(65, 20));
        txtYear.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(txtYear);
        txtYear.setBounds(120, 470, 160, 30);

        txtMake.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtMake.setMaximumSize(new java.awt.Dimension(65, 20));
        txtMake.setMinimumSize(new java.awt.Dimension(65, 20));
        txtMake.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(txtMake);
        txtMake.setBounds(120, 410, 160, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Year");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(30, 480, 90, 15);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Make");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(30, 420, 90, 15);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(30, 370, 90, 15);

        txtModel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtModel.setMaximumSize(new java.awt.Dimension(65, 20));
        txtModel.setMinimumSize(new java.awt.Dimension(65, 20));
        txtModel.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(txtModel);
        txtModel.setBounds(120, 310, 160, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Model");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(30, 320, 90, 15);

        cmbType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "company vehicle", "rented vehicle" }));
        cmbType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTypeActionPerformed(evt);
            }
        });
        getContentPane().add(cmbType);
        cmbType.setBounds(120, 260, 160, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Vehicle ID");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(30, 210, 120, 30);

        txtVehicleId.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtVehicleId.setMaximumSize(new java.awt.Dimension(65, 20));
        txtVehicleId.setMinimumSize(new java.awt.Dimension(65, 20));
        txtVehicleId.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(txtVehicleId);
        txtVehicleId.setBounds(120, 210, 160, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Type");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 270, 90, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 120, 50, 40);

        jDateChooser1.setDateFormatString("yyyy-MMM-dd");
        getContentPane().add(jDateChooser1);
        jDateChooser1.setBounds(120, 360, 160, 30);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("VEHICLE REGISTRATION");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, 270, 30));

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 70, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 120, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 90, 120, 30));

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSearch.setMaximumSize(new java.awt.Dimension(65, 20));
        txtSearch.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSearch.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 160, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "VehicleID", "type", "model", "date", "make", "year", "owner_name", "owner_nic", "Address", "Telephone", "Deposit", "Rental"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true, true
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
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 780, 190));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(560, 160, 790, 350);

        btnNew.setText("Clean");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew);
        btnNew.setBounds(290, 210, 70, 30);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 290, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 70, 80));

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
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 20, 30, 30));

        jLabel17.setBackground(new java.awt.Color(51, 51, 51));
        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("LogOut");
        jLabel17.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1360, 101);

        jPanelOwner.setBackground(new java.awt.Color(0, 0, 0));
        jPanelOwner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Rental charge");
        jPanelOwner.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Owner Name");
        jPanelOwner.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("NIC Number");
        jPanelOwner.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 80, -1));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Deposit Money");
        jPanelOwner.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Telephone");
        jPanelOwner.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, -1, -1));

        txtRental.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtRentalKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRentalKeyTyped(evt);
            }
        });
        jPanelOwner.add(txtRental, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 140, 30));
        jPanelOwner.add(txtOwner, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 140, 30));
        jPanelOwner.add(txtNIC, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 140, 30));

        txtDeposit.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtDepositCaretUpdate(evt);
            }
        });
        txtDeposit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDepositKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDepositKeyTyped(evt);
            }
        });
        jPanelOwner.add(txtDeposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 140, 30));
        jPanelOwner.add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 140, 30));
        jPanelOwner.add(add, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, 30));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Address");
        jPanelOwner.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        getContentPane().add(jPanelOwner);
        jPanelOwner.setBounds(300, 260, 260, 250);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setMinimumSize(new java.awt.Dimension(1184, 666));
        jLabel2.setPreferredSize(new java.awt.Dimension(1184, 666));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1360, 770);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        new Home().setVisible(true);

    }//GEN-LAST:event_jLabel1MouseClicked
    private void jDateChooser1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseReleased
        //if(jLabel12.getText().after(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText())){}
    }//GEN-LAST:event_jDateChooser1MouseReleased
//Start Search//
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        if (txtSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Search value cannot be empty!!", "", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {
                ResultSet rs = DB.getData("select* from vehicle where VID like '%" + txtSearch.getText() + "%'");

                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("VID"));
                    v.add(rs.getString("type"));
                    v.add(rs.getString("model"));
                    v.add(rs.getString("Date"));
                    v.add(rs.getString("make"));
                    v.add(rs.getString("year"));
                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //View All button//
        LoadTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        boolean validationStatus = ValidationForSave1();
        if (validationStatus == true)
        {
            
            ResultSet rs = null;
        try {
                rs = DB.getData("select* from vehicle where VID = '" + txtVehicleId.getText() + "'");   
            } 
        catch (Exception e) {
                e.printStackTrace();
            }

            String VID, type, model, date, make, year, owner, NIC,address,phone, rental, deposit;

            VID = txtVehicleId.getText();
            type = cmbType.getSelectedItem().toString();
            model = txtModel.getText();
            date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
            make = txtMake.getText();
            year = txtYear.getText();
            owner = txtOwner.getText();
            NIC = txtNIC.getText();
            address = add.getText();
            phone = tel.getText();
            rental = txtRental.getText();
            deposit = txtDeposit.getText();

            try {
                String strSql = ("insert into vehicle value('" + VID + "', '" + type + "', '" + model + "' ,'" + make + "','" + year + "','" + date + "','" + owner + "','" + NIC + "','"+address+"','"+phone+"','" + deposit + "','" + rental + "')");

                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Vehicle ID: " + VID + "  This vehicle ID already exsists!");
                } else {
                    db.setData(strSql);
                    JOptionPane.showMessageDialog(this, "Vehicle ID: " + VID + "  Added Successfully");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            clearAll();
            LoadTable();

        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        boolean validationStatus = ValidationForSave1();
        if (validationStatus == true) {

            String VID, type, model, date, make, year, owner, NIC,address,phone, rental, deposit;

            VID = txtVehicleId.getText();
            type = cmbType.getSelectedItem().toString();
            model = txtModel.getText();
            date = ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).getText();
            make = txtMake.getText();
            year = txtYear.getText();
            owner = txtOwner.getText();
            NIC = txtNIC.getText();
            address=add.getText();
            phone=tel.getText();
            rental = txtRental.getText();
            deposit = txtDeposit.getText();

            try {
                String strSql = "update vehicle set type='" + type + "',model='" + model + "',date='" + date + "', make='" + make + "', year='" + year + "', owner_name='" + owner + "', owner_nic='" + NIC + "',address='"+address+"',phone='"+phone+"', deposit='" + deposit + "', rental='" + rental + "' where VID='" + VID + "'";
                db.setData(strSql);
                JOptionPane.showMessageDialog(this, "Vehicle " + VID + "  updated Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            LoadTable();
        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int row = this.jTable1.getSelectedRow();
        this.txtVehicleId.setText(dtm.getValueAt(row, 0).toString());
        this.cmbType.setSelectedItem(dtm.getValueAt(row, 1).toString());
        this.txtModel.setText(dtm.getValueAt(row, 2).toString());
        ((JTextField) jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());
        this.txtMake.setText(dtm.getValueAt(row, 4).toString());
        this.txtYear.setText(dtm.getValueAt(row, 5).toString());

        if (dtm.getValueAt(row, 1).toString().equals("rented vehicle")) {
            try {
                ResultSet rs = DB.getData("select* from vehicle where VID like '%" + dtm.getValueAt(row, 0).toString() + "%'");
                while (rs.next()) {
                    txtOwner.setText(rs.getString("owner_name"));
                    txtNIC.setText(rs.getString("owner_nic"));
                    add.setText(rs.getString("address"));
                    tel.setText(rs.getString("phone"));
                    txtRental.setText(rs.getString("deposit"));
                    txtDeposit.setText(rs.getString("rental"));
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        txtVehicleId.enable(false);
        btnSave.enable(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you want to remove this vehicle?");
        if (x == 0) {
            try {
                String strSql = "delete from vehicle where VID = '" + txtVehicleId.getText() + "'";
                DB.setData(strSql);

                JOptionPane.showMessageDialog(this, "Vehicle " + txtVehicleId.getText() + "  Deleted Successfully");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            clearAll();
            LoadTable();
            txtVehicleId.enable(true);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        //clear all details in txtfield(clean)//
        txtVehicleId.enable(true);
        btnSave.enable(true);
        clearAll();
    }//GEN-LAST:event_btnNewActionPerformed

    private void cmbTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTypeActionPerformed
        if (cmbType.getSelectedIndex() == 0) {
            jPanelOwner.setVisible(false);
        } else if (cmbType.getSelectedIndex() == 1) {
            jPanelOwner.setVisible(true);
        }
    }//GEN-LAST:event_cmbTypeActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        this.dispose();
        new main2().setVisible(true);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void txtDepositKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyPressed
 
    }//GEN-LAST:event_txtDepositKeyPressed

    private void txtRentalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRentalKeyPressed
    }//GEN-LAST:event_txtRentalKeyPressed

    private void txtDepositCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtDepositCaretUpdate
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtDepositCaretUpdate

    private void txtDepositKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDepositKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACKSPACE)
               || c ==KeyEvent.VK_DELETE )){
          getToolkit().beep();
            
        evt.consume();  
        
        }
    }//GEN-LAST:event_txtDepositKeyTyped

    private void txtRentalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRentalKeyTyped
        char c=evt.getKeyChar();
        if(!(Character.isDigit(c)|| (c==KeyEvent.VK_BACKSPACE)
               || c ==KeyEvent.VK_DELETE )){
          getToolkit().beep();
            
        evt.consume();  
        
        }
    }//GEN-LAST:event_txtRentalKeyTyped

   
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
            java.util.logging.Logger.getLogger(Home.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        

} catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class
.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JTextField add;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cmbType;
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JPanel jPanelOwner;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField tel;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txtDeposit;
    private javax.swing.JTextField txtMake;
    private javax.swing.JTextField txtModel;
    private javax.swing.JTextField txtNIC;
    private javax.swing.JTextField txtOwner;
    private javax.swing.JTextField txtRental;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JTextField txtVehicleId;
    private javax.swing.JTextField txtYear;
    // End of variables declaration//GEN-END:variables

    
}
