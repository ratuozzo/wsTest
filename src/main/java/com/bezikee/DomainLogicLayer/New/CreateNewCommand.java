package com.bezikee.DomainLogicLayer.New;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DataAccessLayer.New.NewBean;
import com.bezikee.DomainLogicLayer.Command;

public class CreateNewCommand extends Command {

    private NewBean _bean;
    private boolean _status;
    private String _message;

    public CreateNewCommand(NewBean bean){

        _bean = bean;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing CreateCalendarCommand");

        INewDao dao = DaoFactory.instantiateNewDao();


        if (dao.create(_bean) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("New Created Successfully!"));
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
