package us.ilgar;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

enum Command {
    create, insert, remove, replace, unknown;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        do {

            System.out.println("Write Your Command:");

            String input = scanner.next();
            if (terminalCommandToEnum(input).equals(Command.unknown)) {
                System.out.println("Unknown Command!");
            }

            checkCommand(terminalCommandToEnum(input));
            i++;
        } while (true);
    }

    public static Command terminalCommandToEnum(String input) {
        input = input.toLowerCase();
        Command command = Command.create;
        switch (input) {
            case "create":
                command = Command.create;
                break;
            case "insert":
                command = Command.insert;
                break;
            case "remove":
                command = Command.remove;
                break;
            case "replace":
                command = Command.replace;
                break;
            default:
                command = Command.unknown;

        }
        return command;
    }

    public static void checkCommand(Command command) {
        switch (command) {
            case create:
                create();
                break;
            case insert:
                insert();
                break;
            case remove:
                remove();
                break;
            case replace:
                replace();
                break;
            default:
                ;
        }
    }

    public static void create() {
        System.out.println("Define Your Array");
        String[] array = new String[10];
        do {
            Scanner creatScanner = new Scanner(System.in);
            String stringArray = creatScanner.next();
            array = stringArray.split(",");
            System.out.print(Arrays.toString(array));
        } while (checkArray(array));
    }

    public static void insert() {

    }

    public static void remove() {

    }

    public static void replace() {

    }

    public static boolean checkArray(String[] array) {
        if (array[1].matches("\\d")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].matches("\\D")) {
                    return false;
                }
            }
        }
        else if (array[1].matches("\\d")) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].matches("\\D")) {
                    return false;
                }
            }
        }

        return true;
    }
}

