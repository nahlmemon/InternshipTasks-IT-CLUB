package application;

import java.sql.*;

public class DatabaseHandler {
    private static final String URL = "jdbc:sqlite:SmartFinance.db";

    public static Connection connect() {
        try {
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void createTables() {
        String expenseTable = "CREATE TABLE IF NOT EXISTS Expenses (id INTEGER PRIMARY KEY AUTOINCREMENT, category TEXT, amount REAL, date TEXT)";
        String incomeTable = "CREATE TABLE IF NOT EXISTS Income (id INTEGER PRIMARY KEY AUTOINCREMENT, source TEXT, amount REAL, date TEXT)";

        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(expenseTable);
            stmt.execute(incomeTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
