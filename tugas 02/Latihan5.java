import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Latihan01 extends JFrame {
  public Latihan01() {
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JLabel labelInput = new JLabel("Input Nama: ");
    labelInput.setBounds(130,40,100,20);

    JTextField dataNama = new JTextField();
    dataNama.setBounds(130,60,100,30);

    JLabel labelNomor = new JLabel("Input Nomor: ");
    labelNomor.setBounds(130,100,100,20);

    JTextField dataNomor = new JTextField();
    dataNomor.setBounds(130,120,100,30);

    JButton button = new JButton("Klik");
    button.setBounds(130,160,100,40);

    JTextArea textArea = new JTextArea("");
    textArea.setBounds(100,210,160,160);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String nama = dataNama.getText();
        String nomor = dataNomor.getText();
        textArea.append("Nama: " + nama +"\n"+"Nomor: " + nomor+"\n");
        textArea.append("=================================="+"\n");
        dataNama.setText("");
        dataNomor.setText("");
      }
    });

    this.add(labelInput);
    this.add(dataNama);
    this.add(labelNomor);
    this.add(dataNomor);
    this.add(button);
    this.add(textArea);

    this.setSize(400,500);
    this.setLayout(null);
  }

  public static void main(String[] args) {
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        Latihan01 l = new Latihan01();
        l.setVisible(true);
      }
    });
  }
}