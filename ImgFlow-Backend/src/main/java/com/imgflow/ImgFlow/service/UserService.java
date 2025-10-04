package com.imgflow.ImgFlow.service;


import com.imgflow.ImgFlow.dto.UserResponse;
import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

// for this service the server validates token from the header and then only proceed which means here JwtAF intercepts this request

    public UserResponse getUser(String email)throws Exception{
        System.out.println("looking for email "+email);
     User user=userRepository.findByEmail(email).orElseThrow(()-> new Exception("User Not Found"));
        UserResponse ur=new UserResponse();
        ur.setUsername(user.getUsername());
        ur.setEmail(user.getEmail());
        ur.setAccessToken(user.getAccessToken());
        return ur;
    }
}
