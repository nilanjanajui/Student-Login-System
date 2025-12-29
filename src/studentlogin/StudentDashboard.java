package studentlogin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

@SuppressWarnings("serial")
public class StudentDashboard extends JFrame {
	private String studentID;
	private JButton logoutButton;
	private JPanel panel;

	public StudentDashboard(String studentID) {
		this.studentID = studentID;
		setTitle("Student Dashboard");
		setSize(700, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(new BorderLayout());

		panel = new JPanel(new GridBagLayout());
		panel.setBackground(new Color(0xF6F8D5));
		panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.insets = new Insets(20, 5, 5, 5);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel titleLabel = new JLabel("Student Dashboard", SwingConstants.CENTER);
		titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
		titleLabel.setForeground(new Color(0x693382));
		panel.add(titleLabel, gbc);

		gbc.gridwidth = 1;
		gbc.gridy++;

		fetchStudentDetails(gbc);

		// Logout Button
		logoutButton = createButton("Logout", new Color(0xD84040));

		gbc.gridy++;
		gbc.gridx = 0;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.CENTER; // Center alignment
		panel.add(logoutButton, gbc);

		logoutButton.addActionListener(e -> {
			dispose();
			new LoginPage();
		});

		add(panel, BorderLayout.CENTER);
		getContentPane().setBackground(new Color(0xF6F8D5));
		setVisible(true);
	}

	private void fetchStudentDetails(GridBagConstraints gbc) {
		try (Connection conn = DriverManager.getConnection("jdbc:sqlite:students.db");
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?")) {

			stmt.setString(1, studentID);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				gbc.anchor = GridBagConstraints.EAST;

				addTitleAndValue("🆔 Student ID:", rs.getString("id"), gbc);
				addTitleAndValue("👤 Name:", rs.getString("name"), gbc);
				addTitleAndValue("👤 Username:", rs.getString("username"), gbc);
				addTitleAndValue("🏫 University:", rs.getString("university"), gbc);
				addTitleAndValue("📚 Department:", rs.getString("department"), gbc);
				addTitleAndValue("📆 Semester:", rs.getString("semester"), gbc);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addTitleAndValue(String title, String value, GridBagConstraints gbc) {
		JLabel titleLabel = createLabel(title);
		gbc.gridx = 0;
		panel.add(titleLabel, gbc);

		JLabel valueLabel = createLabel(value);
		gbc.gridx = 1;
		panel.add(valueLabel, gbc);
		gbc.gridy++;
	}

	private JLabel createLabel(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial", Font.PLAIN, 20));
		label.setForeground(new Color(0x001F3F));
		return label;
	}

	private JButton createButton(String text, Color color) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setBackground(color);
		button.setForeground(Color.WHITE);
		button.setFocusPainted(false);
		button.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		return button;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new StudentDashboard("123")); // Example Student ID
	}
}
