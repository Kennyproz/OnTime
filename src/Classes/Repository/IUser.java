package Classes.Repository;

import Classes.User;

/**
 * Created by Ken on 14-6-2017.
 */
public interface IUser {

    boolean login(String username,String password) ;
    boolean addUser(User user);
    User getUserInfo(int id);
}
