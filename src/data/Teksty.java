package data;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Teksty implements Source {
	
	private String source;
	
	public Teksty(String source) {
		this.source = source;
	}

	@Override
	public List<Lyric> read(String artist) {
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		/* W tym miejscu powinnoby się znaleźć ewentualne wywołanie funkcji wczytującej 
		 * teksty utworów z dysku lokalnego, gdyby zostałyby one wcześniej na nim zapisane.
		 */
		String webartist = "";
		for (int i = 0; i < artist.length(); i++) {
			webartist += artist.charAt(i) == ' ' ? '-' : Character.toLowerCase(artist.charAt(i));
		}
		String url = source + "/" + webartist + ",teksty-piosenek";
		while (url != null) {
			try {
				Document doc = Jsoup.connect(url).get();
				readPage(doc, lyricList);
				Elements next = doc.select("div.next");
				if (next == null || next.size() == 0) break;
				Element link = next.get(0).select("a").first();
				if (link == null) break;
				url = link.attr("abs:href");
			} catch (IOException e) {
				System.err.println("Could not read page: " + url);
			}
		}
		// Tutaj powinienby się znaleźć ewentualny zapis pobranych tekstów na dysk.
		return lyricList;
	}
	
	// Wczytuje odnośniki do tekstów z danej strony.
	private static void readPage(Document doc, List<Lyric> list) {
		Elements links = doc.select("a.artist");
		String url;
		for (Element e: links) {
			url = e.attr("abs:href");
			if (url.contains("tekst-piosenki")) {
				try {
					list.add(readLyric(url));
				} catch (IOException el) {
					System.err.println("Could not read lyric: " + url);
				}
			}
		}
	}
	
	// Wczytuje tekst utworu ze strony o podanym adresie url.
	private static Lyric readLyric(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		String text = doc.select("div.originalText").get(0).text();
		return new Lyric(text);
	}
	
}