package com.capgemini.payment.test;

import java.math.BigDecimal;

import org.junit.Test;

import com.capgemini.payment.exceptions.DuplicateMobileNumberException;
import com.capgemini.payment.exceptions.InsufficientAmountException;
import com.capgemini.payment.exceptions.InvalidMobileNumberException;
import com.capgemini.payment.exceptions.NameNotEnteredException;
import com.capgemini.payment.repo.WalletRepo;
import com.capgemini.payment.repo.WalletRepoImp;
import com.capgemini.payment.service.WalletService;
import com.capgemini.payment.service.WalletServiceImp;

public class WalletTest {
	
	WalletRepo walletRepo=new WalletRepoImp();
	WalletService walletService=new WalletServiceImp(walletRepo);
	
	/*
	 * -------------when entered new mobile number is already exists then this blocks executes------------------
	 */
	@Test(expected=com.capgemini.payment.exceptions.DuplicateMobileNumberException.class)
	public void whenDuplicateMobileNumberEntryIsPassed() throws DuplicateMobileNumberException, NameNotEnteredException {
		
		  walletService.createAccount("Abhinav Singh","9639399344",BigDecimal.valueOf(20000));
		  walletService.createAccount("Aditya Nath Singh","9639399344",BigDecimal.valueOf(50000));
		    
	}
  /* 
   *  --------------------when name is not entered in a create account method -----------------------
  */
	
	@Test(expected=com.capgemini.payment.exceptions.NameNotEnteredException.class)
	public void whenNameNotEnteredInCreateAccount() throws NameNotEnteredException, Exception{
		
		  walletService.createAccount("","9639399344",BigDecimal.valueOf(20000));
		    
	}
	
	
	/*
	 *  ------------------when amount is not sufficient in an account----------------
	 */
	
	@Test(expected=com.capgemini.payment.exceptions.InsufficientAmountException.class)
	public void whenTheAmountEnterdIsInsufficient() throws  InvalidMobileNumberException, InsufficientAmountException, DuplicateMobileNumberException, NameNotEnteredException{
		  walletService.createAccount("Sushil","9639399344",BigDecimal.valueOf(20000));

		walletService.withdrawAmount("9639399344", BigDecimal.valueOf(100000));
		    
	}
	
	
	
	/*
	 * 
	 *----------------------when valid mobile number is passed show balance successfully------------------- 
	 *
	 */
	@Test
	public void whenValidInfoIsPassedShowBalanceSuccessfully() throws InvalidMobileNumberException, DuplicateMobileNumberException, InsufficientAmountException, NameNotEnteredException {
		walletService.createAccount("Nawab", "9854121412",BigDecimal.valueOf(200000));
		walletService.showBalance("9854121412");	

}
	
	/*
	 * -------------------when valid information is passed fund transfer successfully----------------------------
	 * 
	 */
	@Test
	public void whenValidInfoIsPassedDepositandWithdrawAmountSuccessfully() throws InvalidMobileNumberException, DuplicateMobileNumberException, InsufficientAmountException, NameNotEnteredException {
		walletService.createAccount("Nawab", "9854121412",BigDecimal.valueOf(20000));
		walletService.createAccount("suraj", "9854121413", BigDecimal.valueOf(60000));
		walletService.fundTransfer("9854121412","9854121413",BigDecimal.valueOf(200000));	
	
}
	/*
	 * 
	 * ------------------when valid info is passed  deposit amount successfully------------------------
	 */
	@Test
	public void whenValidInfoIsPassedDepositAmountSuccessfully() throws InvalidMobileNumberException, DuplicateMobileNumberException, InsufficientAmountException, NameNotEnteredException {
		walletService.createAccount("Abhishek", "9639399344", BigDecimal.valueOf(200000));
		walletService.depositAmount("9639399344", BigDecimal.valueOf(20000));	

	}
	
	/*
	 * 
	 * ------------------when valid info is passed  withdraw amount successfully------------------------
	 */
	@Test
	public void whenValidInfoIsPassedWithdrawAmountSuccessfully() throws InvalidMobileNumberException, DuplicateMobileNumberException, InsufficientAmountException, NameNotEnteredException {
		walletService.createAccount("Abhinav", "9639399344", BigDecimal.valueOf(200000));
		walletService.withdrawAmount("9639399344", BigDecimal.valueOf(2000));	
}
	/*
	 * 
	 * -------------------when valid info is passed account created successfully-------------------------
	 */
	@Test
	public void whenValidInfoIsPassedCreateAccountSuccessfully() throws DuplicateMobileNumberException, NameNotEnteredException {
		walletService.createAccount("sushil", "9854121412", new BigDecimal("100.00"));
}
}