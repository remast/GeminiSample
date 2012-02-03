package com.pe_international.sample.ui.main;

import com.pe_international.sample.ui.main.service.User;

public class UserImpl implements User {
   
   private String name;
   
   public UserImpl(final String name) {
      this.name = name;
   }
   
   public void setName(final String name) {
      this.name = name;
   }

   @Override
   public String getName() {
      return name;
   }

}
