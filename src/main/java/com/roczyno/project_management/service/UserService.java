package com.roczyno.project_management.service;

import com.roczyno.project_management.model.User;

public interface UserService {
   User findUserProfileByJwt(String jwt) ;
   User findUserByEmail(String email);
   User findUserById(Long id);
   User updateUsersProjectSize(User user,int number);
   String deleteUser(Long id);
}
