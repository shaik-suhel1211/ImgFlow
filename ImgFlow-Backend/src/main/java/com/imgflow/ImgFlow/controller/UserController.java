package com.imgflow.ImgFlow.controller;



import com.imgflow.ImgFlow.dto.UserResponse;
import com.imgflow.ImgFlow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {
    //this controller requires authentication which means requests now should include the jwt in header

      @Autowired
      private UserService userService;

     @GetMapping("/getUser/{email}")
     public UserResponse getUser(@PathVariable String email) throws Exception{
             return userService.getUser(email);
    }
}
