import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiDataMahasiswa extends JFrame {
    private JTextField nameField;
    private JSpinner ageSpinner;
    private JComboBox<String> majorComboBox;
    private JRadioButton statusGraduated;
    private JRadioButton statusNotGraduated;
    private ButtonGroup statusGroup;
    private JCheckBox scholarshipCheck;
    private JTextArea notesArea;
    private JSlider ratingSlider;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    public AplikasiDataMahasiswa() {
        setTitle("Aplikasi Data Mahasiswa");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initMenuBar();
        initForm();
        initTable();

        setVisible(true);
    }

    private void initMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitItem);

        JMenu viewMenu = new JMenu("Lihat Data");
        JMenuItem viewDataItem = new JMenuItem("Tampilkan Data Mahasiswa");
        viewDataItem.addActionListener(e -> new DataViewFrame(tableModel));
        viewMenu.add(viewDataItem);

        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
        setJMenuBar(menuBar);
    }

    private void initForm() {
        JPanel formPanel = new JPanel(new GridLayout(8, 2, 5, 5));

        // Name Field
        formPanel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        // Age Spinner
        formPanel.add(new JLabel("Usia:"));
        ageSpinner = new JSpinner(new SpinnerNumberModel(18, 0, 100, 1));
        formPanel.add(ageSpinner);

        // Major ComboBox
        formPanel.add(new JLabel("Jurusan:"));
        majorComboBox = new JComboBox<>(new String[]{"Teknik Informatika", "Sistem Informasi", "Manajemen Informatika"});
        formPanel.add(majorComboBox);

        // Graduation Status Radio Buttons
        formPanel.add(new JLabel("Status Kelulusan:"));
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusGraduated = new JRadioButton("Lulus");
        statusNotGraduated = new JRadioButton("Belum Lulus");
        statusGroup = new ButtonGroup();
        statusGroup.add(statusGraduated);
        statusGroup.add(statusNotGraduated);
        statusPanel.add(statusGraduated);
        statusPanel.add(statusNotGraduated);
        formPanel.add(statusPanel);

        // Scholarship CheckBox
        formPanel.add(new JLabel("Beasiswa:"));
        scholarshipCheck = new JCheckBox("Penerima Beasiswa");
        formPanel.add(scholarshipCheck);

        // Notes TextArea
        formPanel.add(new JLabel("Catatan:"));
        notesArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(notesArea));

        // Rating Slider
        formPanel.add(new JLabel("Penilaian:"));
        ratingSlider = new JSlider(0, 10, 5);
        ratingSlider.setMajorTickSpacing(1);
        ratingSlider.setPaintTicks(true);
        ratingSlider.setPaintLabels(true);
        formPanel.add(ratingSlider);

        // Add Button
        JButton addButton = new JButton("Tambahkan Data");
        addButton.addActionListener(new AddButtonListener());
        formPanel.add(addButton);

        add(formPanel, BorderLayout.NORTH);
    }

    private void initTable() {
        tableModel = new DefaultTableModel(new String[]{"Nama", "Usia", "Jurusan", "Status", "Beasiswa", "Catatan", "Penilaian"}, 0);
        dataTable = new JTable(tableModel);
        add(new JScrollPane(dataTable), BorderLayout.CENTER);
    }

    private class AddButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int age = (int) ageSpinner.getValue();
            String major = (String) majorComboBox.getSelectedItem();
            String status = statusGraduated.isSelected() ? "Lulus" : "Belum Lulus";
            String scholarship = scholarshipCheck.isSelected() ? "Ya" : "Tidak";
            String notes = notesArea.getText();
            int rating = ratingSlider.getValue();

            // Add data to table
            tableModel.addRow(new Object[]{name, age, major, status, scholarship, notes, rating});

            // Clear fields after adding
            nameField.setText("");
            ageSpinner.setValue(18);
            majorComboBox.setSelectedIndex(0);
            statusGroup.clearSelection();
            scholarshipCheck.setSelected(false);
            notesArea.setText("");
            ratingSlider.setValue(5);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AplikasiDataMahasiswa::new);
    }
}

// Kelas tambahan untuk melihat data mahasiswa
class DataViewFrame extends JFrame {
    public DataViewFrame(DefaultTableModel tableModel) {
        setTitle("Data Mahasiswa");
        setSize(500, 400);
        setLocationRelativeTo(null);

        JTable table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        setVisible(true);
    }
}
