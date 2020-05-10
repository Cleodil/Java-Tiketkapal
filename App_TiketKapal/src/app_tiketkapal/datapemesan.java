/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_tiketkapal;

import Koneksi.db_koneksi;
import com.mysql.jdbc.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public final class datapemesan extends javax.swing.JFrame {
    //membuat class DefautTableModel
    private DefaultTableModel model;
    String nopemesan, nama, alamat, notelp, usia;
    
    /**
     * Creates new form 
     */
    public datapemesan() {
        initComponents();
        //memberi penamaan pada judul kolom tblGaji
        model = new DefaultTableModel();
        tblpemesan.setModel(model);
        model.addColumn("Nopemesan");
        model.addColumn("Nama");
        model.addColumn("Alamat");
        model.addColumn("Notelp");
        model.addColumn("Usia");        
        getData();
    }
    
    public void loadData(){
        nopemesan = txtpemesan.getText();
        nama      = txtnamapemesan.getText();
        alamat    = txtalamat.getText();
        notelp    = txtnotelp.getText();
        usia      = txtumur.getText();
    }
    
    public void getData(){
        //menghapus isi tabel gaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            //membuat statement pemanggilan data pada table tblpemesan dari database
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "select * from pemesan";
            ResultSet res = stat.executeQuery(sql);
            
            //penelusuran baris pada tabeltblGaji dari database
            while(res.next()){
                Object[] obj = new Object [5];
                obj[0] = res.getString("Nopemesan");
                obj[1] = res.getString("Nama");
                obj[2] = res.getString("Alamat");
                obj[3] = res.getString("Notelp");
                obj[4] = res.getString("Usia");
                
                model.addRow (obj);             
            }        
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());          
        }        
    }

    public void saveData(){
        loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "Insert into pemesan(Nopemesan, Nama, Alamat,Notelp, Usia)"
                    + "values('"+nopemesan+"','"+nama+"','"+alamat+"', '"+notelp+"', '"+usia+"')";                  
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void Reset(){
        nopemesan       = "";
        nama            = "";
        alamat          = "";
        notelp          = "";
        usia            = "";
        txtpemesan.setText(nopemesan);
        txtnamapemesan.setText(nama);
        txtalamat.setText(alamat);
        txtnotelp.setText(notelp);
        txtumur.setText(usia);
    }
    
    public void dataSelect(){
        int i = tblpemesan.getSelectedRow();
        if (i == -1){
            //tidak ada baris terpilih
            return;           
        }
        txtpemesan.setText(""+model.getValueAt(i, 0));
        txtnamapemesan.setText(""+model.getValueAt(i, 1));
        txtalamat.setText(""+model.getValueAt(i, 2));
        txtnotelp.setText(""+model.getValueAt(i, 3));
        txtumur.setText(""+model.getValueAt(i, 4));
    }
    
    public void updateData(){
        /*memanggil class loadData() untuk menentukan kondisi atau variable nip yang akan
        diubah berdasarkan data yang dipilih.
        */
        loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "UPDATE pmesan SET Nama      = '"+nama+"',"
                                   +"Alamat        = '"+alamat+"',"
                                   +"Notelp        = '"+notelp+"',"
                                   +"Usia        = '"+usia+"' WHERE Nopemesan = '"+nopemesan+"'";
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            /* memanggil class getData() agar setelah update berasil data yang telah diubah 
             *dapat ditampilkan pada tabel
            */
            getData();
            /* memanggil class Reset() agar setelah update berhasil data yang terdapat pada
            * komponen" langsung dikosongkan
            */
            Reset();
            JOptionPane.showMessageDialog(null, "Update Berhasil...");          
        }catch(SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }        
    }
    
    public void deleteData(){
        loadData();
        //menampilkan pesan konfirmasi OK dan Cancel sebelum dilakikan proses delete
        int pesan = JOptionPane.showConfirmDialog(null,"Anda YaQin Menghapus Data ini"+nopemesan+"?", "KONFIRMASI", 
                JOptionPane.OK_CANCEL_OPTION);
        //Jika user memilik OK maka proses delete akan dilakikan
        if (pesan == JOptionPane.OK_OPTION){
            try{
                Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
                String sql = "DELETE FROM pemesan WHERE Nopemesan = '"+nopemesan+"'";
                PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
                p.executeUpdate();
                getData();
                Reset();
                JOptionPane.showMessageDialog(null, "Delete Berhasil");
            }catch(SQLException err){
                JOptionPane.showMessageDialog(null, err.getMessage());
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblpemesan = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtpemesan = new javax.swing.JTextField();
        txtnamapemesan = new javax.swing.JTextField();
        txtalamat = new javax.swing.JTextField();
        txtnotelp = new javax.swing.JTextField();
        txtumur = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblpemesan.setModel(new javax.swing.table.DefaultTableModel(
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
        tblpemesan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblpemesanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblpemesan);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("DATA PEMESAN TIKET KAPAL");

        jLabel2.setText("No Pemesan");

        jLabel3.setText("Nama");

        jLabel4.setText("Alamat");

        jLabel5.setText("No Telp");

        jLabel6.setText("Usia");

        btnsave.setText("SAVE");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnreset.setText("RESET");
        btnreset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnresetActionPerformed(evt);
            }
        });

        btnupdate.setText("UPDATE");
        btnupdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdateActionPerformed(evt);
            }
        });

        btndelete.setText("DELETE");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
            }
        });

        jButton5.setText("HOME");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(633, 633, 633)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtpemesan, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtnamapemesan)
                    .addComponent(txtalamat)
                    .addComponent(txtnotelp, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(txtumur, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btndelete, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnreset, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtpemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnamapemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtnotelp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtumur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
    saveData();        // TODO add your handling code here:
    }//GEN-LAST:event_btnsaveActionPerformed

    private void btnresetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnresetActionPerformed
    Reset();        // TODO add your handling code here:
    }//GEN-LAST:event_btnresetActionPerformed

    private void btnupdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdateActionPerformed
    updateData();        // TODO add your handling code here:
    }//GEN-LAST:event_btnupdateActionPerformed

    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
    deleteData();        // TODO add your handling code here:
    }//GEN-LAST:event_btndeleteActionPerformed

    private void tblpemesanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblpemesanMouseClicked
    dataSelect();        // TODO add your handling code here:
    }//GEN-LAST:event_tblpemesanMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    new Home().setVisible(true); 
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

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
            java.util.logging.Logger.getLogger(datapemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datapemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datapemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datapemesan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datapemesan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblpemesan;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtnamapemesan;
    private javax.swing.JTextField txtnotelp;
    private javax.swing.JTextField txtpemesan;
    private javax.swing.JTextField txtumur;
    // End of variables declaration//GEN-END:variables
}
