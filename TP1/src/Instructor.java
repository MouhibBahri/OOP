public class Instructor {
	// update
    private int instructorId;
    private String firstName;
    private String lastName;
    public Instructor(int instructorId,String firstName,String lastName)
    {
        this.instructorId=instructorId;
        this.firstName=firstName;
        this.lastName=lastName;
    }
    public int getInstructorId()
    {
        return instructorId;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getFirstName()
    {
        return firstName;
    }
}
