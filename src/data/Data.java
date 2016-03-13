package data;
import java.io.File;

// Klasa będąca pojemnikiem na źródła, filtry i zbiór autorów wczytanych z parametrów wywołania programu.

public class Data {
	
	private Source source;
	private Filter filter;
	private Artists artists;
	//private Set<String> artists;
	
	public Data(String[] input) throws DataException {
		
		// Przetwarzanie parametrów źródeł.
		String sourcetype = null, strsource = null;
		for (int i = 0; i < input.length; i++) {
			if (input[i].contains("--source-type=")) {
				sourcetype = input[i].substring(14);
				break;
			}
		}
		for (int i = 0; i < input.length; i++) {
			if (input[i].contains("--source=")) {
				strsource = input[i].substring(9);
				break;
			}
		}
		if (sourcetype == null) { 
			throw new DataException("No sourcetype specified");
		}
		else if (strsource == null) {
			throw new DataException("No source specified");
		}
		// Tutaj należy dodawać obsługę nowych źródeł.
		switch (sourcetype) {
		case "file": 
			source = new FileSource(strsource);
			break;
		case "teksty.org": 
			source = new Teksty(strsource);
			break;
		case "azlyrics.com": 
			source = new Azlyrics(strsource);
			break;
		}
		
		// Przetwarzanie filtrów.
		for (int i = 0; i < input.length; i++) {
			if (input[i].contains("--filters=")) {
				filter = new FileFilter(input[i].substring(10).split(File.pathSeparator));
				break;
			}
		}
		if (filter == null) {
			filter = new EmptyFilter();
		}
		
		// Tworzenie zbioru artystów.
		artists = new ArtistsSet();
		for (int i = 1; i < input.length; i++) {
			if (!input[i].contains("--")) {
				artists.add(input[i]);
			}
		}
		
	}
	
	public Artists getArtists() {
		return artists;
	}
	
	public Source getSource() {
		return source;
	}
	
	public Filter getFilter() {
		return filter;
	}
	
}
