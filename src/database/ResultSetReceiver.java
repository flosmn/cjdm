package database;

import java.sql.ResultSet;

public interface ResultSetReceiver {
	public void receive(ResultSet resultSet);
}
