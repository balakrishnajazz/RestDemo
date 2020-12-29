package com.example.RestDemo.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.RestDemo.user.User;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	
	private static List<User> users = new ArrayList<>();
	
	private static int userCount = 3;
	static {
		users.add(new User(1, "bala",new Date()));
		users.add(new User(2, "krishna",new Date()));
		users.add(new User(3, "jazz",new Date()));
	}
	
	//returning all users
	public List<User> findAll(){
		return users;
	}
	
	//saving a user 
	public User save(User user ) {
		
		if(user.getId() == null) {
			user.setId(++userCount);
			user.setDob(new Date());
		}
		users.add(user);
		return user;
	}
	
	//reterving a user 
	public User findUser(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}
	
//	Delete User
	public User deleteUser(int id) {
		
		User user = null;
		if(users.get(id-1) != null) {
			user = users.get(id-1);
			users.remove(user);
		}
		return user;
		
		
	}
	
	
}
