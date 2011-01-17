/*
 *
 * Haim Cohen 0-3438837-1
 *
 */

public class SoftTest {
	public static boolean ms_bWasShutdown = false;

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Error!! Incorrect parameter count!!");
			System.out.println("usage:");
			System.out.println("    SoftTest <input file> <output file>");
			return;
		}
		FileHandle reader = new FileHandle(args[0], args[1]);
		
		DataPrinter printer = new DataPrinter();
		
		reader.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e = e;
		}

		printer.start();

		try {
			reader.join();
		} catch (InterruptedException e) {
			e = e;
		}

		ms_bWasShutdown = true;

		try {
			printer.join();
		} catch (InterruptedException e) {
			e = e;
		}
	}
}
