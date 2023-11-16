package MusicPlatform;

public class FreeUser implements User{
    private final int capacity=10;
    private Playlist playlist;
    public FreeUser()
    {
        playlist=new Playlist();
    }
    public void listen()
    {
        playlist.currentSong().playSong();
    }
    public void addToPlaylist(Song song)
    {
        if(playlist.getSize()<capacity)
            playlist.addSong(song);
        else
            System.out.println("Playlist is full");
    }
}
