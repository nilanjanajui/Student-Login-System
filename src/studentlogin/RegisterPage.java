package studentlogin;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class RegisterPage extends JFrame {
    private JTextField idField, usernameField, nameField, universityField, departmentField, semesterField;
    private JPasswordField passwordField;
    private JButton registerButton, loginButton;

    public RegisterPage() {
        setTitle("Student Registration");
        setSize(700, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Main panel with padding and layout
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(0xF6F8D5)); // Light Cream Background
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Student Registration", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(new Color(0x205781)); // Dark Blue
        panel.add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        // Form fields
        idField = createTextField();
        usernameField = createTextField();
        passwordField = new JPasswordField(15);
        nameField = createTextField();
        universityField = createTextField();
        departmentField = createTextField();
        semesterField = createTextField();

        addField(panel, "Student ID:", idField, gbc);
        addField(panel, "Username:", usernameField, gbc);
        addField(panel, "Password:", passwordField, gbc);
        addField(panel, "Name:", nameField, gbc);
        addField(panel, "University:", universityField, gbc);
        addField(panel, "Department:", departmentField, gbc);
        addField(panel, "Semester:", semesterField, gbc);

        // Buttons
        registerButton = createButton("Register", new Color(0x4F959D)); // Teal
        loginButton = createButton("Go to Login", new Color(0x7A73D1)); // Soft Greenish-Blue

        gbc.gridy++;
        gbc.gridwidth = 2;
        panel.add(registerButton, gbc);

        gbc.gridy++;
        panel.add(loginButton, gbc);

        // Action Listeners
        registerButton.addActionListener(e -> {
            boolean success = DatabaseManager.registerStudent(
                idField.getText(), usernameField.getText(),
                new String(passwordField.getPassword()), nameField.getText(),
                universityField.getText(), departmentField.getText(), semesterField.getText());

            if (success) {
                JOptionPane.showMessageDialog(this, "Registration Successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new LoginPage();
            } else {
                JOptionPane.showMessageDialog(this, "Student ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginButton.addActionListener(e -> {
            dispose();
            new LoginPage();
        });

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // Method to create styled text fields
    private JTextField createTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x205781), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        field.setPreferredSize(new Dimension(250, 30)); // Increased width and height
        field.setBackground(Color.WHITE);
        return field;
    }

    private void addField(JPanel panel, String labelText, JComponent field, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 1;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(new Color(0x205781));
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow the field to expand
        gbc.fill = GridBagConstraints.HORIZONTAL; // Use horizontal scaling
        panel.add(field, gbc);
    }

    // Method to create styled buttons
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
        SwingUtilities.invokeLater(RegisterPage::new);
    }
}


