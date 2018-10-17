package mainAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import dao09.Dao;

public class InsertDo {

	Connection con = null;
	ResultSet set = null;
	PreparedStatement pstm = null;

	public InsertDo() {
		// TODO Auto-generated constructor stud

	}

	public int insertDo(String id, String username, String password ) {
		// TODO Auto-generated constructor stub
		String query = "insert into member values(?,?,?,?)";
		
		int n = 0;
		try {
			pstm = Dao.getConnection().prepareStatement(query);
			pstm.setString(1, id);
			pstm.setString(2, username);
			pstm.setString(3, password);
			pstm.setTimestamp(4,new Timestamp(System.currentTimeMillis()));
			n = pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (this.con != null)
					this.con.close();
				if (this.pstm != null)
					this.pstm.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return n;
	}

}
