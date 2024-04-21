package org.example.bookmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bookmanagementsystem.enums.RentStatus;

import java.util.Date;
@Entity
@Table(name = "book_Tbl")
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
    private Boolean active_close;
    @OneToOne(mappedBy = "member")
    private Member member;

}
