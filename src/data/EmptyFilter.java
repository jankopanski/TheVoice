package data;

// Pusty filtr wykorzystywany, gdy parametr --filters nie zostanie podany.

public class EmptyFilter implements Filter {

	@Override
	public boolean contains(String word) {
		return false;
	}

}
