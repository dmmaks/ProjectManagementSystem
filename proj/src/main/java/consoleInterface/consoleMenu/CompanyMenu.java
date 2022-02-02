package consoleInterface.consoleMenu;

import models.Company;
import repositories.CompanyRepository;
import services.CompanyService;
import java.util.List;
import java.util.Scanner;

public class CompanyMenu extends ConsoleMenu {
    private static CompanyService service = new CompanyService(CompanyRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all companies\n2 to get company by id\n3 to delete company by id\n4 to add company\n5 to update company by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Company> companies = service.getAll();
            for (Company company : companies) {
                printCompany(company);
            }
        } else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = getInputNum();
            Company company = service.getById(inputNum);
            if (company != null) {
                printCompany(company);
            } else {
                System.out.println("No such company found");
            }
        } else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = getInputNum();
            boolean deleted = service.delete(inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            } else {
                System.out.println("Deletion not successful");
            }
        } else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            String date = getDate();
            Company company = new Company();
            company.setName(name);
            company.setFoundationDate(date);
            boolean created = service.create(company);
            if (created) {
                System.out.println("Creation successful");
            } else {
                System.out.println("Creation not successful");
            }
        } else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = getInputNum();
            System.out.print("Enter new name: ");
            String name = in.nextLine();
            String date = getDate();
            Company company = new Company(id, name, date);
            boolean updated = service.update(company);
            if (updated) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update not successful");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    private static void printCompany(Company company) {
        System.out.println("Company " + company.getId() + ":");
        System.out.println("Name: " + company.getName());
        System.out.println("Date of foundation: " + company.getFoundationDate() + "\n");
    }
}
