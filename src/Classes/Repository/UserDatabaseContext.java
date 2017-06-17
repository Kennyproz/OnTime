package Classes.Repository;

import Classes.User;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Ken on 14-6-2017.
 */
public class UserDatabaseContext implements IUser {

    Properties properties;
    Connection connection;
    Database database = new Database(properties,connection);

    @Override
    public boolean login(String username, String password) {
        PreparedStatement myStatement;
        try{
            database.initConnection();
            myStatement = database.myConn.prepareStatement("SELECT Id FROM User WHERE Username = (?) AND Password = (?)");
            myStatement.setString(1,username);
            myStatement.setString(2,password);
            ResultSet resultSet = myStatement.executeQuery();
            if (resultSet.next()){
                myStatement.close();
                System.out.println("Logged in!");
                return true;
            }
            else {
                System.out.println("Wrong credentials");
                return false;
            }
        }
        catch (SQLException ex){
            JOptionPane.showMessageDialog(null,"Error, Connect to VPN.","Error",JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addUser(User user) {
        PreparedStatement myStatement;
        try{
            database.initConnection();
            myStatement = database.myConn.prepareStatement("INSERT INTO User (Username,Password) VALUES (?,?) ");

            myStatement.setString(1,user.getUsername());
            myStatement.setString(2,user.getPassword());
            myStatement.executeUpdate();
            myStatement.close();

            System.out.println("User saved successfully");
            return true;
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserInfo(int id) {
        User user;
        PreparedStatement myStatement;
        try{
            database.initConnection();
            myStatement = database.myConn.prepareStatement("SELECT username FROM [User] WHERE id =(?) ");
            myStatement.setInt(1,id);
            ResultSet resultSet = myStatement.executeQuery();
            while (resultSet.next()){
                String username = resultSet.getString("Username");
                String password = resultSet.getString("Password");
                user = new User(resultSet.getString("Username"),resultSet.getString("Password"));
                myStatement.close();
                return user;
            }
        }
        catch (SQLException ex){
            ex.printStackTrace();
            return null;
        }
        return null;
    }
}
