package services;

import java.util.List;

import models.Student;

public interface StudentService {
	public List<Student> vrati();
	public Student nadjiPoId(Integer id);
	public boolean obrisiStudenta(Integer id);
}

