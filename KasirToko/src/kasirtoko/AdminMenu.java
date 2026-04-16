package kasirtoko;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminMenu extends JFrame {
    private JTextField txtNama, txtHarga, txtStok, txtId;
    private JButton btnTambah, btnUpdate, btnHapus, btnRefresh, btnLogout;
    private JTable tableProduk, tableTransaksi;
    private DefaultTableModel modelProduk, modelTransaksi;
    private JScrollPane scrollPane, scrollTransaksi;
    
    // Warna Alfamart
    private final Color WARNA_MERAH = new Color(220, 20, 60);  // Crimson Red
    private final Color WARNA_MERAH_TUA = new Color(178, 34, 34);  // Dark Red
    private final Color WARNA_ORANYE = new Color(255, 140, 0);  // Orange
    private final Color WARNA_ABU_TERANG = new Color(245, 245, 245);
    private final Color WARNA_HIJAU = new Color(34, 139, 34);
    
    public AdminMenu() {
        setTitle("MENU ADMIN - KELOLA PRODUK & TRANSAKSI");
        setSize(1000, 750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        getContentPane().setBackground(WARNA_ABU_TERANG);
        
        // === HEADER PANEL ===
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(WARNA_MERAH);
        headerPanel.setBounds(0, 0, 1000, 70);
        headerPanel.setLayout(null);
        
        JLabel lblTitle = new JLabel("🛒 KELOLA PRODUK & TRANSAKSI");
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 24));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(30, 20, 500, 30);
        headerPanel.add(lblTitle);
        
        btnLogout = new JButton("🚪 LOGOUT");
        btnLogout.setFont(new Font("Poppins", Font.BOLD, 12));
        btnLogout.setBackground(WARNA_ORANYE);
        btnLogout.setForeground(Color.WHITE);
        btnLogout.setFocusPainted(false);
        btnLogout.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));
        btnLogout.setBounds(850, 20, 120, 35);
        btnLogout.setCursor(new Cursor(Cursor.HAND_CURSOR));
        headerPanel.add(btnLogout);
        
        add(headerPanel);
        
        // === PANEL INPUT ===
        JPanel panelInput = new JPanel();
        panelInput.setBackground(Color.WHITE);
        panelInput.setBounds(30, 85, 940, 90);
        panelInput.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(WARNA_MERAH, 2),
            BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panelInput.setLayout(null);
        
        JLabel lblJudulInput = new JLabel("📝 INPUT DATA PRODUK");
        lblJudulInput.setFont(new Font("Poppins", Font.BOLD, 14));
        lblJudulInput.setForeground(WARNA_MERAH);
        lblJudulInput.setBounds(10, 5, 200, 25);
        panelInput.add(lblJudulInput);
        
        // ID
        JLabel lblId = new JLabel("ID:");
        lblId.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblId.setBounds(10, 35, 30, 25);
        panelInput.add(lblId);
        
        txtId = new JTextField();
        txtId.setBounds(45, 35, 70, 25);
        txtId.setEditable(false);
        txtId.setBackground(new Color(240, 240, 240));
        panelInput.add(txtId);
        
        // Nama
        JLabel lblNama = new JLabel("Nama:");
        lblNama.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblNama.setBounds(130, 35, 50, 25);
        panelInput.add(lblNama);
        
        txtNama = new JTextField();
        txtNama.setBounds(180, 35, 180, 25);
        panelInput.add(txtNama);
        
        // Harga
        JLabel lblHarga = new JLabel("Harga:");
        lblHarga.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblHarga.setBounds(380, 35, 50, 25);
        panelInput.add(lblHarga);
        
        txtHarga = new JTextField();
        txtHarga.setBounds(430, 35, 120, 25);
        panelInput.add(txtHarga);
        
        // Stok
        JLabel lblStok = new JLabel("Stok:");
        lblStok.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblStok.setBounds(570, 35, 40, 25);
        panelInput.add(lblStok);
        
        txtStok = new JTextField();
        txtStok.setBounds(610, 35, 100, 25);
        panelInput.add(txtStok);
        
        add(panelInput);
        
        // === PANEL BUTTONS ===
        JPanel panelButton = new JPanel();
        panelButton.setBackground(Color.WHITE);
        panelButton.setBounds(30, 185, 940, 50);
        panelButton.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        panelButton.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        btnTambah = createButton("➕ TAMBAH", WARNA_HIJAU);
        btnUpdate = createButton("✏️ UPDATE", WARNA_ORANYE);
        btnHapus = createButton("🗑️ HAPUS", WARNA_MERAH);
        btnRefresh = createButton("🔄 REFRESH", new Color(70, 130, 180));
        
        panelButton.add(btnTambah);
        panelButton.add(btnUpdate);
        panelButton.add(btnHapus);
        panelButton.add(btnRefresh);
        
        add(panelButton);
        
        // === LABEL PRODUK ===
        JLabel lblProduk = new JLabel("📦 DAFTAR PRODUK");
        lblProduk.setFont(new Font("Poppins", Font.BOLD, 16));
        lblProduk.setForeground(WARNA_MERAH_TUA);
        lblProduk.setBounds(30, 245, 300, 30);
        add(lblProduk);
        
        // === TABLE PRODUK ===
        String[] kolomProduk = {"ID", "Nama", "Harga", "Stok"};
        modelProduk = new DefaultTableModel(kolomProduk, 0);
        tableProduk = new JTable(modelProduk);
        tableProduk.setFont(new Font("Poppins", Font.PLAIN, 12));
        tableProduk.setRowHeight(30);
        tableProduk.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 12));
        tableProduk.getTableHeader().setBackground(WARNA_MERAH);
        tableProduk.getTableHeader().setForeground(Color.WHITE);
        tableProduk.setSelectionBackground(new Color(255, 220, 220));
        tableProduk.setGridColor(new Color(230, 230, 230));
        
        scrollPane = new JScrollPane(tableProduk);
        scrollPane.setBounds(30, 280, 940, 150);
        scrollPane.setBorder(BorderFactory.createLineBorder(WARNA_MERAH, 1));
        add(scrollPane);
        
        // === LABEL TRANSAKSI ===
        JLabel lblTransaksi = new JLabel("🛍️ RIWAYAT TRANSAKSI PEMBELIAN");
        lblTransaksi.setFont(new Font("Poppins", Font.BOLD, 16));
        lblTransaksi.setForeground(WARNA_MERAH_TUA);
        lblTransaksi.setBounds(30, 440, 400, 30);
        add(lblTransaksi);
        
        // === TABLE TRANSAKSI ===
        String[] kolomTransaksi = {"ID", "Produk", "Jumlah", "Total", "Tanggal"};
        modelTransaksi = new DefaultTableModel(kolomTransaksi, 0);
        tableTransaksi = new JTable(modelTransaksi);
        tableTransaksi.setFont(new Font("Poppins", Font.PLAIN, 12));
        tableTransaksi.setRowHeight(30);
        tableTransaksi.getTableHeader().setFont(new Font("Poppins", Font.BOLD, 12));
        tableTransaksi.getTableHeader().setBackground(WARNA_ORANYE);
        tableTransaksi.getTableHeader().setForeground(Color.WHITE);
        tableTransaksi.setSelectionBackground(new Color(255, 240, 220));
        tableTransaksi.setGridColor(new Color(230, 230, 230));
        
        scrollTransaksi = new JScrollPane(tableTransaksi);
        scrollTransaksi.setBounds(30, 475, 940, 220);
        scrollTransaksi.setBorder(BorderFactory.createLineBorder(WARNA_ORANYE, 1));
        add(scrollTransaksi);
        
        // === ACTION LISTENERS ===
        btnTambah.addActionListener(e -> tambahProduk());
        btnUpdate.addActionListener(e -> updateProduk());
        btnHapus.addActionListener(e -> hapusProduk());
        btnRefresh.addActionListener(e -> {
            loadData();
            loadTransaksi();
        });
        btnLogout.addActionListener(e -> logout());
        
        tableProduk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klikTable();
            }
        });
        
        loadData();
        loadTransaksi();
        setVisible(true);
    }
    
    // ✅ METHOD UNTUK MEMBUAT BUTTON DENGAN STYLE
    private JButton createButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Poppins", Font.BOLD, 12));
        btn.setBackground(bgColor);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(130, 35));
        return btn;
    }
    
    private void loadData() {
        modelProduk.setRowCount(0);
        try {
            String sql = "SELECT * FROM produk ORDER BY id ASC";
            Statement stmt = koneksi.getKoneksi().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama"),
                    "Rp " + String.format("%,d", rs.getInt("harga")),
                    rs.getInt("stok")
                };
                modelProduk.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
    private void loadTransaksi() {
        modelTransaksi.setRowCount(0);
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
                modelTransaksi.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void tambahProduk() {
        try {
            if (txtNama.getText().isEmpty() || txtHarga.getText().isEmpty() || txtStok.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String sql = "INSERT INTO produk (nama, harga, stok) VALUES (?,?,?)";
            PreparedStatement ps = koneksi.getKoneksi().prepareStatement(sql);
            ps.setString(1, txtNama.getText());
            ps.setInt(2, Integer.parseInt(txtHarga.getText()));
            ps.setInt(3, Integer.parseInt(txtStok.getText()));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "✅ Data berhasil ditambahkan!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            bersihkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateProduk() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String sql = "UPDATE produk SET nama=?, harga=?, stok=? WHERE id=?";
            PreparedStatement ps = koneksi.getKoneksi().prepareStatement(sql);
            ps.setString(1, txtNama.getText());
            ps.setInt(2, Integer.parseInt(txtHarga.getText()));
            ps.setInt(3, Integer.parseInt(txtStok.getText()));
            ps.setInt(4, Integer.parseInt(txtId.getText()));
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "✅ Data berhasil diupdate!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
            loadData();
            bersihkanForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hapusProduk() {
        try {
            if (txtId.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String sql = "DELETE FROM produk WHERE id=?";
                PreparedStatement ps = koneksi.getKoneksi().prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(txtId.getText()));
                ps.executeUpdate();
                
                JOptionPane.showMessageDialog(this, "✅ Data berhasil dihapus!", "Sukses", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                bersihkanForm();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "❌ Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void klikTable() {
        int row = tableProduk.getSelectedRow();
        if (row >= 0) {
            txtId.setText(modelProduk.getValueAt(row, 0).toString());
            txtNama.setText(modelProduk.getValueAt(row, 1).toString());
            String harga = modelProduk.getValueAt(row, 2).toString().replace("Rp ", "").replace(",", "");
            txtHarga.setText(harga);
            txtStok.setText(modelProduk.getValueAt(row, 3).toString());
        }
    }
    
    private void bersihkanForm() {
        txtId.setText("");
        txtNama.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }
    
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            dispose();
            new login();
        }
    }
}