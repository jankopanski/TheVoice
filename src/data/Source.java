package data;
import java.util.List;


public interface Source {
	public List<Lyric> read(String artist);
}
