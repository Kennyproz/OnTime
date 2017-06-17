package Classes.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ken on 14-6-2017.
 */
public class Database {
    Properties porps;
    Connection myConn;

    public Database() {

    }

    public Database(Properties porps, Connection myConn) {
        this.porps = porps;
        this.myConn = myConn;
    }

    public void closeConnection() throws SQLException {
        this.myConn.close();
    }

    public void initConnection() throws SQLException{
        String databaseUrl = "jdbc:mysql://studmysql01.fhict.local/dbi357714";
        String user = "dbi357714";
        String pass = "P@ssw0rd";
        myConn = DriverManager.getConnection(databaseUrl,user,pass );
        System.out.println("Database connection established!");

    }
}
