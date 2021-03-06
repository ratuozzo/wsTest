package com.bezikee.DomainLogicLayer.Payment;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Payment.IPaymentDao;
import com.bezikee.DataAccessLayer.Payment.PaymentBean;
import com.bezikee.DomainLogicLayer.Command;

import java.util.ArrayList;

public class GetAllPaymentCommand extends Command {

    private boolean _status;
    private String _message;


    @Override
    public void execute() {
        LoggerOps.debug("Executing GetAllPaymentCommand");

        IPaymentDao dao = DaoFactory.instantiatePaymentDao();

        ArrayList<PaymentBean> payments = dao.readAll();
        if ( payments == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(payments));
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
