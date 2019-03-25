package com.bezikee.DataAccessLayer;

import com.bezikee.DataAccessLayer.Calendar.CalendarDao;
import com.bezikee.DataAccessLayer.Donation.DonationDao;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DataAccessLayer.New.NewDao;
import com.bezikee.DataAccessLayer.Payment.IPaymentDao;
import com.bezikee.DataAccessLayer.Payment.PaymentDao;
import com.bezikee.DataAccessLayer.Service.IServiceDao;
import com.bezikee.DataAccessLayer.Service.ServiceDao;
import com.bezikee.DataAccessLayer.User.UserDao;

public class DaoFactory
{
    public static UserDao instantiateUserDao(){
        return new UserDao();
    }

    public static DonationDao instantiateDonationDao() {
      return new DonationDao();
    }

    public static CalendarDao instantiateCalendarDao() {
      return new CalendarDao();
    }

    public static NewDao instantiateNewDao() {
      return new NewDao();
    }

    public static ServiceDao instantiateServiceDao() {
        return new ServiceDao();
    }

    public static PaymentDao instantiatePaymentDao() {
        return new PaymentDao();
    }
}
