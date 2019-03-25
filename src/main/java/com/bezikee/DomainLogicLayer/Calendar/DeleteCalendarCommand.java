package com.bezikee.DomainLogicLayer.Calendar;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Calendar.ICalendarDao;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DataAccessLayer.Donation.IDonationDao;
import com.bezikee.DomainLogicLayer.Command;

public class DeleteCalendarCommand extends Command {

    private int _calendarId;
    private boolean _status;
    private String _message;

    public DeleteCalendarCommand(int calendarId){

        _calendarId = calendarId;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing DeleteCalendarCommand");

        ICalendarDao dao = DaoFactory.instantiateCalendarDao();


        if ( !dao.delete(_calendarId)) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Calendar Deleted Successfully"));
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
