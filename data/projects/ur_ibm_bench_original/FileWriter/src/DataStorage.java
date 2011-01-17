/*
 *
 * Haim Cohen 0-3438837-1
 *
 */
 
public class DataStorage {
	private Data[] m_DataArray; // array of data elements
	private int m_iSize; // size of array
	private int m_iCount; // count of elements in the array

	private Object m_sync;

	private static DataStorage ms_instanse = null;

	public static DataStorage getInstanse() {
		if (ms_instanse == null)
			ms_instanse = new DataStorage();
		return ms_instanse;
	}

	private DataStorage() {
		m_DataArray = new Data[8];
		m_iSize = 8;
		m_iCount = 0;

		m_sync = new Object();
	}

	public int getCount() {
		return m_iCount;
	}

	public void putData(Data data) {
		if (m_iCount >= m_iSize) {
			doubleArray();
		}
		m_DataArray[m_iCount++] = new Data(data);
	}

	public Data getData(int place) {
		Data ret = null;
		synchronized (m_sync) {
			try {
				ret = m_DataArray[place];
			} catch (ArrayIndexOutOfBoundsException e) {
                FileHandle.getInstance().printError("DataStorage: Exception while getting data in place - " + place);
			}
		}
		return ret;
	}

	private void doubleArray() {
		Data[] tmp = null;

		synchronized (m_sync) {
			tmp = m_DataArray;
			m_DataArray = new Data[m_iSize * 2];
			m_iSize *= 2;
		}

		ArrayCopier copyThread = new ArrayCopier(tmp, m_DataArray, m_iSize / 2);
		copyThread.start();
	}
}
