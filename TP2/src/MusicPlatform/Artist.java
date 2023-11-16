package MusicPlatform;
import java.util.ArrayList;
public class Artist {
    private ArrayList<Album> albums;
    public Artist()
    {
        albums=new ArrayList<Album>();
    }
    public Artist(ArrayList<Album> albums)
    {
        this.albums=albums;
    }
    public void addSong(Album album)
    {
        albums.add(album);
    }
    public void removeSong(Album album)
    {
        albums.remove(album);
    }
    public void listSongs()
    {
        for(int i=0;i<albums.size();i++)
        {
            System.out.println("Album n°"+i);
            albums.get(i).listSongs();
        }
    }
}
