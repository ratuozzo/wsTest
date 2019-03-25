package com.bezikee.DomainLogicLayer.Donation;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.DonationBean;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

public class GetDonationCommand extends Command {

    private int _donationId;
    private boolean _status;
    private String _message;

    public GetDonationCommand(int donationId){

        _donationId = donationId;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing GetDonationCommand");

        IDonationDao dao = DaoFactory.instantiateDonationDao();

        DonationBean donation = dao.read(_donationId);
        if ( donation == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(donation));
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
