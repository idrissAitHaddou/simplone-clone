package org.example.Former;

import org.example.Registration.Storage;
import org.example.admin.AdminServer;
import org.example.brief.Breif;
import org.example.database.ConnectPostgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FormerRepository {
    public ResultSet getIDPromoServer(int idFormer) {
        ResultSet data = null;
        String query = String.format("select * from promotion where id_former = %d", idFormer);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            data = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  data;
    }

    public boolean createBreifRepository(Breif newBreif) {
        String query = String.format("insert into breif(name, id_former, id_promo) values('%s', %d, %d)", newBreif.name, newBreif.id_former, newBreif.id_promo);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public ResultSet getALLLearnerNotPromoRepository() {
        ResultSet learners = null;
        String query = "select * from learner where id_promo = 0";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            learners = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  learners;
    }

    public ResultSet getALLMyLearnerRepository() {
        ResultSet learners = null;
        String query = "select * from learner where id_former = " + Storage.idLogger + "";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            learners = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  learners;
    }

    public ResultSet getAllMyBreifsRepository() {
        ResultSet breifs = null;
        String query = "select * from breif where id_former = " + Storage.idLogger + "";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            breifs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  breifs;
    }

    public ResultSet getAllMyBreifsLaunchedRepository() {
        ResultSet breifs = null;
        String query = "select * from breif where id_former = " + Storage.idLogger + " and launch = 'true'";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            breifs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  breifs;
    }

    public ResultSet checkIdLearnerRepository(int idLearner) {
        ResultSet data = null;
        String query = String.format("select * from learner where id = %d", idLearner);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            data = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  data;
    }

    public boolean addLearnerRepository(int idPromo, int idLearner, int idLogger) {
        String query = String.format("update learner set id_promo = %d, id_former = %d where id = %d", idPromo, idLogger, idLearner);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  false;
    }

    public int getPromoInfoReposotory(int idFormer) {
        ResultSet data = null;
        String query = String.format("select * from promotion where id_former = %d", idFormer);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            data = stmt.executeQuery(query);
            if(data.next())
                return data.getInt("id");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  0;
    }

    public ResultSet checkIdBreifRepository(int idBreif) {
        ResultSet data = null;
        String query = String.format("select * from breif where id = %d and launch like 'false'", idBreif);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            data = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  data;
    }

    public boolean launchBreifRepository(int idBreif) {
        String query = String.format("update breif set launch = 'true' where id = %d", idBreif);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  false;
    }
}
