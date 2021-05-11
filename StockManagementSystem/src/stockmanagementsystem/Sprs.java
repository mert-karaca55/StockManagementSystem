/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stockmanagementsystem;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class Sprs extends javax.swing.JFrame {

    /**
     * Creates new form Sprs
     */
    public Sprs() {
        initComponents();
        connect();
        Urun();
       
    }
    Connection myConn;
    PreparedStatement pst;
    PreparedStatement pst1;
    PreparedStatement pst2;
    Product product=new Product();
    DefaultTableModel df;
     ResultSet rs;
    public void connect(){
         String url = "jdbc:mysql://localhost:3306/201635036?autoReconnect=true&useSSL=false";
         String username="root";
         String password="1234";
    
        try{
            
            myConn= (Connection)DriverManager.getConnection(url,username,password);
          // myStat= (Statement) myConn.createStatement();
            //ResultSet myRs = myStat.executeQuery("select * from urunler");
          
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void Urun(){
             try{
                pst=myConn.prepareStatement("select distinct musteri_ad from musteri");
                rs= pst.executeQuery();
                txtUrun.removeAllItems();
                
                while(rs.next()){
                    txtUrun.addItem(rs.getString("musteri_ad"));
                }
                } catch (SQLException ex) {
            Logger.getLogger(Product.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
    public void Id(){
        String id=txtId.getText();
        
        try {
            pst=myConn.prepareStatement("select * from urunlerr where urun_id=?");
            pst.setString(1, id);
            rs=pst.executeQuery();
            
                if(rs.next()==false){
                    JOptionPane.showMessageDialog(this, "Product is not found");
                    txtId.setText("");
                }
                else{
                String name=rs.getString("urun_adi");
                String price=rs.getString("urun_fiyati");
                
                txtAd.setText(name);
                txtUcret.setText(price);
                txtAdet.requestFocus();
                }
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sprs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Add(){
        
        try {
            int lastId=0;
        String pId=txtId.getText();
        String price=txtUcret.getText();
        String qty=txtAdet.getText();
        String total=txtTop.getText();
        String vendor=txtUrun.getSelectedItem().toString();
        String query1 = "insert into siparisdetay(musteriid,urunlerrid,price,qty,total)values(?,?,?,?,?)";
        
            pst=myConn.prepareStatement(query1,Statement.RETURN_GENERATED_KEYS);
         pst.setString(1, vendor);   
         pst.setString(2, pId);
         pst.setString(3, price);
         pst.setString(4,qty);
         pst.setString(5, total);
         pst.executeUpdate();
         rs=pst.getGeneratedKeys();
         
         if(rs.next()){
             lastId=rs.getInt(1);
         }
            
            
          String query2="update urunlerr set urun_stok=urun_stok+? where urunlerrid=?";
          pst2=myConn.prepareStatement(query2);
          
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                
                pId=(String)jTable1.getValueAt(i,0);
                qty=(String)jTable1.getValueAt(i,3);
                
                pst2.setString(1, pId);
                pst2.setString(1, qty);
                pst2.executeUpdate();
                
                
                
                
            }
            JOptionPane.showMessageDialog(this, "Purchase completed.");
            
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Sprs.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public void Purchase(){
        
        int price=Integer.parseInt(txtUcret.getText());
        int qty=Integer.parseInt(txtAdet.getText());
        
        int total=price*qty;
        
        df=(DefaultTableModel)jTable1.getModel();
        df.addRow(new Object[]{
        
            txtId.getText(),
            txtAd.getText(),
            txtUcret.getText(),
            txtAdet.getText(),
            total
        
        
        });
          
            int sum=0;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
            sum=sum+Integer.parseInt(jTable1.getValueAt(i, 4).toString());
        }
            txtTop.setText(String.valueOf(sum));
            txtId.setText("");
            txtAd.setText("");
            txtUcret.setText("");
            txtAdet.setText("");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUcret = new javax.swing.JTextField();
        txtTop = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUrun = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtId = new javax.swing.JTextField();
        txtAd = new javax.swing.JTextField();
        txtAdet = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel4.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        jLabel4.setText("Total");

        jLabel5.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        jLabel5.setText("Price");

        jLabel8.setText("Vendor:");

        jLabel6.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        jLabel6.setText("ProductName");

        jLabel7.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        jLabel7.setText("ProductId");

        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ProductId", "ProductName", "Price", "qty", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtIdKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        jLabel9.setText("Qty");

        jButton4.setText("◄");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(txtId)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addComponent(jLabel6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(txtUcret, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(53, 53, 53)
                                .addComponent(jLabel5)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addComponent(txtAdet, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTop, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel4)))
                        .addGap(200, 200, 200))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtUrun, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8)
                        .addComponent(txtUrun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUcret, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtAdet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
            Purchase();
            txtId.setText("");
            txtAd.setText("");
            txtUcret.setText("");
            txtAdet.setText("");
            Add();
          
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyPressed
           if(evt.getKeyCode()==KeyEvent.VK_ENTER)
                Id();
    }//GEN-LAST:event_txtIdKeyPressed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        User user=new User();
        user.show();
        this.hide();
    }//GEN-LAST:event_jButton4ActionPerformed
    Siparis spr=new Siparis();
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
            java.util.logging.Logger.getLogger(Sprs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sprs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sprs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sprs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sprs().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtAd;
    private javax.swing.JTextField txtAdet;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTop;
    private javax.swing.JTextField txtUcret;
    private javax.swing.JComboBox<String> txtUrun;
    // End of variables declaration//GEN-END:variables
}
