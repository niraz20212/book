package org.example.bookmanagementsystem.service;

import org.example.bookmanagementsystem.entity.Member;

import java.util.List;

public interface MemberService {
    Member addMember(Member member);

    Member updateMember(Member member);

    void deleteMember(int id);

    List<Member> getAllMembers();

}
