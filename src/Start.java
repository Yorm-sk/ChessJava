/*Here game is starting
* This file contains start menu*/

import Interface.Interface;
import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        System.out.println("Hello, welcome to chess!");
        Scanner scan = new Scanner(System.in);
        boolean isWrite = true;
        while (isWrite){
            System.out.println("""
                Choose your option:
                1 - Start new game!
                2 - Check description
                3 - Exit""");
            while (!scan.hasNextInt()){
                scan.nextLine();
                System.out.println("Enter int please!");
            }
            int choice = scan.nextInt();
            switch (choice){
                case 1 -> {
                    System.out.println("Game started");
                    new Interface();
                }
                case 2 -> Interface.description();
                case 3 -> isWrite = false;
                default -> System.out.println("Wrong number...");
            }

        }
    }
}
