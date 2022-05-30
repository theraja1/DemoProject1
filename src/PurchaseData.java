import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class PurchaseData {
	
	
	
//	method to Insert product shopping details into db
	public void addShoppingInfo(int user_id, ArrayList<Product> cartList) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null,ps1 = null;
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			
			for (Product product : cartList) {
				System.out.println("Records Updated!!!!!!!!!!");
//				System.out.println("Product id: " + product.getProduct_id() + " Rate: " + product.getRate()+ " Quantity: " + product.getQuantity() + " Total: " + product.getTotal());
				ps =  con.prepareStatement("insert into purchase_record(user_id,product_id,quantity,total) values(?,?,?,?)");
				ps.setInt(1, user_id);
				ps.setInt(2, product.getProduct_id());
				ps.setInt(3, product.getQuantity());
				ps.setInt(4, product.getTotal());
				ps.execute();
				
				ps1 =  con.prepareStatement("update products set quantity= (quantity-?) where id=?; ");
				ps1.setInt(1, product.getQuantity());
				ps1.setInt(2, product.getProduct_id());
				ps1.execute();
				
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
			ps1.close();			
		}
		
	}
	
//	method to get All Purchase history data

	public void getPurchaseRecord() throws SQLException {
		System.out.println("***************Purchase Record***************");
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select purchase_record.id, users.name, email, mobile, products.name, description,price, purchase_record.Quantity,total from purchase_record \r\n" + 
					"join users on purchase_record.user_id = users.id\r\n" + 
					"join products on purchase_record.product_id = products.id\r\n" + 
					"");
			ResultSet rs =  ps.executeQuery();
			
			System.out.println("************Product List************");
			while(rs.next()) {
				System.out.println("Record ID: "+rs.getInt(1)+ " Buyer Name: "+rs.getString(2)+ " Email: "+rs.getString(3)+ " Mobile: "+rs.getString(4)+ " Product Name: "+rs.getString(5)+ " Descr: "+rs.getString(6)+ " Price: "+rs.getInt(7)+ " Qty: "+rs.getInt(8)+ " Total "+rs.getInt(9));
				
			}
						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
	}

	
//	method to get Purchase history  by id
	public void getPurchaseHistory(int user_id) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			Scanner scanner  =new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver");
			con =  DriverManager.getConnection("jdbc:mysql://localhost:3306/e_commerce", "root", "password");
			
			ps =  con.prepareStatement("select purchase_record.id, users.name, email, mobile, products.name, description,price, purchase_record.Quantity,total from purchase_record \r\n" + 
					"join users on purchase_record.user_id = users.id\r\n" + 
					"join products on purchase_record.product_id = products.id where users.id=?");
			ps.setInt(1, user_id);
			ResultSet rs =  ps.executeQuery();
			
//			System.out.println("************Purchase Record for "+rs.getString(2)+" ************");
			System.out.println("************Purchase Record ************");
			while(rs.next()) {
				System.out.println("Record ID: "+rs.getInt(1)+ " Buyer Name: "+rs.getString(2)+ " Email: "+rs.getString(3)+ " Mobile: "+rs.getString(4)+ " Product Name: "+rs.getString(5)+ " Descr: "+rs.getString(6)+ " Price: "+rs.getInt(7)+ " Qty: "+rs.getInt(8)+ " Total "+rs.getInt(9));
				
			}						
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			con.close();
			ps.close();			
		}
		
	}

}
