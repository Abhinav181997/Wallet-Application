package com.capgemini.payment.repo;

import java.util.HashMap;
import java.util.Map;

import com.capgemini.payment.beans.*;
import com.capgemini.payment.exceptions.DuplicateMobileNumberException;

public class WalletRepoImp implements WalletRepo{

	 HashMap<String,Customer> hashmap=new HashMap<>();
	
	@Override
	public boolean save(Customer customer) {
	
		if (hashmap.containsKey(customer.getMobileNo())) {
			return false;
		}
		hashmap.put(customer.getMobileNo(), customer);
		return true;
		
		/*String mobileNo=customer.getMobileNo();
	hashmap.put(mobileNo,customer);
	
		return true;*/
	}

	@Override
	public Customer findOne(String mobileNo) throws DuplicateMobileNumberException {
		
		for (Map.Entry<String, Customer> entry : hashmap.entrySet()) {
			if (entry.getValue().getMobileNo().equals(mobileNo)) {
				return entry.getValue();
			}
		}
		throw new DuplicateMobileNumberException();
		
	
	}
}


