package backend.models;

public class Exams implements Comparable<Exams> {
    public Integer Id;
    public String Title;
    public String Description;
    public Integer ClassId;
    public String StartTime;
    public Integer Duration;

    @Override
    public int compareTo(Exams e) {
        return StartTime.compareTo(e.StartTime);
    }
}