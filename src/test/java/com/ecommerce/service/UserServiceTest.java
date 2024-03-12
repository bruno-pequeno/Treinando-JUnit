package com.ecommerce.service;

import com.ecommerce.DTO.SaveUserDTO;
import com.ecommerce.model.UserModel;
import com.ecommerce.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.CREATED;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService service;

    @Mock
    UserRepository repository;

    UserModel user;

    @BeforeEach
    public void setUp(){
        user = new UserModel(0, "Bruno", "123", "43276543590", 70);
    }

    @Test
    void deveCriarUsuarioComSucesso(){
        SaveUserDTO userSave = new SaveUserDTO();
        userSave.setName(user.getName());
        userSave.setPassword(user.getPassword());
        userSave.setCpf(user.getCpf());
        userSave.setStamps(user.getStamps());

        when(repository.save(user)).thenReturn(user);

        ResponseEntity<UserModel> userSaved = service.creatingUser(userSave);

        Assertions.assertEquals(HttpStatusCode.valueOf(201), userSaved.getStatusCode());

        System.out.println(userSaved.getBody());
    }

    @Test
    void deveFalharPelaFaltaDeAtributos(){
        SaveUserDTO userSave = new SaveUserDTO();
        userSave.setName(user.getName());
        userSave.setPassword(user.getPassword());
        userSave.setCpf("");
        userSave.setStamps(user.getStamps());

        ResponseEntity<UserModel> userSaved = service.creatingUser(userSave);

        Assertions.assertEquals(HttpStatusCode.valueOf(400), userSaved.getStatusCode());

        System.out.println(userSaved.getStatusCode());
    }

    @Test
    void deveRetornarusuarioPeloCPF(){
        when(repository.findDataByCPF(user.getCpf())).thenReturn(user);

        ResponseEntity<UserModel> userSaved = service.getDataUser(user.getCpf());

        Assertions.assertEquals(user, userSaved.getBody());
        System.out.println(userSaved.getBody());
    }
}
