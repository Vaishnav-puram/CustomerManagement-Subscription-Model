package validations;

import java.time.LocalDate;
import java.util.regex.Pattern;

import Exceptions.*;
import model.Customer;
import model.ServicePlan.enumServicePlan;

public class ValidateInputs {
	private static void validatePlan(String p) throws InvalidPlanException {
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
	}
	private static void validateRegAmt(enumServicePlan plan,double amt) throws InvalidRegtAmt {
		
		if(plan.getPlan()!=amt) {
			throw new InvalidRegtAmt("Amount doesn't match with the plan selected !");
		}
	}
	private static void validateEmail(String email) throws InvalidEmail {
		if(!(Pattern.matches("^[a-z].*@gmail.com$", email))) {
			throw new InvalidEmail("Invalid Email!");
		}
	}
	
	public static Customer validateAll(String firstName, String lastName, String email, String password, double regAmt,
			LocalDate dob, String plan) throws InvalidPlanException, InvalidRegtAmt, InvalidEmail {
		validatePlan(plan);
		validateRegAmt(enumServicePlan.valueOf(plan), regAmt);
		validateEmail(email);
		Customer c=new Customer( firstName,  lastName,  email,  password,  regAmt,
				 dob,  enumServicePlan.valueOf(plan));
		return c;
	}
}
