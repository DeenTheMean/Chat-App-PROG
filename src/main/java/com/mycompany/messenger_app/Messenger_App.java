package com.mycompany.messenger_app;

import java.util.ArrayList;
import java.util.Scanner;

public class Messenger_App {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("============ CHAT APP ============");
    
        System.out.println("\n1. Sign up");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        
        System.out.print("\nSelect an option (1, 2, 3): ");
        int selection = scanner.nextInt();
        
        switch(selection){
            case 1 -> {
                System.out.println("Signed up");
            }
            case 2 -> {
                System.out.println("Logged in");
            }
            case 3 -> {
                System.out.println("Exited");
            }
            default -> 
                System.out.println("Please enter a valid number (1, 2, 3)");
        }
        
        scanner.close();
    }
}
