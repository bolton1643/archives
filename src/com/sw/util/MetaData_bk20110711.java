package com.sw.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/***
 * 用jdbc实现
 * @author sw
 *
 */
public class MetaData_bk20110711 extends HibernateDaoSupport {
	private static Connection con;

	private MetaData_bk20110711() {
	};

	public boolean operateTable2(String sql){
		Session s = getHibernateTemplate().getSessionFactory().getCurrentSession();
		s.createSQLQuery(sql).executeUpdate();
		return true;
	}
	public boolean operateTable(String sql) {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					if (stmt != null) {
						stmt.close();
					}
					if (con != null) {
						con.close();
					}
				}
			} catch (SQLException ex1) {
			}
		}
		return true;
	}

	public List<MetaDataRow> getMetaData(String tName) {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ResultSetMetaData rsmd = null;

		List<MetaDataRow> m = new ArrayList<MetaDataRow>();

		try {
			String sql = "select * from " + tName;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rsmd = rs.getMetaData(); // 获取字段名
			if (rsmd != null) {
				int count = rsmd.getColumnCount();
				for (int i = 1; i <= count; i++) {
					MetaDataRow mdr = new MetaDataRow();
					if (rsmd.getColumnName(i) != null)
						mdr.setDName(rsmd.getColumnName(i));

					if (rsmd.getColumnTypeName(i) != null)
						mdr.setDType(rsmd.getColumnTypeName(i));
					mdr.setDLength(rsmd.getColumnDisplaySize(i));
					if (1 == rsmd.isNullable(i))
						mdr.setDAllowNull("允许为空");
					else
						mdr.setDAllowNull("不允许为空");
					m.add(mdr);
				}
			}
		} catch (SQLException ex2) {
			ex2.printStackTrace();
		} catch (Exception ex2) {
			ex2.printStackTrace();
		} finally {
			rsmd = null;
			try {
				if (rs != null) {
					rs.close();
					if (stmt != null) {
						stmt.close();
					}
					if (con != null) {
						con.close();
					}

				}

			} catch (SQLException ex1) {
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
