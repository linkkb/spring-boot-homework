package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createCourseAndStudents(appDAO);
			//findCourseAndStudents(appDAO);
			//findStudentAndCourses(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteCourse(appDAO);
			deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		int theId=2;
		System.out.println("deleting student for ID: " + theId);
		appDAO.deleteStudentById(theId);
		System.out.println("Done!");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		int theId=2;
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);
		Course c1 = new Course("Pinball");
		Course c2 = new Course("Rubik's");
		student.addCourse(c1);
		student.addCourse(c2);
		System.out.println("Updating student " + student);
		System.out.println("associated courses " + student.getCourses());
		appDAO.update(student);
		//due to cascade, this should add new courses
		System.out.println("done");
	}

	private void findStudentAndCourses(AppDAO appDAO) {
		int theId=1;
		Student student = appDAO.findStudentAndCoursesByStudentId(theId);
		System.out.println("Loaded student: " + student);
		System.out.println("Loaded courses: " + student.getCourses());
		System.out.println("Done!");
	}

	private void findCourseAndStudents(AppDAO appDAO) {
		int theId=10;
		Course course = appDAO.findCourseAndStudentsByCourseId(theId);
		System.out.println("Loaded course: " + course);
		System.out.println("Loaded students: " + course.getStudents());
		System.out.println("Done!");
	}

	private void createCourseAndStudents(AppDAO appDAO) {
		Course course = new Course("Pacman");
		Student student1 = new Student("John","Doe","jdoe@gmail.com");
		Student student2 = new Student("Johns","Does","jdoes@gmail.com");

		course.addStudent(student1);
		course.addStudent(student2);

		System.out.println("Saving course " + course);
		System.out.println("cascade saving students " + student1 + student2);

		appDAO.save(course);
		System.out.println("done!");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		int courseId=10;
		System.out.println("deleting course " + courseId);
		appDAO.deleteCourseById(courseId);
	}

	private void retrieveCourseAndReviews(AppDAO appDAO) {
		int theId=10;
		//get the course and reviews
		Course course = appDAO.findCourseAndReviewsByCourseId(theId);
		//print the course and reviews
		System.out.println("Found course: "+course);
		System.out.println("Found reviews: "+course.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		//create a course
		Course tempCourse = new Course("Pac-man");
		//add reviews
		tempCourse.addReview(new Review("Awesome!"));
		tempCourse.addReview(new Review("Awesome!!"));
		tempCourse.addReview(new Review("Awesome!!!"));
		//save course - cascade.ALL will also save the reviews.
		appDAO.save(tempCourse);
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId=10;
		System.out.println("deleting instructor for ID: " + theId);
		appDAO.deleteCourseById(theId);
		System.out.println("Done!");
	}

	private void deleteInstructorById(AppDAO appDAO) {
		//must remove instructor from course before deleting instructor
		int theId=1;
		System.out.println("deleting instructor for ID: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("Done!");
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 10;

		System.out.println("Finding course for ID: " + theId);
		Course course = appDAO.findCourseById(theId);

		System.out.println("Updating course id: " + theId);
		course.setTitle("New title");

		appDAO.update(course);
		System.out.println("done");

	}

	private void updateInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor for ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		System.out.println("Updating instructor id: " + theId);
		tempInstructor.setLastName("TESTER");

		appDAO.update(tempInstructor);
		System.out.println("done");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int theId=1;

		System.out.println("Finding instructor for ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("done");
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;

		System.out.println("Finding instructor for ID: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);

		System.out.println("Finding courses for ID: " + theId);
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);
		tempInstructor.setCourses(courses);
		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("done");
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int theId=1;
		System.out.println("Finding instructorId: " + theId);

		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		//This will error out since default fetch type is lazy
		System.out.println("The associated courses: " + tempInstructor.getCourses());
		System.out.println("done");
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		//Create the instructor
		Instructor tempInstructor =
				new Instructor("Susan","Darby","sdarby@luv2code.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://wwww.luv2code.com/youtube",
						"Luv2Code"
				);
		//associate the details
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//same for courses
		Course tempCourse1 = new Course("Air Guitar");
		Course tempCourse2 = new Course("Pinball");
		tempInstructor.add(tempCourse1);
		tempInstructor.add(tempCourse2);

		//Save the instructor
		//Will also save details/courses objects due to Cascade
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("Saving courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);
		System.out.println("done");
	}

	private void deleteInstructorDetail(AppDAO appDAO) {
		int theId = 3;
		System.out.println("Deleting instructorDetail id: " + theId);
		appDAO.deleteInstructorDetailById(theId);
		System.out.println("deleted");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		//get the instructorDetail
		int theId = 2;
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(theId);
		//print detail
		System.out.println("Found InstructorDetail for ID 2: " + tempInstructorDetail);
		//print instructor
		System.out.println("The associated instructor: "+tempInstructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int theId = 1;
		System.out.println("Deleting instructor id: " + theId);
		appDAO.deleteInstructorById(theId);
		System.out.println("deleted");
	}

	private void findInstructor(AppDAO appDAO) {
		int theId=3;
		System.out.println("Finding instructor id: " + theId);
		Instructor tempInstructor = appDAO.findInstructorById(theId);
		System.out.println("tempInstructor: " + tempInstructor);
		System.out.println("instructorDetails: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		//Create the instructor
		Instructor tempInstructor =
				new Instructor("Chad","Darby","darby@luv2code.com");

		InstructorDetail tempInstructorDetail =
				new InstructorDetail(
						"http://wwww.luv2code.com/youtube",
						"Luv2Code"
				);
		//associate the objects
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		//Save the instructor
		//Will also save details object due to CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);
		return;
	}
}
