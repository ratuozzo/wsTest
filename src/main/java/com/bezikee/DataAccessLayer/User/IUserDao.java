package com.bezikee.DataAccessLayer.User;

import java.util.ArrayList;

public interface IUserDao {

    boolean create (UserBean toCreate);

    ArrayList<UserBean> readAll();

    UserBean read(int idToRead);

    boolean update(UserBean toUpdate);

    boolean delete(int userId);
}
