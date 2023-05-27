package backend.models;

public class Questions implements Comparable<Questions> {
    public Integer Id;
    public String Title;
    public String Description;
    public Integer ExamId;
    public String Type;
    public Integer Ord;

    @Override
    public int compareTo(Questions q) {
        return Ord.compareTo(q.Ord);
    }
}