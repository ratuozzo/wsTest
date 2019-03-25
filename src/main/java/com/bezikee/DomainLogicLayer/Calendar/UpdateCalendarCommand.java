package com.bezikee.DomainLogicLayer.Calendar;

import com.bezikee.Common.GsonOps;
import com.bezikee.Common.LoggerOps;
import com.bezikee.DataAccessLayer.Calendar.CalendarBean;
import com.bezikee.DataAccessLayer.Calendar.ICalendarDao;
import com.bezikee.DataAccessLayer.DaoFactory;
import com.bezikee.DomainLogicLayer.Command;

public class UpdateCalendarCommand extends Command {

    private CalendarBean _calendar;
    private boolean _status;
    private String _message;

    public UpdateCalendarCommand(CalendarBean calendar){

        _calendar = calendar;

    }

    @Override
    public void execute() {
        LoggerOps.debug("Executing UpdateCalendarCommand");

        ICalendarDao dao = DaoFactory.instantiateCalendarDao();


        if (dao.update(_calendar) == false) {
            setStatus(false);
            setMessage(GsonOps.toJson("Server Side Error"));
        } else {
            setStatus(true);
            setMessage(GsonOps.toJson("Calendar Updated Successfully!"));
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
