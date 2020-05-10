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
public class datakapal extends javax.swing.JFrame {
    private DefaultTableModel model;
    String nokapal, namakapal, kelas;
    int hargatiket;
    /**
     * Creates new form datakapal
     */
    public datakapal() {
        initComponents();
        model = new DefaultTableModel();
        tblkapal.setModel(model);
        model.addColumn("Nokapal");
        model.addColumn("Namakapal");
        model.addColumn("Kelas");
        model.addColumn("Hargatiket");
             
        getData();
    }
    
    public void loadData(){
        nokapal         = txtnokapal.getText();
        namakapal       = txtnamakapal.getText();
        kelas           = (String) cmboxkelas.getSelectedItem();
        hargatiket      = Integer.parseInt(txthargatiket.getText());
    }
    
    public void loadHarga(){
        kelas = "" + cmboxkelas.getSelectedItem();
        switch (kelas){
            case "Pesiar":
                hargatiket   = 1500000;
            break;
            case "Ferry RoRo":
                hargatiket   = 300000;
            break;       
        }
        txthargatiket.setText(""+hargatiket);
    }
    
    public void getData(){
        //menghapus isi tabel gaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            //membuat statement pemanggilan data pada table tblpemesan dari database
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "select * from kapal";
            ResultSet res = stat.executeQuery(sql);
            
            //penelusuran baris pada tabeltblGaji dari database
            while(res.next()){
                Object[] obj = new Object [4];
                obj[0] = res.getString("Nokapal");
                obj[1] = res.getString("Namakapal");
                obj[2] = res.getString("Kelas");
                obj[3] = res.getString("Hargatiket");
                
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
            String sql = "Insert into kapal(Nokapal, Namakapal, Kelas, Hargatiket)"
                    + "values('"+nokapal+"','"+namakapal+"','"+kelas+"', '"+hargatiket+"')";                  
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void Reset(){
        nokapal         = "";
        namakapal       = "";
        kelas           = "";
        hargatiket      = 0;
        txtnokapal.setText(nokapal);
        txtnamakapal.setText(namakapal);
        txthargatiket.setText("");
    }
    
    public void dataSelect(){
        int i = tblkapal.getSelectedRow();
        if (i == -1){
            //tidak ada baris terpilih
            return;           
        }
        txtnokapal.setText(""+model.getValueAt(i, 0));
        txtnamakapal.setText(""+model.getValueAt(i, 1));
        cmboxkelas.setSelectedItem(""+model.getValueAt(i, 2));
        txthargatiket.setText(""+model.getValueAt(i, 3));
    }
    
    public void updateData(){
        /*memanggil class loadData() untuk menentukan kondisi atau variable nip yang akan
        diubah berdasarkan data yang dipilih.
        */
        loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "UPDATE kapal SET Namakapal      = '"+namakapal+"',"
                                   +"Kelas        = '"+kelas+"',"
                                   +"Hargatiket        = '"+hargatiket+"' WHERE Nokapal = '"+nokapal+"'";
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
        int pesan = JOptionPane.showConfirmDialog(null,"Anda YaQin Menghapus Data ini"+nokapal+"?", "KONFIRMASI", 
                JOptionPane.OK_CANCEL_OPTION);
        //Jika user memilik OK maka proses delete akan dilakikan
        if (pesan == JOptionPane.OK_OPTION){
            try{
                Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
                String sql = "DELETE FROM pemesan WHERE Nopemesan = '"+nokapal+"'";
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblkapal = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtnokapal = new javax.swing.JTextField();
        txtnamakapal = new javax.swing.JTextField();
        cmboxkelas = new javax.swing.JComboBox<>();
        txthargatiket = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("KAPAL - KAPALAN");

        jLabel2.setText("No Kapal");

        tblkapal.setModel(new javax.swing.table.DefaultTableModel(
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
        tblkapal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblkapalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblkapal);

        jLabel3.setText("Nama Kapal");

        jLabel4.setText("Kelas");

        jLabel5.setText("Harga Tiket");

        cmboxkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Kelas Kapal--", "Pesiar", "Ferry RoRo" }));
        cmboxkelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxkelasActionPerformed(evt);
            }
        });

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
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnsave)
                        .addGap(18, 18, 18)
                        .addComponent(btnreset)
                        .addGap(18, 18, 18)
                        .addComponent(btnupdate)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtnamakapal, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txthargatiket, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmboxkelas, javax.swing.GroupLayout.Alignment.LEADING, 0, 134, Short.MAX_VALUE))
                            .addComponent(txtnokapal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnokapal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnamakapal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmboxkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txthargatiket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsave)
                    .addComponent(btnreset)
                    .addComponent(btnupdate)
                    .addComponent(btndelete)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void cmboxkelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxkelasActionPerformed
    loadHarga();        // TODO add your handling code here:
    }//GEN-LAST:event_cmboxkelasActionPerformed

    private void tblkapalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblkapalMouseClicked
    dataSelect();        // TODO add your handling code here:
    }//GEN-LAST:event_tblkapalMouseClicked

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
            java.util.logging.Logger.getLogger(datakapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datakapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datakapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datakapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datakapal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cmboxkelas;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblkapal;
    private javax.swing.JTextField txthargatiket;
    private javax.swing.JTextField txtnamakapal;
    private javax.swing.JTextField txtnokapal;
    // End of variables declaration//GEN-END:variables
}
