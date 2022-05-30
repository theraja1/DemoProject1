import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class User {
//	Product product = new Product();
	Shopping shopping = new Shopping();
	
//	method for user Register
	public void userRegister() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("insert into users(name, email, mobile, password) values(?,?,?,?)");
			
			System.out.println("************Register User************");
			System.out.print("Enter User name>> ");
			String name = scanner.next();
			System.out.print("Enter User Email >> ");
			String email = scanner.next();
			System.out.print("Enter User Mobile>> ");
			String mobile = scanner.next();
			System.out.print("Set Pasword>> ");
			String password = scanner.next();
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, password);

			int i = ps.executeUpdate();
			
			System.out.println("User Successfully Registered!!");
			
//			Directed to Login method
			userLogin();			
			
						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
	}
	
//	method for user Login
	public void userLogin() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Scanner scanner  =new Scanner(System.in);
			System.out.println("************User Login************");
			System.out.print("Enter Email id>> ");
			String email = scanner.next();
			System.out.print("Enter Pasword>> ");
			String password = scanner.next();
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select * from users where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs =  ps.executeQuery();
//			System.out.println("rs"+rs);
			
			if(rs.next()) {
				
//				if(email.equals(rs.getString(3)) && password.equals(rs.getString(5))) {
					System.out.println(rs.getString(2)+ " Logged In successfully!!!" );
//				System.out.println("ID="+rs.getInt(1));
//				System.out.println("name="+rs.getString(2));
//				System.out.println("email="+rs.getString(3));
//				System.out.println("mob="+rs.getString(4));
//				System.out.println("Password="+rs.getString(5));
				
//				after login user will be redirected to shopping page.
				shopping.shopping(rs.getInt(1));
			}else {
					System.out.println("Invalid Credentials" );
					
			}
				
			
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}

		
	}
	
	public void getList() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select * from users");
			ResultSet rs =  ps.executeQuery();
			
			System.out.println("************Users List************");
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt(1)+ " Name: "+rs.getString(2)+ " Email: "+rs.getString(3)+ " Mobile: "+rs.getString(4)+ " Password: "+rs.getString(5));
				
			}
						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
	}
	

}
