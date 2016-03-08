package com.jdbc;

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

public class JdbcUtil {
	private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@10.6.157.5:1521/webhu";
	private static String USERNAME = "huet";
	private static String PASSWORD = "huet";
	public  static Connection conn = null;
	static {
		try {
			Class.forName(DRIVER);
			System.out.println("ORACLE LOADED=======================");
			conn = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			conn.setAutoCommit(true);
			System.out.println("ORACLE CONNECTED========================");
		} catch (Exception e) {
			System.out.println("OCACLE CONNECTED FAILEDXXXXXXXXXXXXXXXXXXXXX");
			System.out.println(e.getStackTrace());
			e.printStackTrace();
		}
	}
	//
	public int update(String sql,List<String> params){
		int result = -1;// 表示当用户执行增加删除和修改的操作影响的行数    
		try{
		    int index = 1; // 表示 占位符 ，从1开始   
		   PreparedStatement statm = conn.prepareStatement(sql);
		    if (params != null && !params.isEmpty()) {
		      for (int i = 0; i < params.size(); i++) {
		        statm.setObject(index++, params.get(i)); // 填充占位符    
		      }
		    }
		    result = statm.executeUpdate();
		}catch(Exception e){e.printStackTrace();}
		return result;
	}
	
	public String querySingleString(String colname,String sql,List params){
		String rs=null;
		try{
			PreparedStatement statm = conn.prepareStatement(sql);
			if(params.size()!=0&&!params.isEmpty()){
				int index=1;
				for(int i =0;i<params.size();i++){
					statm.setObject(i+1, params.get(i));
				}
				ResultSet resultset = statm.executeQuery();
				while(resultset.next()){
					rs = (String)resultset.getObject(colname);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;
	}
}
	