/**
 * 
 */
package services;

import java.util.List;
import java.util.UUID;

import com.google.inject.Inject;

import models.User;
import play.db.jpa.JPAApi;

/**
 * @author Vitor 2
 *
 */
public class UserService {
	
	private final JPAApi jpaApi;
	
	@Inject
	public UserService(JPAApi jpaApi){
		this.jpaApi = jpaApi;
	}
	
	public User createUser(String firstName, String lastName, String email, String type){
		User user = new User(firstName, lastName, email, type);
		user.accessCode = UUID.randomUUID().toString();
		jpaApi.em().persist(user);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAll(){
		List<User> users = jpaApi.em().createQuery("select u from User u").getResultList();
		return users;
	}
}
