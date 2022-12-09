package DataAccessLayer.Implementation;

import DataAccessLayer.Interfaces.RepositoryInterface;
import Models.Users.User;

import java.util.ArrayList;

public class UserRepository implements RepositoryInterface<User> {
    @Override
    public Boolean add(User object) {
        return null;
    }

    @Override
    public Boolean modify(User object) {
        return null;
    }

    @Override
    public ArrayList<User> get() {
        return null;
    }

    @Override
    public ArrayList<User> getById(int id) {
        return null;
    }

    @Override
    public Boolean delete(int id) {
        return null;
    }
}
