package MusicPlatform;
import java.util.ArrayList;
public class Album {
    private ArrayList<Song> songs;
    public Album()
    {
        songs=new ArrayList<Song>();
    }
    public Album(ArrayList<Song> songs)
    {
        this.songs=songs;
    }
    public void addSong(Song song)
    {
        songs.add(song);
    }
    public void removeSong(Song song)
    {
        songs.remove(song);
    }
    public void listSongs()
    {
        for(int i=0;i<songs.size();i++)
            System.out.println(songs.get(i).toString());
    }

}
