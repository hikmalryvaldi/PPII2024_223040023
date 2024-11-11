package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    // URL koneksi ke database MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/biodata_db"; 
    private static final String DB_USER = "root"; 
    private static final String DB_PASS = "";     

    // Metode untuk membuat koneksi ke database
    public static Connection connect() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            System.out.println("Koneksi ke database berhasil.");
        } catch (SQLException e) {
            System.out.println("Koneksi ke database gagal.");
            throw e;
        }
        return conn;
    }
}
