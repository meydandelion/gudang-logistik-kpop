/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import config.Koneksi;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

/**
 *
 * @author najwa
 */
public class FormBarangMasuk extends javax.swing.JFrame {
    Connection conn;
    DefaultTableModel model;
    JDateChooser dcTanggal;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormBarangMasuk.class.getName());

    /**
     * Creates new form FormBarangMasuk
     */
    public FormBarangMasuk() {
        initComponents();
        setLocationRelativeTo(null);
        conn = Koneksi.getConnection();
        buatKalenderTanggal();
        tampilData();
        resetForm();
    }
    
    private void tampilData() {
    model = new DefaultTableModel();
    model.addColumn("ID Masuk");
    model.addColumn("Tanggal");
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Jumlah Masuk");
    model.addColumn("Keterangan");

    tblMasuk.setModel(model);

    try {
        String sql = "SELECT bm.id_masuk, bm.tanggal, bm.kode_barang, b.nama_barang, bm.jumlah_masuk, bm.keterangan "
                   + "FROM barang_masuk bm "
                   + "JOIN barang b ON bm.kode_barang = b.kode_barang "
                   + "ORDER BY bm.id_masuk DESC";

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_masuk"),
                rs.getDate("tanggal"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah_masuk"),
                rs.getString("keterangan")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data barang masuk: " + e.getMessage());
    }
}
    
    private void cariData() {
    model = new DefaultTableModel();
    model.addColumn("ID Masuk");
    model.addColumn("Tanggal");
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Jumlah Masuk");
    model.addColumn("Keterangan");

    tblMasuk.setModel(model);

    try {
        String keyword = txtCari.getText().trim();

        String sql = "SELECT bm.id_masuk, bm.tanggal, bm.kode_barang, b.nama_barang, bm.jumlah_masuk, bm.keterangan "
                   + "FROM barang_masuk bm "
                   + "JOIN barang b ON bm.kode_barang = b.kode_barang "
                   + "WHERE bm.id_masuk LIKE ? "
                   + "OR bm.tanggal LIKE ? "
                   + "OR bm.kode_barang LIKE ? "
                   + "OR b.nama_barang LIKE ? "
                   + "OR bm.keterangan LIKE ? "
                   + "ORDER BY bm.id_masuk DESC";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        pst.setString(3, "%" + keyword + "%");
        pst.setString(4, "%" + keyword + "%");
        pst.setString(5, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_masuk"),
                rs.getDate("tanggal"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah_masuk"),
                rs.getString("keterangan")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari data barang masuk: " + e.getMessage());
    }
}
    
    private void cariBarangByKode() {
    try {
        String kode = txtKode.getText().trim();

        if (kode.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kode barang terlebih dahulu.");
            txtKode.requestFocus();
            return;
        }

        String sql = "SELECT nama_barang FROM barang WHERE kode_barang = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, kode);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            txtNama.setText(rs.getString("nama_barang"));
            dcTanggal.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Kode barang tidak ditemukan.");
            txtNama.setText("");
            txtKode.requestFocus();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari barang: " + e.getMessage());
    }
}
    private void buatKalenderTanggal() {
    dcTanggal = new JDateChooser();
    dcTanggal.setDateFormatString("yyyy-MM-dd");

    dcTanggal.setBounds(
        txtTanggal.getX(),
        txtTanggal.getY(),
        txtTanggal.getWidth(),
        txtTanggal.getHeight()
    );

    txtTanggal.setVisible(false);

    getContentPane().add(dcTanggal);
    getContentPane().revalidate();
    getContentPane().repaint();
}
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMasuk = new javax.swing.JTable();
        btnKembali = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 236));

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Tanggal Masuk ");

        jLabel4.setText("Jumlah Masuk");

        txtKode.setText("txtKode");
        txtKode.addActionListener(this::txtKodeActionPerformed);

        txtNama.setText("txtNama");

        txtTanggal.setText("txtTanggal");

        txtKeterangan.setText("txtKeterangan");

        jLabel5.setText("Keterangan ");

        txtJumlah.setText("txtJumlah");
        txtJumlah.addActionListener(this::txtJumlahActionPerformed);

        btnSimpan.setForeground(new java.awt.Color(0, 51, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnReset.setForeground(new java.awt.Color(0, 51, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        tblMasuk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID Masuk", "Tanggal", "Kode Barang", "Nama Barang", "Jumlah Masuk", "Keterangan"
            }
        ));
        jScrollPane1.setViewportView(tblMasuk);

        btnKembali.setForeground(new java.awt.Color(0, 51, 255));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("BARANG MASUK");

        txtCari.addActionListener(this::txtCariActionPerformed);
        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jLabel7.setText("Cari Riwayat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtNama))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtKeterangan)
                                    .addComponent(txtJumlah)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnKembali)
                        .addGap(18, 18, 18)
                        .addComponent(btnReset)
                        .addGap(18, 18, 18)
                        .addComponent(btnSimpan)
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(26, 26, 26)
                        .addComponent(txtKode))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTanggal))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(21, 21, 21))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSimpan)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnKembali)
                        .addComponent(btnReset)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
        cariBarangByKode();
    }//GEN-LAST:event_txtKodeActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (txtNama.getText().trim().isEmpty()){
            cariBarangByKode();
        }
        
        if (!validasiInput()) {
        return;
       }
        
        try {
            String kode = txtKode.getText().trim();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = sdf.format(dcTanggal.getDate());

            int jumlahMasuk = Integer.parseInt(txtJumlah.getText().trim());
            String keterangan = txtKeterangan.getText().trim();

            String sqlMasuk = "INSERT INTO barang_masuk (tanggal, kode_barang, jumlah_masuk, keterangan) VALUES (?, ?, ?, ?)";
            PreparedStatement pstMasuk = conn.prepareStatement(sqlMasuk);
            pstMasuk.setString(1, tanggal);
            pstMasuk.setString(2, kode);
            pstMasuk.setInt(3, jumlahMasuk);
            pstMasuk.setString(4, keterangan);
            pstMasuk.executeUpdate();

            String sqlUpdateStok = "UPDATE barang SET jumlah = jumlah + ? WHERE kode_barang = ?";
            PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateStok);
            pstUpdate.setInt(1, jumlahMasuk);
            pstUpdate.setString(2, kode);
            pstUpdate.executeUpdate();

            JOptionPane.showMessageDialog(this, "Barang masuk berhasil disimpan dan stok bertambah.");

            tampilData();
            resetForm();

        }  catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menyimpan barang masuk: " + e.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
        // TODO add your handling code here:
         if (txtCari.getText().trim().isEmpty()) {
            tampilData();
         } else {
            cariData();
      }
    }//GEN-LAST:event_txtCariKeyReleased

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormBarangMasuk().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMasuk;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
        txtKode.setText("");
        txtNama.setText("");
        dcTanggal.setDate(null);
        txtJumlah.setText("");
        txtKeterangan.setText("");

        txtNama.setEditable(false);
        txtKode.requestFocus();
    }

   private boolean validasiInput() {
    
    if (txtKode.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kode barang tidak boleh kosong.");
        txtKode.requestFocus();
        return false;
    }

    if (txtNama.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Barang tidak ditemukan. Tekan enter terlebih.");
        txtKode.requestFocus();
        return false;
    }
    
    if (dcTanggal.getDate() == null) {
        JOptionPane.showMessageDialog(this, "Tanggal masuk harus dipilih.");
        dcTanggal.requestFocus();
        return false;
    }   

    if (txtJumlah.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Jumlah masuk tidak boleh kosong.");
        txtJumlah.requestFocus();
        return false;
    }

    try {
        int jumlah = Integer.parseInt(txtJumlah.getText().trim());

        if (jumlah <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah masuk harus lebih dari 0.");
            txtJumlah.requestFocus();
            return false;
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah masuk harus berupa angka.");
        txtJumlah.requestFocus();
        return false;
    }

    return true;
  }
}