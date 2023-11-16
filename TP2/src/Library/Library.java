package Library;
import java.util.ArrayList;
public class Library {
    private static ArrayList<Book> books;
    public Library()
    {
        books=new ArrayList<Book>();
    }
    public Library(ArrayList<Book> books)
    {
        this.books=books;
    }
    public static void addBook(Book book)
    {
        books.add(book);
    }
    public static void removeBook(Book book)
    {
        books.remove(book);
    }
    public static void listBooks()
    {
        for(int i=0;i<books.size();i++)
        {
            books.get(i).displayInofrmation();
        }
    }
}
