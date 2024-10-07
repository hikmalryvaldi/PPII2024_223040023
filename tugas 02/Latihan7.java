import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Latihan02 extends JFrame{
  private boolean checkBoxSelected;

  public Latihan02(){
    this.checkBoxSelected = false;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel labelNama = new JLabel("Nama:");
    labelNama.setBounds(15,40,350,20);

    JTextField inputNama = new JTextField();
    inputNama.setBounds(15,60,350,30);

    JLabel labelNomor = new JLabel("Nomor:");
    labelNomor.setBounds(15,100,350,20);

    JTextField inputNomor = new JTextField();
    inputNomor.setBounds(15,120,350,30);

    JLabel labelJenisKelamin = new JLabel("Jenis Kelamin");
    labelJenisKelamin.setBounds(15,160,350,20);

    JRadioButton radioButton1 = new JRadioButton("Laki-laki");
    radioButton1.setBounds(15,180,350,30);

    JRadioButton radioButton2 = new JRadioButton("Perempuan");
    radioButton2.setBounds(15,200,350,30);

    ButtonGroup bg = new ButtonGroup();
    bg.add(radioButton1);
    bg.add(radioButton2);

    JCheckBox checkBox = new JCheckBox("Warga Negara Asing");
    checkBox.setBounds(15,240,350,20);

    JButton button = new JButton("Simpan");
    button.setBounds(15,280,100,40);

    JTextArea textArea = new JTextArea("");
    textArea.setBounds(15,330,760,80);

    checkBox.addItemListener(new ItemListener() {
      public void itemStateChanged(ItemEvent e) {
        checkBoxSelected = e.getStateChange()==1;
      }
    });

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String jenisKelamin = "";
        if(radioButton1.isSelected()) {
          jenisKelamin = radioButton1.getText();
        }
        if(radioButton2.isSelected()) {
          jenisKelamin = radioButton2.getText();
        }
          String nama = inputNama.getText();
          String nomor = inputNomor.getText();
          textArea.append(
            "Nama          : " + nama + "\n" +
            "Nomor HP      : " + nomor + "\n" +
            "Jenis Kelamin : " + jenisKelamin + "\n"
            );
            if(checkBoxSelected) {
              textArea.append("WNA           : Ya" + "\n");
            } else {
              textArea.append("WNA           : Bukan" + "\n");
            }
          textArea.append("==================\n");
          inputNama.setText("");
          inputNomor.setText("");
        // if(checkBoxSelected) {
        //   String nama = inputNama.getText();
        //   String nomor = inputNomor.getText();
        //   textArea.append(
        //     "Nama          : " + nama + "\n" +
        //     "Nomor HP      : " + nomor + "\n" +
        //     "Jenis Kelamin : " + jenisKelamin + "\n" +
        //     "WNA           : Ya" + "\n"
        //   );
        //   textArea.append("==================\n");
        //   inputNama.setText("");
        //   inputNomor.setText("");
        // } else {
        //   String nama = inputNama.getText();
        //   String nomor = inputNomor.getText();
        //   textArea.append(
        //     "Nama          : " + nama + "\n" +
        //     "Nomor HP      : " + nomor + "\n" +
        //     "Jenis Kelamin : " + jenisKelamin + "\n" +
        //     "WNA           : Bukan" + "\n"
        //   );
        //   textArea.append("==================\n");
        //   inputNama.setText("");
        //   inputNomor.setText("");
        // }
      }
    });

    this.add(labelNama);
    this.add(inputNama);
    this.add(labelNomor);
    this.add(inputNomor);
    this.add(labelJenisKelamin);
    this.add(radioButton1);
    this.add(radioButton2);
    this.add(checkBox);
    this.add(button);
    this.add(textArea);

    this.setSize(800,500);
    this.setLayout(null);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Latihan02 l = new Latihan02();
        l.setVisible(true);
      }
    });
  }
}
