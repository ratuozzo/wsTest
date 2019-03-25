package com.bezikee.DataAccessLayer.Donation;

import java.util.ArrayList;

public interface IDonationDao {

    boolean create (DonationBean toCreate);

    ArrayList<DonationBean> readAll();

    DonationBean read(int idToRead);

    boolean update(DonationBean toUpdate);

    boolean delete(int idToDelete);
}
