package com.example.bet.repository;

import com.example.bet.models.LoginModel;
import jakarta.persistence.Table;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Table(name = "login")
public interface LoginRepository extends CrudRepository<LoginModel,Long> {
}
