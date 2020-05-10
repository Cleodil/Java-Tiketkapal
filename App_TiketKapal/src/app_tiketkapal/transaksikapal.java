/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_tiketkapal;

import Koneksi.db_koneksi;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;


/**
 *
 * @author ASUS
 */
public class transaksikapal extends javax.swing.JFrame {
    private DefaultTableModel model;
    String notransaksi, tanggal, nopemesan, namapemesan, nokapal, namakapal,
            kelaskapal;
    int hargatiket, jumlahtiket, totalharga;
    private Object JasperFillManager;
    /**
     * Creates new form transaksikapal
     */
    public transaksikapal() {
        initComponents();
        model = new DefaultTableModel();
        tbltransaksi.setModel(model);
        model.addColumn("Notransaksi");
        model.addColumn("Tanggal");
        model.addColumn("Nopemesan");
        model.addColumn("Namapemesan");
        model.addColumn("Nokapal");
        model.addColumn("Namakapal");
        model.addColumn("Kelaskapal");
        model.addColumn("Hargatiket");
        model.addColumn("Jumlahtiket");
        model.addColumn("Totalharga");               
        getData();
        tampil_combo();
        tampil_combo2();
  
    }

    public void loadData(){
        notransaksi     = txtnotransaksi.getText();
        tanggal         = txttanggal.getText();
        nopemesan       = (String) cmboxnopemesan.getSelectedItem();
        namapemesan     = txtnamapemesan.getText();
        nokapal         = (String) cmboxnokapal.getSelectedItem();
        namakapal       = txtnamakapal.getText();
        kelaskapal      = txtkelaskapal.getText();
        hargatiket      = Integer.parseInt(txtharga.getText());
        jumlahtiket     = Integer.parseInt(txtjumlah.getText());
        totalharga      = Integer.parseInt(txttotalharga.getText());
    }
        
    public void getData(){
        //menghapus isi tabel gaji
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        
        try{
            //membuat statement pemanggilan data pada table tblpemesan dari database
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "select * from transaksi";
            ResultSet res = stat.executeQuery(sql);
            
            //penelusuran baris pada tabeltblGaji dari database
            while(res.next()){
                Object[] obj = new Object [10];
                obj[0] = res.getString("Notransaksi");
                obj[1] = res.getString("Tanggal");
                obj[2] = res.getString("Nopemesan");
                obj[3] = res.getString("Namapemesan");
                obj[4] = res.getString("Nokapal");
                obj[5] = res.getString("Namakapal");
                obj[6] = res.getString("Kelaskapal");
                obj[7] = res.getString("Hargatiket");
                obj[8] = res.getString("Jumlahtiket");
                obj[9] = res.getString("Totalharga");
                
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
            String sql = "Insert into kapal(Notransaksi, Tanggal, Nopemesan, Namapemesan, Nokapal, Namakapal, Kelaskapal, Hargatiket, Jumlahtiket, Totalharga)"
                    + "values('"+notransaksi+"','"+tanggal+"','"+nopemesan+"', '"+namapemesan+"', '"+nokapal+"','"+namakapal+"', '"+kelaskapal+"', '"+hargatiket+"', '"+jumlahtiket+"', '"+totalharga+"')";                  
            PreparedStatement p = (PreparedStatement) db_koneksi.getKoneksi().prepareStatement(sql);
            p.executeUpdate();
            getData();
        }catch (SQLException err){
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
    }
    
    public void Reset(){
        notransaksi     = "";
        tanggal         = "";
        nopemesan       = "";
        namapemesan     = "";
        nokapal         = "";
        namakapal       = "";
        kelaskapal      = "";
        hargatiket      = 0;
        jumlahtiket     = 0;
        totalharga      = 0;
        txtnotransaksi.setText(notransaksi);
        txttanggal.setText(tanggal);
        txtnamapemesan.setText(namapemesan);
        txtnamakapal.setText(namakapal);
        txtkelaskapal.setText(kelaskapal);
        txtharga.setText("");
        txtjumlah.setText("");
        txttotalharga.setText("");
    }
    
    public void dataSelect(){
        int i = tbltransaksi.getSelectedRow();
        if (i == -1){
            //tidak ada baris terpilih
            return;           
        }
        txtnotransaksi.setText(""+model.getValueAt(i, 0));
        txttanggal.setText(""+model.getValueAt(i, 1));
        cmboxnopemesan.setSelectedItem(""+model.getValueAt(i, 2));
        txtnamapemesan.setText(""+model.getValueAt(i, 3));
        cmboxnokapal.setSelectedItem(""+model.getValueAt(i, 4));
        txtnamakapal.setText(""+model.getValueAt(i, 5));
        txtkelaskapal.setText(""+model.getValueAt(i, 6));
        txtharga.setText(""+model.getValueAt(i, 7));
        txtjumlah.setText(""+model.getValueAt(i, 8));
        txttotalharga.setText(""+model.getValueAt(i, 9));
    }
    
    public void updateData(){
        /*memanggil class loadData() untuk menentukan kondisi atau variable nip yang akan
        diubah berdasarkan data yang dipilih.
        */
        loadData();
        try{
            Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
            String sql = "UPDATE transaksi SET Tanggal      = '"+tanggal+"',"
                                   +"Nopemesan        = '"+nopemesan+"',"
                                   +"Namapemesan        = '"+namapemesan+"',"
                                   +"Nokapal        = '"+nokapal+"',"
                                   +"Namakapal        = '"+namakapal+"',"
                                   +"Kelaskapal        = '"+kelaskapal+"',"
                                   +"Hargatiket        = '"+hargatiket+"',"
                                   +"Jumlahtiket        = '"+jumlahtiket+"',"
                                   +"Totalharga        = '"+totalharga+"' WHERE Notransaksi = '"+notransaksi+"'";
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
        int pesan = JOptionPane.showConfirmDialog(null,"Anda YaQin Menghapus Data ini"+notransaksi+"?", "KONFIRMASI", 
                JOptionPane.OK_CANCEL_OPTION);
        //Jika user memilik OK maka proses delete akan dilakikan
        if (pesan == JOptionPane.OK_OPTION){
            try{
                Statement stat = (Statement) db_koneksi.getKoneksi().createStatement();
                String sql = "DELETE FROM transaksi WHERE Notransaksi = '"+notransaksi+"'";
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbltransaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnotransaksi = new javax.swing.JTextField();
        txttanggal = new javax.swing.JTextField();
        cmboxnopemesan = new javax.swing.JComboBox<>();
        txtnamapemesan = new javax.swing.JTextField();
        txtnamakapal = new javax.swing.JTextField();
        txtkelaskapal = new javax.swing.JTextField();
        txtharga = new javax.swing.JTextField();
        cmboxnokapal = new javax.swing.JComboBox<>();
        txtjumlah = new javax.swing.JTextField();
        txttotalharga = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btnreset = new javax.swing.JButton();
        btnupdate = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbltransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbltransaksi);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("TRANSAKSI PEMESANAN TIKET KAPAL");

        jLabel2.setText("No Transaksi");

        jLabel3.setText("Tanggal");

        jLabel4.setText("No Pelanggan");

        jLabel5.setText("Nama Pelanggan");

        jLabel6.setText("No Kapal");

        jLabel7.setText("Nama Kapal");

        jLabel8.setText("Kelas Kapal");

        jLabel9.setText("Harga Tiket");

        jLabel10.setText("Jumlah Tiket");

        jLabel11.setText("Total Harga");

        cmboxnopemesan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih No Pelanggan--" }));
        cmboxnopemesan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxnopemesanActionPerformed(evt);
            }
        });

        cmboxnokapal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Pilih Kapal--" }));
        cmboxnokapal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmboxnokapalActionPerformed(evt);
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

        jButton1.setText("HITUNG HARGA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btndelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnreset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnsave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnupdate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(171, 171, 171))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnotransaksi)
                            .addComponent(txttanggal, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(cmboxnokapal, 0, 191, Short.MAX_VALUE)
                            .addComponent(cmboxnopemesan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtnamapemesan)
                            .addComponent(txtnamakapal)
                            .addComponent(txttotalharga, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                            .addComponent(txtjumlah)
                            .addComponent(txtharga)
                            .addComponent(txtkelaskapal))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnotransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txttanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnreset))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cmboxnopemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnupdate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtnamapemesan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmboxnokapal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtnamakapal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtkelaskapal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtjumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txttotalharga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmboxnopemesanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxnopemesanActionPerformed
    tampil();        // TODO add your handling code here:
    }//GEN-LAST:event_cmboxnopemesanActionPerformed

    private void cmboxnokapalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmboxnokapalActionPerformed
    tampil2();        // TODO add your handling code here:
    }//GEN-LAST:event_cmboxnokapalActionPerformed

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int angka1, angka2, hasil;
        angka1 = Integer.parseInt(txtharga.getText());
        angka2 = Integer.parseInt(txtjumlah.getText());

        hasil = angka1 * angka2;

        txttotalharga.setText(Integer.toString(hasil));        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    new Home().setVisible(true); 
    dispose();
    }//GEN-LAST:event_jButton5ActionPerformed
    public void tampil_combo()
    {
        try {
        Connection con = db_koneksi.getKoneksi();
            java.sql.Statement stt = con.createStatement();
        String sql = "select Nopemesan from pemesan order by Nopemesan asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            cmboxnopemesan.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampil_combo2()
    {
        try {
        Connection con = db_koneksi.getKoneksi();
            java.sql.Statement stt = con.createStatement();
        String sql = "select Nokapal from kapal order by Nokapal asc";      // disini saya menampilkan NIM, anda dapat menampilkan
        ResultSet res = stt.executeQuery(sql);                                // yang anda ingin kan
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0] = res.getString(1);
            
            cmboxnokapal.addItem((String) ob[0]);                                      // fungsi ini bertugas menampung isi dari database
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void tampil()
    {
        try {
        Connection con = db_koneksi.getKoneksi();
            java.sql.Statement stt = con.createStatement();
        String sql = "select Nama from pemesan where Nopemesan='"+cmboxnopemesan.getSelectedItem()+"'";  
        ResultSet res = stt.executeQuery(sql);
        
        while(res.next()){
            Object[] ob = new Object[1];
            ob[0]=  res.getString(1);
            
            txtnamapemesan.setText((String) ob[0]);
            
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
    
    public void tampil2()
    {
        try {
        Connection con = db_koneksi.getKoneksi();
            java.sql.Statement stt = con.createStatement();
        String sql = "select Namakapal, Kelas, Hargatiket from kapal where Nokapal='"+cmboxnokapal.getSelectedItem()+"'";  
        ResultSet res = stt.executeQuery(sql);
        
        while(res.next()){
            Object[] ob = new Object[3];
            ob[0]=  res.getString(1);
            ob[1]= res.getString(2);
            ob[2]= res.getString(3);
            
            txtnamakapal.setText((String) ob[0]);
            txtkelaskapal.setText((String) ob[1]);
            txtharga.setText((String) ob[2]);
            
        }
        res.close(); stt.close();
         
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }              
    }
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
            java.util.logging.Logger.getLogger(transaksikapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksikapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksikapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksikapal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksikapal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnreset;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btnupdate;
    private javax.swing.JComboBox<String> cmboxnokapal;
    private javax.swing.JComboBox<String> cmboxnopemesan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbltransaksi;
    private javax.swing.JTextField txtharga;
    private javax.swing.JTextField txtjumlah;
    private javax.swing.JTextField txtkelaskapal;
    private javax.swing.JTextField txtnamakapal;
    private javax.swing.JTextField txtnamapemesan;
    private javax.swing.JTextField txtnotransaksi;
    private javax.swing.JTextField txttanggal;
    private javax.swing.JTextField txttotalharga;
    // End of variables declaration//GEN-END:variables
}
