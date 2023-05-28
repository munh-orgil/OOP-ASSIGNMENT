package backend.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exams implements Comparable<Exams> {
    public Integer Id;
    public String Title;
    public String Description;
    public Integer ClassId;
    public LocalDate StartDate;
    public LocalTime StartTime;
    public Integer Duration;
    public LocalDate EndDate;
    public LocalTime EndTime;

    @Override
    public int compareTo(Exams e) {
        int res = StartDate.compareTo(e.StartDate);
        if(res == 0) {
            return StartTime.compareTo(e.StartTime);
        }
        return res;
    }
}