import java.sql.SQLException;
import java.util.Scanner;

public class HomePage {
	
//	method to show home screen
	public void homeScreen() throws SQLException {
		User user = new User();
		Admin admin = new Admin();
		
		Scanner scanner  =new Scanner(System.in);
		System.out.println("Provide Appropriate Input!!(Numeric only)");
		System.out.println("1 for User Login");
		System.out.println("2 For New User Registration ");
		System.out.println("3 for Admin Login");
		System.out.println("4 For Admin Registration ");
		int input = scanner.nextInt();
		
		if (input == 1) {
			System.out.println("User Login!!");
			user.userLogin();
			
		} else if(input == 2) {
			System.out.println("Register New User!!");
			user.userRegister();
			
			
		}else if(input == 3) {
			System.out.println("Admin Login!!");
			admin.adminLogin();
			
		}else if(input == 4) {
			System.out.println("Admin Register!!");
			admin.adminRegister();
			
		}else {
			System.out.println("Invalid input");
		}
		
	}
	
	
	
	public static void main(String[] args) throws SQLException {
		HomePage homePage = new HomePage();
		homePage.homeScreen();
		
		
		
	}

}
