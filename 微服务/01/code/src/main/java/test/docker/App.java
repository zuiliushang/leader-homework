package test.docker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	StringRedisTemplate stringRedisTemplate;
	
	@Autowired
	AppService appService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
	
	@GetMapping("/redis")
	@ResponseBody
	public String redis() {
		String result=null;
		RedisConnection redisConnection = null;
		try {
			redisConnection = stringRedisTemplate.getConnectionFactory().getConnection();
			result = redisConnection.ping();
		} catch (Exception e) {
			if (redisConnection!=null) {
				redisConnection.close();
			}
		}
		return String.format("Redis status => PING > result %s at %s",result ,new Date());
	}
	
	@PostMapping("/user")
	@ResponseBody
	public String add(String name,String info) {
		String sql = "insert into user(name,info) values(?,?)";
		Object[] params = new Object[] {name,info};
		jdbcTemplate.update(sql,params);
		return "ok";
	}
	
	@GetMapping("/user/list")
	@ResponseBody
	public List<User> list(){
		String sql = "select id,name,info from user";
		List<Map<String, Object>> typeMap = jdbcTemplate.queryForList(sql);
		final List<User> users = new ArrayList<>();
		typeMap.forEach(m->{
			User user = new User();
			user.setId((Integer)m.get("id"));
			user.setInfo((String)m.get("info"));
			user.setName((String)m.get("name"));
			users.add(user);
		});
		return users;
	}
	
	@PostMapping("/user/muti")
	public String muti() {
		new Thread(()->{appService.save("XSH"+1, "INFO"+1,1);}) .start();
		new Thread(()->{appService.save("XSH"+2, "INFO"+2,2);}) .start();
		new Thread(()->{appService.save("XSH"+3, "INFO"+3,3);}) .start();
		new Thread(()->{appService.save("XSH"+4, "INFO"+4,4);}) .start();
		new Thread(()->{appService.save("XSH"+5, "INFO"+5,5);}) .start();
		new Thread(()->{appService.save("XSH"+6, "INFO"+6,6);}) .start();
		new Thread(()->{appService.save("XSH"+7, "INFO"+7,7);}) .start();
		new Thread(()->{appService.save("XSH"+8, "INFO"+8,8);}) .start();
		new Thread(()->{appService.save("XSH"+9, "INFO"+9,9);}) .start();
		new Thread(()->{appService.save("XSH"+10, "INFO"+10,10);}) .start();
		return "ok";
	}
}

class User{
	
	private Integer id;
	
	private String name;
	
	private String info;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
}