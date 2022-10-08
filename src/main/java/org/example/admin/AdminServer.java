package org.example.admin;

import org.example.Former.Former;
import org.example.Learner.Learner;
import org.example.Promotion.Promo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AdminServer {
    private AdminRepository adminRepository = new AdminRepository();
    public void createFormerServer(Former newFormer) {
        if(adminRepository.createFormerRepository(newFormer)) {
            System.out.println("inserted");
        } else {
            System.out.println("error");
        }
    }

    public void createLearnerServer(Learner newLearner) {
        if(adminRepository.createLearnerRepository(newLearner)) {
            System.out.println("inserted");
        } else {
            System.out.println("error");
        }
    }

    public void createPromoServer(Promo newPromo) {
        if(adminRepository.createPromoRepository(newPromo)) {
            System.out.println("inserted");
        } else {
            System.out.println("error");
        }
    }

    public String passwordHashing(String password) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        messageDigest.update(password.getBytes());
        byte[] resultByteArray = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for(Byte res:resultByteArray) {
            sb.append(String.format("%02x", res));
        }
        return sb.toString();
    }

    public ResultSet getALLFormerServer() {
        ResultSet formers = null;
        return  formers = adminRepository.getAllFormersRepository();
    }

    public ResultSet getALLLearnerServer() {
        ResultSet learners = null;
        return  learners = adminRepository.getAllLearnersRepository();
    }

    public ResultSet getALLPromotionsServer() {
        ResultSet promotions = null;
        return  promotions = adminRepository.getAllPromotionsRepository();
    }

    public void addFormerToPromoServer(int idFormer, int idPromo) {
        if(adminRepository.addFormerToPromoRepository(idFormer, idPromo))
            System.out.println("added");
        else
            System.out.println("not added!!!");
    }

    public boolean checkIdServer(int idFormer, int idPromo) {
        ResultSet data = null;
        data = adminRepository.checkIdRepository(idFormer, idPromo);
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
}
