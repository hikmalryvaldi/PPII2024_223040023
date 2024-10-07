import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Tugas extends JFrame {
    private boolean checkBoxSelected;

    public Tugas() {
        this.checkBoxSelected = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel labelNama = new JLabel("Nama:");
        labelNama.setBounds(15, 40, 350, 20);

        JTextField inputNama = new JTextField();
        inputNama.setBounds(15, 60, 350, 30);

        JLabel labelNomor = new JLabel("Nomor:");
        labelNomor.setBounds(15, 100, 350, 20);

        JTextField inputNomor = new JTextField();
        inputNomor.setBounds(15, 120, 350, 30);

        JLabel labelJenisKelamin = new JLabel("Jenis Kelamin");
        labelJenisKelamin.setBounds(15, 160, 350, 20);

        JRadioButton radioButton1 = new JRadioButton("Laki-laki");
        radioButton1.setBounds(15, 180, 350, 30);

        JRadioButton radioButton2 = new JRadioButton("Perempuan");
        radioButton2.setBounds(15, 200, 350, 30);

        ButtonGroup bg = new ButtonGroup();
        bg.add(radioButton1);
        bg.add(radioButton2);

        JLabel labelJenisTabungan = new JLabel("Jenis Tabungan:");
        labelJenisTabungan.setBounds(15, 240, 350, 20);
        String[] tabunganOptions = {"Tabungan A", "Tabungan B", "Tabungan C", "Tabungan D"};

        JList<String> listTabungan = new JList<>(tabunganOptions);
        listTabungan.setBounds(15, 260, 350, 60);

        JLabel labelFrekuensi = new JLabel("Frekuensi Transaksi per Bulan:");
        labelFrekuensi.setBounds(15, 330, 350, 20);

        SpinnerModel modelFrekuensi = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner spinnerFrekuensi = new JSpinner(modelFrekuensi);
        spinnerFrekuensi.setBounds(15, 350, 100, 30);

        JLabel labelTanggalLahir = new JLabel("Tanggal Lahir:");
        labelTanggalLahir.setBounds(15, 390, 350, 20);

        SpinnerModel modelTanggalLahir = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
        JSpinner spinnerTanggalLahir = new JSpinner(modelTanggalLahir);
        spinnerTanggalLahir.setEditor(new JSpinner.DateEditor(spinnerTanggalLahir, "dd/MM/yyyy"));
        spinnerTanggalLahir.setBounds(15, 410, 150, 30);

        JLabel labelPassword = new JLabel("Password:");
        labelPassword.setBounds(15, 450, 350, 20);

        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(15, 470, 350, 30);
        
        JLabel labelConfirmPassword = new JLabel("Confirm Password:");
        labelConfirmPassword.setBounds(15, 510, 350, 20);

        JPasswordField inputConfirmPassword = new JPasswordField();
        inputConfirmPassword.setBounds(15, 530, 350, 30);

        JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
        checkBox.setBounds(15, 570, 350, 20);

        JButton button = new JButton("Simpan");
        button.setBounds(15, 600, 100, 40);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(15, 650, 760, 80);

        JButton resetButton = new JButton("Reset");
        resetButton.setBounds(130, 600, 100, 40);
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inputNama.setText("");
                inputNomor.setText("");
                bg.clearSelection();
                listTabungan.clearSelection();
                spinnerFrekuensi.setValue(1);
                spinnerTanggalLahir.setValue(new Date());
                inputPassword.setText("");
                inputConfirmPassword.setText("");
                checkBox.setSelected(false);
                textArea.setText("");
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem menuReset = new JMenuItem("Reset");
        JMenuItem menuExit = new JMenuItem("Exit");
        menuReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetButton.doClick();
            }
        });
        menuExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menu.add(menuReset);
        menu.add(menuExit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String jenisKelamin = radioButton1.isSelected() ? "Laki-laki" : (radioButton2.isSelected() ? "Perempuan" : "");
                String nama = inputNama.getText();
                String nomor = inputNomor.getText();
                String tabungan = listTabungan.getSelectedValue();
                int frekuensi = (int) spinnerFrekuensi.getValue();
                Date tanggalLahir = (Date) spinnerTanggalLahir.getValue();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String password = new String(inputPassword.getPassword());
                String confirmPassword = new String(inputConfirmPassword.getPassword());

                textArea.append(
                    "Nama          : " + nama + "\n" +
                    "Nomor HP      : " + nomor + "\n" +
                    "Jenis Kelamin : " + jenisKelamin + "\n" +
                    "Jenis Tabungan: " + tabungan + "\n" +
                    "Frekuensi     : " + frekuensi + " kali/bulan\n" +
                    "Tanggal Lahir : " + dateFormat.format(tanggalLahir) + "\n"
                );
                if (password.equals(confirmPassword)) {
                    textArea.append("Password      : Cocok\n");
                } else {
                    textArea.append("Password      : Tidak Cocok\n");
                }
                textArea.append(checkBoxSelected ? "WNA           : Ya\n" : "WNA           : Bukan\n");
                textArea.append("==================\n");
            }
        });

        this.add(labelNama);
        this.add(inputNama);
        this.add(labelNomor);
        this.add(inputNomor);
        this.add(labelJenisKelamin);
        this.add(radioButton1);
        this.add(radioButton2);
        this.add(labelJenisTabungan);
        this.add(listTabungan);
        this.add(labelFrekuensi);
        this.add(spinnerFrekuensi);
        this.add(labelTanggalLahir);
        this.add(spinnerTanggalLahir);
        this.add(labelPassword);
        this.add(inputPassword);
        this.add(labelConfirmPassword);
        this.add(inputConfirmPassword);
        this.add(checkBox);
        this.add(button);
        this.add(resetButton);
        this.add(textArea);

        this.setSize(800, 900);
        this.setLayout(null);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Tugas l = new Tugas();
                l.setVisible(true);
            }
        });
    }
}
