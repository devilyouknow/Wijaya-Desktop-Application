/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import com.barcodelib.barcode.Linear;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javassist.CtMethod.ConstParameter.string;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.connectcode.Code128Auto;
import static wijaya.DBconnect.connect;

/**
 *
 * @author Tharaka
 */
public class Stock_Barcode extends javax.swing.JFrame {
 PreparedStatement pst = null;
    Connection con = null;
    ResultSet rs = null;
    Image image;
    int row=-1;
    int xMouse;
    int yMouse;
    SimpleDateFormat formatter = new SimpleDateFormat("MMM d, yyyy");
    
    DefaultTableModel model = new DefaultTableModel(){  
      @Override
      public boolean isCellEditable(int row, int column){  
        return false;  
      }  
    };
    
    /**
     * Creates new form Stock_BarCode
     */
    public Stock_Barcode() {
        initComponents();
        con = DBconnect.connect();
       
        jDateChooser1.setDateFormatString("MMM d, yyyy");
        jDateChooser2.setDateFormatString("MMM d, yyyy");
        
        this.load_table();
        jTable1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            l.setBorder(new LineBorder(Color.GRAY,1));
            l.setHorizontalAlignment(CENTER);
            l.setBackground(Color.cyan);
            return l;
        }
        });
        
        
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
    
    public Stock_Barcode(int x,int y) {
        setLocation(x, y);
        initComponents();  
        jDateChooser1.setDateFormatString("MMM d, yyyy");
        jDateChooser2.setDateFormatString("MMM d, yyyy");
        
        con = DBconnect.connect();
        this.load_table();
        jTable1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
            JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            l.setBorder(new LineBorder(Color.GRAY,1));
            l.setHorizontalAlignment(CENTER);
            l.setBackground(Color.cyan);
            return l;
        }
        });
    }
    
    private void load_table(){
        
        Vector column_name = new Vector();
        column_name.addElement("BarCode");
        column_name.addElement("Item_Name");
        column_name.addElement("Category");
        column_name.addElement("Manufacture");
        column_name.addElement("Unit_Price");
        column_name.addElement("Qty");

        model.setColumnIdentifiers(column_name);
        jTable1.setRowHeight(25);
        
        
        String sql = "SELECT BarCode,Item_Name,Category,Manufacture,Unit_Price,Qty FROM stock_entry";
        try{
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next())
            {
                Vector column_data = new Vector();
                column_data.addElement(rs.getString("BarCode"));
                column_data.addElement(rs.getString("Item_Name"));
                column_data.addElement(rs.getString("Category"));
                column_data.addElement(rs.getString("Manufacture"));
                column_data.addElement(rs.getString("Unit_Price"));
                column_data.addElement(rs.getString("Qty"));
       
                model.addRow(column_data);
            }
            
            jTable1.setModel(model);
            
            
        }
        catch(Exception e){
            e.getStackTrace();
        }
    }
    
    private void load_barcode(String code){
        Linear barcode = new Linear();
        barcode.setType(Linear.CODE128);
        barcode.setData(code);
        barcode.setN(1.0f);
        jTextField1.setText(code);
        try {
            InputStream is = new ByteArrayInputStream(barcode.renderBarcodeToBytes());
            image = ImageIO.read(is).getScaledInstance(400,90,0);
            barcode_pic.setText(null);
            barcode_pic.setIcon(new ImageIcon(image));
        } catch (Exception ex) {
            Logger.getLogger(Stock_Barcode.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(rootPane,ex);
        }
    }
    private Image get_barcode(String code){
        Image im=null;
        Linear barcode = new Linear();
        barcode.setType(Linear.CODE128);
        barcode.setData(code);
        barcode.setN(1.0f);

        try {
            InputStream is = new ByteArrayInputStream(barcode.renderBarcodeToBytes());
            im = ImageIO.read(is).getScaledInstance(400,90,0);
        } catch (Exception ex) {
            Logger.getLogger(Stock_Barcode.class.getName()).log(Level.SEVERE, null, ex);
        }
        return im;
    }
    
    private void create_barcode_pdf(int ss)
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Set a Location to Save");
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
        {   
            try
            {
                Document document = new Document();
                FileOutputStream out = new FileOutputStream(
                new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\barcode_page.pdf"));
                PdfWriter.getInstance(document,out);
                document.open();
                com.lowagie.text.Image img1 = com.lowagie.text.Image.getInstance(image, null);
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100);
                PdfPCell cell;
                
                for(int y=0;y<ss;y++)
                {
                    cell = new PdfPCell();
                    cell.setImage(img1);
                    table.addCell(cell);
                }

                document.add(table);
                document.close();
                File yourFile = new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\barcode_page.pdf");
                if(yourFile.exists())
                {
                    Desktop.getDesktop().open(yourFile);
                }
            } catch (DocumentException | IOException e)
            {
                System.out.println(e);
//                e.getStackTrace();
            }
        }
    
    }
    
    private String get_date_f(String dd){
        int year = Integer.parseInt(dd.substring(6,10));
        int month = Integer.parseInt(dd.substring(3,5));
        int day = Integer.parseInt(dd.substring(0,2));

        int hours = 00;
        int minutes = 00;
        int seconds = 00;

        return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    }
    
    
    
    private void filter_and_print_btn(){
        if(jDateChooser1.getDate() == null || jDateChooser2.getDate() == null)
            JOptionPane.showMessageDialog(null,"Select date period","RMS Warning Message",JOptionPane.WARNING_MESSAGE);
        else
        {
            String from_d = ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText();
            String to_d = ((JTextField)jDateChooser2.getDateEditor().getUiComponent()).getText();
               //System.out.println(from_d);
            model.setRowCount(0);
            jTable1.setModel(model);

            Vector column_name = new Vector();
            column_name.addElement("BarCode");
            column_name.addElement("Item_Name");
            column_name.addElement("Category");
            column_name.addElement("Manufacture");
            column_name.addElement("Unit_Price");
            column_name.addElement("Qty");
            model.setColumnIdentifiers(column_name);
            jTable1.setRowHeight(25);

            String sql = "SELECT BarCode,Item_Name,Category,Manufacture,Unit_Price,Qty FROM stock_entry WHERE  (reg_date BETWEEN '"+from_d+"' AND '"+to_d+"')";
            try{
                pst = con.prepareStatement(sql);
                rs = pst.executeQuery();

                while(rs.next())
                {
                    Vector column_data = new Vector();
                    column_data.addElement(rs.getString("BarCode"));
                    column_data.addElement(rs.getString("Item_Name"));
                    column_data.addElement(rs.getString("Category"));
                    column_data.addElement(rs.getString("Manufacture"));
                    column_data.addElement(rs.getString("Unit_Price"));
                    column_data.addElement(rs.getString("Qty"));
                    model.addRow(column_data);
                }

                jTable1.setModel(model);

                rs.beforeFirst();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Set a Location to Save");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fileChooser.setAcceptAllFileFilterUsed(false);

                if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) 
                {   
                    try
                    {
                        Document document = new Document();
                        FileOutputStream out = new FileOutputStream(
                        new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\barcode_page.pdf"));
                        PdfWriter.getInstance(document,out);
                        document.open();

                        PdfPTable table = new PdfPTable(2);
                        table.setWidthPercentage(100);
                        PdfPCell cell;

                        while(rs.next())
                        {
                            String ss = rs.getString("BarCode").toUpperCase()+"-"+rs.getString("Item_Name").toUpperCase()+"-"+rs.getString("Category").toUpperCase()+"-"+rs.getString("Manufacture").toUpperCase()+"-"+rs.getString("Unit_Price").toUpperCase()+"-"+rs.getString("Qty");
                            com.lowagie.text.Image img1 = com.lowagie.text.Image.getInstance(get_barcode(ss), null);
                            cell = new PdfPCell();
                            cell.setImage(img1);
                            table.addCell(cell);

                            cell = new PdfPCell();
                            cell.setImage(img1);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(rs.getString("BarCode").toUpperCase()+" - Copy-1"));
                            cell.setBackgroundColor(Color.yellow);
                            table.addCell(cell);

                            cell = new PdfPCell(new Phrase(rs.getString("BarCode").toUpperCase()+" - Copy-2"));
                            cell.setBackgroundColor(Color.yellow);
                            table.addCell(cell);
                        }

                        document.add(table);
                        document.close();
                        File yourFile = new File(fileChooser.getSelectedFile().toString().replace("\\","/")+"\\barcode_page.pdf");
                        if(yourFile.exists())
                        {
                            Desktop.getDesktop().open(yourFile);
                        }
                    } catch (DocumentException | IOException e)
                    {
                        System.out.println(e);
//                        e.getStackTrace();
                    }
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
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        barcode_pic = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

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
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 710, 1310, 10));

        jLabel19.setForeground(new java.awt.Color(240, 240, 240));
        jLabel19.setText("All right reserved. Wijaya Digital Printers [2018]");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 720, 360, -1));

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Trajan Pro", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 255, 0));
        jLabel4.setText("<html> <body> <u> bar-code generate </u> </body> </html>");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 0, 420, 50));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 90, 10, 470));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        barcode_pic.setBackground(new java.awt.Color(255, 255, 255));
        barcode_pic.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        barcode_pic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        barcode_pic.setText("* No Asset Selected");
        barcode_pic.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 204, 0), 1, true));
        jPanel3.add(barcode_pic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 450, 90));

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 110, 450, 90));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(240, 240, 240));
        jLabel2.setText("Barcode Text :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 230, 140, -1));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 226, 300, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(240, 240, 240));
        jLabel5.setText("No. of Images :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 320, 140, -1));
        jPanel2.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 370, 530, 10));

        jDateChooser1.setBackground(new java.awt.Color(204, 255, 204));
        jDateChooser1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 180, 30));

        jButton2.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jButton2.setText("filter & print");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton2MouseClicked(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 490, 200, 40));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 530, 460));

        jPanel4.setBackground(new java.awt.Color(51, 51, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 255, 255)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jDateChooser2.setBackground(new java.awt.Color(204, 255, 204));
        jDateChooser2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jPanel4.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 180, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(240, 240, 240));
        jLabel6.setText("to");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, 20, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(240, 240, 240));
        jLabel7.setText("Select date period to print barcords");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, 290, -1));

        jComboBox1.setBackground(new java.awt.Color(255, 255, 204));
        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 240, 50, 30));

        jButton4.setFont(new java.awt.Font("Trajan Pro", 1, 18)); // NOI18N
        jButton4.setText("Print Barcode");
        jButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton4MouseClicked(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 230, 200, 40));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 630, 480));

        jPanel5.setBackground(new java.awt.Color(51, 51, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 550, 480));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/go_previous_blue.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, 50, 50));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1360, 640));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton2MouseClicked

        this.filter_and_print_btn();
        
    }//GEN-LAST:event_jButton2MouseClicked

    private void jButton4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton4MouseClicked
        
        if(row == -1)
            JOptionPane.showMessageDialog(null,"Please select Item Code","RMS Warning Message",JOptionPane.WARNING_MESSAGE);
        else
        {
            int pics = Integer.parseInt(jComboBox1.getSelectedItem().toString());
            create_barcode_pdf(pics);        
        }
        
//        try
//        {
//            String s  = " insert into process(BarCode )  "
//                + "values ('"+jTextField1.getText()+"') ";
//                pst = con.prepareStatement(s);
//                pst.execute();
//            
//        }
//  catch (Exception e)       
//  {
//         JOptionPane.showMessageDialog(this,e);
//         System.out.println(e);     
//        }
    }//GEN-LAST:event_jButton4MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
               
        this.dispose();
        new Stock_PanelSet().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
       
        System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
      
        row = jTable1.getSelectedRow();
        String BarCode = jTable1.getModel().getValueAt(row,0).toString();
        String Item_Name = jTable1.getModel().getValueAt(row,1).toString();
        String Category = jTable1.getModel().getValueAt(row,2).toString();
        String Manufacture = jTable1.getModel().getValueAt(row,3).toString();
        String Unit_Price = jTable1.getModel().getValueAt(row,4).toString();
        String Qty = jTable1.getModel().getValueAt(row,5).toString();
        
        this.load_barcode(BarCode.toUpperCase()+"-"+Item_Name.toUpperCase()+"-"+Category.toUpperCase()+"-"+Manufacture.toUpperCase()+Unit_Price.toUpperCase()+"-"+Qty.toUpperCase());
    }//GEN-LAST:event_jTable1MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
       
        this.dispose();
        new Stock_PanelSet().setVisible(true);
    }//GEN-LAST:event_jLabel8MouseClicked

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
            java.util.logging.Logger.getLogger(Stock_Barcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock_Barcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock_Barcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock_Barcode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock_Barcode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel barcode_pic;
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
