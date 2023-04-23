package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.UserEntity;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:22:30 PM
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {

}