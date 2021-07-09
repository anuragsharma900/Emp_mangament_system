package employee_pkg;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.*;

public class emp_details implements ActionListener {
	JFrame f;
	JLabel l3;
	static String hidden_empid;
	JButton b1,b2,b3,b4,b5,b6;
	JPanel MyPanel1;
	JComboBox<String> tdid1 = new JComboBox<String>();
	HashMap<String,Integer> map=new HashMap<String,Integer>();//Creating HashMap    
	HashMap<Integer, String> map2=new HashMap<Integer, String>();
	static JPanel dept_panel = new JPanel();
	static JPanel emp_panel = new JPanel();
	static JPanel ViewEmp_panel = new JPanel();
	DefaultTableModel defaultTableModel;
	//DefaultTableModel defaultTableModel1 = new DefaultTableModel();//creating object of DefaultTableModel
	
	    static String emp_id = "";    
	    static String first_name = "";
	    static String last_name = "";
	    static String dob = "";
	    static String email = "";
	    static String dept = "";
	    static String status = "";
	    static String pass = "";
	JTable table;//Creating object of JTable
	emp_details(){
		
		f = new JFrame("Add Employee details");

		MyPanel1 = new JPanel();
		GridLayout grid = new GridLayout(6,1);
		grid.setVgap(5);
	   	MyPanel1.setLayout(grid);

	   	 b1 = new JButton("Add Dept");designbtn(b1);
	   	 b2 = new JButton("Add/Edit Emp");designbtn(b2);
	   	 b3 = new JButton("Edit Emp");designbtn(b3);
	   	 b4 = new JButton("View/Delete Emp");designbtn(b4);
	   	 //b5 = new JButton("Edit Emp");designbtn(b5);
		 //b6 = new JButton("Create RL");designbtn(b6);
	   	MyPanel1.add(b1);
	   	MyPanel1.add(b2);
	   //	MyPanel1.add(b3);
	   	MyPanel1.add(b4);
		f.getContentPane().add( MyPanel1, "West");   // Add MyPanel1 to West
		ImageIcon i1 = new ImageIcon(this.getClass().getResource("/img/welcome.jpg"));
		Image im = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(im);
		l3 = new JLabel(i3);
		l3.setBounds(80, 50, 500, 500);
		f.add(l3);
		f.setVisible(true);
		f.setSize(500,400);
		f.setLocation(400, 100);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent ae) {

		//Add Department
		
		if(ae.getSource()==b1) {
			
			f.setSize(500,400);
			emp_panel.setVisible(false);
			ViewEmp_panel.setVisible(false);
			dept_panel.removeAll();
			// refresh the panel.
			dept_panel.updateUI();
			dept_panel.setVisible(true);
			l3.setVisible(false);
			//dept_panel = new JPanel(); // the panel is not visible in output
		   
			    JLabel label = new JLabel("Department Name:");
			    JTextField tf = new JTextField(20);
			    tf.setText("enter new dept");
			    JButton send = new JButton("Add");
			    JButton close = new JButton("Close");

			    dept_panel.add(label); // Components Added using Flow Layout
			    dept_panel.add(tf);
			    dept_panel.add(send);
			    dept_panel.add(close);
			//retrieve all department name
        	//Setting the properties of JTable and DefaultTableModel
            defaultTableModel = new DefaultTableModel();
            table = new JTable(defaultTableModel);
            table.setPreferredScrollableViewportSize(new Dimension(300, 200));
            table.setFillsViewportHeight(true);
            dept_panel.add(new JScrollPane(table));
            defaultTableModel.addColumn("Department_ID");
            defaultTableModel.addColumn("Department_Name");
            dept_panel.add(new JScrollPane(table));
            data_dept(defaultTableModel);
            

		             
               send.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	String d = tf.getText();
		        	if(d != null && !d.isEmpty() && d=="enter new dept") {
		        	 try {
		     			Conn c11 = new Conn();
		                c11.s.executeUpdate("INSERT INTO department(department_nm) " + 
		                    "VALUES ('" + d + "')"); 
		               
		             data_dept(defaultTableModel);   
		             c11.c.close();
		        	 }catch (Exception e1) { 
		                 //System.err.println("Got an exception! "); 
		                 //System.err.println(e1.getMessage()); 
		        		 JOptionPane.showMessageDialog(null, "Already exist");
		             }
		        	}else {
		        		JOptionPane.showMessageDialog(null, "Empty Dept column");
		        	}
		        }
		    });
		    
		    
		   
		    
		   
		   
		    close.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		                
		                l3.setVisible(true);
		                f.getContentPane().remove(dept_panel);
		                
		        }
		    });
		    
		    	f.getContentPane().add(dept_panel, "Center");
		    
		}
		
		//View employee
		if(ae.getSource()==b4) {
				f.setSize(800,350);
				dept_panel.setVisible(false);
				emp_panel.setVisible(false);
				l3.setVisible(false);
				ViewEmp_panel.setVisible(true);
				ViewEmp_panel.removeAll();
				// refresh the panel.
				ViewEmp_panel.updateUI();	
				GridBagLayout gg = new GridBagLayout();
				ViewEmp_panel.setLayout(gg);
				
				GridBagConstraints c = new GridBagConstraints();
				
				
				
				c.weightx = 1;
				c.weighty = 1;
				c.fill = GridBagConstraints.HORIZONTAL;
				
				
			    JTextField tf = new JTextField(20);
			    tf.setText("Enter EmpID");
			    JButton search = new JButton("Search");
			    JButton del = new JButton("Delete");
			    JButton update = new JButton("Update");
			    
			   // Components Added using Flow Layout
			    c.gridx = 0;
			    c.gridwidth = 1;
			    c.gridheight = 1;
			    c.gridy = 0;
			    ViewEmp_panel.add(tf,c);
			    c.gridx = 1;
			    c.gridwidth = 1;
			    c.gridy = 0;
			    ViewEmp_panel.add(search,c);
			    c.gridx = 2;
			    c.gridwidth = 1;
			    c.gridy = 0;
			    ViewEmp_panel.add(del,c);
			    c.gridx = 1;
			    c.gridwidth = 1;
			    c.gridy = 1;
			    ViewEmp_panel.add(update,c);
			    
			    c.gridx = 0;
			    c.gridwidth = 3;
			    c.gridy = 2;
			 	defaultTableModel = new DefaultTableModel();
	            table = new JTable(defaultTableModel);
	            table.setPreferredScrollableViewportSize(new Dimension(300, 200));
	            table.setFillsViewportHeight(true);
	            
	            ViewEmp_panel.add(new JScrollPane(table), c);
	           
	            defaultTableModel.addColumn("Emp_ID");
	            defaultTableModel.addColumn("FirstName");
	            defaultTableModel.addColumn("LastName");
	            defaultTableModel.addColumn("D.O.B");
	            defaultTableModel.addColumn("Email");
	            defaultTableModel.addColumn("Dept_ID");
	            defaultTableModel.addColumn("Role");
	            data_emp(defaultTableModel, tf.getText());
	           
	            update.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        		f.setSize(500,400);
			                l3.setVisible(true);
			                hidden_empid = tf.getText();
			                if(tf.getText().equals("Enter EmpID") || tf.getText().equals("")) {
			                	b4.doClick();
			                	JOptionPane.showMessageDialog(null, "Enter EmpID");
			                }else {
			                	l3.setVisible(false);
			                	 b3.doClick();
					                f.setSize(500,400);
					                
					                
					                ViewEmp_panel.removeAll();
					    			// refresh the panel.
					                ViewEmp_panel.updateUI();
			                }
			             
			               
			                
			        }
			    });
	            
	            del.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	try {
			    			Conn c1 = new Conn();
			    			//String sql = "delete from login_master where userid = " + Integer.parseInt(tf.getText()) + "";
			    			//c1.s.executeUpdate(sql);
			    			
			    			String sql1 = "delete from employees where empid = " + Integer.parseInt(tf.getText()) + "";
			    			c1.s.executeUpdate(sql1);
			    			JOptionPane.showMessageDialog(null, "Record Deleted");
			    			data_emp(defaultTableModel, tf.getText());
			     		c1.c.close();
			    		}catch(Exception e1) {
			    			e1.printStackTrace();
			    		}
			                
			        }
			    });
	            
	            search.addActionListener(new ActionListener(){
			        public void actionPerformed(ActionEvent e){
			        	data_emp(defaultTableModel, tf.getText());
			                
			        }
			    });
	            f.getContentPane().add( ViewEmp_panel);
	            
		}
		//Add Employee
		if(ae.getSource()==b2) {
			f.setSize(500,400);
			l3.setVisible(false);
			dept_panel.setVisible(false);
			emp_panel.setVisible(true);
			ViewEmp_panel.setVisible(false);
			emp_panel.removeAll();
			// refresh the panel.
			emp_panel.updateUI();	
			
			GridBagLayout gg = new GridBagLayout();
			
			emp_panel.setLayout(gg);
			
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.HORIZONTAL;

		    JLabel fn = new JLabel("FirstName :");
		    fn.setBorder(new EmptyBorder(0,20,0,0));//top,left,bottom,right
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 0;
		    emp_panel.add(fn,c);
		    JTextField tf1 = new JTextField(12); // accepts upto 10 characters
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 0;
		    emp_panel.add(tf1,c);
		    JLabel l = new JLabel("LastName :");
		    l.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 1;
		    emp_panel.add(l,c);
		    JTextField tl1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 1;
		    emp_panel.add(tl1,c);
		    JLabel db1 = new JLabel("D.O.B: ");
		    db1.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 2;
		    emp_panel.add(db1,c);
		    JTextField tdb1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 2;
		    emp_panel.add(tdb1,c);
		    JLabel e = new JLabel("Email: ");
		    e.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 3;
		    emp_panel.add(e,c);
		    JTextField te1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 3;
		    emp_panel.add(te1,c);
		    JLabel did = new JLabel("Department: ");
		    did.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 4;
		    emp_panel.add(did,c);
		     
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 4;
		    emp_panel.add(tdid1,c);
		    fillcombo();
		    tdid1.setSelectedItem("");
		    JLabel role = new JLabel("Status: ");
		    role.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 5;
		    emp_panel.add(role,c);
		    JComboBox<String> role1 = new JComboBox<String>();
		    role1.addItem("admin");
		    role1.addItem("user");
		    role1.setSelectedItem("");
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 5;
		    emp_panel.add(role1,c);
		    
		    JLabel pwd = new JLabel("Login password: ");
		    role.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 6;
		    emp_panel.add(pwd,c);
		    JTextField word = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 6;
		    emp_panel.add(word,c);
		    
		    JButton close1 = new JButton("Close");
		    c.gridx = 3;
		    c.gridwidth = 1;
		    c.gridy = 8;
		    emp_panel.add(close1,c);
			//retrieve all department name
        	//Setting the properties of JTable and DefaultTableModel
		    JButton add = new JButton("Add");
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 7;
		    emp_panel.add(add ,c);
		    
		    add.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	
		        	
		        	 try {
		     			Conn c11 = new Conn();
		                c11.s.executeUpdate("INSERT INTO employees(firstname,lastname,dob,email, department_id, role, pass) " + 
		                    "VALUES ('" + tf1.getText() + "','" + tl1.getText() + "','" + tdb1.getText() + "', '" + te1.getText() + "','" + map.get(tdid1.getSelectedItem().toString()) + "','" + role1.getSelectedItem().toString() + "','" + word.getText() + "' )");
		                
		                
		              JOptionPane.showMessageDialog(null, "Record Inserted");
		              b2.doClick();
		             data_dept(defaultTableModel);   
		             c11.c.close();
		        	 }catch (Exception e1) { 
		                 //System.err.println("Got an exception! "); 
		                 System.err.println(e1.getMessage()); 
		        		 //JOptionPane.showMessageDialog(null, "Already exist");
		             }
		        	
		        }
		    });
		    
		
		   //f.getContentPane().remove(dept_panel);
	
		    f.getContentPane().add( emp_panel);
		    
		    
		    close1.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        		f.setSize(500,400);
		                l3.setVisible(true);
		                emp_panel.removeAll();
		    			// refresh the panel.
		                emp_panel.updateUI();
		                
		        }
		    });
		}
		
		//Edit Employee
		if(ae.getSource()==b3) {
			f.setSize(500,400);
			l3.setVisible(false);
			dept_panel.setVisible(false);
			emp_panel.setVisible(true);
			ViewEmp_panel.setVisible(false);
			emp_panel.removeAll();
			// refresh the panel.
			emp_panel.updateUI();	
			
			GridBagLayout gg = new GridBagLayout();
			
			emp_panel.setLayout(gg);
			Border blackline = BorderFactory.createTitledBorder(hidden_empid);
			emp_panel.setBorder(blackline);
			GridBagConstraints c = new GridBagConstraints();
			c.weightx = 1;
			c.weighty = 1;
			c.fill = GridBagConstraints.HORIZONTAL;

		    JLabel fn = new JLabel("FirstName :");
		    fn.setBorder(new EmptyBorder(0,20,0,0));//top,left,bottom,right
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 0;
		    emp_panel.add(fn,c);
		    JTextField tf1 = new JTextField(12); // accepts upto 10 characters
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 0;
		    emp_panel.add(tf1,c);
		    JLabel l = new JLabel("LastName :");
		    l.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 1;
		    emp_panel.add(l,c);
		    JTextField tl1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 1;
		    emp_panel.add(tl1,c);
		    JLabel db1 = new JLabel("D.O.B: ");
		    db1.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 2;
		    emp_panel.add(db1,c);
		    JTextField tdb1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 2;
		    emp_panel.add(tdb1,c);
		    JLabel e = new JLabel("Email: ");
		    e.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 3;
		    emp_panel.add(e,c);
		    JTextField te1 = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 3;
		    emp_panel.add(te1,c);
		    JLabel did = new JLabel("Department: ");
		    did.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 4;
		    emp_panel.add(did,c);
		     
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 4;
		    emp_panel.add(tdid1,c);
		    fillcombo();
		    JLabel role = new JLabel("Status: ");
		    role.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 5;
		    emp_panel.add(role,c);
		    JComboBox<String> role1 = new JComboBox<String>();
		    role1.addItem("admin");
		    role1.addItem("user");
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 5;
		    emp_panel.add(role1,c);
		    
		    JLabel pwd = new JLabel("Login password: ");
		    role.setBorder(new EmptyBorder(0,20,0,0));
		    c.gridx = 0;
		    c.gridwidth = 1;
		    c.gridy = 6;
		    emp_panel.add(pwd,c);
		    JTextField word = new JTextField(12); 
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 6;
		    emp_panel.add(word,c);
		    
		    JButton close1 = new JButton("Close");
		    c.gridx = 3;
		    c.gridwidth = 1;
		    c.gridy = 8;
		    emp_panel.add(close1,c);
			//retrieve all department name
        	//Setting the properties of JTable and DefaultTableModel
		    JButton edit = new JButton("Edit");
		    c.gridx = 1;
		    c.gridwidth = 1;
		    c.gridy = 7;
		    emp_panel.add(edit ,c);
		    
		    defaultTableModel = new DefaultTableModel();
            table = new JTable(defaultTableModel);
            //table.setPreferredScrollableViewportSize(new Dimension(300, 200));
            //table.setFillsViewportHeight(true);
            
            //ViewEmp_panel.add(new JScrollPane(table), c);
           
            defaultTableModel.addColumn("Emp_ID");
            defaultTableModel.addColumn("FirstName");
            defaultTableModel.addColumn("LastName");
            defaultTableModel.addColumn("D.O.B");
            defaultTableModel.addColumn("Email");
            defaultTableModel.addColumn("Dept_ID");
            defaultTableModel.addColumn("Role");
            data_emp(defaultTableModel, hidden_empid);
		    tf1.setText(first_name);
		    tl1.setText(last_name);
		    tdb1.setText(dob);
		    te1.setText(email);
		    word.setText(pass);
		    
		    tdid1.setSelectedItem(dept);
		    role1.setSelectedItem(status);
		    edit.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        	
		        	
		        	 try {
		     			Conn c11 = new Conn();
		                c11.s.executeUpdate("INSERT INTO employees(firstname,lastname,dob,email, department_id, role, pass) " + 
		                    "VALUES ('" + tf1.getText() + "','" + tl1.getText() + "','" + tdb1.getText() + "', '" + te1.getText() + "','" + map.get(tdid1.getSelectedItem().toString()) + "','" + role1.getSelectedItem().toString() + "','" + word.getText() + "' )");
		                
		                
		              JOptionPane.showMessageDialog(null, "Record Inserted");
		             data_dept(defaultTableModel);   
		             c11.c.close();
		        	 }catch (Exception e1) { 
		                 //System.err.println("Got an exception! "); 
		                 System.err.println(e1.getMessage()); 
		        		 //JOptionPane.showMessageDialog(null, "Already exist");
		             }
		        	
		        }
		    });
		    
		
		   //f.getContentPane().remove(dept_panel);
	
		    f.getContentPane().add( emp_panel);
		    
		    
		    close1.addActionListener(new ActionListener(){
		        public void actionPerformed(ActionEvent e){
		        		f.setSize(500,400);
		                l3.setVisible(true);
		                Border blackline = BorderFactory.createTitledBorder("");
		    			emp_panel.setBorder(blackline);
		                emp_panel.removeAll();
		    			// refresh the panel.
		                emp_panel.updateUI();
		                
		        }
		    });
		}
		
	}

	public void designbtn(JButton b) {
		b.setBackground(Color.blue);
		b.setForeground(Color.WHITE);
		b.addActionListener(this);
	
	}

	public void data_dept(DefaultTableModel dd) {
		if (dd.getRowCount() > 0) {
		    for (int i = dd.getRowCount() - 1; i > -1; i--) {
		        dd.removeRow(i);
		    }
		}
		
		 try {
 			Conn c1 = new Conn();
 		
 			String q = "select * from department";
 			ResultSet rs = c1.s.executeQuery(q);
 			while (rs.next()) {
 	                //Retrieving details from the database and storing it in the String variables
 	                String dept_id = rs.getString("department_id");
 	                String dept_name = rs.getString("department_nm");
 	                dd.addRow(new Object[]{dept_id, dept_name});//Adding row in Table
 	                
 	               
 			}
 		c1.c.close();
 		}catch(Exception e1) {
 			e1.printStackTrace();
 		}
		
	}
	
	private void fillcombo() {
		
		try {
			Conn c1 = new Conn();
			String sql = " Select * from department";
			ResultSet rs = c1.s.executeQuery(sql);
 			while (rs.next()) {
 	                //Retrieving details from the database and storing it in the String variables
 	                String dept_id = rs.getString("department_id");
 	                String dept_name = rs.getString("department_nm");
 	               map.put(dept_name,Integer.parseInt(dept_id));
 	               map2.put(Integer.parseInt(dept_id), dept_name);//Put elements in Map  
 	               tdid1.addItem(dept_name);//Adding row in Table
 	                
 	               
 			}
 		c1.c.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public void data_emp(DefaultTableModel dd, String val) {
		String s = "Enter EmpID";
		if(val.equals(s)  ) {
			
		
		if (dd.getRowCount() > 0) {
		    for (int i = dd.getRowCount() - 1; i > -1; i--) {
		        dd.removeRow(i);
		    }
		}
		
		 try {
 			Conn c1 = new Conn();
 		
 			String q = "select * from employees";
 			ResultSet rs = c1.s.executeQuery(q);
 			while (rs.next()) {
 	                //Retrieving details from the database and storing it in the String variables
 				String emp_id = rs.getString("empid");    
 				String first_name = rs.getString("firstname");
 				String last_name = rs.getString("lastname");
 				String dob = rs.getString("dob");
 				String email = rs.getString("email");
 				String dept_id = rs.getString("department_id");
 	            String role = rs.getString("role") ; 
 				emp_id = rs.getString("empid");    
 				
 	            dd.addRow(new Object[]{emp_id, first_name, last_name, dob, email, dept_id, role});//Adding row in Table
 	                
 	               
 			}
 			
 		c1.c.close();
 		}catch(Exception e1) {
 			e1.printStackTrace();
 		}
		}else if(val.matches("[0-9]+")){
			if (dd.getRowCount() > 0) {
			    for (int i = dd.getRowCount() - 1; i > -1; i--) {
			        dd.removeRow(i);
			    }
			}
			
			 try {
	 			Conn c1 = new Conn();
	 		
	 			String q = "select * from employees where empid=" + Integer.parseInt(val) + "";
	 			ResultSet rs = c1.s.executeQuery(q);
	 			
	 			if (rs.next() == false) { JOptionPane.showMessageDialog(null, "No record Found, Check ID");
	 			} else { do {
	 			

	 				
	 			
	 	                //Retrieving details from the database and storing it in the String variables
	 				String emp_id = rs.getString("empid");    
	 				
	 				
	 				
	 				
	 				
	 	            //String role = rs.getString("role") ;   
	 	            first_name = rs.getString("firstname");
	 			    last_name = rs.getString("lastname");
	 				dob = rs.getString("dob");
	 				email = rs.getString("email");
	 				dept = rs.getString("department_id");
	 				
	 				status = rs.getString("role") ;   
	 				pass = rs.getString("pass");
	 	            dd.addRow(new Object[]{emp_id, first_name, last_name, dob, email, dept, status});//Adding row in Table
	 	                
	 	               
	 			}while (rs.next()) ;
	 			dept = map2.get(Integer.parseInt(dept));
	 		c1.c.close();
	 		}
	 			}catch(Exception e1) {
	 			e1.printStackTrace();
	 		}
		}else{
			JOptionPane.showMessageDialog(null, "Invalid Empid");
		}
		
	}
}
