package persistance;

import java.sql.Connection;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class PostgresBaseDao {
	
	protected final Connection getConnection() {
		System.out.println("get connection");
		Connection result = null;
		
		try {
			System.out.println("connection 1");
			InitialContext ic = new InitialContext();
			System.out.println("connection 2");
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			System.out.println("connection 3");
			
			result = ds.getConnection();
			System.out.println("connection 4");
		} catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException(e);
		}
		
		return result;
	}
}
