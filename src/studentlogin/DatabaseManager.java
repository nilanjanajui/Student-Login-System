package studentlogin;

import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:students.db";

    public static void main(String[] args) {
        createTable();
    }

    public static void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                         "id TEXT PRIMARY KEY, " +
                         "username TEXT NOT NULL, " +
                         "password TEXT NOT NULL, " +
                         "name TEXT NOT NULL, " +
                         "university TEXT NOT NULL, " +
                         "department TEXT NOT NULL, " +
                         "semester TEXT NOT NULL)";
            stmt.executeUpdate(sql);
            System.out.println("Database and Table Created Successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean registerStudent(String id, String username, String password, String name, String department, String semester, String year) {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO students VALUES (?, ?, ?, ?, ?, ?, ?)")) {

            stmt.setString(1, id);
            stmt.setString(2, username);
            stmt.setString(3, password);
            stmt.setString(4, name);
            stmt.setString(5, department);
            stmt.setString(6, semester);
            stmt.setString(7, year);
            
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();  // This prints error details in the console
            return false;
        }
    }


    public static ResultSet loginStudent(String id, String password) {
        try {
            Connection conn = DriverManager.getConnection(DB_URL);
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE id = ? AND password = ?");
            stmt.setString(1, id);
            stmt.setString(2, password);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}