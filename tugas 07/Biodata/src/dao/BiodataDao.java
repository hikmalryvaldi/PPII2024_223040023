package dao;

import db.MySqlConnection;
import model.Biodata;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BiodataDao {

    // Menambahkan data baru ke tabel biodata
    public void insert(Biodata biodata) {
        String sql = "INSERT INTO biodata (id, nama, alamat, noTelp) VALUES (?, ?, ?, ?)";
        try (Connection conn = MySqlConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, biodata.getId());
            stmt.setString(2, biodata.getNama());
            stmt.setString(3, biodata.getAlamat());
            stmt.setString(4, biodata.getNoTelp());
            stmt.executeUpdate();
            System.out.println("Data berhasil disimpan.");
        } catch (SQLException e) {
            System.out.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    // Memperbarui data berdasarkan ID di tabel biodata
    public void update(Biodata biodata) {
        String sql = "UPDATE biodata SET nama = ?, alamat = ?, noTelp = ? WHERE id = ?";
        try (Connection conn = MySqlConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, biodata.getNama());
            stmt.setString(2, biodata.getAlamat());
            stmt.setString(3, biodata.getNoTelp());
            stmt.setString(4, biodata.getId());
            stmt.executeUpdate();
            System.out.println("Data berhasil diperbarui.");
        } catch (SQLException e) {
            System.out.println("Gagal memperbarui data: " + e.getMessage());
        }
    }

    // Menghapus data berdasarkan ID di tabel biodata
    public void delete(String id) {
        String sql = "DELETE FROM biodata WHERE id = ?";
        try (Connection conn = MySqlConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
            System.out.println("Data berhasil dihapus.");
        } catch (SQLException e) {
            System.out.println("Gagal menghapus data: " + e.getMessage());
        }
    }

    // Mengambil semua data dari tabel biodata
    public List<Biodata> getAll() {
        List<Biodata> list = new ArrayList<>();
        String sql = "SELECT * FROM biodata";
        try (Connection conn = MySqlConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Biodata biodata = new Biodata();
                biodata.setId(rs.getString("id"));
                biodata.setNama(rs.getString("nama"));
                biodata.setAlamat(rs.getString("alamat"));
                biodata.setNoTelp(rs.getString("noTelp"));
                list.add(biodata);
            }
            System.out.println("Data berhasil diambil.");
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data: " + e.getMessage());
        }
        return list;
    }

    // Mengambil data berdasarkan ID
    public Biodata getById(String id) {
        Biodata biodata = null;
        String sql = "SELECT * FROM biodata WHERE id = ?";
        try (Connection conn = MySqlConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    biodata = new Biodata();
                    biodata.setId(rs.getString("id"));
                    biodata.setNama(rs.getString("nama"));
                    biodata.setAlamat(rs.getString("alamat"));
                    biodata.setNoTelp(rs.getString("noTelp"));
                }
            }
            System.out.println("Data berhasil diambil berdasarkan ID.");
        } catch (SQLException e) {
            System.out.println("Gagal mengambil data berdasarkan ID: " + e.getMessage());
        }
        return biodata;
    }
}
