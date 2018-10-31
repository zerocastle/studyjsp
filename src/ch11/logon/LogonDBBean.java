package ch11.logon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class LogonDBBean {
   // LogonDBBean ���� ��ü ���� <- �Ѱ��� ��ü��  �����ؼ� ����
   private static LogonDBBean instance = new LogonDBBean();
   
   // LogonDBBean ��ü�� �����ϴ� �޼ҵ�
   public static LogonDBBean getInstance() {
      return instance;
   }
   
   private LogonDBBean() {}
   
   //Ŀ�ؼ� Ǯ���� Ŀ�ؼ� ��ü�� ���� �޼ҵ�
   private Connection getConnection() throws Exception{
      Context initCtx = new InitialContext();
      Context envCtx = (Context)initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource)envCtx.lookup("jdbc/TestDB");
      return ds.getConnection();
   }
   
   // ȸ������ó������ ����ϴ� �� ���ڵ� �߰��޼ҵ�
   public void insertMember(LogonDataBean member2) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      
      try {
         conn=getConnection();
         
         String orgPass = member2.getPasswd();
         
         pstmt=conn.prepareStatement("insert into member2 values(?,?,?,?,?,?)");
         pstmt.setString(1, member2.getId());
         pstmt.setString(2, orgPass);
         pstmt.setString(3, member2.getName());
         pstmt.setTimestamp(4, member2.getReg_date());
         pstmt.setString(5, member2.getAddress());
         pstmt.setString(6, member2.getTel());
         pstmt.executeUpdate();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         if(pstmt!=null) try { pstmt.close(); }catch(SQLException e){}
         if(conn!=null) try { conn.close(); }catch(SQLException e){}
      }
   }
   
   // �α��� �� ó��(loginPro.jsp)�������� ����� ���� ó�� �� 
   // ȸ�� ���� ����/Ż�� ����� ����(memberCheck.jsp)���� ����ϴ� �޼ҵ�
   public int userCheck(String id, String passwd) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int x=-1;
      
      try {
         conn=getConnection();
         
         String orgPass = passwd;
         
         pstmt=conn.prepareStatement("select passwd from member2 where id=?");
         pstmt.setString(1, id);
         rs=pstmt.executeQuery();
         
         if(rs.next()) { // �ش���̵� ������ ����
            String dbpasswd = rs.getString("passwd");
            if(orgPass.equals(dbpasswd))
               x=1; // ��������
            else
               x=0; // ��й�ȣƲ��
         }else // �ش���̵� ������ ����
            x=-1;
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      return x;
      
   }
   
   //���̵� �ߺ� Ȯ�ο��� ���̵��� �ߺ� ���θ� Ȯ���ϴ� �޼ҵ�
   public int confirmId(String id) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int x=-1;
      
      try {
         conn=getConnection();
         
         pstmt=conn.prepareStatement("select id from member2 where id = ?");
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next())//���̵� ����
            x=1;
         else
            x=-1;
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      
      return x;
   }
   
   //ȸ�� ���� ���� ���� ���� ���� ���� ������ �������� �޼ҵ�
   public LogonDataBean getMember(String id, String passwd) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      LogonDataBean member2 = null;
      
      try {
         conn = getConnection();
         
         String orgPass = passwd;
         
         pstmt = conn.prepareStatement("select * from member2 where id = ?");
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) { // �ش� ���̵� ���� ���ڵ尡 ����
            String dbpasswd = rs.getString("passwd");
            // ����ڰ� �Է��� ��й�ȣ�� ���̺��� ��й�ȣ�� ������ ����
            if(orgPass.equals(dbpasswd)){
               member2 = new LogonDataBean();
               member2.setId(rs.getString("id"));
               member2.setName(rs.getString("name"));
               member2.setReg_date(rs.getTimestamp("reg_date"));
               member2.setAddress(rs.getString("address"));
               member2.setTel(rs.getString("tel"));
            }
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      
      return member2; // ������ ����� ��ü member2 ����
   }
   
   // ȸ�� ���� ���� ó��(modifyPro.jsp)���� ȸ�� ���� ������ ó���ϴ� �޼ҵ�
   public int updateMember(LogonDataBean member2) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int x = -1;
      
      try {
         conn = getConnection();
         
         String orgPass = member2.getPasswd();
         
         pstmt = conn.prepareStatement("select passwd from member2 where id = ?");
         pstmt.setString(1, member2.getId());
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            String dbpasswd = rs.getString("passwd");
            if(orgPass.equals(dbpasswd)) {
               pstmt = conn.prepareStatement("update member2 set name = ?, address = ? , tel =? where id =?");
               pstmt.setString(1, member2.getName());
               pstmt.setString(2, member2.getAddress());
               pstmt.setString(3, member2.getTel());
               pstmt.setString(4, member2.getId());
               pstmt.executeUpdate();
               x=1;//ȸ������ ����ó�� ����
            }else
               x=0;//ȸ������ ����ó�� ����
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally {
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      
      return x;
   }
   
   //ȸ�� Ż�� ó��(deletePro.jsp)���� ȸ�� ������ �����ϴ� �޼ҵ�
   public int deleteMember(String id, String passwd) {
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      int x = -1;
      
      try {
         conn = getConnection();
         
         String orgPass = passwd;
         
         pstmt = conn.prepareStatement("select passwd from member2 where id = ?");
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         if(rs.next()) {
            String dbpasswd = rs.getString("passwd");
            if(orgPass.equals(dbpasswd)) {
               pstmt = conn.prepareStatement("delete from member2 where id = ?");
               pstmt.setString(1, id);
               pstmt.executeUpdate();
               x=1;
            }else
               x=0;
         }
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }finally{
         if(rs!=null) try { rs.close(); } catch(SQLException e) {}
         if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
         if(conn!=null) try {conn.close();}catch(SQLException e) {}
      }
      
      return x;
   }
}