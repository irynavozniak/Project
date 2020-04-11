package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.FacultyRepository;
import ua.lviv.lgs.domain.Faculty;

@Service
public class FacultyService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private FacultyRepository facultyRepository;

	public Faculty save(Faculty faculty) {
		logger.info("Створити новий факультет: " + faculty);

		return facultyRepository.save(faculty);
	}

	public List<Faculty> findAllFaculties() {
		logger.info("Знайти всі факультети");

		return facultyRepository.findAll();
	}

	public Faculty findFacultyById(Integer id) {
		logger.info("Знайти факультет за id: " + id);

		return facultyRepository.findById(id).get();
	}
}
