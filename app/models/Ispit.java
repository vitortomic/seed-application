/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author Vitor 2
 *
 */

@Entity
public class Ispit {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String sifra;
	public String naziv;
	public Integer espBodovi;
	
	@ManyToOne
	public Katedra katedra;
}
