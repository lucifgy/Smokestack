package JavaFile;

import java.util.Objects;

public class Sabbath {
    String song;
    Integer year;
    String album;

    public Sabbath(String song, Integer year, String album) {
        this.song = song;
        this.year = year;
        this.album = album;
    }

    @Override
    public String toString() {
        return this.song;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sabbath other = (Sabbath) obj;
        return Objects.equals(year, other.year) && Objects.equals(album, other.album)
                && Objects.equals(song, other.song);
    }
}
