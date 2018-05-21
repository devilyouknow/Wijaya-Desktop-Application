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
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 *
 * @author Tharaka
 */
public class Stock_Manage extends javax.swing.JFrame {
    PreparedStatement pst = null;
    Connection con = null;
    ResultSet rs = null;
    /**
     * Creates new form Stock_Manage
     */
        
    public Stock_Manage() {
        initComponents();
               
        con = DBconnect.connect();
        loadtable();
        tableload();
//        tableload1();
                    
        showdate();
        showtime();
    }
    
    final void showdate(){
        Date d = new Date();
        SimpleDateFormat adf = new SimpleDateFormat("yyyy-MM-dd");
        String datee = adf.format(d);
        date.setText(datee);
        
         }
    
    void showtime()
    {
        
        new Timer(0,(ActionEvent e) -> {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            String timee = sdf.format(d);
            time.setText(timee);
        }).start();
    }
    
    
    
    public void tableload()
    {
        try{
        String lod = "SELECT BarCode as BarCode,Item_Name,Manufacture,Date_return,Note as a FROM stock_damage_last order by BarCode";
        pst = con.prepareStatement(lod);
        rs = pst.executeQuery();
        
        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
//        public void tableload1()
//    {
//        try{
//        String lod = "SELECT BarCode as BarCode,Item_Name,Manufacture,Date_return,Note as a FROM stock_return_final order by BarCode";
//        pst = con.prepareStatement(lod);
//        rs = pst.executeQuery();
//        
//        jTable4.setModel(DbUtils.resultSetToTableModel(rs));
//        }
//        catch(Exception e)
//        {
//            System.out.println(e);
//        }
//    } 
    
    
    void loadtable()
    {
        try
        {
            String sql="Select * from  stock_return_final Order by BarCode";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch (Exception e)
        {
            System.out.println(e);
        }           
    }
    
    void loadtable1()
    {
        try
        {
            String sql="Select * from  stock_damage_last Order by BarCode";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            jTable4.setModel(DbUtils.resultSetToTableModel(rs));
            
        }
        catch (Exception e)
        {
            
        }
                
    }
    
    
    public void word()
    {
        
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Set a Location to Save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
        {   
            Cursor hourglassCursor = new Cursor(Cursor.WAIT_CURSOR);
            setCursor(hourglassCursor);
            
            try
            {
               //Blank Document
                XWPFDocument document= new XWPFDocument();
                //Write the Document in file system
                FileOutputStream out = new FileOutputStream(
                new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.docx"));
                //create table
                XWPFTable table = document.createTable();
                //create first row             
                
                XWPFTableRow tableRowOne = table.getRow(0);
                tableRowOne.getCell(0).setText("BarCode");
                tableRowOne.addNewTableCell().setText("Item_Name");
                tableRowOne.addNewTableCell().setText("Manufacture");
                tableRowOne.addNewTableCell().setText("Date_return");
                tableRowOne.addNewTableCell().setText("Note");
                for(int y=0;y<jTable4.getRowCount();y++)
                {
                    XWPFTableRow new_row = table.createRow();//create new row
                    for(int x=0;x<jTable4.getColumnCount();x++)
                    {
                        new_row.getCell(x).setText((String)jTable4.getModel().getValueAt(y, x));
                    }
                }

                document.write(out);
                out.close();
                
                //to check whether file is created or not
                File yourFile = new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.docx");
                if(yourFile.exists())
                {
                    //JOptionPane.showMessageDialog(null,"Word Document created Successfully");
                    Desktop.getDesktop().open(yourFile);
                    
                    hourglassCursor = new Cursor(Cursor.DEFAULT_CURSOR);
                    setCursor(hourglassCursor);
                }
            }
            catch(Exception e){
                e.getStackTrace();
            }   
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane6 = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel24 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        time.setForeground(new java.awt.Color(255, 255, 255));
        time.setText("Time");
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 219, 60));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/cal1.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 40, 60));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/clock2.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 40, 80));

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        date.setForeground(new java.awt.Color(255, 255, 255));
        date.setText("date");
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 150, 40));

        jLabel14.setBackground(new java.awt.Color(51, 51, 51));
        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("LogOut");
        jLabel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 80, 60, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/mlabel.jpeg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-110, 20, 440, 80));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/employee.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, 60, 60));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1360, 110));

        jLabel19.setForeground(new java.awt.Color(240, 240, 240));
        jLabel19.setText("All right reserved. Wijaya Digital Printers [2018]");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 720, 360, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTabbedPane6.setBackground(new java.awt.Color(51, 255, 153));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 51));
        jLabel9.setText("*");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 20, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 204));
        jLabel8.setText("BarCode ");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 172, 110, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 204));
        jLabel6.setText("Item Name");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 170, 160, 30));

        jButton3.setBackground(new java.awt.Color(51, 255, 51));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jButton3.setText("Filter");
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
        jPanel4.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, 240, 40));

        jButton1.setBackground(new java.awt.Color(153, 255, 153));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/pdf.png"))); // NOI18N
        jButton1.setText("PDF Document");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jPanel4.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 160, 300, 40));

        jButton2.setBackground(new java.awt.Color(153, 255, 153));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/docx.png"))); // NOI18N
        jButton2.setText("Word Document");
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 210, 300, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 1300, 280));

        jLabel4.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 0));
        jLabel4.setText("<html>\n<body>\n<u> Quick Lookup </u>\n</body>\n</html>");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, 350, 60));

        jLabel2.setFont(new java.awt.Font("Trajan Pro", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 0));
        jLabel2.setText("<html>\n<body>\n<p> <b> Filter Inventory </b> </p>\n</body>\n</html>");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));
        jPanel4.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 200, 30));
        jPanel4.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 190, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/go_previous_blue.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 50, 40));

        jTabbedPane6.addTab("Quick Look-Up", jPanel4);

        jPanel6.setBackground(new java.awt.Color(51, 51, 51));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("BarCode :");
        jPanel6.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 150, 110, -1));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Item Name :");
        jPanel6.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 190, 130, -1));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Date :");
        jPanel6.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 270, 140, -1));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Note :");
        jPanel6.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 320, 70, -1));
        jPanel6.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 150, 190, 30));
        jPanel6.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 190, 190, 30));
        jPanel6.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 230, 190, 30));
        jPanel6.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 310, 180, 60));

        jButton9.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        jButton9.setText("Clear");
        jButton9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton9MouseClicked(evt);
            }
        });
        jPanel6.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 390, 300, 40));

        jButton14.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jButton14.setText("delete item");
        jButton14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton14MouseClicked(evt);
            }
        });
        jPanel6.add(jButton14, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 500, -1, 30));

        jButton10.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        jButton10.setText("Return");
        jButton10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton10MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jButton10MouseEntered(evt);
            }
        });
        jPanel6.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 490, 300, 40));

        jTextField13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTextField13MouseEntered(evt);
            }
        });
        jPanel6.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 240, 30));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("BarCode");
        jPanel6.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 120, -1));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jPanel6.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 910, 120));

        jButton15.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jButton15.setText("Show All");
        jButton15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton15MouseClicked(evt);
            }
        });
        jPanel6.add(jButton15, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 500, 130, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 51));
        jLabel22.setText("Damage Item list :");
        jPanel6.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 220, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 51));
        jLabel23.setText("Return list :");
        jPanel6.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 220, -1));

        jButton8.setBackground(new java.awt.Color(204, 255, 204));
        jButton8.setFont(new java.awt.Font("Trajan Pro", 1, 20)); // NOI18N
        jButton8.setText("add list");
        jButton8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton8MouseClicked(evt);
            }
        });
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel6.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 440, 300, 40));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 910, 140));

        jButton12.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jButton12.setText("Clean Table");
        jButton12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton12MouseClicked(evt);
            }
        });
        jPanel6.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 500, -1, 30));

        jButton13.setFont(new java.awt.Font("Trajan Pro", 1, 14)); // NOI18N
        jButton13.setText("delete item");
        jButton13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton13MouseClicked(evt);
            }
        });
        jPanel6.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 500, -1, 30));

        jLabel33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel33MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 480, -1, -1));

        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 480, -1, -1));

        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, -1, -1));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 140, 10, 390));

        jLabel29.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 255, 0));
        jLabel29.setText("<html> <body> <u> Damage Stock Management </u> </body> </html>");
        jPanel6.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, -10, 620, 60));
        jPanel6.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 270, 190, 30));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Manufacture :");
        jPanel6.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 230, 140, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/go_previous_blue.png"))); // NOI18N
        jLabel7.setText("jLabel7");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 50));

        jTabbedPane6.addTab("Return Item", jPanel6);

        jPanel2.add(jTabbedPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 1340, 600));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1360, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
                
        this.dispose();
        new Stock_PanelSet().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked

        MessageFormat header = new MessageFormat("Report Print!");
        MessageFormat foter = new MessageFormat("page{0,number,integer}");

        try{

            jTable4.print(JTable.PrintMode.NORMAL,header,foter);

        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Try Again"); }
    }//GEN-LAST:event_jLabel35MouseClicked

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        word();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void jLabel33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel33MouseClicked

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Set a Location to Save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                //
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                FileOutputStream out = new FileOutputStream(
                    new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.pdf"));
                        PdfWriter.getInstance(document,out);
                        document.open();
                        // a table with 12 columns
                        PdfPTable table = new PdfPTable(10);
                        table.setWidthPercentage(110);
                        Font colfont = new Font(Font.HELVETICA, 9);
                        Font data_font = new Font(Font.HELVETICA, 8);
                        PdfPCell cell;
                        cell = new PdfPCell(new Phrase("BarCode",colfont));
                        cell.setBackgroundColor(Color.orange);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Item_Name",colfont));
                        cell.setBackgroundColor(Color.yellow);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Manufacture",colfont));
                        cell.setBackgroundColor(Color.yellow);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Date_return",colfont));
                        cell.setBackgroundColor(Color.yellow);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Note",colfont));
                        cell.setBackgroundColor(Color.yellow);
                        table.addCell(cell);

                        // we add the remaining data cells
                        for(int y=0;y<jTable4.getRowCount();y++)
                        {
                            for(int x=0;x<jTable4.getColumnCount();x++)
                            {
                                cell = new PdfPCell(new Phrase((String)jTable4.getModel().getValueAt(y, x),data_font));
                                table.addCell(cell);
                            }
                        }
                        document.add(table);
                        document.close();
                        File yourFile = new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.pdf");
                            if(yourFile.exists())
                            {
                                Desktop.getDesktop().open(yourFile);
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e);
                        }
                    }
    }//GEN-LAST:event_jLabel33MouseClicked

    private void jButton13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton13MouseClicked
        int y = JOptionPane.showConfirmDialog(null,"Delete");

        if(y==0)
        {
            String BarCode = jTextField9.getText();
            try{
                String sql ="DELETE from stock_return_final where BarCode = '"+BarCode+"'";
                pst = con.prepareStatement(sql);
                pst.execute();

                tableload();
//                tableload1();
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
    }//GEN-LAST:event_jButton13MouseClicked

    private void jButton12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton12MouseClicked

        DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();
        while(dtm.getRowCount() > 0)
        {
            dtm.removeRow(0);
        }
                
        DefaultTableModel dtm1 = (DefaultTableModel) jTable4.getModel();
        while(dtm1.getRowCount() > 0)
        {
            dtm1.removeRow(0);
        }
    }//GEN-LAST:event_jButton12MouseClicked

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked

        int ro = jTable4.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) jTable4.getModel();

        int row = this.jTable4.getSelectedRow();
        this.jTextField9.setText(dtm.getValueAt(row, 0).toString());
        this.jTextField10.setText(dtm.getValueAt(row, 1).toString());
        this.jTextField11.setText(dtm.getValueAt(row, 2).toString());
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());
        this.jTextField12.setText(dtm.getValueAt(row, 4).toString());
    }//GEN-LAST:event_jTable4MouseClicked

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked

        int ro = jTable3.getSelectedRow();
        DefaultTableModel dtm = (DefaultTableModel) jTable3.getModel();

        int row = this.jTable3.getSelectedRow();
        this.jTextField9.setText(dtm.getValueAt(row, 0).toString());
        this.jTextField10.setText(dtm.getValueAt(row, 1).toString());
        this.jTextField11.setText(dtm.getValueAt(row, 2).toString());
        ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).setText(dtm.getValueAt(row, 3).toString());
        this.jTextField12.setText(dtm.getValueAt(row, 4).toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void jButton10MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10MouseEntered

    private void jButton10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton10MouseClicked
        String BarCode = jTextField9.getText();
        String Item_Name = jTextField10.getText();
        String Manufacture = jTextField11.getText();
        String Date_return = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String Note = jTextField12.getText();
        

        int x = JOptionPane.showConfirmDialog(null, " Do You want to Return ?", "add to DB", JOptionPane.INFORMATION_MESSAGE);

        {

            try{
                String s  = " insert into stock_damage_last(BarCode , Item_Name , Manufacture , Date_return , Note )  "
                + "values ('"+BarCode+"' , '"+Item_Name+"', '"+Manufacture+"' , '"+Date_return+"' , '"+Note+"') ";
                pst = con.prepareStatement(s);
                pst.execute();

                String q  = " delete from stock_return_final where BarCode = '"+BarCode+"'";
                pst = con.prepareStatement(q);
                pst.execute();

                // getentry_value();
                loadtable();
                loadtable1();
                JOptionPane.showMessageDialog(this,"Complete !");

                jTextField9.setText("");
                jTextField10.setText("");
                jTextField11.setText("");
                jDateChooser1.setDate(null);
                jTextField12.setText("");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);

            }

        }
    }//GEN-LAST:event_jButton10MouseClicked

    private void jButton9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton9MouseClicked

        jTextField9.setText(null);
        jTextField10.setText(null);
        jTextField11.setText(null);
        jDateChooser1.setDate(null);
        jTextField12.setText(null);
    }//GEN-LAST:event_jButton9MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        word();
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Set a Location to Save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
        {
            try
            {
                //
                com.lowagie.text.Document document = new com.lowagie.text.Document();
                FileOutputStream out = new FileOutputStream(
                    new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.pdf"));
                        PdfWriter.getInstance(document,out);
                        document.open();
                        // a table with 12 columns
                        PdfPTable table = new PdfPTable(10);
                        table.setWidthPercentage(110);
                        Font colfont = new Font(Font.HELVETICA, 9);
                        Font data_font = new Font(Font.HELVETICA, 8);
                        PdfPCell cell;
                        cell = new PdfPCell(new Phrase("BarCode",colfont));
                        cell.setBackgroundColor(Color.orange);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Item_Name",colfont));
                        cell.setBackgroundColor(Color.orange);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Manufacture",colfont));
                        cell.setBackgroundColor(Color.orange);
                        table.addCell(cell);
                        cell = new PdfPCell(new Phrase("Date",colfont));
                        cell.setBackgroundColor(Color.orange);
                        table.addCell(cell);

                        // we add the remaining data cells
                        for(int y=0;y<jTable2.getRowCount();y++)
                        {
                            for(int x=0;x<jTable2.getColumnCount();x++)
                            {
                                cell = new PdfPCell(new Phrase((String)jTable2.getModel().getValueAt(y, x),data_font));
                                table.addCell(cell);
                            }
                        }
                        document.add(table);
                        document.close();
                        File yourFile = new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\report.pdf");
                            if(yourFile.exists())
                            {
                                Desktop.getDesktop().open(yourFile);
                            }
                        } catch (Exception e)
                        {
                            System.out.println(e);
                        }
                    }
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked

        String BarCode = jTextField14.getText();
        String iname = jTextField15.getText();
        //String acode = jTextField1.getText();
        
        if(BarCode.equals(""))
            {
            JOptionPane.showMessageDialog(this, "Please Enter BarCode ,Do you want find details!!");
            //----------------------------------------------------------------------------------------//
             Color color=new Color(230,130,130); 

             if(jTextField14.getText().equals(""))
                jTextField14.setBackground(color);        
            //-----------------------------------------------------------------------------------------//
            return;
        
            }
            else
            {
        try
        {
            String sql ="select * from stock_entry where BarCode = '"+BarCode+"'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {}
        try{
            String sql ="select * from stock_entry where BarCode = '"+BarCode+"'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {}
            }
            
    }//GEN-LAST:event_jButton3MouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton8MouseClicked

        String BarCode = jTextField9.getText();
        String Item_Name = jTextField10.getText();
        String Manufacture = jTextField11.getText();
        String Date_return = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
        String Note = jTextField12.getText();

        int x = JOptionPane.showConfirmDialog(null, " Do You want to save ?", "add to DB", JOptionPane.INFORMATION_MESSAGE);

        {
            if(BarCode.equals("")||Item_Name.equals("")||Manufacture.equals("")||Date_return.equals("")||Note.equals(""))
            {
            JOptionPane.showMessageDialog(this, "Some Fields are empty!!");
            //----------------------------------------------------------------------------------------//
             Color color=new Color(230,130,130); 

             if(jTextField9.getText().equals(""))
                jTextField9.setBackground(color);
            if(jTextField10.getText().equals(""))
                jTextField10.setBackground(color);
             if(jTextField11.getText().equals(""))
                jTextField11.setBackground(color);
            if(jTextField12.getText().equals(""))
                jTextField12.setBackground(color);
            //-----------------------------------------------------------------------------------------//
            return;
        
            }
            else
            {

            try{
                String s  = " insert into  stock_return_final(BarCode , Item_Name , Manufacture , Date_return , Note )  "
                + "values ('"+BarCode+"' , '"+Item_Name+"', '"+Manufacture+"' , '"+Date_return+"' , '"+Note+"') ";
                pst = con.prepareStatement(s);
                pst.execute();

                // getentry_value();
                loadtable();
                JOptionPane.showMessageDialog(this,"Complete !");

                jTextField9.setText("");
                jTextField10.setText("");
                jTextField11.setText("");
                jDateChooser1.setDate(null);
                jTextField12.setText("");

            }catch(Exception e){
                JOptionPane.showMessageDialog(null,e);

            }

            }
        }
    }//GEN-LAST:event_jButton8MouseClicked

    private void jTextField13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextField13MouseEntered
            String BarCode = jTextField13.getText();
        //String acode = jTextField1.getText();

        try{
            String sql ="select * from stock_return_final where BarCode = '"+BarCode+"'";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {}
        
        
        
    }//GEN-LAST:event_jTextField13MouseEntered

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jButton14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton14MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14MouseClicked

    private void jButton15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton15MouseClicked
                
        try
        {
            String sql ="select * from stock_entry";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e)
        {}
//        try{
//            String sql ="select * from stock_entry";
//
//            pst = con.prepareStatement(sql);
//            rs = pst.executeQuery();
//            jTable3.setModel(DbUtils.resultSetToTableModel(rs));
//        }
//        catch(Exception e)
//        {}
        
        
    }//GEN-LAST:event_jButton15MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        this.dispose();
        new Stock_PanelSet().setVisible(true);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
       
        this.dispose();
        new Stock_PanelSet().setVisible(true);
    }//GEN-LAST:event_jLabel7MouseClicked

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
            java.util.logging.Logger.getLogger(Stock_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock_Manage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock_Manage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane6;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
