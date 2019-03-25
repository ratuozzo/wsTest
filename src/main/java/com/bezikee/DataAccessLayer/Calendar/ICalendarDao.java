package com.bezikee.DataAccessLayer.Calendar;

import java.util.ArrayList;

public interface ICalendarDao {

    boolean create (CalendarBean toCreate);

    ArrayList<CalendarBean> readAll();

    CalendarBean read(int idToRead);

    boolean update(CalendarBean toUpdate);

    boolean delete(int idToDelete);
}
