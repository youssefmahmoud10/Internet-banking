package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entities.TransactionEntity;

/**
 * @author YoussefMahmoud
 * @created Apr 23, 2023-4:26:53 PM
 */

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

}