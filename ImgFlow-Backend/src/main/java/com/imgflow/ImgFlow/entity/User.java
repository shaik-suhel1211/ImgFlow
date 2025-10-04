package com.imgflow.ImgFlow.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    @Column(nullable = false,length = 30)
    @Size(min =3,max =30,message = "Username must be 8 characters")
    private String username;
    @Email
    @Column(nullable = false,unique = true)
    @Size(min =12,max=50,message = "Email should be greater than 11 characters")
    private String email;
    @Column(nullable = false)
    @Size(min =8,message = "Password must be greater than 8 characters")
    private String password;
    @Column(nullable = false,unique = true)
    private String accessToken;
}