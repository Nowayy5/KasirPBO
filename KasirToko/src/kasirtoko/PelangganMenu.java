package kasirtoko;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PelangganMenu extends JFrame {
    private JComboBox<String> cmbProduk;
    private JTextField txtJumlah, txtTotal;
    private JButton btnBeli, btnLogout;
    private JTable tableTransaksi;
    private DefaultTableModel model;
    
    // Warna Alfamart
    private final Color WARNA_MERAH = new Color(220, 20, 60);
    private final Color WARNA_MERAH_TUA = new Color(178, 34, 34);
    private final Color WARNA_ORANYE = new Color(255, 140, 0);
    private final Color WARNA_ABU_TERANG = new Color(245, 245, 245);
    private final Color WARNA_HIJAU = new Color(34, 139, 34);
    
    public PelangganMenu() {
        setTitle("MENU PELANGGAN - BELI PRODUK");
        setSize(800, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(WARNA_ABU_TERANG);
        
        // === HEADER PANEL ===
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(WARNA_MERAH);
        headerPanel.setBounds(0, 0, 800, 70);
        headerPanel.setLayout(null);
        
        JLabel lblTitle = new JLabel("🛒 BELANJA PRODUK");
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 20, 400, 30);
        headerPanel.add(lblTitle);
        
        btnLogout = new JButton("🚪 LOGOUT");
        btnLogout.setFont(new Font("Poppins", Font.BOLD, 12));
        btnLogout.setBackground(WARNA_ORANYE);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnLogout.setBounds(650, 20, 120, 35);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerPanel.add(btnLogout);
        
        add(headerPanel);
        
        // === PANEL PEMILIHAN PRODUK ===
        JPanel panelPilih = new JPanel();
        panelPilih.setBackground(Color.WHITE);
        panelPilih.setBounds(30, 85, 740, 120);
        panelPilih.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(WARNA_MERAH, 2),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        panelPilih.setLayout(null);
        
        JLabel lblJudulPilih = new JLabel("🛍️ PILIH PRODUK");
        lblJudulPilih.setFont(new Font("Poppins", Font.BOLD, 14));
        lblJudulPilih.setForeground(WARNA_MERAH);
        lblJudulPilih.setBounds(10, 5, 200, 25);
        panelPilih.add(lblJudulPilih);
        
        // Produk
        JLabel lblProduk = new JLabel("Produk:");
        lblProduk.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblProduk.setBounds(10, 40, 60, 25);
        panelPilih.add(lblProduk);
        
        cmbProduk = new JComboBox<>();
        cmbProduk.setFont(new Font("Poppins", Font.PLAIN, 12));
        cmbProduk.setBounds(80, 40, 400, 25);
        cmbProduk.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelPilih.add(cmbProduk);
        
        // Jumlah
        JLabel lblJumlah = new JLabel("Jumlah:");
        lblJumlah.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblJumlah.setBounds(10, 75, 60, 25);
        panelPilih.add(lblJumlah);
        
        txtJumlah = new JTextField();
        txtJumlah.setFont(new Font("Poppins", Font.PLAIN, 12));
        txtJumlah.setBounds(80, 75, 100, 25);
        txtJumlah.setHorizontalAlignment(JTextField.CENTER);
        panelPilih.add(txtJumlah);
        
        // Info Harga
        JLabel lblInfo = new JLabel("💡 Masukkan jumlah, lalu klik BELI");
        lblInfo.setFont(new Font("Poppins", Font.ITALIC, 11));
        lblInfo.setForeground(new Color(100, 100, 100));
        lblInfo.setBounds(200, 75, 300, 25);
        panelPilih.add(lblInfo);
        
        add(panelPilih);
        
        // === PANEL TOTAL & BUTTON ===
        JPanel panelBayar = new JPanel();
        panelBayar.setBackground(Color.WHITE);
        panelBayar.setBounds(30, 220, 740, 70);
        panelBayar.setBorder(BorderFactory.createLineBorder(WARNA_ORANYE, 2));
        panelBayar.setLayout(null);
        
        JLabel lblTotal = new JLabel("💰 Total Bayar:");
        lblTotal.setFont(new Font("Poppins", Font.BOLD, 14));
        lblTotal.setForeground(WARNA_MERAH_TUA);
        lblTotal.setBounds(20, 20, 150, 30);
        panelBayar.add(lblTotal);
        
        txtTotal = new JTextField();
        txtTotal.setFont(new Font("Poppins", Font.BOLD, 16));
        txtTotal.setForeground(WARNA_HIJAU);
        txtTotal.setBackground(new Color(240, 255, 240));
        txtTotal.setHorizontalAlignment(JTextField.RIGHT);
        txtTotal.setEditable(false);
        txtTotal.setBorder(BorderFactory.createLineBorder(WARNA_HIJAU, 1));
        txtTotal.setBounds(180, 20, 200, 30);
        panelBayar.add(txtTotal);
        
        btnBeli = new JButton("🛒 BELI SEKARANG");
        btnBeli.setFont(new Font("Poppins", Font.BOLD, 14));
        btnBeli.setBackground(WARNA_HIJAU);
        btnBeli.setForeground(Color.WHITE);
        btnBeli.setFocusPainted(false);
        btnBeli.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        btnBeli.setBounds(420, 15, 280, 40);
        btnBeli.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelBayar.add(btnBeli);
        
        add(panelBayar);
        
        // === LABEL RIWAYAT ===
        JLabel lblRiwayat = new JLabel("📋 RIWAYAT PEMBELIAN ANDA");
        lblRiwayat.setFont(new Font("Poppins", Font.BOLD, 16));
        lblRiwayat.setForeground(WARNA_MERAH_TUA);
        lblRiwayat.setBounds(30, 300, 400, 30);
        add(lblRiwayat);
        
        // === TABLE RIWAYAT ===
        String[] kolom = {"ID", "Produk", "Jumlah", "Total", "Tanggal"};
        model = new DefaultTableModel(kolom, 0);
        tableTransaksi = new JTable(model);
        tableTransaksi.setFont(new Font("Poppins", Font.PLAIN, 12));
        tableTransaksi.setRowHeight(30);
        tableTransaksi.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 12));
        tableTransaksi.getTableHeader().setBackground(WARNA_ORANYE);
        tableTransaksi.getTableHeader().setForeground(Color.WHITE);
        tableTransaksi.setSelectionBackground(new Color(255, 240, 220));
        tableTransaksi.setGridColor(new Color(230, 230, 230));
        
        JScrollPane scroll = new JScrollPane(tableTransaksi);
        scroll.setBounds(30, 335, 740, 250);
        scroll.setBorder(BorderFactory.createLineBorder(WARNA_ORANYE, 1));
        add(scroll);
        
        // === ACTION LISTENERS ===
        btnBeli.addActionListener(e -> beliProduk());
        btnLogout.addActionListener(e -> logout());
        
        // Auto format jumlah (hanya angka)
        txtJumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) && c != java.awt.event.KeyEvent.VK_BACK_SPACE) {
                    e.consume();
                }
            }
        });
        
        loadProduk();
        loadTransaksi();
        setVisible(true);
    }
    
    private void loadProduk() {
        try {
            String sql = "SELECT id, nama, harga FROM produk WHERE stok > 0 ORDER BY nama ASC";
            Statement stmt = koneksi.getKoneksi().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            cmbProduk.removeAllItems();
            cmbProduk.addItem("-- Pilih Produk --");
            
            while (rs.next()) {
                String item = rs.getInt("id") + " - " + rs.getString("nama") + 
                             " | Rp " + String.format("%,d", rs.getInt("harga"));
                cmbProduk.addItem(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error load produk: " + e.getMessage());
        }
    }
    
    private void beliProduk() {
        try {
            // Validasi input
            if (cmbProduk.getSelectedIndex() <= 0) {
                JOptionPane.showMessageDialog(this, "⚠️ Silakan pilih produk terlebih dahulu!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            if (txtJumlah.getText().isEmpty() || Integer.parseInt(txtJumlah.getText()) <= 0) {
                JOptionPane.showMessageDialog(this, "⚠️ Masukkan jumlah yang valid!", 
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String selected = cmbProduk.getSelectedItem().toString();
            int idProduk = Integer.parseInt(selected.split(" - ")[0]);
            int jumlah = Integer.parseInt(txtJumlah.getText());
            
            // Cek stok dengan PreparedStatement (lebih aman)
            String sqlCek = "SELECT nama, harga, stok FROM produk WHERE id = ?";
            PreparedStatement psCek = koneksi.getKoneksi().prepareStatement(sqlCek);
            psCek.setInt(1, idProduk);
            ResultSet rs = psCek.executeQuery();
            
            if (rs.next()) {
                String namaProduk = rs.getString("nama");
                int harga = rs.getInt("harga");
                int stok = rs.getInt("stok");
                
                if (jumlah > stok) {
                    JOptionPane.showMessageDialog(this, 
                        "❌ Stok " + namaProduk + " tidak cukup!\nTersedia: " + stok, 
                        "Stok Habis", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                int total = harga * jumlah;
                
                // Update stok
                String sqlUpdate = "UPDATE produk SET stok = stok - ? WHERE id = ?";
                PreparedStatement psUpdate = koneksi.getKoneksi().prepareStatement(sqlUpdate);
                psUpdate.setInt(1, jumlah);
                psUpdate.setInt(2, idProduk);
                psUpdate.executeUpdate();
                
                // Simpan transaksi
                String sqlTrans = "INSERT INTO transaksi (id_produk, jumlah, total) VALUES (?, ?, ?)";
                PreparedStatement psTrans = koneksi.getKoneksi().prepareStatement(sqlTrans);
                psTrans.setInt(1, idProduk);
                psTrans.setInt(2, jumlah);
                psTrans.setInt(3, total);
                psTrans.executeUpdate();
                
                // Tampilkan sukses
                txtTotal.setText("Rp " + String.format("%,d", total));
                JOptionPane.showMessageDialog(this, 
                    "✅ Pembelian berhasil!\n\n📦 Produk: " + namaProduk + 
                    "\n🔢 Jumlah: " + jumlah + 
                    "\n💰 Total: Rp " + String.format("%,d", total),
                    "Sukses", JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh data
                loadProduk();
                loadTransaksi();
                txtJumlah.setText("");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "⚠️ Masukkan angka yang valid!", "Error Input", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void loadTransaksi() {
        model.setRowCount(0);
        try {
            String sql = "SELECT t.id, p.nama, t.jumlah, t.total, t.tanggal " +
                        "FROM transaksi t " +
                        "JOIN produk p ON t.id_produk = p.id " +
                        "ORDER BY t.tanggal DESC";
            Statement stmt = koneksi.getKoneksi().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    rs.getInt("jumlah"),
                    "Rp " + String.format("%,d", rs.getInt("total")),
                    rs.getTimestamp("tanggal")
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "🚪 Yakin ingin logout?", "Konfirmasi", 
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new login();
        }
    }
}