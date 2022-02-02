package consoleInterface.consoleMenu;

import java.util.Scanner;

abstract class ConsoleMenu {
    private static Scanner in = new Scanner(System.in);

    protected static int getInputNum() {
        int inputNum = 0;
        String inputNumStr = in.nextLine();
        if (inputNumStr.matches("\\d+")) {
            inputNum = Integer.parseInt(inputNumStr);
        }
        return inputNum;
    }

    protected static String getDate() {
        boolean invalidDate = true;
        String date = null;
        while (invalidDate) {
            System.out.print("Enter date in yyyy-mm-dd format: ");
            date = in.nextLine();
            if (date.matches("^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")) {
                invalidDate = false;
            } else {
                System.out.println("Date format is invalid");
            }
        }
        return date;
    }
}
