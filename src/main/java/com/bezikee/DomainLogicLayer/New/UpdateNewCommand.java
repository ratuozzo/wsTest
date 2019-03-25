package com.bezikee.DomainLogicLayer.New;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.New.NewBean;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DomainLogicLayer.Command;

public class UpdateNewCommand extends Command {

    private NewBean _bean;
    private boolean _status;
    private String _message;

    public UpdateNewCommand(NewBean bean){

        _bean = bean;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing UpdateNewCommand");

        INewDao dao = DaoFactory.instantiateNewDao();


        if (dao.update(_bean) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("New Updated Successfully!"));
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
