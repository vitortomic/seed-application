package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Student {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;
	
	public String ime;
	public String prezime;
	public Integer godine;
	
	@JsonIgnore
	@ManyToMany(fetch=FetchType.EAGER)
	public List<PrijavaIspita> prijavljeniIspiti;
	
	@Transient
	public Double prosek;
	@Transient
	public Integer esp;

}
