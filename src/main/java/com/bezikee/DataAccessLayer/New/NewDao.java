package com.bezikee.DataAccessLayer.New;

import com.bezikee.Common.DateOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NewDao implements INewDao {

    private final static Logger logger = LoggerFactory.getLogger(NewDao.class);

    public boolean create(NewBean input) {
        LoggerOps.debug("NewDao - create");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call CreateNew (?,?,?,?,?)}");
            Sentence.setString(1, input.getTitle());
            Sentence.setString(2, input.getContent());
            Sentence.setString(3, input.getImage());
            Sentence.setString(4, input.getVideo());
            Sentence.setString(5, input.getDate());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "NewDao - create", e.toString() );
        }



        return output;
    }

    public NewBean read(int id) {
        LoggerOps.debug("NewDao - read");

        Dao dao = new Dao();
        NewBean output = null;
        ResultSet rs;
        CallableStatement Sentence = dao.getCallableSentence("{Call GetNew (?)}");

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

    public ArrayList<NewBean> readAll(){
        LoggerOps.debug("NewDao - readAll");

        Dao dao = new Dao();
        ArrayList<NewBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetAllNew ()} ");


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }

    public boolean update(NewBean input) {

        LoggerOps.debug("NewDao - update");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call UpdateNew (?,?,?,?,?,?)}");

            Sentence.setString(1, input.getTitle());
            Sentence.setString(2, input.getContent());
            Sentence.setString(3, input.getImage());
            Sentence.setString(4, input.getVideo());
            Sentence.setString(5, input.getDate());
            Sentence.setInt(6, input.getId());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "NewDao - Update", e.toString() );
        }

        return output;
    }


    public boolean delete(int id) {
        LoggerOps.debug("NewDao - delete");

        Dao dao = new Dao();
        boolean output;
        CallableStatement Sentence = dao.getCallableSentence("{Call DeleteNew (?)}");


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


    private ArrayList<NewBean> getResponseArrayListBD(ResultSet rs){
        LoggerOps.debug("NewDao - getResponseArrayListBD");

        Dao dao = new Dao();
        ArrayList<NewBean> output = new ArrayList<NewBean>();

        try {
            while (rs.next()){
                NewBean aux = new NewBean(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("image"),
                        rs.getString("video"),
                        rs.getString("date")
                );
                output.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }

        return output;

    }

    private NewBean getResponseBD(ResultSet rs) throws NullPointerException, SQLException{
        LoggerOps.debug("NewDao - getResponseBD");

        NewBean output = null;

        while (rs.next()){
            output = new NewBean(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("content"),
                    rs.getString("image"),
                    rs.getString("video"),
                    rs.getString("date")
            );
        }

        return output;

    }

}
