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
public class PrijavaIspita {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	@ManyToOne
	public IspitniRok ispit;
	
	@ManyToOne
	public User profesor;
	
	@ManyToOne
	public Student student;
	
	public Integer ocena;
	
	public Boolean deleted = false;
}
