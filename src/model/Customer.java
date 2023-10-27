package model;

import java.time.LocalDate;
import java.util.Objects;

import model.ServicePlan.enumServicePlan;

public class Customer {
	private int id;
	private static int idCounter=0;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private double regAmt;
	private LocalDate dob;
	private enumServicePlan plan;
	public Customer(String email) {
		this.email=email;
	}
	public Customer(String firstName, String lastName, String email, String password, double regAmt,
			LocalDate dob, enumServicePlan plan) {
		this.id = idCounter++;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.regAmt = regAmt;
		this.dob = dob;
		this.plan = plan;
	}
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public double getRegAmt() {
		return regAmt;
	}
	public LocalDate getDob() {
		return dob;
	}
	public enumServicePlan getPlan() {
		return plan;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPlan(enumServicePlan plan) {
		this.plan = plan;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Customer) {
			if(this.getEmail().equals(((Customer)obj).getEmail())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", regAmt=" + regAmt + ", dob=" + dob + ", plan=" + plan + " PlanCharges :"+this.plan.getPlan()+"]";
	}
}
