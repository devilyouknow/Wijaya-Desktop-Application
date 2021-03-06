/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wijaya;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Date.from;
import javax.swing.JPanel;
import javax.swing.Timer;
import static mondrian.tui.XmlUtil.select;
import org.w3c.tidy.Out;
import static sun.misc.MessageUtils.where;

/**
 *
 * @author Tharaka
 */
public class Stock_PanelSet extends javax.swing.JFrame {
    PreparedStatement pst = null;
    Connection con = null;
    ResultSet rs = null;
    /**
     * Creates new form Stock_Main
     */
    public Stock_PanelSet() {
        initComponents();
            
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
    
    //------panel_color_select------//
    void setpanel(JPanel x){
        x.setBackground(new Color(102,102,102));
    }
     void resetpanell (JPanel x){
        x.setBackground(new Color(102,102,102));
    }
    
    
    
     void setpanelnext(JPanel x){
        x.setBackground(new Color(51,51,51));
    }
    void resetpanel (JPanel x){
        x.setBackground(new Color(51,51,51));
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
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel19 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(null);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1366, 768));
        setResizable(false);
        setSize(new java.awt.Dimension(1366, 768));
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

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel11.setBackground(new java.awt.Color(51, 51, 51));
        jPanel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel11MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel11MouseEntered(evt);
            }
        });
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("<html> <u><font size =10>Stock</font></u><br> Entry </html>");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jPanel11.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 280, 140));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/new_database.png"))); // NOI18N
        jPanel11.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(142, 10, -1, 130));

        jPanel3.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 150));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 320, 170));

        jPanel8.setBackground(new java.awt.Color(102, 102, 102));
        jPanel8.setMaximumSize(new java.awt.Dimension(175, 137));
        jPanel8.setMinimumSize(new java.awt.Dimension(175, 137));
        jPanel8.setPreferredSize(new java.awt.Dimension(175, 137));
        jPanel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel8MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel8MouseExited(evt);
            }
        });
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(51, 51, 51));
        jPanel14.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/rsz_1tls-boca-systems-div-barcode-reader.png"))); // NOI18N
        jPanel14.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 100, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("<html> <u><b>BarCode</b></u><br><font size = 5> Generate </html>");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });
        jPanel14.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 270, 140));

        jPanel8.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 300, 140));

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 390, 320, 160));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setMaximumSize(new java.awt.Dimension(128, 189));
        jPanel6.setMinimumSize(new java.awt.Dimension(128, 189));
        jPanel6.setPreferredSize(new java.awt.Dimension(128, 189));
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel6MouseExited(evt);
            }
        });
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel16.setBackground(new java.awt.Color(51, 51, 51));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/session_manager.png"))); // NOI18N
        jPanel16.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 130, 150));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("<html> <u><b>Manage</b></u><br><font size = 5> Stock </html>");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel16.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 140, 280));

        jPanel6.add(jPanel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 170, 330));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 200, 190, 350));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 710, 1310, 10));

        jLabel19.setForeground(new java.awt.Color(240, 240, 240));
        jLabel19.setText("All right reserved. Wijaya Digital Printers [2018]");
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel19MouseEntered(evt);
            }
        });
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 720, 360, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/stock/images/bg.jpg"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 1580, 890));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.dispose();
        new Stock_Entry().setVisible(true);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        setpanelnext(jPanel3);
        setpanel(jPanel11);
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        resetpanell(jPanel3);
        resetpanel(jPanel11);
    }//GEN-LAST:event_jPanel3MouseExited

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        this.dispose();
        new Stock_Barcode().setVisible(true);
    }//GEN-LAST:event_jLabel24MouseClicked

    private void jPanel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseEntered
        setpanelnext(jPanel6);
        setpanel(jPanel16);
    }//GEN-LAST:event_jPanel6MouseEntered

    private void jPanel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseExited
        resetpanell(jPanel6);
        resetpanel(jPanel16);
    }//GEN-LAST:event_jPanel6MouseExited

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        this.dispose();
        new Stock_Manage().setVisible(true);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void jPanel8MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseEntered
        setpanelnext(jPanel8);
        setpanel(jPanel14);
    }//GEN-LAST:event_jPanel8MouseEntered

    private void jPanel8MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel8MouseExited
        resetpanell(jPanel8);
        resetpanel(jPanel14);
    }//GEN-LAST:event_jPanel8MouseExited

    private void jPanel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseClicked
       
    }//GEN-LAST:event_jPanel11MouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       
        this.dispose();
        new Home().setVisible(true);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jPanel11MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel11MouseEntered
        

//        new NoticeWindow("Out of Stock..",NPosition.TOP_LEFT);
//        new NoticeWindow(NoticeType.ERROR_NOTIFICATION,"",NoticeWindow.LONG_DELAY,NPosition.TOP_LEFT);
          
        
    }//GEN-LAST:event_jPanel11MouseEntered

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseEntered
//                String sql = "select min(qty) from stock_entry";
//        try
//        {
//            pst = con.prepareStatement(sql);
//            rs = pst.executeQuery();
//
//            String nf = "Select BarCode,Item_Name From stock_entry Where Qty<20";
//            while(rs.next())
//            {
        
                new NoticeWindow(" 4L RGB Ink Bottle Out of Stock",NPosition.TOP_LEFT);
                new NoticeWindow(NoticeType.ERROR_NOTIFICATION,"",NoticeWindow.LONG_DELAY,NPosition.TOP_LEFT);
//        
//            }        
//        }
//        catch(Exception e)
//        {     
//        } 
        
        
        
    }//GEN-LAST:event_jLabel19MouseEntered

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
            java.util.logging.Logger.getLogger(Stock_PanelSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Stock_PanelSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Stock_PanelSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Stock_PanelSet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Stock_PanelSet().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
