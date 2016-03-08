package fun.login;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fun.jdbc.JdbcUtil;

public class LoginDao {
	private JdbcUtil util=new JdbcUtil();
	public Boolean queryPassWord(String username,String password){
		String sql="select * from TEST where username = ?";
		List<String> Params =new ArrayList<String>();
		List<Map<String,Object>> rs =new ArrayList<Map<String,Object>>();
		
		Params.add(username);
		rs = util.queryMulti(sql, Params);
		String pwd="";
		if(rs.size()!=0){
			pwd = (String)rs.get(0).get("PASSWORD");
			System.out.println(pwd);
		}
		if(password.equals(pwd)){
			return true;
		}
		return false;
	}
}
