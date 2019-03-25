package com.bezikee.DomainLogicLayer.Service;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Service.IServiceDao;
import com.bezikee.DataAccessLayer.Service.ServiceBean;
import com.bezikee.DomainLogicLayer.Command;

import java.util.ArrayList;

public class GetAllServiceCommand extends Command {

    private boolean _status;
    private String _message;


    @Override
    public void execute() {
        LoggerOps.debug("Executing GetAllServiceCommand");

        IServiceDao dao = DaoFactory.instantiateServiceDao();

        ArrayList<ServiceBean> services = dao.readAll();
        if ( services == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(services));
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
