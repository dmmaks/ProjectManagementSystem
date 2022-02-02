package consoleInterface.consoleMenu;

import models.Developer;
import models.DevsPerProject;
import repositories.DeveloperRepository;
import services.DeveloperService;

import java.util.List;
import java.util.Scanner;

public class SpecialQueriesMenu extends ConsoleMenu {
    private static DeveloperService service = new DeveloperService(DeveloperRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all Java devs\n2 to get all middle devs\n3 to get dev salary sum by project id\n4 to get developers list by project id\n5 to get developer count per project");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Developer> developers = service.getJavaDevelopers();
            for (Developer developer : developers) {
                printDeveloper(developer);
            }
        } else if (input.equals("2")) {
            List<Developer> developers = service.getMiddleDevelopers();
            for (Developer developer : developers) {
                printDeveloper(developer);
            }
        } else if (input.equals("3")) {
            System.out.print("Enter project id: ");
            int inputNum = getInputNum();
            int sum = service.getSalarySum(inputNum);
            System.out.println(sum);
        } else if (input.equals("4")) {
            System.out.print("Enter project id: ");
            int inputNum = getInputNum();
            List<Developer> developers = service.getDevelopersByProject(inputNum);
            for (Developer developer : developers) {
                printDeveloper(developer);
            }
        } else if (input.equals("5")) {
            List<DevsPerProject> developersPerProject = service.getDevelopersPerProject();
            for (DevsPerProject entry : developersPerProject) {
                System.out.println("Project name: " + entry.getProjectName());
                System.out.println("Start date: " + entry.getStartDate());
                System.out.println("Dev count: " + entry.getDevCount() + "\n");
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
}