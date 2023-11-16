package Library;

public class LibraryCard {
    private int _CardNumber;
    public static int CardNumber;
    public LibraryCard()
    {
        _CardNumber=CardNumber++;
    }
    public int getCardNumber()
    {
        return _CardNumber;

    }
}
