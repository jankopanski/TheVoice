package data;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ArtistsSet implements Artists {

	private Set<String> names;
	
	public ArtistsSet() {
		names = new HashSet<String>();
	}
	
	@Override
	public void add(String name) {
		names.add(name);
	}

	@Override
	public Iterator<String> iterator() {
		return names.iterator();
	}

}
