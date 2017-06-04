/**
 * 
 */
package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 * @author Vitor 2
 *
 */
@Entity
public class Katedra {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String naziv;
	
	@OneToMany(fetch=FetchType.LAZY)
	public List<Ispit> ispiti;
	
	@ManyToMany
	public List<Korisnik> profesori;
}
