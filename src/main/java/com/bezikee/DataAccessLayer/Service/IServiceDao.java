package com.bezikee.DataAccessLayer.Service;

import java.util.ArrayList;

public interface IServiceDao {

    boolean create(ServiceBean toCreate);

    ArrayList<ServiceBean> readAll();

    ServiceBean read(int idToRead);

    boolean update(ServiceBean toUpdate);

    boolean delete(int idToDelete);

    ArrayList<ServiceBean> readByCategory(String category);
}
