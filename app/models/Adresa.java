/**
 * 
 */
package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Vitor 2
 *
 */

@Entity
public class Adresa {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String grad;
	public String ulica;
	public String broj;
	
	public String getFullAddress(){
		return grad + ", " + ulica + " " + broj;
	}
	
}
