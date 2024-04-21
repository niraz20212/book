package org.example.bookmanagementsystem.repository;

import org.example.bookmanagementsystem.entity.BookTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookTransactionRepository extends JpaRepository<BookTransaction, Long> {
}
