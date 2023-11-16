package MusicPlatform;

public class Song {
    private String Title;
    private int length;
    public Song(String Title,int lenght)
    {
        this.Title=Title;
        this.length=length;
    }
    public void playSong()
    {
        System.out.println("Playing "+Title);
    }
    public int getSongLength()
    {
        return length;
    }
    public String toString()
    {
        return "Song titled "+Title+" of length "+length+" seconds";
    }

}
