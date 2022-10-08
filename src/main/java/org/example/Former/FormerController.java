package org.example.Former;

import org.example.Registration.SignInController;
import org.example.Registration.Storage;
import org.example.admin.AdminServer;
import org.example.brief.Breif;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FormerController {
    private FormerServer formerServer = new FormerServer();
    private SignInController signInController = new SignInController();
    public void menu() {
        Scanner scanFormer = new Scanner(System.in);
        System.out.println("************** MENU FORMER **************");
        // print all function for create
        System.out.println(" All function for create : ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.println(" Logout : Enter 0");
        System.out.println(" Create breif : Enter 1");
        System.out.println(" Add learner to my promo : Enter 2");
        System.out.println(" Distribue un brief : Enter 3");
        System.out.println(" ---------------------------------------------------------------------------------------");
        // print all function for view data
        System.out.println(" All function for get data : ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.println(" All breifs : Enter 4");
        System.out.println(" All breifs launched : Enter 5");
        System.out.println(" My learners : Enter 6");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.print(" Enter your choose here : ");
        int choosFormer = scanFormer.nextInt();
        // check this choos number
        switch (choosFormer) {
            case 0 : signInController.logoutController(); break;
            case 1 : createBreifController(); menu(); break;
            case 2 : addLearnerToPromoController(); menu(); break;
            case 3 : launchBriefController(); menu(); break;
            case 4 : getAllMyBreifsController(); menu(); break;
            case 5 : getAllBreifsLaunchedController(); menu(); break;
            case 6 : getFormerlearnersController(); menu(); break;
            default:menu();
        }
    }
    public void createBreifController() {
        Scanner scanForString = new Scanner(System.in);
        Scanner scanForInteger = new Scanner(System.in);
        Breif newBreif = new Breif();
        // enter info of a former
        System.out.println("************** Create a new former **************");
        System.out.print(" Enter name : ");
        newBreif.name = scanForString.nextLine();
        newBreif.id_former = Storage.idLogger;
        if(formerServer.getIDPromoServer(newBreif.id_former) != 0) {
            newBreif.id_promo = formerServer.getIDPromoServer(newBreif.id_former);
            newBreif.launch = false;
            formerServer.createBreifServer(newBreif);
        } else {
            menu();
        }
    }

    public void getALLLearnerNotPromoController() {
        ResultSet learners = null;
        learners = formerServer.getALLLearnerNotPromoServer();
        System.out.println("");
        System.out.println("************** all learners not have any promotion **************");
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

    public void addLearnerToPromoController() {
        Scanner scan = new Scanner(System.in);
        int searchCode;
        ResultSet learners = null;
        learners = formerServer.getALLLearnerNotPromoServer();
        try {
            if(!learners.next()) {
                System.out.println("");
                System.out.println("has'nt have any learner");
                System.out.println("");
                menu();
            }else {
                getALLLearnerNotPromoController();
                System.out.println("");
                System.out.print("Enter code of learner : ");
                searchCode = scan.nextInt();
                System.out.println("");
                if(formerServer.checkIdLearnerServer(searchCode)) {
                    formerServer.addLearnerController(searchCode, Storage.idLogger);
                    System.out.println(" --------- success ------------");
                    menu();
                } else {
                    System.out.println(" ---------------------");
                    System.out.println(" this code incorrect |");
                    System.out.println(" ---------------------");
                    menu();
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getAllMyBreifsController() {
        ResultSet breifs = null;
        breifs = formerServer.getAllMyBreifsServer();
        System.out.println("");
        System.out.println("************** My breifs **************");
        System.out.println("");
        try {
            while (breifs.next()) {
                System.out.println(" ---------------------------------------------------------------------------------------");
                System.out.print(" Code : " + breifs.getInt("id") + " | ");
                System.out.print(" Name : " + breifs.getString("name") + " | ");
                System.out.print(" your code : " + breifs.getString("id_former") + " | ");
                System.out.print(" promo code : " + breifs.getString("id_promo") + " | ");
                System.out.println(" launch : " + breifs.getString("launch") + " | ");
                System.out.println(" ---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }

    public void getAllBreifsLaunchedController() {
        ResultSet breifs = null;
        breifs = formerServer.getAllMyBreifsLaunchedServer();
        System.out.println("");
        System.out.println("************** My breifs **************");
        System.out.println("");
        try {
            while (breifs.next()) {
                System.out.println(" ---------------------------------------------------------------------------------------");
                System.out.print(" Code : " + breifs.getInt("id") + " | ");
                System.out.print(" Name : " + breifs.getString("name") + " | ");
                System.out.print(" your code : " + breifs.getString("id_former") + " | ");
                System.out.print(" promo code : " + breifs.getString("id_promo") + " | ");
                System.out.println(" launch : " + breifs.getString("launch") + " | ");
                System.out.println(" ---------------------------------------------------------------------------------------");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("");
    }

    public void launchBriefController() {
            getAllMyBreifsController();
            Scanner scan = new Scanner(System.in);
            int searchCode;
            System.out.println("");
            System.out.println("************** launch the breif **************");
            System.out.print(" Enter code breif : ");
            searchCode = scan.nextInt();
            if(formerServer.checkIdBreifServer(searchCode)) {
                formerServer.launchBreifServer(searchCode);
            } else {
                System.out.println("brief not exist or is launchde!!!");
            }
    }

    public void getFormerlearnersController() {
        ResultSet learners = null;
        learners = formerServer.getALLMyLearnerServer();
        System.out.println("");
        System.out.println("************** all my learners **************");
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
}
