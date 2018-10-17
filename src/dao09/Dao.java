package dao09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Dao {

	private static Dao INSTANCE = new Dao();

	private Dao() {
		// TODO Auto-generated constructor stub
	}

	public Dao getInstance() {
		// TODO Auto-generated method stub
		return INSTANCE;
	}

	public static Connection getConnection() {
		// TODO Auto-generated method stub

		Connection con = null;
		PreparedStatement pstm = null;

		String url = "jdbc:mariadb://localhost:3306/test";
		String id = "root";
		String pw = "maria";
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			try {
				con = DriverManager.getConnection(url, id, pw);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

}
