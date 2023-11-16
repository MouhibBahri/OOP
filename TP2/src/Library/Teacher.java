package Library;

public class Teacher implements LibraryUser{
    private int CIN;
    private String nom;
    private LibraryCard card;
    public Teacher(int CIN,String nom)
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
