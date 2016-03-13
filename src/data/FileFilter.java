package data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Klasa wczytująca i przechowująca filtry z plików.

public class FileFilter implements Filter {
	
	private Set<String> words;
	
	public FileFilter (String[] filterPaths) {
		words = new HashSet<String>();
		List<String> list;
		for (String path: filterPaths) {
			try {
				list = Files.readAllLines(new File(path).toPath(), StandardCharsets.UTF_8);
				for (String word: list) {
					words.add(word);
				}
			} catch (IOException e) {
				System.err.println("Could not read file: " + path);
			}
		}
	}
	
	public boolean contains(String s) {
		return words.contains(s);
	}
	
}
