package com.ecommerce;

import com.ecommerce.controllers.UserController;
import com.ecommerce.model.UserModel;
import com.ecommerce.service.UserServiceTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserTest {
    @Mock
    private UserController userController;

    @InjectMocks
    private UserServiceTest userService;

    @Test
    void validatingUserCreation() {
        UserModel userModel = new UserModel(1, "Bruno", "123", "6690867590", 70);
        Assertions.assertEquals("Bruno", userModel.getName());
        Assertions.assertEquals("123", userModel.getPassword());
        Assertions.assertEquals("6690867590", userModel.getCpf());
        Assertions.assertEquals(70, userModel.getStamps());

        System.out.println(userModel.toString());
    }
}
