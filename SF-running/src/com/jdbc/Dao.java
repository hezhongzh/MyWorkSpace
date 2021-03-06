package com.jdbc;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Dao implements Runnable {
	private  int LEGAL_START_1_HOUR =10;
	private int LEGAL_START_2_HOUR=14;
	private int LEGAL_END_1_HOUR=11;
	private int LEGAL_END_2_HOUR=18;
	public JdbcUtil util = new JdbcUtil();
	public static int SEQUENCE_ID = -1;
	private static int INIT_ID = 141;// 初始化sequence_id 141
	private String DB = "storeandforward";
	private String sql_update = "update " + DB
			+ " set status='P'  where  sequence_id = ?";
	private String sql_query = "select status from "+DB+" where sequence_id = ?";
	public static List<String> param = new ArrayList<String>();
	static {
		param.add("");
	}

	@Override
	public void run() {
		try {
			do{
				if(tell_legal()){
				int res = -1;
				Thread.sleep(1000);
				boolean flag = false;
				do {
					// 判断sequence_id 是否大于310 等于是还原
					// INIT_ID = INIT_ID == 310?141 :
					// INIT_ID++;
					if (INIT_ID == 310) {
						INIT_ID = 140;
					} else {
						INIT_ID++;
					}
					
					
					String test_id = "STATUS";
					List query_params = new ArrayList();
					query_params.add(INIT_ID);
					String query_rs = util.querySingleString(test_id,sql_query,query_params);
					String status_S = "S";
					String status_P = "P";
					if (status_S.equals(query_rs)) {
						// 将status从S->P
						param.set(0, INIT_ID + "");
						res = util.update(sql_update,param);
						//如果成功计数在storeandforward_count中
						boolean flag_count = this.resultCount(res);
					}
					//如果当前查到的订单还没推送，那么查询下一条
					flag = status_P.equals(query_rs) ? true: false;
				} while (flag);
				System.out.println("===================UPDATE SPNR"+INIT_ID+" STATUS TO P"+"=======================");
				}else{
					Thread.sleep(1000);
					System.out.println("----------------------------INLEGAL TIME TO DO THIS..."+getDate().get("hour").toString()+":"+getDate().get("minit").toString()+"-------------------------------------");
				}
			} while (true);
		} catch (Exception e) {
			System.out.println(e.getStackTrace()
					+ "============线程错误=============");
			e.printStackTrace();
		}
	}
	public boolean resultCount(int  res){
		String sql_count;
		if(res==1){
			sql_count = "update storeandforward_count set SUCCESS = SUCCESS+1 where test_id='test1'";
		}else{
			sql_count ="update storeandforward_count set error = error + 1 where test_id ='test1'";
		}
		List params = new ArrayList();
		int result = util.update(sql_count, params);
		boolean flag=(result==1)?true:false;
		return flag;
	}
	/**
	 * 获得当前时间
	 * @return 包含当前时间的map 
	 */ 
	public Map<String,Integer> getDate(){
		Map<String , Integer > date_now = new HashMap<String, Integer>();
		Date date=new Date();
		int hour = date.getHours();
		int minit = date.getMinutes();
		date_now.put("minit", minit);
		date_now.put("hour", hour);
		return date_now;
	} 
	public boolean tell_legal(){
		Map<String,Integer> date_now = getDate();
		int hour=date_now.get("hour");
		int minit=date_now.get("minit");
		boolean flag_run = false;
		if((hour>=LEGAL_START_1_HOUR&&hour<LEGAL_END_1_HOUR) ||(hour>=LEGAL_START_2_HOUR&&hour<LEGAL_END_2_HOUR))
		{
			flag_run=true;
		}
		return flag_run;
	}
}