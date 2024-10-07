import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LayoutLatihan05 extends JFrame {
    
    public LayoutLatihan05() {
        setTitle("Form Biodata");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Judul
        JLabel titleLabel = new JLabel("Form Biodata");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(titleLabel, gbc);
        
        // Nama
        JLabel namaLabel = new JLabel("Nama:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(namaLabel, gbc);
        
        JTextField namaField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(namaField, gbc);
        
        // Nomor HP
        JLabel hpLabel = new JLabel("Nomor HP:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(hpLabel, gbc);
        
        JTextField hpField = new JTextField(10);
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(hpField, gbc);
        
        // Jenis Kelamin
        JLabel jenisKelaminLabel = new JLabel("Jenis Kelamin:");
        gbc.gridx = 1;
        gbc.gridy = 1;
        add(jenisKelaminLabel, gbc);
        
        JPanel jenisKelaminPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        ButtonGroup jenisKelaminGroup = new ButtonGroup();
        JRadioButton lakiLakiRadio = new JRadioButton("Laki-Laki");
        JRadioButton perempuanRadio = new JRadioButton("Perempuan"+"\n");
        jenisKelaminGroup.add(lakiLakiRadio);
        jenisKelaminGroup.add(perempuanRadio);
        jenisKelaminPanel.add(lakiLakiRadio);
        jenisKelaminPanel.add(perempuanRadio);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(jenisKelaminPanel, gbc);
        
        // Warga Negara Asing
        JCheckBox wnaCheckBox = new JCheckBox("Warga Negara Asing");
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(wnaCheckBox, gbc);
        
        // Tombol Simpan
        JButton simpanButton = new JButton("Simpan");
        gbc.gridx = 1;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        add(simpanButton, gbc);

        JTextArea textArea = new JTextArea(5, 20);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LayoutLatihan05());
    }
}