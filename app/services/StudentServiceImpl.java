package services;

import java.util.List;

import javax.persistence.Query;

import com.google.inject.Inject;

import models.Student;
import play.db.jpa.JPAApi;

public class StudentServiceImpl implements StudentService {
	
	@Inject
	private JPAApi jpaApi;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> vrati() {
		return (List<Student>) jpaApi.em().createQuery("select s from Student s").getResultList();
	}

	@Override
	public Student nadjiPoId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean obrisiStudenta(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}

}
