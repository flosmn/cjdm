package utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileLineCounter {

	public int getNumberOfLines(String filename) throws IOException {
		InputStream is = new BufferedInputStream(new FileInputStream(filename));
	    byte[] c = new byte[1024];
	    int count = 0;
	    int readChars = 0;
	    while ((readChars = is.read(c)) != -1) {
	        for (int i = 0; i < readChars; ++i) {
	            if (c[i] == '\n')
	                ++count;
	        }
	    }
	    return count;
	}
}
