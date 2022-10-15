package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.service.BrazilTaxService;
import model.service.RentalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scan = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Entre com os dados do aluguel");
		System.out.print("Modelo do carro: ");
		String carmodel = scan.nextLine();
		System.out.print("Retirada (dd/MM/yyyy HH:mm): ");
		LocalDateTime start = LocalDateTime.parse(scan.nextLine(), fmt);
		System.out.print("Retorno (dd/MM/yyyy HH:mm): ");
		LocalDateTime finish = LocalDateTime.parse(scan.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carmodel));
		
		System.out.print("Entre com o preço por hora: ");
		double pricePerHour = scan.nextDouble();
		System.out.print("Entre com o preço por dia: ");
		double pricePerDay = scan.nextDouble();
		
		RentalService rentalservice = new RentalService(pricePerDay, pricePerHour, new BrazilTaxService());
		rentalservice.ProcessingInvoice(cr);
		
		System.out.println("FATURA: ");
		System.out.println("Pagamento básico:  " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
		System.out.println("Imposto:  " + String.format("%.2f", cr.getInvoice().getTax()));
		System.out.print("Pagamento total:  " + String.format("%.2f", cr.getInvoice().getTotalPayment()));

		scan.close();
	}

}
