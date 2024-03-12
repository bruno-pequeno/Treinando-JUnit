package com.ecommerce.repository;

import com.ecommerce.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {

    @Query(nativeQuery = true, value = "SELECT password FROM tb_users WHERE cpf = :cpf")
    public String findByCPF(String cpf);

    @Query(nativeQuery = true, value = "SELECT * FROM tb_users WHERE cpf = :cpf")
    UserModel findDataByCPF(String cpf);
}
