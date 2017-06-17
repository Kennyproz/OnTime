package Classes.Repository;

import Classes.User;

/**
 * Created by Ken on 14-6-2017.
 */
public class UserRepository {
    private IUser _context;

    public UserRepository(IUser conext){
        this._context = conext;
    }

    public boolean login(String username, String password){
        return _context.login(username,password);
    }

    public boolean addUser(User user){
        return _context.addUser(user);
    }

    public User getUserInfo(int id){
        return _context.getUserInfo(id);
    }

}
