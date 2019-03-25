package com.bezikee.DomainLogicLayer.Donation;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DomainLogicLayer.Command;

public class DeleteDonationCommand extends Command {

    private int _donationId;
    private boolean _status;
    private String _message;

    public DeleteDonationCommand(int donationId){

        _donationId = donationId;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing DeleteDonationCommand");

        IDonationDao dao = DaoFactory.instantiateDonationDao();


        if ( !dao.delete(_donationId)) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Donation Deleted Successfully"));
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
