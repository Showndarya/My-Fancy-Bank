package DataAccessLayer;

import java.util.ArrayList;

public interface RepositoryInterface<T> {
    public Boolean add(T object);
    public Boolean modify(T object);
    public ArrayList<T> get();
    public ArrayList<T> getById(int id);
    public Boolean delete(int id);
}
