package com.springJwt.repository;

import com.springJwt.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
//    Optional<UserModel> findByEmailOrName(String email, String name);
      Optional<UserModel> findByEmail(String email);
}
