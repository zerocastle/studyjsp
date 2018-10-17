package mainAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao09.Dao;

public class DeleteDo {

	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet set = null;

	public DeleteDo() {
		// TODO Auto-generated constructor stub
	}

	public int deleteMember(String userid, String password) {
		// TODO Auto-generated method stub
		String dbId = null;
		String dbPassword = null;
		int signal = 0;
		String selectQuery = "select * from member where userid = ?";
		this.con = Dao.getConnection();
		try {
			this.pstm = this.con.prepareStatement(selectQuery);
			this.pstm.setString(1, userid);

			this.set = this.pstm.executeQuery();

			while (this.set.next()) {
				dbId = this.set.getString(1);
				dbPassword = this.set.getString(3);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (this.set != null) {
					this.set.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (dbId.equals(userid) && dbPassword.equals(password)) {
			try {

				String query = "delete from member where userid = ?";
				this.pstm = this.con.prepareStatement(query);
				this.pstm.setString(1, userid);
				signal = this.pstm.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (pstm != null) {
						pstm.close();
					}
					if (this.con != null) {
						this.con.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return signal;
		} else
			return 0;
	}

}
