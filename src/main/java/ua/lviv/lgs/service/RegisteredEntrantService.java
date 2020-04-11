package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.RegisteredEntrantRepository;
import ua.lviv.lgs.domain.RegisteredEntrant;

@Service
public class RegisteredEntrantService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private RegisteredEntrantRepository registeredEntrantRepository;

	public void save(RegisteredEntrant registeredEntrant) {
		logger.info("Створити нового вступника: " + registeredEntrant);

		registeredEntrantRepository.save(registeredEntrant);
	}

	public RegisteredEntrant findById(Integer id) {
		logger.info("Знайти вступника за id: " + id);

		return registeredEntrantRepository.findById(id).get();
	}

	public List<RegisteredEntrant> findAllRegisteredEntrants() {
		logger.info("Знайти всіх втупників");

		return registeredEntrantRepository.findAll();
	}

	public void deleteById(Integer id) {
		logger.info("Видалити вступника за id: " + id);

		registeredEntrantRepository.deleteById(id);
	}
}
