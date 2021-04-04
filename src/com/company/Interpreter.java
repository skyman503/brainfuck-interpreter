package com.company;

import java.util.Scanner;

public class Interpreter {
    Scanner scanner = new Scanner(System.in);

    private char[] input;
    private byte[] memory;
    private int memoryPointer;
    private int nestedLoopCounter;
    private final int MAX_SIZE = 30000;

    public Interpreter(String input_){
        this.input = input_.toCharArray();
        this.memory = new byte[MAX_SIZE];
        this.memoryPointer = 0;
        this.nestedLoopCounter = 0;
    }

    private int printMemoryValue(int memoryPointer){
        if (0 <= memoryPointer && memoryPointer < MAX_SIZE){
            System.out.print(Character.toChars(memory[memoryPointer]));
            return 0;
        }
        return 1;
    }

    private int interpretInstruction(char instruction, int instructionID){
        switch (instruction){
            case '>':
                memoryPointer++;
                break;
            case '<':
                memoryPointer--;
                break;
            case '+':
                memory[memoryPointer]++;
                break;
            case '-':
                memory[memoryPointer]--;
                break;
            case '.':
                if(printMemoryValue(memoryPointer) != 0){
                    return 1;
                }
                break;
            case ',':
                char value = scanner.next().charAt(0);
                memory[memoryPointer] = (byte)value;
                break;
            default:
                return 2;
        }
        return 0;
    }


    public void interpretCode(){
        int noError = 0;
        for(int i=0; i<input.length; i++){
            if(input[i] == '[' || input[i] == ']'){
                if (memory[memoryPointer] == 0 && input[i] == '[') {
                    nestedLoopCounter++;
                    while (input[i] != ']' || nestedLoopCounter != 0) {
                        i++;

                        if (input[i] == '[') {
                            nestedLoopCounter++;
                        } else if (input[i] == ']') {
                            nestedLoopCounter--;
                        }
                    }
                }
                if (memory[memoryPointer] != 0 && input[i] == ']') {
                    nestedLoopCounter++;
                    while (input[i] != '[' || nestedLoopCounter != 0) {
                        i--;

                        if (input[i] == ']') {
                            nestedLoopCounter++;
                        } else if (input[i] == '[') {
                            nestedLoopCounter--;
                        }
                    }
                }

            }else {
                noError = interpretInstruction(input[i], i);
            }

            if(noError != 0){
                System.out.println("An error occurred!");
                break;
            }
        }

        System.out.println("\nProgram finished with exit code: " + noError);

    }
}
