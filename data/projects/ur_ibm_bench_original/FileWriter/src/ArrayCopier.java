/*
 *
 * Haim Cohen 0-3438837-1
 *
 */
 
public class ArrayCopier extends Thread {
	Data[] m_Dest;
	int m_iCount;
	Data[] m_Source;

	public ArrayCopier(Data[] source, Data[] dest, int iCount) {
		m_Source = source;
		m_Dest = dest;
		m_iCount = iCount;
	}

	public void run() {
		for (int i = 0; i < m_iCount; ++i) {
		    try {
			    m_Dest[i] = new Data(m_Source[i]);
			} catch (NullPointerException e) {
                FileHandle.getInstance().printError("ArrayCopier: Exception while copying data in place - " + i);
            }
		}
	}
}
