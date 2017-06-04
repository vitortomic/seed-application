package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String ime;
	public String prezime;
	public Integer godine;
	
	@OneToMany(fetch=FetchType.EAGER)
	public List<Telefon> telefoni;
	
	@ManyToMany
	public List<Ispit> ispiti;
}
