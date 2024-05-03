package app.romail.moodle_plus_plus.bootstrap;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.repositories.AccountRepository;
import app.romail.moodle_plus_plus.repositories.StudentGroupRepository;
import app.romail.moodle_plus_plus.repositories.StudentRepository;
import app.romail.moodle_plus_plus.repositories.TeacherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class DataLoader implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final StudentGroupRepository studentGroupRepository;

	public DataLoader(AccountRepository accountRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, StudentGroupRepository studentGroupRepository) {
		this.accountRepository = accountRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.studentGroupRepository = studentGroupRepository;

	}

	@Override
	public void run(String... args) throws Exception {
		PasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Account studentAccount1 = new Account("john.doe", bcrypt.encode("1234"));
		studentAccount1.getRoles().add(Role.ROLE_STUDENT);
		accountRepository.save(studentAccount1);
		Student student1 = new Student("John", "Doe", "john@doe.com","+1234567890", "Main Road 1, Bucharest", "M", Date.valueOf("2002-01-01"),"john.doe", Date.valueOf("2020-10-01"));
		student1.setAccount(studentAccount1);
		studentAccount1.setPerson(student1);
		studentRepository.save(student1);
		accountRepository.save(studentAccount1);

		Account teacherAccount1 = new Account("jane.smith", bcrypt.encode("1234"));
		teacherAccount1.getRoles().add(Role.ROLE_TEACHER);
		accountRepository.save(teacherAccount1);

		Teacher teacher1 = new Teacher("Jane", "Smith", "jane.smith@gmail.com", "+1234567890", "Main Road 2, Bucharest", "F", Date.valueOf("1980-01-01"), 10000, "Math Teacher");
		teacher1.setAccount(teacherAccount1);
		teacherAccount1.setPerson(teacher1);

		teacherRepository.save(teacher1);
		accountRepository.save(teacherAccount1);

		Account serviceAccount = new Account("service", bcrypt.encode("service"));
		serviceAccount.getRoles().add(Role.ROLE_SYSTEM);
		accountRepository.save(serviceAccount);

		StudentGroup sg1 = new StudentGroup("1231A");
		studentGroupRepository.save(sg1);
		sg1.getStudents().add(student1);
		student1.setGroup(sg1);
		studentRepository.save(student1);
		studentGroupRepository.save(sg1);

	}
}
