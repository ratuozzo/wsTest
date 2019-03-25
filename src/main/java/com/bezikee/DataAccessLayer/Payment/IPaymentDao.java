package com.bezikee.DataAccessLayer.Payment;

import java.util.ArrayList;

public interface IPaymentDao {

    boolean create(PaymentBean toCreate);

    ArrayList<PaymentBean> readAll();

    PaymentBean read(int idToRead);

    boolean update(PaymentBean toUpdate);

    boolean delete(int idToDelete);

    ArrayList<PaymentBean> readByCategory(String category);
}
