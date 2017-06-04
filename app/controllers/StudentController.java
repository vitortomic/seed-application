package controllers;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import models.Student;
import play.db.jpa.Transactional;
import static play.libs.Json.toJson;
import play.mvc.Controller;
import play.mvc.Result;
import services.StudentService;

public class StudentController extends Controller {
	
	@Inject
	StudentService studentskiServis;
	
	@Transactional
	public Result vratiSveStudente(){
		List<Student> studenti = studentskiServis.vrati();
		return ok(toJson(studenti));
	}

	
	
}
