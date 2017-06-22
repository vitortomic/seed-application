/**
 * 
 */
package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

import models.AuthUser;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AuthenticationService;

/**
 * @author Vitor 2
 *
 */
public class AuthController extends Controller {
	
	private final AuthenticationService authService;
	
	@Inject
	public AuthController(AuthenticationService authService) {
		this.authService = authService;
	}
	
	public Result registerNewUser(){
		JsonNode json = request().body().asJson();
		String email = json.get("email").asText();
		String password = json.get("password").asText();
		if(email == null || email.length()<4 || password == null || password.length() <4)return badRequest();
		try{
			authService.registerAuthUser(email, password);
		}
		catch(Exception e){
			return internalServerError();
		}
		
		return ok();
	}
	
	public Result login(){
		JsonNode json = request().body().asJson();
		String email = json.get("email").asText();
		String password = json.get("password").asText();
		AuthUser user = authService.doLogin(email, password);
		if(user == null){
			return unauthorized();
		}
		ObjectNode token = Json.newObject();
		token.put("token", user.token);
		token.put("expirationTime", user.tokenExpirationTime.getTime());
		
		return ok(token);
	}
}
