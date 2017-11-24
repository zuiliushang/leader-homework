package test.docker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AppService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Transactional
	public void save(String name,String info,int i) {
		if (i==5) {
			System.out.println(1/0);
		}
		String sql = "insert into user(name,info) values(?,?)";
		Object[] params = new Object[] {name,info};
		jdbcTemplate.update(sql,params);
	}
	
}
