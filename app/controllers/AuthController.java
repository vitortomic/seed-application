/**
 * 
 */
package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

import actions.SecureAction;
import models.AuthToken;
import models.AuthUser;
import models.User;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.AuthenticationService;
import services.UserService;

/**
 * @author Vitor 2
 *
 */
public class AuthController extends Controller {
	
	private AuthenticationService authService;
	private UserService userService;
	
	@Inject
	public AuthController(AuthenticationService authService, UserService userService) {
		this.authService = authService;
		this.userService = userService;
	}
	
	@Transactional
	public Result registerNewUser(){
		JsonNode json = request().body().asJson();
		String email = json.get("email").asText();
		String password = json.get("password").asText();
		String firstName = json.get("firstName").asText();
		String lastName = json.get("lastName").asText();
		String type = json.get("userType").asText();
		if(email == null || email.length()<4 || password == null || password.length() <4)return badRequest("invalid data");
		try{
			AuthUser auth = authService.registerAuthUser(email, password);
			User newUser = userService.createUser(firstName, lastName, email, type);
			newUser.authUser = auth;
			return ok(Json.toJson(newUser));
		}
		catch(Exception e){
			Logger.error(e.getMessage());
			return internalServerError();
		}
	}
	
	@Transactional
	public Result login(){
		JsonNode json = request().body().asJson();
		String email = json.get("email").asText();
		String password = json.get("password").asText();
		AuthUser user = authService.doLogin(email, password);
		if(user == null){
			return unauthorized();
		}
		ObjectNode token = Json.newObject();
		token.put("token", user.authToken.token);
		token.put("expirationTime", user.authToken.tokenExpirationTime.getTime());
		
		return ok(token);
	}
	
	@With(SecureAction.class)
	@Transactional
	public Result logout(){
		String token = request().headers().get("Authorization")[0];
		AuthToken authToken = authService.invalidateToken(token);
		return ok(Json.toJson(authToken));
	}
}
