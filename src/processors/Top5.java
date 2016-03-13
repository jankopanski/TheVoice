package processors;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import data.Data;
import data.DataException;
import data.Filter;
import data.Lyric;


public class Top5 extends Processor {

	@Override
	public void run(Data data) throws DataException {
		// Zliczanie słów z uwzględnieniem filtrów.
		System.out.println("top5:");
		if (data == null) throw new DataException("Invalid data");
		for (String artist: data.getArtists()) {
			List<Lyric> lyricList = data.getSource().read(artist);
			Map<String, Integer> topWords = new HashMap<String, Integer>();
			Filter filter = data.getFilter();
			for (Lyric lyric: lyricList) {
				for (String word: lyric) {
					if (!filter.contains(word)) {
						if (topWords.containsKey(word)) {
							topWords.put(word, topWords.get(word) + 1);
						}
						else {
							topWords.put(word, 1);
						}
					}
				}
			}
			// Wypisywaie 5 najczęściej występujących słów.
			System.out.print(artist + ":  ");
			int curnumber = 0;
			final int endnumber = Math.min(5, topWords.size());
			int bestvalue, lastvalue = Integer.MAX_VALUE;
			List<String> wordList = new ArrayList<String>();
			while (curnumber < endnumber) {
				bestvalue = 0;
				for (Map.Entry<String, Integer> entry: topWords.entrySet()) {
					int value = entry.getValue();
					if (value == bestvalue) {
						wordList.add(entry.getKey());
					}
					else if (value > bestvalue && value < lastvalue) {
						wordList.clear();
						wordList.add(entry.getKey());
						bestvalue = value;
					}
				}
				Collections.sort(wordList);
				Iterator<String> it = wordList.iterator();
				while (it.hasNext() && curnumber < endnumber) {
					System.out.print(it.next() + "=" + bestvalue + "  ");
					curnumber++;
				}
				lastvalue = bestvalue;
			}
			System.out.println();
		}
	}

}
