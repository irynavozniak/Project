package test;
import org.springframework.test.context.junit4.SpringRunner;

import ua.lviv.lgs.dao.FacultyRegistrationRepo;
import ua.lviv.lgs.dao.FacultyRepo;
import ua.lviv.lgs.dao.UserRepo;
import ua.lviv.lgs.domain.Faculty;
import ua.lviv.lgs.domain.FacultyNames;
import ua.lviv.lgs.domain.FacultyRegistration;
import ua.lviv.lgs.domain.Subjects;
import ua.lviv.lgs.domain.User;
import ua.lviv.lgs.domain.UserRoles;
import ua.lviv.lgs.service.FacultyRegistrationService;
import ua.lviv.lgs.service.FacultyService;
import ua.lviv.lgs.service.UserService;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.security.auth.Subject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProjectApplicationTests {
	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private FacultyService facultyService;
	
	@Autowired
	private FacultyRepo facultyRepo;
	
	@Autowired
	private FacultyRegistrationService facultyRegistrationService;
	
	@Autowired
	private FacultyRegistrationRepo facultyRegistrationRepo;

	@Test
	public void testSaveUser() {
		List<User> users = userRepo.findAll();
		assertThat(users, hasSize(0));

		User user = new User("a", "a", "a@a", "a", UserRoles.USER);
		userService.save(user);

		users = userRepo.findAll();
		assertThat(users, hasSize(1));
		
		User userFromDB = users.get(0);
		
		assertTrue(userFromDB.getName().equals(user.getName()));
		assertTrue(userFromDB.getSurname().equals(user.getSurname()));
		assertTrue(userFromDB.getEmail().equals(user.getEmail()));
		assertTrue(userFromDB.getPassword().equals(user.getPassword()));
		assertTrue(userFromDB.getRole().equals(user.getRole()));
	}
	
	@Test
	public void testFindByEmail() {
		User user = new User("a", "a", "a@a", "a", UserRoles.USER);
		User user2 = new User("b", "b", "b@b", "b", UserRoles.USER);
		userRepo.saveAll(Arrays.asList(user, user2));
		
		List<User> users = userRepo.findAll();
		assertThat(users, hasSize(2));
		
		User userFromDB = userService.findByEmail("b@b");
		
		assertTrue(userFromDB.equals(user2));
		assertTrue(userFromDB.getName().equals(user2.getName()));
		assertTrue(userFromDB.getSurname().equals(user2.getSurname()));
		assertTrue(userFromDB.getEmail().equals(user2.getEmail()));
		assertTrue(userFromDB.getPassword().equals(user2.getPassword()));
		assertTrue(userFromDB.getRole().equals(user2.getRole()));
	}
	
	@Test
	public void testSaveFaculty() {
		List<Faculty> faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty(FacultyNames.ПЕДАГОГІЧНИЙ, 100, Arrays.asList(Subjects.ІНОЗЕМНА_МОВА, Subjects.МАТЕМАТИКА));
		facultyService.save(faculty);

		faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(1));
		
		Faculty facultyFromDB = faculties.get(0);
		
		assertTrue(facultyFromDB.equals(faculty));
		assertTrue(facultyFromDB.getId().equals(faculty.getId()));
		assertTrue(facultyFromDB.getName().equals(faculty.getName()));
		assertTrue(facultyFromDB.getNumberOfStudents() == faculty.getNumberOfStudents());
		assertTrue(facultyFromDB.getSubjects().equals(faculty.getSubjects()));
	}
	
	@Test
	public void testGetAllFaculties() {
		List<Faculty> faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty(FacultyNames.ЮРИДИЧНИЙ, 100, Arrays.asList(Subjects.МАРКЕТИНГ, Subjects.МЕНЕДЖМЕНТ));
		Faculty faculty2 = new Faculty(FacultyNames.ПЕДАГОГІЧНИЙ, 150, Arrays.asList(Subjects.ІСТОРІЯ, Subjects.ПСИХОЛОГІЯ));
		facultyRepo.saveAll(Arrays.asList(faculty, faculty2));
		
		faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(2));
		
		Faculty facultyFromDB = faculties.get(0);
		Faculty facultyFromDB2 = faculties.get(1);
		
		assertTrue(facultyFromDB.equals(faculty));
		assertTrue(facultyFromDB.getId().equals(faculty.getId()));
		assertTrue(facultyFromDB.getName().equals(faculty.getName()));
		assertTrue(facultyFromDB.getNumberOfStudents() == faculty.getNumberOfStudents());
		assertTrue(facultyFromDB.getSubjects().equals(faculty.getSubjects()));
		
		assertTrue(facultyFromDB2.equals(faculty2));
		assertTrue(facultyFromDB2.getId().equals(faculty2.getId()));
		assertTrue(facultyFromDB2.getName().equals(faculty2.getName()));
		assertTrue(facultyFromDB2.getNumberOfStudents() == faculty2.getNumberOfStudents());
		assertTrue(facultyFromDB2.getSubjects().equals(faculty2.getSubjects()));
	}
	
	@Test
	public void testFindFacultyById() {
		List<Faculty> faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(0));
		
		Faculty faculty = new Faculty(FacultyNames.БІОЛОГІЧНИЙ, 120, Arrays.asList(Subjects.ГЕОГРАФІЯ, Subjects.ІНФОРМАТИКА));
		Faculty faculty2 = new Faculty(FacultyNames.ЕКОНОМІЧНИЙ, 180, Arrays.asList(Subjects.ІНОЗЕМНА_МОВА, Subjects.ІСТОРІЯ));
		facultyRepo.saveAll(Arrays.asList(faculty, faculty2));
		
		faculties = facultyRepo.findAll();
		assertThat(faculties, hasSize(2));
		
		Faculty facultyFromDB = facultyService.findById(faculties.get(1).getId());
		
		assertTrue(facultyFromDB.equals(faculty2));
		assertTrue(facultyFromDB.getId().equals(faculty2.getId()));
		assertTrue(facultyFromDB.getName().equals(faculty2.getName()));
		assertTrue(facultyFromDB.getNumberOfStudents() == faculty2.getNumberOfStudents());
		assertTrue(facultyFromDB.getSubjects().equals(faculty2.getSubjects()));
	}
	
	@Test
	public void testSaveFacultyRegistration() {
		List<FacultyRegistration> facultyregistrations = facultyRegistrationRepo.findAll();
		assertThat(facultyregistrations, hasSize(0));
		
		Faculty faculty = new Faculty(FacultyNames.ФІЗИКО_МАТЕМАТИЧНИЙ, 100, Arrays.asList(Subjects.ІНОЗЕМНА_МОВА, Subjects.ІНФОРМАТИКА));
		facultyRepo.save(faculty);
		Faculty facultyFromDB = facultyRepo.findAll().get(0);
		
		User user = new User("a", "a", "a@a", "a", UserRoles.USER);
		userRepo.save(user);
		User userFromDB = userRepo.findAll().get(0);
		
		FacultyRegistration facultyRegistration = new FacultyRegistration();
		facultyRegistration.setUser(userFromDB);
		facultyRegistration.setFaculty(facultyFromDB);
		facultyRegistration.setMarks(Arrays.asList(157, 175, 180));
		facultyRegistration.setBase64("*");
		facultyRegistrationService.save(facultyRegistration);
		
		facultyregistrations = facultyRegistrationRepo.findAll();
		assertThat(facultyregistrations, hasSize(1));
		
		FacultyRegistration facultyRegistrationFromDB = facultyregistrations.get(0);
		
		assertTrue(facultyRegistrationFromDB.equals(facultyRegistration));
		assertTrue(facultyRegistrationFromDB.getUser().equals(userFromDB));
		assertTrue(facultyRegistrationFromDB.getFaculty().equals(facultyFromDB));
		assertTrue(facultyRegistrationFromDB.getMarks().equals(facultyRegistration.getMarks()));
		assertTrue(facultyRegistrationFromDB.getBase64().equals(facultyRegistration.getBase64()));
	}
	
	@Test
	public void testGetAllRegisteredUsers() {
		List<FacultyRegistration> facultyregistrations = facultyRegistrationService.getAllRegisteredUsers();
		assertThat(facultyregistrations, hasSize(0));
		
		Faculty faculty = new Faculty(FacultyNames.ЮРИДИЧНИЙ, 100, Arrays.asList(Subjects.МАРКЕТИНГ, Subjects.ХІМІЯ));
		facultyRepo.save(faculty);
		Faculty facultyFromDB = facultyRepo.findAll().get(0);
		
		User user = new User("a", "a", "a@a", "a", UserRoles.USER);
		User user2 = new User("b", "b", "b@b", "b", UserRoles.USER);
		userRepo.saveAll(Arrays.asList(user, user2));
		List<User> users = userRepo.findAll();
		assertThat(users, hasSize(2));
		
		User userFromDB = userRepo.findAll().get(0);
		User userFromDB2 = userRepo.findAll().get(1);
		
		FacultyRegistration facultyRegistration = new FacultyRegistration();
		facultyRegistration.setUser(userFromDB);
		facultyRegistration.setFaculty(facultyFromDB);
		facultyRegistration.setMarks(Arrays.asList(157, 175, 180));
		facultyRegistration.setBase64("*");
		facultyRegistration.setEmail("hhh");
		
		FacultyRegistration facultyRegistration2 = new FacultyRegistration();
		facultyRegistration2.setUser(userFromDB2);
		facultyRegistration2.setFaculty(facultyFromDB);
		facultyRegistration2.setMarks(Arrays.asList(174, 156, 160));
		facultyRegistration2.setBase64("!");

		facultyRegistrationRepo.saveAll(Arrays.asList(facultyRegistration, facultyRegistration2));
		
		facultyregistrations = facultyRegistrationService.getAllRegisteredUsers();
		assertThat(facultyregistrations, hasSize(2));
		
		assertTrue(facultyregistrations.get(0).equals(facultyRegistration));
		assertTrue(facultyregistrations.get(1).equals(facultyRegistration2));
	}
}