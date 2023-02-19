package com.example.bet.repository;

import com.example.bet.models.WinningNumbersModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface WinningNumbersRepository extends JpaRepository<WinningNumbersModel, String> {

    List<WinningNumbersModel> findByDate(String date);

    Page<WinningNumbersModel> findByGameIdAndDate(Pageable pageable, String gameId, String date);
}
