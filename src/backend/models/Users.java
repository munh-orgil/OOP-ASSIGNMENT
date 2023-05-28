package backend.models;

public class Users implements Comparable<Users> {
    public Integer Id;
    public String RegNo;
    public String LastName;
    public String FirstName;
    public Integer Gender;

    @Override
    public int compareTo(Users u) {
        return Id.compareTo(u.Id);
    }

    @Override
    public String toString() {
        return LastName.charAt(0) + ". " + FirstName;
    }
}