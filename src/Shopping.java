import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Shopping {
	Product product = new Product();
	PurchaseData purchaseData = new PurchaseData();
	Scanner scanner  =new Scanner(System.in);

	public void shopping(int user_id) throws SQLException {
		System.out.println("***************Welcome to ABC Mart ***************");
		System.out.println("List of available Products");
		product.getList();
		System.out.println("----------------------------------------------------");
		
		ArrayList<Product> cartList = new ArrayList<Product>();
		char flag;
		int grand_total=0;
		
		do {
			System.out.print("Enter  product ID to add in cart>> ");
			int product_id  = scanner.nextInt();
			System.out.print("Enter Quantity>> ");
			int qty  = scanner.nextInt();
			
			HashMap map = product.getProductInfo(product_id);
			System.out.println("***"+map);
			int rate = (Integer) map.get("price");
			int total = qty * rate;
			
			cartList.add(new Product(user_id, product_id, rate, qty, total));
			
			System.out.print("Do you want to add more??(Y/N)");
			flag = scanner.next().charAt(0);
			
		} while (flag == 'Y' || flag == 'y');
		
		if (flag == 'N' || flag == 'n') {
			System.out.println("Checkout !!");			
			System.out.println("------------------------------------------------");			
			for (Product product : cartList) {
				System.out.println("Product id: " + product.getProduct_id() + " Rate: " + product.getRate() + " Quantity: " + product.getQuantity() + " Total: " + product.getTotal());
				grand_total  += product.getTotal();
				
			}
			System.out.println("\t \t Total Bill >>> "+grand_total);
			purchaseData.addShoppingInfo(user_id, cartList);
			
			System.out.println("------------------------------------------------");			
			System.out.println("Thank you for Shopping in ABC Mart!!");			
			
		} else {
			System.out.println("Invalid Input!!");

		}

		
	}
	
}
