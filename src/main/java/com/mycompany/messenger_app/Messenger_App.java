package com.mycompany.messenger_app;

import java.util.ArrayList;
import java.util.Scanner;

public class Messenger_App {

    public static void main(String[] args) {
        
        
        // ADD COMMENTS!!!!!!!!
        
        
        Scanner scanner = new Scanner(System.in);
        ArrayList<Login> user = new ArrayList<>();
        boolean running = true;
        
        System.out.println("============ CHAT APP ============");
    
        while(running){
            System.out.println("\n1. Sign up");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("\nSelect an option (1, 2, 3): ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch(selection){
                case 1 -> {
                    System.out.print("Enter a username: ");
                    String username = scanner.nextLine();
                    
                    if(username.length() <= 5 && username.contains("_")){
                        System.out.println("Username successfully captured.");
                    }else{
                        System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
                        break;
                    }
                         
                    
                    System.out.print("Enter a password: ");
                    String password = scanner.nextLine();
                    
                    if(password.length() >= 8 && password.matches(".*[A-Z].*") && password.matches(".*//d.*") && password.matches(".*(!@#$%^&*_,.-+=|;:'/<>?).*")){
                        System.out.println("Password successfully captured.");
                    }else{
                        System.out.println("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
                        break;
                    }
                       
                    
                    System.out.print("Enter your cell phone number (+27XXXXXXXXX): ");
                    String phoneNumber = scanner.nextLine();
                                        
                    if(phoneNumber.matches("\\+27[6-8]\\{8}")){
                        System.out.println("Cell phone number successfully added.");
                    }else{
                        System.out.println("Cell phone number cell phone number incorrectly formatted or does not contain international code.");
                    }
                    
                    //if(user.stream().anyMatch(u -> u.getUsername().equals(username))){
                        
                    //}
                }
                case 2 -> {
                    System.out.println("Logged in");
                }
                case 3 -> {
                    running = false;
                }
                default -> 
                    System.out.println("Please enter a valid number (1, 2, 3)");
            }
        }
    }
    
    class Login {
        public String username;
        public String password;
        public String phoneNumber;
    }
}
