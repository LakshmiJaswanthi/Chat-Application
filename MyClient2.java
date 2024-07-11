import java.net.*;
import java.io.*;
import java.util.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class MyClient2
{
public static void main(String args[]) throws Exception
{
 
  Socket s=new Socket("192.168.73.3",3333);
  DataInputStream din=new DataInputStream(s.getInputStream());
  DataOutputStream dout=new DataOutputStream(s.getOutputStream());
  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

      JFrame frm;
      JButton b1;
      JTextField tf1;
      JTextArea ta1;
      String a,b;
     
frm=new JFrame("Client");
frm.setVisible(true);
frm.setSize(500,500);
frm.setLayout(new FlowLayout());
frm.getContentPane().setBackground(Color.pink);


tf1=new JTextField(15);
frm.add(tf1);
b1=new JButton("Send");
frm.add(b1);
ta1=new JTextArea(25,25);
frm.add(ta1);

    String str3,str4;
 b1.addActionListener(new ActionListener()
{
             public void actionPerformed(ActionEvent ae)
   {
String str="";
String str2="";
try{

str=tf1.getText().trim();
str2=ta1.getText().trim();
ta1.setText(str2+"\n"+"\t\t"+str);
dout.writeUTF(str);
dout.flush();
}
catch(Exception e){}

   }

});


while(true)
{

str3="";
str4="";
try{
str3=din.readUTF();
str4=ta1.getText();
ta1.setText(str4+"\n"+str3);
}
catch(Exception e){}

}  
 
}
}