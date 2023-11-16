package Library;

public class Textbook extends Book{
    private String Subject;
    public Textbook(String Title,String Author,int YearOfProduction,String Subject)
    {
        super(Title,Author,YearOfProduction);
        this.Subject=Subject;
    }
    public void displayInformation()
    {
        super.displayInofrmation();
        System.out.println("Subject: "+Subject);
    }
}
