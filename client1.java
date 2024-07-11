import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class client1 implements ActionListener {
    JFrame f;
    JLabel l1;
    JTextArea ta1;
    JTextField t1;
    JButton b1;
    String str = "";

    client1() {
        f = new JFrame("Client GUI Application");
        l1 = new JLabel("Client is running...");
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

        f.setSize(450, 520);
        f.setLayout(null);
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);

        while (true) {
            try {
                ServerSocket ss = new ServerSocket(11000);
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
                str += "Server : " + (String) dis.readUTF() + "\n";
                ta1.setText(str);
                ss.close();
            } catch (Exception exp) {
                System.out.println(exp);
                t1.setText("");
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        String s1 = t1.getText();
        if (e.getSource() == b1) {
            if (s1.isEmpty()) {
            } else {
                str += "\t\tYou : " + s1 + "\n";
                try {
                    Socket s = new Socket("localhost", 12000);
                    DataOutputStream dout = new DataOutputStream(s.getOutputStream());
                    dout.writeUTF(s1);
                    dout.flush();
                    dout.close();
                    s.close();
                    t1.setText("");
                } catch (Exception exp1) {
                    System.out.println(exp1);
                }
            }
        }
        ta1.setText(str);
    }

    public static void main(String[] args) {
        new client1();
    }
}
