package com.example.bet.repository;

import com.example.bet.models.SlotModel;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<SlotModel,String> {
    List<SlotModel> findByGameId(Sort sort, String gameId);
}
