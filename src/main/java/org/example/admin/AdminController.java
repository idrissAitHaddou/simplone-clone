package org.example.admin;

import org.example.Former.Former;
import org.example.Learner.Learner;
import org.example.Promotion.Promo;
import org.example.Registration.SignInController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminController {
    private AdminServer adminServer = new AdminServer();
    private SignInController signInController = new SignInController();
    public void menu() {
        // instance objects
        Scanner scanAdmin = new Scanner(System.in);
        System.out.println("");
        System.out.println("************** MENU ADMIN **************");
        // print all function for create
        System.out.println("");
        System.out.println(" All function for create : ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.println(" Logout : Enter 0 ");
        System.out.println(" Create former : Enter 1 ");
        System.out.println(" Create learner : Enter 2 ");
        System.out.println(" Create promotion : Enter 3 ");
        System.out.println(" Add former to promotion : Enter 7 ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        // print all function for view data
        System.out.println(" All function for get data : ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.println(" All formers : Enter 4");
        System.out.println(" All learners : Enter 5 ");
        System.out.println(" All promotions : Enter 6 ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        // Enter your choose
        System.out.print(" Enter your choose here : ");
        int choosAdmin = scanAdmin.nextInt();
        // switch this your choos
        switch (choosAdmin) {
            case 0 : signInController.logoutController(); break;
            case 1 : createFormerController(); menu(); break;
            case 2 : createLeanerController(); menu(); break;
            case 3 : createPromotionsController(); menu(); break;
            case 4 : getAllFormersController(); menu(); break;
            case 5 : getAllLearnersController(); menu(); break;
            case 6 : getAlLPromotionsController(); menu(); break;
            case 7 : addFormerToPromoController(); menu(); break;
            default:menu();
        }
    }

    public void createFormerController() {
        Scanner scanForString = new Scanner(System.in);
        Former newFormer = new Former();
        // enter info of a former
        System.out.println("************** Create new former **************");
        System.out.print(" Enter firstName : ");
        newFormer.firstName = scanForString.nextLine();
        System.out.print(" Enter lastName : ");
        newFormer.lastName = scanForString.nextLine();
        System.out.print(" Enter email : ");
        newFormer.email = scanForString.nextLine();
        System.out.print(" Enter password : ");
        newFormer.password = scanForString.nextLine();
        adminServer.createFormerServer(newFormer);
    }

    public void createPromotionsController() {
        Scanner scanForString = new Scanner(System.in);
        Scanner scanForInteger = new Scanner(System.in);
        Promo newPromotion = new Promo();
        System.out.println("************** Create new former **************");
        System.out.print(" Enter name : ");
        newPromotion.name = scanForString.nextLine();
        adminServer.createPromoServer(newPromotion);
    }

    public void createLeanerController() {
        Scanner scanForString = new Scanner(System.in);
        Scanner scanForInteger = new Scanner(System.in);
        Learner newLearner = new Learner();
        System.out.println("************** Create new learner **************");
        System.out.print(" Enter firstName : ");
        newLearner.firstName = scanForString.nextLine();
        System.out.print(" Enter lastName : ");
        newLearner.lastName = scanForString.nextLine();
        System.out.print(" Enter email : ");
        newLearner.email = scanForString.nextLine();
        System.out.print(" Enter password : ");
        newLearner.password = scanForString.nextLine();
        adminServer.createLearnerServer(newLearner);
    }

    public void getAllFormersController() {
        ResultSet formers = null;
        formers = adminServer.getALLFormerServer();
        System.out.println("");
        System.out.println("************** all formers **************");
        System.out.println("");
        try {
            while (formers.next()) {
                System.out.println(" ---------------------------------------------------------------------------------------");
                System.out.print(" id : " + formers.getInt("id") + " | ");
                System.out.print(" Email : " + formers.getString("email") + " | ");
                System.out.print(" FirstName : " + formers.getString("first_name") + " | ");
                System.out.println(" LastName : " + formers.getString("last_name") + " | ");
                System.out.println(" ---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }

    public void getAllLearnersController() {
        ResultSet learners = null;
        learners = adminServer.getALLLearnerServer();
        System.out.println("");
        System.out.println("************** all learners **************");
        System.out.println("");
        try {
            while (learners.next()) {
                System.out.println(" ---------------------------------------------------------------------------------------");
                System.out.print(" id : " + learners.getInt("id") + " | ");
                System.out.print(" Email : " + learners.getString("email") + " | ");
                System.out.print(" FirstName : " + learners.getString("first_name") + " | ");
                System.out.println(" LastName : " + learners.getString("last_name") + " | ");
                System.out.println(" ---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }

    public void getAlLPromotionsController() {
        ResultSet promotions = null;
        promotions = adminServer.getALLPromotionsServer();
        System.out.println("");
        System.out.println("************** all promotions **************");
        System.out.println("");
        try {
            while (promotions.next()) {
                System.out.println(" ---------------------------------------------------------------------------------------");
                System.out.print(" id : " + promotions.getInt("id") + " | ");
                System.out.print(" Name : " + promotions.getString("name"));
                System.out.println(" id former : " + promotions.getString("id_former"));
                System.out.println(" ---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }

    public void addFormerToPromoController() {
        Scanner scanner = new Scanner(System.in);
        int idPromo, idFormer;
        getAllFormersController();
        getAlLPromotionsController();
        System.out.print("Enter id of promotion : ");
        idPromo = scanner.nextInt();
        System.out.print("Enter id of former : ");
        idFormer = scanner.nextInt();
        if(adminServer.checkIdServer(idFormer, idPromo)) {
            adminServer.addFormerToPromoServer(idFormer, idPromo);
        } else {
            System.out.println("");
            System.out.println("id former or id promotion invalid!!!!");
            addFormerToPromoController();
        }
    }

}
