package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IncomeTracker {
    public static void addIncome(String source, double amount, String date) {
        String sql = "INSERT INTO Income (source, amount, date) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseHandler.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, source);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, date);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

