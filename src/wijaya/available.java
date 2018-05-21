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
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import wijaya.DB;
import wijaya.Home;
import wijaya.main2;


//start connection// 
public class available extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst;
    DB db = new DB();

    public available() throws Exception {
            initComponents();
        {
            jPanel2.setBackground(new Color(255, 255, 255, 100));
            jPanelOwner2.setBackground(new Color(0, 0, 0, 0));
            con = db.getConnection();
            jPanelOwner2.setVisible(false);
            jPanelOwner1.setBackground(new Color(0, 0, 0, 0));
            con = db.getConnection();
            jPanelOwner1.setVisible(false);
        }
        {
            
            showdate();
            showtime();
            LoadTable();
            fillcombo();
        }
    }

void fillcombo() {
        jComboBox1.removeAllItems();
        try {
            ResultSet rs = DB.getData("SELECT VID FROM vehicle");
            while (rs.next()) {
        jComboBox1.addItem(rs.getString("VID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
                        Logger.getLogger(main2.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
        };
        clock.start();
    }

        public void LoadTable() {

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from available");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("VID"));
                v.add(rs.getString("type"));
                v.add(rs.getString("Sdel"));
                v.add(rs.getString("Edel"));
                v.add(rs.getString("Srepair"));
                v.add(rs.getString("Erepair"));
                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        jComboBox1.setSelectedIndex(0);
        txtcombo.setSelectedIndex(0);
        sdel.setText("");
        edel.setText("");
        jPanelOwner1.setVisible(false);
        ((JTextField) cal1.getDateEditor().getUiComponent()).setText("");
        ((JTextField) cal2.getDateEditor().getUiComponent()).setText("");

    }

    public boolean ValidationForSave() {
        String VID, type, Sdel, Edel, Srepair, Erepair;

        String VIDpattern1 = "([A-Za-z]{2}-[\\d]{4})";
        String VIDpattern2 = "([A-Za-z]{3}[\\d]{4})";

        VID = jComboBox1.getSelectedItem().toString();
        type = txtcombo.getSelectedItem().toString();
        Srepair = ((JTextField) cal1.getDateEditor().getUiComponent()).getText();
        Erepair = ((JTextField) cal2.getDateEditor().getUiComponent()).getText();

        if (VID.equals("") || type.equals("")) {
            JOptionPane.showMessageDialog(null, "All the fields should have values!!", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!VID.matches(VIDpattern2) && !VID.matches(VIDpattern1)) {
            JOptionPane.showMessageDialog(null, "Invalid Vehicle Number!! Ex :XX-0000 or XXX0000", "", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (txtcombo.getSelectedIndex() == 2) {
            if (Srepair.equals("") || Erepair.equals("")) {
                JOptionPane.showMessageDialog(null, "All the Repair fields should have values!!", "", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
 
        return true;
    }
    /**
     * ******************** DATE AND TIME ********************************
     */
    /**
     * ******************** SET BACKGROUND COLOR ***********************
     */
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanelOwner2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        edel = new javax.swing.JTextField();
        sdel = new javax.swing.JTextField();
        txtcombo = new javax.swing.JComboBox<>();
        jPanelOwner1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        cal1 = new com.toedter.calendar.JDateChooser();
        cal2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(140, 550, 80, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(260, 550, 100, 33);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(410, 550, 97, 33);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle/go_previous_blue.png"))); // NOI18N
        jLabel9.setText("jLabel8");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9);
        jLabel9.setBounds(140, 140, 60, 40);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(660, 212, 170, 0);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(250, 220, 160, 30);

        jPanelOwner2.setBackground(new java.awt.Color(0, 0, 0));
        jPanelOwner2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Start Delivery Time");
        jPanelOwner2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("End Delivery Time");
        jPanelOwner2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        edel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        edel.setMaximumSize(new java.awt.Dimension(65, 20));
        edel.setMinimumSize(new java.awt.Dimension(65, 20));
        edel.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanelOwner2.add(edel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 160, 30));

        sdel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        sdel.setMaximumSize(new java.awt.Dimension(65, 20));
        sdel.setMinimumSize(new java.awt.Dimension(65, 20));
        sdel.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanelOwner2.add(sdel, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 160, 30));

        getContentPane().add(jPanelOwner2);
        jPanelOwner2.setBounds(90, 310, 330, 100);

        txtcombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Available Vehicle", "Already Send Vehicle", "Repair Vehicle" }));
        txtcombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcomboActionPerformed(evt);
            }
        });
        getContentPane().add(txtcombo);
        txtcombo.setBounds(250, 270, 160, 30);

        jPanelOwner1.setBackground(new java.awt.Color(0, 0, 0));
        jPanelOwner1.setForeground(new java.awt.Color(255, 255, 255));
        jPanelOwner1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Start Date Repair ");
        jPanelOwner1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("End Date Repair");
        jPanelOwner1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        cal1.setDateFormatString("yyyy- MM- dd ");
        jPanelOwner1.add(cal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        cal2.setDateFormatString("yyyy-MM-dd");
        jPanelOwner1.add(cal2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 160, 30));

        getContentPane().add(jPanelOwner1);
        jPanelOwner1.setBounds(90, 420, 340, 100);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Vehicle Id");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(100, 220, 120, 30);

        jButton7.setText("Clear");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7);
        jButton7.setBounds(430, 220, 70, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Type");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(100, 270, 110, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(80, 130, 50, 50);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 300, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 20, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 30, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1270, 0, 70, 80));

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
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 50, 30));

        jLabel23.setBackground(new java.awt.Color(51, 51, 51));
        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("LogOut");
        jLabel23.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(10, 0, 1440, 101);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("AVAILABLE VEHICLE");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, -1, -1));

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, 70, 30));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "VehicleID", "Type", "Start Deliver Time", "End Deliver Time", "Start Repair Date", "End Repair Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
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
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 650, 190));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 100, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, 100, 30));

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSearch.setMaximumSize(new java.awt.Dimension(65, 20));
        txtSearch.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSearch.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 160, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(580, 200, 710, 390);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(1360, 768));
        jLabel2.setMinimumSize(new java.awt.Dimension(1360, 768));
        jLabel2.setPreferredSize(new java.awt.Dimension(1360, 768));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1510, 800);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        this.dispose();
        new Home().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jDateChooser1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jDateChooser1MouseReleased
        //  if(jLabel12.getText().after(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText())){}
    }//GEN-LAST:event_jDateChooser1MouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (txtSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Search value cannot be empty!!", "", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {
                ResultSet rs = DB.getData("select * from available where VID like '" + txtSearch.getText() + "'");

                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("VID"));
                    v.add(rs.getString("type"));
                    v.add(rs.getString("Sdel"));
                    v.add(rs.getString("Edel"));
                    v.add(rs.getString("Srepair"));
                    v.add(rs.getString("Erepair"));
                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //view all button//
        LoadTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ResultSet rs = null;
        int Sdel = 0;
        int Edel = 0;

        try {
            rs = db.getData("select * from available where VID = '" + jComboBox1.getSelectedItem().toString() + "'");
                if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Vehicle ID " + jComboBox1.getSelectedItem().toString() + " alredy exists. You cannot insert!!", "", JOptionPane.ERROR_MESSAGE);
                            } 
                else 
            {
                boolean validationStatus = ValidationForSave();
                if (validationStatus == true) {
                    
                    String VID = "", type = "", Srepair = "", Erepair="";

                    VID = (String) jComboBox1.getSelectedItem();
                    type = (String) txtcombo.getSelectedItem();

                if (type == "Already Send Vehicle") {
                        Sdel = Integer.parseInt(sdel.getText());
                        Edel = Integer.parseInt(edel.getText());
                                                        }
                if (type == "Repair Vehicle") {
                        Srepair = ((JTextField) cal1.getDateEditor().getUiComponent()).getText();
                        Erepair = ((JTextField) cal2.getDateEditor().getUiComponent()).getText();
                                                  }
        try {
                String strSql = ("insert into available value('" + VID + "', '" + type + "', " + Sdel + " ," + Edel + ",'" + Srepair + "','" + Erepair + "')");
                if (Sdel <= 24 && Edel <= 24) {
                            db.setData(strSql);
                            JOptionPane.showMessageDialog(this, "Vehicle ID: " + VID + "  Added Successfully");
                        } 
                else {
                            JOptionPane.showMessageDialog(null, "Invalid time format");
                        }
            } 
        catch (Exception ex) {
                        ex.printStackTrace();
                             }
                    LoadTable();
                    clearAll();
                 }
               }
            } 
        catch (Exception e) {
            e.printStackTrace();
                            }
    }//GEN-LAST:event_jButton1ActionPerformed
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean validationStatus = ValidationForSave();
        if (validationStatus == true) {
            
        String VID = "", type = "", Srepair=null, Erepair=null;

        VID = jComboBox1.getSelectedItem().toString();
        type = txtcombo.getSelectedItem().toString();
        int Sdel = Integer.parseInt(edel.getText());
        int Edel = Integer.parseInt(sdel.getText());
        Srepair = ((JTextField) cal1.getDateEditor().getUiComponent()).getText();
        Erepair = ((JTextField) cal2.getDateEditor().getUiComponent()).getText();
        
    try {
            String strSql = "update available set type='" + type + "',Sdel='"+ Sdel+"',Edel='" + Edel + "', Srepair='" + Srepair + "' ,Erepair ='"+ Erepair+ "' where VID='" + VID + "' ";
           
            if (Sdel <= 24 && Edel <= 24) {
                    db.setData(strSql);
                    JOptionPane.showMessageDialog(this, "Vehicle " + VID + "  updated Successfully");
                } 
            else {
                    JOptionPane.showMessageDialog(null, "Invalid time format");
                        }
        } 
    catch (Exception ex) {
                ex.printStackTrace();
                         }
            LoadTable();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        int x = JOptionPane.showConfirmDialog(null, "Do you want to remove this vehicle?");
        if (x == 0) {
    try     {
                String strSql = "delete from available where VID = '" + jComboBox1.getSelectedItem().toString() + "'";
                DB.setData(strSql);

                JOptionPane.showMessageDialog(this, "Vehicle " + jComboBox1.getSelectedItem().toString() + "  Deleted Successfully");
            } 
    catch (Exception ex) {
                ex.printStackTrace();
                         }
            clearAll();
            LoadTable();
            jComboBox1.enable(true);
                    }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int row = this.jTable1.getSelectedRow();
        this.jComboBox1.setSelectedItem(dtm.getValueAt(row, 0).toString());
        this.txtcombo.setSelectedItem(dtm.getValueAt(row, 1).toString());
        ((JTextField)cal1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 4).toString());
        ((JTextField)cal2.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 5).toString());

        if (dtm.getValueAt(row, 1).toString().equals("Already Send Vehicle")) {
            try {
                ResultSet rs = DB.getData("select* from available where VID = '" + dtm.getValueAt(row, 0).toString() + "' order by VID desc");
                while (rs.next()) {
                    sdel.setText(rs.getString("Sdel"));
                    edel.setText(rs.getString("Edel"));

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        jComboBox1.enable(false);
        jButton1.enable(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void txtcomboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcomboActionPerformed

        if (txtcombo.getSelectedIndex() == 0) {
            jPanelOwner2.setVisible(false);
            jPanelOwner1.setVisible(false);
        } else if (txtcombo.getSelectedIndex() == 1) {
            jPanelOwner2.setVisible(true);
            jPanelOwner1.setVisible(false);
        } else if (txtcombo.getSelectedIndex() == 2) {
            jPanelOwner1.setVisible(true);
            jPanelOwner2.setVisible(false);
        }

    }//GEN-LAST:event_txtcomboActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        jComboBox1.enable(true);
        jButton1.enable(true);
        clearAll();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        this.dispose();
        new main2().setVisible(true);
    }//GEN-LAST:event_jLabel9MouseClicked


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
    private com.toedter.calendar.JDateChooser cal1;
    private com.toedter.calendar.JDateChooser cal2;
    private javax.swing.JLabel date;
    private javax.swing.JTextField edel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelOwner1;
    private javax.swing.JPanel jPanelOwner2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField sdel;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JComboBox<String> txtcombo;
    // End of variables declaration//GEN-END:variables
}
