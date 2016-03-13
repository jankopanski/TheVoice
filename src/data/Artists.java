package data;

/* W zależności od implementacji interfejsu Artists można uzyskać nieco odmienny
 * sposób działania modułu processors np. zbiór a lista
 */

public interface Artists extends Iterable<String> {
	public void add(String name);
}
