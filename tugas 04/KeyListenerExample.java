import javax.swing.*;
import java.awt.event.*;


public class KeyListenerExample {

    public static void main(String[] args){
        // Membuat frame
        JFrame frame = new JFrame("KeyListener Example");

        // Membuat label untuk menampilkan pesan
        JLabel label = new JLabel("Tekan tombol pada keyboard");

        // Membuat text field untuk fokus keyboard
        JTextField textField = new JTextField();
        textField.setBounds(50, 100, 200, 30);

        // Menambahkan KeyListener ke text field
        textField.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                label.setText("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            public void keyReleased(KeyEvent e) {
                label.setText("Key Released: " + KeyEvent.getKeyText(e.getKeyCode()));
            }

            public void keyTyped(KeyEvent e) {
                label.setText("Key Typed: " + e.getKeyChar());
            }
        });

        // Menambahkan Komponen ke frame
        frame.add(label);
        frame.add(textField);

        // Setting frame
        frame.setSize(400, 200);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}