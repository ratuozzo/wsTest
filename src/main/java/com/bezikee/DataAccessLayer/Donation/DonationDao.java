package com.bezikee.DataAccessLayer.Donation;

import com.bezikee.Common.DateOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DonationDao implements IDonationDao{

    private final static Logger logger = LoggerFactory.getLogger(DonationDao.class);

    public boolean create(DonationBean input) {
        LoggerOps.debug("DonationDao - create");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call CreateDonation (?,?,?,?,?,?,?,?,?)}");
            Sentence.setString(1, input.getName());
            Sentence.setString(2, input.getEmail());
            Sentence.setFloat(3, input.getAmount());
            Sentence.setString(4, input.getDescription());
            Sentence.setString(5, input.getPurpose());
            Sentence.setString(6, input.getCurrency());
            Sentence.setString(7, input.getDate());
            Sentence.setString(8, input.getTransferNum());
            Sentence.setString(9, input.getStatus());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "DonationDao - create", e.toString() );
        }



        return output;
    }

    public DonationBean read(int id) {
        LoggerOps.debug("DonationDao - read");

        Dao dao = new Dao();
        DonationBean output = null;
        ResultSet rs;
        CallableStatement Sentence = dao.getCallableSentence("{Call GetDonation (?)}");

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

    public ArrayList<DonationBean> readAll(){
        LoggerOps.debug("DonationDao - readAll");

        Dao dao = new Dao();
        ArrayList<DonationBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetAllDonation ()} ");


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }

    public boolean update(DonationBean input) {

        LoggerOps.debug("DonationDao - update");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call UpdateDonation (?,?,?,?,?,?,?,?,?,?)}");

            Sentence.setString(1, input.getName());
            Sentence.setString(2, input.getEmail());
            Sentence.setFloat(3, input.getAmount());
            Sentence.setString(4, input.getDescription());
            Sentence.setString(5, input.getPurpose());
            Sentence.setString(6, input.getCurrency());
            Sentence.setString(7, input.getDate());
            Sentence.setString(8, input.getTransferNum());
            Sentence.setString(9, input.getStatus());
            Sentence.setInt(10, input.getId());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "DonationDao - Update", e.toString() );
        }

        return output;
    }


    public boolean delete(int id) {
        LoggerOps.debug("DonationDao - delete");

        Dao dao = new Dao();
        boolean output;
        CallableStatement Sentence = dao.getCallableSentence("{Call DeleteDonation (?)}");


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


    private ArrayList<DonationBean> getResponseArrayListBD(ResultSet rs){
        LoggerOps.debug("DonationDao - getResponseArrayListBD");

        ArrayList<DonationBean> output = new ArrayList<DonationBean>();

        try {
            while (rs.next()){
                DonationBean aux = new DonationBean(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("amount"),
                        rs.getString("description"),
                        rs.getString("purpose"),
                        rs.getString("currency"),
                        rs.getString("date"),
                        rs.getString("email"),
                        rs.getString("transferNum"),
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

    private DonationBean getResponseBD(ResultSet rs) throws NullPointerException, SQLException{
        LoggerOps.debug("DonationDao - getResponseBD");

        DonationBean output = null;

        while (rs.next()){
            output = new DonationBean(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getFloat("amount"),
                    rs.getString("description"),
                    rs.getString("purpose"),
                    rs.getString("currency"),
                    rs.getString("date"),
                    rs.getString("email"),
                    rs.getString("transferNum"),
                    rs.getString("status")
            );
        }

        return output;

    }

}
