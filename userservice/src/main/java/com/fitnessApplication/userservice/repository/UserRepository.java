package com.fitnessApplication.userservice.repository;

import com.fitnessApplication.userservice.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Boolean existsByEmail(String email);

}
