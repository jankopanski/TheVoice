package data;
import java.util.Iterator;

// Klasa reprezentuąca wstępnie przetworzony tekst utworu.

public class Lyric implements Iterable<String> {

	private String[] words;
	
	public Lyric(String text) {
		words = text.split("[\\s,.:;?!()-]+");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].toLowerCase();
		}
	}
	
	public String[] getWords() {
		return words;
	}

	// Anonimowy iterator.
	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {

			int index = 0;
			
			@Override
			public boolean hasNext() {
				return index < words.length;
			}

			@Override
			public String next() {
				return words[index++];
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
			
		};
	}

}
