package consoleInterface.consoleMenu;

import models.Customer;
import repositories.CustomerRepository;
import services.CustomerService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu extends ConsoleMenu {
    private static CustomerService service = new CustomerService(CustomerRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all customers\n2 to get customer by id\n3 to delete customer by id\n4 to add customer\n5 to update customer by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Customer> customers = service.getAll();
            for (Customer customer : customers) {
                printCustomer(customer);
            }
        } else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Customer customer = service.getById(inputNum);
            if (customer != null) {
                printCustomer(customer);
            } else {
                System.out.println("No such customer found");
            }
        } else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = service.delete(inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            } else {
                System.out.println("Deletion not successful");
            }
        } else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            System.out.print("Enter country: ");
            String country = in.nextLine();
            Customer customer = new Customer();
            customer.setName(name);
            customer.setCountry(country);
            boolean created = service.create(customer);
            if (created) {
                System.out.println("Creation successful");
            } else {
                System.out.println("Creation not successful");
            }
        } else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter new name: ");
            in.nextLine();
            String name = in.nextLine();
            System.out.print("Enter country: ");
            String country = in.nextLine();
            Customer customer = new Customer(id, name, country);
            boolean updated = service.update(customer);
            if (updated) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update not successful");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    private static void printCustomer(Customer customer) {
        System.out.println("Customer " + customer.getId() + ":");
        System.out.println("Name: " + customer.getName());
        System.out.println("Country: " + customer.getCountry() + "\n");
    }
}