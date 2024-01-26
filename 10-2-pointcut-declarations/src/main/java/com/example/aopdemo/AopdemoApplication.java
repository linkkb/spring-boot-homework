package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			AccountDAO theAccountDAO,
			MembershipDAO theMembershipDAO,
			TrafficFortuneService theTrafficFortuneService) {
		return runner -> {
			//demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			//demoTheAroundAdvice(theTrafficFortuneService);
			demoTheAroundAdvaceHandleException(theTrafficFortuneService);
		};
	}

	private void demoTheAroundAdvaceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program: demoTheAroundAdvice");
		System.out.println("Main program: calling getFortune()");
		String data = theTrafficFortuneService.getFortune(true);
		System.out.println("Got fortune " + data);
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("Main program: demoTheAroundAdvice");
		System.out.println("Main program: calling getFortune()");
		String data = theTrafficFortuneService.getFortune();
		System.out.println("Got fortune " + data);
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		System.out.println("Main program: demoTheAfterAdvice");
		List<Account> accounts = null;
		try {
			//add a boolean flag to simulate exceptions
			boolean tripWire = false;
			accounts = theAccountDAO.findAccounts(tripWire);
		} catch(Throwable t) {
			System.out.println("Main program: caught exception " + t);
		}
		System.out.println("Main program returned with accounts " + accounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		System.out.println("Main program: demoTheAfterThrowingAdvice");
		List<Account> accounts = null;
		try {
			//add a boolean flag to simulate exceptions
			boolean tripWire = true;
			accounts = theAccountDAO.findAccounts(tripWire);
		} catch(Throwable t) {

			System.out.println("Main program: caught exception " + t);

		}
		System.out.println("Main program returned with accounts " + accounts);
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		System.out.println("Main program calling findAccounts");

		List<Account> accounts = theAccountDAO.findAccounts();

		System.out.println("Main program returned with accounts " + accounts);
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		theAccountDAO.addAccount(null, true);
		theMembershipDAO.addMembership();
		theAccountDAO.doWork(true);
		theMembershipDAO.doWork(true);
		theAccountDAO.setName("true");
		theAccountDAO.setServiceCode("true");
		theAccountDAO.getName();
		theAccountDAO.getServiceCode();
	}


}
