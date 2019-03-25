package com.bezikee.DataAccessLayer.User;

import com.bezikee.Common.DateOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao implements IUserDao{

    private final static Logger logger = LoggerFactory.getLogger(UserDao.class);

    public boolean create(UserBean input) {
        LoggerOps.debug("UserDao - create");

        Dao dao = new Dao();
        CallableStatement Sentence;
        boolean output = false;
        try {
            Sentence = dao.getCallableSentence("{Call UserCreate (?,?,?,?,?,?,?)}");
            Sentence.setString(1, input.getName());
            Sentence.setString(2, input.getLastName());
            Sentence.setString(3, input.getEmail());
            Sentence.setString(4, input.getUsername());
            Sentence.setString(5, input.getPassword());
            Sentence.setString(6, input.getBirthDate());
            Sentence.setString(7, input.getSex());
            output = dao.executeCall(Sentence);
            dao.close();

        } catch (Exception e) {
            logger.error( "Method: ", "UserDao - create", e.toString() );
        }



        return output;
    }

   public UserBean read(int id) {
       LoggerOps.debug("UserDao - read");

        Dao dao = new Dao();
        UserBean output = null;
        ResultSet rs;
        CallableStatement Sentence = dao.getCallableSentence("{Call GetUser (?)}");

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

    public ArrayList<UserBean> readAll(){
        LoggerOps.debug("UserDao - readAll");

        Dao dao = new Dao();
        ArrayList<UserBean> output = null;
        ResultSet rs;

        CallableStatement Sentence = dao.getCallableSentence("{Call GetAllUser ()} ");


        rs =dao.executeQuery(Sentence);

        if(rs!=null)
            output = getResponseArrayListBD(rs);

        dao.close();

        return output;
    }

   public boolean update(UserBean input) {

       LoggerOps.debug("UserDao - update");

       Dao dao = new Dao();
       CallableStatement Sentence;
       boolean output = false;
       try {
           Sentence = dao.getCallableSentence("{Call UserUpdate (?,?,?,?,?,?,?,?)}");
           Sentence.setString(1, input.getName());
           Sentence.setString(2, input.getLastName());
           Sentence.setString(3, input.getEmail());
           Sentence.setString(4, input.getUsername());
           Sentence.setString(5, input.getPassword());
           Sentence.setString(6, input.getBirthDate());
           Sentence.setString(7, input.getSex());
           Sentence.setInt(8, input.getId());
           output = dao.executeCall(Sentence);
           dao.close();

       } catch (Exception e) {
           logger.error( "Method: ", "UserDao - Update", e.toString() );
       }

       return output;
    }


    public boolean delete(int id) {
        LoggerOps.debug("UserDao - delete");

        Dao dao = new Dao();
        boolean output;
        CallableStatement Sentence = dao.getCallableSentence("{Call DeleteUser (?)}");


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


    private ArrayList<UserBean> getResponseArrayListBD(ResultSet rs){
        LoggerOps.debug("UserDao - getResponseArrayListBD");

        ArrayList<UserBean> output = new ArrayList<UserBean>();

        try {
            while (rs.next()){
                UserBean aux = new UserBean(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("lastName"),
                        rs.getString("email"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("birthDate"),
                        rs.getString("sex"));
                output.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception: "+ e.getErrorCode());
        }

        return output;

    }

    private UserBean getResponseBD(ResultSet rs) throws NullPointerException, SQLException{
        LoggerOps.debug("UserDao - getResponseBD");

        UserBean output = null;

        while (rs.next()){
            output = new UserBean(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("lastName"),
                    rs.getString("email"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("birthDate"),
                    rs.getString("sex"));
        }

        return output;

    }

}
