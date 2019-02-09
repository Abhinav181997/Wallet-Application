package com.capgemini.payment.repo;

import com.capgemini.payment.beans.*;
import com.capgemini.payment.exceptions.DuplicateMobileNumberException;

public interface WalletRepo {
	
	public boolean save(Customer customer);
    public Customer findOne(String mobileNo) throws DuplicateMobileNumberException;
}
