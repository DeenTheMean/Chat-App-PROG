package com.mycompany.messenger_app;

import java.util.ArrayList;
import java.util.Scanner;

public class Messenger_App {

    public static void main(String[] args) {
        
        // Declaration of Scanner and ArrayList used for storing users
        Scanner scanner = new Scanner(System.in);
        ArrayList<Login> users = new ArrayList<>();
        boolean running = true;
        
        System.out.println("============ CHAT APP ============");
    
        // Main menu reappears after each case unless program is terminated
        while(running){
            System.out.println("\n1. Register User");
            System.out.println("2. Log in");
            System.out.println("3. Exit");

            System.out.print("\nSelect an option (1, 2, 3): ");
            int selection = scanner.nextInt();
            scanner.nextLine();

            switch(selection){
                case 1 -> {
                    System.out.print("\nEnter a username: ");
                    String username = scanner.nextLine();
                    
                    // Validation of username (uses checkUsername method within the Login class)
                    if(Login.checkUsername(username)){
                        // Checks if username already exists in arraylist
                        if(users.stream().anyMatch(u -> u.getUsername().equals(username))){
                            System.out.println("Username already exists. Please choose a different username.");
                            break;
                        }
                        
                        System.out.println("Username successfully captured.");
                    }else{
                        // Prints helpful message using the registerUser method within the Login class
                        System.out.println(Login.registerUser("InvalidUsername"));
                        break;
                    }                                       
                         
                    
                    System.out.print("\nEnter a password: ");
                    String password = scanner.nextLine();
                    
                    // Validation of password (uses checkPasswordComplexity method within the Login class)
                    if(Login.checkPasswordComplexity(password)){
                        System.out.println("Password successfully captured.");
                    }else{
                        // Prints helpful message using the registerUser method within the Login class
                        System.out.println(Login.registerUser("InvalidPassword"));
                        break;
                    }
                       
                    
                    System.out.print("\nEnter your cell phone number (+27XXXXXXXXX): ");
                    String phoneNumber = scanner.nextLine();
                    
                    // Validation of phone number (uses checkCellPhoneNumber method within the Login class)                    
                    if(Login.checkCellPhoneNumber(phoneNumber)){
                        System.out.println("Cell phone number successfully added.");
                    }else{
                        System.out.println("Cell phone number incorrectly formatted " +
                                           "or does not contain international code.");
                        break;
                    }
                    
                    // User is added to the ArrayList and a message is displayed if registration is successful
                    users.add(new Login(username, password, phoneNumber));
                    System.out.println(Login.registerUser("RegisterSuccess"));
                    
                    // User is prompted to log in immediately after registering (uses the promptLogin method)
                    System.out.println("\nWelcome, " + username + "! You can now log in with your username and password.");
                    System.out.println("Redirecting to login page...\n");
                    promptLogin(scanner, users);
                    break;
                }
                case 2 -> {
                    // Checks if the ArrayList is empty (no registered users), otherwise prompts login
                    if(users.isEmpty()){
                        System.out.println("No users have registered yet! Please register first.");
                        break;
                    }
                    
                    promptLogin(scanner, users);
                    break;
                }
                case 3 -> {
                    // Terminates the program
                    System.out.println("Exiting... Come back soon!");
                    scanner.close();
                    running = false;
                }
                default ->
                    // Invalid input error message
                    System.out.println("Please enter a valid number (1, 2, 3)");
            }
        }
    }
    
    // Searches ArrayList for existing user with the given username and password
    private static void promptLogin(Scanner scanner, ArrayList<Login> users){
        System.out.print("Enter username: ");
        String enteredUsername = scanner.nextLine();
        System.out.print("Enter password: ");
        String enteredPassword = scanner.nextLine();
        
        Login user = users.stream()
                  .filter(u -> u.getUsername().equals(enteredUsername))
                  .findFirst()
                  .orElse(null);
        
        boolean loginStatus = user != null && user.loginUser(enteredPassword);
        // Prints message (using the returnLoginStatus method) if the user is found or details are incorrect
        System.out.println(user != null ? user.returnLoginStatus(loginStatus) : "\nUser not found.");
    }
    
   public static class Login {
        public String username;
        public String password;
        public String phoneNumber;
        
        // Constructor
        public Login(String username, String password, String phoneNumber){
            this.username = username;
            this.password = password;
            this.phoneNumber = phoneNumber;
        }
        
        // Returns stored usernames
        public String getUsername(){
            return username;
        }
        
        // Username validation logic
        public static boolean checkUsername(String username){
            return username.length() <= 5 && username.contains("_");
        }
        
        // Password validation logic
        public static boolean checkPasswordComplexity(String password){
            return password.length() >= 8 &&
                   password.matches(".*[A-Z].*") &&
                   password.matches(".*\\d.*") &&
                   password.matches(".*[!@#$%^&*,.?\":()|<>].*");
        }
        
        // Phone number validation logic
        public static boolean checkCellPhoneNumber(String phoneNumber){
            return phoneNumber.matches("\\+27[6-8]\\d{8}");
        }
        
        // Method that returns a message depending on registration success
        public static String registerUser(String registerCase){
            return switch(registerCase){
                case "InvalidUsername" -> "Username is not correctly formatted; please ensure that " +
                                          "your username contains an underscore and is no more than " +
                                          "five characters in length.";
                case "InvalidPassword" -> "Password is not correctly formatted; please ensure that the " +
                                          "password contains at least eight characters, a capital letter, " +
                                          "a number, and a special character.";
                case "RegisterSuccess" -> "\nRegistered successfully!";
                default -> "Invalid case.";
            };
        }
        
        // Checks if password matches when logging in
        public boolean loginUser(String enteredPassword){
            return this.password.equals(enteredPassword);
        }
        
        // Returns a message if login is successful or not
        public String returnLoginStatus(boolean status){
            return status ? "\nLogin successful!" : "\nUsername or password incorrect. Please try again.";
        }
    }
}
