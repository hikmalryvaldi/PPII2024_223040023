import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CRUDApp {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField tfId, tfName, tfAge, tfEmail, tfPhone;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    private Connection connection;

    public CRUDApp() {
        initializeDB();
        initializeUI();
        loadData();
    }

    private void initializeDB() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRUD", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage());
            System.exit(1);
        }
    }

    private void initializeUI() {
        frame = new JFrame("CRUD Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Table setup
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Age", "Email", "Phone"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Form inputs
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(new JLabel("ID:"));
        tfId = new JTextField();
        formPanel.add(tfId);
        formPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        formPanel.add(tfName);
        formPanel.add(new JLabel("Age:"));
        tfAge = new JTextField();
        formPanel.add(tfAge);
        formPanel.add(new JLabel("Email:"));
        tfEmail = new JTextField();
        formPanel.add(tfEmail);
        formPanel.add(new JLabel("Phone:"));
        tfPhone = new JTextField();
        formPanel.add(tfPhone);

        // Buttons
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(formPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Event listeners
        btnAdd.addActionListener(e -> addRecord());
        btnUpdate.addActionListener(e -> updateRecord());
        btnDelete.addActionListener(e -> deleteRecord());
        btnRefresh.addActionListener(e -> loadData());

        frame.setVisible(true);
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM CRUD")) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getInt("age"), rs.getString("email"), rs.getString("phone")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error loading data: " + e.getMessage());
        }
    }

    private void addRecord() {
        try (PreparedStatement stmt = connection.prepareStatement("INSERT INTO CRUD (name, age, email, phone) VALUES (?, ?, ?, ?)");) {
            stmt.setString(1, tfName.getText());
            stmt.setInt(2, Integer.parseInt(tfAge.getText()));
            stmt.setString(3, tfEmail.getText());
            stmt.setString(4, tfPhone.getText());
            stmt.executeUpdate();
            loadData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error adding record: " + e.getMessage());
        }
    }

    private void updateRecord() {
        try (PreparedStatement stmt = connection.prepareStatement("UPDATE CRUD SET name=?, age=?, email=?, phone=? WHERE id=?");) {
            stmt.setString(1, tfName.getText());
            stmt.setInt(2, Integer.parseInt(tfAge.getText()));
            stmt.setString(3, tfEmail.getText());
            stmt.setString(4, tfPhone.getText());
            stmt.setInt(5, Integer.parseInt(tfId.getText()));
            stmt.executeUpdate();
            loadData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error updating record: " + e.getMessage());
        }
    }

    private void deleteRecord() {
        String idText = tfId.getText();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter an ID to delete.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete this record?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM CRUD WHERE id=?");) {
                stmt.setInt(1, Integer.parseInt(idText));
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(frame, "Record deleted successfully.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No record found with the given ID.");
                }
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(frame, "Error deleting record: " + e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid ID format. Please enter a numeric ID.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CRUDApp::new);
    }
}
