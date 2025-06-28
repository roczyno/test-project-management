package com.roczyno.project_management.service;

import com.roczyno.project_management.config.JwtProvider;
import com.roczyno.project_management.exception.UserException;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.UserRepository;
import com.roczyno.project_management.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public User findUserProfileByJwt(String jwt)  {
        String email=jwtProvider.getEmailFromToken(jwt);
		return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) {
        User user= userRepository.findByEmail(email);
        if(user==null){
            throw new UserException(ErrorConstants.USER_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User findUserById(Long id)  {
        return userRepository.findById(id)
                .orElseThrow(()->new UserException(ErrorConstants.USER_NOT_FOUND));
    }

    @Override
    public User updateUsersProjectSize(User user, int number){
        user.setProjectSize(user.getProjectSize()+number);
        return userRepository.save(user);
    }

    @Override
    public String deleteUser(Long id) {
        User user=userRepository.findById(id).orElseThrow();
        userRepository.delete(user);
        return "user deleted";
    }
}
