package mainAction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import dao09.Dao;
import dto09.Member;

public class SelectDo {
	public ArrayList<String> member = new ArrayList<String>();
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet set = null;
	public SelectDo() {
		// TODO Auto-generated constructor stub
		this.selectMember();
	}

	public void selectMember() {
		// TODO Auto-generated method stub
		String query = "select * from member";

		try {
			pstm = Dao.getConnection().prepareStatement(query);
			set = pstm.executeQuery();
			while (set.next()) {
				Member obj = new Member();
				String id = set.getString(1);
				String username = set.getString(2);
				String password = set.getString(3);
				obj.setId(id);
				obj.setPassword(password);
				obj.setUsername(username);
				obj.setReg_date(new Timestamp(System.currentTimeMillis()));
				member.add(obj.toString());
			}
//			request.setAttribute(name, o);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (this.con != null)
					this.con.close();
				if (this.pstm != null)
					this.pstm.close();
				if (this.set != null)
					this.set.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	}

}
