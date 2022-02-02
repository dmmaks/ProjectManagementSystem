package consoleInterface.consoleMenu;

import models.Skill;
import repositories.SkillRepository;
import services.SkillService;

import java.util.List;
import java.util.Scanner;

public class SkillMenu extends ConsoleMenu {
    private static SkillService service = new SkillService(SkillRepository.getInstance());
    private static Scanner in = new Scanner(System.in);

    public static void run() {
        System.out.println("Enter:\n1 to get all skills\n2 to get skill by id\n3 to delete skill by id\n4 to add skill\n5 to update skill by id");
        String input = in.nextLine();
        if (input.equals("1")) {
            List<Skill> skills = service.getAll();
            for (Skill skill : skills) {
                printSkill(skill);
            }
        } else if (input.equals("2")) {
            System.out.print("Enter id: ");
            int inputNum = getInputNum();
            Skill skill = service.getById(inputNum);
            if (skill != null) {
                printSkill(skill);
            } else {
                System.out.println("No such skill found");
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
            System.out.print("Enter area: ");
            String area = in.nextLine();
            System.out.print("Enter level: ");
            String level = in.nextLine();
            Skill skill = new Skill();
            skill.setArea(area);
            skill.setLevel(level);
            boolean created = service.create(skill);
            if (created) {
                System.out.println("Creation successful");
            } else {
                System.out.println("Creation not successful");
            }
        } else if (input.equals("5")) {
            System.out.print("Enter id: ");
            int id = getInputNum();
            System.out.print("Enter new area: ");
            String area = in.nextLine();
            System.out.print("Enter level: ");
            String level = in.nextLine();
            Skill skill = new Skill(id, area, level);
            boolean updated = service.update(skill);
            if (updated) {
                System.out.println("Update successful");
            } else {
                System.out.println("Update not successful");
            }
        } else {
            System.out.println("Invalid command");
        }
    }

    private static void printSkill(Skill skill) {
        System.out.println("Skill " + skill.getId() + ":");
        System.out.println("Area: " + skill.getArea());
        System.out.println("Level: " + skill.getLevel() + "\n");
    }
}