package com.bezikee.DomainLogicLayer.New;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.New.NewBean;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DomainLogicLayer.Command;

public class GetNewCommand extends Command {

    private int _id;
    private boolean _status;
    private String _message;

    public GetNewCommand(int id){

        _id = id;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing GetNewCommand");

        INewDao dao = DaoFactory.instantiateNewDao();

        NewBean newBean = dao.read(_id);
        if ( newBean == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(newBean));
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
