package com.example.bet.repository;

import com.example.bet.models.MoneyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRepository extends JpaRepository<MoneyModel,String> {
}
