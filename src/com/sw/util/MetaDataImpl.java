package com.sw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class MetaDataImpl extends HibernateDaoSupport implements MetaData {
	private static Logger logger = Logger.getLogger(MetaDataImpl.class);
	
	private HashMap<String,String> typeMap = new HashMap<String,String>();

	private static Connection getConnection()throws ClassNotFoundException,SQLException{
		String driver="com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost/rf2";
		String user="root";
		String password="123456";
		Class.forName(driver);
		Connection conn=DriverManager.getConnection(url,user,password);
	
	return conn;

}
	
	private MetaDataImpl() {
		typeMap.put("INT", "整数");
		typeMap.put("VARCHAR", "字符串");
		typeMap.put("DATETIME", "日期");
		typeMap.put("DOUBLE", "小数");
	};

	public boolean operateTable(String sql) {
		Session s = getHibernateTemplate().getSessionFactory().openSession();
		s.createSQLQuery(sql).executeUpdate();
		s.setFlushMode(FlushMode.COMMIT);
		
		
		if (s != null)
		 s.close();
		return true;
	}

	public List<MetaDataRow> getMetaData(String tName) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;

		List<MetaDataRow> m = new ArrayList<MetaDataRow>();

		try {
			con = getConnection();
			// 获取表结构字段信息
			String sql = "select * from " + tName + " where id=0";

			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); // 获取字段名

			// 获取备注，作为字段中文名称
			sql = "select column_comment from information_schema.columns where table_schema='rf2' and table_name='"
					+ tName + "'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rsmd != null) {
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					MetaDataRow mdr = new MetaDataRow();
					if (rsmd.getColumnName(i) != null) {
						mdr.setDName(rsmd.getColumnName(i));
					}

					if (rsmd.getColumnTypeName(i) != null)
						mdr.setDType((String) typeMap.get(rsmd
								.getColumnTypeName(i)));
					mdr.setDLength(rsmd.getColumnDisplaySize(i));
					if (1 == rsmd.isNullable(i))
						mdr.setDAllowNull("允许为空");
					else
						mdr.setDAllowNull("不允许为空");

					if (rs.next()) {
						mdr.setDNotes(rs.getString(1));

						if (mdr.getDName().endsWith("_FILE")) {
							mdr.setDType("文件");
						}
					}

					m.add(mdr);
				}

			}
		} catch (SQLException ex2) {
			logger.error(ex2.getMessage());
			ex2.printStackTrace();
		} catch (Exception ex2) {
			logger.error(ex2.getMessage());
			ex2.printStackTrace();
		} finally {
			rsmd = null;
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
				// if (s != null)
				// s.close();
			} catch (SQLException ex1) {
				logger.error(ex1.getMessage());
			}
		}
		return m;
	}

	public static void main(String[] args) {

		String driver = "com.mysql.jdbc.Driver";

		String strUrl = "jdbc:mysql://localhost/rf";

		Statement stmt = null;

		ResultSet rs = null;

		Connection conn = null;

		PreparedStatement pstmt = null;

		ResultSetMetaData rsmd = null;

		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(strUrl, "root", "123456");

			pstmt = conn.prepareStatement("SELECT * FROM t1");

			rs = pstmt.executeQuery();

			rsmd = rs.getMetaData(); // 获取字段名

			if (rsmd != null) {

				int count = rsmd.getColumnCount();

				for (int i = 1; i <= count; i++) {

					System.out.println(rsmd.getColumnName(i) + ":"
							+ rsmd.getColumnTypeName(i) + ":"
							+ rsmd.getColumnDisplaySize(i));

				}

			}

		}

		catch (SQLException ex2) {

			ex2.printStackTrace();

		}

		catch (Exception ex2) {

			ex2.printStackTrace();

		} finally {

			rsmd = null;

			try {

				if (rs != null) {

					rs.close();

					if (stmt != null) {

						stmt.close();

					}

					if (conn != null) {

						conn.close();

					}

				}

			}

			catch (SQLException ex1) {

			}
		}
	}
}
