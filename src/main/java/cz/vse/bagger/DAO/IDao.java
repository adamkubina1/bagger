package cz.vse.bagger.DAO;

import java.util.List;

public interface IDao<T> {
    List<T> searchEntity();
    boolean updateEntity();
    boolean deleteEntity();
    boolean addEntity();
}
