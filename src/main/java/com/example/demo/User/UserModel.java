package com.example.demo.User;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "tb_user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    @Column(unique = true, updatable = false)
    private String username;
    @Length(min = 8)
    private String password;
    @Email(message = "Insira um email válido")
    private String email;
    private String cpf;
    private String identidade;

    @CreationTimestamp
    private LocalDateTime createdAt;



}
