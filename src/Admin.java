import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.cj.protocol.Resultset;

public class Admin {
	
	Product product = new Product();
	User user= new User();
//	Shopping shopping = new Shopping();
	PurchaseData purchaseData = new PurchaseData();

	
	
//	method for Admin Register
	public void adminRegister() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("insert into admin(name, email, mobile, password) values(?,?,?,?)");
			
			System.out.println("************Register Admin************");
			System.out.print("Enter Admin name>> ");
			String name = scanner.next();
			System.out.print("Enter Admin Email >> ");
			String email = scanner.next();
			System.out.print("Enter Admin Mobile>> ");
			String mobile = scanner.next();
			System.out.print("Set Pasword>> ");
			String password = scanner.next();
			
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, mobile);
			ps.setString(4, password);

			int i = ps.executeUpdate();
			
			System.out.println("Admin Successfully Registered!!");
			adminMenus(name);
									
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
	}
	
//	method for Admin Login
	public void adminLogin() throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		try {
//			Product product = new Product();

			Scanner scanner  =new Scanner(System.in);
			System.out.println("************Admin Login************");
			System.out.print("Enter Email id>> ");
			String email = scanner.next();
			System.out.print("Enter Pasword>> ");
			String password = scanner.next();
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select * from admin where email=? and password=?");
			ps.setString(1, email);
			ps.setString(2, password);

			ResultSet rs =  ps.executeQuery();
//			System.out.println("rs"+rs);
			
			if(rs.next()) {
				
//				if(email.equals(rs.getString(3)) && password.equals(rs.getString(5))) {
					System.out.println("Admin " +rs.getString(2)+ " Logged In successfully!!!" );
					adminMenus(rs.getString(2));

//				System.out.println("ID="+rs.getInt(1));
//				System.out.println("name="+rs.getString(2));
//				System.out.println("email="+rs.getString(3));
//				System.out.println("mob="+rs.getString(4));
//				System.out.println("Password="+rs.getString(5));
				
				
//				product.getListForAdmin();
				
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
	
	
	public void adminMenus(String name) throws SQLException {
		System.out.println("***************WELCOME "+name+" ADMIN***************");
		System.out.println("Provide Appropriate Input!!(Numeric only)");
		System.out.println("1 To view all products List");
		System.out.println("2 To view all Registered Users");
		System.out.println("3 To view all Purchase History");
		System.out.println("4 To view User wise Purchase History");

		Scanner scanner  =new Scanner(System.in);
		
		int input = scanner.nextInt();
		
		if (input == 1) {
			System.out.println("products List!!");
			product.getListForAdmin();			
		} else if(input == 2) {
			System.out.println("Registered Users List!!");
			user.getList();
			
		}else if(input == 3) {
			System.out.println("Purchase History!!");
//			shopping.getPurchaseRecord();
			purchaseData.getPurchaseRecord();
			
		}else if(input == 4) {
			System.out.println("User Wise Purchase History!!");
			System.out.println("Enter User is>> ");
			int user_id  =scanner.nextInt();
			purchaseData.getPurchaseHistory(user_id);
			
		}else {
			System.out.println("Invalid input");
		}

	}
}
