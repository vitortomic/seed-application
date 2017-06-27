package controllers;

import com.google.inject.Inject;

import actions.SecureAction;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.UserService;

public class UserController extends Controller {
	
	@Inject
	private UserService userService;
	
	@With(SecureAction.class)
	@Transactional
	public Result getAllUsers(){
		return ok(Json.toJson(userService.findAll()));
	}
}
