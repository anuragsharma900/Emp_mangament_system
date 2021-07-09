package employee_pkg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class login implements ActionListener{
	JFrame f;
	JLabel l1, l2;
	TextField t1;
	JPasswordField t2;
	JButton b1;
	JButton b2;
	
	login(){
		f = new JFrame("login");
		f.setLayout(null);
		l1 = new JLabel("Username:");
		l1.setBounds(28, 15, 65, 30);
		f.add(l1);
		l2 = new JLabel("Password:");
		l2.setBounds(28, 52, 100, 30);
		f.add(l2);
		t1 = new TextField();
		t1.setBounds(130, 20, 150, 20);
		f.add(t1);
		t2 = new JPasswordField();
		t2.setBounds(130, 58, 150, 20);
		f.add(t2);
		ImageIcon i1 = new ImageIcon(this.getClass().getResource("/img/login.jpg"));
		Image im = i1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(im);
		JLabel l3 = new JLabel(i3);
		l3.setBounds(290, 10, 100, 80);
		f.add(l3);
		
		b1 = new JButton("Login");
		b1.setBounds(125, 90, 75, 30);
		b1.setBackground(Color.black);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		f.add(b1);
		
		b2 = new JButton("Cancel");
		b2.setBounds(210, 90, 75, 30);
		b2.setBackground(Color.black);
		b2.setForeground(Color.WHITE);
		b2.addActionListener(this);
		f.add(b2);
		
		
		f.setVisible(true);
		f.setSize(450,200);
		f.setLocation(400, 200);
	}
	public void actionPerformed(ActionEvent ae) {
		
		try {
			Conn c1 = new Conn();
			String u = t1.getText();
			String p =  String.valueOf(t2.getPassword());
		
			String q = "select role from login_master where userid =" + u + " and password ='"+ p+ "'";
			ResultSet rs = c1.s.executeQuery(q);
			if(rs.next()) {
				emp_details ee = new emp_details();
				ee.f.setVisible(true);
				f.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Invalid Login");
				f.setVisible(false);
				new front();
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		if(ae.getSource()==b2) {
			f.setVisible(false);
			new front();
			
		}
	}
}

