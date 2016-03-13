package data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


public class Azlyrics implements Source {

	private String source;
	private static final String begin = "<div>";
	private static final String end = "</div>";
	
	public Azlyrics(String source) {
		this.source = source;
	}

	@Override
	public List<Lyric> read(String artist) {
		List<Lyric> lyricList = new ArrayList<Lyric>();
		/* W tym miejscu powinnoby się znaleźć ewentualne wywołanie funkcji wczytującej 
		 * teksty utworów z dysku lokalnego, gdyby zostałyby one wcześniej na nim zapisane.
		 */
		String webartist = "";
		for (int i = 0; i < artist.length(); i++) {
			if (artist.charAt(i) != ' ') webartist += Character.toLowerCase(artist.charAt(i));
		}
		String url = source + "/" + webartist.charAt(0) + "/" + webartist + ".html";
		Document doc;
		/*try {
			// Opóźnienie dodane w celu uniknięcia bana na azlyrics.com.
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		try {
			doc = Jsoup.connect(url).get();
			for (Element e: doc.select("a[href^=\"../lyrics\"")) {
				lyricList.add(readLyric(e.attr("abs:href")));
			}
		} catch (IOException e) {
			System.err.println("Could not read page: " + url);
		}
		// Tutaj powinienby się znaleźć ewentualny zapis pobranych tekstów na dysk.
		return lyricList;
	}
	
	// Wczytuje tekst utworu ze strony o podanym adresie url.
	private static Lyric readLyric(String url) {
		/*try {
			// Opóźnienie dodane w celu uniknięcia bana na azlyrics.com.
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		String text = "";
		try {
			String html = Jsoup.connect(url).get().html();
			String[] tokens = html.split("\n");
			if (tokens != null) {
				int index = 0;
				for (index = 0; index < tokens.length; index++) {
					if (tokens[index].contains(begin)) {
						break;
					}
				}
				for (index += 2; index < tokens.length; index++) {
					if (tokens[index].contains(end)) break;
					else text += tokens[index].replaceAll("<br>", "");
				}
			}
		} catch (IOException e) {
			System.err.println("Could not read lyric: " + url);
		}
		return new Lyric(text);
	}
	
}
