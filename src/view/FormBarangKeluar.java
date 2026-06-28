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
/**
 *
 * @author najwa
 */
public class FormBarangKeluar extends javax.swing.JFrame {
    Connection conn;
    DefaultTableModel model;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormBarangKeluar.class.getName());
   

    /**
     * Creates new form FormBarangKeluar
     */
    public FormBarangKeluar() {
        initComponents();
        setLocationRelativeTo(null);
        conn = Koneksi.getConnection();
        tampilData();
        resetForm();
    }
    
    private void tampilData() {
    model = new DefaultTableModel();
    model.addColumn("ID Keluar");
    model.addColumn("Tanggal");
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Jumlah Keluar");
    model.addColumn("Keterangan");

    tblKeluar.setModel(model);

    try {
        String sql = "SELECT bk.id_keluar, bk.tanggal, bk.kode_barang, b.nama_barang, bk.jumlah_keluar, bk.keterangan "
                   + "FROM barang_keluar bk "
                   + "JOIN barang b ON bk.kode_barang = b.kode_barang "
                   + "ORDER BY bk.id_keluar DESC";

        PreparedStatement pst = conn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_keluar"),
                rs.getDate("tanggal"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah_keluar"),
                rs.getString("keterangan")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal menampilkan data barang keluar: " + e.getMessage());
    }
}
    
    private void cariData() {
    model = new DefaultTableModel();
    model.addColumn("ID Keluar");
    model.addColumn("Tanggal");
    model.addColumn("Kode Barang");
    model.addColumn("Nama Barang");
    model.addColumn("Jumlah Keluar");
    model.addColumn("Keterangan");

    tblKeluar.setModel(model);

    try {
        String keyword = txtCari.getText().trim();

        String sql = "SELECT bk.id_keluar, bk.tanggal, bk.kode_barang, b.nama_barang, bk.jumlah_keluar, bk.keterangan "
                   + "FROM barang_keluar bk "
                   + "JOIN barang b ON bk.kode_barang = b.kode_barang "
                   + "WHERE bk.id_keluar LIKE ? "
                   + "OR bk.tanggal LIKE ? "
                   + "OR bk.kode_barang LIKE ? "
                   + "OR b.nama_barang LIKE ? "
                   + "OR bk.keterangan LIKE ? "
                   + "ORDER BY bk.id_keluar DESC";

        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, "%" + keyword + "%");
        pst.setString(2, "%" + keyword + "%");
        pst.setString(3, "%" + keyword + "%");
        pst.setString(4, "%" + keyword + "%");
        pst.setString(5, "%" + keyword + "%");

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getInt("id_keluar"),
                rs.getDate("tanggal"),
                rs.getString("kode_barang"),
                rs.getString("nama_barang"),
                rs.getInt("jumlah_keluar"),
                rs.getString("keterangan")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari data barang keluar: " + e.getMessage());
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

        String sql = "SELECT nama_barang, jumlah FROM barang WHERE kode_barang = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, kode);

        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            txtNama.setText(rs.getString("nama_barang"));
            txtStok.setText(String.valueOf(rs.getInt("jumlah")));
            txtJumlah.requestFocus();
        } else {
            JOptionPane.showMessageDialog(this, "Kode barang tidak ditemukan.");
            txtNama.setText("");
            txtStok.setText("");
            txtKode.requestFocus();
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Gagal mencari barang: " + e.getMessage());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblKeluar = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtStok = new javax.swing.JTextField();
        txtKeterangan = new javax.swing.JTextField();
        txtTanggal = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 236));

        tblKeluar.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tblKeluar.setForeground(new java.awt.Color(255, 0, 153));
        tblKeluar.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblKeluar);

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama Barang");

        jLabel3.setText("Stok saat ini");

        jLabel4.setText("Tanggal Keluar");

        jLabel5.setText("Jumlah Keluar");

        jLabel6.setText("Keterangan");

        txtKode.setText("txtKode");
        txtKode.addActionListener(this::txtKodeActionPerformed);

        txtNama.setText("txtNama");

        txtStok.setText("txtStok");

        txtKeterangan.setText("txtKeterangan");

        txtTanggal.setText("txtTanggal");

        txtJumlah.setText("txtJumlah");

        btnSimpan.setForeground(new java.awt.Color(0, 51, 255));
        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnReset.setForeground(new java.awt.Color(0, 51, 255));
        btnReset.setText("Reset");
        btnReset.addActionListener(this::btnResetActionPerformed);

        btnKembali.setForeground(new java.awt.Color(0, 51, 255));
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(this::btnKembaliActionPerformed);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel7.setText("BARANG KELUAR");

        txtCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCariKeyReleased(evt);
            }
        });

        jLabel8.setText("Cari Barang");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnKembali)
                                .addGap(18, 18, 18)
                                .addComponent(btnReset)
                                .addGap(18, 18, 18)
                                .addComponent(btnSimpan)
                                .addGap(22, 22, 22))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jLabel6)
                                .addGap(24, 24, 24)
                                .addComponent(txtKeterangan))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)))
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTanggal)
                                    .addComponent(txtStok, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNama)
                                    .addComponent(txtKode)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtJumlah))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel8)
                                .addGap(3, 3, 3)))
                        .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtStok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtKeterangan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnReset)
                    .addComponent(btnKembali)
                    .addComponent(btnSimpan))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        if (txtNama.getText().trim().isEmpty() ||txtStok.getText().trim().isEmpty()){
            cariBarangByKode();
        }
        if (!validasiInput()) {
            return;
        }

        try {
            String kode = txtKode.getText().trim();
            String tanggal = txtTanggal.getText().trim();
            int jumlahKeluar = Integer.parseInt(txtJumlah.getText().trim());
            String keterangan = txtKeterangan.getText().trim();

            String sqlKeluar = "INSERT INTO barang_keluar (tanggal, kode_barang, jumlah_keluar, keterangan) VALUES (?, ?, ?, ?)";
            PreparedStatement pstKeluar = conn.prepareStatement(sqlKeluar);
            pstKeluar.setString(1, tanggal);
            pstKeluar.setString(2, kode);
            pstKeluar.setInt(3, jumlahKeluar);
            pstKeluar.setString(4, keterangan);
            pstKeluar.executeUpdate();
            
            String sqlUpdateStok = "UPDATE barang SET jumlah = jumlah - ? WHERE kode_barang = ?";
            PreparedStatement pstUpdate = conn.prepareStatement(sqlUpdateStok);
            pstUpdate.setInt(1, jumlahKeluar);
            pstUpdate.setString(2, kode);
            pstUpdate.executeUpdate();

            JOptionPane.showMessageDialog(this, "Barang keluar berhasil disimpan dan stok berkurang.");

            tampilData();
            resetForm();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan barang keluar: " + e.getMessage());
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

    private void txtCariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCariKeyReleased
            // TODO add your handling code here:
        if (txtCari.getText().trim().isEmpty()) {
        tampilData();
        }   else {
            cariData();
        }
    }//GEN-LAST:event_txtCariKeyReleased

    private void txtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKodeActionPerformed
        // TODO add your handling code here:
        cariBarangByKode();
    }//GEN-LAST:event_txtKodeActionPerformed

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
        java.awt.EventQueue.invokeLater(() -> new FormBarangKeluar().setVisible(true));
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
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblKeluar;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtKeterangan;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtStok;
    private javax.swing.JTextField txtTanggal;
    // End of variables declaration//GEN-END:variables

    private void resetForm() {
         txtKode.setText("");
        txtNama.setText("");
        txtStok.setText("");
        txtTanggal.setText(java.time.LocalDate.now().toString());
        txtJumlah.setText("");
        txtKeterangan.setText("");

        txtNama.setEditable(false);
        txtStok.setEditable(false);
        txtTanggal.setEditable(false);

        txtKode.requestFocus();
    }

private boolean validasiInput() {
    if (txtKode.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Kode barang tidak boleh kosong.");
        txtKode.requestFocus();
        return false;
    }

    if (txtNama.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Barang tidak ditemukan. Tekan Enter pada kode barang terlebih dahulu.");
        txtKode.requestFocus();
        return false;
    }

    if (txtStok.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Stok belum tampil. Tekan Enter pada kode barang terlebih dahulu.");
        txtKode.requestFocus();
        return false;
    }

    if (txtTanggal.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Tanggal keluar tidak boleh kosong.");
        txtTanggal.requestFocus();
        return false;
    }

    if (txtJumlah.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Jumlah keluar tidak boleh kosong.");
        txtJumlah.requestFocus();
        return false;
    }

    try {
        int jumlahKeluar = Integer.parseInt(txtJumlah.getText().trim());
        int stok = Integer.parseInt(txtStok.getText().trim());

        if (jumlahKeluar <= 0) {
            JOptionPane.showMessageDialog(this, "Jumlah keluar harus lebih dari 0.");
            txtJumlah.requestFocus();
            return false;
        }

        if (jumlahKeluar > stok) {
            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi. Stok tersedia hanya " + stok + ".");
            txtJumlah.requestFocus();
            return false;
        }

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Jumlah keluar dan stok harus berupa angka.");
        txtJumlah.requestFocus();
        return false;
    }

    return true;
}

}