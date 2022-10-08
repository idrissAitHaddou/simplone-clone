package org.example.Learner;

import org.example.Registration.SignInController;
import org.example.brief.Breif;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LearnerController {
    private LearnerServer learnerServer = new LearnerServer();
    private SignInController signInController = new SignInController();
    public void menu() {
        Scanner scanLearner = new Scanner(System.in);
        // print menu
        System.out.println("************** MENU LEARNER **************");
        // print all function for view data
        System.out.println(" All function for get data : ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        System.out.print("|| Logout : Enter 0 || ");
        System.out.println("|| My breifs : Enter 1 || ");
        System.out.println(" ---------------------------------------------------------------------------------------");
        // Enter your choos
        System.out.print(" Enter your choose here : ");
        int choosFormer = scanLearner.nextInt();
        // check this choos number
        switch (choosFormer) {
            case 0 : signInController.logoutController(); break;
            case 1 : getAllMyBreifsController(); menu(); break;
            default:menu();
        }
    }

    public void getAllMyBreifsController() {
        ResultSet breifs = null;
        breifs = learnerServer.getAllMyBreifsLaunchedServer();
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
}
