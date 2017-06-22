/**
 * 
 */
package services;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.joda.time.DateTime;

import models.AuthUser;
import play.db.jpa.JPAApi;

/**
 * @author Vitor 2
 *
 */
public class AuthenticationService {
	private final JPAApi jpaApi;

    @Inject
    public AuthenticationService(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }
    
    public void registerAuthUser(String email, String password){
    	AuthUser newUser = new AuthUser(email, password);
    	jpaApi.em().persist(newUser);
    }
    
    @SuppressWarnings("unchecked")
	public AuthUser doLogin(String email, String password){
    	List<AuthUser> users = (List<AuthUser>) jpaApi.em().createQuery("select a from AuthUser a where a.email:=email")
    			.setParameter("email", email).getResultList();
    	if(!users.isEmpty()){
    		if(users.get(0).email.equals(email)) return createToken(users.get(0));
    	}
    	return null;
    	
    }
    
    public AuthUser createToken(AuthUser user){
    	user.token = UUID.randomUUID().toString();
    	
    	DateTime dt = new DateTime();
    	DateTime added = dt.plusHours(1);
    	
    	user.tokenExpirationTime = added.toDate();
    	
    	return jpaApi.em().merge(user);
    }
    
    public boolean authenticate(Long id, String token){
    	return false;
    }
    
}
