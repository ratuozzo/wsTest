package com.bezikee.DomainLogicLayer.Calendar;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Calendar.CalendarBean;
import com.bezikee.DataAccessLayer.Calendar.ICalendarDao;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.DonationBean;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DomainLogicLayer.Command;

import java.util.ArrayList;

public class GetAllCalendarCommand extends Command {

    private boolean _status;
    private String _message;


    @Override
    public void execute() {
        LoggerOps.debug("Executing GetAllCalendarCommand");

        ICalendarDao dao = DaoFactory.instantiateCalendarDao();

        ArrayList<CalendarBean> calendars = dao.readAll();
        if ( calendars == null) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson(calendars));
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
