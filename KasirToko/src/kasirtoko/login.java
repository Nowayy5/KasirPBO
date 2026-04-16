package kasirtoko;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class login extends JFrame {
    private JTextField txtUser;
    private JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel lblUser, lblPass, lblTitle, lblIcon, lblFooter;
    private JPanel panelForm, panelHeader;
    
    // Warna Alfamart
    private final Color WARNA_MERAH = new Color(220, 20, 60);
    private final Color WARNA_MERAH_TUA = new Color(178, 34, 34);
    private final Color WARNA_ORANYE = new Color(255, 140, 0);
    private final Color WARNA_ABU_TERANG = new Color(245, 245, 245);
    private final Color WARNA_ABU_GELAP = new Color(100, 100, 100);
    
    public login() {
        setTitle("LOGIN - APLIKASI KASIR TOKO");
        setSize(450, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(WARNA_ABU_TERANG);
        
        // === HEADER PANEL (Merah) ===
        panelHeader = new JPanel();
        panelHeader.setBackground(WARNA_MERAH);
        panelHeader.setBounds(0, 0, 450, 120);
        panelHeader.setLayout(null);
        
        // Logo/Icon
        lblIcon = new JLabel("🛒", SwingConstants.CENTER);
        lblIcon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 50));
        lblIcon.setForeground(Color.WHITE);
        lblIcon.setBounds(0, 15, 450, 50);
        panelHeader.add(lblIcon);
        
        // Title
        lblTitle = new JLabel("APLIKASI KASIR", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Poppins", Font.BOLD, 20));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBounds(0, 60, 450, 30);
        panelHeader.add(lblTitle);
        
        add(panelHeader);
        
        // === FORM PANEL (Putih) ===
        panelForm = new JPanel();
        panelForm.setBackground(Color.WHITE);
        panelForm.setBounds(40, 140, 370, 320);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(WARNA_MERAH, 3),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        panelForm.setLayout(null);
        
        // Label Form Title
        JLabel lblFormTitle = new JLabel("🔐 SILAKAN LOGIN");
        lblFormTitle.setFont(new Font("Poppins", Font.BOLD, 16));
        lblFormTitle.setForeground(WARNA_MERAH_TUA);
        lblFormTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblFormTitle.setBounds(0, 0, 320, 40);
        panelForm.add(lblFormTitle);
        
        // Username Label & Field
        lblUser = new JLabel("👤 Username");
        lblUser.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblUser.setForeground(WARNA_ABU_GELAP);
        lblUser.setBounds(25, 50, 150, 25);
        panelForm.add(lblUser);
        
        txtUser = new JTextField();
        txtUser.setFont(new Font("Poppins", Font.PLAIN, 13));
        txtUser.setBounds(25, 75, 320, 40);
        txtUser.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtUser.setBackground(new Color(250, 250, 250));
        txtUser.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtUser.setBorder(BorderFactory.createLineBorder(WARNA_ORANYE, 2));
            }
            public void focusLost(FocusEvent e) {
                txtUser.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            }
        });
        panelForm.add(txtUser);
        
        // Password Label & Field
        lblPass = new JLabel("🔒 Password");
        lblPass.setFont(new Font("Poppins", Font.PLAIN, 12));
        lblPass.setForeground(WARNA_ABU_GELAP);
        lblPass.setBounds(25, 125, 150, 25);
        panelForm.add(lblPass);
        
        txtPass = new JPasswordField();
        txtPass.setFont(new Font("Poppins", Font.PLAIN, 13));
        txtPass.setBounds(25, 150, 320, 40);
        txtPass.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
            BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        txtPass.setBackground(new Color(250, 250, 250));
        txtPass.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                txtPass.setBorder(BorderFactory.createLineBorder(WARNA_ORANYE, 2));
            }
            public void focusLost(FocusEvent e) {
                txtPass.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1));
            }
        });
        panelForm.add(txtPass);
        
        // Login Button
        btnLogin = new JButton("🚀 MASUK SEKARANG");
        btnLogin.setFont(new Font("Poppins", Font.BOLD, 13));
        btnLogin.setBackground(WARNA_MERAH);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorder(BorderFactory.createEmptyBorder(12, 0, 12, 0));
        btnLogin.setBounds(25, 210, 320, 45);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLogin.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btnLogin.setBackground(WARNA_MERAH_TUA);
            }
            public void mouseExited(MouseEvent e) {
                btnLogin.setBackground(WARNA_MERAH);
            }
        });
        panelForm.add(btnLogin);
        
        // Info Text
        JLabel lblInfo = new JLabel("💡 Default: admin/admin123 atau budi/budi123");
        lblInfo.setFont(new Font("Poppins", Font.ITALIC, 10));
        lblInfo.setForeground(new Color(150, 150, 150));
        lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblInfo.setBounds(0, 265, 320, 20);
        panelForm.add(lblInfo);
        
        add(panelForm);
        
        // === FOOTER ===
        lblFooter = new JLabel("© 2024 Aplikasi Kasir Toko | Developed with ❤️", SwingConstants.CENTER);
        lblFooter.setFont(new Font("Poppins", Font.PLAIN, 10));
        lblFooter.setForeground(WARNA_ABU_GELAP);
        lblFooter.setBounds(0, 480, 450, 20);
        add(lblFooter);
        
        // === ACTION LISTENER ===
        btnLogin.addActionListener(e -> doLogin());
        
        // Enter key support
        txtUser.addActionListener(e -> doLogin());
        txtPass.addActionListener(e -> doLogin());
        
        // Default focus
        txtUser.requestFocus();
        
        setVisible(true);
    }
    
    private void doLogin() {
        try {
            String username = txtUser.getText().trim();
            String password = new String(txtPass.getPassword());
            
            // Validasi input kosong
            if (username.isEmpty() || password.isEmpty()) {
                showNotification("⚠️ Username dan password tidak boleh kosong!", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Query login
            String sql = "SELECT role FROM users WHERE username=? AND password=?";
            PreparedStatement ps = koneksi.getKoneksi().prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                String role = rs.getString("role");
                
                // Animasi sukses (opsional)
                btnLogin.setText("✅ Loading...");
                btnLogin.setEnabled(false);
                
                // Delay kecil untuk efek
                SwingUtilities.invokeLater(() -> {
                    dispose();
                    if (role.equals("admin")) {
                        new AdminMenu();
                    } else {
                        new PelangganMenu();
                    }
                });
            } else {
                showNotification("❌ Username atau password salah!", JOptionPane.ERROR_MESSAGE);
                txtPass.setText("");
                txtPass.requestFocus();
            }
        } catch (SQLException e) {
            showNotification("❌ Error koneksi database: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Method untuk menampilkan notifikasi dengan styling
    private void showNotification(String message, int messageType) {
        JOptionPane pane = new JOptionPane(message, messageType);
        JDialog dialog = pane.createDialog(this, "Notifikasi");
        dialog.setFont(new Font("Poppins", Font.PLAIN, 12));
        
        // Customisasi tombol OK
        for (Component c : dialog.getComponents()) {
            if (c instanceof JPanel) {
                for (Component comp : ((JPanel) c).getComponents()) {
                    if (comp instanceof JButton) {
                        ((JButton) comp).setFont(new Font("Poppins", Font.BOLD, 11));
                        ((JButton) comp).setBackground(WARNA_ORANYE);
                        ((JButton) comp).setForeground(Color.WHITE);
                    }
                }
            }
        }
        
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Set look and feel (opsional - untuk tampilan lebih modern)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> new login());
    }
}