package com.bezikee.DomainLogicLayer.New;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.New.NewBean;
import com.bezikee.DataAccessLayer.New.INewDao;
import com.bezikee.DomainLogicLayer.Command;

import java.util.ArrayList;

public class GetAllNewCommand extends Command {

    private boolean _status;
    private String _message;


    @Override
    public void execute() {
        LoggerOps.debug("Executing GetAllNewCommand");

        INewDao dao = DaoFactory.instantiateNewDao();

        ArrayList<NewBean> news = dao.readAll();
        if ( news == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(news));
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
