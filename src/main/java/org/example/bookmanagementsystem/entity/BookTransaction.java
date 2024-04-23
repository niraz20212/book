package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookmanagementsystem.enums.RentStatus;

import java.util.Date;

@Entity
@Table(name = "transaction_tbl")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String code;
    private Date fromDate;
    private Date toDate;
    @Enumerated(EnumType.STRING)
    private RentStatus rentStatus;
    private Boolean active;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
