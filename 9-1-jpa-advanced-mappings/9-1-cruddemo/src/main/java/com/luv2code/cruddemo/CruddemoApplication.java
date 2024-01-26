package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.AppDAO;
import com.luv2code.cruddemo.entity.Instructor;
import com.luv2code.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createInstructor(appDAO);
			findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetail(appDAO);
			//deleteInstructorDetail(appDAO);
		};
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
