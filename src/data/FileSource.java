package data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

// Klasa wczytująca teksty z plików na lokalnym dysku.

public class FileSource implements Source {
	
	private String source;
	
	public FileSource(String source) {
		this.source = source;
	}

	@Override
	public List<Lyric> read(String artist) {
		ArrayList<Lyric> lyricList = new ArrayList<Lyric>();
		String path = source.concat("/").concat(artist);
		byte[] bytes;
		String text;
		File[] files = new File(path).listFiles();
		if (files != null) {
			for (File file: files) {
				bytes = null;
				try {
					bytes = Files.readAllBytes(file.toPath());
					text = new String(bytes, StandardCharsets.UTF_8);
					lyricList.add(new Lyric(text));
				} catch (IOException e) {
					System.err.println("Could not read file: " + file);
				}
			}
		}
		return lyricList;
	}
	
}
