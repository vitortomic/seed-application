package services;

import java.util.List;

import com.google.inject.Inject;

import models.Student;
import play.db.jpa.JPAApi;

public class StudentServiceImpl implements StudentService {
	
	@Inject
	private JPAApi jpaApi;
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> vrati() {
		List<Student> studenti = (List<Student>) jpaApi.em().createQuery("select s from Student s").getResultList();
		studenti.forEach(s -> {
			s.prosek = s.prijavljeniIspiti.stream().mapToDouble(ispit -> ispit.ocena != null ? ispit.ocena : 0).average().orElse(0);
			s.esp = s.prijavljeniIspiti.stream().filter(prijava -> prijava.ocena != null && prijava.ocena >= 6)
					.map(prijava -> prijava.ispit).mapToInt(ispit -> ispit.ispit.espBodovi).sum();
		});
		return studenti;	
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
