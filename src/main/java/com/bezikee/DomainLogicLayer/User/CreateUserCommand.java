package com.bezikee.DomainLogicLayer.User;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

public class CreateUserCommand extends Command {

    private UserBean _user;
    private boolean _status;
    private String _message;

    public CreateUserCommand(UserBean user){

        _user = user;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing CreateUserCommand");

        IUserDao dao = DaoFactory.instantiateUserDao();


        if (dao.create(_user) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("User Created Successfully!"));
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
