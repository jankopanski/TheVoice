package thevoice;
import data.Data;
import data.DataException;
import processors.Processor;


public class TheVoice {

	public static void main(String[] args) {
		
		Data data = null;
		try {
			data = new Data(args);
		} catch (DataException e) {
			e.printStackTrace();
		}
		Processor[] processors = Processor.initProcessors(args);
		for (Processor proc: processors) {
			try {
				proc.run(data);
				System.out.println("***\n");
			} catch (DataException e) {
				e.printStackTrace();
			}
		}

	}

}
