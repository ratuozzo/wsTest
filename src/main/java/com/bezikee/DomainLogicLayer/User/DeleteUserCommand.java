package com.bezikee.DomainLogicLayer.User;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

public class DeleteUserCommand extends Command {

    private int _userId;
    private boolean _status;
    private String _message;

    public DeleteUserCommand(int userId){

        _userId = userId;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing DeleteUserCommand");

        IUserDao dao = DaoFactory.instantiateUserDao();


        if ( !dao.delete(_userId)) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("User Deleted Successfully"));
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
