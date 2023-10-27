package validations;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import Exceptions.*;
import model.Customer;
import model.ServicePlan.enumServicePlan;

public class ValidateInputs {
	public static String validatePlan(String p) throws InvalidPlanException {
		boolean flag=false;
		for(enumServicePlan e:enumServicePlan.values()) {
			if(e.toString().equals(p)) {
				flag=true;
				break;
			}
		}
		if(!flag) {
			throw new InvalidPlanException("Invalid plan selected !");
		}
		return p;
	}
	public static double validateRegAmt(enumServicePlan plan,double amt) throws InvalidRegtAmt {
		
		if(plan.getPlan()!=amt) {
			throw new InvalidRegtAmt("Amount doesn't match with the plan selected !");
		}
		return amt;
	}
	public static String validateEmail(String email) throws InvalidEmail {
		if(!(Pattern.matches("^[a-z].*@gmail.com$", email))) {
			throw new InvalidEmail("Invalid Email!");
		}
		return email;
	}
	public static void checkDupEmail(String email,List<Customer> customers) throws DuplicateEmailException {
		if(customers.contains(email)) { //iterates till the size of the list
			throw new DuplicateEmailException("Duplicate email!");
		}
	}
	
	public static Customer validateAll(String firstName, String lastName, String email, String password, double regAmt,
			LocalDate dob, String plan,List<Customer> customers) throws InvalidPlanException, InvalidRegtAmt, InvalidEmail, DuplicateEmailException {
		checkDupEmail(email, customers);
		String validPlan=validatePlan(plan);
		double validAmt=validateRegAmt(enumServicePlan.valueOf(plan), regAmt);
		String validEmail=validateEmail(email);
		Customer c=new Customer( firstName,  lastName,  validEmail,  password,  validAmt,
				 dob,  enumServicePlan.valueOf(validPlan));
		return c;
	}
}
