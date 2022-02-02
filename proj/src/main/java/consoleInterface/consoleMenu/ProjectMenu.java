package consoleInterface.consoleMenu;

import models.Project;
import repositories.ProjectRepository;
import services.ProjectService;

import java.util.List;
import java.util.Scanner;

public class ProjectMenu extends ConsoleMenu {
    private static ProjectService service = new ProjectService(ProjectRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all projects\n2 to get project by id\n3 to delete project by id\n4 to add project\n5 to update project by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Project> projects = service.getAll();
            for (Project project : projects) {
                printProject(project);
            }
        } else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = getInputNum();
            Project project = service.getById(inputNum);
            if (project != null) {
                printProject(project);
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
            Project project = getProject();
            boolean created = service.create(project);
            if (created) {
                System.out.println("Creation successful");
            } else {
                System.out.println("Creation not successful");
            }
        } else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = getInputNum();
            Project project = getProject();
            project.setId(id);
            boolean updated = service.update(project);
            if (updated) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update not successful");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    private static void printProject(Project project) {
        System.out.println("Project " + project.getId() + ":");
        System.out.println("Name: " + project.getName());
        System.out.println("Start date: " + project.getStartDate());
        System.out.println("Customer: " + project.getCustomerId());
        System.out.println("Company: " + project.getCompanyId());
        System.out.println("Cost: " + project.getCost() + "\n");
    }

    private static Project getProject() {
        System.out.print("Enter name: ");
        String name = in.nextLine();
        String date = getDate();
        System.out.print("Customer: ");
        int customer = getInputNum();
        System.out.print("Company: ");
        int company = getInputNum();
        System.out.print("Cost: ");
        int cost = getInputNum();
        Project project = new Project();
        project.setName(name);
        project.setStartDate(date);
        project.setCustomerId(customer);
        project.setCompanyId(company);
        project.setCost(cost);
        return project;
    }
}