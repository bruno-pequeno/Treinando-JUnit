package com.ecommerce.controllers;

import com.ecommerce.DTO.SaveUserDTO;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.UserModel;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserModel> saveUser(@RequestBody SaveUserDTO user){
        return userService.creatingUser(user);
    }

    @GetMapping("/auth/{cpf}/{password}")
    public ResponseEntity<?> authentication(@PathVariable String cpf, @PathVariable String password) throws ResourceNotFoundException {
        try {
        ResponseEntity<UserModel> userAuth = userService.validatingAuthentication(cpf, password);

            return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(userAuth.getBody());
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{cpf}/50")
    public ResponseEntity<?> stamps50(@PathVariable String cpf) throws ResourceNotFoundException {
        try {
            ResponseEntity<UserModel> modifyngStamps = userService.stamps50(cpf);

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(modifyngStamps.getBody());
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{cpf}/75")
    public ResponseEntity<?> stamps75(@PathVariable String cpf) throws ResourceNotFoundException {
        try {
            ResponseEntity<UserModel> modifyngStamps = userService.stamps75(cpf);

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(modifyngStamps.getBody());
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/user/{cpf}/100")
    public ResponseEntity<?> authentication(@PathVariable String cpf) throws ResourceNotFoundException {
        try {
            ResponseEntity<UserModel> modifyngStamps = userService.stamps100(cpf);

            return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(modifyngStamps.getBody());
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
