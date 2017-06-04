/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import enums.TipKorisnika;

/**
 * @author Vitor 2
 *
 */

@Entity
public class Korisnik {
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String ime;
	public String prezime;
	
	public String brojIndeksa;
	
	public String brRadneKnjizice;
	
	@Enumerated(EnumType.STRING)
	public TipKorisnika tip;
	
	public String email;
	
	@OneToOne
	public Kontakt kontakt;
}
