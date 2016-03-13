package data;

// Wyjątek wyrzucany, gdy parametry podane przy wywołaniu programu są nieprawidłowe.

public class DataException extends Exception {

	public DataException() {
		super();
	}
	
	public DataException(String string) {
		super(string);
	}

	private static final long serialVersionUID = 1L;

}
