package com.bezikee.DataAccessLayer.New;

import com.bezikee.DataAccessLayer.New.NewBean;

import java.util.ArrayList;

public interface INewDao {

    boolean create(NewBean toCreate);

    ArrayList<NewBean> readAll();

    NewBean read(int idToRead);

    boolean update(NewBean toUpdate);

    boolean delete(int idToDelete);
}
