package backend.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exams implements Comparable<Exams> {
    public Integer Id;
    public String Title;
    public String Description;
    public Integer ClassId;
    public String StartDate;
    public String StartTime;
    public Integer Duration;
    public String EndDate;
    public String EndTime;

    @Override
    public int compareTo(Exams e) {
        LocalDate date = LocalDate.parse(StartDate);
        LocalDate edate = LocalDate.parse(e.StartDate);
        LocalTime time = LocalTime.parse(e.StartTime);
        LocalTime etime = LocalTime.parse(e.StartTime);

        int res = date.compareTo(edate);
        if(res == 0) {
            return time.compareTo(etime);
        }
        return res;
    }
}