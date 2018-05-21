/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.Segment;
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
public class c_Appointments extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public c_Appointments() {
        initComponents();
        
        
        jComboBox1.removeAllItems();
        jComboBox2.removeAllItems();
        
        jPanel2.setBackground(new Color(0,0,0,50));
        
        
        
        
        {
            //implementing date and time methods
            showdate();
            showtime();
            
             lastId();
             fill1();
             fill2();
             appointments();
             loadtable();
             AutoCompleteDecorator.decorate(jComboBox1);
              AutoCompleteDecorator.decorate(jComboBox2);
          
        }
        
        
    }
    
    void loadtable(){
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from appointments order by date");

            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("AID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("EID"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("From1"));
                v.add(rs.getString("To1"));

                dtm.addRow(v);

                System.out.println("inside while");

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        appointments();
    
    
    }
    
    void fill1(){
        
        try {
            ResultSet rs = DB.getData("SELECT CID FROM customers");
            while(rs.next()){
            
                jComboBox1.addItem(rs.getString("CID"));
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    void fill2(){
        
         try {
            ResultSet rs = DB.getData("SELECT EmpId FROM my1");
            while(rs.next()){
            
                jComboBox2.addItem(rs.getString("EmpId"));
            
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    
    }
    
    
    
    //checking appointments
    void appointments(){
         
        try{ 
            
            boolean time1 =false;
            
             Date d =new Date();
    SimpleDateFormat adf = new SimpleDateFormat("HH.mm");
    SimpleDateFormat adf1 = new SimpleDateFormat("yyyy.MM.dd");
    String cT = adf.format(d);
    String cD = adf1.format(d);
    
    double currentTime = Double.parseDouble(cT);
    
            System.out.println(currentTime);
            System.out.println(cD);
            
            
      ResultSet rs = DB.getData("SELECT AID,from1,date FROM appointments where date= '"+cD+"'");
      
      while(rs.next()){
       double Atime =Double.parseDouble(rs.getString("from1"));
       String Adate =(rs.getString("date"));
       String AID =(rs.getString("AID"));
       
       if((Atime-currentTime)<=2){
       time1 =true;
       }else
           time1 =false;
       
       if(time1&&Adate.equals(cD)){
           JOptionPane.showMessageDialog(this, "Appointment : "+AID+"  is in less than 2hrs");
       
       }
       
       
       
      
      }
            
      
       } catch (Exception e) {
            System.out.println("error in appointments");
            System.out.println(e);
    }      
            
        
        
        
        
        
        
        
       
    
    }
    
    
    
    
    //check last id method
     void lastId(){
    
     try {
            ResultSet rs = DB.getData("SELECT AID FROM appointments ORDER BY AID DESC LIMIT 1");
            
            while (rs.next()){
                jLabel18.setText(rs.getString("AID"));
                 
                 
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

        jButton9 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTextField5 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
        getContentPane().setLayout(null);

        jButton9.setBackground(new java.awt.Color(0, 204, 51));
        jButton9.setText("Genarate A Token");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton9);
        jButton9.setBounds(250, 700, 160, 25);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox2);
        jComboBox2.setBounds(240, 310, 220, 30);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(240, 260, 220, 30);

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(jLabel18);
        jLabel18.setBounds(350, 180, 110, 14);

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Last Appointment ID -");
        getContentPane().add(jLabel16);
        jLabel16.setBounds(230, 180, 110, 14);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 10, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 20, 150, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/WhatsApp Image 2018-03-06 at 5.07.21 PM.jpeg"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 490, 80));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/newicon/employee.png"))); // NOI18N
        jLabel22.setText("jLabel22");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 0, 70, 80));

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
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(1310, 10, 30, 30));

        jLabel15.setBackground(new java.awt.Color(51, 51, 51));
        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("LogOut");
        jLabel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 80, 60, -1));

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 1400, 101);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Back_50px.png"))); // NOI18N
        jLabel14.setText("Back");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel14);
        jLabel14.setBounds(150, 110, 100, 50);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("24 h Format (ex - 13:00)");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(250, 450, 140, 13);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Add_User_Male_25px.png"))); // NOI18N
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(150, 610, 90, 33);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Update_User_25px.png"))); // NOI18N
        jButton2.setText("Update");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(270, 610, 110, 33);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Delete_25px.png"))); // NOI18N
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(400, 610, 97, 33);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(660, 212, 170, 0);

        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField5.setMaximumSize(new java.awt.Dimension(65, 20));
        jTextField5.setMinimumSize(new java.awt.Dimension(65, 20));
        jTextField5.setPreferredSize(new java.awt.Dimension(65, 20));
        getContentPane().add(jTextField5);
        jTextField5.setBounds(240, 530, 220, 30);

        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField4.setMaximumSize(new java.awt.Dimension(65, 20));
        jTextField4.setMinimumSize(new java.awt.Dimension(65, 20));
        jTextField4.setPreferredSize(new java.awt.Dimension(65, 20));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        getContentPane().add(jTextField4);
        jTextField4.setBounds(240, 470, 220, 30);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("To");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(90, 540, 90, 17);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("From");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(90, 480, 90, 17);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(260, 440, 90, 2);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 153, 0));
        jLabel9.setText("Time");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(290, 420, 60, 15);

        jDateChooser1.setDateFormatString("yyyy.MM.dd");
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
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
        jDateChooser1.setBounds(240, 360, 220, 30);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Date");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(90, 370, 90, 17);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Employee ID");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(90, 320, 90, 17);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Appointment ID");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(90, 210, 120, 30);

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
        jTextField1.setBounds(240, 210, 220, 30);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Customer ID");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(90, 270, 90, 17);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/icons8_Home_48px.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 110, 50, 40);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/pdf.png"))); // NOI18N
        jButton7.setText("Save As PDF");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 550, 130, 30));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ico/docx.png"))); // NOI18N
        jButton8.setText("Save As Doc");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 550, 130, 30));

        jButton6.setText("Clean");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 90, 70, 30));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Photo_Gallery_25px.png"))); // NOI18N
        jButton5.setText("View All");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 100, 30));

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customers/icons8_Search_25px.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 100, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jTextField6.setMaximumSize(new java.awt.Dimension(65, 20));
        jTextField6.setMinimumSize(new java.awt.Dimension(65, 20));
        jTextField6.setPreferredSize(new java.awt.Dimension(65, 20));
        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField6KeyReleased(evt);
            }
        });
        jPanel2.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 230, 30));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Search Appointments");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 20, 140, 30));

        jTable1.setBackground(new java.awt.Color(0, 0, 0));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "AID", "CID", "EID", "Date", "From", "To"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 620, 320));

        getContentPane().add(jPanel2);
        jPanel2.setBounds(610, 150, 680, 590);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/back/back.jpg.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 1400, 780);

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

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
            ResultSet rs = DB.getData("select* from appointments where AID='"+jTextField6.getText()+"' or CID='"+jTextField6.getText()+"' or EID='"+jTextField6.getText()+"'");

            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("AID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("EID"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("From1"));
                v.add(rs.getString("To1"));

                dtm.addRow(v);

               

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
       
        appointments();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

       loadtable();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        while(dtm.getRowCount() > 0)
        {
            dtm.removeRow(0);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String AID,CID,EID,date,from,to;
        

        AID = jTextField1.getText();
        CID = jComboBox1.getSelectedItem().toString();
        EID = jComboBox2.getSelectedItem().toString();
        date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        from = jTextField4.getText();
        to = jTextField5.getText();
        
        

        int loyalty =0;
        
        if(AID.equals("")||CID.equals("")||EID.equals("")||date.equals("")||from.equals("")||to.equals("")){
            JOptionPane.showMessageDialog(this, "Fields cannot be empty,Try again");
       
        }else{
                
            try {

            DB.setData("insert into appointments value('"+AID+"', '"+CID+"', '"+EID+"', '"+date+"','"+from+"','"+to+"')");
            //DB.setData("insert into rec_customers value('"+cid+"', '"+f_name+"', '"+l_name+"', '"+ad1+"','"+ad2+"','"+city+"','"+tel+"','"+dob+"','"+nic+"','"+loyalty+"','"+jLabel3.getText()+"')");
            JOptionPane.showMessageDialog(this, "Customer DI: "+CID+"  Added Successfully");
            
             //clearing textfields after
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        
        }

        

        //clearing textfields after
        jTextField1.setText("");
       jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
        

        //
        lastId();
        loadtable();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String AID,CID,EID,date,from,to;
        

        AID = jTextField1.getText();
        CID =jComboBox1.getSelectedItem().toString();
        EID = jComboBox2.getSelectedItem().toString();
        date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        from = jTextField4.getText();
        to = jTextField5.getText();
        
        

        

        try {
            
            DB.setData("update appointments set CID='"+CID+"', EID='"+EID+"',date='"+date+"', from1='"+from+"', to1='"+to+"' where AID='"+AID+"'");
            

            JOptionPane.showMessageDialog(this, "Apointment  "+AID+"  updated Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        //clearing textfields after
        jTextField1.setText("");
        jComboBox1.setSelectedItem(-1);
        jComboBox2.setSelectedIndex(-1);
        jTextField4.setText("");
        jTextField5.setText("");
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
        

        //
        lastId();
        loadtable();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {

            DB.setData("delete from appointments where AID = '"+jTextField1.getText()+"'");

            JOptionPane.showMessageDialog(this, "Appointment "+jTextField1.getText()+"  Deleted Successfully");

        } catch (Exception ex) {
            ex.printStackTrace();

        }

        //clearing textfields after
        jTextField1.setText("");
        jComboBox1.setSelectedIndex(-1);
        jComboBox2.setSelectedIndex(-1);
        jTextField4.setText("");
        jTextField5.setText("");
       ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText("");
       

        lastId();
        
        loadtable();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
         
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int row = this.jTable1.getSelectedRow();
        this.jTextField1.setText(dtm.getValueAt(row, 0).toString());
        
        this.jComboBox1.setSelectedItem(dtm.getValueAt(row, 1).toString());
        
        this.jComboBox2.setSelectedItem(dtm.getValueAt(row, 2).toString());
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());
        
        this.jTextField4.setText(dtm.getValueAt(row, 4).toString());
        this.jTextField5.setText(dtm.getValueAt(row, 5).toString());
       
       
        
        
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
           
      jComboBox1.requestFocus();
       }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       jTextField4.requestFocus();
       }
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
       jTextField5.requestFocus();
       }
    }//GEN-LAST:event_jTextField4KeyPressed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        this.dispose();
        new Customer().setVisible(true);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel28MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel28MouseClicked
        System.exit(0);

    }//GEN-LAST:event_jLabel28MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        
       
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        
        
         

    
        
        
        
        
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTextField6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyReleased
      
        
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
        dtm.setRowCount(0);
        try {
           
            ResultSet rs = DB.getData("select* from appointments where AID LIKE '"+jTextField6.getText()+"%' or CID LIKE '"+jTextField6.getText()+"%' or EID LIKE '"+jTextField6.getText()+"%'");

            while (rs.next()){
                Vector v = new Vector();
                v.add(rs.getString("AID"));
                v.add(rs.getString("CID"));
                v.add(rs.getString("EID"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("From1"));
                v.add(rs.getString("To1"));

                dtm.addRow(v);

               

            }

        } catch (Exception e) {
            e.printStackTrace();

        }
        
        
    }//GEN-LAST:event_jTextField6KeyReleased

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed

            String AID,CID,EID,date,from,to;
        
        AID = jTextField1.getText();
        CID = jComboBox1.getSelectedItem().toString();
        EID = jComboBox2.getSelectedItem().toString();
        date = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        from = jTextField4.getText();
        to = jTextField5.getText();
        
        
        
        if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(this, "Please Select an Appointment from table to generate a Token");

        
        }else{
            
            try {
                DB.setData("insert into t_appointments value('"+AID+"', '"+CID+"', '"+EID+"', '"+date+"','"+from+"','"+to+"')");
                
                String path;
                path = "reports\\token.jrxml";
                JasperReport compileReport = JasperCompileManager.compileReport(path);
                JasperPrint fillreport = JasperFillManager.fillReport(compileReport, null, DB.getConnection());
                JasperViewer.viewReport(fillreport, false);
                
                
              //  
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
            
        
        }
        
        try {
            DB.setData("DELETE FROM t_appointments WHERE AID ='"+AID+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton9ActionPerformed

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
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
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
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
