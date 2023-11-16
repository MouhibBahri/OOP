package Library;
public class Book {
    private String Title;
    private String Author;
    private int YearOfProduction;

    public Book(String Title,String Author,int YearOfProduction)
    {
        this.Title=Title;
        this.Author=Author;
        this.YearOfProduction=YearOfProduction;
    }
    public String getTitle()
    {
        return Title;
    }
    public String getAuthor()
    {
        return Author;
    }
    public int getYearOfProduction()
    {
        return YearOfProduction;
    }
    public void displayInofrmation()
    {
        System.out.print("Title: "+Title+", Author: "+Author+", Year of production: "+getYearOfProduction());
    }
}
