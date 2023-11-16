package MusicPlatform;

import java.util.ArrayList;

public class Playlist {
    private ArrayList<Song> songs;
    private int current;
    public Playlist()
    {
        songs=new ArrayList<>();
        current=0;
    }
    public Playlist(ArrayList<Song> songs)
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
    public void shuffleSongs()
    {
        for(int i=0;i<songs.size();i++)
        {
            int random=(int)(Math.random()*songs.size());
            Song temp=songs.get(i);
            songs.set(i,songs.get(random));
            songs.set(random,temp);
        }
    }
    public int getSize()
    {
        return songs.size();
    }
    public Song currentSong()
    {
     if(current>=songs.size())
         current=0;
     else
         current++;
        return songs.get(current);
    }
}
