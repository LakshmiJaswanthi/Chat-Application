import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class server1 implements ActionListener {
    JFrame f;
    JLabel l1;
    JTextArea ta1;
    JTextField t1;
    JButton b1;
    String str = "";

    server1() {
        f = new JFrame("Server GUI Application");
        l1 = new JLabel("Server is running...");
        l1.setBounds(20, 0, 400, 40);
        ta1 = new JTextArea();
        ta1.setBounds(20, 40, 400, 370);
        ta1.setEditable(false);
        t1 = new JTextField();
        t1.setBounds(20, 420, 300, 40);
        b1 = new JButton("Send");
        b1.setBounds(330, 420, 90, 40);
        b1.addActionListener(this);

        f.add(l1);
        f.add(ta1);
        f.add(t1);
        f.add(b1);

        f.setSize(460, 520);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        while (true) {
            try {
                ServerSocket ss = new ServerSocket(12000);
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                str += "Client : " + (String) dis.readUTF() + "\n";
                ta1.setText(str);
                ss.close();
            } catch (Exception exp) {
                System.out.println(exp);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String s1 = t1.getText();
        if (e.getSource() == b1) {
            if (s1.isEmpty()) {
                // No need to send a message
            } else {
                str += "\t\tYou : " + s1 + "\n";
                try {
                    Socket s = new Socket("localhost", 11000);
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(s1);
                    dout.flush();
                    dout.close();
                    s.close();
                    t1.setText("");
                } catch (Exception exp1) {
                    System.out.println(exp1);
                    t1.setText("");
                }
            }
        }
        ta1.setText(str);
    }

    public static void main(String[] args) {
        new server1();
    }
}
