package com.bezikee.DataAccessLayer.Service;

import com.bezikee.Common.DateOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceDao implements IServiceDao {

    private final static Logger logger = LoggerFactory.getLogger(ServiceDao.class);

    public boolean create(ServiceBean input) {
        LoggerOps.debug("ServiceDao - create");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call CreateService (?,?,?,?)}");
            Sentence.setString(1, input.getName());
            Sentence.setString(2, input.getCurrency());
            Sentence.setFloat(3, input.getBasePrice());
            Sentence.setString(4, input.getCategory());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "ServiceDao - create", e.toString() );
        }



        return output;
    }

    public ServiceBean read(int id) {
        LoggerOps.debug("ServiceDao - read");

        Dao dao = new Dao();
        ServiceBean output = null;
        ResultSet rs;
        CallableStatement Sentence = dao.getCallableSentence("{Call GetService (?)}");

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

    public ArrayList<ServiceBean> readAll(){
        LoggerOps.debug("ServiceDao - readAll");

        Dao dao = new Dao();
        ArrayList<ServiceBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetAllService ()} ");


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }

    public boolean update(ServiceBean input) {

        LoggerOps.debug("ServiceDao - update");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call UpdateService (?,?,?,?,?)}");

            Sentence.setString(1, input.getName());
            Sentence.setString(2, input.getCurrency());
            Sentence.setFloat(3, input.getBasePrice());
            Sentence.setString(4, input.getCategory());
            Sentence.setInt(5, input.getId());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "ServiceDao - Update", e.toString() );
        }

        return output;
    }


    public boolean delete(int id) {
        LoggerOps.debug("ServiceDao - delete");

        Dao dao = new Dao();
        boolean output;
        CallableStatement Sentence = dao.getCallableSentence("{Call DeleteService (?)}");


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

    public ArrayList<ServiceBean> readByCategory(String category){
        LoggerOps.debug("ServiceDao - readByService");

        Dao dao = new Dao();
        ArrayList<ServiceBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetServiceByCategory(?)} ");

        try {
            Sentence.setString(1,category );
        } catch (SQLException e) {
            e.printStackTrace();
        }


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }


    private ArrayList<ServiceBean> getResponseArrayListBD(ResultSet rs){
        LoggerOps.debug("ServiceDao - getResponseArrayListBD");

        ArrayList<ServiceBean> output = new ArrayList<ServiceBean>();

        try {
            while (rs.next()){
                ServiceBean aux = new ServiceBean(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("currency"),
                        rs.getFloat("basePrice"),
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

    private ServiceBean getResponseBD(ResultSet rs) throws NullPointerException, SQLException{
        LoggerOps.debug("ServiceDao - getResponseBD");

        ServiceBean output = null;

        while (rs.next()){
            output = new ServiceBean(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("currency"),
                    rs.getFloat("basePrice"),
                    rs.getString("category")
            );
        }

        return output;

    }

}
