package view;

import dao.BiodataDao;
import model.Biodata;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class BiodataFrame extends JFrame {
    private JTextField idField, namaField, alamatField, noTelpField;
    private JButton saveButton, updateButton, deleteButton, refreshButton;
    private JTable biodataTable;
    private DefaultTableModel tableModel;
    private BiodataDao biodataDao;

    public BiodataFrame() {
        biodataDao = new BiodataDao();
        initComponents();
        loadData();
    }

    private void initComponents() {
        setTitle("Biodata Management");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("ID"));
        idField = new JTextField();
        panel.add(idField);

        panel.add(new JLabel("Nama"));
        namaField = new JTextField();
        panel.add(namaField);

        panel.add(new JLabel("Alamat"));
        alamatField = new JTextField();
        panel.add(alamatField);

        panel.add(new JLabel("No. Telepon"));
        noTelpField = new JTextField();
        panel.add(noTelpField);

        saveButton = new JButton("Save");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        refreshButton = new JButton("Refresh");

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        biodataTable = new JTable();
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Alamat", "No. Telepon"}, 0);
        biodataTable.setModel(tableModel);
        add(new JScrollPane(biodataTable), BorderLayout.SOUTH);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveBiodata();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBiodata();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBiodata();
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });

        biodataTable.getSelectionModel().addListSelectionListener(event -> {
            if (biodataTable.getSelectedRow() != -1) {
                int selectedRow = biodataTable.getSelectedRow();
                idField.setText(tableModel.getValueAt(selectedRow, 0).toString());
                namaField.setText(tableModel.getValueAt(selectedRow, 1).toString());
                alamatField.setText(tableModel.getValueAt(selectedRow, 2).toString());
                noTelpField.setText(tableModel.getValueAt(selectedRow, 3).toString());
            }
        });
    }

    private void saveBiodata() {
        String id = idField.getText();
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String noTelp = noTelpField.getText();

        Biodata biodata = new Biodata(id, nama, alamat, noTelp);
        try {
            biodataDao.insert(biodata);
            JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
            loadData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data: " + ex.getMessage());
        }
    }

    private void updateBiodata() {
        String id = idField.getText();
        String nama = namaField.getText();
        String alamat = alamatField.getText();
        String noTelp = noTelpField.getText();

        Biodata biodata = new Biodata(id, nama, alamat, noTelp);
        try {
            biodataDao.update(biodata);
            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui.");
            loadData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data: " + ex.getMessage());
        }
    }

    private void deleteBiodata() {
        String id = idField.getText();
        try {
            biodataDao.delete(id);
            JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            loadData();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage());
        }
    }

    private void loadData() {
        try {
            List<Biodata> biodataList = biodataDao.getAll();
            tableModel.setRowCount(0); // Clear existing data
            for (Biodata biodata : biodataList) {
                tableModel.addRow(new Object[]{
                        biodata.getId(),
                        biodata.getNama(),
                        biodata.getAlamat(),
                        biodata.getNoTelp()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data: " + ex.getMessage());
        }
    }

    private void clearFields() {
        idField.setText("");
        namaField.setText("");
        alamatField.setText("");
        noTelpField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BiodataFrame().setVisible(true));
    }
}
