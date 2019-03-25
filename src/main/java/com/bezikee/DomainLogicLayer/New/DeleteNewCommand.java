package com.bezikee.DomainLogicLayer.New;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DomainLogicLayer.Command;

public class DeleteNewCommand extends Command {

    private int _id;
    private boolean _status;
    private String _message;

    public DeleteNewCommand(int id){

        _id = id;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing DeleteNewCommand");

        INewDao dao = DaoFactory.instantiateNewDao();


        if ( !dao.delete(_id)) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("New Deleted Successfully"));
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
