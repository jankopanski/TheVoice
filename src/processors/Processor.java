package processors;
import java.util.ArrayList;

import data.Data;
import data.DataException;


public abstract class Processor {
	
	public abstract void run(Data data) throws DataException;
	
	// Tworzy obiekty klas Count lub Top5 na podstawie parametrów wejściowych.
	public static Processor[] initProcessors(String[] input) {
		ArrayList<Processor> processors = new ArrayList<Processor>();
		String processorID = null;
		for (int i = 1; i < input.length; i++) {
			if (input[i].contains("--processors=")) {
				processorID = input[i].substring(13);
			}
		}
		// Tutaj należy dodawać nowe statystyki
		if (processorID.contains("count")) {
			processors.add(new Count());
		}
		if (processorID.contains("top5")) {
			processors.add(new Top5());
		}
		return processors.toArray(new Processor[processors.size()]);
	}
	
}
