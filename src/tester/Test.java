package tester;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import Exceptions.*;
import model.Customer;
import model.ServicePlan.enumServicePlan;
import validations.ValidateInputs;

public class Test {
	public static void main(String[] args) throws InvalidPlanException, InvalidRegtAmt, InvalidEmail {
		 String firstName;
		 String lastName;
		 String email;
		 String password;
		 double regAmt;
		 LocalDate dob;
		 String plan;
		 ArrayList<Customer> customers=new ArrayList<>();
		 int choice;
		 Customer c = null;
		 boolean flag;
		 Scanner s=new Scanner(System.in);
		 do {
			 System.out.println("1.Register customer");
			 System.out.println("2.Login");
			 System.out.println("3.Display Customers");
			 System.out.println("4.Check available plans");
			 System.out.println("5.Exit");
			 choice=s.nextInt();
			 switch(choice) {
			 case 1:
				 System.out.println("Enter first name:");
				 firstName=s.next();
				 System.out.println("Enter last name:");
				 lastName=s.next();
				 System.out.println("Enter email:");
				 email=s.next();
				 System.out.println("Enter password:");
				 password=s.next();
				 System.out.println("Enter reg amt:");
				 regAmt=s.nextDouble();
				 System.out.println("Enter dob:");
				 dob=LocalDate.parse(s.next());
				 System.out.println("Enter plan(SILVER/GOLD/DIAMOND/PLATINUM)");
				 plan=s.next();
				 try {
					 c=ValidateInputs.validateAll(firstName, lastName, email, password, regAmt, dob, plan.toUpperCase());
				 }catch(InvalidPlanException e) {
					 System.out.println(e.getMessage());
				 }catch(InvalidRegtAmt e) {
					 System.out.println(e.getMessage());
				 }
				 flag=false;
				 for(Customer c1:customers) {
					 if(c.equals(c1)) {
						 flag=true;
						 System.out.println("Duplicate email!");
						 break;
					 }
				 }
				 if(!flag) {
					 customers.add(c);
				 }
				 break;
			 case 2:
				 System.out.println("Enter email");
				 email=s.next();
				 System.out.println("Enter password");
				 password=s.next();
				 for(Customer c1:customers) {
					 if(c1.getEmail().equals(email)&&c1.getPassword().equals(password)) {
						 System.out.println("Login successful");
						 System.out.println("want to edit your subscription ? (Y/N):");
						 char val=s.next().charAt(0);
						 if(val=='Y') {
							 System.out.println("Enter new plan name");
							 plan=s.next();
							 c1.setPlan(enumServicePlan.valueOf(plan));
							 System.out.println("Plan updated successfully..");
							 break;
						 }else {
							 break;
						 }
					 }
				 }
				 
				 break;
			 case 3:
				 for(Customer c1:customers) {
					 System.out.println(c1);
				 }
				 break;
			 case 4:
				 for(enumServicePlan e:enumServicePlan.values()) {
					 System.out.println(e.toString()+"---->"+e.getPlan());
				 }
				 break;
			 case 5:
				 System.out.println("Qutting...");
			 }
		 }while(choice!=5);		

	}
}
