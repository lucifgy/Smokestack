package JavaFile;

import java.util.Objects;

public class Sabbath {
    Integer year;
    String song;
    String album;

    public Sabbath(Integer year, String song, String album) {
        this.year = year;
        this.song = song;
        this.album = album;
    }

    @Override
    public String toString() {
        return ""+this.year;
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
