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
public class User {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String firstName;
	
	public String lastName;
	
	public String email;
	
	@Enumerated(EnumType.STRING)
	public TipKorisnika userType;
	
	@OneToOne
	public AuthUser authUser;
	
	public String accessCode;

	public User(String firstName, String lastName, String email, String type) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.userType = TipKorisnika.valueOf(type);
	}
	
	public User(){
		super();
	}
	
	
}
