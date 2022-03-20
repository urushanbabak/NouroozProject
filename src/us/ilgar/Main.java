package us.ilgar;

import javax.management.StringValueExp;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

enum Command {
    create, insert, remove, replace, unknown;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        do {
            System.out.println("**********************");
            if (read()[0] != null) {
                System.out.println(Arrays.toString(read()));
            }
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
            if (!checkArray(array)) {
                System.out.println("inequality of types");
            } else {
                System.out.println("Your Array has been created:");
            }

        } while (!checkArray(array));
        write(array);
    }

    public static void insert() {

    }

    public static void remove() {
        // Reading array in file
        String b[] = read();
        Scanner removeScanner = new Scanner(System.in);
        int key = removeScanner.nextInt();
        b[key] = null;
        // write an array to a file
        write(b);
    }


    public static void replace() {
        // Reading array in file
        String b[] = read();
        Scanner replaceScanner = new Scanner(System.in);
        String replaceCommand = replaceScanner.next();
        String[] key = new String[2];
        String temp = new String();
        key = replaceCommand.split("=>");

        temp = b[Integer.parseInt(key[0])];
        b[Integer.parseInt(key[0])] = b[Integer.parseInt(key[1])];
        b[Integer.parseInt(key[1])] = temp;


        // write an array to a file
        write(b);
    }

    public static boolean checkArray(String[] array) {
        if (array[0].matches("\\d")) {
            for (int i = 1; i < array.length; i++) {
                if (!array[i].matches("\\d")) {
                    return false;
                }
            }
        } else if (array[0].matches("\\w{1}")) {
            for (int i = 1; i < array.length; i++) {
                if (!array[i].matches("\\w{1}")) {
                    return false;
                }
            }
        } else if (array[0].matches("\\w")) {
            for (int i = 1; i < array.length; i++) {
                if (array[i].matches("\\w{1}")) {
                    return false;
                }
            }
        }
        return true;

    }

    public static String[] read() {
        String filename = "array.txt";
        String strArray = "";

        String line;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            while ((line = in.readLine()) != null) {
                strArray += line + " ";

            }
            in.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

        String[] temp = strArray.split(" ");

        String array[] = new String[temp.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = String.valueOf(temp[i]);
        }
        return array;
    }

    public static void write(String[] x) {
        String filename = "array.txt";
        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < x.length; i++) {

                outputWriter.write(x[i] + "");

                outputWriter.newLine();
            }
            outputWriter.flush();
            outputWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}


