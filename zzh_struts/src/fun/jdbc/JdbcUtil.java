package fun.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.sun.xml.internal.ws.api.addressing.WSEndpointReference.Metadata;

public class JdbcUtil implements JdbcUtilsInter{
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static String USERNAME = "scott";
	private static String PASSWORD = "tiger";
	private static Connection conn = null;
	static {
		try {
			Class.forName(DRIVER);
			System.out.println("ORACLE驱动加载完成=======================");
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			System.out.println("ORACLE连接获取成功========================");
		} catch (Exception e) {
			System.out.println("OCALE连接失败XXXXXXXXXXXXXXXXXXXXX");
			System.out.println(e.getStackTrace());
		}
	}

	public String querySingle(String sql, List params) {
		ResultSet rs = doQuery(sql, params);
		String result = null;
		try {
			ResultSetMetaData metaData = rs.getMetaData();
			String cluname = metaData.getColumnClassName(1);
			result = (String) rs.getObject(cluname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public List<Map<String, Object>> queryMulti(String sql, List Params) {
		ResultSet rs = doQuery(sql, Params);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				int colcont = metaData.getColumnCount();
				System.out.println("colcount:"+colcont);
				Map<String , Object> map =new HashMap<String,Object>();
				String result=null;
				for (int i = 0; i < colcont; i++) {
					String colname=metaData.getColumnName(i+1);
					System.out.println("colname:"+colname);
					map.put(colname,rs.getObject(colname));
					System.out.println("colvalue:"+rs.getObject(colname));
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ResultSet doQuery(String sql, List<Object> params) {
		ResultSet re = null;
		try {
			PreparedStatement prem = conn.prepareStatement(sql);
			int index = 1;
			if (params != null & !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					prem.setObject(index++, params.get(i));
					System.out.println("params:"+params.get(i));
				}
			}
			re = prem.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return re;
	}

	@Override
	public boolean updateTabel(String sql, List<String> params) {
		// TODO Auto-generated method stub
		return false;
	}
}
