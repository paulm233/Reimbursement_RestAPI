package dev.marmo.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    public static Connection createConnection(){
        try {
            Connection conn = DriverManager.getConnection("jdbc:postgresql://marmo-db.cli9xj06eeey.us-east-2.rds.amazonaws.com/restapi?user=postgres&password=dusseldorf7");

            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
