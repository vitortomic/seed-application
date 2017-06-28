/**
 * 
 */
package services;

import java.util.Date;
import java.util.UUID;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.mindrot.jbcrypt.BCrypt;

import models.AuthToken;
import models.AuthUser;
import play.db.jpa.JPAApi;


/**
 * @author Vitor 2
 *
 */
public class AuthenticationService {
	private JPAApi jpaApi;

    @Inject
    public AuthenticationService(JPAApi jpaApi) {
        this.jpaApi = jpaApi;
    }
    
    public AuthUser registerAuthUser(String email, String password){
    	AuthUser newUser = new AuthUser(email, BCrypt.hashpw(password, BCrypt.gensalt()));
    	jpaApi.em().persist(newUser);
    	return newUser;
    }
    
    public AuthUser findByEmail(String email){
    	return (AuthUser) jpaApi.em().createQuery("Select a from AuthUser a where a.email=:email")
    			.setParameter("email", email).getSingleResult();
    }
    
    public Boolean checkPassword(String password, AuthUser user){
    	return BCrypt.checkpw(password, user.password);
    }
    
  
    public AuthUser doLogin(String email, String password){
    	AuthUser user = findByEmail(email);
    	if(user != null){
			if(checkPassword(password, user))
				return createToken(user);
    	}
    	return null;
    }
    
    public AuthUser createToken(AuthUser user){
    	
    	AuthToken token = new AuthToken();
    	token.token = UUID.randomUUID().toString();
    	token.isValid = true;
    	
    	DateTime dt = new DateTime();
    	DateTime added = dt.plusHours(1);
    	token.tokenExpirationTime = added.toDate();
    	
    	jpaApi.em().persist(token);
    	
    	user.authToken = token;
    	return jpaApi.em().merge(user);
    }
    
    public boolean authenticate(Long userId, String token){
    	return false;
    }
    
    public Boolean isTokenValid(String tokenString){
    	AuthToken token = findByToken(tokenString);
    	if(token == null)return false;
    	return token.isValid && isTokenExpired(token);
    }
    
    public Boolean isTokenExpired(AuthToken token){
    	return new Date().getTime() < token.tokenExpirationTime.getTime();
    }
    
    public AuthToken findByToken(String token){
    	try{
    		return (AuthToken) jpaApi.em().createQuery("select t from AuthToken t where t.token=:token")
        			.setParameter("token", token).getSingleResult();
    	}
    	catch (Exception e){
    		return null;
    	}
    }
    
    public AuthToken invalidateToken(String token){
    	AuthToken authToken = findByToken(token);
    	authToken.isValid = false;
    	return authToken;
    }
    
   
}
