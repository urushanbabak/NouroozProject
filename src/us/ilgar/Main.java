package us.ilgar;

import java.util.Scanner;

enum Command {
    create, insert, remove, replace;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        do {

            System.out.println("Write Your Command:");

            String input = scanner.next();
            terminalCommandToEnum(input);
            i++;
        } while (true);
    }

    public static Command terminalCommandToEnum(String input) {
        input = input.toLowerCase();
        Command command = Command.create;
        switch (input) {
            case "creat":
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
                return null;

        }
        return command;
    }

    public static void create() {

    }

    public static void insert() {

    }

    public static void remove() {

    }

    public static void replace() {

    }
}

