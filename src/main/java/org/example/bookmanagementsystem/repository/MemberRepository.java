package org.example.bookmanagementsystem.repository;

import org.example.bookmanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
