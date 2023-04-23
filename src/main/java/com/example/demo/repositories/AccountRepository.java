package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.AccountEntity;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:25:34 PM
 */

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, String> {

}