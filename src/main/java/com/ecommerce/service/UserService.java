package com.ecommerce.service;

import com.ecommerce.DTO.SaveUserDTO;
import com.ecommerce.exceptions.ResourceNotFoundException;
import com.ecommerce.model.UserModel;
import com.ecommerce.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<UserModel> creatingUser(SaveUserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setPassword(userDTO.getPassword());
        userModel.setCpf(userDTO.getCpf());
        userModel.setStamps(userDTO.getStamps());

        boolean isValid = validatingObjectOfCreation(userModel);
        if(isValid){
            UserModel savedUser = userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userModel);
    }

    public boolean validatingObjectOfCreation(UserModel userValidate){
        return !userValidate.getName().isEmpty() && !userValidate.getCpf().isEmpty() && !userValidate.getPassword().isEmpty();
    }

    public ResponseEntity<UserModel> getDataUser(String cpfUser){
        UserModel userData = userRepository.findDataByCPF(cpfUser);

         return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

    public String authentication(String cpf){
        return userRepository.findByCPF(cpf);
    }

    public ResponseEntity<UserModel> validatingAuthentication(String cpfUser, String passwordUser) throws ResourceNotFoundException{
      String result =  authentication(cpfUser);

        if (passwordUser.equals(result)){
            return getDataUser(cpfUser);
        } else {
            throw new ResourceNotFoundException("Usuário não encontrado!");
        }
    }
    public ResponseEntity<UserModel> stamps50(String cpfUser) throws ResourceNotFoundException{
        UserModel userData = userRepository.findDataByCPF(cpfUser);

        int stamps = userData.getStamps() - 50;
        userData.setStamps(stamps);

        userRepository.save(userData);

        return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

    public ResponseEntity<UserModel> stamps75(String cpfUser) throws ResourceNotFoundException{
        UserModel userData = userRepository.findDataByCPF(cpfUser);

        int stamps = userData.getStamps() - 75;
        userData.setStamps(stamps);

        userRepository.save(userData);

        return ResponseEntity.status(HttpStatus.OK).body(userData);
    }

    public ResponseEntity<UserModel> stamps100(String cpfUser) throws ResourceNotFoundException{
        UserModel userData = userRepository.findDataByCPF(cpfUser);

        int stamps = userData.getStamps() - 100;
        userData.setStamps(stamps);

        userRepository.save(userData);

        return ResponseEntity.status(HttpStatus.OK).body(userData);

    }

}
