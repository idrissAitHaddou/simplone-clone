package org.example.admin;

import org.example.Former.Former;
import org.example.Learner.Learner;
import org.example.Promotion.Promo;
import org.example.database.ConnectPostgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminRepository {
    public boolean createFormerRepository(Former newFormer) {
        String passwordHashing;
        AdminServer adminServer = new AdminServer();
        passwordHashing = adminServer.passwordHashing(newFormer.password);
        String query = String.format("insert into former(first_name, last_name, email, password) values('%s', '%s', '%s', '%s')", newFormer.firstName, newFormer.lastName, newFormer.email, passwordHashing);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean createLearnerRepository(Learner newLearner) {
        String passwordHashing;
        AdminServer adminServer = new AdminServer();
        passwordHashing = adminServer.passwordHashing(newLearner.password);
        String query = String.format("insert into learner(first_name, last_name, email, password) values('%s', '%s', '%s', '%s')", newLearner.firstName, newLearner.lastName, newLearner.email, passwordHashing);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public boolean createPromoRepository(Promo newPromo) {
        String query = String.format("insert into promotion(name) values('%s')", newPromo.name);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public ResultSet getAllFormersRepository() {
        ResultSet formers = null;
        String query = "select * from former";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            formers = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  formers;
    }

    public ResultSet getAllLearnersRepository() {
        ResultSet learners = null;
        String query = "select * from learner";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            learners = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  learners;
    }

    public ResultSet getAllPromotionsRepository() {
        ResultSet promotions = null;
        String query = "select * from promotion";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            promotions = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  promotions;
    }

    public boolean addFormerToPromoRepository(int idFormer, int idPromo) {
        String query = String.format("update promotion set id_former = %d where id = %d", idFormer, idPromo);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  false;
    }

    public ResultSet checkIdRepository(int idFormer, int idPromo) {
        ResultSet data = null;
        String query = String.format("select * from promotion p, former f where p.id = %d and f.id = %d", idPromo, idFormer);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            data = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  data;
    }
}
