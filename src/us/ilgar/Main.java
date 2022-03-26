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
import java.util.*;
import java.lang.*;
import java.io.*;

enum Command {
    create, insert, remove, replace, unknown;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        do {

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
            System.out.println("**********************");
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
            System.out.println("inequality of types");
        } while (!checkArray(array));
        write(array);
        System.out.println("Your Array has been created:");

    }


    public static void insert() {
        // Reading array in file
        int k = 0;
        Scanner insertScanner = new Scanner(System.in);
        String a[] = read();
        String b[] = new String[a.length + 1];
        for (int i = 0; i <= a.length; i++) {
            if (i < a.length) {
                b[i] = a[i];
            } else {
                k = a.length;
            }

        }
        b[k] = insertScanner.next();
        // write an array to a file

        if (checkArray(b)) {
            write(b);
        } else {
            System.out.println("inequality of types");
        }

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
        if (intlizeDimensional(b) == 1) {
            Scanner replaceScanner = new Scanner(System.in);
            String replaceCommand = replaceScanner.next();
            String[] key = new String[2];
            String temp = new String();
            key = replaceCommand.split("=>");

            temp = b[Integer.parseInt(key[0])];
            b[Integer.parseInt(key[0])] = b[Integer.parseInt(key[1])];
            b[Integer.parseInt(key[1])] = temp;
        } else if (intlizeDimensional(b) == 2) {
            int count = intlizeContOfElements(b);
            Scanner replaceScanner = new Scanner(System.in);
            String replaceCommand = replaceScanner.next();
            String[] key = new String[2];
            String temp = new String();
            key = replaceCommand.split("=>");
            String[] key2 = new String[2];
            String[] key3 = new String[2];
            key2 = key[0].split(",");
            key3 = key[1].split(",");
            String[] strEditing = new String[2];
            strEditing[0] = b[Integer.parseInt(key2[0]) * count + Integer.parseInt(key2[1])];
            strEditing[1] = b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])];
            String[] editedStr = editString(strEditing);
            b[Integer.parseInt(key2[0]) * count + Integer.parseInt(key2[1])] = editedStr[0];
            b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])] = editedStr[1];
            temp = b[Integer.parseInt(key2[0]) * count + Integer.parseInt(key2[1])];
            b[Integer.parseInt(key2[0]) * count + Integer.parseInt(key2[1])] = b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])];
            b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])] = temp;
        }


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

    public static int intlizeDimensional(String[] array) {
//        for (int i = 0; i < array.length; i++) {
//            if (array[i].matches("[")) {
//                return 2;
//            } else if (array[i].matches("[[")) {
//                return 3;
//            }
//
//
//        }

        return 2;
    }

    public static int intlizeContOfElements(String[] array) {
        int count = 0;
        for (int i = 0; ; i++) {

            if (array[i].substring(array[i].length() - 1).equals("]")) {
                count = i;
                break;

            }

        }

        return count + 1;
    }

    public static String[] editString(String[] str) {
        String[] newStr = str;
        if (isBraketLeft(newStr[0]) && isBraketRight(newStr[1])) {
            newStr[0] = newStr[0].replace("[", "");
            newStr[0] = newStr[0] + "]";
            newStr[1] = newStr[1].replace("]", "");
            newStr[1] = "[" + newStr[1];
            return newStr;
        } else if (isBraketRight(newStr[0]) && isBraketLeft(newStr[1])) {
            newStr[1] = newStr[1].replace("[", "");
            newStr[1] = newStr[1] + "]";
            newStr[0] = newStr[0].replace("]", "");
            newStr[0] = "[" + newStr[0];
            return newStr;
        } else if (isBraketLeft(newStr[0]) || isBraketLeft(newStr[1])) {
            if (isBraketLeft(newStr[0]) && isBraketLeft(newStr[1])) {
                return newStr;
            } else if (!isBraketLeft(newStr[1])) {
                newStr[1] = "[" + newStr[1];
                newStr[0] = newStr[0].replace("[", "");
                return newStr;
            } else if (!isBraketLeft(newStr[0])) {
                newStr[0] = "[" + newStr[0];
                newStr[1] = newStr[1].replace("[", "");
                return newStr;
            }

        } else if (isBraketRight(newStr[0]) || isBraketRight(newStr[1])) {
            if (isBraketRight(newStr[0]) && isBraketRight(newStr[1])) {
                return newStr;
            } else if (!isBraketRight(newStr[1])) {
                newStr[1] = newStr[1] + "]";
                newStr[0] = newStr[0].replace("]", "");
                return newStr;
            } else if (!isBraketRight(newStr[0])) {
                newStr[0] = newStr[0] + "]";
                newStr[1] = newStr[1].replace("]", "");
                return newStr;
            }
        }


        return newStr;

    }


    public static boolean isBraketLeft(String str) {
        if (str.contains("[")) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isBraketRight(String str) {
        if (str.contains("]")) {
            return true;
        } else {
            return false;
        }
    }

}


