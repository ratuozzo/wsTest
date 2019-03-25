package com.bezikee.DomainLogicLayer.Payment;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Payment.IPaymentDao;
import com.bezikee.DomainLogicLayer.Command;

public class DeletePaymentCommand extends Command {

    private int _id;
    private boolean _status;
    private String _message;

    public DeletePaymentCommand(int id){

        _id = id;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing DeletePaymentCommand");

        IPaymentDao dao = DaoFactory.instantiatePaymentDao();


        if ( !dao.delete(_id)) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Payment Deleted Successfully"));
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
