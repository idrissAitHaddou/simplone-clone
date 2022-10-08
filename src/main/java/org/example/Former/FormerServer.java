package org.example.Former;

import org.example.brief.Breif;
import org.example.mail.SendEmailServer;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FormerServer {
    private FormerRepository formerRepository = new FormerRepository();
    public void createBreifServer(Breif newBreif) {
        if(formerRepository.createBreifRepository(newBreif)) {
            System.out.println("inserted");
        } else {
            System.out.println("error");
        }
    }

    public int getIDPromoServer(int idFormer) {
        ResultSet data = null;
        data = formerRepository.getIDPromoServer(idFormer);
        try {
            if(data.next()) {
                return (int)data.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public ResultSet getALLLearnerNotPromoServer() {
        ResultSet learners = null;
        return  learners = formerRepository.getALLLearnerNotPromoRepository();
    }

    public ResultSet getALLMyLearnerServer() {
        ResultSet learners = null;
        return  learners = formerRepository.getALLMyLearnerRepository();
    }

    public ResultSet getAllMyBreifsServer() {
        ResultSet breifs = null;
        return  breifs = formerRepository.getAllMyBreifsRepository();
    }

    public ResultSet getAllMyBreifsLaunchedServer() {
        ResultSet breifs = null;
        return  breifs = formerRepository.getAllMyBreifsLaunchedRepository();
    }

    public boolean checkIdLearnerServer(int idLearner) {
        ResultSet data = null;
        data = formerRepository.checkIdLearnerRepository(idLearner);
        try {
            if(!data.next()){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void addLearnerController(int idLearner, int idLogger) {
        int idPromo = formerRepository.getPromoInfoReposotory(idLogger);
        if(idPromo != 0)
            formerRepository.addLearnerRepository(idPromo, idLearner, idLogger);
        else
            System.out.println("you are not have any promotion!!!");
    }

    public boolean checkIdBreifServer(int idBreif) {
        ResultSet data = null;
        data = formerRepository.checkIdBreifRepository(idBreif);
        try {
            if(!data.next()){
                return false;
            }else {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void launchBreifServer(int idBreif) {
        if(formerRepository.launchBreifRepository(idBreif)) {
            ResultSet learners = null;
            learners = formerRepository.getALLMyLearnerRepository();
                try {
                    while (learners.next()) {
                        SendEmailServer.sendEmail(learners.getString("email"));
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            System.out.println("launched success");
        }
    }
}
