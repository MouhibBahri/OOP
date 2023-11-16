package Library;

public class Novel extends Book{
    private String Genre;
    public Novel(String Title,String Author,int YearOfProduction,String Genre)
    {
        super(Title,Author,YearOfProduction);
        this.Genre=Genre;
    }
    public void displayInformation()
    {
        super.displayInofrmation();
        System.out.println("Genre: "+Genre);
    }
}
