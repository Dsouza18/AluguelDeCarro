package model.entities;

public class Invoice {

	private Double basicpayment;
	private Double tax;

	public Invoice() {

	}

	public Invoice(Double payment, Double tax) {
		this.basicpayment = payment;
		this.tax = tax;
	}

	public Double getBasicPayment() {
		return basicpayment;
	}

	public void setBasicPayment(Double payment) {
		this.basicpayment = payment;
	}

	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}
	
	public Double getTotalPayment() {
		return getBasicPayment() +  getTax();
	}

}
