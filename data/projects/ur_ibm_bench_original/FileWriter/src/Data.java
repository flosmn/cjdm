/*
 *
 * Haim Cohen 0-3438837-1
 *
 */
 
public class Data {
	private int m_data1;
	private int m_data2;
	private int m_data3;
	private int m_data4;
	
	public Data() {
		m_data1 = 0;
		m_data2 = 0;
		m_data3 = 0;
		m_data4 = 0;
	}

	public Data(Data data) {
		m_data1 = data.m_data1;
		m_data2 = data.m_data2;
		m_data3 = data.m_data3;
		m_data4 = data.m_data4;		
	}

	public Data(int a, int b, int c, int d) {
		m_data1 = a;
		m_data2 = b;
		m_data3 = c;
		m_data4 = d;
	}

	public void print() {
		System.out.println("First data: " + m_data1);
		System.out.println("Second data: " + m_data2);
		System.out.println("Third data: " + m_data3);
		System.out.println("Fourth data: " + m_data4);
	}
	
    public int getSum() {
	    return (m_data1 + m_data2 + m_data3 + m_data4);
	}
}
