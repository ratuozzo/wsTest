package com.bezikee.DomainLogicLayer.User;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

public class GetUserCommand extends Command {

    private int _userId;
    private boolean _status;
    private String _message;

    public GetUserCommand(int userId){

        _userId = userId;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing GetUserCommand");

        IUserDao dao = DaoFactory.instantiateUserDao();

        UserBean user = dao.read(_userId);
        if ( user == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(user));
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
