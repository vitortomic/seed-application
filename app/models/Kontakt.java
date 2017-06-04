/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 * @author Vitor 2
 *
 */
@Entity
public class Kontakt {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@OneToOne
	public Korisnik korisnik;
	
	@OneToMany
	public List<Telefon> telefoni;
	
	@OneToOne
	public Adresa adresa;
}
