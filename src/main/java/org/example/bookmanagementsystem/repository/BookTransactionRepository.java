package org.example.bookmanagementsystem.repository;

import org.example.bookmanagementsystem.entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookTransactionRepository extends JpaRepository<BookTransaction, Integer> {
    @Query(value = "SELECT active FROM transaction_tbl where memberId=? ",nativeQuery = true)
    boolean existedByMemberIdAndActiveTrue(Integer memberId);


}
