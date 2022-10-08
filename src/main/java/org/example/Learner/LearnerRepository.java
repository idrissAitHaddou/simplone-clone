package org.example.Learner;

import org.example.Registration.Storage;
import org.example.database.ConnectPostgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LearnerRepository {
    public ResultSet getAllMyBreifsLaunchedRepository() {
        ResultSet breifs = null;
        String query = String.format("select b.* from breif as b, learner l, former as f where l.id_former = f.id and b.id_former = f.id and l.id = %d and launch = 'true'", Storage.idLogger);
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            breifs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return  breifs;
    }
}
