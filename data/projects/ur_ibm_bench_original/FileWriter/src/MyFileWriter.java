/*
 *
 * Haim Cohen 0-3438837-1
 *
 */
 
import java.io.*;
import java.util.Random;

public class MyFileWriter {
    
    public static void createInfputFile(String fileName, int size) throws IOException {
        FileWriter outputFile = new FileWriter(fileName);
        BufferedWriter outputBuffer = new BufferedWriter(outputFile);
        Random rnd = new Random(System.currentTimeMillis());
        
        for (int i=0; i < size; ++i) {
            int a = rnd.nextInt();
            int b = rnd.nextInt();
            int c = rnd.nextInt();
            int d = rnd.nextInt();
            
            String line = a + " " + b + " " + c + " " + d + "\n";
            outputBuffer.write(line);
        }
        outputBuffer.flush();
    }

    public static void main(String[] args) throws IOException {
        String solutionFilePath = args[0];
        int numberOfInts = Integer.parseInt(args[1]);
        createInfputFile(solutionFilePath, numberOfInts);
    }

}