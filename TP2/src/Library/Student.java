package Library;

public class Student implements LibraryUser{
    private int CIN;
    private String nom;
    private LibraryCard card;
    public Student(int CIN,String nom)
    {
        this.CIN=CIN;
        this.nom=nom;
        card=new LibraryCard();
    }
    public void borrowBook(Book book)
    {
        Library.removeBook(book);
    }
    public void returnBook(Book book)
    {
        Library.addBook(book);
    }
}
