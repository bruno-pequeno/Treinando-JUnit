package com.ecommerce.DTO;

import lombok.Data;

@Data
public class SaveUserDTO {
    private String name;
    private String cpf;
    private String password;
    private int stamps;
}
