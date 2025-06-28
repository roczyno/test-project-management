package com.roczyno.project_management.service;

import com.roczyno.project_management.config.JwtProvider;
import com.roczyno.project_management.exception.UserException;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.UserRepository;
import com.roczyno.project_management.request.AuthResponse;
import com.roczyno.project_management.request.LoginRequest;
import com.roczyno.project_management.util.AppConstants;
import com.roczyno.project_management.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserDetailsService customUserDetailService;
    private final SubscriptionService subscriptionService;




    public String registerUser(User user) {
        User isEmailExist= userRepository.findByEmail(user.getEmail());
        if(isEmailExist !=null){
            throw new UserException("Email already exists");
        }
        User createUser= new User();
        createUser.setUsername(user.getUsername());
        createUser.setEmail(user.getEmail());
        createUser.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser= userRepository.save(createUser);
        subscriptionService.createSubscription(savedUser);
        userRepository.save(savedUser);
        return AppConstants.USER_REGISTERED;
    }


    public AuthResponse login(LoginRequest req){
        String email= req.getEmail();
        String password= req.getPassword();
        Authentication authentication= authenticate(email, password);
        String token = jwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse();
        res.setToken(token);
        res.setMessage(AppConstants.LOGIN_SUCCESSFUL);
        return res;

    }

    private Authentication authenticate(String username, String password) {

        UserDetails userDetails= customUserDetailService.loadUserByUsername(username);
        if(userDetails ==null){
            throw new BadCredentialsException(ErrorConstants.INVALID_USERNAME);
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException(ErrorConstants.WRONG_CREDENTIALS);
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
