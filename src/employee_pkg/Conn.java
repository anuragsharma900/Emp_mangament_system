package employee_pkg;
import java.sql.*;
public class Conn {
	public Connection c;
	public Statement s;
	public Conn() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql:///empdb","root","sahib");
			s = c.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
