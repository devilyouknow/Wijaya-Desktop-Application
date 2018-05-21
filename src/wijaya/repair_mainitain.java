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
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

/**
 *
 * @author salit
 */
public class repair_mainitain extends javax.swing.JFrame {

    Connection con = null;
    PreparedStatement pst;
    DB db = new DB();

    /**
     * Creates new form Home
     */
    public repair_mainitain() throws Exception {
        this.pst = null;
        initComponents();

        {
            //making panel transparent
            //jPanel1.setBackground(new Color(0,0,0,50));
            jPanel2.setBackground(new Color(255, 255, 255, 100));
            jPanelOwner1.setBackground(new Color(0, 0, 0, 0));
            con = db.getConnection();
            jPanelOwner1.setVisible(false);
        }

        {
            
            showtime();
            showdate();
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

    public void LoadTable() {

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from repair_and_maintainance ");

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("VID"));
                v.add(rs.getString("Type"));
                v.add(rs.getString("Repair_Type"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("AMount"));
                v.add(rs.getString("KM"));
                v.add(rs.getString("KMprice"));
                v.add(rs.getString("Total"));

                dtm.addRow(v);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearAll() {
        jComboBox1.setSelectedIndex(-1);
        type.setSelectedIndex(0);
        rtype.setSelectedIndex(0);
        ((JTextField) datee.getDateEditor().getUiComponent()).setText("");
        amount1.setText("");
        km1.setText("");
        price.setText("");
        Fuelex.setText("");

    }

    public boolean ValidationForSave() throws Exception {

        boolean b1 = true;

        String VID, Type, Repair_Type, Date, Amount, KM, KMprice, Total;

        String VIDpattern1 = "([A-Za-z]{2}-[\\d]{4})";
        String VIDpattern2 = "([A-Za-z]{3}[\\d]{4})";

        ResultSet rs = null;
        VID = jComboBox1.getSelectedItem().toString();
        Type = type.getSelectedItem().toString();
        Repair_Type = rtype.getSelectedItem().toString();
        Date = ((JTextField) datee.getDateEditor().getUiComponent()).getText();
        Amount = amount1.getText();
        KM = price.getText();
        KMprice = price.getText();
        Total = Fuelex.getText();
        
            if(type.getSelectedIndex()== 0){
            
              if (VID.equals("") || Date.equals("") || Amount.equals("") || Repair_Type.equals("")||!validateDouble(Amount)) {
            JOptionPane.showMessageDialog(null, "Inputs are invalid!!", "", JOptionPane.ERROR_MESSAGE);
            b1 = false;   
            } 
              else {
              }
              
              
        }
       
        
        if (!VID.matches(VIDpattern2) && !VID.matches(VIDpattern1)) {
            JOptionPane.showMessageDialog(null, "Invalid Vehicle Number!! Ex :XX-0000 or XXX0000", "", JOptionPane.ERROR_MESSAGE);
            b1 = false;
        }
        if (type.getSelectedIndex() == 1) {
            try {
                if (KM.equals("") || KMprice.equals("") || Total.equals("")) {
                    JOptionPane.showMessageDialog(null, "All the fields should have values!!", "", JOptionPane.ERROR_MESSAGE);
                    b1 = false;
                } else {

                }

            } catch (Exception ex) {
                Logger.getLogger(repair_mainitain.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
        return b1;
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
     * ******************** SET BACKGROUND COLOR ***********************
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<String>();
        jPanel4 = new javax.swing.JPanel();
        datee = new com.toedter.calendar.JDateChooser();
        amount1 = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        rtype = new javax.swing.JComboBox<String>();
        jLabel24 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        type = new javax.swing.JComboBox<String>();
        jLabel19 = new javax.swing.JLabel();
        jPanelOwner1 = new javax.swing.JPanel();
        price = new javax.swing.JTextField();
        Fuelex = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        km1 = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        time1 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        date1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtSearch = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(300, 230, 160, 30);

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        datee.setDateFormatString("yyyy-MM-dd");
        jPanel4.add(datee, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 50, 160, 30));
        jPanel4.add(amount1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 160, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("Amount");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, -1, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Date");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        rtype.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "vehicle Service", "Engine Overhaul", "Gearbox Repair", "Time belt Replacing", "Clutch kit Replace", "Suspension repair", "Shock repair", "Others" }));
        rtype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rtypeActionPerformed(evt);
            }
        });
        jPanel4.add(rtype, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 160, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Repair Type");
        jPanel4.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        getContentPane().add(jPanel4);
        jPanel4.setBounds(150, 320, 320, 140);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(180, 650, 90, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(310, 650, 110, 33);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(450, 650, 93, 33);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vehicle/go_previous_blue.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8);
        jLabel8.setBounds(160, 120, 60, 40);

        btnNew.setText("Clear");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        getContentPane().add(btnNew);
        btnNew.setBounds(500, 230, 80, 30);

        type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Repair Vehicle", "maintain fuel", " " }));
        type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeActionPerformed(evt);
            }
        });
        getContentPane().add(type);
        type.setBounds(300, 280, 160, 30);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Fuel Total Price");
        getContentPane().add(jLabel19);
        jLabel19.setBounds(0, 0, 92, 15);

        jPanelOwner1.setBackground(new java.awt.Color(0, 0, 0));
        jPanelOwner1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        price.setForeground(new java.awt.Color(204, 0, 0));
        price.setToolTipText("");
        price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceActionPerformed(evt);
            }
        });
        price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                priceKeyPressed(evt);
            }
        });
        jPanelOwner1.add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 10, 80, 30));

        Fuelex.setForeground(new java.awt.Color(204, 0, 0));
        Fuelex.setToolTipText("");
        Fuelex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FuelexActionPerformed(evt);
            }
        });
        jPanelOwner1.add(Fuelex, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 130, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Fuel Price for a Litre");
        jPanelOwner1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 130, -1));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Total Fuel Price");
        jPanelOwner1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 100, -1));

        km1.setForeground(new java.awt.Color(204, 0, 0));
        km1.setToolTipText("");
        km1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                km1ActionPerformed(evt);
            }
        });
        km1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                km1KeyPressed(evt);
            }
        });
        jPanelOwner1.add(km1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, 130, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("How many Kilometre");
        jPanelOwner1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 140, -1));

        getContentPane().add(jPanelOwner1);
        jPanelOwner1.setBounds(150, 470, 480, 100);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Type");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(160, 290, 90, 15);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 250, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel23.setText("jLabel22");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 0, 70, 80));

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
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1320, 10, 30, 30));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1360, 101);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Vehicle ID");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(160, 240, 90, 15);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(90, 110, 50, 50);

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time1.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time1.setForeground(new java.awt.Color(255, 255, 255));
        time1.setText("Time");
        jPanel3.add(time1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 219, 60));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 40, 60));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date1.setForeground(new java.awt.Color(255, 255, 255));
        date1.setText("date");
        jPanel3.add(date1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 150, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel25.setText("jLabel22");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 0, 70, 80));

        jLabel31.setBackground(new java.awt.Color(51, 51, 51));
        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cancel.png"))); // NOI18N
        jLabel31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel31MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 10, 30, 30));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("LogOut");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 80, 60, -1));

        getContentPane().add(jPanel3);
        jPanel3.setBounds(0, 0, 1170, 101);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "VehicleID", "Type", "Repair Type", "Date", "Amount", "Kilomtre", "Fuel Price", "Total Price"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 630, 110));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("REPAIR MAINTAINCE");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, 160, 30));

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 70, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 60, 110, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 110, 30));

        txtSearch.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtSearch.setMaximumSize(new java.awt.Dimension(65, 20));
        txtSearch.setMinimumSize(new java.awt.Dimension(65, 20));
        txtSearch.setPreferredSize(new java.awt.Dimension(65, 20));
        jPanel2.add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 160, 30));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(640, 190, 680, 380);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Fuel Total Price");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(0, 0, 92, 15);

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
        //  if(jLabel12.getText().after(((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText())){}
    }//GEN-LAST:event_jDateChooser1MouseReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (txtSearch.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Search value cannot be empty!!", "", JOptionPane.ERROR_MESSAGE);
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            try {
                ResultSet rs = DB.getData("select* from repair_and_maintainance where VID like '%" + txtSearch.getText() + "%'");

                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("VID"));
                    v.add(rs.getString("Type"));
                    v.add(rs.getString("Repair_Type"));
                    v.add(rs.getString("Date"));
                    v.add(rs.getString("Amount"));
                    v.add(rs.getString("KM"));
                    v.add(rs.getString("KMprice"));
                    v.add(rs.getString("Total"));
                    dtm.addRow(v);
                }

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        LoadTable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while (dtm.getRowCount() > 0) {
            dtm.removeRow(0);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        double Amount = 0;
        try {
            
            boolean validationStatus = ValidationForSave();

            if (validationStatus == true) {

                String VID, Type, Repair_Type = "", Date = "",KM1= null,KMprice1 = null, Total1 = null;
                

                VID = jComboBox1.getSelectedItem().toString();
                Type = type.getSelectedItem().toString();
                                
            if(Type == "Repair Vehicle"){
                Repair_Type = rtype.getSelectedItem().toString();
                Date = ((JTextField) datee.getDateEditor().getUiComponent()).getText();
                Amount = Double.parseDouble(amount1.getText());
                                        }
            if(Type == "maintain fuel"){
                KM1 = km1.getText();
                KMprice1 = price.getText();
                Total1 = Fuelex.getText();
                                        }
        try {
                String strSql = ("insert into repair_and_maintainance  values('" + VID + "', '" + Type + "', '" + Repair_Type + "' ,'" + Date + "'," +Amount+ ",'" + KM1 + "','" + KMprice1 + "','" + Total1 + "')");
                System.out.println(strSql);
                JOptionPane.showMessageDialog(this, "Vehicle ID: " + VID + "  Added Successfully");
                db.setData(strSql);    
            } 
        catch (Exception ex ) {
                    ex.printStackTrace();
                              }
                clearAll();
                LoadTable();
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
                             }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String VID, Type, Repair_Type, Date, KM, KMprice, Total;

        VID = jComboBox1.getSelectedItem().toString();
        Type = type.getSelectedItem().toString();
        Repair_Type = rtype.getSelectedItem().toString();
        Date = ((JTextField) datee.getDateEditor().getUiComponent()).getText();
        double Amount = Double.parseDouble(amount1.getText());
        KM = km1.getText();
        KMprice = price.getText();
        Total = Fuelex.getText();

        boolean validationStatus=false;
      try {
            validationStatus = ValidationForSave();
           } 
      catch (Exception ex) {
            Logger.getLogger(repair_mainitain.class.getName()).log(Level.SEVERE, null, ex);
                           }

        if (validationStatus == true) {
            try {
                String strSql = "update repair_and_maintainance  set Type='" + Type + "',Repair_Type='" + Repair_Type + "',Date='" + Date + "', Amount='" + Amount + "', KM='" + KM + "', KMprice='" + KMprice + "', Total='" + Total + "' where VID='" + VID + "'";
                db.setData(strSql);
                JOptionPane.showMessageDialog(this, "Vehicle " + VID + "  updated Successfully");
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
   try {
                String strSql = "delete from repair_and_maintainance  where VID = '" + jComboBox1.getSelectedItem().toString() + "'";
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
        this.type.setSelectedItem(dtm.getValueAt(row, 1).toString());
        this.rtype.setSelectedItem(dtm.getValueAt(row, 2).toString());
        ((JTextField) datee.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());
        this.amount1.setText(dtm.getValueAt(row, 4).toString());
        this.km1.setText(dtm.getValueAt(row, 5).toString());
        this.price.setText(dtm.getValueAt(row, 6).toString());
        this.Fuelex.setText(dtm.getValueAt(row, 7).toString());

        jComboBox1.enable(false);
        jButton1.enable(false);
    }//GEN-LAST:event_jTable1MouseClicked

    private void FuelexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FuelexActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FuelexActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        jComboBox1.enable(true);
        jButton1.enable(true);
        clearAll();
    }//GEN-LAST:event_btnNewActionPerformed

    private void typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeActionPerformed
        if(type.getSelectedIndex()==0){
            jPanel4.setVisible(true);
            jPanelOwner1.setVisible(false);     
        } 
        else if(type.getSelectedIndex()==1){
            jPanelOwner1.setVisible(true);
             jPanel4.setVisible(false); 
        }
    }//GEN-LAST:event_typeActionPerformed

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jLabel31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel31MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel31MouseClicked

    private void priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceActionPerformed

    private void km1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_km1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_km1ActionPerformed

    private void km1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_km1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_km1KeyPressed

    private void priceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceKeyPressed
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            double b1 = Double.parseDouble(km1.getText());
            double b2 = Double.parseDouble(price.getText());
            double res = (b1 * b2);
            String result = String.format("%.2f", res);
            Fuelex.setText(result);
            
        }
    }//GEN-LAST:event_priceKeyPressed

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        this.dispose();
        new main2().setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

    private void rtypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rtypeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rtypeActionPerformed

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
    private javax.swing.JTextField Fuelex;
    private javax.swing.JTextField amount1;
    private javax.swing.JButton btnNew;
    private javax.swing.JLabel date;
    private javax.swing.JLabel date1;
    private com.toedter.calendar.JDateChooser datee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanelOwner1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField km1;
    private javax.swing.JTextField price;
    private javax.swing.JComboBox<String> rtype;
    private javax.swing.JLabel time;
    private javax.swing.JLabel time1;
    private javax.swing.JTextField txtSearch;
    private javax.swing.JComboBox<String> type;
    // End of variables declaration//GEN-END:variables
}
