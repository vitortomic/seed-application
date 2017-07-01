/**
 * 
 */
package services;

import java.util.List;

import com.google.inject.Inject;

import models.PrijavaIspita;
import play.db.jpa.JPA;
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
		return (List<PrijavaIspita>)jpaApi.em().createQuery("Select p from PrijavaIspita p where p.deleted is false").getResultList();
 	}
	
	public PrijavaIspita updatePrijave(PrijavaIspita prijava){
		return jpaApi.em().merge(prijava);
	}
	
	public void obrisiPrijavu(Long id){
		PrijavaIspita prijava = (PrijavaIspita)JPA.em()
				.createQuery("Select p from PrijavaIspita p where p.id =:id").setParameter("id", id).getSingleResult();
		prijava.deleted = true;
	}
}
