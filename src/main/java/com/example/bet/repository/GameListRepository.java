package com.example.bet.repository;

import com.example.bet.models.GameListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameListRepository extends JpaRepository<GameListModel,String> {
}
