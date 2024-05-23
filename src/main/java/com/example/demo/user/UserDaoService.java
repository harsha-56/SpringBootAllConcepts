package com.example.demo.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

//userservice managed by spring
@Component
public class UserDaoService {
	private static List<User> users = new ArrayList<>();
	private static int usersCount =0;
	
	static {
		users.add(new User(++usersCount, "harsha", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "pooja", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "malli", LocalDate.now().minusYears(20)));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User findOne(int id) {
		Predicate<? super User> predicate = user ->user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}
	
	public void deleteByid(int id) {
		Predicate<? super User> predicate = user ->user.getId().equals(id);
		 users.removeIf(predicate);
	}
	
	
	public User save(User user) {
		user.setId(usersCount);//temparary implementation
		users.add(user);
		return user;
		
	}

}
