package consoleInterface.consoleMenu;
import models.Developer;
import repositories.DeveloperRepository;
import services.DeveloperService;

import java.util.List;
import java.util.Scanner;

public class DeveloperMenu extends ConsoleMenu {
    private static DeveloperService service = new DeveloperService(DeveloperRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all developers\n2 to get developer by id\n3 to delete developer by id\n4 to add developer\n5 to update developer by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Developer> developers = service.getAll();
            for (Developer developer : developers) {
                printDeveloper(developer);
            }
        } else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = getInputNum();
            Developer developer = service.getById(inputNum);
            if (developer != null) {
                printDeveloper(developer);
            } else {
                System.out.println("No such developer found");
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
            Developer developer = getDeveloper();
            boolean created = service.create(developer);
            if (created) {
                System.out.println("Creation successful");
            } else {
                System.out.println("Creation not successful");
            }
        } else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = getInputNum();
            Developer developer = getDeveloper();
            developer.setId(id);
            boolean updated = service.update(developer);
            if (updated) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update not successful");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    private static void printDeveloper(Developer developer) {
        System.out.println("Developer " + developer.getId() + ":");
        System.out.println("Name: " + developer.getName());
        System.out.println("Birth date: " + developer.getBirthDate());
        System.out.println("Sex: " + developer.getSex());
        System.out.println("Salary: " + developer.getSalary() + "\n");
    }

    private static Developer getDeveloper() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        String date = getDate();
        System.out.print("Enter sex: ");
        String sex = in.nextLine();
        System.out.print("Enter salary: ");
        int salary = getInputNum();
        Developer developer = new Developer();
        developer.setName(name);
        developer.setSex(sex);
        developer.setBirthDate(date);
        developer.setSalary(salary);
        return developer;
    }
}