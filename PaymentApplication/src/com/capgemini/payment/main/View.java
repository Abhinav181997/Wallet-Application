package com.capgemini.payment.main;

import java.math.BigDecimal;

import com.capgemini.payment.repo.WalletRepo;
import com.capgemini.payment.repo.WalletRepoImp;
import com.capgemini.payment.service.*;

public class View {

	public static void main(String[] args) throws Exception  {
		
		WalletRepo walletRepo=new WalletRepoImp();
		WalletService walletService=new WalletServiceImp(walletRepo);

	    System.out.println(walletService.createAccount("Abhinav Singh","9639399344",BigDecimal.valueOf(20000)));
	    System.out.println(walletService.createAccount("Aditya Nath Singh","8931887477",BigDecimal.valueOf(50000)));
	    
	   System.out.println(walletService.depositAmount("9639399344",BigDecimal.valueOf(1000)));
	   
	   System.out.println(walletService.showBalance("8931887477"));
	   System.out.println(walletService.showBalance("9639399344"));
	   	   
	   System.out.println(walletService.fundTransfer("8931887477", "9639399344", BigDecimal.valueOf(1000)));

	   System.out.println(walletService.showBalance("8931887477"));
	   
	   System.out.println(walletService.withdrawAmount("9639399344", BigDecimal.valueOf(52000)));
	   
	}

}
