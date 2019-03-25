package com.bezikee.DomainLogicLayer.Service;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Service.IServiceDao;
import com.bezikee.DataAccessLayer.Service.ServiceBean;
import com.bezikee.DomainLogicLayer.Command;

public class GetServiceCommand extends Command {

    private int _id;
    private boolean _status;
    private String _message;

    public GetServiceCommand(int id){

        _id = id;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing GetServiceCommand");

        IServiceDao dao = DaoFactory.instantiateServiceDao();

        ServiceBean service = dao.read(_id);
        if ( service == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(service));
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
