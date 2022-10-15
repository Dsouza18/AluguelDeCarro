package model.service;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalService {

	private Double priceperday;
	private Double priceperhour;

	private BrazilTaxService taxservice;

	public RentalService(Double priceperday, Double priceperhour, BrazilTaxService taxservice) {
		this.priceperday = priceperday;
		this.priceperhour = priceperhour;
		this.taxservice = taxservice;
	}

	public void ProcessingInvoice(CarRental carRental) {

		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.0;

		double basicpayment;

		if (hours <= 12.0) {
			basicpayment = priceperhour * Math.ceil(hours);
		} else {
			basicpayment = priceperday * Math.ceil(hours / 24.0);
		}

		double tax = taxservice.tax(basicpayment);

		carRental.setInvoice(new Invoice(basicpayment, tax));
	}
}
