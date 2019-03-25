package com.bezikee.DataAccessLayer.Payment;

import com.bezikee.Common.DateOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentDao implements IPaymentDao {

    private final static Logger logger = LoggerFactory.getLogger(PaymentDao.class);

    public boolean create(PaymentBean input) {
        LoggerOps.debug("PaymentDao - create");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call CreatePayment (?,?,?,?,?,?,?,?)}");
            Sentence.setInt(1, input.getServiceId());
            Sentence.setString(2, input.getName());
            Sentence.setFloat(3, input.getPersonalId());
            Sentence.setFloat(4, input.getAmount());
            Sentence.setString(5,input.getDate());
            Sentence.setString(6, input.getTransferNum());
            Sentence.setString(7,input.getEmail());
            Sentence.setString(8,input.getStatus());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "PaymentDao - create", e.toString() );
        }



        return output;
    }

    public PaymentBean read(int id) {
        LoggerOps.debug("PaymentDao - read");

        Dao dao = new Dao();
        PaymentBean output = null;
        ResultSet rs;
        CallableStatement Sentence = dao.getCallableSentence("{Call GetPayment (?)}");

        try {
            Sentence.setInt(1, id);

            rs =dao.executeQuery(Sentence);

            if(rs!=null)
                output = getResponseBD(rs);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }
        dao.close();

        return output;


    }

    public ArrayList<PaymentBean> readAll(){
        LoggerOps.debug("PaymentDao - readAll");

        Dao dao = new Dao();
        ArrayList<PaymentBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetAllPayment ()} ");


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }

    public boolean update(PaymentBean input) {

        LoggerOps.debug("PaymentDao - update");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call UpdatePayment (?,?,?,?,?,?,?,?,?)}");

            Sentence.setInt(1, input.getServiceId());
            Sentence.setString(2, input.getName());
            Sentence.setFloat(3, input.getPersonalId());
            Sentence.setFloat(4, input.getAmount());
            Sentence.setString(5,input.getDate());
            Sentence.setString(6, input.getTransferNum());
            Sentence.setString(7,input.getEmail());
            Sentence.setString(8,input.getStatus());
            Sentence.setInt(9, input.getId());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "PaymentDao - Update", e.toString() );
        }

        return output;
    }


    public boolean delete(int id) {
        LoggerOps.debug("PaymentDao - delete");

        Dao dao = new Dao();
        boolean output;
        CallableStatement Sentence = dao.getCallableSentence("{Call DeletePayment (?)}");


        try {
            Sentence.setInt(1, id);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }

        output = dao.executeCall(Sentence);

        dao.close();

        return output;
    }

    public ArrayList<PaymentBean> readByCategory(String category){
        LoggerOps.debug("PaymentDao - readByService");

        Dao dao = new Dao();
        ArrayList<PaymentBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetPaymentByCategory(?)} ");

        try {
            Sentence.setString(1,category );
        } catch (SQLException e) {
            e.printStackTrace();
        }


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBDreadByCat(rs);

        dao.close();

        return output;
    }

    private ArrayList<PaymentBean> getResponseArrayListBDreadByCat(ResultSet rs){
        LoggerOps.debug("PaymentDao - getResponseArrayListBD");

        ArrayList<PaymentBean> output = new ArrayList<PaymentBean>();

        try {
            while (rs.next()){
                PaymentBean aux = new PaymentBean(
                        rs.getInt("id"),
                        rs.getInt("serviceId"),
                        rs.getString("name"),
                        rs.getInt("personalId"),
                        rs.getFloat("amount"),
                        rs.getString("date"),
                        rs.getString("transferNum"),
                        rs.getString("email"),
                        rs.getString("status"),
                        rs.getString("serviceName"),
                        rs.getString("currency"),
                        rs.getString("category")
                );
                output.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }

        return output;

    }


    private ArrayList<PaymentBean> getResponseArrayListBD(ResultSet rs){
        LoggerOps.debug("PaymentDao - getResponseArrayListBD");

        ArrayList<PaymentBean> output = new ArrayList<PaymentBean>();

        try {
            while (rs.next()){
                PaymentBean aux = new PaymentBean(
                        rs.getInt("id"),
                        rs.getInt("Service_id"),
                        rs.getString("name"),
                        rs.getInt("personalId"),
                        rs.getFloat("amount"),
                        rs.getString("date"),
                        rs.getString("transferNum"),
                        rs.getString("email"),
                        rs.getString("status")
                );
                output.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }

        return output;

    }

    private PaymentBean getResponseBD(ResultSet rs) throws NullPointerException, SQLException{
        LoggerOps.debug("PaymentDao - getResponseBD");

        PaymentBean output = null;

        while (rs.next()){
            output = new PaymentBean(
                    rs.getInt("id"),
                    rs.getInt("Service_id"),
                    rs.getString("name"),
                    rs.getInt("personalId"),
                    rs.getFloat("amount"),
                    rs.getString("date"),
                    rs.getString("transferNum"),
                    rs.getString("email"),
                    rs.getString("status")
            );
        }

        return output;

    }

}
