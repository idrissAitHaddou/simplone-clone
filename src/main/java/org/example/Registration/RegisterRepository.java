package org.example.Registration;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import org.example.admin.AdminRepository;
import org.example.admin.AdminServer;
import org.example.database.ConnectPostgresql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegisterRepository {
    private AdminServer adminServer = new AdminServer();
    public ResultSet checkForLogin(String role, String getEmail, String getPassword) {
        String passwordHashing = adminServer.passwordHashing(getPassword);
        ResultSet resultats = null;
        String query;
        if(role.equals("former"))
            query = "select f.* from former as f, promotion as p where f.email like '" + getEmail + "' and f.password like '" + passwordHashing + "' and p.id_former = f.id";
         else
            query = "select * from " + role + " where email like '" + getEmail + "' and password like '" + passwordHashing + "'";
        try {
            Statement stmt = ConnectPostgresql.getConnection().createStatement();
            resultats = stmt.executeQuery(query);
        } catch (SQLException ex) {
                System.out.println(ex.getMessage());
        }
        return resultats;
    }
}
