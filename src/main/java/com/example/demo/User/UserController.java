package com.example.demo.User;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody UserModel userModel) {
        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("/")
    public ResponseEntity findUser(String username){
        var user = this.userRepository.findByUsername(username);
        if(user==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario nao encontrado");
        }

        return ResponseEntity.status(HttpStatus.FOUND).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id){
        Optional<UserModel> user = userRepository.findById(id);
        if(user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }else{
            userRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuario deletado com sucesso");
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel userModel,
                                                @PathVariable UUID id,
                                                HttpServletRequest request) {

        Optional<UserModel> optionalUser = this.userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else
        {
            UserModel existingUser = optionalUser.get();
            if (request.getAttribute("nome") != null) {
                existingUser.setNome((String) request.getAttribute("nome"));
            } else {
                existingUser.setNome(userModel.getNome());
            }
            if (request.getAttribute("username") != null) {
                existingUser.setUsername((String) request.getAttribute("username"));
            } else {
                existingUser.setUsername(userModel.getUsername());
            }
            if (request.getAttribute("password") != null) {
                existingUser.setPassword((String) request.getAttribute("password"));
            } else {
                existingUser.setPassword(userModel.getPassword());
            }
            if (request.getAttribute("email") != null) {
                existingUser.setEmail((String) request.getAttribute("email"));
            } else {
                existingUser.setEmail(userModel.getEmail());
            }

            UserModel updatedUser = this.userRepository.save(existingUser);
            return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
        }
    }

}
