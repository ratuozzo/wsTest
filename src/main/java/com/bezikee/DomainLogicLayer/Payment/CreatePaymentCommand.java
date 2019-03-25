package com.bezikee.DomainLogicLayer.Payment;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Payment.IPaymentDao;
import com.bezikee.DataAccessLayer.Payment.PaymentBean;
import com.bezikee.DomainLogicLayer.Command;

public class CreatePaymentCommand extends Command {

    private PaymentBean _bean;
    private boolean _status;
    private String _message;

    public CreatePaymentCommand(PaymentBean bean){

        _bean = bean;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing CreatePaymentCommand");

        IPaymentDao dao = DaoFactory.instantiatePaymentDao();


        if (dao.create(_bean) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Payment Created Successfully!"));
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
