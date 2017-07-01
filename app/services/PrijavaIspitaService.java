/**
 * 
 */
package services;

import java.util.List;

import com.google.inject.Inject;

import models.PrijavaIspita;
import play.db.jpa.JPAApi;

/**
 * @author Vitor 2
 *
 */
public class PrijavaIspitaService {
	
	@Inject
	private JPAApi jpaApi;
	
	@SuppressWarnings("unchecked")
	public List<PrijavaIspita> vratiPrijave(){
		return (List<PrijavaIspita>)jpaApi.em().createQuery("Select p from PrijavaIspita p").getResultList();
 	}
	
	public PrijavaIspita updatePrijave(PrijavaIspita prijava){
		return jpaApi.em().merge(prijava);
	}
	
	public void obrisiPrijavu(PrijavaIspita prijava){
		jpaApi.em().remove(prijava);
	}
}
