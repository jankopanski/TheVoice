package processors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import data.Data;
import data.DataException;
import data.Lyric;


public class Count extends Processor {

	@Override
	public void run(Data data) throws DataException {
		System.out.println("count:");
		if (data == null) throw new DataException("Invalid data");
		List<Pair> output = new ArrayList<Pair>();
		for (String artist: data.getArtists()) {
			List<Lyric> lyricList = data.getSource().read(artist);
			Set<String> uniqueWords = new HashSet<String>();
			for (Lyric lyric: lyricList) {
				for (String word: lyric) {
					uniqueWords.add(word);
				}
			}
			output.add(new Pair(artist, uniqueWords.size()));
		}
		Collections.sort(output);
		for (Pair pair: output) {
			System.out.println(pair.key + "  " + pair.value);
		}
	}

}

// Para String, Integer
// Porównywanie w kolejności malejących wartości [value], a następnie alfabetycznie po kluczach [key]
class Pair implements Comparable<Pair> {

	protected String key;
	protected Integer value;

	protected Pair(String key, int value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public int compareTo(Pair pair) {
		int com = value.compareTo(pair.value);
		if (com > 0) return -1;
		else if (com < 0) return 1;
		else return key.compareTo(pair.key);
	}

}