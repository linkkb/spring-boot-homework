package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Course;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findInstructorWithCoursesJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructorById(appDAO);
			deleteCourse(appDAO);
		};
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
