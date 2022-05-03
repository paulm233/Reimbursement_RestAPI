package dev.marmo.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://aai0maat8uid59.cli9xj06eeey.us-east-2.rds.amazonaws.com/postgres?user=postgres&password=dusseldorf7");

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
