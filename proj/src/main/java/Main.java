import databaseInteractor.DatabaseInteractor;
import databaseInteractor.PostgreInteractor;
import entities.*;
import enums.Entities;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        DatabaseInteractor interactor = null;
        boolean exit = false;
        try {
            interactor = new PostgreInteractor();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (interactor != null) {
            while (!exit) {
                System.out.print("Enter name of an entity to perform crud operations, special queries to perform special queries or anything else to exit: ");
                String input = in.nextLine();
                if (input.isEmpty()) {
                    input = in.nextLine();
                }
                if (input.toLowerCase().equals("company")) {
                    processCompanies(interactor);
                }
                else if (input.toLowerCase().equals("customer")) {
                    processCustomers(interactor);
                }
                else if (input.toLowerCase().equals("developer")) {
                    processDevelopers(interactor);
                }
                else if (input.toLowerCase().equals("project")) {
                    processProjects(interactor);
                }
                else if (input.toLowerCase().equals("skill")) {
                    processSkills(interactor);
                }
                else if (input.toLowerCase().equals("special queries")) {
                    processSpecialQueries(interactor);
                }
                else {
                    exit = true;
                }
            }
        }
    }

    private static void processCompanies(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all companies\n2 to get company by id\n3 to delete company by id\n4 to add company\n5 to update company by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Company> companies = interactor.getCompanies();
            for (Company company : companies) {
                System.out.println("Company " + company.getId() + ":");
                System.out.println("Name: " + company.getName());
                System.out.println("Date of foundation: " + company.getFoundationDate() + "\n");
            }
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Company company = interactor.getCompanyById(inputNum);
            if (company != null) {
                System.out.println("Company " + company.getId() + ":");
                System.out.println("Name: " + company.getName());
                System.out.println("Date of foundation: " + company.getFoundationDate() + "\n");
            }
            else {
                System.out.println("No such company found");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = interactor.deleteById(Entities.COMPANIES, inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            }
            else {
                System.out.println("Deletion not successful");
            }
        }
        else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            Company company = new Company(0, name, date);
            boolean created = interactor.createCompany(company);
            if (created) {
                System.out.println("Creation successful");
            }
            else {
                System.out.println("Creation not successful");
            }
        }
        else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter new name: ");
            in.nextLine();
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter new date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            Company company = new Company(id, name, date);
            boolean updated = interactor.updateCompany(company);
            if (updated) {
                System.out.println("Update successful");
            }
            else {
                System.out.println("Update not successful");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void processCustomers(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all customers\n2 to get customer by id\n3 to delete customer by id\n4 to add customer\n5 to update customer by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Customer> customers = interactor.getCustomers();
            for (Customer customer : customers) {
                System.out.println("Customer " + customer.getId() + ":");
                System.out.println("Name: " + customer.getName());
                System.out.println("Country: " + customer.getCountry() + "\n");
            }
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Customer customer = interactor.getCustomerById(inputNum);
            if (customer != null) {
                System.out.println("Customer " + customer.getId() + ":");
                System.out.println("Name: " + customer.getName());
                System.out.println("Country: " + customer.getCountry() + "\n");
            }
            else {
                System.out.println("No such customer found");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = interactor.deleteById(Entities.CUSTOMERS, inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            }
            else {
                System.out.println("Deletion not successful");
            }
        }
        else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            System.out.print("Enter country: ");
            String country = in.nextLine();
            Customer customer = new Customer(0, name, country);
            boolean created = interactor.createCustomer(customer);
            if (created) {
                System.out.println("Creation successful");
            }
            else {
                System.out.println("Creation not successful");
            }
        }
        else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter new name: ");
            in.nextLine();
            String name = in.nextLine();
            System.out.print("Enter country: ");
            String country = in.nextLine();
            Customer customer = new Customer(id, name, country);
            boolean updated = interactor.updateCustomer(customer);
            if (updated) {
                System.out.println("Update successful");
            }
            else {
                System.out.println("Update not successful");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void processDevelopers(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all developers\n2 to get developer by id\n3 to delete developer by id\n4 to add developer\n5 to update developer by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Developer> developers = interactor.getDevelopers();
            for (Developer developer : developers) {
                System.out.println("Developer " + developer.getId() + ":");
                System.out.println("Name: " + developer.getName());
                System.out.println("Birth date: " + developer.getBirthDate());
                System.out.println("Sex: " + developer.getSex());
                System.out.println("Salary: " + developer.getSalary() + "\n");
            }
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Developer developer = interactor.getDeveloperById(inputNum);
            if (developer != null) {
                System.out.println("Developer " + developer.getId() + ":");
                System.out.println("Name: " + developer.getName());
                System.out.println("Birth date: " + developer.getBirthDate());
                System.out.println("Sex: " + developer.getSex());
                System.out.println("Salary: " + developer.getSalary() + "\n");
            }
            else {
                System.out.println("No such developer found");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = interactor.deleteById(Entities.DEVELOPERS, inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            }
            else {
                System.out.println("Deletion not successful");
            }
        }
        else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            System.out.print("Enter sex: ");
            String sex = in.nextLine();
            System.out.print("Enter salary: ");
            int salary = in.nextInt();
            Developer developer = new Developer(0, name, date, sex, salary);
            boolean created = interactor.createDeveloper(developer);
            if (created) {
                System.out.println("Creation successful");
            }
            else {
                System.out.println("Creation not successful");
            }
        }
        else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter name: ");
            in.nextLine();
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            System.out.print("Enter sex: ");
            String sex = in.nextLine();
            System.out.print("Enter salary: ");
            int salary = in.nextInt();
            Developer developer = new Developer(id, name, date, sex, salary);
            boolean updated = interactor.updateDeveloper(developer);
            if (updated) {
                System.out.println("Update successful");
            }
            else {
                System.out.println("Update not successful");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void processProjects(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all projects\n2 to get project by id\n3 to delete project by id\n4 to add project\n5 to update project by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Project> projects = interactor.getProjects();
            for (Project project : projects) {
                System.out.println("Project " + project.getId() + ":");
                System.out.println("Name: " + project.getName());
                System.out.println("Start date: " + project.getStartDate());
                System.out.println("Customer: " + project.getCustomerId());
                System.out.println("Company: " + project.getCompanyId());
                System.out.println("Cost: " + project.getCost() + "\n");
            }
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Project project = interactor.getProjectById(inputNum);
            if (project != null) {
                System.out.println("Project " + project.getId() + ":");
                System.out.println("Name: " + project.getName());
                System.out.println("Start date: " + project.getStartDate());
                System.out.println("Customer: " + project.getCustomerId());
                System.out.println("Company: " + project.getCompanyId());
                System.out.println("Cost: " + project.getCost() + "\n");
            }
            else {
                System.out.println("No such developer found");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = interactor.deleteById(Entities.PROJECTS, inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            }
            else {
                System.out.println("Deletion not successful");
            }
        }
        else if (input.equals("4")) {
            System.out.print("Enter name: ");
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            System.out.print("Customer: ");
            int customer = in.nextInt();
            System.out.print("Company: ");
            int company = in.nextInt();
            System.out.print("Cost: ");
            int cost = in.nextInt();
            Project project = new Project(0, name, date, customer, company, cost);
            boolean created = interactor.createProject(project);
            if (created) {
                System.out.println("Creation successful");
            }
            else {
                System.out.println("Creation not successful");
            }
        }
        else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter name: ");
            in.nextLine();
            String name = in.nextLine();
            boolean invalidDate = true;
            String date = null;
            while (invalidDate) {
                System.out.print("Enter date in yyyy-mm-dd format: ");
                date = in.nextLine();
                if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                    invalidDate = false;
                }
                else {
                    System.out.println("Date format is invalid");
                }
            }
            System.out.print("Customer: ");
            int customer = in.nextInt();
            System.out.print("Company: ");
            int company = in.nextInt();
            System.out.print("Cost: ");
            int cost = in.nextInt();
            Project project = new Project(id, name, date, customer, company, cost);
            boolean updated = interactor.updateProject(project);
            if (updated) {
                System.out.println("Update successful");
            }
            else {
                System.out.println("Update not successful");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void processSkills(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all skills\n2 to get skill by id\n3 to delete skill by id\n4 to add skill\n5 to update skill by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Skill> skills = interactor.getSkills();
            for (Skill skill : skills) {
                System.out.println("Skill " + skill.getId() + ":");
                System.out.println("Area: " + skill.getArea());
                System.out.println("Level: " + skill.getLevel() + "\n");
            }
        }
        else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            Skill skill = interactor.getSkillById(inputNum);
            if (skill != null) {
                System.out.println("Skill " + skill.getId() + ":");
                System.out.println("Area: " + skill.getArea());
                System.out.println("Level: " + skill.getLevel() + "\n");
            }
            else {
                System.out.println("No such skill found");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter id: ");
            int inputNum = in.nextInt();
            boolean deleted = interactor.deleteById(Entities.SKILLS, inputNum);
            if (deleted) {
                System.out.println("Deletion successful");
            }
            else {
                System.out.println("Deletion not successful");
            }
        }
        else if (input.equals("4")) {
            System.out.print("Enter area: ");
            String area = in.nextLine();
            System.out.print("Enter level: ");
            String level = in.nextLine();
            Skill skill = new Skill(0, area, level);
            boolean created = interactor.createSkill(skill);
            if (created) {
                System.out.println("Creation successful");
            }
            else {
                System.out.println("Creation not successful");
            }
        }
        else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = in.nextInt();
            System.out.print("Enter new area: ");
            in.nextLine();
            String area = in.nextLine();
            System.out.print("Enter level: ");
            String level = in.nextLine();
            Skill skill = new Skill(id, area, level);
            boolean updated = interactor.updateSkill(skill);
            if (updated) {
                System.out.println("Update successful");
            }
            else {
                System.out.println("Update not successful");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }

    private static void processSpecialQueries(DatabaseInteractor interactor) {
        System.out.println("Enter:\n1 to get all Java devs\n2 to get all middle devs\n3 to get dev salary sum by project id\n4 to get developers list by project id\n5 to get developer count per project");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Developer> developers = interactor.getJavaDevelopers();
            for (Developer developer : developers) {
                System.out.println("Developer " + developer.getId() + ":");
                System.out.println("Name: " + developer.getName());
                System.out.println("Birth date: " + developer.getBirthDate());
                System.out.println("Sex: " + developer.getSex());
                System.out.println("Salary: " + developer.getSalary() + "\n");
            }
        }
        else if (input.equals("2")) {
            List<Developer> developers = interactor.getMiddleDevelopers();
            for (Developer developer : developers) {
                System.out.println("Developer " + developer.getId() + ":");
                System.out.println("Name: " + developer.getName());
                System.out.println("Birth date: " + developer.getBirthDate());
                System.out.println("Sex: " + developer.getSex());
                System.out.println("Salary: " + developer.getSalary() + "\n");
            }
        }
        else if (input.equals("3")) {
            System.out.print("Enter project id: ");
            int inputNum = in.nextInt();
            int sum = interactor.getSalarySum(inputNum);
            System.out.println(sum);
        }
        else if (input.equals("4")) {
            System.out.print("Enter project id: ");
            int inputNum = in.nextInt();
            List<Developer> developers = interactor.getDevelopersByProject(inputNum);
            for (Developer developer : developers) {
                System.out.println("Developer " + developer.getId() + ":");
                System.out.println("Name: " + developer.getName());
                System.out.println("Birth date: " + developer.getBirthDate());
                System.out.println("Sex: " + developer.getSex());
                System.out.println("Salary: " + developer.getSalary() + "\n");
            }
        }
        else if (input.equals("5")) {
            List<DevsPerProject> developersPerProject = interactor.getDevelopersPerProject();
            for (DevsPerProject entry : developersPerProject) {
                System.out.println("Project name: " + entry.getProjectName());
                System.out.println("Start date: " + entry.getStartDate());
                System.out.println("Dev count: " + entry.getDevCount() + "\n");
            }
        }
        else {
            System.out.println("Invalid command");
        }
    }
    
}
