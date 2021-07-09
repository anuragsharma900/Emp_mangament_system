package employee_pkg;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class front implements ActionListener {
	JFrame f;
	JButton b1;
	JLabel l2;
	front(){
		f = new JFrame("Employee Management System");
		f.setLayout(null);
		
		l2 = new JLabel("Welcome to EMS");
		l2.setBounds(190, 20, 400, 50);
		l2.setFont(new Font("serif", Font.BOLD, 40));
		l2.setForeground(Color.RED);
		f.add(l2);
		
		ImageIcon i1 = new ImageIcon(this.getClass().getResource("/img/welcome.jpg"));
		Image im = i1.getImage().getScaledInstance(500, 400, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(im);
		JLabel l3 = new JLabel(i3);
		l3.setBounds(80, 50, 500, 500);
		f.add(l3);
		
		b1 = new JButton("LogIn");
		b1.setBounds(270, 570, 100, 70);
		b1.setBackground(Color.black);
		b1.setForeground(Color.WHITE);
		b1.addActionListener(this);
		f.add(b1);
		
		f.setVisible(true);
		f.setSize(670,700);
		f.setLocation(400, 100);
	}
	
	public static void main(String[] args) {
		 new front();
	}
		
	public void actionPerformed(ActionEvent ae) {
		
		if(ae.getSource()==b1) {
			f.setVisible(false);
			new login();
		
		}
		
	}
	
}
