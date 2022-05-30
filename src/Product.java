import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;

public class Product {
	int user_id;
	int product_id;
	int quantity;
	int rate;
	int total;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getRate() {
		return rate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public Product() {
	}
	
	public Product(int user_id, int product_id, int rate, int quantity, int total) {
		this.user_id = user_id;
		this.product_id = product_id;
		this.quantity = quantity;
		this.rate = rate;
		this.total = total;
	}

	public void getListForAdmin() throws SQLException {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				
				Scanner scanner  =new Scanner(System.in);
				
				Class.forName("com.mysql.jdbc.Driver");
				con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
				
				ps =  con.prepareStatement("select * from products order by name");
				ResultSet rs =  ps.executeQuery();
				
				System.out.println("************Product List************");
				while(rs.next()) {
					System.out.println("ID: "+rs.getInt(1)+ " Name: "+rs.getString(2)+ " Description: "+rs.getString(3)+ " Price: "+rs.getInt(4)+" Quantity: "+rs.getInt(5) );
					
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
			
			ps =  con.prepareStatement("select * from products order by name");
			ResultSet rs =  ps.executeQuery();
			
			System.out.println("************Product List************");
			while(rs.next()) {
				System.out.println("ID: "+rs.getInt(1)+ " Name: "+rs.getString(2)+ " Description: "+rs.getString(3)+ " Price: "+rs.getInt(4));
				
			}
						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
	}
	
//	method to get product details by id
	public HashMap getProductInfo(int id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		HashMap map  =new HashMap<>();
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select * from products where id=?");
			ps.setInt(1, id);
			ResultSet rs =  ps.executeQuery();
			
//			System.out.println("************Product List************");
			while(rs.next()) {
//				System.out.println("ID: "+rs.getInt(1)+ " Name: "+rs.getString(2)+ " Description: "+rs.getString(3)+ " Price: "+rs.getInt(4));
				map.put("id", rs.getInt(1));
				map.put("name", rs.getString(2));
				map.put("desc", rs.getString(3));
				map.put("price", rs.getInt(4));
			}
						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
		return map;
		
	}
	
}
