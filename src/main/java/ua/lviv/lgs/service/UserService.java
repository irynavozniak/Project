package ua.lviv.lgs.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ua.lviv.lgs.dao.UserRepository;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRoles;

@Service
public class UserService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public void save(User user) {
		logger.info("Створити нового користувача: " + user);

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPasswordConfirm()));
		user.setRole(UserRoles.USER);
		userRepository.save(user);
	}

	public User findUserByEmail(String email) {
		logger.info("Знайти користувача за email: " + email);

		return userRepository.findByEmail(email).get();
	}

	public User findUserById(Integer id) {
		logger.info("Знайти користувача за id: " + id);

		return userRepository.findById(id).get();
	}
}
