/**
 * 
 */
package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import actions.SecureAction;
import models.PrijavaIspita;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;
import services.PrijavaIspitaService;

/**
 * @author Vitor 2
 *
 */
public class PrijavaIspitaController extends Controller {
	
	@Inject
	private PrijavaIspitaService prijavaService;
	
	@With(SecureAction.class)
	@Transactional
	public Result vratiPrijave(){
		return ok(Json.toJson(prijavaService.vratiPrijave()));
	}
	
	@With(SecureAction.class)
	@Transactional
	public Result updejtujPrijavu(){
		JsonNode json = request().body().asJson();
		PrijavaIspita prijava = Json.fromJson(json, PrijavaIspita.class);
		prijavaService.updatePrijave(prijava);
		return ok(Json.toJson(prijava));
	}
	
	@With(SecureAction.class)
	@Transactional
	public Result obrisiPrijavu(Long id){
		prijavaService.obrisiPrijavu(id);
		return ok();
	}
}
