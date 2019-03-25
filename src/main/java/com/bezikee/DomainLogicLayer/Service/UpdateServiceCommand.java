package com.bezikee.DomainLogicLayer.Service;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Service.IServiceDao;
import com.bezikee.DataAccessLayer.Service.ServiceBean;
import com.bezikee.DomainLogicLayer.Command;

public class UpdateServiceCommand extends Command {

    private ServiceBean _bean;
    private boolean _status;
    private String _message;

    public UpdateServiceCommand(ServiceBean bean){

        _bean = bean;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing UpdateServiceCommand");

        IServiceDao dao = DaoFactory.instantiateServiceDao();


        if (dao.update(_bean) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Service Updated Successfully!"));
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
