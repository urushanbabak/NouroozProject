package us.ilgar;

import java.util.Scanner;

enum Command {
    creat, insert, remove, replace;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        System.out.println("Hi Dear. What Can I do?!");
        do {
            if (i > 1) {
                System.out.print("Your Array:");
                System.out.println("[hi,hello,how are you]");
            }
            String input = scanner.next();
            terminalCommand(input);
            i++;
        } while (true);
    }

    public static void terminalCommand(String input) {
        input = input.toLowerCase();
        Command command =Command.creat;
        if (input.equals("creat"))
            command = Command.creat;
        if (input.equals("insert"))
            command = Command.insert;
        if (input.equals("remove"))
            command = Command.remove;
        if (input.equals("replace"))
            command = Command.replace;
    }

}

