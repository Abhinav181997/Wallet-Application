package com.capgemini.payment.service;

import java.math.BigDecimal;
import com.capgemini.payment.beans.Customer;
import com.capgemini.payment.exceptions.DuplicateMobileNumberException;
import com.capgemini.payment.exceptions.InsufficientAmountException;
import com.capgemini.payment.exceptions.InvalidMobileNumberException;
import com.capgemini.payment.exceptions.NameNotEnteredException;

public interface WalletService {

public Customer createAccount(String name,String mobileNo,BigDecimal amount) throws DuplicateMobileNumberException, NameNotEnteredException;
public Customer showBalance(String mobileNo) throws InvalidMobileNumberException, DuplicateMobileNumberException;
public Customer fundTransfer(String sourceMobileNo,String targetMobileNo,BigDecimal amount) throws InvalidMobileNumberException,DuplicateMobileNumberException,InsufficientAmountException;
public Customer depositAmount(String mobileNo,BigDecimal amount) throws InvalidMobileNumberException, DuplicateMobileNumberException;
public Customer withdrawAmount(String mobileNo, BigDecimal amount) throws InvalidMobileNumberException,InsufficientAmountException, DuplicateMobileNumberException;
}
