package consoleInterface;

import consoleInterface.consoleMenu.*;

import java.util.Scanner;

public class ConsoleInterface {
    private static Scanner in = new Scanner(System.in);

    public void run() {
        boolean exit = false;
        while (!exit) {
            System.out.print("Enter name of an entity to perform crud operations, special queries to perform special queries or anything else to exit: ");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("company")) {
                CompanyMenu.run();
            } else if (input.equalsIgnoreCase("customer")) {
                CustomerMenu.run();
            } else if (input.equalsIgnoreCase("developer")) {
                DeveloperMenu.run();
            } else if (input.equalsIgnoreCase("project")) {
                ProjectMenu.run();
            } else if (input.equalsIgnoreCase("skill")) {
                SkillMenu.run();
            } else if (input.equalsIgnoreCase("special queries")) {
                SpecialQueriesMenu.run();
            } else {
                exit = true;
            }
        }
    }

}

