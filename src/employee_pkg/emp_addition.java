package employee_pkg;

import java.sql.*;

import javax.swing.JOptionPane;

public class emp_addition {
	protected String first_name;
	protected String last_name;
	protected Date dob;
	protected String email;
	protected int dept_id;
	
	public void add_emp(String f, String l, Date db, String e, int d) {
		this.first_name =f;
		this.last_name= l;
		this.dob = db;
		this.email = e;
		this.dept_id = d;
		
		try {
 			Conn c11 = new Conn();
            c11.s.executeUpdate("INSERT INTO employees(firstname,lastname,dob,email,department_id) " + 
                "VALUES ('" + f + "','" + l + "','" +  db + "','"+ e +"'," + d +")"); 
           
            c11.c.close();
    	 }catch (Exception e1) { 
             //System.err.println("Got an exception! "); 
             //System.err.println(e1.getMessage()); 
    		 JOptionPane.showMessageDialog(null, "Already exist");
         }
		
	}

}
