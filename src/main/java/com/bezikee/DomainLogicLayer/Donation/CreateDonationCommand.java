package com.bezikee.DomainLogicLayer.Donation;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.DonationBean;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DataAccessLayer.User.IUserDao;
import com.bezikee.DataAccessLayer.User.UserBean;
import com.bezikee.DomainLogicLayer.Command;

public class CreateDonationCommand extends Command {

    private DonationBean _donation;
    private boolean _status;
    private String _message;

    public CreateDonationCommand(DonationBean donation){

        _donation = donation;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing CreateCalendarCommand");

        IDonationDao dao = DaoFactory.instantiateDonationDao();


        if (dao.create(_donation) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Donation Created Successfully!"));
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
