/*
 *
 * Haim Cohen 0-3438837-1
 *
 */
 
import java.io.*;

public class FileHandle extends Thread {
    private String m_InputFileName;
    private BufferedWriter m_OutFile;
    private boolean m_bEnd;
    
    static FileHandle sm_instance = null;
    static boolean sm_error = false;

    static FileHandle getInstance() {
        return sm_instance;
    }
    
    public synchronized void printError(String msg) {
        try {
            m_OutFile.write(msg, 0, msg.length());
            m_OutFile.newLine();
            m_OutFile.flush();
        } catch (IOException e) {
            e = e; // do nothing
        }
    }
    
    public FileHandle(String inFile, String outFile) {
        if (sm_instance == null) {
            sm_instance = this;
        }
        
        m_bEnd = false;
        m_InputFileName = inFile;
        
        try {
            FileWriter outputFile = new FileWriter(outFile);
            m_OutFile = new BufferedWriter(outputFile);
        } catch (IOException e) {
            e = e; // do nothing
        }
    }

    public void run() {
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(m_InputFileName);
        } catch (IOException e) {
            System.out.println("Error!! Cannot open input file!!");
            return;
        }

        BufferedReader inputReader = new BufferedReader(new InputStreamReader(inputFile)); // reader
        int numbers[] = new int[4];
        while (SoftTest.ms_bWasShutdown == false && m_bEnd == false) {
		    String intstring[] = new String[5];
            String line = new String("1 2 3 4");
            
            try {
                line = inputReader.readLine();
            } catch (IOException e) {
                e = e; // do nothing
            }
            
            if (line == null) {
                m_bEnd = true;
                continue;
            }
            
            intstring = line.split(" ");
            for(int i = 0; i < 4; ++i) {
                numbers[i] = Integer.parseInt(intstring[i]);
            }
            DataStorage.getInstanse().putData(new Data(numbers[0],numbers[1],numbers[2],numbers[3])); 
        }
    }
}
