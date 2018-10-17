package mainAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao09.Dao;

public class UpdateDo {

	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet set = null;

	public UpdateDo() {
		// TODO Auto-generated constructor stub
	}

	public int updateMember(String userid, String username, String password) {
		// TODO Auto-generated method stub
		String selectQuery = "select * from member where userid = ?";
		String dbId = null;
		String dbPassword = null;
		int n = 0;
		con = Dao.getConnection();
		try {
			pstm = con.prepareStatement(selectQuery);
			pstm.setString(1, userid);
			set = pstm.executeQuery();
			while (set.next()) {
				dbId = set.getString(1);
				dbPassword = set.getString(3);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (userid.equals(dbId) && password.equals(dbPassword)) {

			try {
				String query = "update member set userid=?,username=?,password=?,reg_date=? where userid= ?";

				pstm = con.prepareStatement(query);
				pstm.setString(1, userid);
				pstm.setString(2, username);
				pstm.setString(3, password);
				pstm.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
				pstm.setString(5, userid);

				n = pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (this.con != null) {
						this.con.close();
					}
					if (this.pstm != null)
						this.pstm.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return n;
		}else {
			n = 0;
			return n;
		}
	}

}
