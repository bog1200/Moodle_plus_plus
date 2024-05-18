package app.romail.moodle_plus_plus.bootstrap;

import app.romail.moodle_plus_plus.domain.*;
import app.romail.moodle_plus_plus.repositories.*;
import app.romail.moodle_plus_plus.services.SubjectEnrollmentServiceImpl;
import app.romail.moodle_plus_plus.services.SubjectServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Component
public class DataLoader implements CommandLineRunner {

	private final AccountRepository accountRepository;
	private final StudentRepository studentRepository;
	private final TeacherRepository teacherRepository;
	private final StudentGroupRepository studentGroupRepository;
	private final CourseRepository courseRepository;
	private final SubjectRepository subjectRepository;
	private final CourseAttendanceRepository courseAttendanceRepository;
	private final SubjectEnrollmentRepository subjectEnrollmentRepository;
	private final AssignmentRepository assignmentRepository;
	private final AssignmentSubmissionRepository assingmentSubmissionRepository;
	private final GradeRepository gradeRepository;
	private final IdDocumentRepository idDocumentRepository;
	private final SubjectServiceImpl subjectServiceImpl;
	private final SubjectEnrollmentServiceImpl subjectEnrollmentServiceImpl;

	public DataLoader(AccountRepository accountRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, StudentGroupRepository studentGroupRepository, CourseRepository courseRepository, SubjectRepository subjectRepository, CourseAttendanceRepository courseAttendanceRepository, SubjectEnrollmentRepository subjectEnrollmentRepository, AssignmentRepository assignmentRepository, AssignmentSubmissionRepository assingmentSubmissionRepository, GradeRepository gradeRepository, IdDocumentRepository idDocumentRepository, SubjectServiceImpl subjectServiceImpl, SubjectEnrollmentServiceImpl subjectEnrollmentServiceImpl) {
		this.accountRepository = accountRepository;
		this.studentRepository = studentRepository;
		this.teacherRepository = teacherRepository;
		this.studentGroupRepository = studentGroupRepository;
		this.courseRepository = courseRepository;
		this.subjectRepository = subjectRepository;
		this.courseAttendanceRepository = courseAttendanceRepository;
		this.subjectEnrollmentRepository = subjectEnrollmentRepository;
		this.assignmentRepository = assignmentRepository;
		this.assingmentSubmissionRepository = assingmentSubmissionRepository;
		this.gradeRepository = gradeRepository;
		this.idDocumentRepository = idDocumentRepository;
		this.subjectServiceImpl = subjectServiceImpl;
		this.subjectEnrollmentServiceImpl = subjectEnrollmentServiceImpl;
	}

	@Override
	public void run(String... args) throws Exception {
		PasswordEncoder bcrypt = new BCryptPasswordEncoder();

		if (accountRepository.count() > 0) {
			System.out.println("Data already loaded");
			return;
		}
		System.out.println("Loading data...");
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

		Subject subject1 = new Subject("Math", "Mathematics", "MATH101");
		subjectRepository.save(subject1);

		Subject subject2 = new Subject("Physics", "Physics", "PHYS101");
		subjectRepository.save(subject2);

		Date startDate = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-10-01 08:00:00").getTime());
		Date endDate = new Date(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2021-10-01 10:00:00").getTime());
		Course course1 = new Course(startDate, endDate);
		course1.setSubject(subject1);
		courseRepository.save(course1);
		subject1.getCourses().add(course1);
		subjectRepository.save(subject1);


		subject1.getCourses().add(course1);
		subject1.getTeachers().add(teacher1);
		subject1.getStudentGroups().add(sg1);
		sg1.getSubjects().add(subject1);

		teacher1.getSubjects().add(subject1);

		subject2.getTeachers().add(teacher1);
		teacher1.getSubjects().add(subject2);

		subjectRepository.save(subject1);


		studentGroupRepository.save(sg1);
		subjectRepository.save(subject1);
		teacherRepository.save(teacher1);
		SubjectEnrollment ce1 = new SubjectEnrollment(subject1, student1);
		subjectEnrollmentServiceImpl.save(ce1);
		CourseAttendance ca1 = new CourseAttendance(ce1, startDate);
		course1.getCourseAttendances().add(ca1);
		ca1.setCourse(course1);
		courseAttendanceRepository.save(ca1);
		courseRepository.save(course1);

		Assignment assignment1 = new Assignment(AssignmentType.HOMEWORK, "Homework 1", "First homework", subject1, startDate, endDate, endDate, 10);
		assignmentRepository.save(assignment1);
		subject1.getAssignments().add(assignment1);
		subjectRepository.save(subject1);

		AssignmentSubmission assignmentSubmission1 = new AssignmentSubmission(assignment1, student1, startDate, "Homework 1 submission");
	    assingmentSubmissionRepository.save(assignmentSubmission1);

		AssignmentSubmission assignmentSubmission2 = new AssignmentSubmission(assignment1, student1, startDate, "Homework 2 submission");
	    assingmentSubmissionRepository.save(assignmentSubmission2);


		Grade grade1 = new Grade(9.0, 10.0, "Good job", "2021-10-01", assignmentSubmission1);
		gradeRepository.save(grade1);
		assignmentSubmission1.setGrade(grade1);
		assingmentSubmissionRepository.save(assignmentSubmission1);

		IdDocument idDocument1 = new IdDocument(studentAccount1, IdDocumentType.EU_ID_CARD, "123456789", "2020-01-01", "2025-01-01", "1234567890", "ROU");
		idDocumentRepository.save(idDocument1);

		IdDocument idDocument2 = new IdDocument(teacherAccount1, IdDocumentType.PASSPORT, "123456789", "2020-01-01", "2025-01-01", "1234567891", "ROU");
		idDocumentRepository.save(idDocument2);

		System.out.println("Data loaded");

	}
}
