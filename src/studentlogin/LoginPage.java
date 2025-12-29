package studentlogin;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class LoginPage extends JFrame {
    private JTextField idField;
    private JPasswordField passwordField;
    private JButton loginButton, registerButton;

    public LoginPage() {
        setTitle("Student Login");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0xF6F8D5)); // Light Cream Background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Student Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(0x205781)); // Dark Blue
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Form fields
        idField = createTextField();
        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x205781), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));

        addField(panel, "Student ID:", idField, gbc);
        addField(panel, "Password:", passwordField, gbc);

        // Buttons
        loginButton = createButton("Login", new Color(0x4F959D));
        registerButton = createButton("Go to Register", new Color(0x7A73D1));

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(loginButton, gbc);

        gbc.gridy++;
        panel.add(registerButton, gbc);

        // Action Listeners
        loginButton.addActionListener(e -> {
            ResultSet rs = DatabaseManager.loginStudent(idField.getText(), new String(passwordField.getPassword()));
            try {
                if (rs != null && rs.next()) {
                    JOptionPane.showMessageDialog(this, "Login Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    new StudentDashboard(rs.getString("id"));
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect ID or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        registerButton.addActionListener(e -> {
            dispose();
            new RegisterPage();
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x205781), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        field.setPreferredSize(new Dimension(250, 40)); // Increased width and height
        field.setBackground(Color.WHITE);
        return field;
    }

    private void addField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(0x205781)); // Dark Blue Text
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow the field to expand
        gbc.fill = GridBagConstraints.HORIZONTAL; // Use horizontal scaling
        panel.add(field, gbc);
    }
	
    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}

