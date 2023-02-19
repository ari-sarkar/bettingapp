package com.example.bet.repository;

import com.example.bet.models.MyBidsModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyBidsRepository extends JpaRepository<MyBidsModel,String> {

//    @Query(value = "Select p from my_bids where p.user_id = :userId")
//    Page<MyBidsModel> findByUserId(Pageable pageable, String userId);

    Page<MyBidsModel> findByUserAndGameId(Pageable pageable,String user, String gameId);

    Page<MyBidsModel> findAllByGameId(Pageable pageable, String gameId);

    Page<MyBidsModel> findByBajiNo(Pageable pageable, String bajiNo);
}
