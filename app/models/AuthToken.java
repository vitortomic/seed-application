/**
 * 
 */
package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vitor 2
 *
 */
@Entity
public class AuthToken {

	@JsonIgnore
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@OneToOne
	public User owner;
	
	public String token;
	
	public Date tokenExpirationTime;
	
	public Boolean isValid;
	
}
