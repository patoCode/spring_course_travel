package com.f5.travel.infraestructure;

import com.f5.travel.util.exceptions.ForbiddenCustomerException;
import org.springframework.stereotype.Component;

@Component
public class BlackListHelper {

  public void isInBlackListCustomer(Integer customerId){
    if(customerId == 1){
      throw new ForbiddenCustomerException();
    }
  }

}