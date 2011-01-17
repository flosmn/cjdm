/*
 *
 * Haim Cohen 0-3438837-1
 *
 */

import java.util.Random;

public class DataPrinter extends Thread {
    public int dummy;
	public DataPrinter() {
	}

	public void run() {
		Random randomizer = new Random(System.currentTimeMillis());

		while (SoftTest.ms_bWasShutdown == false) {
		    int rand = Math.abs(randomizer.nextInt());
		    int count = DataStorage.getInstanse().getCount();
			int place = rand % count;
			try {
				//DataStorage.getInstanse().getData(place).print();
				dummy = DataStorage.getInstanse().getData(place).getSum();
			} catch (NullPointerException e) {
                FileHandle.getInstance().printError("DataPrinter: Exception while printing data in place - " + place);
			}
			
		}
	}
}
