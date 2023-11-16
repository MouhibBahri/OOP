package MusicPlatform;

public class PremiumUser implements User{
    private Playlist playlist;

    public void listen()
    {
        playlist.currentSong().playSong();
    }
    public void addToPlaylist(Song song)
    {
            playlist.addSong(song);
    }
}
