package com.bezikee.DomainLogicLayer.User;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

import java.util.ArrayList;

public class GetAllUserCommand extends Command {

    private boolean _status;
    private String _message;


    @Override
    public void execute() {
        LoggerOps.debug("Executing GetAllUserCommand");

        IUserDao dao = DaoFactory.instantiateUserDao();

        ArrayList<UserBean> users = dao.readAll();
        if ( users == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(users));
        }


    }

    public void setStatus(boolean status) {
        _status = status;
    }

    public void setMessage(String message) {
        _message = message;
    }

    @Override
    public boolean getStatus() {
        return _status;
    }

    @Override
    public String getMessage() {
        return _message;
    }
}
