package com.bezikee.DomainLogicLayer.Calendar;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Calendar.CalendarBean;
import com.bezikee.DataAccessLayer.Calendar.ICalendarDao;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.DonationBean;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DomainLogicLayer.Command;

public class CreateCalendarCommand extends Command {

    private CalendarBean _calendar;
    private boolean _status;
    private String _message;

    public CreateCalendarCommand(CalendarBean calendar){

        _calendar = calendar;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing CreateCalendarCommand");

        ICalendarDao dao = DaoFactory.instantiateCalendarDao();


        if (dao.create(_calendar) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Calendar Created Successfully!"));
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
