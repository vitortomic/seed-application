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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Vitor 2
 *
 */
@Entity
@Table(uniqueConstraints=@UniqueConstraint(columnNames="email"))
public class AuthUser {
	public AuthUser(String email, String password) {
		this.email = email;
		this.password = password;//encode password
	}
	@JsonIgnore
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	
	public String email;
	public String password;
	
	@JsonIgnore
	public String token;
	@JsonIgnore
	public Date tokenExpirationTime;
	
	@OneToOne
	public User user;
	
}
