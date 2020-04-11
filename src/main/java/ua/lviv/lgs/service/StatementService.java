package ua.lviv.lgs.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.StatementRepository;
import ua.lviv.lgs.domain.Statement;

@Service
public class StatementService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private StatementRepository statementRepository;

	public Statement save(Statement statement) {
		logger.info("Створити нове завдання: " + statement);

		return statementRepository.save(statement);
	}

	public List<Statement> findAllStatements() {
		logger.info("Знайти всі завдання");

		return statementRepository.findAll();
	}
}
