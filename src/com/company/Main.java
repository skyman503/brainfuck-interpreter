package com.company;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String codeToExecute = scanner.nextLine();
	    Interpreter x = new Interpreter(codeToExecute);
	    x.interpretCode();
    }
}
