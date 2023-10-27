package tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import model.Admin;
import model.Customer;
import model.ServicePlan.enumServicePlan;
import validations.ValidateInputs;
import Dao.CustRepo;

public class Test {
	public static void main(String[] args)
			throws InvalidPlanException, InvalidRegtAmt, InvalidEmail, UserNotFound, InvalidCredentials {
		String firstName,lastName,name,email,plan;
		String password, newPassword;
		double regAmt;
		LocalDate dob;
		ArrayList<Customer> customers = new ArrayList<>();
		Customer dummy=new Customer("vaishnav", "puram", "a@gmail.com", "123", 1000,LocalDate.now(),enumServicePlan.valueOf("SILVER"));
		customers.add(dummy);
		int choice;
		Customer c ,cust= null;
		
		Scanner s = new Scanner(System.in);
		do {
			System.out.println("1.SignUp customer");
			System.out.println("2.Login");
			System.out.println("3.Display Customers");
			System.out.println("4.Check available plans");
			System.out.println("5.Find Specific Customer");
			System.out.println("6.Change Password");
			System.out.println("7.Unsubscribe Plan");
			System.out.println("8.Delete Customer");
			System.out.println("9.Exit");
			choice = s.nextInt();
			try {
				switch (choice) {
				case 1:
					System.out.println("Enter first name:");
					firstName = s.next();
					System.out.println("Enter last name:");
					lastName = s.next();
					System.out.println("Enter email:");
					email = s.next();
					System.out.println("Enter password:");
					password = s.next();
					System.out.println("Enter reg amt:");
					regAmt = s.nextDouble();
					System.out.println("Enter dob:");
					dob = LocalDate.parse(s.next());
					System.out.println("Enter plan(SILVER/GOLD/DIAMOND/PLATINUM)");
					plan = s.next();

					c = ValidateInputs.validateAll(firstName, lastName, email, password, regAmt, dob,
							plan.toUpperCase(), customers);
					customers.add(c);
					break;
				case 2:
					System.out.println("Enter email");
					email = s.next();
					CustRepo.findByEmail(email, customers);
					System.out.println("Enter password");
					password = s.next();
					cust = CustRepo.credentialsCheck(email, password, customers);

					System.out.println("Login successful");
					System.out.println("want to edit your subscription ? (Y/N):");
					char val = s.next().charAt(0);
					if (val == 'Y') {
						System.out.println("Enter new plan name");
						plan = s.next();
						cust.setPlan(enumServicePlan.valueOf(plan));
						System.out.println("Plan updated successfully..");
					}else {
						System.out.println("Loggin off....");
					}

					break;
				case 3:
					if(customers.isEmpty()) {
						System.out.println("No users found!");
						break;
					}
					for (Customer c1 : customers) {
						System.out.println(c1);
					}
					break;
				case 4:
					for (enumServicePlan e : enumServicePlan.values()) {
						System.out.println(e.toString() + "---->" + e.getPlan());
					}
					break;
				case 5:
					System.out.println("Enter admin credentials");
					System.out.println("Enter username:");
					name=s.next();
					System.out.println("Enter password:");
					password=s.next();
					if(name.equals(Admin.getUsername())&&password.equals(Admin.getPassword()))
					{
						System.out.println("Logged in as admin");
					}else {
						throw new InvalidCredentials("Authentication failed!");
					}
					System.out.println("Enter email :"); {
					email = s.next();
					System.out.println(CustRepo.findByEmail(email, customers));
					break;
				}
				case 6:
					System.out.println("Enter email :");
					email = s.next();
					CustRepo.findByEmail(email, customers);
					System.out.println("Enter old password:");
					password = s.next();
					cust = CustRepo.credentialsCheck(email, password, customers);
					System.out.println("Enter new password");
					newPassword = s.next();
					cust.setPassword(newPassword);
					System.out.println("Password updated successfully.");
					break;
				case 7:
					System.out.println("Enter email");
					email = s.next();
					CustRepo.findByEmail(email, customers);
					System.out.println("Enter password");
					password = s.next();
					cust = CustRepo.credentialsCheck(email, password, customers);
					System.out.println("Login successful");
					cust.setPlan(null);
					System.out.println("Unsubscribed");
					break;
				case 8:
					System.out.println("Enter admin credentials");
					System.out.println("Enter username:");
					name=s.next();
					System.out.println("Enter password:");
					password=s.next();
					if(name.equals(Admin.getUsername())&&password.equals(Admin.getPassword()))
					{
						System.out.println("Logged in as admin");
					}else {
						throw new InvalidCredentials("Authentication failed!");
					}
					System.out.println("Enter email");
					cust=CustRepo.findByEmail(s.next(), customers);
					customers.remove(cust);
					System.out.println("Deleted customer");
					break;
				case 9:
					System.out.println("Qutting...");
					break;
				default:
					System.out.println("Invalid option!");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (choice != 9);
		s.close();
	}
}
