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
    create, insert, remove, replace, total, submissionxy, submissionyx, multiplication,
    divisionxy, divisionyx, adhesivexy, adhesiveyx, matrixmultiplicationxy,
    matrixmultiplicationyx, aTx, aTy, unknown;
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;

        do {

            if (readx()[0] != null) {
                System.out.println("X=" + " " + Arrays.toString(readx()));
                System.out.println("Y=" + " " + Arrays.toString(ready()));
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
        Command command = Command.unknown;
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
                break;

        }
        if (input.contains("+")) {
            command = Command.total;
        } else if (input.contains("x-") || input.contains("X-")) {
            command = Command.submissionxy;
        } else if (input.contains("y-") || input.contains("Y-")) {
            command = Command.submissionyx;
        } else if (input.contains("*")) {
            command = Command.multiplication;
        } else if (input.contains("x/") || input.contains("X/")) {
            command = Command.divisionxy;
        } else if (input.contains("y/") || input.contains("Y/")) {
            command = Command.divisionyx;
        } else if (input.contains("x#") || input.contains("X#")) {
            command = Command.adhesivexy;
        } else if (input.contains("y#") || input.contains("Y#")) {
            command = Command.adhesiveyx;
        } else if (input.contains("x@") || input.contains("X@")) {
            command = Command.matrixmultiplicationxy;
        } else if (input.contains("y@") || input.contains("Y@")) {
            command = Command.matrixmultiplicationyx;
        } else if (input.contains("x&") || input.contains("X&")) {
            command = Command.aTx;
        } else if (input.contains("y&") || input.contains("Y&")) {
            command = Command.aTy;
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
            case total:
                total();
                break;
            case submissionxy:
                submissionxy();
                break;
            case submissionyx:
                submissionyx();
                break;
            case multiplication:
                multiplication();
                break;
            case divisionxy:
                divisionxy();
                break;
            case divisionyx:
                divisionyx();
                break;
            case adhesivexy:
                adhesivexy();
                break;
            case adhesiveyx:
                adhesiveyx();
                break;
            case matrixmultiplicationxy:
                matrixmultiplicationxy();
                break;
            case matrixmultiplicationyx:
                matrixmultiplicationyx();
                break;
            case aTx:
                aTx();
                break;
            case aTy:
                aTy();
                break;
            default:
                ;
        }
    }

    public static void create() {
        Scanner createScanner = new Scanner(System.in);
        String arrayName = createScanner.next();
        System.out.println("Define Your Array");
        String[] array = new String[20];
        do {
            Scanner creatScanner = new Scanner(System.in);
            String stringArray = creatScanner.next();
            array = stringArray.split(",");
            System.out.println("inequality of types");
        } while (!checkArray(array));
        if (arrayName.equals("x") || arrayName.equals("X")) {
            writex(array);
        } else {
            writey(array);
        }
        System.out.println("Your Array has been created:");

    }


    public static void insert() {
        // Reading array in file

        Scanner insertScanner = new Scanner(System.in);
        String arrayName = insertScanner.next();
        String b[] = arrayName.equals("x") || arrayName.equals("X") ? readx() : ready();
        if (intlizeDimensional(b) == 1) {
            int key = insertScanner.nextInt();
            b[key] = insertScanner.next();
            if (checkArray(b)) {
                if (arrayName.equals("x") || arrayName.equals("X")) {
                    writex(b);
                } else {
                    writey(b);
                }
            } else {
                System.out.println("inequality of types");
            }

        } else if (intlizeDimensional(b) == 2) {
            int count = intlizeContOfElements(b);
            String[] key = new String[2];
            String keyString = insertScanner.next();
            if (keyString.matches("\\d,\\d")) {
                key = keyString.split(",");
                String[] editString = new String[2];
                editString[0] = b[Integer.parseInt(key[0]) * count + Integer.parseInt(key[1])];
                editString[1] = insertScanner.next();
                editString = editString(editString);
                b[Integer.parseInt(key[0]) * count + Integer.parseInt(key[1])] = editString[1];
                // write an array to a file
                if (checkArray(b)) {
                    if (arrayName.equals("x") || arrayName.equals("X")) {
                        writex(b);
                    } else {
                        writey(b);
                    }
                } else {
                    System.out.println("inequality of types");
                }
            } else if (keyString.matches("\\d")) {
                String[] newElements = insertScanner.next().split(",");
                String[] newb = new String[count * (Integer.parseInt(keyString) + 1)];
                for (int i = 0; i < b.length; i++) {
                    newb[i] = b[i];
                }
                for (int i = 0; i < count; i++) {
                    newb[Integer.parseInt(keyString) * count + i] = newElements[i];
                }
                // write an array to a file
                if (checkArray(newb)) {
                    if (arrayName.equals("x") || arrayName.equals("X")) {
                        writex(newb);
                    } else {
                        writey(newb);
                    }
                } else {
                    System.out.println("inequality of types");
                }
            }
        }
    }


    public static void remove() {
        // Reading array in file
        Scanner removeScanner = new Scanner(System.in);
        String arrayName = removeScanner.next();
        String b[] = arrayName.equals("x") || arrayName.equals("X") ? readx() : ready();
        if (intlizeDimensional(b) == 1) {
            int key = removeScanner.nextInt();
            b[key] = null;
            // write an array to a file
            writex(b);
        } else if (intlizeDimensional(b) == 2) {
            int count = intlizeContOfElements(b);
            String keyString = removeScanner.next();
            if (keyString.matches("\\d,\\d")) {
                String[] key = keyString.split(",");
                b[Integer.parseInt(key[0]) * count + Integer.parseInt(key[1])] = null;
            } else if (keyString.matches("\\d")) {
                String[] newb = new String[b.length - count];
                for (int i = 0; i < newb.length; i++) {
                    newb[i] = b[i];
                    if (i == Integer.parseInt(keyString) * count) {
                        i = i + count;
                    }
                }
                b = newb;
            }

            // write an array to a file
            if (arrayName.equals("x") || arrayName.equals("X")) {
                writex(b);
            } else {
                writey(b);
            }
        }
    }


    public static void replace() {
        // Reading array in file
        Scanner replaceScanner = new Scanner(System.in);
        String arrayName = replaceScanner.next();
        String b[] = arrayName.equals("x") || arrayName.equals("X") ? readx() : ready();
        if (intlizeDimensional(b) == 1) {
            String replaceCommand = replaceScanner.next();
            String[] key = new String[2];
            String temp = new String();
            key = replaceCommand.split("=>");

            temp = b[Integer.parseInt(key[0])];
            b[Integer.parseInt(key[0])] = b[Integer.parseInt(key[1])];
            b[Integer.parseInt(key[1])] = temp;
        } else if (intlizeDimensional(b) == 2) {
            int count = intlizeContOfElements(b);
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
            b[Integer.parseInt(key2[0]) * count + Integer.parseInt(key2[1])] =
                    b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])];
            b[Integer.parseInt(key3[0]) * count + Integer.parseInt(key3[1])] = temp;
        }


        // write an array to a file
        if (arrayName.equals("x") || arrayName.equals("X")) {
            writex(b);
        } else {
            writey(b);
        }

    }

    public static void total() {
        System.out.print("Y+X=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {

            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) +
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }
                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) +
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }

        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void submissionxy() {
        System.out.print("X-Y=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {
            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) -
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }
                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) -
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }


    public static void submissionyx() {
        System.out.print("Y-X=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {
            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(y[i])) -
                            Double.parseDouble(editString(x[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }

                System.out.println(Arrays.toString(total));

            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(y[i])) -
                            Double.parseDouble(editString(x[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void multiplication() {
        System.out.print("Y*X=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {
            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) *
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }

                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(y[i])) *
                            Double.parseDouble(editString(x[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void divisionxy() {
        System.out.print("X/Y=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {
            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) /
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }

                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(x[i])) /
                            Double.parseDouble(editString(y[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void divisionyx() {
        System.out.print("Y/X=");
        String x[] = readx();
        String y[] = ready();
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {
            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(y[i])) /
                            Double.parseDouble(editString(x[i]));
                    total[i] = String.valueOf(result);
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }

                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    double result = Double.parseDouble(editString(y[i])) /
                            Double.parseDouble(editString(x[i]));
                    total[i] = String.valueOf(result);

                }
                System.out.println(Arrays.toString(total));
            }
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void adhesivexy() {
        System.out.print("X#Y=");
        String x[] = readx();
        String y[] = ready();
        if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {

            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    String result = editString(x[i]) + editString(y[i]);
                    total[i] = result;
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }
                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    String result = editString(x[i]) + editString(y[i]);
                    total[i] = result;

                }
                System.out.println(Arrays.toString(total));
            }

        } else if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void adhesiveyx() {
        System.out.print("Y#X=");
        String x[] = readx();
        String y[] = ready();
        if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))
                && intlizeContOfElements(x) == intlizeContOfElements(y)
                && intlizeDimensional(x) == intlizeDimensional(y)) {

            String total[] = new String[x.length];
            if (intlizeDimensional(x) == 2) {
                for (int i = 0; i < x.length; i++) {
                    String result = editString(y[i]) + editString(x[i]);
                    total[i] = result;
                    if (x[i].contains("[")) {
                        total[i] = "[" + total[i];
                    } else if (x[i].contains("]")) {
                        total[i] = total[i] + "]";
                    }
                }
                System.out.println(Arrays.toString(total));
            } else if (intlizeDimensional(x) == 1) {
                for (int i = 0; i < x.length; i++) {
                    String result = editString(y[i]) + editString(x[i]);
                    total[i] = result;

                }
                System.out.println(Arrays.toString(total));
            }

        } else if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == intlizeDimensional(y))) {
            System.out.println(" inequality of dimensionals");
        } else if (!(intlizeContOfElements(x) == intlizeContOfElements(y))) {
            System.out.println(" inequality of sizes");
        }
    }

    public static void matrixmultiplicationxy() {
        System.out.println("X@Y=");
        String x[] = readx();
        String y[] = ready();
        int row1 = x.length / intlizeContOfElements(x);
        int col1 = intlizeContOfElements(x);
        double A[][] = convertTo2dArray(x);
        int row2 = y.length / intlizeContOfElements(y);
        int col2 = intlizeContOfElements(y);
        double B[][] = convertTo2dArray(y);
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(x) == y.length / intlizeContOfElements(y)
                && intlizeDimensional(x) == 2 && intlizeDimensional(y) == 2) {
            int i, j, k;

            // Check if multiplication is Possible
            if (row2 != col1) {

                System.out.println(
                        "\nMultiplication Not Possible");
                return;
            }

            // Matrix to store the result
            // The product matrix will
            // be of size row1 x col2
            double C[][] = new double[row1][col2];

            // Multiply the two matrices
            for (i = 0; i < row1; i++) {
                for (j = 0; j < col2; j++) {
                    for (k = 0; k < row2; k++)
                        C[i][j] += A[i][k] * B[k][j];
                }
            }

            // Print the result

            printMatrix(C, row1, col2);
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == 2 && intlizeDimensional(y) == 2)) {
            System.out.println(" Wrong dimensionals");
        } else if (!(intlizeContOfElements(x) == y.length / intlizeContOfElements(y))) {
            System.out.println(" incompatible dimension");
        }
    }

    public static void matrixmultiplicationyx() {
        System.out.println("Y@X=");
        String x[] = readx();
        String y[] = ready();
        int row1 = y.length / intlizeContOfElements(y);
        int col1 = intlizeContOfElements(y);
        double A[][] = convertTo2dArray(y);
        int row2 = x.length / intlizeContOfElements(x);
        int col2 = intlizeContOfElements(x);
        double B[][] = convertTo2dArray(x);
        if ((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y))
                && intlizeContOfElements(y) == x.length / intlizeContOfElements(x)
                && intlizeDimensional(x) == 2 && intlizeDimensional(y) == 2) {

            int i, j, k;

            // Check if multiplication is Possible
            if (row2 != col1) {

                System.out.println(
                        "\nMultiplication Not Possible");
                return;
            }

            // Matrix to store the result
            // The product matrix will
            // be of size row1 x col2
            double C[][] = new double[row1][col2];

            // Multiply the two matrices
            for (i = 0; i < row1; i++) {
                for (j = 0; j < col2; j++) {
                    for (k = 0; k < row2; k++)
                        C[i][j] += A[i][k] * B[k][j];
                }
            }

            // Print the result
            printMatrix(C, row1, col2);
        } else if (!((isInteger(x) || isFloat(x)) && (isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == 2 && intlizeDimensional(y) == 2)) {
            System.out.println(" Wrong dimensionals");
        } else if (!(intlizeContOfElements(y) == x.length / intlizeContOfElements(x))) {
            System.out.println(" incompatible dimension");
        }
    }

    public static void aTx() {
        System.out.println("X&=");
        String x[] = readx();
        int row1 = x.length / intlizeContOfElements(x);
        int col1 = intlizeContOfElements(x);
        double A[][] = convertTo2dArray(x);
        double B[][] = new double[col1][row1];
        if ((isInteger(x) || isFloat(x))
                && intlizeDimensional(x) == 2) {
            int i, j;
            for (i = 0; i < col1; i++)
                for (j = 0; j < row1; j++)
                    B[i][j] = A[j][i];

            for (i = 0; i < col1; i++)
            {
                for (j = 0; j < row1; j++)
                    System.out.print(B[i][j] + " ");
                System.out.print("\n");
            }

        } else if (!((isInteger(x) || isFloat(x)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(x) == 2)) {
            System.out.println(" Wrong dimensionals");

        }
    }

    public static void aTy() {
        System.out.println("Y&=");
        String y[] = ready();
        int row1 = y.length / intlizeContOfElements(y);
        int col1 = intlizeContOfElements(y);
        double A[][] = convertTo2dArray(y);
        double B[][] = new double[col1][row1];
        if ((isInteger(y) || isFloat(y))
                && intlizeDimensional(y) == 2) {
            int i, j;
            for (i = 0; i < col1; i++)
                for (j = 0; j < row1; j++)
                    B[i][j] = A[j][i];

            for (i = 0; i < col1; i++)
            {
                for (j = 0; j < row1; j++)
                    System.out.print(B[i][j] + " ");
                System.out.print("\n");
            }

        } else if (!((isInteger(y) || isFloat(y)))) {
            System.out.println(" Wrong type");
        } else if (!(intlizeDimensional(y) == 2)) {
            System.out.println(" Wrong dimensionals");

        }
    }

    public static boolean checkArray(String[] array) {
//        for (int i = 0; i < array.length; i++) {
//
//            System.out.print(!(array[i].matches("\\[\\d") || array[i].matches("\\d]")
//                    || array[i].matches("\\d")
//                    || array[i].matches("\\[[+-]?([0-9]*[.])?[0-9]+")
//                    || array[i].matches("[+-]?([0-9]*[.])?[0-9]+]")
//                    || array[i].matches("[+-]?([0-9]*[.])?[0-9]+")));
//            System.out.print(!(array[i].matches("\\[[abc]{1}")
//                    || array[i].matches("[abc]{1}")
//                    || array[i].matches("[abc]{1}]")));
//            System.out.println(!(array[i].matches("\\[[abc]") || array[i].matches("[abc]")
//                    || array[i].matches("[abc]]")));
//        }

        if (array[0].

                matches("\\[\\d") || array[0].

                matches("\\d")
                || array[0].

                matches("\\[[+-]?([0-9]*[.])?[0-9]+")
                || array[0].

                matches("[+-]?([0-9]*[.])?[0-9]+")) {
            for (int i = 1; i < array.length; i++) {
                if (!(array[i].matches("\\[\\d") || array[i].matches("\\d]")
                        || array[i].matches("\\d")
                        || array[i].matches("\\[[+-]?([0-9]*[.])?[0-9]+")
                        || array[i].matches("[+-]?([0-9]*[.])?[0-9]+]")
                        || array[i].matches("[+-]?([0-9]*[.])?[0-9]+"))) {
                    return false;
                }
            }
        } else if (array[0].

                matches("\\[[abc]{1}")
                || array[0].

                matches("[abc]{1}")) {
            for (int i = 1; i < array.length; i++) {
                if (!(array[i].matches("\\[[abc]{1}")
                        || array[i].matches("[abc]{1}")
                        || array[i].matches("[abc]{1}]"))) {
                    return false;
                }
            }
        } else if (array[0].

                matches("\\[[abc]") || array[0].

                matches("[abc]")) {
            for (int i = 1; i < array.length; i++) {
                if (!(array[i].matches("\\[[abc]") || array[i].matches("[abc]")
                        || array[i].matches("[abc]]"))) {
                    return false;
                }
            }
        }
        return true;

    }

    public static String[] ready() {
        String filename = "arrayy.txt";
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

    public static String[] readx() {
        String filename = "arrayx.txt";
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

    public static void writey(String[] x) {
        String filename = "arrayy.txt";
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

    public static void writex(String[] x) {
        String filename = "arrayx.txt";
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
        if (array[0].contains("[")) {
            return 2;
        } else {
            return 1;
        }

    }


    public static int intlizeContOfElements(String[] array) {
        if (intlizeDimensional(array) == 2) {


            int count = 0;
            if (array[0].contains("[")) {


                for (int i = 0; ; i++) {

                    if (array[i].contains("]")) {
                        count = i;
                        break;

                    }

                }

                return count + 1;
            }
        }
        return array.length;
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

    public static String editString(String str) {
        if (str.contains("[")) {
            return str.replace("[", "");
        } else if (str.contains("]")) {
            return str.replace("]", "");
        }
        return str;
    }

    public static boolean isInteger(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!(array[i].matches("[0-9]+") || array[i].matches("\\[[0-9]+")
                    || array[i].matches("[0-9]+]"))) {
                return false;
            }

        }
        return true;
    }

    public static boolean isFloat(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (!(array[i].matches("[+-]?([0-9]*[.])?[0-9]+") ||
                    array[i].matches("\\[[+-]?([0-9]*[.])?[0-9]+")
                    || array[i].matches("[+-]?([0-9]*[.])?[0-9]+\\]"))) {
                return false;
            }

        }
        return true;
    }

    public static double[][] convertTo2dArray(String[] array) {
        double[][] result = new
                double[array.length / intlizeContOfElements(array)][intlizeContOfElements(array)];
        int key = 0;
        for (int i = 0; i < array.length / intlizeContOfElements(array); i++) {
            for (int j = 0; j < intlizeContOfElements(array); j++) {
                result[i][j] = Double.parseDouble(editString(array[key]));
                key++;
                if (key == array.length) {
                    return result;
                }
            }
        }
        return result;
    }

    static void printMatrix(double M[][],
                            int rowSize,
                            int colSize) {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++)
                System.out.print(M[i][j] + " ");

            System.out.println();
        }
    }


}