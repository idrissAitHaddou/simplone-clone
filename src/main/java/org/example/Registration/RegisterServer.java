package org.example.Registration;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterServer {
    private RegisterRepository registerRepository = new RegisterRepository();
    public boolean AuthServer(String role, String email, String password) {
        ResultSet loggedInfo = null;
        loggedInfo = registerRepository.checkForLogin(role, email, password);
        try {
            if(!loggedInfo.next()){
                return false;
            }else {
                adduserToLocalStorage(loggedInfo);
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void adduserToLocalStorage(ResultSet logged) {
        try {
            Storage.idLogger = logged.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
