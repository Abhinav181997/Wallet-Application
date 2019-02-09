package com.capgemini.payment.service;

import java.math.BigDecimal;

import com.capgemini.payment.beans.Customer;
import com.capgemini.payment.beans.Wallet;
import com.capgemini.payment.repo.WalletRepo;
import com.capgemini.payment.exceptions.*;

public class WalletServiceImp implements WalletService {

	WalletRepo walletRepo;

	public WalletServiceImp(WalletRepo walletRepo) {
		this.walletRepo = walletRepo;
	}

	@Override
	public Customer createAccount(String name, String mobileNo, BigDecimal amount)
			throws DuplicateMobileNumberException, NameNotEnteredException {


		Wallet wallet = new Wallet(amount);
		Customer customer = new Customer(name, mobileNo, wallet);


		if (name == "")
			throw new NameNotEnteredException();

		if (walletRepo.save(customer)) {
			return customer;
		}
		throw new DuplicateMobileNumberException();
	}

	@Override
	public Customer showBalance(String mobileNo) throws DuplicateMobileNumberException {

		Customer customer = walletRepo.findOne(mobileNo);
		if ((customer.getMobileNo()).equals(mobileNo)) {
			return customer;
		}
		return null;
	}

	@Override
	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount)
			throws DuplicateMobileNumberException {

		Customer a = walletRepo.findOne(sourceMobileNo);
		Customer b = walletRepo.findOne(targetMobileNo);

		if (a != null && b != null) {
			Wallet w1 = new Wallet();
			Wallet w2 = new Wallet();
			w1.setBalance(amount.add(b.getWallet().getBalance()));
			w2.setBalance((a.getWallet().getBalance()).subtract(amount));

			a.setWallet(w2);
			b.setWallet(w1);
			return b;

		}
		return null;
	}

	@Override
	public Customer depositAmount(String mobileNo, BigDecimal amount) throws DuplicateMobileNumberException, InvalidMobileNumberException {
      
		
		Customer customer = walletRepo.findOne(mobileNo);
	
		if ((walletRepo.findOne(mobileNo).getMobileNo()).equals(mobileNo)) {
			customer.getWallet().setBalance(amount.add(customer.getWallet().getBalance()));
			return customer;
		}
	
		throw new InvalidMobileNumberException();
	}

	@Override
	public Customer withdrawAmount(String mobileNo, BigDecimal amount)
			throws InsufficientAmountException, DuplicateMobileNumberException, InvalidMobileNumberException {

		Customer customer= walletRepo.findOne(mobileNo);
		Wallet wallet = customer.getWallet();
		
		if (amount.compareTo(wallet.getBalance()) > 0) {
			
			throw new InsufficientAmountException();
		}
			if ((walletRepo.findOne(mobileNo).getMobileNo()).equals(mobileNo)) {
				wallet.setBalance((customer.getWallet().getBalance()).subtract(amount));
				customer.setWallet(wallet);
				return customer;
			}
			throw new InvalidMobileNumberException();
		}

	}

