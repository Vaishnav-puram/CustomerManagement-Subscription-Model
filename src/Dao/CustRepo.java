package Dao;

import java.util.List;

import Exceptions.InvalidCredentials;
import Exceptions.UserNotFound;
import model.Customer;

public class CustRepo {

	public static Customer findByEmail(String email, List<Customer> customers) throws UserNotFound {
		Customer c = new Customer(email);
		int i = customers.indexOf(c);
		if (i == -1) {
			throw new UserNotFound("User Not Found !");
		}
		return customers.get(i);
	}

	public static Customer credentialsCheck(String email, String password, List<Customer> customers) throws InvalidCredentials {
		boolean flag = false;
		Customer c=null;
		for (Customer c1 : customers) {
			if (c1.getEmail().equals(email) && c1.getPassword().equals(password)) {
				flag=true;
				c=c1;
				break;
			}
		}
		if(!flag) {
			throw new InvalidCredentials("Invalid Credentials!");
		}
		return c;
	}
}
