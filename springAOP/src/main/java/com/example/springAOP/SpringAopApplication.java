package com.example.springAOP;

import com.example.springAOP.dao.AccountDao;
import com.example.springAOP.dao.MembershipDao;
import com.example.springAOP.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDao accountDao, MembershipDao membershipDao, TrafficFortuneService theTrafficFortuneService){
		return runner -> {
			//demoTheBeforeAdvice(accountDao, membershipDao);

			//demoTheAfterReturningAdvice(accountDao);

			//demoTheAfterThrowingAdvice(accountDao);

			//demoTheAfterAdvice(accountDao);

			//demoTheAroundAdvice(theTrafficFortuneService);

			demoTheAroundAdviceHandleException(theTrafficFortuneService);
		};
	}


	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdviceHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWire = true;
		String data = theTrafficFortuneService.getFortune(tripWire);

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");

	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\nMain Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = theTrafficFortuneService.getFortune();

		System.out.println("\nMy fortune is: " + data);

		System.out.println("Finished");
	}

	private void demoTheAfterAdvice(AccountDao accountDao) {
		try {
			List<Account> accounts = accountDao.findAccounts(false);
		}catch (Exception e){
			System.out.println("Main method: exception"+ e);
		}
		List<Account> accounts = accountDao.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterFinallyAdvice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void demoTheAfterThrowingAdvice(AccountDao accountDao) {
		try {
			List<Account> accounts = accountDao.findAccounts(true);
 		}catch (Exception e){
			System.out.println("Main method: exception"+ e);
		}
		List<Account> accounts = accountDao.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}

	private void demoTheAfterReturningAdvice(AccountDao accountDao) {
		List<Account> accounts = accountDao.findAccounts();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
		System.out.println("----");
		System.out.println(accounts);
		System.out.println("\n");
	}


	private void demoTheBeforeAdvice(AccountDao accountDao, MembershipDao membershipDao) {
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		accountDao.addAccount(myAccount);
		accountDao.updateAccount();

		//call accountdao getter/setter
		accountDao.setAccHolderName("Shubham Gupta");
		accountDao.setAmount(1000);

		System.out.println("Getter method inside main class, the account holder name is : "+ accountDao.getAccHolderName() + " amount: " + accountDao.getAmount());

		membershipDao.addSillyMember();
		membershipDao.updateSillyMember();
	}
}
