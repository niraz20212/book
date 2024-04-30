package org.example.bookmanagementsystem.repository;

import org.example.bookmanagementsystem.entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Integer> {
    @Query(value = "SELECT bt.active\n" +
            "FROM transaction_tbl bt" +
            "JOIN member_tbl m ON bt.member_id = m.id" +
            "WHERE m.id = ?;",nativeQuery = true)
    boolean existedByMemberIdAndActiveTrue(Integer memberId);
    @Query(value = "SELECT * FROM transaction_tbl WHERE active = false",nativeQuery = true)
    List<BookTransaction> findAllByActiveTrue();

}
