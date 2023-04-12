package dbproject.springjdbc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

/*
 * Hello world!
 *
 *
 */
public class App{
	public static void main( String[] args ){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
		
		//Get the EmployeeDAO Bean
		App1 obj = ctx.getBean("app", App1.class);
		//obj.dataInsert();
		obj.readData("Amit");
	}
}

class App1 
{   
	public DataSource dataSource;
  	
    	
//    	String URL = "jdbc:mysql://localhost:3306/library"; 
//    	try {
//    		Class.forName("com.mysql.cj.jdbc.Driver");
//    		String DB_User = "root";
//    		String DB_Pass = "admin";
//    		Connection con = DriverManager.getConnection(URL, DB_User, DB_Pass);
//    		Statement stmt = con.createStatement();
//    		String query = "insert into login values('Amit','abcd1234')";
//    		boolean b = stmt.execute(query);
//    		System.out.println(b);
//    		//rs.close();
//    		stmt.close();
//    		con.close();
//    	}catch(Exception e) {
//    		System.out.println(e);
//    	}
    public void setDataSource(DataSource ds) {
		this.dataSource = ds;
	}
    void dataInsert() {
    	String query = "insert into login values('Shyam','abcd1234')";
    	Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement(query);	
			int out = ps.executeUpdate();
			if(out!=0) {
				System.out.println("It's Done");
			}
			else {
				System.out.println("Insert record failed...");
			}
		}catch(Exception e) {System.out.println(e);}
		try {
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    List<libuser> readData(String un) {
    	String query = "select * from login where username='Amit'"; 
    	JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    	List<libuser> lsList = new ArrayList<libuser>();
    	List<Map<String,Object>> lsRows = jdbcTemplate.queryForList(query);
//    	@SuppressWarnings("deprecation")
//		libuser ls = jdbcTemplate.queryForObject(query, new RowMapper <libuser>(){
//    		public libuser mapRow(ResultSet rs, int rowNum) throws SQLException {
//				libuser ls = new libuser();
//				ls.user = rs.getString("username");
//				ls.password = rs.getString("password");
//				System.out.println(ls);
//				return ls;
//    	}});
    	for(Map<String,Object> lsRow : lsRows){
    		libuser ls = new libuser();
			ls.user =String.valueOf(lsRow.get("username")); 
			ls.password = String.valueOf(lsRow.get("password"));
			System.out.println(ls);
		}
    	return lsList;
    }
}

@Configuration
class libuser{
	String user="Amit";
	String password="abcd1234";
	libuser(){
		System.out.println("From libuser Constructor...");
	}
	@Override
	public String toString() {
		return "libuser [user=" + user + ", password=" + password + "]";
	}
}