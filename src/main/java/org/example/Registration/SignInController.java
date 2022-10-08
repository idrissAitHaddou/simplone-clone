package org.example.Registration;

import org.example.Former.FormerController;
import org.example.Learner.LearnerController;
import org.example.admin.AdminController;

import java.util.Scanner;

public class SignInController {
    private RegisterServer registerServer = new RegisterServer();
    public void authController() {
        Scanner scanInteger = new Scanner(System.in);
        Scanner scanString = new Scanner(System.in);
        int choose;
        String email, password;
        System.out.println("Admin : 1");
        System.out.println("Former : 2");
        System.out.println("Learner : 3");
        System.out.print("Enter nomber of role : ");
        choose = scanInteger.nextInt();
        System.out.print("Enter email : ");
        email = scanString.nextLine();
        System.out.print("Enter password : ");
        password = scanString.nextLine();
        switch (choose) {
            case 1 :
                if(registerServer.AuthServer("admin", email, password)) {
                    System.out.println("successfuly login, your welcome");
                    AdminController adminController = new AdminController();
                    adminController.menu();
                } else
                    System.out.println("your info invalid!!!"); authController(); break;
            case 2 :
                if(registerServer.AuthServer("former", email, password)){
                    System.out.println("suucessfuly login, your welcome");
                    FormerController formerController = new FormerController();
                    formerController.menu();
                } else
                    System.out.println("your info invalid!!!"); authController(); break;
            case 3 :
                if(registerServer.AuthServer("learner", email, password)){
                    System.out.println("suucessfuly login, your welcome");
                    LearnerController learnerController = new LearnerController();
                    learnerController.menu();
                } else
                    System.out.println("your info invalid!!!"); authController(); break;
            default: authController();break;
        }
    }

    public void logoutController() {
        authController();
    }
}
