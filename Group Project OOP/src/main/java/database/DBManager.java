package database;

import java.sql.*;

public class DBManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/Tetris";
    private static final String USER = "postgres";
    private static final String PASS = "ANUARKZ08";

    public static boolean checkConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
                if (conn != null) {
                    System.out.println("✅ SUCCESS: Database connected!");
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("❌ CONNECTION ERROR: Could not connect to the game database.");
            System.err.println("Error message: " + e.getMessage());
        }
        return false;
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public static boolean registerUser(String username, String password) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.out.println("⚠️ Username is already taken.");
            } else {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static int loginUser(String username, String password) {
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void saveScore(int userId, String mode, int score, double timeTaken) {
        String sql = "INSERT INTO leaderboards (user_id, mode, score, time_taken) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, mode);
            pstmt.setInt(3, score);
            pstmt.setDouble(4, timeTaken);
            pstmt.executeUpdate();
            System.out.println("✅ SCORE SAVED TO DATABASE!");
        } catch (SQLException e) {
            System.err.println("❌ Error saving score: " + e.getMessage());
        }
    }

    public static void showLeaderboard() {
        String sql = "SELECT u.username, l.mode, l.score FROM leaderboards l " +
                     "JOIN users u ON l.user_id = u.id ORDER BY l.score DESC LIMIT 5";
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n=== LEADERBOARD ===");
            while (rs.next()) {
                System.out.println(rs.getString("username") + ": " + rs.getInt("score"));
            }
            System.out.println("===================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}