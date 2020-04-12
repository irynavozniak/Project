package ua.lviv.lgs.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.security.auth.Subject;

@Entity
@Table(name = "faculties")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private FacultyNames name;
	
	@Column(name = "numberOfStudents")
	private int numberOfStudents;
	
	@ElementCollection
	private List<Subject> subjects;
	
	public Faculty() {}

	public Faculty(FacultyNames name, int numberOfStudents, List<Subject> subjects) {
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.subjects = subjects;
	}

	public Faculty(Integer id, FacultyNames name, int numberOfStudents, List<Subject> subjects) {
		this.id = id;
		this.name = name;
		this.numberOfStudents = numberOfStudents;
		this.subjects = subjects;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public FacultyNames getName() {
		return name;
	}

	public void setName(FacultyNames name) {
		this.name = name;
	}

	public int getNumberOfStudents() {
		return numberOfStudents;
	}

	public void setNumberOfStudents(int numberOfStudents) {
		this.numberOfStudents = numberOfStudents;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Faculty other = (Faculty) obj;
		if (name != other.name)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Faculty [id=" + id + ", name=" + name + ", numberOfStudents=" + numberOfStudents + ", subjects="
				+ subjects + "]";
	}
	
	
	
}