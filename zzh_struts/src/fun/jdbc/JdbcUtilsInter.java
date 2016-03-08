package fun.jdbc;

import java.util.List;
import java.util.Map;

public interface JdbcUtilsInter {
	//查询单条数据
	 String querySingle(String sql, List<String> params);
	 List<Map<String,Object>>queryMulti(String sq,List<String> params);
	 boolean updateTabel(String sql,List<String> params);
	 
}
